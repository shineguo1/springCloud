package gxj.study.springcloud.activity.facade;//package gxj.study.springcloud.feign;

//import gxj.study.springcloud.activity.facade.fallback.ActivityFallback;
//import gxj.study.springcloud.activity.facade.fallback.ActivityFallbackFactory;
//import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2019/12/12 17:50
*/


/**
 * 可以把@FeignClient标签丢到消费者去做，用一个接口继承这个接口
 */
//@FeignClient(value = "activity",
//        fallback = ActivityFallback.class,
//        fallbackFactory = ActivityFallbackFactory.class)
public interface ActivityFeign {

    @PostMapping("/firstLoginActivity")
    String firstLoginActivity(@RequestBody String userId);

    @PostMapping("/firstLoginActivityTimeout")
    String firstLoginActivityTimeout(@RequestBody String userId) throws Exception;
}
