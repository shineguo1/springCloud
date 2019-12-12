package gxj.study.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeoutException;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2019/12/11 10:08
 * @description
 */
@Service
public class ActivityService {
    @Autowired
    private RestTemplate restTemplate;

    public String firstLogin(String id) {
       return  restTemplate.postForObject("http://activity/firstLoginActivity",id,String.class);
    }

    /**
     *
     * timeout 2秒，超时抛异常
     * @param id
     * @return
     */
    @HystrixCommand(
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            }
    )
    public String firstLoginTimeout (String id) throws HystrixRuntimeException {
        return  restTemplate.postForObject("http://activity/firstLoginActivityTimeout",id,String.class);
    }


    /**
     *
     * timeout 2秒，超时抛异常，回调函数处理
     * @param id
     * @return
     */
    @HystrixCommand(
            commandProperties = {
                    //缺省值 = 1000 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            },
            fallbackMethod = "firstLoginFallback"
    )
    public String firstLoginTimeoutFallBack (String id) throws Exception {
        //  自身方法超时
        //        Thread.sleep(5000);
        //  远程调用超时
        //  若远程服务抛出异常，会抛出HttpServerErrorException$InternalServerError: 500 异常，同样会捕获回调
        return  restTemplate.postForObject("http://activity/firstLoginActivityTimeout",id,String.class);
    }

    public String  firstLoginFallback(String id, Throwable e){
        System.out.println("fallback 处理异常 e:"+e);
        return "fallback 执行超时补救方法 | e:"+e;
    }

    /**
     *
     * timeout 2秒，超时抛异常，回调函数处理
     * @param id
     * @return
     */
    @HystrixCommand(
            commandProperties = {
                    //缺省值 = 1000 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            },
            fallbackMethod = "firstLoginFallback"
    )
    public String firstLoginExceptionFallBack (String id) throws Exception {
        if(true){
            throw new Exception("手动抛出异常");
        }
        return "[USER] throw exception";
    }


}
