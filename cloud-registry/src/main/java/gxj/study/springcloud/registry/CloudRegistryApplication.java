package gxj.study.springcloud.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;


/**
 * @author xinjie_guo
 */

/*
 * @SpringBootApplication包含以下三项：
 * @SpringBootApplication
 * @EnableDiscoveryClient
 * @EnableCircuitBreaker
 */
@SpringCloudApplication
// @SpringBootApplication
//  @EnableDiscoveryClient
//  @EnableCircuitBreaker
public class CloudRegistryApplication {

	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(CloudRegistryApplication.class, args);
//		Thread.sleep(100000);
		System.out.println("done");
	}

}
