package com.luna.wednesday.platform.serveice.impl;

import com.luna.wednesday.platform.constant.ProjectStatusConstant;
import com.luna.wednesday.platform.constant.TaskStatusConstant;
import com.luna.wednesday.platform.dao.*;
import com.luna.wednesday.platform.entity.ProjectDO;
import com.luna.wednesday.platform.entity.TaskDO;
import com.luna.wednesday.platform.serveice.PlatformService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: wednesday-platform
 * @description: 平台任务接口实现
 * @author: luna
 * @create: 2021-02-04 15:30
 **/
@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private ProjectDAO    ProjectDO;
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
}
