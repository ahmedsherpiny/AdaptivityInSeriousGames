package adaptivity.server.axis2.ws;


import java.util.ArrayList;
import java.util.Arrays;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;

import adaptivity.server.axis2.AdaptiveElement;
import adaptivity.server.axis2.GameModel;
import adaptivity.server.axis2.LearningGoal;
import adaptivity.server.axis2.MonitoringEvent;
import adaptivity.server.axis2.PlayerDataModel;

public class WSGameModel {
	
	/*
	 * This class, and associated WS classes are created as work around that the webservice creator JAX_RPC does not support ArrayLists
	 * */

	public String gameInstanceID;
	public String getGameInstanceID() {
		return gameInstanceID;
	}

	public void setGameInstanceID(String gameInstanceID) {
		this.gameInstanceID = gameInstanceID;
	}

	public WSPlayerDataModel[] getPlayers() {
		return players;
	}

	public void setPlayers(WSPlayerDataModel[] players) {
		this.players = players;
	}

	public WSSkillSet getGameSkillSet() {
		return gameSkillSet;
	}

	public void setGameSkillSet(WSSkillSet gameSkillSet) {
		this.gameSkillSet = gameSkillSet;
	}

	public MonitoringEvent[] getListOfEvents() {
		return listOfEvents;
	}

	public void setListOfEvents(MonitoringEvent[] listOfEvents) {
		this.listOfEvents = listOfEvents;
	}

	public int getCurrentGameLevel() {
		return currentGameLevel;
	}

	public void setCurrentGameLevel(int currentGameLevel) {
		this.currentGameLevel = currentGameLevel;
	}

	public AdaptiveElement[] getGameElements() {
		return gameElements;
	}

	public void setGameElements(AdaptiveElement[] gameElements) {
		this.gameElements = gameElements;
	}

	public LearningGoal[] getLearningGoals() {
		return learningGoals;
	}

	public void setLearningGoals(LearningGoal[] learningGoals) {
		this.learningGoals = learningGoals;
	}
	public WSPlayerDataModel players[];
	public WSSkillSet gameSkillSet;
	public MonitoringEvent listOfEvents[];
	public int currentGameLevel;
	public AdaptiveElement gameElements[];
	public LearningGoal learningGoals[];


	public WSGameModel(GameModel gameModel)
	{
		init(gameModel);
	}
	
	public static WSGameModel convertToWS(GameModel gameModel)
	{
		WSGameModel returnValue = new WSGameModel(gameModel);
		/*returnValue.gameInstanceID = gameModel.getGameInstanceID();
		returnValue.players = (WSPlayerDataModel[]) WSPlayerDataModel.convertToWS((PlayerDataModel[]) gameModel.getPlayers().toArray());
		returnValue.gameSkillSet = WSSkillSet.convertToWS(gameModel.getGameSkillSet());
		returnValue.listOfEvents=(MonitoringEvent[]) gameModel.getListOfEvents().toArray();
		returnValue.currentGameLevel=gameModel.getCurrentGameLevel();
		returnValue.gameElements=(AdaptiveElement[]) gameModel.getGameElements().toArray();
		returnValue.learningGoals=(LearningGoal[]) gameModel.getLearningGoals().toArray();*/
		return returnValue;
		
	}
	public WSGameModel()
	{}
	public WSGameModel init(GameModel gameModel)
	{
		System.out.println("gameModel: "+gameModel);
		gameInstanceID = gameModel.getGameInstanceID();
		PlayerDataModel[] playersArray =(PlayerDataModel[]) gameModel.getPlayers().toArray(new PlayerDataModel[gameModel.getPlayers().size()]);
		//System.out.println(playersArray);
		players = (WSPlayerDataModel[]) WSPlayerDataModel.convertToWS(playersArray);
		gameSkillSet = WSSkillSet.convertToWS(gameModel.getGameSkillSet());
		listOfEvents=(MonitoringEvent[]) gameModel.getListOfEvents().toArray(new MonitoringEvent[gameModel.getListOfEvents().size()]);
		currentGameLevel=gameModel.getCurrentGameLevel();
		if(gameModel.getGameElements()!=null)
			gameElements=(AdaptiveElement[]) gameModel.getGameElements().toArray(new AdaptiveElement[gameModel.getGameElements().size()]);
		if(gameModel.getLearningGoals()!=null)
			learningGoals=(LearningGoal[]) gameModel.getLearningGoals().toArray(new LearningGoal[gameModel.getLearningGoals().size()]);
		return this;
		
	}
	public GameModel convertToGameModel()
	{
	//	System.out.println("Start convreting WSGameModel to GameModel");
		GameModel returnValue = new GameModel();
		returnValue.setGameInstanceID(gameInstanceID);
		//System.out.println("game instance set");
		returnValue.setPlayers(WSPlayerDataModel.convertToPlayerDataModel(players));
		//System.out.println("Player Model set");
		returnValue.setGameSkillSet(gameSkillSet.convertToSkillSet());
		//System.out.println("game skillSet set");
		returnValue.setListOfEvents((new ArrayList<MonitoringEvent>(Arrays.asList(listOfEvents))));
		//System.out.println("events set");
		returnValue.setCurrentGameLevel(currentGameLevel);
		//System.out.println("game level set");
		if(gameElements!=null)
			returnValue.setGameElements((new ArrayList<AdaptiveElement>(Arrays.asList(gameElements))));
		//System.out.println("game elements set");
		if(learningGoals!=null)
			returnValue.setLearningGoals((new ArrayList<LearningGoal>(Arrays.asList(learningGoals))));
		//System.out.println("learning goals set");
		
		return returnValue;
	}
	public SOAPElement toSOAPElement(SOAPElement xml) throws SOAPException
{
	SOAPElement gameModelEl = xml.addChildElement("GameModel");
	if(gameInstanceID != null)
	{
		SOAPElement gameInstEl = gameModelEl.addChildElement("gameInstanceID");
		gameInstEl.addTextNode(gameInstanceID);
	}
	SOAPElement playersEl = gameModelEl.addChildElement("players");
	playersEl.addAttribute(new QName("arrayType"), "WSPlayerDataModel[]");

	for(int i=0;i<players.length;i++)
		players[i].toSOAPElement(playersEl);
	SOAPElement skillSetEl = gameModelEl.addChildElement("gameSkillSet");
	gameSkillSet.toSOAPElement(skillSetEl);
	SOAPElement currentLevelEl = gameModelEl.addChildElement("currentGameLevel");
	currentLevelEl.addTextNode(String.valueOf(currentGameLevel));
	return xml;
	}
}
