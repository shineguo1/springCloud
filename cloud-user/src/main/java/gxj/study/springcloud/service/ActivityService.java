package gxj.study.springcloud.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.utils.FallbackMethod;
import com.netflix.hystrix.exception.HystrixRuntimeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
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
        System.out.println("[USER] in Normal" + new Date());
        return  restTemplate.postForObject("http://activity/firstLoginActivity",id,String.class) +  Thread.currentThread().getName();
    }

    /**
     *
     * timeout 2秒，超时抛异常
     * @param id
     * @return
     */
    @HystrixCommand(
            //默认的threadPoolKey: 类名
            //Thread.currentThread().getName: Thread[hystrix-ActivityService-1,5,main]

            //配置属性在HystrixCommandProperties.java里
            commandProperties = {
                @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            }
    )
    public String firstLoginTimeout (String id) throws HystrixRuntimeException {
        System.out.println("[USER] in Timeout" + new Date());
        return  restTemplate.postForObject("http://activity/firstLoginActivityTimeout",id,String.class);
    }


    /**
     *
     * timeout 2秒，超时抛异常，回调函数处理
     * @param id
     * @return
     */
    @HystrixCommand(
            //配置属性在HystrixCommandProperties.java里
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
        System.out.println("[USER] in Fallback" + new Date());
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
            //配置属性在HystrixCommandProperties.java里
            commandProperties = {
                    //缺省值 = 1000 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "2000")
            },
            fallbackMethod = "firstLoginFallback"
    )
    public String firstLoginExceptionFallBack (String id) throws Exception {
        System.out.println("[USER] in Exception Fallback" + new Date());
        if(true){
            throw new Exception("手动抛出异常");
        }
        return "[USER] throw exception";
    }

    /**
     *
     * timeout 2秒，超时抛异常，回调函数处理
     * @param id
     * @return
     */
    @HystrixCommand(
            //配置属性在HystrixCommandProperties.java里
            commandProperties = {
                    //超时时间0.5秒，缺省值 = 1000 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "500"),
                    //断路器统计的时间窗口 2秒内 缺省值
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "3000"),
                    //该属性用来设置断路器打开的错误百分比条件。默认值为50，表示在滚动时间窗中，在请求值超过requestVolumeThreshold阈值的前提下，如果错误请求数百分比超过50，就把断路器设置为“打开”状态，否则就设置为“关闭”状态。 缺省值=50
                    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "50"),
                    //请求2次，缺省值=20
                    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "2"),
                    //时间窗5秒 缺省值5000
                   @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "5000")
            },
            //不影响跳闸机制，跳闸后跳过firstLoginCircuitBreaker直接进入回调方法处理。
            //跳闸阻断时抛出的异常是e:java.lang.RuntimeException: Hystrix circuit short-circuited and is OPEN
            //跳闸每个时间窗会放一个请求进入方法，会正常抛出异常 e:com.netflix.hystrix.exception.HystrixTimeoutException
            //如果这个请求成功了，会恢复跳闸状态
            fallbackMethod = "firstLoginFallback"
    )
    public String firstLoginCircuitBreaker (String id) throws Exception {
        System.out.println("[USER] in CircuitBreaker" + new Date());
//        if(true){
//            throw new Exception("[USER] Exception");
//        }
//        return "hello";
        return  "[USER] in CircuitBreaker" + restTemplate.postForObject("http://activity/firstLoginActivityTimeout",id,String.class);
    }


    @HystrixCommand(
            //线程池的名字：selfThreadPool0， 缺省值：类名
            threadPoolKey = "selfThreadPool0",
            //配置属性在HystrixThreadPoolProperties.java里
            threadPoolProperties = {
                    //这两条注释掉的的属性不知道为啥有错误
                    @HystrixProperty(name = "coreSize",value = "3"),
//                    @HystrixProperty(name = "maximumSize",value = "5"),
                    @HystrixProperty(name = "keepAliveTimeMinutes",value = "1"),
                    @HystrixProperty(name = "maxQueueSize",value = "-1"),
                    @HystrixProperty(name = "queueSizeRejectionThreshold",value = "5"),
//                    @HystrixProperty(name = "allowMaximumSizeToDivergeFromCoreSize",value="true"),
                    @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds",value = "10000"),
                    @HystrixProperty(name = "metrics.rollingStats.numBuckets",value = "5")
            },
            //配置属性在HystrixCommandProperties.java里
            commandProperties = {
                    //超时时间0.5秒，缺省值 = 1000 毫秒
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "500"),
            },
            fallbackMethod = "firstLoginFallback"
    )
    public String firstLoginSelfThreadPool (String id) throws Exception {
        System.out.println("[USER] in SelfThreadPool" + new Date());
        return "hello" + Thread.currentThread().getName();
    }
}
