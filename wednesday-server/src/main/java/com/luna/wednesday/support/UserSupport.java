package com.luna.wednesday.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.utils.ResultDTOUtils;
import com.iteknical.fusion.user.client.UserClient;
import com.iteknical.wednesday.constant.ConstantHolder;

/**
 * @author Tony
 */
@Component
public class UserSupport {
    private Logger     logger = LoggerFactory.getLogger(UserSupport.class);

    @Autowired
    private UserClient userClient;

    public Long getUserIdBySessionKey(String sessionKey) {
        ResultDTO<Long> resultDTO = userClient.getUserIdBySessionKey(sessionKey, ConstantHolder.SITE);
        // 日志量太大，不输出
        // logger.info("userClient.getUserIdBySessionKey, sessionKey={}, site={}, resultDTO={}", sessionKey, site,
        // JSON.toJSONString(resultDTO));
        return ResultDTOUtils.checkResultAndGetData(resultDTO);
    }
}
