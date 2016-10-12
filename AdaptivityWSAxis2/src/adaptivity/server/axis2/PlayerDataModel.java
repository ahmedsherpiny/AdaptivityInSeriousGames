package adaptivity.server.axis2;

import adaptivity.server.axis2.LearningStyle;

public class PlayerDataModel {
	private String name;
	private SkillSet skillSet;
	private int ZPDValue;
	private LearningStyle learningStyle;
	public int getZPDValue() {
		return ZPDValue;
	}
	public void setZPDValue(int zPDValue) {
		ZPDValue = zPDValue;
	}
	
//	public SkillSet getLatestSkillSet()
//	{
//		int lastIndex = skillsHistory.getSkillSet().size();
//		return skillsHistory.getSkillSet().get(lastIndex);
//	}
//	public Date getLatestSkillSetTimeStamp()
//	{
//		int lastIndex = skillsHistory.getSkillTimeStamp().size();
//		return skillsHistory.getSkillTimeStamp().get(lastIndex);
//	}
//	public void updateSkillSet(SkillSet skill, Date timeStamp) {
//		// TODO Auto-generated method stub
//		skillsHistory.getSkillSet().add(skill);
//		skillsHistory.getSkillTimeStamp().add(timeStamp);
//		
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SkillSet getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(SkillSet skillSet) {
		this.skillSet = skillSet;
	}
	public LearningStyle getLearningStyle() {
		return learningStyle;
	}
	public void setLearningStyle(LearningStyle learningStyle) {
		this.learningStyle=learningStyle;		
	}

}
