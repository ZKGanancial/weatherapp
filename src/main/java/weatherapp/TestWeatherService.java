package weatherapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestWeatherService {
	
	private static final String BASE_URL = "http://weather.livedoor.com/forecast/webservice/json/v1?city=";
	private ObjectMapper mapper = new ObjectMapper();
	
	@Test
	public void checkWhetherLinkIsWorking()
	  throws ClientProtocolException, IOException {
	  
	   HttpUriRequest request = new HttpGet( BASE_URL + "130010" );
	 
	   HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	   
	   Assert.assertThat(
	     httpResponse.getStatusLine().getStatusCode(),
	     Matchers.equalTo(HttpStatus.SC_OK));
	}
	
	@Test
	public void checkWhetherCityIsTokyo() throws ClientProtocolException, IOException {
		
	    HttpUriRequest request = new HttpGet( BASE_URL + "130010" );
	 
	    HttpResponse response = HttpClientBuilder.create().build().execute( request );
	    StringBuilder sb = new StringBuilder();
	    try(BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));){
	    	String line;
	    	while (null != (line = br.readLine())) {
	    		sb.append(line);
	    	}
	    }
	    WeatherModel resource = mapper.readValue(sb.toString(), WeatherModel.class);
	    Assert.assertThat( "東京", Matchers.is( resource.getLocation().getCity() ) );
	}
}
