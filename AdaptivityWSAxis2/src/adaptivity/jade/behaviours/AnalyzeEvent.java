package adaptivity.jade.behaviours;

import adaptivity.jade.AdaptivityAgent;
import adaptivity.jade.JADEBoot;
import adaptivity.server.axis2.PlayerSkill;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.wrapper.StaleProxyException;




public class AnalyzeEvent extends CyclicBehaviour {

	@Override
	public void action() {

		ACLMessage msg = myAgent.receive(MessageTemplate.MatchPerformative(ACLMessage.PROPOSE));
		//System.out.println("Analyze event behavior is running");
		if (msg != null) {
		//	System.out.println(msg.getContent()+" RECEIVED BY" + myAgent.getLocalName());
			if (msg.getContent().equalsIgnoreCase("event detected: CorrectNumber")) {
				// if a greetings message is arrived then send an ANSWER
				System.out.println(msg.getContent()+" RECEIVED BY " + myAgent.getLocalName());
				
				((AdaptivityAgent)myAgent).updateskillValue("CorrectNumber");

			} 
			else if (msg.getContent().equalsIgnoreCase("event detected: WrongNumber")) {
				// if a greetings message is arrived then send an ANSWER
				System.out.println(msg.getContent()+" RECEIVED BY" + myAgent.getLocalName());
				((AdaptivityAgent)myAgent).updateskillValue("WrongNumber");
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
	}


//	@Override
//	public boolean done() {
//		// TODO Auto-generated method stub
//		return false;
//	}

}
