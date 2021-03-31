package com.luna.wednesday.platform.utils;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.luna.wednesday.platform.dto.HashDTO;
import com.luna.wednesday.platform.dto.HashcatProjectDTO;
import com.luna.wednesday.platform.dto.HashcatTaskDTO;
import com.luna.wednesday.platform.entity.CalculationObjectDO;
import com.luna.wednesday.platform.entity.ProjectDO;
import com.luna.wednesday.platform.entity.TaskDO;

/**
 * @author luna@mac
 * @className DTO2DOUtils.java
 * @description TODO
 * @createTime 2021年02月25日 14:40:00
 */
public class DTO2DOUtils {

    public static CalculationObjectDO hashDTO2CalculationObjectDO(HashDTO hashDTO) {
        if (hashDTO == null) {
            return null;
        }
        CalculationObjectDO calculationObjectDO = new CalculationObjectDO();
        calculationObjectDO.setId(hashDTO.getId());
        calculationObjectDO.setCreateTime(hashDTO.getCreateTime());
        calculationObjectDO.setModifiedTime(hashDTO.getModifiedTime());
        calculationObjectDO.setVersion(hashDTO.getVersion());
        calculationObjectDO.setType(hashDTO.getType());
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("found", hashDTO.getFound());
        map.put("left", hashDTO.getLeft());
        calculationObjectDO.setContent(JSON.toJSONString(map));
        calculationObjectDO.setRemarks(hashDTO.getRemarks());
        return calculationObjectDO;
    }

    public static ProjectDO hashcatProjectDTO2ProjectDO(HashcatProjectDTO hashcatProjectDTO) {
        if (hashcatProjectDTO == null) {
            return null;
        }
        ProjectDO projectDO = new ProjectDO();
        projectDO.setId(hashcatProjectDTO.getId());
        projectDO.setCreateTime(hashcatProjectDTO.getCreateTime());
        projectDO.setModifiedTime(hashcatProjectDTO.getModifiedTime());
        projectDO.setVersion(hashcatProjectDTO.getVersion());
        projectDO.setType(hashcatProjectDTO.getType());
        projectDO.setCalculationObjectId(hashcatProjectDTO.getCalculationObjectId());
        projectDO.setStatus(hashcatProjectDTO.getStatus());
        projectDO.setContent(JSON.toJSONString(
            ImmutableMap.of("keyspace", hashcatProjectDTO.getKeyspace(), "mask", hashcatProjectDTO.getMask(),
                "attackMode",
                hashcatProjectDTO.getAttackMode(), "lines", hashcatProjectDTO.getLines())));
        projectDO.setRemarks(hashcatProjectDTO.getRemarks());
        return projectDO;
    }

    public static TaskDO hashcatTaskDTOTaskDO(HashcatTaskDTO hashcatTaskDTO) {
        if (hashcatTaskDTO == null) {
            return null;
        }
        TaskDO taskDO = new TaskDO();
        taskDO.setId(hashcatTaskDTO.getId());
        taskDO.setCreateTime(hashcatTaskDTO.getCreateTime());
        taskDO.setModifiedTime(hashcatTaskDTO.getModifiedTime());
        taskDO.setVersion(hashcatTaskDTO.getVersion());
        taskDO.setProjectId(hashcatTaskDTO.getProjectId());
        taskDO.setStatus(hashcatTaskDTO.getStatus());
        taskDO.setContent(JSON.toJSONString(
            ImmutableMap.of("limit", hashcatTaskDTO.getLimit(), "skip", hashcatTaskDTO.getSkip())));
        return taskDO;
    }
}
