package bpir2456;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TaskIO;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ServiceRepoIntegration {

    private static TasksService tasksService;
    private static TaskIO taskIO;
    private static Task task;

    @BeforeAll
    static void init() {
        taskIO = new TaskIO();
        tasksService = new TasksService(new ArrayTaskList(), taskIO);
        task = Mockito.mock(Task.class);
        Mockito.when(task.getTitle()).thenReturn("nonvulgar");
        Mockito.when(task.getTime()).thenReturn(new Date(2021, Calendar.MARCH, 2));
        Mockito.when(task.isRepeated()).thenReturn(false);
        Mockito.when(task.getStartTime()).thenReturn(new Date(2021, Calendar.MARCH, 2));
        Mockito.when(task.getEndTime()).thenReturn(new Date(2021, Calendar.JUNE, 2));
        Mockito.when(task.isActive()).thenReturn(true);
        Mockito.when(task.getRepeatInterval()).thenReturn(0);
    }

    @Test
    void test_non_valid_ecp_1() {
        assertThrows(RuntimeException.class, () -> tasksService.addTask(task));
    }

    @Test
    void test_valid_bva_1() {
        assertDoesNotThrow(() -> tasksService.addTask(task));
    }

    @Test
    public void testSaveFails() {
        Mockito.when(task.getTitle()).thenReturn("vulgar");
        Assertions.assertThrows(RuntimeException.class, () -> tasksService.addTask(task));
    }
}
