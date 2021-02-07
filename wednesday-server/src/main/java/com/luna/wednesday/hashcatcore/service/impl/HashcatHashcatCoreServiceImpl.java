package com.luna.wednesday.hashcatcore.service.impl;

import com.luna.wednesday.hashcatcore.constant.HashcatTaskStatusConstant;
import com.luna.wednesday.hashcatcore.dao.HashcatHashModeConfigDAO;
import com.luna.wednesday.hashcatcore.dao.HashcatProjectDAO;
import com.luna.wednesday.hashcatcore.dao.HashcatTaskDAO;
import com.luna.wednesday.hashcatcore.entity.HashcatHashModeConfigDO;
import com.luna.wednesday.hashcatcore.entity.HashcatProjectDO;
import com.luna.wednesday.hashcatcore.entity.HashcatTaskDO;
import com.luna.wednesday.hashcatcore.service.HashcatCoreService;
import com.luna.wednesday.platform.dao.*;
import com.luna.wednesday.platform.entity.ProjectDO;
import com.luna.wednesday.platform.entity.TaskDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.iteknical.wednesday.computing.dto.HashcatJobDTO;
import com.iteknical.wednesday.computing.dto.HashcatJobResultDTO;
import com.iteknical.wednesday.computing.dto.HashcatProjectDTO;

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
        return null;
    }

    @Override
    public HashcatJobDTO buildHashcatJobDTO4Task(long agentId, ProjectDO ProjectDO) {
        return null;
    }

    @Override
    public long createHashcatProject(String sessionKey, HashcatProjectDTO hashcatProjectDTO) {
        return 0;
    }

    @Override
    public void submitJobResult(String sessionKey, long agentId, HashcatJobResultDTO hashcatJobResultDTO) {

    }
}
