package gxj.study.springcloud.activity;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringCloudApplication
@EnableEurekaClient
public class CloudActivityApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudActivityApplication.class, args);
	}

}
