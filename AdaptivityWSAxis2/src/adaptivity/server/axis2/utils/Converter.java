package adaptivity.server.axis2.utils;

import adaptivity.server.axis2.AdaptiveElement;
import adaptivity.server.axis2.GameModel;
import adaptivity.server.axis2.LearningGoal;
import adaptivity.server.axis2.MonitoringEvent;
import adaptivity.server.axis2.PlayerDataModel;
import adaptivity.server.axis2.PlayerSkill;
import adaptivity.server.axis2.SkillRequirement;
import adaptivity.server.axis2.SkillSet;
import adaptivity.server.axis2.ws.WSGameModel;
import adaptivity.server.axis2.ws.WSPlayerDataModel;
import adaptivity.server.axis2.ws.WSPlayerSkill;
import adaptivity.server.axis2.ws.WSSkillRequirement;
import adaptivity.server.axis2.ws.WSSkillSet;

public class Converter {
	
	public static WSGameModel convertToWS(GameModel gameModel)
	{
		WSGameModel returnValue = new WSGameModel(gameModel);
		/*returnValue.gameInstanceID = gameModel.getGameInstanceID();
		returnValue.players = (WSPlayerDataModel[]) WSPlayerDataModel.convertToWS((PlayerDataModel[]) gameModel.getPlayers().toArray());
		returnValue.gameSkillSet = WSSkillSet.convertToWS(gameModel.getGameSkillSet());
		returnValue.listOfEvents=(MonitoringEvent[]) gameModel.getListOfEvents().toArray();
		returnValue.currentGameLevel=gameModel.getCurrentGameLevel();
		if(gameModel.getGameElements()!=null)
			returnValue.gameElements=(AdaptiveElement[]) gameModel.getGameElements().toArray();
		if(gameModel.getLearningGoals()!=null)
			returnValue.learningGoals=(LearningGoal[]) gameModel.getLearningGoals().toArray();*/
		
		return returnValue;
		
	}
	
	public static WSPlayerDataModel[] convertToWS(PlayerDataModel[] array) {
		WSPlayerDataModel[] wsPlayers = new WSPlayerDataModel[array.length];
		for(int i=0; i <array.length;i++)
		{
			wsPlayers[i]=convertToWS(array[i]);
		}
				
		return wsPlayers;
	}
	private static WSPlayerDataModel convertToWS(PlayerDataModel playerDataModel) {
		WSPlayerDataModel wsPlayer = new WSPlayerDataModel(playerDataModel);
		return wsPlayer;
	}
	
	public static WSPlayerSkill[] convertToWS(PlayerSkill[] array) {
		WSPlayerSkill[] skills = new WSPlayerSkill[array.length];
		for(int i=0;i<array.length;i++)
		{
			skills[i] = WSPlayerSkill.convertToWS(array[i]);
		}
		return skills;
	}
	
	public static WSSkillRequirement[] convertToWS(SkillRequirement[] array) {
		WSSkillRequirement[] skillRequirements = new WSSkillRequirement[array.length];
		for(int i=0; i<array.length;i++)
		{
			skillRequirements[i] = convertToWS(array[i]);
		}
		return skillRequirements;
	}
	private static WSSkillRequirement convertToWS(
			SkillRequirement skillRequirement) {
		WSSkillRequirement returnValue = new WSSkillRequirement(skillRequirement);		
		return returnValue;
	}
	public static WSSkillSet convertToWS(SkillSet gameSkillSet) {
		
		WSSkillSet returnValue = new WSSkillSet(gameSkillSet);
		return returnValue;
	}
}
