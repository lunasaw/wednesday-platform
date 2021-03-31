package com.luna.wednesday.platform.service;

import com.luna.wednesday.calculate.constant.HashcatTaskStatusConstant;
import com.luna.wednesday.platform.hashcat.entity.HashcatHashModeConfigDO;
import com.luna.wednesday.platform.utils.computing.HashcatUtils;
import com.luna.wednesday.tools.UserTools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luna.wednesday.platform.dao.CalculationObjectDAO;
import com.luna.wednesday.platform.dao.ProjectDAO;
import com.luna.wednesday.platform.dao.TaskDAO;
import com.luna.wednesday.platform.dto.HashcatProjectDTO;
import com.luna.wednesday.platform.dto.HashcatTaskDTO;
import com.luna.wednesday.platform.entity.ProjectDO;
import com.luna.wednesday.platform.hashcat.dao.HashcatHashModeConfigDAO;
import com.luna.wednesday.platform.utils.DO2DTOUtils;
import com.luna.wednesday.platform.utils.DTO2DOUtils;

/**
 * @author luna@mac
 * @className HashcatProjectService.java
 * @description TODO
 * @createTime 2021年03月30日 19:01:00
 */
@Service
public class HashcatProjectService {

    private static final Logger      logger = LoggerFactory.getLogger(HashcatProjectService.class);

    @Autowired
    private ProjectDAO               projectDAO;

    @Autowired
    private TaskDAO                  taskDAO;

    @Autowired
    private CalculationObjectDAO     calculationObjectDAO;

    @Autowired
    private HashcatHashModeConfigDAO hashcatHashModeConfigDAO;

    @Autowired
    private UserTools                userTools;

    /**
     * 创建hashcatProject
     * 
     * @param sessionKey
     * @param hashcatProjectDTO
     * @return
     */
    public long createHashcatProject(String sessionKey, HashcatProjectDTO hashcatProjectDTO) {
        userTools.isAdmin(sessionKey);
        long l = HashcatUtils.calculateMaskLines(hashcatProjectDTO.getMask());
        hashcatProjectDTO.setLines(l);
        ProjectDO projectDO = DTO2DOUtils.hashcatProjectDTO2ProjectDO(hashcatProjectDTO);
        projectDAO.insert(projectDO);
        return projectDO.getId();
    }

    /**
     * 创建tasks
     *
     * @param projectDO
     */
    private void createHashcatTasks(ProjectDO projectDO) {
        HashcatProjectDTO hashcatProjectDTO = DO2DTOUtils.projectDO2HashcatProjectDTO(projectDO);
        HashcatHashModeConfigDO hashcatHashModeConfigDO =
            hashcatHashModeConfigDAO.get(hashcatProjectDTO.getAttackMode());

        // task数=project总行数/此种hashMode的每个Task的行数+1
        long taskCount = hashcatProjectDTO.getLines() / hashcatHashModeConfigDO.getLinesPerTask() + 1;
        // 每个task的keyspace大小=总keyspace数/task数
        long keyspacePerTask = hashcatProjectDTO.getKeyspace() / taskCount;

        // for debug
        logger.info("calculated taskCount={}, keyspacePerTask={}", taskCount, keyspacePerTask);

        // 布置tasks
        long skip = 0L;
        do {
            HashcatTaskDTO hashcatTaskDTO = new HashcatTaskDTO();
            hashcatTaskDTO.setProjectId(projectDO.getId());
            hashcatTaskDTO.setStatus(HashcatTaskStatusConstant.WAIT);
            hashcatTaskDTO.setSkip(skip);
            if (skip + keyspacePerTask > hashcatProjectDTO.getKeyspace()) {
                hashcatTaskDTO.setLimit(hashcatProjectDTO.getKeyspace() - skip + 1);
            } else {
                hashcatTaskDTO.setLimit(keyspacePerTask);
            }
            skip = skip + keyspacePerTask;
            taskDAO.insert(DTO2DOUtils.hashcatTaskDTOTaskDO(hashcatTaskDTO));
        } while (skip < hashcatProjectDTO.getKeyspace());
    }
}
