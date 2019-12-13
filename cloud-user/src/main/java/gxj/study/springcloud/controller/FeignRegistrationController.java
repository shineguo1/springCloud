package gxj.study.springcloud.controller;

import gxj.study.springcloud.activity.facade.ActivityFeign;
import gxj.study.springcloud.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2019/12/12 18:02
 * @description
 */
@RestController
public class FeignRegistrationController {

    @Autowired
    ActivityFeign activityFeign;

    @PostMapping("/feign/register")
    public String userRegistration(@RequestBody User user) {
        System.out.println("用户注册 成功" + user.getName());
        return "[USER] 用户注册成功 " + activityFeign.firstLoginActivity(user.getId());
    }

    @PostMapping("/feign/registerTimeout")
    public String userRegistrationTimeout(@RequestBody User user) throws Exception {
        System.out.println("用户注册 成功" + user.getName());
        return "[USER] 用户注册成功 " + activityFeign.firstLoginActivityTimeout(user.getId());
    }
}
