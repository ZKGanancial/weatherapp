package weatherapp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class WeatherClient implements CommandLineRunner{
	
	private static final String BASE_URL = "http://weather.livedoor.com/forecast/webservice/json/v1";

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@Retryable(backoff = @Backoff(5000), maxAttempts = 5)
	public void run(String... args) throws Exception {
		try(Scanner scanner = new Scanner(System.in);
				){
					System.out.print("地域コードを入力してください： ");
					String input = null;
					if(scanner.hasNext()) {
						input = scanner.nextLine();
						if(input.length() > 6) {
							throw new IllegalArgumentException();
						}
						//入力をチェック
						Integer.parseInt(input);
						//入力をチェック
					}
					UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(BASE_URL)
					        .queryParam("city", input);
					WeatherModel weatherModel = restTemplate.getForObject(builder.build().toUri(), WeatherModel.class);
					String weather = generateWeather(weatherModel);
					String system = System.getProperty("os.name").contains("Windows") ? System.getenv("SystemDrive") + "/": "/";
					System.out.print("保存する場所を記載してください： " + system);
					if(scanner.hasNext()) {
						input = scanner.nextLine();
					}
					input = system.concat(input);
					File file = new File(input);
					if(file.isDirectory()) {
						input = input.concat("/weather.txt");
					}
					if(!isFilenameValid(input)) {
						System.out.println("\nパスは不正です。\n");
					}
					else {
						try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(input), StandardCharsets.UTF_8);){
							writer.write(weather);
						}		
					}
					System.out.println(weather);
					if(isFilenameValid(input))
						System.out.println("\n天気予報は"+input+"に保存しました。");
					else
						System.out.println("\n有効な場所を記載してください。");
				}
				catch(Exception e) {
					if (e instanceof NumberFormatException) {
						System.out.println("数字のみ入力してください。");
					}
					else if (e instanceof IllegalArgumentException) {
						System.out.println("6桁まで入力してください。");
					}
					else if (e instanceof RestClientException && e.getMessage().contains("404")) {
						System.out.println("地域は見つかりませんでした。");
					}
					else {
						System.out.println("システムエラーが発生しました。");
						if (e instanceof RestClientException) {
							throw e; //リトライ処理
						}
						else {
							e.printStackTrace();
						}
					}
				}		
		
	}
	
	private String generateWeather(WeatherModel weather) {
		//Display weather
		StringBuilder sb = new StringBuilder();
		sb.append(weather.getTitle()+"\n")
		.append("【地域】" + weather.getLocation().getArea() + " ")
		.append("【都道府県】" + weather.getLocation().getPrefecture() + " ")
		.append("【市】" + weather.getLocation().getCity() + "\n\n")
		.append(weather.getDescription().getText() + "\n\n")
		.append("予報:");
		weather.getForecasts().forEach(f -> {
			sb.append("\n("+f.getDateLabel()+")")
			.append(f.getTelop());
		});
		return sb.toString();
	}
	
    private boolean isFilenameValid(String file) {
    	File f = new File(file);
    	try {
			f.getCanonicalPath();
			return true;
    	}
    	catch (IOException e) {
    		return false;
    	}
    }
}
