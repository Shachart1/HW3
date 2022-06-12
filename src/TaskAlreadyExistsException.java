public class TaskAlreadyExistsException extends RuntimeException  {
    public TaskAlreadyExistsException(String message){super((message));}
    public TaskAlreadyExistsException(Exception cause){super((cause));}
    public TaskAlreadyExistsException(String message, Exception cause){super(message,cause);}
    public TaskAlreadyExistsException(){super("Task Already Exists");}
}
