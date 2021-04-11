package tasks.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;

import java.util.Date;

public class TasksService {

    private ArrayTaskList tasks;

    public TasksService(ArrayTaskList tasks) {
        this.tasks = tasks;
    }


    public ObservableList<Task> getObservableList() {
        return FXCollections.observableArrayList(tasks.getAll());
    }

    public String getIntervalInHours(Task task) {
        int seconds = task.getRepeatInterval();
        int minutes = seconds / DateService.SECONDS_IN_MINUTE;
        int hours = minutes / DateService.MINUTES_IN_HOUR;
        minutes = minutes % DateService.MINUTES_IN_HOUR;
        return formTimeUnit(hours) + ":" + formTimeUnit(minutes);//hh:MM
    }

    public String formTimeUnit(int timeUnit) {
        StringBuilder sb = new StringBuilder();
        if (timeUnit < 10)
            sb.append("0");
        else {
            sb.append(timeUnit);
        }
        return sb.toString();
    }


    public int parseFromStringToSeconds(String stringTime) {//hh:MM
        String[] units = stringTime.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int result = (hours * DateService.MINUTES_IN_HOUR + minutes) * DateService.SECONDS_IN_MINUTE;
        return result;
    }

    public Iterable<Task> filterTasks(Date start, Date end) {
        TasksOperations tasksOps = new TasksOperations(getObservableList());
        Iterable<Task> filtered = tasksOps.incoming(start, end);
        //Iterable<Task> filtered = tasks.incoming(start, end);

        return filtered;
    }

    public void addTask(Task task) {
        this.validateTask(task);
        tasks.add(task);
        TaskIO.rewriteFile(tasks);
    }

    public void updateTask(Task task) {
        boolean found = false;
        for (Task other : tasks.getAll()) {
            if (other.getTitle().equals(task.getTitle())) {
                found = true;
            }
            if (found) {
                break;
            }
        }
        if (!found) {
            throw new RuntimeException("Task not found");
        } else {
            this.addTask(task);
        }
    }

    public void validateTask(Task task) {
        if (task.getStartTime().after(task.getEndTime()) || task.getStartTime().equals(task.getEndTime())) {
            throw new RuntimeException("Start time must be before end time");
        }
        if (task.isRepeated() && task.getRepeatInterval() <= 0) {
            throw new RuntimeException("Interval must be strictly positive");
        }
    }
}
