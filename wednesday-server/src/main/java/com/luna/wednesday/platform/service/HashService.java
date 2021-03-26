package com.luna.wednesday.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.luna.wednesday.platform.dao.CalculationObjectDAO;
import com.luna.wednesday.platform.dto.FoundDTO;
import com.luna.wednesday.platform.dto.HashDTO;
import com.luna.wednesday.platform.entity.CalculationObjectDO;
import com.luna.wednesday.platform.utils.DO2DTOUtils;
import com.luna.wednesday.platform.utils.DTO2DOUtils;
import com.luna.wednesday.tools.UserTools;

/**
 * @author luna@mac
 * @className HashService.java
 * @description TODO
 * @createTime 2021年02月25日 15:24:00
 */
@Service
public class HashService {

    @Autowired
    private CalculationObjectDAO calculationObjectDAO;

    @Autowired
    private UserTools            userTools;

    public HashDTO getById(Long id) {
        CalculationObjectDO calculationObjectDO = calculationObjectDAO.get(id);
        return DO2DTOUtils.calculationObjectDO2HashDTO(calculationObjectDO);
    }

    public boolean add(String sessionKey, HashDTO hashDTO) {
        userTools.isAdmin(sessionKey);
        hashDTO.setFound(Lists.newArrayList());
        return calculationObjectDAO.insert(DTO2DOUtils.hashDTO2CalculationObjectDO(hashDTO)) == 1;
    }

    public void update(Long id, String hash, String plain) {
        CalculationObjectDO calculationObjectDO = calculationObjectDAO.get(id);
        HashDTO hashDTO = DO2DTOUtils.calculationObjectDO2HashDTO(calculationObjectDO);
        if (hashDTO.getLeft().contains(hash)) {
            FoundDTO foundDTO = new FoundDTO(hash, plain);
            hashDTO.getFound().add(foundDTO);
            hashDTO.getLeft().remove(hash);
            calculationObjectDAO.update(DTO2DOUtils.hashDTO2CalculationObjectDO(hashDTO));
        }
    }
}
