package tasks.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.log4j.Logger;
import tasks.model.Task;
import tasks.services.TaskIO;


public class TaskInfoController {

    private static final Logger log = Logger.getLogger(TaskInfoController.class.getName());
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelStart;
    @FXML
    private Label labelEnd;
    @FXML
    private Label labelInterval;
    @FXML
    private Label labelIsActive;
    private TaskIO taskIO;

    public TaskIO getTaskIO() {
        return taskIO;
    }

    public void setTaskIO(TaskIO taskIO) {
        this.taskIO = taskIO;
    }

    @FXML
    public void initialize() {
        log.info("task info window initializing");
        Task currentTask = Controller.mainTable.getSelectionModel().getSelectedItem();
        labelTitle.setText("Title: " + currentTask.getTitle());
        labelStart.setText("Start time: " + currentTask.getFormattedDateStart());
        labelEnd.setText("End time: " + currentTask.getFormattedDateEnd());
        labelInterval.setText("Interval: " + currentTask.getFormattedRepeated(taskIO));
        labelIsActive.setText("Is active: " + (currentTask.isActive() ? "Yes" : "No"));
    }

    @FXML
    public void closeWindow() {
        Controller.infoStage.close();
    }

}
