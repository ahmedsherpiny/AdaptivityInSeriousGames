package adaptivity.server.axis2.ws;

import java.util.ArrayList;
import java.util.Arrays;









import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import adaptivity.server.axis2.PlayerSkill;
import adaptivity.server.axis2.SkillSet;

public class WSSkillSet {
	public WSPlayerSkill skills[];

	public WSPlayerSkill[] getSkills() {
		return skills;
	}

	public void setSkills(WSPlayerSkill[] skills) {
		this.skills = skills;
	}

	public WSSkillSet()
	{
		
	}
	
	public WSSkillSet(SkillSet skillSet) {
		skills =(WSPlayerSkill[]) WSPlayerSkill.convertToWS((PlayerSkill[]) skillSet.getSkills().toArray(new PlayerSkill[skillSet.skillsCount()]));
	}



	public static WSSkillSet convertToWS(SkillSet gameSkillSet) {
		
		WSSkillSet returnValue = new WSSkillSet(gameSkillSet);
		return returnValue;
	}
	public int skillsCount()
	{
		return skills.length;
	}

	public SkillSet convertToSkillSet() {
		SkillSet skillSet = new SkillSet();
		
		skillSet.setSkills(new ArrayList<PlayerSkill>(Arrays.asList(WSPlayerSkill.convertToPlayerSkill(skills))));
		return skillSet;
	}
	public SOAPElement toSOAPElement(SOAPElement skillSetEl) throws SOAPException {
	SOAPElement skillsEl = skillSetEl.addChildElement("skills");
	//skillsEl.addAttribute(new QName("q0:arrayType"), "ns0:WSPlayerSkill[]");
	for(int i =0; i<skillsCount();i++)
	{
		//SOAPElement skillEl = skillsEl.addChildElement("WSPlayerSkill");
		skills[i].toSOAPElement(skillsEl);
	}
	return skillSetEl;
}	
	

}
