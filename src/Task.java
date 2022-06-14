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
     * Getters and Setters
     */
    public Date getDueDate(){
        return this.dueDate;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDueDate(Date dueDate){
        this.dueDate = dueDate;
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
        if(this.dueDate.getMonth()+1 < 10){
            return  this.description + ", " +
                    this.dueDate.getDate() + ".0" + (this.dueDate.getMonth() + 1) + "." + (this.dueDate.getYear()+1900);
        }
        return  this.description + ", " +
                this.dueDate.getDate() + "." + (this.dueDate.getMonth() + 1) + "." + (this.dueDate.getYear()+1900);
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
     * @return hash code is sum of the date's hash value and the description's hash value
     */
    @Override
    public int hashCode(){
        int hash = 0;
        hash += this.dueDate.hashCode(); //using Date's hash code
        int descriptionHash = this.description.hashCode();
        hash +=  descriptionHash;//adding String's hash code to consider both date and description
        return hash;
    }

    /**
     * using the clone methods of Date class and creating a copy of String manually to deep copy Task
     * @return cloned Task
     */
    public Task clone(){
        try{
            String newDescription = new String(this.description);
            Method dateCloneMethod = Date.class.getMethod("clone");
            Date newDueDate =(Date) dateCloneMethod.invoke(this.dueDate);
            Task cloned = new Task(newDescription, newDueDate);
            return cloned;
        }

        catch(NoSuchMethodException e){return null;}
        catch(IllegalAccessException e){return null;}
        catch (InvocationTargetException e){return null;}
        }
}
