package com.luna.wednesday.hashcatcore.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.iteknical.wednesday.computing.dto.HashDTO;
import com.iteknical.wednesday.computing.dto.constant.HashcatJobTypeConstant;
import com.luna.wednesday.hashcatcore.constant.HashcatProjectStatusConstant;
import com.luna.wednesday.hashcatcore.constant.HashcatTaskResultStatusConstant;
import com.luna.wednesday.hashcatcore.constant.HashcatTaskStatusConstant;
import com.luna.wednesday.hashcatcore.dao.HashcatHashModeConfigDAO;
import com.luna.wednesday.hashcatcore.dao.HashcatProjectDAO;
import com.luna.wednesday.hashcatcore.dao.HashcatTaskDAO;
import com.luna.wednesday.hashcatcore.entity.HashcatHashModeConfigDO;
import com.luna.wednesday.hashcatcore.entity.HashcatProjectDO;
import com.luna.wednesday.hashcatcore.entity.HashcatTaskDO;
import com.luna.wednesday.hashcatcore.service.HashService;
import com.luna.wednesday.hashcatcore.service.HashcatCoreService;
import com.luna.wednesday.platform.dao.*;
import com.luna.wednesday.platform.entity.ProjectDO;
import com.luna.wednesday.platform.entity.SpeedDO;
import com.luna.wednesday.platform.entity.TaskDO;
import com.luna.wednesday.platform.entity.TaskResultDO;
import com.luna.wednesday.platform.serveice.impl.PlatformBaseService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iteknical.wednesday.computing.dto.HashcatJobDTO;
import com.iteknical.wednesday.computing.dto.HashcatJobResultDTO;
import com.iteknical.wednesday.computing.dto.HashcatProjectDTO;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author luna
 */
@Service
public class HashcatHashcatCoreServiceImpl implements HashcatCoreService {
    @Autowired
    private HashcatProjectDAO        hashcatProjectDAO;
    @Autowired
    private HashcatTaskDAO           hashcatTaskDAO;
    @Autowired
    private TaskResultDAO            taskResultDAO;
    @Autowired
    private HashcatHashModeConfigDAO hashcatHashModeConfigDAO;
    @Autowired
    private StatusDAO                StatusDAO;
    @Autowired
    private StatusLogDAO             StatusLogDAO;
    @Autowired
    private SpeedDAO                 speedDAO;
    @Autowired
    private TaskDAO                  taskDAO;
    @Autowired
    private ProjectDAO               projectDAO;
    @Autowired
    private HashService              hashService;
    @Autowired
    PlatformBaseService              platformBaseService;

    private final static Logger      logger = LoggerFactory.getLogger(HashcatHashcatCoreServiceImpl.class);

    @Override
    public void createHashcatTasks(ProjectDO projectDO) {
        HashcatProjectDO hashcatProjectDO = hashcatProjectDAO.getByProjectId(projectDO.getId());
        HashcatHashModeConfigDO hashcatHashModeConfigDO = hashcatHashModeConfigDAO.get(hashcatProjectDO.getHashMode());

        // task数=project总行数/此种hashMode的每个Task的行数+1
        long taskCount = hashcatProjectDO.getLines() / hashcatHashModeConfigDO.getLinesPerTask() + 1;
        // 每个task的keyspace大小=总keyspace数/task数
        long keyspacePerTask = hashcatProjectDO.getKeyspace() / taskCount;

        // for debug
        logger.info("calculated taskCount={}, keyspacePerTask={}", taskCount, keyspacePerTask);

        // 布置tasks
        long skip = 0L;
        do {
            HashcatTaskDO hashcatTaskDO = new HashcatTaskDO();
            TaskDO taskDO = new TaskDO();
            taskDO.setStatus(HashcatTaskStatusConstant.WAIT);
            taskDAO.insert(taskDO);
            hashcatTaskDO.setTaskId(taskDO.getId());
            hashcatTaskDO.setSkip(skip);
            if (skip + keyspacePerTask > hashcatProjectDO.getKeyspace()) {
                hashcatTaskDO.setLimit(hashcatProjectDO.getKeyspace() - skip + 1);
            } else {
                hashcatTaskDO.setLimit(keyspacePerTask);
            }
            skip = skip + keyspacePerTask;
            hashcatTaskDAO.insert(hashcatTaskDO);
        } while (skip < hashcatProjectDO.getKeyspace());
    }

    @Override
    public HashcatJobDTO buildHashcatJobDTO4Keyspace(ProjectDO projectDO) {
        // TODO projectDO-> HashcatProjeckDO
        HashcatProjectDO hashcatProjectDO = hashcatProjectDAO.getByProjectId(projectDO.getId());
        HashcatJobDTO hashcatJobDTO = new HashcatJobDTO();
        hashcatJobDTO.setType(HashcatJobTypeConstant.KEYSPACE);
        hashcatJobDTO.setHashMode(hashcatProjectDO.getHashMode());
        hashcatJobDTO.setAttackMode(hashcatProjectDO.getAttackMode());
        hashcatJobDTO.setMask(hashcatProjectDO.getMask());

        // 更新project状态
        List<HashcatProjectDO> hashcatProjectDOList =
            hashcatProjectDAO.listByHashModeAndAttackModeAndMask(
                hashcatProjectDO.getHashMode(), hashcatProjectDO.getAttackMode(), hashcatProjectDO.getMask());
        hashcatProjectDOList.forEach(eachHashcatProjectDO -> {
            ProjectDO oneByStatusAndProjectId =
                projectDAO.getOneByStatusAndProjectId(HashcatProjectStatusConstant.INIT, eachHashcatProjectDO.getId());
            oneByStatusAndProjectId.setProjectStatus(HashcatProjectStatusConstant.CALCULATING_KEYSPACE);
            projectDAO.update(oneByStatusAndProjectId);
        });

        return hashcatJobDTO;
    }

    @Override
    public HashcatJobDTO buildHashcatJobDTO4Task(long agentId, ProjectDO projectDO) {
        // 计算应该分配给这个agent的task数目
        int taskSize = 1;
        SpeedDO SpeedDO =
            speedDAO.getByAgentIdAndProjectId(agentId, projectDO.getId());
        if (SpeedDO != null) {
            taskSize = 5 * 60 * SpeedDO.getTaskSize() / SpeedDO.getRunningSecond();
            // 最少分配1个
            if (taskSize == 0) {
                taskSize = 1;
            }
        }
        logger.info("get taskSize, agentid={}, taskSize={}", agentId, taskSize);

        List<HashcatTaskDO> hashcatTaskDOList = listLongestConsecutiveUnfinishedTaskList(taskSize);
        if (CollectionUtils.isEmpty(hashcatTaskDOList)) {
            return null;
        }
        logger.info("listLongestConsecutiveUnfinishedTaskList success, agentid={}, taskSize={}, taskListSize={}",
            agentId, taskSize, hashcatTaskDOList.size());

        HashcatJobDTO hashcatJobDTO = new HashcatJobDTO();
        HashcatProjectDO byProjectId = hashcatProjectDAO.getByProjectId(projectDO.getId());
        hashcatJobDTO.setType(HashcatJobTypeConstant.TASK);
        hashcatJobDTO.setHashMode(byProjectId.getHashMode());
        hashcatJobDTO.setAttackMode(byProjectId.getAttackMode());
        hashcatJobDTO.setMask(byProjectId.getMask());

        hashcatJobDTO.setSkip(hashcatTaskDOList.get(0).getSkip());
        hashcatJobDTO.setLimit(hashcatTaskDOList.get(0).getLimit() * hashcatTaskDOList.size());

        HashDTO hashDTO = hashService.get(byProjectId.getHashId());
        hashcatJobDTO.setHashSet(hashDTO.getHashSet());

        Set<Long> taskResultIdSet = Sets.newHashSet();
        hashcatTaskDOList.forEach(hashcatTaskDO -> {
            TaskResultDO hashcatTaskResultDO = new TaskResultDO();
            hashcatTaskResultDO.setTaskId(hashcatTaskDO.getId());
            hashcatTaskResultDO.setAgentId(agentId);
            hashcatTaskResultDO.setStatus(HashcatTaskResultStatusConstant.RUNNING);
            // 插入taskResult
            taskResultDAO.insert(hashcatTaskResultDO);
            taskResultIdSet.add(hashcatTaskResultDO.getId());

            // 更新task
            TaskDO taskDO = taskDAO.get(hashcatTaskDO.getTaskId());
            taskDO.setStatus(HashcatTaskStatusConstant.RUNNING);
            taskDAO.update(taskDO);
        });

        // 更新project（如果需要）
        if (StringUtils.equals(projectDO.getProjectStatus(), HashcatProjectStatusConstant.WAIT)) {
            projectDO.setProjectStatus(HashcatProjectStatusConstant.RUNNING);
            // 这儿的更新如果并发不影响，所以不做并发判断
            projectDAO.update(projectDO);
        }

        hashcatJobDTO.setTaskResultIdSet(taskResultIdSet);
        return hashcatJobDTO;
    }

    private List<HashcatTaskDO> listLongestConsecutiveUnfinishedTaskList(int taskSize) {
        List<TaskDO> taskDOList = platformBaseService.listUnfinishedTaskList(taskSize);
        List<HashcatTaskDO> hashcatTaskDOList = Lists.newLinkedList();
        taskDOList.forEach(taskDO -> {
            HashcatTaskDO hashcatTaskDO = hashcatTaskDAO.get(taskDO.getId());
            hashcatTaskDOList.add(hashcatTaskDO);
        });
        return longestConsecutiveTask(hashcatTaskDOList);
    }

    public List<HashcatTaskDO> longestConsecutiveTask(List<HashcatTaskDO> hashcatTaskDOList) {
        if (CollectionUtils.isEmpty(hashcatTaskDOList)) {
            return null;
        }

        List<Long> idList =
            hashcatTaskDOList.stream().map(HashcatTaskDO::getId).collect(Collectors.toList());
        List<Long> longestConsecutiveList = getLongestConsecutive(idList);

        List<HashcatTaskDO> resultHashcatTaskDOList = Lists.newArrayList();
        for (long id : longestConsecutiveList) {
            for (HashcatTaskDO hashcatTaskDO : hashcatTaskDOList) {
                if (hashcatTaskDO.getId() == id) {
                    resultHashcatTaskDOList.add(hashcatTaskDO);
                }
            }
        }

        return resultHashcatTaskDOList;
    }

    public static List<Long> getLongestConsecutive(List<Long> longList) {
        List<Long> tempLongList = Lists.newArrayList(longList);
        Collections.sort(tempLongList);

        int startIndex = 0;
        int endIndex = 0;
        int maxLength = 0;
        for (int i = 0; i < tempLongList.size(); i++) {
            int tempStartIndex = i;
            int tempEndIndex = i;
            while (i < tempLongList.size() - 1 && tempLongList.get(i) + 1L == tempLongList.get(i + 1)) {
                tempEndIndex++;
                i++;
            }
            if (tempEndIndex - tempStartIndex + 1 > maxLength) {
                startIndex = tempStartIndex;
                endIndex = tempEndIndex;
            }
        }

        return tempLongList.subList(startIndex, endIndex + 1);
    }

    @Override
    public long createHashcatProject(String sessionKey, HashcatProjectDTO hashcatProjectDTO) {
        return 0;
    }

    @Override
    public void submitJobResult(String sessionKey, long agentId, HashcatJobResultDTO hashcatJobResultDTO) {

    }
}
