package com.luna.wednesday.platform.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luna.wednesday.platform.dto.HashDTO;
import com.luna.wednesday.platform.dto.HashcatProjectDTO;
import com.luna.wednesday.platform.dto.HashcatTaskDTO;
import com.luna.wednesday.platform.entity.CalculationObjectDO;
import com.luna.wednesday.platform.entity.ProjectDO;
import com.luna.wednesday.platform.entity.TaskDO;

/**
 * @author luna@mac
 * @className DO2DTOUtils.java
 * @description TODO
 * @createTime 2021年02月25日 14:31:00
 */
public class DO2DTOUtils {

    public static HashDTO calculationObjectDO2HashDTO(CalculationObjectDO calculationObjectDO) {
        if (calculationObjectDO == null) {
            return null;
        }

        HashDTO hashDTO = JSON.parseObject(calculationObjectDO.getContent(), HashDTO.class);
        hashDTO.setId(calculationObjectDO.getId());
        hashDTO.setCreateTime(calculationObjectDO.getCreateTime());
        hashDTO.setModifiedTime(calculationObjectDO.getModifiedTime());
        hashDTO.setVersion(calculationObjectDO.getVersion());
        hashDTO.setType(calculationObjectDO.getType());
        hashDTO.setRemarks(calculationObjectDO.getRemarks());
        return hashDTO;
    }

    public static HashcatProjectDTO projectDO2HashcatProjectDTO(ProjectDO projectDO) {
        if (projectDO == null) {
            return null;
        }
        HashcatProjectDTO hashcatProjectDTO = new HashcatProjectDTO();
        hashcatProjectDTO.setId(projectDO.getId());
        hashcatProjectDTO.setCreateTime(projectDO.getCreateTime());
        hashcatProjectDTO.setModifiedTime(projectDO.getModifiedTime());
        hashcatProjectDTO.setVersion(projectDO.getVersion());
        hashcatProjectDTO.setType(projectDO.getType());
        hashcatProjectDTO.setCalculationObjectId(projectDO.getCalculationObjectId());
        hashcatProjectDTO.setStatus(projectDO.getStatus());

        JSONObject jsonObject = JSON.parseObject(projectDO.getContent());
        hashcatProjectDTO.setKeyspace(jsonObject.getLong("keyspace"));
        hashcatProjectDTO.setMask(jsonObject.getString("mask"));
        hashcatProjectDTO.setLines(jsonObject.getLong("lines"));
        hashcatProjectDTO.setAttackMode(jsonObject.getInteger("attackMode"));
        hashcatProjectDTO.setRemarks(projectDO.getRemarks());
        return hashcatProjectDTO;
    }

    public static HashcatTaskDTO taskDO2HashcatTaskDTO(TaskDO taskDO) {
        if (taskDO == null) {
            return null;
        }
        JSONObject jsonObject = JSON.parseObject(taskDO.getContent());
        HashcatTaskDTO hashcatTaskDTO = new HashcatTaskDTO();
        hashcatTaskDTO.setSkip(jsonObject.getLong("skip"));
        hashcatTaskDTO.setLimit(jsonObject.getLong("limit"));
        hashcatTaskDTO.setId(taskDO.getId());
        hashcatTaskDTO.setCreateTime(taskDO.getCreateTime());
        hashcatTaskDTO.setModifiedTime(taskDO.getModifiedTime());
        hashcatTaskDTO.setVersion(taskDO.getVersion());
        hashcatTaskDTO.setProjectId(taskDO.getProjectId());
        hashcatTaskDTO.setStatus(taskDO.getStatus());
        return hashcatTaskDTO;

    }
}
