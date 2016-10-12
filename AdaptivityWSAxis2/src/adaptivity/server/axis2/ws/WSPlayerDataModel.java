package adaptivity.server.axis2.ws;

import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.soap.SOAPElement;

import adaptivity.server.axis2.LearningStyle;
import adaptivity.server.axis2.PlayerDataModel;

public class WSPlayerDataModel {
	public String name;
	
	public WSSkillSet skillSet;
	public int ZPDValue;
	public LearningStyle learningStyle;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public WSSkillSet getSkillSet() {
		return skillSet;
	}
	public void setSkillSet(WSSkillSet skillSet) {
		this.skillSet = skillSet;
	}
	public int getZPDValue() {
		return ZPDValue;
	}
	public void setZPDValue(int zPDValue) {
		ZPDValue = zPDValue;
	}
	public LearningStyle getLearningStyle() {
		return learningStyle;
	}
	public void setLearningStyle(LearningStyle learningStyle) {
		this.learningStyle = learningStyle;
	}
public WSPlayerDataModel()
{}
	public WSPlayerDataModel(PlayerDataModel player)
	{
		name = player.getName();
		skillSet = WSSkillSet.convertToWS(player.getSkillSet());
		ZPDValue = player.getZPDValue();
		learningStyle = player.getLearningStyle();
	}
	public PlayerDataModel convertToPlayerDataModel() {
		PlayerDataModel player = new PlayerDataModel();
		player.setName(name);
		if(skillSet!=null)
			player.setSkillSet(skillSet.convertToSkillSet());
		//System.out.println("SkillSet" + player.getSkillSet());
		if(learningStyle!=null)
			player.setLearningStyle(learningStyle);
		return player;
	}
	public static ArrayList<PlayerDataModel> convertToPlayerDataModel(
			WSPlayerDataModel[] players) {
		//System.out.println("Players model" + players);
		PlayerDataModel[] playersArray = new PlayerDataModel[players.length];
		for(int i=0;i<players.length;i++)
		{
			playersArray[i] = players[i].convertToPlayerDataModel();
		}
		//System.out.println("Players Array" + playersArray);
		return new ArrayList<PlayerDataModel>(Arrays.asList(playersArray));
	}
	public static WSPlayerDataModel[] convertToWS(PlayerDataModel[] array) {
		WSPlayerDataModel[] wsPlayers = new WSPlayerDataModel[array.length];
		for(int i=0; i <array.length;i++)
		{
		//	System.out.println(array[i]);
			wsPlayers[i]=WSPlayerDataModel.convertToWS(array[i]);
		}
				
		return wsPlayers;
	}
	private static WSPlayerDataModel convertToWS(PlayerDataModel playerDataModel) {
		WSPlayerDataModel wsPlayer = new WSPlayerDataModel(playerDataModel);
		return wsPlayer;
	}
	public SOAPElement toSOAPElement(SOAPElement playersEl) {
//		SOAPElement Player = playersEl.addChildElement("skills");
//		//skillsEl.addAttribute(new QName("q0:arrayType"), "ns0:WSPlayerSkill[]");
//		for(int i =0; i<skillsCount();i++)
//		{
//			//SOAPElement skillEl = skillsEl.addChildElement("WSPlayerSkill");
//			skills[i].toSOAPElement(skillsEl);
//		}
		return playersEl;
		
	}

}
