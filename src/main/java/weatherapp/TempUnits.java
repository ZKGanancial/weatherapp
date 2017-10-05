package weatherapp;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TempUnits implements Serializable {

	private static final long serialVersionUID = -3131771469848813214L;
	
	private Integer celsius;
	
	private Double fahrenheit;
	
	public Integer getCelsius() {
		return celsius;
	}
	public void setCelsius(Integer celsius) {
		this.celsius = celsius;
	}
	public Double getFahrenheit() {
		return fahrenheit;
	}
	public void setFahrenheit(Double fahrenheit) {
		this.fahrenheit = fahrenheit;
	}

}
