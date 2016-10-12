package adaptivity.server;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.net.ConnectException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;


import adaptivity.server.GameModel;
import adaptivity.server.MonitoringEvent;
import adaptivity.server.PlayerDataModel;
import adaptivity.server.PlayerSkill;
import adaptivity.server.SkillSet;
import adaptivity.server.utils.Converter;
import adaptivity.server.axis2.ws.AdaptivityWS;
import adaptivity.server.axis2.ws.AdaptivityWSClient;
import adaptivity.server.axis2.ws.AdaptivityWSStub;
import adaptivity.server.axis2.ws.AdaptivityWSStub.GetGameModel;
import adaptivity.server.axis2.ws.AdaptivityWSStub.IsActive;
import adaptivity.server.axis2.ws.AdaptivityWSStub.IsActiveResponse;
import adaptivity.server.axis2.ws.AdaptivityWSStub.SetGameModel;
import adaptivity.server.axis2.ws.AdaptivityWSStub.SetGameModelResponse;
import adaptivity.server.axis2.ws.AdaptivityWSStub.WSGameModel;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;
import javafx.util.Duration;
import static java.lang.Math.random;

public class Launch extends Application {
	// =  http://docs.oracle.com/javafx/2/api/index.html?javafx/application/package-tree.html
	private static GameModel gameModel; 
	//private static AdaptivityWS adaptiviityWebService;
	private static AdaptivityWSClient adaptivityClient;
	public static void main(String[] args) {
		
		//instantiate game model, should be instantiated from game/user data 
		try {
			instantiateGameModel();
			
//			//run JADE
//			JADEBoot.runJade();
			
			Launch.launch( args );
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}//main

public static GameModel refreshGameModel() throws RemoteException
{
	gameModel=adaptivityClient.getGameModel();
	return gameModel;
}
	

	private static void instantiateGameModel() throws Exception {
		gameModel = new GameModel();
		
		PlayerDataModel player1 = new PlayerDataModel();
		player1.setName("Ahmed");
		SkillSet skillSet1 = new SkillSet();
		PlayerSkill addition = new PlayerSkill();
		addition.setSkillName("addition");
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(new Date(113,4,30));
		addition.updateSkill(10,cal1);;
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(new Date(113,5,1));
		addition.updateSkill(40, cal2);
		Calendar cal3 = Calendar.getInstance();
		cal3.setTime(new Date(113,5,3));
		addition.updateSkill(50, cal3);
		PlayerSkill counting = new PlayerSkill();
		counting.setSkillName("counting");
		counting.updateSkill(40, cal1);
		counting.updateSkill(70, cal2);
		counting.updateSkill(90, cal3);
		skillSet1.addSkill(counting);
		skillSet1.addSkill(addition);
		PlayerSkill subtraction = new PlayerSkill();
		subtraction.setSkillName("subtraction");
		subtraction.updateSkill(5, cal1);
		subtraction.updateSkill(20, cal2);
		subtraction.updateSkill(25, cal3);
		skillSet1.addSkill(subtraction);
		PlayerSkill multiplication= new PlayerSkill();
		multiplication.setSkillName("multiplication");
		multiplication.updateSkill(0, cal1);
		multiplication.updateSkill(0, cal2);
		multiplication.updateSkill(10, cal3);
		skillSet1.addSkill(multiplication);
		player1.setSkillSet(skillSet1);
		
		PlayerSkill recogniseNumber = new PlayerSkill();
		recogniseNumber.setSkillName("recognizeNumber");
		recogniseNumber.updateSkill(5, cal1);
		recogniseNumber.updateSkill(20, cal2);
		recogniseNumber.updateSkill(25, cal3);
		
		gameModel.addSkill(recogniseNumber);
		gameModel.addSkill(addition);
		gameModel.addSkill(subtraction);
		gameModel.addSkill(multiplication);
		
		
		
		gameModel.addPlayer(player1);
		
		
		
		ArrayList<MonitoringEvent> monitoringEvents = new ArrayList<MonitoringEvent>();
		MonitoringEvent addSuccessful = new MonitoringEvent();
		addSuccessful.setName("Add Successful");
		monitoringEvents.add(addSuccessful);
		MonitoringEvent wrongCount = new MonitoringEvent();
		wrongCount.setName("WrongNumber");
		wrongCount.setAffectedSkillIndex(0);
		wrongCount.setSkillEffect(-5);
		monitoringEvents.add(wrongCount);
		MonitoringEvent correctCount = new MonitoringEvent();
		correctCount.setName("CorrectNumber");
		correctCount.setAffectedSkillIndex(0);
		correctCount.setSkillEffect(5);
		monitoringEvents.add(correctCount);
		MonitoringEvent wrongAddition = new MonitoringEvent();
		wrongAddition.setName("Wrong addition");
		monitoringEvents.add(wrongAddition);
		gameModel.setListOfEvents(monitoringEvents);

		
		
		PlayerDataModel player2 = new PlayerDataModel();
		player2.setName("Sara");
		SkillSet saraSkillSet = new SkillSet();
		PlayerSkill jumping = new PlayerSkill();
		jumping.setSkillName("jumping");
		jumping.updateSkill(20, cal1);
		jumping.updateSkill(60, cal2);
		jumping.updateSkill(70, cal3);
		PlayerSkill walking = new PlayerSkill();
		walking.setSkillName("walking");
		walking.updateSkill(30, cal1);
		walking.updateSkill(60, cal2);
		walking.updateSkill(80, cal3);
		saraSkillSet.addSkill(walking);
		saraSkillSet.addSkill(jumping);
		PlayerSkill running = new PlayerSkill();
		running.setSkillName("running");
		running.updateSkill(5, cal1);
		running.updateSkill(20, cal2);
		running.updateSkill(25, cal3);
		saraSkillSet.addSkill(running);
		
		player2.setSkillSet(saraSkillSet);
		gameModel.addPlayer(player2);
		
/*	//	adaptiviityWebService = new AdaptivityWS();
		AdaptivityWSClient adaptivityClient = new AdaptivityWSClient();
	
		adaptivityClient.openConnection();
	
		if(adaptivityClient.isWebServiceActive())
		{
			System.out.println("Webservice is online");
			
			
			//set gameModel
			adaptivityClient.setGameModel(gameModel);
		}
		else
		{
			//System.out.println("Webservice is offline");
			throw new Exception("Webservice is offline");
		}
		
		adaptivityClient.closeConnection();
	
		
		
		
//		if(adaptiviityWebService.setGameModel(gameModel))
//			System.out.println("GameModel added to the webservice");
*/
		
		  try
		  {
		  
			// read server address from configuration file
	        DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder builder = domFactory.newDocumentBuilder();
	        Document dDoc = builder.parse("conf/config.xml");

	        XPath xPath = XPathFactory.newInstance().newXPath();
	        String node =  (String) xPath.evaluate("/WSaddress", dDoc, XPathConstants.STRING);
	        //  System.out.println(node);
			// set the url
	        String url_str = node;

	    //    AdaptivityWSSoapBindingStub service = ()new AdaptivityWSServiceLocator().getAdaptivityWS(new URL(url_str));
	        adaptivityClient = new AdaptivityWSClient(url_str);

	         	 	   
	 	   
			if(adaptivityClient.isActive())
			{
				System.out.println("Webservice is online");
				//set gameModel
				
				if(adaptivityClient.setGameModel(gameModel))
					System.out.println("GameModel added to the webservice");
				
				gameModel = adaptivityClient.getGameModel();
				
			}
			else
			{
				//System.out.println("Webservice is offline");
				throw new Exception("Webservice is offline");
			}
		  }
		  catch (Exception e)
		  {
		   e.printStackTrace();
		  }
		
		
	}


	@Override
	public void start(Stage primaryStage ) throws Exception {

		
//		  Group root = new Group();
//	        Scene scene = new Scene(root, 800, 600, Color.BLACK);
//	        primaryStage.setScene(scene);
//	        Group circles = new Group();
//	        for (int i = 0; i < 30; i++) {
//	            Circle circle = new Circle(150, Color.web("white", 0.05));
//	            circle.setStrokeType(StrokeType.OUTSIDE);
//	            circle.setStroke(Color.web("white", 0.16));
//	            circle.setStrokeWidth(4);
//	            circles.getChildren().add(circle);
//	        }
//	        Rectangle colors = new Rectangle(scene.getWidth(), scene.getHeight(),
//	                new LinearGradient(0f, 1f, 1f, 0f, true, CycleMethod.NO_CYCLE, new Stop[]{
//	                    new Stop(0, Color.web("#f8bd55")),
//	                    new Stop(0.14, Color.web("#c0fe56")),
//	                    new Stop(0.28, Color.web("#5dfbc1")),
//	                    new Stop(0.43, Color.web("#64c2f8")),
//	                    new Stop(0.57, Color.web("#be4af7")),
//	                    new Stop(0.71, Color.web("#ed5fc2")),
//	                    new Stop(0.85, Color.web("#ef504c")),
//	                    new Stop(1, Color.web("#f2660f")),}));
//	        colors.widthProperty().bind(scene.widthProperty());
//	        colors.heightProperty().bind(scene.heightProperty());
//	        Group blendModeGroup =
//	                new Group(new Group(new Rectangle(scene.getWidth(), scene.getHeight(),
//	                     Color.BLACK), circles), colors);
//	        colors.setBlendMode(BlendMode.OVERLAY);
//	        root.getChildren().add(blendModeGroup);      
//	        circles.setEffect(new BoxBlur(10, 10, 3));
//	        Timeline timeline = new Timeline();
//	        for (Node circle : circles.getChildren()) {
//	            timeline.getKeyFrames().addAll(
//	                    new KeyFrame(Duration.ZERO, // set start position at 0
//	                    new KeyValue(circle.translateXProperty(), random() * 800),
//	                    new KeyValue(circle.translateYProperty(), random() * 600)),
//	                    new KeyFrame(new Duration(40000), // set end position at 40s
//	                    new KeyValue(circle.translateXProperty(), random() * 800),
//	                    new KeyValue(circle.translateYProperty(), random() * 600)));
//	        }
//	        // play 40s of animation
//	        timeline.play();
//	        primaryStage.show();
		
		
		FXMLLoader l = new FXMLLoader();
		l.setBuilderFactory(new JavaFXBuilderFactory());
		
		   //-- If we are using JavaFX 2.0.x 
		   //   we need to use another constructor
		if( System.getProperty("javafx.version") != null && 
			System.getProperty("javafx.version").startsWith("2.0") ) {
			try {
				Constructor<JavaFXBuilderFactory> c = JavaFXBuilderFactory.class.getConstructor(boolean.class);
				l.setBuilderFactory(c.newInstance(false));
			} catch (Throwable ex ) {
				ex.printStackTrace();
				throw(ex);
			}//try
		}//if

	       //-- (something)
	//	ResourceBundle msgs = ResourceBundle.getBundle("messages");
		
	//	URL uri = Launch.class.getResource("/TutorView.fxml");
		     
	//	URL uri = Launch.class.getResource("/adaptivity/server/PlayerDataView.fxml");
		URL uri = Launch.class.getResource("/adaptivity/server/TutorView.fxml");
		
		FXMLLoader fxmlLoader = new FXMLLoader(uri);     
		Parent p = (Parent) fxmlLoader.load();  
		
	       //-- Show the scene
		Scene s = new Scene(p);
		primaryStage.setScene(s);
		
		primaryStage.show();

		//Set GameModel to the controller
	//	PlayerDataViewController controller = fxmlLoader.<PlayerDataViewController>getController();
		TutorViewController controller = fxmlLoader.<TutorViewController>getController();
		controller.setGameModel(gameModel);


		
		
		//// HELLO WORLD
//		primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
// 
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();
		
	}//start

}//Launch class
