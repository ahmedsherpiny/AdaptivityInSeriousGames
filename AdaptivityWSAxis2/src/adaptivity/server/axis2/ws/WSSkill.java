package adaptivity.server.axis2.ws;


import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import sun.security.util.Length;
import adaptivity.server.axis2.Skill;
import adaptivity.server.axis2.SkillRequirement;

public class WSSkill {
	public String skillName;
	public String description;
	public WSSkillRequirement requirements[];
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
	public WSSkillRequirement[] getRequirements() {
		return requirements;
	}
	public void setRequirements(WSSkillRequirement[] requirements) {
		this.requirements = requirements;
	}
	public WSSkill()
	{}
	public WSSkill(Skill skill)
	{
		skillName=skill.getSkillName();
		description=skill.getDescription();
		requirements = null;
		if(skill.getSkillRequirements()!=null)
			requirements=(WSSkillRequirement[]) WSSkillRequirement.convertToWS((SkillRequirement[])skill.getSkillRequirements().toArray(new SkillRequirement[skill.getSkillRequirements().size()]));
	}
	public SkillRequirement[] getRequirementsAsSkillRequirements() {
		SkillRequirement[] requirementsArray;
		if(requirements!=null)
		{
			requirementsArray = new SkillRequirement[requirements.length];
			
			for(int i=0;i<requirements.length;i++)
			{
			//	System.out.println(requirements.length);
			//	System.out.println("requirement "+requirements[i]);
				if(requirements[i]!=null)
					requirementsArray[i]=requirements[i].convertToSkillRequirement();
			//	System.out.println(requirementsArray[i]);

			}

		}
		else
		{
			requirementsArray=new SkillRequirement[0];
		}
	//	System.out.println(requirementsArray);

		return requirementsArray;
	}
	public SOAPElement toSOAPElement(SOAPElement skillEl) throws SOAPException {
		SOAPElement skillNameEl= skillEl.addChildElement("skillName");
		skillNameEl.addTextNode(skillName);
		if(description!=null){
			SOAPElement desciptionEl = skillEl.addChildElement("description");
			desciptionEl.addTextNode(description);
			}
		SOAPElement skillRequirementsEl = skillEl.addChildElement("requirements");
		for(int i=0;requirements!=null&&i<requirements.length;i++)
		{
			SOAPElement SkillRequirementEl = skillRequirementsEl.addChildElement("SkillRequirement");

			requirements[i].toSOAPElement(SkillRequirementEl);
		}
		return skillEl;
	}

	

	

}
