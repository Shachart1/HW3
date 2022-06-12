public class NegativeCapacityException extends QueueException{
    public NegativeCapacityException(String message){super(message);}
    public NegativeCapacityException(Exception cause){
        super(cause);
    }
    public NegativeCapacityException(String message, Exception cause){
        super(message, cause);
    }
    public NegativeCapacityException(){
        super("Negative Capacity! Please enter a positive number for the max capacity");
    }
}
