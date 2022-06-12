import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ToDoListIterator implements Iterator<Task>{
    LinkedList<Task> list;
    Date dueDate;
    int index = 0;

    public ToDoListIterator(LinkedList list, Date dueDate){
        this.list = list;
        this.dueDate = dueDate;
    }

    @Override
    public boolean hasNext(){
        Task current = list.get(index);
        if(dueDate == null && list.get(index) != null){ //if there is no due date we want to go through all the tasks
            return true;
        }
        return current.getDueDate().compareTo(dueDate) < 0; // if current due date is smaller than provided due date, we want it
    }

    @Override
    public Task next(){
        index++;
        return list.get(index-1);}
}
