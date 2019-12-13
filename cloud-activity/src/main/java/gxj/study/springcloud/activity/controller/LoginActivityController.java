package gxj.study.springcloud.activity.controller;

import gxj.study.springcloud.activity.facade.ActivityFeign;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2019/12/5 14:12
 * @description
 */
@RestController
public class LoginActivityController implements ActivityFeign{

//  继承了ActivityFeign接口，就不需要写@RequestMapping注解了，注解写在了接口上
//    @PostMapping("/firstLoginActivity")
    @Override
    public String firstLoginActivity(@RequestBody String userId){
        System.out.println("LoginActivityController 处理首次登录用户 发放奖励" + userId);
        return "[ACTIVITY]发放奖励"+userId;
    }

//    @PostMapping("/firstLoginActivityTimeout")
    @Override
    public String firstLoginActivityTimeout(@RequestBody String userId) throws Exception {
        Thread.sleep(5000);
//        if(true){
//            throw new Exception("[Activity] 手动抛出Exception");
//        }
        System.out.println("LoginActivityController 处理首次登录用户 发放奖励" + userId);
        return "[ACTIVITY]发放奖励"+userId;
    }
}
