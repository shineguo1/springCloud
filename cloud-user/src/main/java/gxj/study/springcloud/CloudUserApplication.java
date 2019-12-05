package gxj.study.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
public class CloudUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudUserApplication.class, args);
	}

}
