import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

public class Task {
    final String description;
    Date dueDate;

    public Task(String description, Date dueDate){
        this.description = description;
        this.dueDate = dueDate;
    }

    /**
     * Getters
     */
    public Date getDueDate(){
        return this.dueDate;
    }
    public String getDescription(){
        return this.description;
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
     * @return String describing the Task in the format: "(description, date)"
     */
    @Override
    public String toString(){
        return "(" + this.description + ", " +
                this.dueDate.getDay() + "." + this.dueDate.getMonth() + "." + this.dueDate.getYear() + ")";
    }

    /**
     * check that other is a Task. if it is then determine equality.
     * using the unique hashCode to determine equality
     * @param other
     * @return true if the date and description values are equals between "this" and other. false otherwise
     */
    @Override
    public boolean equals(Object other){
        if(other == this){return true;}
        if(other instanceof Task){
            if(this.hashCode() == other.hashCode()){return true;} //each set of values will have a unique hash value
        }
        return false;
    }

    /**
     * using String's and Date's hashCode methods to determine the hashCode of a given task
     * the format will be: dateHash0stringHash
     * @return hash code combined from the date's hash value and the description's hash value
     */
    @Override
    public int hashCode(){
        int hash = 0;
        hash += this.dueDate.hashCode(); //using Date's hash code
        int descriptionHash = this.description.hashCode();
        hash *= 10^(getNumDigits(descriptionHash));
        hash +=  descriptionHash;//adding String's hash code to consider both date and description
        return hash;
    }

    /**
     * using the clone methods of String and Date classes to deep copy Task
     * @param task that needs to be cloned
     * @return cloned Task
     */
    public Task clone(Task task){
        try{
            Method descCloneMethod = String.class.getMethod("clone");
            String newDescription = (String) descCloneMethod.invoke(task.description);
            Method dateCloneMethod = Date.class.getMethod("clone");
            Date newDueDate =(Date) dateCloneMethod.invoke(task.dueDate);
            Task cloned = new Task(newDescription, newDueDate);
            return cloned;
        }
        catch(NoSuchMethodException e){return null;}
        catch(IllegalAccessException e){return null;}
        catch (InvocationTargetException e){return null;}
        }


    }
}
