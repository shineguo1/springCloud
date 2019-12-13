//package gxj.study.springcloud.activity.facade.fallback;
//
//import feign.hystrix.FallbackFactory;
//import gxj.study.springcloud.activity.facade.ActivityFeign;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// * @author xinjie_guo
// * @version 1.0.0 createTime:  2019/12/13 15:21
// * @description
// */
//@Component
//public class ActivityFallbackFactory implements FallbackFactory<ActivityFeign> {
//    @Override
//    public ActivityFeign create(Throwable throwable) {
//        return new ActivityFeign() {
//            @Override
//            public String firstLoginActivity(@RequestBody String userId) {
//                return "[ACTIVITY FACADE] first login fallbackFactory";
//            }
//
//            @Override
//            public String firstLoginActivityTimeout(@RequestBody String userId) throws Exception {
//                return "[ACTIVITY FACADE] first login timeout fallbackFactory";
//            }
//        };
//    }
//}
