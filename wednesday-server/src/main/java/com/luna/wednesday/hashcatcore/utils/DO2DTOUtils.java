package com.luna.wednesday.hashcatcore.utils;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;

import com.luna.wednesday.hashcatcore.entity.HashDO;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Sets;
import com.iteknical.wednesday.computing.dto.HashDTO;

/**
 * DO到DTO的防腐
 * 
 * @author Luna@win10
 * @date 2020/3/17 14:47
 */
public class DO2DTOUtils {

    public static HashDTO hashDO2HashDTO(HashDO hashDO) {
        if (hashDO == null) {
            return null;
        }
        HashDTO hashDTO = new HashDTO();
        hashDTO.setId(hashDO.getId());
        hashDTO.setCreateTime(hashDO.getCreateTime());
        hashDTO.setModifiedTime(hashDO.getModifiedTime());
        hashDTO.setVersion(hashDO.getVersion());
        hashDTO.setName(hashDTO.getName());
        if (StringUtils.isNotBlank(hashDO.getContent())) {
            List<String> hashList = JSON.parseArray(hashDO.getContent(), String.class);
            if (CollectionUtils.isNotEmpty(hashList)) {
                hashDTO.setHashSet(Sets.newHashSet(hashList));
            }
        }
        if (StringUtils.isNotBlank(hashDO.getResult())) {
            List<String> hashResultList = JSON.parseArray(hashDO.getResult(), String.class);
            if (CollectionUtils.isNotEmpty(hashResultList)) {
                hashDTO.setHashResultSet(Sets.newHashSet(hashResultList));
            }
        }
        return hashDTO;
    }

}
