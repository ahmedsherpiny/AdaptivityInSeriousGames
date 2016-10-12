package adaptivity.server.axis2;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import adaptivity.server.axis2.Skill;

public class PlayerSkill extends Skill {

	private ArrayList<SkillValue> skillValues;
	public PlayerSkill()
	{
		skillValues = new ArrayList<SkillValue>();
	}
	
	public ArrayList<SkillValue> getSkillValue() {
		return skillValues;
	}
	public void setSkillValue(ArrayList<SkillValue> skillValue) {
		this.skillValues = skillValue;
	}
	public int getLatestValue()
	{
		int lastIndex = skillValues.size();
		return skillValues.get(lastIndex-1).getValue();
	}
	public Calendar getLatestDateTimeStamp()
	{
		int lastIndex = skillValues.size();
		return skillValues.get(lastIndex-1).getDateTimeStamp();
	}
	public void updateSkill(int value, Calendar dateTimeStamp)
	{
		SkillValue newValue = new SkillValue();
		newValue.setValue(value);
		newValue.setDateTimeStamp(dateTimeStamp);
		skillValues.add(newValue);
	}

//	public SOAPElement toSOAPElement(SOAPElement skillSetEl) throws SOAPException {
//		SOAPElement skillEl = super.toSOAPElement(skillSetEl);
//		SOAPElement skillValuesEl = skillEl.addChildElement("skillValues");
//		for(int i =0;i<skillValues.size();i++)
//		{
//			SOAPElement skillValueEl = skillValuesEl.addChildElement("skillValue");
//
//			skillValues.get(i).toSOAPElement(skillValueEl);
//		}
//		
//		return skillSetEl;
//	}

}
