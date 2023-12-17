package stocks.services.center;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SpringBootApplication
public class CenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CenterApplication.class, args);
	}
	

}
