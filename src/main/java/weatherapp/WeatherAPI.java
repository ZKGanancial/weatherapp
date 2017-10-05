package weatherapp;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@EnableRetry
public class WeatherAPI {
	
	public static void main(String args[]) {
		Result result = JUnitCore.runClasses(TestWeatherService.class);
		if(result.getFailureCount()>0)
			result.getFailures().forEach(failure -> System.out.println(failure.toString()));
		else {
			System.out.println("ユニットテストはすべてPASSしました。");
		}
		SpringApplication.run(WeatherAPI.class);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

}
