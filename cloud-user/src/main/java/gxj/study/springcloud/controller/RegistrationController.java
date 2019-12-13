package gxj.study.springcloud.controller;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import gxj.study.springcloud.entity.User;
import gxj.study.springcloud.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2019/12/11 10:09
 * @description 用户注册
 */
@RestController
public class RegistrationController {

    @Autowired
    private ActivityService activityService;

    //等价于@RequestMapping 请求方法=post
    @PostMapping("/register")
    public String userRegistration(@RequestBody User user) {
        System.out.println("用户注册 成功" + user.getName());
        return "[USER] 用户注册成功 " + activityService.firstLogin(user.getId());
    }


    /**
     * 请求超时：等待2秒，然后超时，Hystrix抛出异常并捕获
     *
     * @param user
     * @return
     */
    @PostMapping("/registerTimeout")
    public String userRegistrationTimeout(@RequestBody User user) {
        System.out.println("用户注册 成功" + user.getName());
        String ret = "";
        try {
            ret = "[USER] 用户注册成功 " + activityService.firstLoginTimeout(user.getId());
            return ret;
        } catch (HystrixRuntimeException e) {
            System.out.println("用户注册 请求超时");
            return "[USER] TIME OUT EXCEPTION";
        }
    }

    /**
     * 请求超时：2秒后超时，Hystrix回调函数处理
     *
     * @param user
     * @return
     */
    @PostMapping("/registerTimeoutFallback")
    public String userRegistrationTimeoutFallback(@RequestBody User user) {
        System.out.println("用户注册 成功" + user.getName());
        String ret = "";
        try {
            ret = "[USER] 用户注册成功 " + activityService.firstLoginTimeoutFallBack(user.getId());
            return ret;
        } catch (HystrixRuntimeException e) {
            //不会接收到firstLoginTimeoutFallBack异常
            //如果接受到异常，是fallbackMethod抛出的异常
            System.out.println("用户注册 请求超时");
            return "[USER] TIME OUT EXCEPTION";
        } catch (Exception e) {
            return "[USER] EXCEPTION";
        }
    }

    /**
     * 手动抛出异常，Hystrix回调函数处理
     *
     * @param user
     * @return
     */
    @PostMapping("/registerExceptionFallback")
    public String userRegistrationExceptionFallback(@RequestBody User user) {
        System.out.println("用户注册 成功" + user.getName());
        String ret = "";
        try {
            ret = "[USER] 用户注册成功 " + activityService.firstLoginExceptionFallBack(user.getId());
            return ret;
        } catch (HystrixRuntimeException e) {
            //不会接收到firstLoginTimeoutFallBack异常
            //如果接受到异常，是fallbackMethod抛出的异常
            System.out.println("用户注册 请求超时");
            return "[USER] RUNTIME EXCEPTION";
        } catch (Exception e) {
            return "[USER] EXCEPTION";
        }
    }


    /**
     * Hystrix的跳闸机制
     *
     * @param user
     * @return
     */
    @PostMapping("/registerCircuitBreaker")
    public String userRegistrationCircuitBreaker(@RequestBody User user) {
        System.out.println("用户注册 成功" + user.getName());
        String ret = "";
        try {
            ret = "[USER] 用户注册成功 " + activityService.firstLoginCircuitBreaker(user.getId());
            return ret;
        } catch (HystrixRuntimeException e) {
            //不会接收到firstLoginTimeoutFallBack异常
            //如果接受到异常，是fallbackMethod抛出的异常
            System.out.println("用户注册 请求超时");
            return "[USER] RUNTIME EXCEPTION";
        } catch (Exception e) {
            return "[USER] EXCEPTION";
        }
    }

    /**
     * 定制Hystrix处理的线程池
     *
     * @param user
     * @return
     */
    @PostMapping("/registerSelfThreadPool")
    public String userRegistrationSelfThreadPool(@RequestBody User user) {
        System.out.println("用户注册 成功" + user.getName());
        String ret = "";
        try {
            ret = "[USER] 用户注册成功 " + activityService.firstLoginSelfThreadPool(user.getId());
            return ret;
        } catch (HystrixRuntimeException e) {
            //不会接收到firstLoginTimeoutFallBack异常
            //如果接受到异常，是fallbackMethod抛出的异常
            System.out.println("用户注册 请求超时");
            return "[USER] RUNTIME EXCEPTION";
        } catch (Exception e) {
            return "[USER] EXCEPTION";
        }
    }

}
