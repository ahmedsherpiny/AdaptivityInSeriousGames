package adaptivity.server.axis2;

import java.util.ArrayList;
import java.util.Date;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import adaptivity.server.axis2.SkillRequirement;

public class Skill {

	private String skillName;
	private String description;
	private ArrayList <SkillRequirement> requirements;
	public String getSkillName() {
		return skillName;
	}
	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
//	public SOAPElement toSOAPElement(SOAPElement skillEl) throws SOAPException {
//		SOAPElement skillNameEl= skillEl.addChildElement("skillName");
//		skillNameEl.addTextNode(skillName);
//		if(description!=null){
//			SOAPElement desciptionEl = skillEl.addChildElement("description");
//			desciptionEl.addTextNode(description);
//			}
//		SOAPElement skillRequirementsEl = skillEl.addChildElement("requirements");
//		for(int i=0;requirements!=null&&i<requirements.size();i++)
//		{
//			SOAPElement SkillRequirementEl = skillRequirementsEl.addChildElement("SkillRequirement");
//
//			requirements.get(i).toSOAPElement(SkillRequirementEl);
//		}
//		return skillEl;
//	}
	public ArrayList <SkillRequirement> getSkillRequirements() {
		return requirements;
	}
	public void setSkillRequirements(
			ArrayList<SkillRequirement> skillRequirements) {
		this.requirements=skillRequirements;
		
	}
	
}
