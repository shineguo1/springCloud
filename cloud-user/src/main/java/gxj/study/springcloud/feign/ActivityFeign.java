//package gxj.study.springcloud.feign;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//
///**
// * @author xinjie_guo
// * @version 1.0.0 createTime:  2019/12/12 17:50
// */
//

/**
 *  有feignClient，就能在注册中心（eureka）找到对应的服务。
 *  再根据RequestMapping找到对应的接口入口。
 *
 *  如果写在消费者，有如下问题：
 *  1. 每一个消费者都要写一个feignClient接口，冗余
 *  2. 当提供者发生改变，每一个消费者都需要修改feignClient
 *  3. 消费者并不清楚提供者接口发生了什么样的改变，feignClient不应该由消费者书写
 *
 *  所以，可以把feignClient抽出来单独作为一个服务提供者的接口模块（provider-facade、provider-api）
 *  这样消费者只需要引入相应的依赖，就能获得feignClient
 *
 *  然后用另一个接口去继承feignClient接口，打上@feignClient注释
 */
//@FeignClient(value = "activity")
//public interface ActivityFeign {
//
//    @PostMapping("/firstLoginActivity")
//    String firstLoginActivity(@RequestBody String userId);
//
//    @PostMapping("/firstLoginActivityTimeout")
//    String firstLoginActivityTimeout(@RequestBody String userId) throws Exception;
//}
