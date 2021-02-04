package com.luna.wednesday.hashcatcore.service.impl;

import com.luna.wednesday.hashcatcore.service.HashcatCoreService;
import com.luna.wednesday.platform.entity.ProjectDO;
import org.springframework.stereotype.Service;
import com.iteknical.wednesday.computing.dto.HashcatJobDTO;
import com.iteknical.wednesday.computing.dto.HashcatJobResultDTO;
import com.iteknical.wednesday.computing.dto.HashcatProjectDTO;


/**
 * @author luna
 */
@Service
public class HashcatHashcatCoreServiceImpl implements HashcatCoreService {

    @Override
    public void createHashcatTasks(ProjectDO projectDO) {

    }

    @Override
    public HashcatJobDTO buildHashcatJobDTO4Keyspace(ProjectDO projectDO) {
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
