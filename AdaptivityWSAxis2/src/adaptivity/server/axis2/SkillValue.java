package adaptivity.server.axis2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

public class SkillValue {

	private int value;
	private Calendar dateTimeStamp;
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public Calendar getDateTimeStamp() {
		return dateTimeStamp;
	}
	public void setDateTimeStamp(Calendar dateTimeStamp) {
		this.dateTimeStamp = dateTimeStamp;
	}
	public String getDateTimeStampString() {
		Calendar cal = dateTimeStamp;
		String dateString = String.valueOf(cal.get(Calendar.DATE))+"/"+String.valueOf(cal.get(Calendar.MONTH))+"/"+String.valueOf(cal.get(Calendar.YEAR));
		return dateString;
	}
	public SOAPElement toSOAPElement(SOAPElement skillValueEl) throws SOAPException {
		SOAPElement valueEl = skillValueEl.addChildElement("value");
		valueEl.addTextNode(String.valueOf(value));
		
		SOAPElement dateTimeEl = skillValueEl.addChildElement("dateTimeStamp");
		String lFormatTemplate = "yyyy-MM-dd'T'hh:mm:ss'Z'";
		DateFormat lDateFormat = new SimpleDateFormat(lFormatTemplate);
		String lDate = lDateFormat.format(dateTimeStamp);
		dateTimeEl.addTextNode(lDate);
		return skillValueEl;
	}
}
