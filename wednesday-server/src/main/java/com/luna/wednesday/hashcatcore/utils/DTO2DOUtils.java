package com.luna.wednesday.hashcatcore.utils;

import com.luna.wednesday.hashcatcore.entity.HashDO;
import org.apache.commons.collections4.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.iteknical.wednesday.computing.dto.HashDTO;

/**
 * 外部DTO到内部DO的防腐层
 * 
 * @author Tony
 */
public class DTO2DOUtils {

    public static HashDO hashDTO2HashDO(HashDTO hashDTO) {
        HashDO hashDO = new HashDO();
        hashDO.setId(hashDTO.getId());
        hashDO.setCreateTime(hashDTO.getCreateTime());
        hashDO.setModifiedTime(hashDTO.getModifiedTime());
        hashDO.setVersion(hashDTO.getVersion());
        hashDO.setName(hashDTO.getName());
        if (CollectionUtils.isNotEmpty(hashDTO.getHashSet())) {
            hashDO.setContent(JSON.toJSONString(hashDTO.getHashSet()));
        }
        if (CollectionUtils.isNotEmpty(hashDTO.getHashResultSet())) {
            hashDO.setResult(JSON.toJSONString(hashDTO.getHashResultSet()));
        }
        return hashDO;
    }

}
