package hzh.dataanalytics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class DataAnalyticsApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataAnalyticsApplication.class, args);
    }

}
