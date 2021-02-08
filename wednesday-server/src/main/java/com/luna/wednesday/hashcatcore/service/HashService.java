package com.luna.wednesday.hashcatcore.service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

import com.luna.wednesday.hashcatcore.dao.HashDAO;
import com.luna.wednesday.hashcatcore.entity.HashDO;
import com.luna.wednesday.hashcatcore.utils.DO2DTOUtils;
import com.luna.wednesday.hashcatcore.utils.DTO2DOUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iteknical.wednesday.computing.dto.HashDTO;

/**
 * @author Tony
 */
@Service
public class HashService {
    @Autowired
    private HashDAO hashDAO;

    @Autowired
    // private UserTools userTools;

    public long add(String sessionKey, HashDTO hashDTO) {
        // userTools.isAdmin(sessionKey);

        HashDO hashDO = DTO2DOUtils.hashDTO2HashDO(hashDTO);
        hashDAO.insert(hashDO);
        return hashDO.getId();
    }

    public HashDTO get(long id) {
        HashDO hashDO = hashDAO.get(id);
        return DO2DTOUtils.hashDO2HashDTO(hashDO);
    }

    public HashDTO get(String name) {
        HashDO hashDO = hashDAO.getByName(name);
        return DO2DTOUtils.hashDO2HashDTO(hashDO);
    }

    public void update(HashDTO hashDTO) {
        HashDO hashDO = DTO2DOUtils.hashDTO2HashDO(hashDTO);
        hashDAO.update(hashDO);
    }

    public boolean allCracked(long id) {
        HashDTO hashDTO = get(id);
        return CollectionUtils.isEmpty(hashDTO.getHashSet());
    }

    public synchronized void addHashResultSet(long id, Set<String> hashResultSet) {
        HashDTO hashDTO = get(id);

        // hashList中去掉已找到的
        Set<String> hashSet = hashDTO.getHashSet();
        Iterator<String> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            String hash = iterator.next();
            // TODO 二进制hash比对未实现
            // TODO 不是准确比对，对于短hash可能有潜在问题
            if (CollectionUtils.isNotEmpty(hashResultSet.stream()
                .filter(result -> CollectionUtils.isNotEmpty(Arrays.asList(result.split(":")).stream()
                    .filter(split -> StringUtils.equals(split, hash)).collect(Collectors.toList())))
                .collect(Collectors.toList()))) {
                iterator.remove();
            }
        }

        // hashResultSet中加上已找到的
        if (CollectionUtils.isNotEmpty(hashDTO.getHashResultSet())) {
            hashResultSet.addAll(hashDTO.getHashResultSet());
        }

        hashDTO.setHashSet(hashSet);
        hashDTO.setHashResultSet(hashResultSet);

        update(hashDTO);
    }
}
