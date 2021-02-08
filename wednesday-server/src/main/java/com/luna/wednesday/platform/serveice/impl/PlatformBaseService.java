package com.luna.wednesday.platform.serveice.impl;

import com.iteknical.fusion.user.constant.UserTagNameConstant;
import com.iteknical.fusion.user.vo.TagVO;
import com.luna.wednesday.hashcatcore.constant.HashcatProjectStatusConstant;
import com.luna.wednesday.hashcatcore.entity.HashcatProjectDO;
import com.luna.wednesday.hashcatcore.entity.HashcatTaskDO;
import com.luna.wednesday.platform.constant.ProjectStatusConstant;
import com.luna.wednesday.platform.constant.TaskResultStatusConstant;
import com.luna.wednesday.platform.constant.TaskStatusConstant;
import com.luna.wednesday.platform.dao.*;
import com.luna.wednesday.platform.dto.JobResultDTO;
import com.luna.wednesday.platform.dto.StatusDTO;
import com.luna.wednesday.platform.entity.*;
import com.luna.wednesday.platform.serveice.PlatformService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: wednesday-platform
 * @description: 平台任务接口实现
 * @author: luna
 * @create: 2021-02-04 15:30
 **/
@Service
public class PlatformBaseService {

    @Autowired
    private ProjectDAO    ProjectDAO;
    @Autowired
    private TaskDAO       TaskDAO;
    @Autowired
    private TaskResultDAO TaskResultDAO;
    @Autowired
    private StatusDAO     StatusDAO;
    @Autowired
    private StatusLogDAO  StatusLogDAO;
    @Autowired
    private SpeedDAO      SpeedDAO;

    private TaskDO getUnfinishedTask() {
        // project上优先派发RUNNING的
        List<ProjectDO> ProjectDOList =
            ProjectDAO.listByStatus(ProjectStatusConstant.RUNNING);
        if (CollectionUtils.isNotEmpty(ProjectDOList)) {
            for (ProjectDO ProjectDO : ProjectDOList) {
                // task上优先派发WAIT的，TIMEOUT的虽然超时但是也有可能后面收到结果
                TaskDO TaskDO =
                    TaskDAO.getOneByProjectIdAndStatus(ProjectDO.getId(), TaskStatusConstant.WAIT);
                if (TaskDO != null) {
                    return TaskDO;
                }
                TaskDO =
                    TaskDAO.getOneByProjectIdAndStatus(ProjectDO.getId(),
                        TaskStatusConstant.TIMEOUT);
                if (TaskDO != null) {
                    return TaskDO;
                }
            }
        }

        ProjectDOList = ProjectDAO.listByStatus(ProjectStatusConstant.WAIT);
        if (CollectionUtils.isNotEmpty(ProjectDOList)) {
            for (ProjectDO ProjectDO : ProjectDOList) {
                // task上优先派发WAIT的，TIMEOUT的虽然超时但是也有可能后面收到结果
                TaskDO TaskDO =
                    TaskDAO.getOneByProjectIdAndStatus(ProjectDO.getId(), TaskStatusConstant.WAIT);
                if (TaskDO != null) {
                    return TaskDO;
                }
                TaskDO =
                    TaskDAO.getOneByProjectIdAndStatus(ProjectDO.getId(),
                        TaskStatusConstant.TIMEOUT);
                if (TaskDO != null) {
                    return TaskDO;
                }
            }
        }

        return null;
    }

    /**
     * 捞出taskSize大小的taskList
     * <p>
     * 取自同一个projectId
     * </p>
     *
     * @param taskSize
     * @return
     */
    public List<TaskDO> listUnfinishedTaskList(int taskSize) {
        // project上优先派发RUNNING的
        List<ProjectDO> ProjectDOList =
            ProjectDAO.listByStatus(ProjectStatusConstant.RUNNING);
        List<TaskDO> TaskDOList =
            listUnfinishedTaskListByProjectDOList(ProjectDOList, taskSize);
        if (CollectionUtils.isNotEmpty(TaskDOList)) {
            return TaskDOList;
        }

        ProjectDOList = ProjectDAO.listByStatus(ProjectStatusConstant.WAIT);
        TaskDOList = listUnfinishedTaskListByProjectDOList(ProjectDOList, taskSize);
        if (CollectionUtils.isNotEmpty(TaskDOList)) {
            return TaskDOList;
        }

        return null;
    }

    /**
     *
     * @param ProjectDOList 实际取的是projectDOList中第一个有未完成task的taskList
     * @param taskSize
     * @return
     */
    private List<TaskDO>
        listUnfinishedTaskListByProjectDOList(List<ProjectDO> ProjectDOList, int taskSize) {
        if (CollectionUtils.isNotEmpty(ProjectDOList)) {
            for (ProjectDO ProjectDO : ProjectDOList) {
                // task上优先派发WAIT的，TIMEOUT的虽然超时但是也有可能后面收到结果
                List<TaskDO> TaskDOList = TaskDAO
                    .listByProjectIdAndStatusWithLimit(ProjectDO.getId(), TaskStatusConstant.WAIT,
                        taskSize);
                if (CollectionUtils.isNotEmpty(TaskDOList)) {
                    return TaskDOList;
                }
                TaskDOList =
                    TaskDAO.listByProjectIdAndStatusWithLimit(ProjectDO.getId(),
                        TaskStatusConstant.TIMEOUT, taskSize);
                if (CollectionUtils.isNotEmpty(TaskDOList)) {
                    return TaskDOList;
                }
            }
        }
        return null;
    }

    /**
     * 判断当前有无任务执行
     * 
     * @param sessionKey
     * @param agentId
     * @return
     */
    public boolean hasJob(String sessionKey, long agentId) {
        // userTools.getOneUserIdBySessionKey(sessionKey);
        // TODO 校验agent是否是这个用户登录的

        // 在测试时，只允许打标用户参与运算
        // if (switchService.isHashcatTest()) {
        // TagVO tagVO = new TagVO();
        // tagVO.setName(UserTagNameConstant.IS_PASSWORD_EXPIRE);
        // if (!userTagSupport.hasTag(sessionKey, tagVO)) {
        // return false;
        // }
        // }

        ProjectDO ProjectDO = ProjectDAO.getOneByStatus(ProjectStatusConstant.INIT);
        if (ProjectDO != null) {
            return true;
        }

        TaskDO TaskDO = getUnfinishedTask();
        return TaskDO != null;
    }

    /**
     * 扫描project表，状态为CALCULATING_KEYSPACE且没有keyspace且超过1分钟还没有变为WAIT，说明客户端超时，回退回INIT状态
     * 平台行为
     */
    private void scanProject4Rollback2Init() {
        List<ProjectDO> ProjectDOList =
            ProjectDAO.listByStatus(ProjectStatusConstant.CALCULATING_KEYSPACE);
        if (CollectionUtils.isNotEmpty(ProjectDOList)) {
            // 回退INIT
            ProjectDOList.forEach(ProjectDO -> {
                // TODO 这里原来有 ProjectDO.getKeyspace() == null 条件
                if (ProjectDO.getModifiedTime().before(DateTime.now().minusMinutes(1).toDate())) {
                    ProjectDO.setProjectStatus(ProjectStatusConstant.INIT);
                    ProjectDAO.update(ProjectDO);
                }
            });
        }
    }

    /**
     * 扫描project表，状态为PENDING则创造task
     * 平台行为
     * createTasks 作为一个接口
     */
    private void scanProject4CreateTasks() {
        List<ProjectDO> ProjectDOList =
            ProjectDAO.listByStatus(ProjectStatusConstant.PENDING);
        if (CollectionUtils.isNotEmpty(ProjectDOList)) {
            // 创造task
            ProjectDOList.forEach(ProjectDO -> {
                // TODO CoreServiceImpl.createTasks(ProjectDO);
                // 更新project状态
                ProjectDO.setProjectStatus(ProjectStatusConstant.WAIT);
                ProjectDAO.update(ProjectDO);
            });
        }
    }

    /**
     * 扫描project表，推向finish
     *
     * 平台行为
     */
    private void scanProject4Push2Finish() {
        List<ProjectDO> ProjectDOList =
            ProjectDAO.listByStatus(ProjectStatusConstant.RUNNING);
        ProjectDOList.forEach(ProjectDO -> {
            // 如果没有一个task不是FINISH状态，说明所有task都是FINISH，说明project是FINISH
            if (null == TaskDAO.getOneByProjectIdAndNotStatus(ProjectDO.getId(),
                TaskStatusConstant.FINISH)) {
                ProjectDO.setProjectStatus(ProjectStatusConstant.FINISH);
                ProjectDAO.update(ProjectDO);
            }
        });

    }

    /**
     * 扫描taskResult表，状态为RUNNING且10分钟都没有变为FINISH，说明客户端超时，置为TIMEOUT
     * 接着扫描task表，如果一个task对于的所有taskResult均为TIMEOUT，则task置为TIMEOUT
     *
     * 平台行为
     */
    @Scheduled(fixedDelay = 1000 * 60)
    public void scanTaskResultAndTask4Timeout() {
        scanTaskResult4Timeout();
        scanTask4Timeout();
    }

    private void scanTaskResult4Timeout() {
        List<TaskResultDO> TaskResultDOList =
            TaskResultDAO.listByStatus(TaskResultStatusConstant.RUNNING);
        if (CollectionUtils.isEmpty(TaskResultDOList)) {
            return;
        }
        TaskResultDOList.forEach(TaskResultDO -> {
            // 10分钟以前的任务都标记为超时
            if (TaskResultDO.getModifiedTime().before(DateTime.now().minusMinutes(10).toDate())) {
                TaskResultDO.setStatus(TaskResultStatusConstant.TIMEOUT);
                TaskResultDAO.update(TaskResultDO);
            }
        });
    }

    /**
     * 扫描task表，如果一个task对于的所有taskResult均为TIMEOUT，则task置为TIMEOUT
     *
     * 平台行为
     */
    private void scanTask4Timeout() {
        List<TaskDO> TaskDOList = TaskDAO.listByStatus(TaskStatusConstant.RUNNING);
        if (CollectionUtils.isEmpty(TaskDOList)) {
            return;
        }
        TaskDOList.forEach(TaskDO -> {
            List<TaskResultDO> TaskResultDOList =
                TaskResultDAO.listByTaskId(TaskDO.getId());
            if (CollectionUtils.isEmpty(TaskResultDOList)) {
                return;
            }
            // 判断所有的taskResult为TIMEOUT，则task为TIMEOUT
            List<TaskResultDO> filterTaskResultDOList =
                TaskResultDOList.stream().filter(TaskResultDO -> StringUtils
                    .equals(TaskResultDO.getStatus(), TaskResultStatusConstant.TIMEOUT))
                    .collect(Collectors.toList());
            if (CollectionUtils.isNotEmpty(filterTaskResultDOList)
                && filterTaskResultDOList.size() == TaskResultDOList.size()) {
                TaskDO.setStatus(TaskStatusConstant.TIMEOUT);
                TaskDAO.update(TaskDO);
            }
        });
    }

    public long putStatusDTO(String sessionKey, long agentId, StatusDTO StatusDTO) {
        StatusDO StatusDO = new StatusDO();
        StatusDO.setAgentId(agentId);
        StatusDO.setContent(StatusDTO.getContent());

        StatusDO originStatusDO =
            StatusDAO.getByAgentId(agentId);
        if (originStatusDO != null) {
            StatusDO.setId(originStatusDO.getId());
            StatusDO.setVersion(originStatusDO.getVersion());
            StatusDAO.update(StatusDO);
        } else {
            StatusDAO.insert(StatusDO);
        }

        StatusLogDO StatusLogDO = new StatusLogDO();
        StatusLogDO.setAgentId(StatusDO.getAgentId());
        StatusLogDO.setContent(StatusDO.getContent());
        StatusLogDAO.insert(StatusLogDO);

        return StatusDO.getId();
    }

    /**
     * 记录速度应该是平台行为 但是跟内核算法有关
     * 
     * @param sessionKey
     * @param agentId
     * @param JobResultDTO
     */
    public void submitJobResult(String sessionKey, long agentId, JobResultDTO JobResultDTO) {
        // 记录speed
        SpeedDO SpeedDO = new SpeedDO();
        SpeedDO.setAgentId(agentId);
        SpeedDO.setProjectId(JobResultDTO.getProjectId());
        SpeedDO.setTaskSize(JobResultDTO.getTaskResultIdSet().size());
        SpeedDO.setRunningSecond(JobResultDTO.getRunningSecond());
        putSpeedDO(SpeedDO);
    }

    /**
     * 更新速度应该是平台行为 但是跟内核算法有关
     * TODO hashcat 为 hashmode + agent 保证速度唯一性 这里使用 agent + projectId 保证唯一性
     * 
     * @param SpeedDO
     */
    private void putSpeedDO(SpeedDO SpeedDO) {
        SpeedDO originSpeedDO =
            SpeedDAO.getByAgentIdAndProjectId(SpeedDO.getAgentId(), SpeedDO.getProjectId());
        if (originSpeedDO != null) {
            SpeedDO.setId(originSpeedDO.getId());
            SpeedDO.setVersion(originSpeedDO.getVersion());
            SpeedDAO.update(SpeedDO);
        } else {
            SpeedDAO.insert(SpeedDO);
        }
    }
}
