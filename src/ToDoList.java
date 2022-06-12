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
            addTask(task);
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
        String finalString = "[";
        for(Task node:this.taskList){
            finalString += ", " + node.toString();
        }
        return finalString + "]";
    }

    /**
     * checking if the new task already exists' if not adding it to the ToDoList by order
     * sorted first by Date and then by Description
     * @param newTask - task to add to the ToDoList
     * @throws TaskAlreadyExistsException
     */
    public void addTask(Task newTask) {
        for(Task node:this.taskList){
            if(newTask.equals(node)){
                throw new TaskAlreadyExistsException();
            }
            else{
                int index = 0; //run with an index to save the place where we want to add the Task
                for (Task current : this.taskList) {
                    //task.date <= current.date
                    if (newTask.getDueDate().compareTo(current.getDueDate()) <= 0){
                        //task.description <= current.description
                        if (newTask.getDescription().compareTo(current.getDescription()) <= 0) {
                            this.taskList.add(index, newTask);
                            break; //added the Task, can move on to the next one
                        }
                    }
                    index++;
                }
                //if reached the end of the list then add
                if(index == this.taskList.size()){this.taskList.add(index, newTask);}
            }
        }
    }

    @Override
    public void setScanningDueDate(Date dueDate){
        this.limitDueDate = dueDate;
    }

    @Override
    public Iterator iterator(){
        return new ToDoListIterator(taskList, limitDueDate);
    }

    /**
     * create the hash code using the hash codes of every task and chaining them.
     * the format is: T1T2T3...Tn where Tk is the hash code of kTH Task
     * @return unique hash code
     */
    @Override
    public int hashCode(){
        int hash = 0;
        for(Object task: this){
            hash*=10^(getNumDigits(task.hashCode()));
            hash+= task.hashCode();//adding String's hash code to consider both date and description
        }
        return hash;
    }

    /**
     * use Task's clone method to deep clone the ToDoList(clone every element)
     * @return deep clone of ToDoList
     */
    @Override
    public ToDoList clone(){
        Task[] cloned = new Task[taskList.size()]; //using array so we can use the constractor
        int index = 0;
        for(Object task: taskList){
            cloned[index] = (Task)task.clone();
            index++;
        }
        return new ToDoList(cloned);
    }

    /**
     *
     * @param other
     * @return
     */
    @Override
    public boolean equals(Object other){
        if(this == other){return true;}
        if(other instanceof ToDoList){
            if(this.hashCode() == other.hashCode()){ //using hash code since it has unique values
                return true;
            }
        }
        return false;
    }
}
