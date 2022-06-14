import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

public class ToDoList implements TaskIterable{
    LinkedList<Task> taskList; //need a dynamic list since we don't have a size limit and need to be able to sort
    Date limitDueDate = null;

    /**
     * for inside usage in case of clone
     * @param limitDueDate - dueDate limit for the Iterator
     * @param tasks - array of tasks to be entered to the toDoList
     */
    private ToDoList(Date limitDueDate, Task...tasks){
        this.taskList = new LinkedList<Task>();
        if(tasks.length==0){return;}
        for (Task task : tasks) {
            if(task != null){addTask(task);} //tasks is array so will go over all elemenets even if null
        }
        this.limitDueDate = limitDueDate;
    }

    /**
     * adding the given tasks to the taskList using the addTask method
     * @param tasks - array of tasks to be entered to the toDoList
     */
    public ToDoList(Task... tasks) {
        this(null, tasks);
    }

    public ToDoList(){this(null, new Task[0]);}

    /**
     * @param num
     * @return number of digit in num
     */
    private int getNumDigits(int num){
        int count = 0;
        while(num != 0){
            num = num/10;
            count++;
        }
        return count;
    }

    /**
     * using Task's toString method and formatting to create the needed String
     * @return String describing the ToDoList in the format: "[Task, Task,..., Task]"
     */
    @Override
    public String toString(){
        int firstPrint = 1;
        String finalString = "[";
        for(Task node:this.taskList){
            if(firstPrint == 0) {
                finalString += ", (" + node.toString() +")";
            }
            else{
                firstPrint = 0;
                finalString += "(" + node.toString() + ")";
            }
        }
        return finalString + "]";
    }

    /**
     * checking if the new task already exists' if not adding it to the ToDoList by order
     * sorted first by Date and then by Description
     * @param newTask - task to add to the ToDoList
     * @throws TaskAlreadyExistsException - trying to add a task that already exists
     */
    public void addTask(Task newTask) {
        for (Task node : this.taskList) {
            if (newTask.getDescription().compareTo(node.getDescription()) == 0) {
                throw new TaskAlreadyExistsException();
            }
        }
            this.taskList.add(newTask);
    }

    @Override
    public void setScanningDueDate(Date dueDate){
        this.limitDueDate = dueDate;
    }

    @Override
    public Iterator<Task> iterator(){
        Iterator<Task> Itr = new ToDoListIterator(taskList, limitDueDate);
        return Itr;
    }

    /**
     * create the hash code using the hash codes of every task and sum them
     * @return unique hash code
     */
    @Override
    public int hashCode(){
        int hash = 0;
        Date tempScan = this.limitDueDate; // we want to go over all the list. we need the limit due date to be null
        setScanningDueDate(null);
        for(Object task: this){ // go over the elements in a sorted manner
            hash+= task.hashCode();//adding String's hash code to consider both date and description
        }
        setScanningDueDate(tempScan); // move limit due date back to what it was
        return hash;
    }

    /**
     * use Task's clone method to deep clone the ToDoList(clone every element)
     * @return deep clone of ToDoList
     */
    @Override
    public ToDoList clone(){


        Task[] cloned = new Task[taskList.size()]; //using array so we can use the constructor
        int index = 0;
        for(Task task: taskList){
            cloned[index] = task.clone();
            index++;
        }
        return new ToDoList(cloned);
    }

    /**
     * determine equality by hash code since it is unique.
     * @param other - list to compare
     * @return true if equals and false if not
     */
    @Override
    public boolean equals(Object other){
        if(this == other){return true;}
        if(other == null){return false;}
        if(other instanceof ToDoList){
            if(this.hashCode() == other.hashCode()){ //using hash code since it has unique values
                return true;
            }
        }
        return false;
    }
}
