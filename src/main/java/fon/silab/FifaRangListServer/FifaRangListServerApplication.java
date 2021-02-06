package fon.silab.FifaRangListServer;

import org.apache.log4j.PropertyConfigurator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FifaRangListServerApplication {

    public static void main(String[] args) {
        String log4jConfPath = "log4j.properties";
        PropertyConfigurator.configure(log4jConfPath);
        SpringApplication.run(FifaRangListServerApplication.class, args);
    }

}
