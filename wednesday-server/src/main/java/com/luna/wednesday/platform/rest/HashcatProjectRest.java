package com.luna.wednesday.platform.rest;

import javax.servlet.http.HttpServletRequest;

import com.iteknical.wednesday.constant.URLConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.iteknical.common.anno.MyValid;
import com.iteknical.common.dto.ResultDTO;
import com.iteknical.common.dto.constant.ResultCode;
import com.iteknical.fusion.user.utils.CookieUtils;
import com.luna.wednesday.platform.dto.HashcatProjectDTO;
import com.luna.wednesday.platform.service.HashcatProjectService;

/**
 * @author luna@mac
 */
@RestController
@RequestMapping(URLConstant.HASHCAT + URLConstant.S + URLConstant.API)
public class HashcatProjectRest {

    @Autowired
    private HashcatProjectService hashcatProjectService;

    @PostMapping("createHashcatProject")
    public ResultDTO<Long> createHashcatProject(HttpServletRequest request,
        @RequestParam(name = CookieUtils.SESSION_KEY_NAME, required = false) String sessionKey,
        @RequestBody @MyValid HashcatProjectDTO hashcatProjectDTO) {

        sessionKey = CookieUtils.getOneSessionKey(sessionKey, request);

        return new ResultDTO<>(true, ResultCode.SUCCESS, ResultCode.MSG_SUCCESS,
            hashcatProjectService.createHashcatProject(sessionKey, hashcatProjectDTO));
    }

}
