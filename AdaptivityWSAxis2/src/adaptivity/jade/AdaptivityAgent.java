package adaptivity.jade;

import java.io.CharConversionException;
import java.util.ArrayList;
import java.util.Calendar;

import adaptivity.jade.behaviours.AnalyzeEvent;
import adaptivity.server.axis2.GameModel;
import adaptivity.server.axis2.MonitoringEvent;
import adaptivity.server.axis2.PlayerDataModel;
import adaptivity.server.axis2.PlayerSkill;
import adaptivity.server.axis2.SkillValue;
import jade.core.AID;
import jade.core.Agent;
import jade.core.AgentContainer;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.StaleProxyException;


public class AdaptivityAgent extends Agent {
	
	private GameModel gameModel;
	

public void sendMessage(int messageType, String content, String receiver)
{
	ACLMessage msg = new ACLMessage(messageType);
	msg.setContent(content);

	msg.addReceiver(new AID(receiver, AID.ISLOCALNAME));
	send(msg);
	
	System.out.println(getLocalName()+" sent "+content+" to " + receiver); 

	}
	
	protected void setup() {
		System.out.println(getLocalName()+" STARTED");
		Object[] args = getArguments();
		if (args != null && args.length > 0) {
		//	setCharacterObject((storyCharacter) args[0]);
		}

//		Search for an agent
//		DFAgentDescription dfdEnv = new DFAgentDescription();
//         ServiceDescription sd  = new ServiceDescription();
//         sd.setType( "starting_environment" );
//         dfdEnv.addServices(sd);
			//initiator = new AID(characterObject.getName(), AID.ISLOCALNAME);
		try {
//			DFAgentDescription[] result;
//			int i=0;
//			do
//			{
//				i++;
//				result = DFService.search(this, dfdEnv);
//			} while (result.length<=0 && i<10);
//			if(i==10) System.out.println("Can not find initial environment");
//			currentEnvironment = result[0].getName().getLocalName();

		
			// create the agent descrption of itself
			DFAgentDescription dfd = new DFAgentDescription();
			dfd.setName(getAID());
			// register the description with the DF
			DFService.register(this, dfd);
			System.out.println(getLocalName()+" REGISTERED WITH THE DF");
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		
		
		
		//Static story
		
//		//Introduction
		System.out.println(getLocalName()+" is running now and waiting to receive message.");
	
//
//		//
			// send them a GREETINGS message
		// example of message
		//sendMessage(ACLMessage.PROPOSE,RUN_ENGINE_BY_SAMART_PHONE,currentEnvironment)
			
		//example of logging
		//System.out.println(getLocalName()+" uses the car administration app on "+singularPossessivePronouns+" smart phone to run the car engine "); 

		
		////move to new environment
		//sendMessage(ACLMessage.PROPOSE,MOVE_TO_ENV,currentEnvironment)
		//System.out.println(getLocalName()+ " moves down to the Road ");


		  /* IF YOU COMMENTED OUT THIS ELSE CLAUSE, THEN YOU WOULD GENERATE
	      AN INTERESTING INFINITE LOOP WITH INFINTE AGENTS AND AGENT 
	      CONTAINERS BEING CREATED 
	      else {
	      IAmTheCreator = true;
	      doWait(2000); // wait two seconds
		 
		}*/

		//Let the Agent listen to event and analyze them
		addBehaviour(new AnalyzeEvent());
		
		// add a Behaviour that listen if a greeting message arrives
				// and sends back an ANSWER.
				// if an ANSWER to a greetings message is arrived 
				// then send a THANKS message
	/*			addBehaviour(new CyclicBehaviour(this) {
	public void action() {
	
			ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.ACCEPT_PROPOSAL));
			System.out.println(msg.getContent()+" RECEIVED BY" + myAgent.getLocalName());
			if (msg != null) {
				if (msg.getContent().equalsIgnoreCase("event detected: CorrectNumber")) {
					// if a greetings message is arrived then send an ANSWER
					System.out.println(msg.getContent()+" RECEIVED BY" + myAgent.getLocalName()); 
				} 
				else if (msg.getContent().equalsIgnoreCase("event detected: WrongNumber")) {
					// if a greetings message is arrived then send an ANSWER
					System.out.println(msg.getContent()+" RECEIVED BY" + myAgent.getLocalName()); 
				} 
	//			else if (SATISFY_GOAL.equalsIgnoreCase(msg.getContent())) {
	//				System.out.println(myAgent.getLocalName()+" RECEIVED SATISFY GOAL MESSAGE FROM "+msg.getSender().getLocalName());
	//				System.out.println(myAgent.getLocalName()+ " has achieved "+singularPossessivePronouns+" goal");
	//				// Kill the created container
	//				try {
	//					ac.kill();
	//				} catch (StaleProxyException e) {
	//					// TODO Auto-generated catch block
	//					e.printStackTrace();
	//				}
	//
	//			}
				else {
					System.out.println(myAgent.getLocalName()+" Unexpected message received from "+msg.getSender().getLocalName()); 
				}					
			}
			else {
				// if no message is arrived, block the behaviour
				block();
			}
	}});*/
			}

			protected void takeDown() {
				// Deregister with the DF
				try {
					
					System.out.println("----THE END----");
					
					DFService.deregister(this);
					System.out.println(getLocalName()+" DEREGISTERED WITH THE DF");
				} catch (FIPAException e) {
					e.printStackTrace();
				}
			}
			
			public void setGameModel(GameModel _gameModel)
			{
				System.out.println("set game Model into adaptivity Agent: "+_gameModel);
				this.gameModel=_gameModel;
			}
			public void performAction(Action action, String receiver)
			{
				sendMessage(ACLMessage.PROPOSE,action.getName(),receiver);
			}

			public void updateskillValue(String eventName)
			{
				//System.out.println("gameModel is "+ gameModel);
				ArrayList<MonitoringEvent> events = gameModel.getListOfEvents();
				int skillIndex = -1;
				int increment = 0;
				for(int i=0;i<events.size();i++)
				{
					MonitoringEvent event = events.get(i);
					if(event.isEqualTo(eventName))
					{
						System.out.println("Event found in index "+i);
						skillIndex = event.getAffectedSkillIndex();
						increment= event.getSkillEffect();
					}
					
				}
			
				System.out.println("SKILL INDEX IS "+ skillIndex + " and increment is " +increment);
				if(skillIndex>=0){
					PlayerSkill retrievedSkill = gameModel.getPlayers().get(gameModel.getPlayers().size()-1).getSkillSet().getSkills().get(skillIndex);
					int currentValue = retrievedSkill.getLatestValue();
					SkillValue value = new SkillValue();
					value.setValue(currentValue+increment);
					Calendar cal1 =Calendar.getInstance();
					cal1.setTime(Calendar.getInstance().getTime());
					value.setDateTimeStamp(cal1);
					retrievedSkill.getSkillValue().add(value);
					
					System.out.println("Skill "+retrievedSkill.getSkillName()+" updated from value " +currentValue+ " to value "+retrievedSkill.getLatestValue()+ " on " +retrievedSkill.getLatestDateTimeStamp());
					System.out.println("Skill "+gameModel.getPlayers().get(gameModel.getPlayers().size()-1).getSkillSet().getSkills().get(skillIndex).getSkillName()+" updated from value " +currentValue+ " to value "+gameModel.getPlayers().get(gameModel.getPlayers().size()-1).getSkillSet().getSkills().get(skillIndex).getLatestValue()+ " on " +gameModel.getPlayers().get(gameModel.getPlayers().size()-1).getSkillSet().getSkills().get(skillIndex).getLatestDateTimeStamp());
				}
				else
				{
					System.out.println("NO SKILL TO UPDATE FOR THIS EVENT");
				}
			}

			public GameModel getUpdatedGameModel() {
				// TODO Auto-generated method stub
				return gameModel;
			}	

}

