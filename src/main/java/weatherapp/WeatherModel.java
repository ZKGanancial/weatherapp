package weatherapp;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherModel implements Serializable{

	private static final long serialVersionUID = -5512033682179197374L;
	
	private String publicTime;
	
	private String title;
	
	private Description description;
	
	private String link;
	
	private List<Forecast> forecasts;
	
	private Location location;
	
	private List<Link> pinpointLocations;
	
	private Copyright copyright;
	
	public String getPublicTime() {
		return publicTime;
	}
	public void setPublicTime(String publicTime) {
		this.publicTime = publicTime;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Description getDescription() {
		return description;
	}
	public void setDescription(Description description) {
		this.description = description;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public List<Forecast> getForecasts() {
		return forecasts;
	}
	public void setForecasts(List<Forecast> forecasts) {
		this.forecasts = forecasts;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public List<Link> getPinpointLocations() {
		return pinpointLocations;
	}
	public void setPinpointLocations(List<Link> pinpointLocations) {
		this.pinpointLocations = pinpointLocations;
	}
	public Copyright getCopyright() {
		return copyright;
	}
	public void setCopyright(Copyright copyright) {
		this.copyright = copyright;
	}
	
}
