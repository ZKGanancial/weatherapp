package weatherapp;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Copyright implements Serializable{

	private static final long serialVersionUID = -1041332279567293443L;
	
	private List<Link> provider;
	
	private String link;
	
	private String title;
	
	private Image image;
	
	public List<Link> getProvider() {
		return provider;
	}
	public void setProvider(List<Link> provider) {
		this.provider = provider;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
		this.image = image;
	}
	
}
