package adaptivity.server.axis2;

import java.util.ArrayList;

public class AdaptiveElement {
	private int elementID;
	private String elementName;
	private ArrayList<ElementProperty> properties;
	public int getElementID() {
		return elementID;
	}
	public void setElementID(int elementID) {
		this.elementID = elementID;
	}
	public String getElementName() {
		return elementName;
	}
	public void setElementName(String elementName) {
		this.elementName = elementName;
	}
}
