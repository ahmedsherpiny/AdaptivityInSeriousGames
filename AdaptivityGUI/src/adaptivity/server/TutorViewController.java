package adaptivity.server;

import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import adaptivity.server.AddSkillController;
import adaptivity.server.GameModel;
import adaptivity.server.MonitoringEvent;
import adaptivity.server.PlayerDataModel;
import adaptivity.server.SkillSet;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class TutorViewController implements Initializable {

	private GameModel gameModel;
	@FXML
    private ListView<String> skillList;
	@FXML
    private ListView<String> playerList;
	@FXML
    private ListView<String> eventList;
	@FXML
    private TextArea details;
	@FXML
	private Button btn_ConnectEventToSkill;
	@FXML
	private Button btn_removeSkill;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
	}

  
	@FXML
    void SkillSelected(MouseEvent event) {
	
		//show skill details
		
		// enable remove skill button
		int selectedSkill = skillList.getSelectionModel().getSelectedIndex();
		if (selectedSkill>-1)
		{
			btn_removeSkill.setDisable(false);
		}
		else
		{
			btn_removeSkill.setDisable(true);
		}
    }
	
	@FXML
    void PlayerSelected(MouseEvent event) {
		//show player details
		
		
    }

	@FXML
    void EventSelected(MouseEvent event) {
		//show event details
		
		// enable 'connect event to skill' button
		int selectedEvent = eventList.getSelectionModel().getSelectedIndex();
		if (selectedEvent>-1)
		{
			btn_ConnectEventToSkill.setDisable(false);
		}
		else
		{
			btn_ConnectEventToSkill.setDisable(true);
		}

    }
	@FXML
    void refreshLists(ActionEvent event) {
		clearListViews();
		refreshListViews();
	}
	@FXML
    void viewPlayerSkills(ActionEvent event) {
		 Parent root;
	        try {
	        	URL uri = Launch.class.getResource("/adaptivity/server/PlayerDataView.fxml");
	    		
	    		FXMLLoader fxmlLoader = new FXMLLoader(uri);     
	    		root = (Parent) fxmlLoader.load();  

	            Stage stage = new Stage();
	    		Scene s = new Scene(root);
	    		stage.setScene(s);
	            stage.show();
	            
	            PlayerDataViewController controller = fxmlLoader.<PlayerDataViewController>getController();
	    		controller.setGameModel(gameModel);

	            //hide this current window (if this is want you want
	       //     ((Node)(event.getSource())).getScene().getWindow().hide();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    }
	
	@FXML
    void AddEvent(ActionEvent event) {
		 Parent root;
	        try {
	        	URL uri = Launch.class.getResource("/adaptivity/server/AddEvent.fxml");
	    		
	    		FXMLLoader fxmlLoader = new FXMLLoader(uri);     
	    		root = (Parent) fxmlLoader.load();  

	            Stage stage = new Stage();
	    		Scene s = new Scene(root);
	    		stage.setScene(s);
	            stage.show();
	            
	    //        PlayerDataViewController controller = fxmlLoader.<PlayerDataViewController>getController();
	    	//	controller.setGameModel(gameModel);

	            //hide this current window (if this is want you want
	       //     ((Node)(event.getSource())).getScene().getWindow().hide();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    }
	@FXML
    void AddSkill(ActionEvent event) {
		 Parent root;
	        try {
	        	URL uri = Launch.class.getResource("/adaptivity/server/AddSkill.fxml");
	    		
	    		FXMLLoader fxmlLoader = new FXMLLoader(uri);     
	    		root = (Parent) fxmlLoader.load();  

	            Stage stage = new Stage();
	    		Scene s = new Scene(root);
	    		stage.setScene(s);
	            stage.show();
	            
	            AddSkillController controller = fxmlLoader.<AddSkillController>getController();
	    		controller.setSkillSet(gameModel.getGameSkillSet());

	            //hide this current window (if this is want you want
	       //     ((Node)(event.getSource())).getScene().getWindow().hide();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    }

	@FXML
    void ConnectEventToSkill(ActionEvent event) {
		Parent root;
        try {
        	URL uri = Launch.class.getResource("/adaptivity/server/ConnectEventToSkill.fxml");
    		
    		FXMLLoader fxmlLoader = new FXMLLoader(uri);     
    		root = (Parent) fxmlLoader.load();  

            Stage stage = new Stage();
    		Scene s = new Scene(root);
    		stage.setScene(s);
            stage.show();
            
          //  PlayerDataViewController controller = fxmlLoader.<PlayerDataViewController>getController();
    	//	controller.setGameModel(gameModel);

            //hide this current window (if this is want you want
       //     ((Node)(event.getSource())).getScene().getWindow().hide();

        } catch (IOException e) {
            e.printStackTrace();
        }

	}
	
	@FXML
    void RemoveSkill(ActionEvent event) {
		
		int selectedSkill = skillList.getSelectionModel().getSelectedIndex();
		gameModel.removeSkill(selectedSkill);
		skillList.getItems().clear();
		refreshSkillView();
		btn_removeSkill.setDisable(true);
	}



	private void clearListViews() {
		skillList.getItems().clear();
		playerList.getItems().clear();
		eventList.getItems().clear();
	}


	public void setGameModel(GameModel _gameModel) {
		this.gameModel = _gameModel;
		
		refreshListViews();
		
	}


	private void refreshListViews() {
		//refresh gameModel
		try {
			gameModel=Launch.refreshGameModel();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
		ObservableList<String> playerItems = playerList.getItems();
		ArrayList<PlayerDataModel> players = gameModel.getPlayers();
		int numOfPlayer = players==null?0:players.size();
		for(int i = 0 ; i<numOfPlayer;i++)
			playerItems.add(players.get(i).getName());

		//playerList.getSelectionModel().selectFirst();
		
		
		refreshSkillView();

		//skillList.getSelectionModel().selectFirst();
		
		
		ObservableList<String> eventItems = eventList.getItems();
		ArrayList<MonitoringEvent> events = gameModel.getListOfEvents();
		int numOfeventss = events==null?0:events.size();
		for(int i = 0 ; i<numOfeventss;i++)
			eventItems.add(events.get(i).getName());

		
	}


	private void refreshSkillView() {
		ObservableList<String> skillsItems = skillList.getItems();
		SkillSet skillSet = gameModel.getGameSkillSet();
		int numOfskills = skillSet==null?0:skillSet.skillsCount();
		for(int i = 0 ; i<numOfskills;i++)
			skillsItems.add(skillSet.getSkills().get(i).getSkillName());		
	}

}
