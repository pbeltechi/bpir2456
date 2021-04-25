package bpir2456;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import tasks.model.Task;

import java.util.Calendar;
import java.util.Date;

public class TaskTest {

    private static Task testTask;

    @BeforeAll
    private static void setup() {
        testTask = new Task("title1", new Date(2020, Calendar.FEBRUARY, 1), new Date(2021, Calendar.FEBRUARY, 2), 0);
    }

    @Test
    public void testGetTitle() {
        Assertions.assertEquals("title1", testTask.getTitle());
    }

    @Test
    public void testSetTitle() {
        testTask.setTitle("title");
        Assertions.assertEquals("title", testTask.getTitle());
    }
}
