package com.imooc.user.controller;

import com.imooc.api.controller.user.BaseController;
import com.imooc.api.controller.user.PassportControllerApi;
import com.imooc.grace.result.GraceJSONResult;
import com.imooc.utils.IPUtil;
import com.imooc.utils.RedisOperator;
import com.imooc.utils.SMSUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PassportController extends BaseController implements PassportControllerApi {

    final static Logger logger = LoggerFactory.getLogger(PassportController.class);

    @Autowired
    private SMSUtils smsUtils;

    @Override
    public GraceJSONResult getSMSCode(String mobile, HttpServletRequest request) throws Exception {

        // 获得用户ip
        String userIp = IPUtil.getRequestIp(request);

        // 根据用户的ip进行限制，限制用户在60秒内只能获得一次验证码
        redis.setnx60s(MOBILE_SMSCODE + ":" + userIp, userIp);

        // 生成随机验证码并且发送短信
        String random = (int)((Math.random() * 9 + 1) * 100000) + "";
//        smsUtils.sendSMS(mobile, random);

        // 把验证码存入redis，用于后续进行验证
        redis.set(MOBILE_SMSCODE + ":" + mobile, random, 10 * 60);

        return GraceJSONResult.ok();
    }
}
