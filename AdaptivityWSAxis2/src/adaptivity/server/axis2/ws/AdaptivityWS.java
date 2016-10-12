package adaptivity.server.axis2.ws;

//import adaptivity.jade.AdaptivityAgent;
import adaptivity.jade.Action;
import adaptivity.jade.AdaptivityAgent;
import adaptivity.jade.JADEBoot;
import adaptivity.server.axis2.GameModel;
import adaptivity.server.axis2.PlayerDataModel;
import adaptivity.server.axis2.PlayerSkill;
import adaptivity.server.axis2.utils.MonitoringEvent;

public class AdaptivityWS {
//	private AdaptivityAgent adaptivityAgent;
	private static GameModel _gameModel;
	private static boolean JADERunning = false;
	
	public boolean isActive()
	{
		if(!JADERunning)
		{
			//run JADE
			JADEBoot.runJade();
			JADERunning=true;
			
		}
		return true;
	}
	
	public WSGameModel eventDetected (MonitoringEvent event)
	{
		System.out.println("Event "+event.getName()+" detected");
		Action action = new Action();
		action.setName("event detected: "+event.getName());
		JADEBoot.adaptivityAgent.setGameModel(_gameModel);
		//JADEBoot.adaptivityAgent.performAction(action , "The EventAnalyzer Agent");
		JADEBoot.adaptivityAgent.performAction(action , "The Adaptivity Agent");
		_gameModel = JADEBoot.adaptivityAgent.getUpdatedGameModel();
		
		return new WSGameModel(_gameModel);
	}
	public boolean setGameModel (WSGameModel gameModel)
	{
		//boolean returnValue = false;
		System.out.println("Game Model received:" + gameModel.toString());
		//System.out.println(gameModel.getGameSkillSet().getSkills()[0].getRequirements().length);
		
		_gameModel = gameModel.convertToGameModel();
	//	System.out.println(this.gameModel.getGameSkillSet().getSkills().size());
	//	System.out.println(this.gameModel.getPlayers().size());
		
		System.out.println("Game model is set");
		
		return true;
	}
	public boolean addPlayer(WSPlayerDataModel player)
	{
		boolean returnValue = false;
		if (_gameModel != null)
		{
			_gameModel.addPlayer(player.convertToPlayerDataModel());
			returnValue = true;
			System.out.println("Player "+player.convertToPlayerDataModel().getName()+" added");
		}
		else
		{
		      System.err.println("Error: No GameModel defined. Define one before adding player");
		}

		return returnValue;
	}
	public boolean addSkill(WSPlayerSkill skill)
	{
		boolean returnValue = false;
		if (_gameModel != null)
		{
			_gameModel.addSkill(skill.convertToPlayerSkill());
			returnValue = true;
			System.out.println("Skill "+skill.getSkillName()+" added");
		}
		else
		{
		      System.err.println("Error: No gamemodel defined. Define one before adding player");
		}

		return returnValue;
	}
	
	public WSGameModel getGameModel()
	{
//		System.out.println("gameModel: "+gameModel);
		WSGameModel wsGameModel =null;
		if (_gameModel != null)
		{
			wsGameModel = new WSGameModel(_gameModel);
			//System.out.println(wsGameModel.players.length);
			System.out.println("WSgameModel: "+wsGameModel);
		}
		else
		{
		      System.err.println("Error: No gamemodel defined. Define one before adding player");
		}
		return wsGameModel;
	}

}
