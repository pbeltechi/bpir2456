import org.junit.jupiter.api.*;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TasksServiceTest {

    ArrayTaskList arrayTaskList = new ArrayTaskList();
    TasksService tasksService = new TasksService(arrayTaskList);

    @BeforeEach
    void init() {
        this.arrayTaskList = new ArrayTaskList();
        this.tasksService = new TasksService(this.arrayTaskList);
    }

    @Test
    @Order(1)
    @Tag("ECP")
    @DisplayName("Test valid ecp 1")
    void test_valid_ecp_1() {
        Task task = new Task(
                "desc1",
                new Date(2021, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                2
        );
        assertDoesNotThrow(() -> this.tasksService.addTask(task));
    }

    @Test
    @Order(2)
    @Tag("ECP")
    void test_valid_ecp_2() {
        Task task = new Task(
                "desc1",
                new Date(1990, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                6
        );
        assertDoesNotThrow(() -> this.tasksService.addTask(task));
    }

    @Test
    @Order(3)
    @Tag("ECP")
    void test_non_valid_ecp_1() {
        Task task = new Task(
                "desc1",
                new Date(2021, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                -5
        );
        assertThrows(RuntimeException.class, () -> this.tasksService.addTask(task));
    }

    @Test
    @Tag("ECP")
    void test_non_valid_ecp_2() {
        Task task = new Task(
                "desc1",
                new Date(2023, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                124
        );
        assertThrows(RuntimeException.class, () -> this.tasksService.addTask(task));
    }

    @Test
    void test_valid_bva_1() {
        Task task = new Task(
                "desc1",
                new Date(2021, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                1
        );
        assertDoesNotThrow(() -> this.tasksService.addTask(task));
    }

    @Test
    void test_valid_bva_2() {
        Task task = new Task(
                "desc1",
                new Date(2021, Calendar.MAY, 2),
                new Date(2021, Calendar.MAY, 3),
                1
        );
        assertDoesNotThrow(() -> this.tasksService.addTask(task));
    }

    @Test
    void test_non_valid_bva_1() {
        Task task = new Task(
                "desc1",
                new Date(2021, Calendar.APRIL, 2),
                new Date(2021, Calendar.MAY, 2),
                -1
        );
        assertThrows(RuntimeException.class, () -> this.tasksService.addTask(task));
    }

    @Test
    @RepeatedTest(10)
    void test_non_valid_bva_2() {
        Task task = new Task(
                "desc1",
                new Date(2021, Calendar.MAY, 2),
                new Date(2021, Calendar.MAY, 2),
                1
        );
        assertThrows(RuntimeException.class, () -> this.tasksService.addTask(task));
    }

    @Test
    @Disabled
    void ignored() {
        System.out.println("Acest test este ingnorat");
    }
}
