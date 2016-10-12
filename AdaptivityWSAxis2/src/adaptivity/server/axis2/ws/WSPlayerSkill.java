package adaptivity.server.axis2.ws;


import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import adaptivity.server.axis2.PlayerSkill;
import adaptivity.server.axis2.SkillRequirement;
import adaptivity.server.axis2.SkillValue;

public class WSPlayerSkill extends WSSkill{
	public SkillValue skillValues[];

	public SkillValue[] getSkillValues() {
		return skillValues;
	}
	public void setSkillValues(SkillValue[] skillValues) {
		this.skillValues = skillValues;
	}
	public WSPlayerSkill()
	{}
	public WSPlayerSkill(PlayerSkill skill)
	{
		super(skill);
		skillValues=(SkillValue[]) skill.getSkillValue().toArray(new SkillValue[skill.getSkillValue().size()]);
	}


	public static WSPlayerSkill[] convertToWS(PlayerSkill[] array) {
		WSPlayerSkill[] skills = new WSPlayerSkill[array.length];
		for(int i=0;i<array.length;i++)
		{
			skills[i] = WSPlayerSkill.convertToWS(array[i]);
		}
		return skills;
	}

	public static WSPlayerSkill convertToWS(PlayerSkill playerSkill) {
		WSPlayerSkill skill = new WSPlayerSkill(playerSkill);
		return skill;
	}

	public static PlayerSkill[] convertToPlayerSkill(WSPlayerSkill[] skills) {
		PlayerSkill[] returnValue = new PlayerSkill[skills.length];
		for(int i=0;i<skills.length;i++)
		{
			returnValue[i] = skills[i].convertToPlayerSkill();
		}
		return returnValue;
	}

	public PlayerSkill convertToPlayerSkill() {
	//	System.out.println("Start converting skill");
		PlayerSkill skill = new PlayerSkill();
		skill.setSkillName(super.getSkillName());
		skill.setDescription(super.getDescription());
		if(getRequirementsAsSkillRequirements()!=null)
			skill.setSkillRequirements(new ArrayList<SkillRequirement>(Arrays.asList(getRequirementsAsSkillRequirements())));
		if(skillValues!=null)
			skill.setSkillValue(new ArrayList<SkillValue>(Arrays.asList(skillValues)));
		
	//	System.out.println("Finish converting skill");
		return skill;
	}
	public SOAPElement toSOAPElement(SOAPElement skillSetEl) throws SOAPException {
		SOAPElement skillEl = super.toSOAPElement(skillSetEl);
		SOAPElement skillValuesEl = skillEl.addChildElement("skillValues");
		skillValuesEl.addAttribute(new QName("arrayType"), "q1:SkillValue[]");
		for(int i =0;i<skillValues.length;i++)
		{
		//	SOAPElement skillValueEl = skillValuesEl.addChildElement("SkillValue");
			

			skillValues[i].toSOAPElement(skillValuesEl);
		}
		
		return skillSetEl;
	}
	
}
