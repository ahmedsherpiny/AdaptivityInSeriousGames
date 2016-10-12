package adaptivity.server.axis2;

public class IntegerElement extends ElementProperty {
private int value;
public IntegerElement() {
setPropertyType("int");
}
public int getValue() {
	return value;
}
public void setValue(int value) {
	this.value = value;
}
}
