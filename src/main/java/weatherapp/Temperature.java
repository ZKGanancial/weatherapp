package weatherapp;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Temperature implements Serializable {

	private static final long serialVersionUID = -7577208807549960689L;
	
	private TempUnits min;
	
	private TempUnits max;
	
	public TempUnits getMin() {
		return min;
	}
	public void setMin(TempUnits min) {
		this.min = min;
	}
	public TempUnits getMax() {
		return max;
	}
	public void setMax(TempUnits max) {
		this.max = max;
	}

}
