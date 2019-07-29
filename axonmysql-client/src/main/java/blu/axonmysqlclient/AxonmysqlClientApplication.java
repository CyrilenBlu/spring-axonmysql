package blu.axonmysqlclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AxonmysqlClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonmysqlClientApplication.class, args);
    }

}
