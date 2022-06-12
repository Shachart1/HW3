import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ToDoListIterator implements Iterator{
    LinkedList<Task> list;
    Date dueDate;

    public ToDoListIterator(LinkedList list, Date dueDate){
        this.list = list;
        this.dueDate = dueDate;
    }

    @Override
    public boolean hasNext(){return false;}
    @Override
    public Task next(){}
}
