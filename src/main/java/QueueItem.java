import java.time.LocalDateTime;

public class QueueItem {

    private Integer item;
    private LocalDateTime dateTime;

    public QueueItem(Integer item, LocalDateTime dateTime) {
        this.item = item;
        this.dateTime = dateTime;
    }
    public Integer getItem() {
        return item;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }

}