import java.util.Date;

public class Task {
    final String description;
    Date dueDate;

    public Task(String description, Date dueDate){
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public String toString(){
        return "(" + this.description + "," +
                this.dueDate.getDay() + "." + this.dueDate.getMonth() + "." + this.dueDate.getYear() + ")";
    }

    @Override
    public boolean equals(Object other){
        if(other == this){return true;}
        if(other instanceof Task){
            if(this.toString() == other.toString()){return true;}
        }
        return false;
    }

}
