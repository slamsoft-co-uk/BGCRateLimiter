import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class QueueHandlerTest {

    @Test
    public void testQueueHandler() {
        QueueHandler queueHandler = new QueueHandler(3, 30);
        queueHandler.addItem(1);
        queueHandler.addItem(2);
        queueHandler.addItem(3);
        queueHandler.addItem(4);
        assertEquals(3, queueHandler.getQueue().size());
        assertEquals(Integer.valueOf(1), queueHandler.getQueue().get(0).getItem());
        assertEquals(Integer.valueOf(2), queueHandler.getQueue().get(1).getItem());
        assertEquals(Integer.valueOf(3), queueHandler.getQueue().get(2).getItem());
    }

}
