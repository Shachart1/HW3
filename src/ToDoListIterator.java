import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ToDoListIterator implements Iterator<Task> {
    LinkedList<Task> list;
    Date dueDate;
    Task current;

    public ToDoListIterator(LinkedList list, Date dueDate) {
        this.list = (LinkedList<Task>) list.clone();
        this.dueDate = dueDate;
        if (this.list.isEmpty()) {
            this.current = null;
        }
        else{this.current = this.list.getFirst();} //initiating current only after initiating the list
    }

    /**
     * checking for a task with smaller dueDate or equal dueDate and smaller description
     * @return true if there is next
     */
    @Override
    public boolean hasNext() {
        if (this.list.isEmpty()) {
            return false;
        }
        for (Task task : this.list) {
            if (this.dueDate == null || task.dueDate.compareTo(this.dueDate) <= 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * looking for the smallest next task
     * @return next task
     */
    @Override
    public Task next() {
        Task min = this.list.getFirst();
        for (Task task : this.list) {
            if(task.getDueDate().compareTo(min.getDueDate()) < 0){ //sort by date
                if(this.dueDate == null || task.dueDate.compareTo(this.dueDate) <= 0) { //scan due date condition.
                    min = task;
                }
                continue;
            }
            if (task.getDueDate().compareTo(min.getDueDate()) == 0){
                if(task.getDescription().compareTo(min.getDescription()) < 0){ // if due date is equal, sort by description
                    if(this.dueDate == null || task.dueDate.compareTo(this.dueDate) <= 0) {
                        min = task;
                    }
                }
            }
        }
        this.list.remove(min);
        this.current = min;
        return min;
    }
}
