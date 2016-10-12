package adaptivity.server.axis2.ws;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import adaptivity.server.axis2.SkillRequirement;

public class WSSkillRequirement extends WSSkill {
	public int minRequiredValue;
	public int getMinRequiredValue() {
		return minRequiredValue;
	}
	public void setMinRequiredValue(int minRequiredValue) {
		this.minRequiredValue = minRequiredValue;
	}
	public WSSkillRequirement(SkillRequirement skillRequirement)
	{
		super(skillRequirement);
		minRequiredValue = skillRequirement.getMinRequiredValue();
	}
	public WSSkillRequirement()
	{}
	public static WSSkillRequirement[] convertToWS(SkillRequirement[] array) {
		WSSkillRequirement[] skillRequirements = new WSSkillRequirement[array.length];
		for(int i=0; i<array.length;i++)
		{
			if(skillRequirements[i]!=null)
				skillRequirements[i] = WSSkillRequirement.convertToWS(array[i]);
		}
		return skillRequirements;
	}
	private static WSSkillRequirement convertToWS(
			SkillRequirement skillRequirement) {
		WSSkillRequirement returnValue = new WSSkillRequirement(skillRequirement);		
		return returnValue;
	}
	public SkillRequirement convertToSkillRequirement() {
		
		SkillRequirement skillReq = new SkillRequirement();
		skillReq.setSkillName(super.getSkillName());
		skillReq.setDescription(super.getDescription());
		
		if(getRequirementsAsSkillRequirements()!=null)
			skillReq.setSkillRequirements(new ArrayList<SkillRequirement>(Arrays.asList(getRequirementsAsSkillRequirements())));		
		skillReq.setMinRequiredValue(minRequiredValue);
		return skillReq;
	}
	
	public SOAPElement toSOAPElement(SOAPElement skillReqEl) throws SOAPException 
	{
		super.toSOAPElement(skillReqEl);
		SOAPElement minReqValueEl = skillReqEl.addChildElement("minRequiredValue");
		minReqValueEl.addTextNode(String.valueOf(minRequiredValue));
		return skillReqEl;
	}

}
