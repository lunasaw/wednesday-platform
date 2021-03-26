package com.luna.wednesday.support;

import com.luna.wednesday.constant.ConstantHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.utils.ResultDTOUtils;
import com.iteknical.fusion.user.client.UserTagClient;
import com.iteknical.fusion.user.vo.TagVO;

/**
 * @author Iszychen@win10
 * @date 2020/2/24 14:08
 */
@Component
public class UserTagSupport {
    private final Logger  logger = LoggerFactory.getLogger(UserTagSupport.class);

    @Autowired
    private UserTagClient userTagClient;

    public Boolean hasTag(String sessionKey, TagVO tagVO) {
        ResultDTO<Boolean> resultDTO = userTagClient.hasTag(sessionKey, ConstantHolder.SITE, tagVO);
        logger.info("userTagClient.hasTag, sessionKey={}, tagVO={}, resultDTO={}", sessionKey, JSON.toJSONString(tagVO),
            JSON.toJSONString(resultDTO));
        return ResultDTOUtils.checkResultAndGetData(resultDTO);
    }

    public void addTag(String sessionKey, TagVO tagVO) {
        ResultDTO<Void> resultDTO = userTagClient.addTag(sessionKey, ConstantHolder.SITE, tagVO);
        logger.info("userTagClient.addTag, sessionKey={}, tagVO={}, resultDTO={}", sessionKey, JSON.toJSONString(tagVO),
            JSON.toJSONString(resultDTO));
        ResultDTOUtils.checkResultAndGetData(resultDTO);
    }

    public void removeTag(String sessionKey, TagVO tagVO) {
        ResultDTO<Void> resultDTO = userTagClient.removeTag(sessionKey, ConstantHolder.SITE, tagVO);
        logger.info("userTagClient.removeTag, sessionKey={}, tagVO={}, resultDTO={}", sessionKey,
            JSON.toJSONString(tagVO), JSON.toJSONString(resultDTO));
        ResultDTOUtils.checkResultAndGetData(resultDTO);
    }

}
