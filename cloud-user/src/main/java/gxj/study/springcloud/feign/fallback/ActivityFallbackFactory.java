package gxj.study.springcloud.feign.fallback;

import feign.hystrix.FallbackFactory;
import gxj.study.springcloud.activity.facade.ActivityFeign;
import gxj.study.springcloud.feign.ActivityFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author xinjie_guo
 * @version 1.0.0 createTime:  2019/12/13 15:21
 * @description
 */
@Component
public class ActivityFallbackFactory implements FallbackFactory<ActivityFeignClient> {
    @Override
    public ActivityFeignClient create(Throwable throwable) {
        return new ActivityFeignClient() {
            @Override
            public String firstLoginActivity(@RequestBody String userId) {
                return "[USER] first login fallbackFactory";
            }

            @Override
            public String firstLoginActivityTimeout(@RequestBody String userId) throws Exception {
                return "[USER] first login timeout fallbackFactory";
            }
        };
    }
}
