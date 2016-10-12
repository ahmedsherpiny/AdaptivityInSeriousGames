package adaptivity.server.axis2.utils;

public class MonitoringEvent {
private String name;
private int affectedSkillIndex;
private int skillEffect;

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getAffectedSkillIndex() {
	return affectedSkillIndex;
}

public void setAffectedSkillIndex(int affectedSkillIndex) {
	this.affectedSkillIndex = affectedSkillIndex;
}

public int getSkillEffect() {
	return skillEffect;
}

public boolean isEqualTo(String eventName)
{
	if(eventName.equalsIgnoreCase(name))
			return true;
	else
		return false;
	}

public void setSkillEffect(int skillEffect) {
	this.skillEffect = skillEffect;
}
}
