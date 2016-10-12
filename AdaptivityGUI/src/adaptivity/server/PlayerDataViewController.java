package adaptivity.server;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale.Category;
import java.util.ResourceBundle;

import adaptivity.server.GameModel;
import adaptivity.server.PlayerDataModel;
import adaptivity.server.PlayerSkill;
import adaptivity.server.SkillSet;
import adaptivity.server.SkillValue;
import adaptivity.server.ViewableSkillIndex;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.MouseEvent;

public class PlayerDataViewController implements Initializable {

	private GameModel gameModel;
	private ArrayList<ViewableSkillIndex> CurrentlyShowedSkills;
	@FXML
    private ListView<String> skillList;
	@FXML
	private ChoiceBox<String> playerChoiceBox;
	@FXML
	private ProgressBar progressBar1;
	@FXML private ProgressBar progressBar2;
	@FXML private ProgressBar progressBar3;
	@FXML private ProgressBar progressBar4;
	@FXML private ProgressBar progressBar5;
	@FXML private ProgressBar progressBar6;
	@FXML private ProgressBar progressBar7;
	@FXML private ProgressBar progressBar8;
	@FXML private ProgressBar progressBar9;
	@FXML private ScatterChart<Category, Number> skillsChart;
	
	private ArrayList<ProgressBar> progressArray;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playerChoiceBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override public void changed(ObservableValue ov, String t, String t1) {                
                PlayerSelected();             
            }  });
		initializeProgressBars();
		CurrentlyShowedSkills = new ArrayList<ViewableSkillIndex>();

	}

    private void initializeProgressBars() {
    	progressArray = new ArrayList<>();
    	progressArray.add(progressBar1);
    	progressArray.add(progressBar2);
    	progressArray.add(progressBar3);
    	progressArray.add(progressBar4);
    	progressArray.add(progressBar5);
    	progressArray.add(progressBar6);
    	progressArray.add(progressBar7);
    	progressArray.add(progressBar8);
    	progressArray.add(progressBar9);
	}

	@FXML
    void SkillSelected(MouseEvent event) {
		ArrayList<PlayerDataModel> players = gameModel.getPlayers();
		int selectedPlayerIndex = playerChoiceBox.getSelectionModel().getSelectedIndex();
		PlayerSkill skill=null;
		int selectedSkill=-1;
		if (selectedPlayerIndex>-1)
		{
			SkillSet selectedPlayerSkillSet = players.get(selectedPlayerIndex).getSkillSet();
			selectedSkill = skillList.getSelectionModel().getSelectedIndex();
			if(selectedSkill>-1)
				skill =selectedPlayerSkillSet.getSkills().get(selectedSkill);
		}
		
		//show skill only if not not shown, if skill is already shown do nothing
		if(!CurrentlyShowedSkills.contains(new ViewableSkillIndex(selectedPlayerIndex, selectedSkill))){
			
			XYChart.Series series1 = new XYChart.Series<Category, Number>();
	        series1.setName(skill.getSkillName());
	        for(int i =0; i<skill.getSkillValue().size();i++)
	        {
	        	SkillValue skillValue = skill.getSkillValue().get(i);
	        	series1.getData().add(new XYChart.Data(skillValue.getDateTimeStampString(),skillValue.getValue()));
	        }
	            
	        skillsChart.getData().add(series1);
	        CurrentlyShowedSkills.add(new ViewableSkillIndex(selectedPlayerIndex,selectedSkill));
		}
    }
	
  
    void PlayerSelected() {
    	updateSkillView();
    }

	public void setGameModel(GameModel _gameModel) {
		this.gameModel = _gameModel;
		
		ObservableList<String> playerItems = playerChoiceBox.getItems();
		ArrayList<PlayerDataModel> players = gameModel.getPlayers();
		int numOfPlayer = players==null?0:players.size();
		for(int i = 0 ; i<numOfPlayer;i++)
			playerItems.add(players.get(i).getName());

		playerChoiceBox.getSelectionModel().selectFirst();
		
		updateSkillView();
		
	}

	private void updateSkillView() {
		ArrayList<PlayerDataModel> players = gameModel.getPlayers();
		ObservableList<String> items = skillList.getItems();
		items.clear();
		//clear progressbar
		for(int j =0;j<progressArray.size();j++)
		{
			progressArray.get(j).setProgress(0);
			progressArray.get(j).setVisible(false);
		}
		int selectedPlayerIndex = playerChoiceBox.getSelectionModel().getSelectedIndex();
		if (selectedPlayerIndex>-1)
		{
			SkillSet selectedPlayerSkillSet = players.get(selectedPlayerIndex).getSkillSet();
	        for(int i=0; i<selectedPlayerSkillSet.skillsCount();i++)
	       	{
	       		items.add(selectedPlayerSkillSet.getSkills().get(i).getSkillName());
	       		progressArray.get(i).setProgress((double)selectedPlayerSkillSet.getSkills().get(i).getLatestValue()/100);
	       		progressArray.get(i).setVisible(true);
	       		//progressArray.get(i).setProgress(selectedPlayerSkillSet.getSkills().get(i).getLatestValue());
	        }
        }
	}
	
	public void clearSkillsChart()
	{
		skillsChart.getData().clear();
		CurrentlyShowedSkills.clear();
	}

}
