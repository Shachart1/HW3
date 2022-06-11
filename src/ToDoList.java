import java.util.LinkedList;

public class ToDoList {
    LinkedList<Task> taskList; //need a dynamic list since we don't have a size limit and need to be able to sort


    @Override
    public String toString(){
        String finalString = "[";
        for(Task node:this.taskList){
            finalString += ", " + node.toString();
        }
        return finalString + "]";
    }
}
