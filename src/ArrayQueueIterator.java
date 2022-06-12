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

    @Override
    public boolean hasNext() {
        if(this.arrayQueue.isEmpty()){return false;}
        if(this.firstTimeFlag){this.firstTimeFlag=false; return true;}
        return (index%lengthOfArray != arrayQueue.getTailIndex());
    }
    @Override
    public Cloneable next(){
        Cloneable element = arrayQueue.getElement(index);
        index++;
        return element;
    }
}
