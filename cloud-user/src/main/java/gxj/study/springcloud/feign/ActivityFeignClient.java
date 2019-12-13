package gxj.study.springcloud.feign;

import gxj.study.springcloud.activity.facade.ActivityFeign;
import gxj.study.springcloud.feign.fallback.ActivityFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2019/12/13 16:19
 * @description
 */
@FeignClient(value = "activity",
//        fallback = ActivityFallback.class,
        fallbackFactory = ActivityFallbackFactory.class)
public interface ActivityFeignClient extends ActivityFeign {

}
