import org.apache.commons.collections4.queue.CircularFifoQueue;

import java.time.Duration;
import java.time.LocalDateTime;

public class QueueHandler {

    private final CircularFifoQueue<QueueItem> queue;
    private final int maxTimeMillis;

    public QueueHandler(int maxSize, int maxTimeMillis) {
        this.queue = new CircularFifoQueue<>(maxSize);
        this.maxTimeMillis = maxTimeMillis;
    }

    public void addItem(QueueItem queueItem) {

        if (queue.size() == queue.maxSize()) {
            long diffMillis = Math.abs(Duration.between(LocalDateTime.now(), queue.get(0).getDateTime()).toMillis());
            if (diffMillis <= maxTimeMillis) {
                System.out.printf("Queue is full, but not removing the oldest item as the different of %s ms is still within the time limit of %s ms. Rejecting item: %s\n", diffMillis, maxTimeMillis, queueItem.getItem());
                return;
            } else {
                System.out.printf("Queue is full and the oldest item will be removed as the difference of %s ms is beyond the time limit of %s ms \n", diffMillis, maxTimeMillis);
            }
        }

        queue.add(queueItem);

        for (QueueItem qi : queue) {
            System.out.print(qi.getItem() + "-" + qi.getDateTime() + ",");
        }

        System.out.println("\n");

    }

    public CircularFifoQueue<QueueItem> getQueue() {
        return queue;
    }

    public static void main(String[] args) {
        QueueHandler queueHandler = new QueueHandler(50, 200);

        for (int i = 0; i < 300; i++) {
            queueHandler.addItem(new QueueItem(i, LocalDateTime.now()));
            try {
                Thread.sleep(1); // Simulate a delay between adding items
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

}
