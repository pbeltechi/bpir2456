import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.powermock.reflect.Whitebox;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TaskIO;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

public class TaskIOTest {

    private static TaskIO taskIO;
    private static ArrayTaskList arrayTaskList;
    private static Task task;

    @BeforeAll
    static void init() {
        taskIO = new TaskIO();
        arrayTaskList = Mockito.mock(ArrayTaskList.class);
        task = Mockito.mock(Task.class);
        Mockito.when(task.getTime()).thenReturn(new Date(2021, Calendar.MARCH, 2));
        Mockito.when(task.isRepeated()).thenReturn(false);
        Mockito.when(task.isActive()).thenReturn(true);
        Mockito.when(task.getRepeatInterval()).thenReturn(0);
        Mockito.when(arrayTaskList.size()).thenReturn(1);
        Mockito.when(arrayTaskList.getAll()).thenReturn(Collections.singletonList(task));
    }

    @Test
    public void testGetIntervalFromText() throws Exception {
        String line = "[1 day 2 hours 46 minutes 40 seconds]";
        Assertions.assertEquals(96400, (int) Whitebox.invokeMethod(taskIO, "getIntervalFromText", line));
    }

    @Test
    public void testRewriteFile() {
        Mockito.when(task.getTitle()).thenReturn("title1");
        Assertions.assertDoesNotThrow(() -> taskIO.rewriteFile(arrayTaskList));
    }

    @Test
    public void testRewriteFileFails() {
        Mockito.when(task.getTitle()).thenReturn("vulgar");
        Assertions.assertThrows(RuntimeException.class, () -> taskIO.rewriteFile(arrayTaskList));
    }
}
