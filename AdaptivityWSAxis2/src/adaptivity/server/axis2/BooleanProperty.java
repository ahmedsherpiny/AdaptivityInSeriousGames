package adaptivity.server.axis2;


public class BooleanProperty extends ElementProperty {
private boolean vlaue;

public BooleanProperty() {
	setPropertyType("bool");
	}

public boolean getVlaue() {
	return vlaue;
}

public void setVlaue(boolean vlaue) {
	this.vlaue = vlaue;
}

}
