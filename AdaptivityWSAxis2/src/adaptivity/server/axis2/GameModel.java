package adaptivity.server.axis2;

import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.namespace.QName;
import javax.xml.soap.Name;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;
import org.w3c.dom.UserDataHandler;

import adaptivity.server.axis2.AdaptiveElement;
import adaptivity.server.axis2.LearningGoal;
import adaptivity.server.axis2.MonitoringEvent;

public class GameModel {
private String gameInstanceID;
private ArrayList<PlayerDataModel> players;
private SkillSet gameSkillSet;
private ArrayList<MonitoringEvent> listOfEvents;
private int currentGameLevel;
private ArrayList<AdaptiveElement> gameElements;
private ArrayList<LearningGoal> learningGoals;


public GameModel()
{
	players = new ArrayList<PlayerDataModel>();
	gameSkillSet = new SkillSet();
	}

public ArrayList<PlayerDataModel> getPlayers() {
	return players;
}
public SkillSet getGameSkillSet() {
	return gameSkillSet;
}

public void setPlayers(ArrayList<PlayerDataModel> players) {
	this.players = players;
}

public void addSkill(PlayerSkill skill)
{
	gameSkillSet.addSkill(skill);
	for(int i=0; i<players.size();i++)
	{
		players.get(i).getSkillSet().addSkill(skill);
	}
}
public void removeSkill(int skillIndex)
{
	gameSkillSet.removeSkill(skillIndex);
/*	for(int i=0; i<players.size();i++)
	{
		players.get(i).getSkillSet().removeSkill(skillIndex);
	}*/
}

public void addPlayer(PlayerDataModel player)
{
	if(gameSkillSet.getSkills().size()>0)
		player.setSkillSet(gameSkillSet);
	players.add(player);
	}

public ArrayList<MonitoringEvent> getListOfEvents() {
	return listOfEvents;
}

public void setListOfEvents(ArrayList<MonitoringEvent> listOfEvents) {
	this.listOfEvents = listOfEvents;
}
//public SOAPElement toSOAPElement(SOAPElement xml) throws SOAPException
//{
//	SOAPElement gameModelEl = xml.addChildElement("GameModel");
//	if(gameInstanceID != null)
//	{
//		SOAPElement gameInstEl = gameModelEl.addChildElement("gameInstanceID");
//		gameInstEl.addTextNode(gameInstanceID);
//	}
//	SOAPElement skillSetEl = gameModelEl.addChildElement("gameSkillSet");
//	gameSkillSet.toSOAPElement(skillSetEl);
//	SOAPElement currentLevelEl = gameModelEl.addChildElement("currentGameLevel");
//	currentLevelEl.addTextNode(String.valueOf(currentGameLevel));
//	return xml;
//	}

public String getGameInstanceID() {
	return gameInstanceID;
}

public ArrayList<AdaptiveElement> getGameElements() {
	return gameElements;
}

public int getCurrentGameLevel() {
	return currentGameLevel;
}

public ArrayList<LearningGoal> getLearningGoals() {
	return learningGoals;
}

public void setGameInstanceID(String gameInstanceID) {
	this.gameInstanceID=gameInstanceID;	
}

public void setCurrentGameLevel(int currentGameLevel) {
	this.currentGameLevel = currentGameLevel;	
}

public void setGameElements(ArrayList<AdaptiveElement> arrayList) {
	this.gameElements=arrayList;	
}

public void setLearningGoals(ArrayList<LearningGoal> arrayList) {
	this.learningGoals = arrayList;	
}

public void setGameSkillSet(SkillSet gameSkillSet) {
	this.gameSkillSet=gameSkillSet;
}
}
