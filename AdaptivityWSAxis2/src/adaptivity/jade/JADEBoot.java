package adaptivity.jade;

import jade.core.ProfileImpl;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class JADEBoot {
	public static AdaptivityAgent adaptivityAgent;

	public static void runJade() {
			jade.Boot.main(new String[]{"-nomtp"});
			//jade.Boot.main(new String[]{"-gui"});
			
			Runtime rt = Runtime.instance();
			// Create a default profile
			ProfileImpl p = new ProfileImpl(false);
			
			jade.wrapper.AgentContainer authorContainer = rt.createAgentContainer(p); // get a container controller for creating new agents
			
			//create authorAgen
			adaptivityAgent = new AdaptivityAgent();
			try {
				AgentController t1 = authorContainer.acceptNewAgent("The Adaptivity Agent", adaptivityAgent);				
				t1.start();
				
				AgentController t2 = authorContainer.acceptNewAgent("The EventAnalyzer Agent", new AdaptivityAgent());				
				t2.start();
				
			} catch (StaleProxyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

}
