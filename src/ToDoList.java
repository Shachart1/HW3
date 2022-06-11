import java.util.LinkedList;

public class ToDoList {
    LinkedList<Task> taskList; //need a dynamic list since we don't have a size limit and need to be able to sort


    /**
     * adding the given tasks to the taskList in a sorted manner.
     * first sort by date and then by description.
     * @param tasks
     */
    public ToDoList(Task... tasks) {
        for (Task task : tasks) {
            int index = 0; //run with an index to save the place where we want to add the Task
            for (Task current : this.taskList) {
                //task.date <= current.date
                if (task.getDueDate().compareTo(current.getDueDate()) <= 0){
                    //task.description <= current.description
                    if (task.getDescription().compareTo(current.getDescription()) <= 0) {
                        this.taskList.add(index, task);
                        break; //added the Task, can move on to the next one
                    }
                }
                index++;
            }
        }
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
}
