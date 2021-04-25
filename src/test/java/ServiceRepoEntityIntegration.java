import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TaskIO;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceRepoEntityIntegration {
    private static TasksService tasksService;
    private static TaskIO taskIO;

    @BeforeAll
    static void init() {
        taskIO = new TaskIO();
        tasksService = new TasksService(new ArrayTaskList(), taskIO);
    }

    @Test
    void test_non_valid_ecp_1() {
        Task task = new Task(
                "desc1",
                new Date(2021, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                -5
        );
        assertThrows(RuntimeException.class, () -> tasksService.addTask(task));
    }

    @Test
    void test_valid_bva_1() {
        Task task = new Task(
                "desc1",
                new Date(2021, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                1
        );
        assertDoesNotThrow(() -> tasksService.addTask(task));
    }

    @Test
    public void testTaskSaveFails() {
        Task task = new Task(
                "vulgar",
                new Date(2021, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                1
        );
        Assertions.assertThrows(RuntimeException.class, () -> tasksService.addTask(task));
    }
}
