//package gxj.study.springcloud.activity.facade.fallback;
//
//import gxj.study.springcloud.activity.facade.ActivityFeign;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// * @author xinjie_guo
// * @version 1.0.0 createTime:  2019/12/13 11:12
// * @description
// */
//
///**
// * 1. 实现feignClient interface
// * 2. 纳入Spring Bean管理
// */
//@Component
//public class ActivityFallback implements ActivityFeign {
//    @Override
//    public String firstLoginActivity(@RequestBody String userId) {
//        return "[ACTIVITY FACADE] first login fallback";
//    }
//
//    @Override
//    public String firstLoginActivityTimeout(@RequestBody String userId) throws Exception {
//        return "[ACTIVITY FACADE] first login timeout fallback";
//    }
//}
