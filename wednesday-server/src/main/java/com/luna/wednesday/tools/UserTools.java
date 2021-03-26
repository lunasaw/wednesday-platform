package com.luna.wednesday.tools;

import com.luna.wednesday.support.UserSupport;
import com.luna.wednesday.support.UserTagSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.iteknical.common.dto.constant.ResultCode;
import com.iteknical.fusion.user.constant.UserTagNameConstant;
import com.iteknical.fusion.user.vo.TagVO;
import com.iteknical.wednesday.exception.WednesdayException;

@Component
public class UserTools {
    @Autowired
    private UserSupport    userSupport;

    @Autowired
    private UserTagSupport userTagSupport;

    public long getOneUserIdBySessionKey(String sessionKey) {
        Long userId = userSupport.getUserIdBySessionKey(sessionKey);
        if (userId == null) {
            // 不存在的sessionKey
            throw new WednesdayException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
        return userId;
    }

    public void isAdmin(String sessionKey) {
        TagVO tagVO = new TagVO();
        tagVO.setName(UserTagNameConstant.IS_ADMIN);
        if (!userTagSupport.hasTag(sessionKey, tagVO)) {
            throw new WednesdayException(ResultCode.ERROR_SYSTEM_EXCEPTION, ResultCode.MSG_ERROR_SYSTEM_EXCEPTION);
        }
    }
}
