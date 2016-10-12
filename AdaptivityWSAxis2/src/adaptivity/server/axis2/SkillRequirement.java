package adaptivity.server.axis2;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import adaptivity.server.axis2.Skill;

public class SkillRequirement extends Skill {

	private int minRequiredValue;

	public int getMinRequiredValue() {
		return minRequiredValue;
	}

	public void setMinRequiredValue(int minRequiredValue) {
		this.minRequiredValue = minRequiredValue;
	}
//	public SOAPElement toSOAPElement(SOAPElement skillReqEl) throws SOAPException 
//	{
//		super.toSOAPElement(skillReqEl);
//		SOAPElement minReqValueEl = skillReqEl.addChildElement("minRequiredValue");
//		minReqValueEl.addTextNode(String.valueOf(minRequiredValue));
//		return skillReqEl;
//	}
}
