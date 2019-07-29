package blu.axonmysqlserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class AxonmysqlServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AxonmysqlServerApplication.class, args);
    }

}
