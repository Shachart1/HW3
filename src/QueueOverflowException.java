public class QueueOverflowException extends QueueException{
    public QueueOverflowException(String message){
        super(message);
    }
    public QueueOverflowException(Exception cause){
        super(cause);
    }
    public QueueOverflowException(String message, Exception cause){
        super(message, cause);
    }
    public QueueOverflowException(){
        super("Queue Overflow! reached the max capacity");
    }
}
