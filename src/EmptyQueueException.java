public class EmptyQueueException extends QueueException{
    public EmptyQueueException(String message){super(message);}
    public EmptyQueueException(Exception cause){
        super(cause);
    }
    public EmptyQueueException(String message, Exception cause){
        super(message, cause);
    }
    public EmptyQueueException(){
        super("Empty Queue Exception occurred");
    }

}
