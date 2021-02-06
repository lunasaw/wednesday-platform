package com.luna.wednesday.hashcatcore.service;

import com.iteknical.wednesday.computing.dto.HashcatJobDTO;
import com.iteknical.wednesday.computing.dto.HashcatJobResultDTO;
import com.iteknical.wednesday.computing.dto.HashcatProjectDTO;
import com.luna.wednesday.platform.entity.ProjectDO;

public interface HashcatCoreService {

    /**
     * 创建tasks  内核行为
     *
     * @param projectDO
     *
     */
    void createHashcatTasks(ProjectDO projectDO);

    /**
     * 计算keyspace的工作  内核行为
     * @param projectDO
     * @return
     */
    HashcatJobDTO buildHashcatJobDTO4Keyspace(ProjectDO projectDO);

    /**
     * 构建Hashcat任务 内核行为
     * @param agentId
     * @param ProjectDO
     * @return
     */
    HashcatJobDTO buildHashcatJobDTO4Task(long agentId, ProjectDO ProjectDO);

    /**
     * 创建HashcatProject
     * @param sessionKey
     * @param hashcatProjectDTO
     * @return
     */
    long createHashcatProject(String sessionKey, HashcatProjectDTO hashcatProjectDTO);

    /**
     * 提交返回结果
     * 
     * @param sessionKey
     * @param agentId
     * @param hashcatJobResultDTO
     */
    void submitJobResult(String sessionKey, long agentId, HashcatJobResultDTO hashcatJobResultDTO);
}
