package adaptivity.server.axis2;

import java.util.ArrayList;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class SkillSet {
private ArrayList<PlayerSkill> skills;

public SkillSet()
{
	skills = new ArrayList<PlayerSkill>();
	}
public ArrayList<PlayerSkill> getSkills() {
	return skills;
}

public void setSkills(ArrayList<PlayerSkill> skills) {
	this.skills = skills;
}

public int skillsCount()
{
	return skills.size();
	}
public void addSkill(PlayerSkill skill) {
	skills.add(skill);
}
public void removeSkill(int skillIndex) {
	skills.remove(skillIndex);
}
//public SOAPElement toSOAPElement(SOAPElement skillSetEl) throws SOAPException {
//	SOAPElement skillsEl = skillSetEl.addChildElement("skills");
//	for(int i =0; i<skillsCount();i++)
//	{
//		SOAPElement skillEl = skillsEl.addChildElement("PlayerSkill");
//		skills.get(i).toSOAPElement(skillEl);
//	}
//	return skillSetEl;
//}

}
