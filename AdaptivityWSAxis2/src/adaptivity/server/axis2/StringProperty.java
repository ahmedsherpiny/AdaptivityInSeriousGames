package adaptivity.server.axis2;

public class StringProperty extends ElementProperty {
	private String vlaue;
	
	public StringProperty() {
	setPropertyType("string");
	}

	public String getVlaue() {
		return vlaue;
	}

	public void setVlaue(String vlaue) {
		this.vlaue = vlaue;
	}
	

}
