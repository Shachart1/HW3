public class QueueException extends RuntimeException {
    public QueueException(String message){super(message);}
    public QueueException(Exception cause){
        super(cause);
    }
    public QueueException(String message, Exception cause){
        super(message, cause);
    }
    public QueueException(){
        super("Queue Exception occurred");
    }
}
