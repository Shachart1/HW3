import java.util.Iterator;

public class ArrayQueueIterator implements Iterator {
    ArrayQueue arrayQueue;
    int index;
    int lengthOfArray;
    boolean firstTimeFlag = true;

    public ArrayQueueIterator (int index, ArrayQueue arrayQueue,int lengthOfArray){
        this.index=index;
        this.arrayQueue = arrayQueue;
        this.lengthOfArray = lengthOfArray;
    }

    /**
     * checking if index is not on tail
     * @return true if there is next
     */
    @Override
    public boolean hasNext() {
        if(this.arrayQueue.isEmpty()){return false;}
        if(this.firstTimeFlag){this.firstTimeFlag=false; return true;} // flag to include the head
        return (index%lengthOfArray != arrayQueue.getTailIndex());  //if reached beyond tail, use % to stay in bounds.
    }

    /**
     * getting the next index element
     * @return next element
     */
    @Override
    public Cloneable next(){
        Cloneable element = arrayQueue.getElement(index);
        index++;
        return element;
    }
}
