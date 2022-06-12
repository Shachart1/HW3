import java.util.LinkedList;

public class ToDoList {
    LinkedList<Task> taskList; //need a dynamic list since we don't have a size limit and need to be able to sort


    /**
     * adding the given tasks to the taskList in a sorted manner.
     * first sort by date and then by description.
     * @param tasks
     */
    public ToDoList(Task... tasks) {
        this.taskList = new LinkedList<Task>();
        if(tasks.length==0){return;}
        for (Task task : tasks) {
            addTask(task);
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

    /**
     * checking if the new task already exists' if not adding it to the ToDoList
     * @param newTask
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
                if(index == this.taskList.size()){this.taskList.add(index, newTask);} //if reached the end of the list then add
            }
        }
    }
}
