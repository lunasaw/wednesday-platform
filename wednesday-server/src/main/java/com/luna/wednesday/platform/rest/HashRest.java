package com.luna.wednesday.platform.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.iteknical.common.anno.MyValid;
import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.dto.constant.ResultCode;
import com.iteknical.fusion.user.utils.CookieUtils;
import com.luna.wednesday.platform.dto.HashDTO;
import com.luna.wednesday.platform.service.HashService;

/**
 * @author luna@mac
 * @className HashRest.java
 * @description TODO
 * @createTime 2021年03月09日 16:04:00
 */
@RestController
public class HashRest {

    @Autowired
    private HashService hashService;

    @PostMapping("add")
    public ResultDTO<Boolean> addHash(HttpServletRequest request,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestBody @MyValid HashDTO hashDTO) {

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            hashService.add(sessionKey, hashDTO));
    }
}
