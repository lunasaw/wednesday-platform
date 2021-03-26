package com.luna.wednesday.platform.utils;

import com.alibaba.fastjson.JSON;
import com.luna.wednesday.platform.dto.HashDTO;
import com.luna.wednesday.platform.entity.CalculationObjectDO;

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
}
