import java.util.Iterator;

public class ArrayQueueIterator implements Iterator {
    ArrayQueue arrayQueue;
    int index;

    public ArrayQueueIterator (int index, ArrayQueue arrayQueue){
        this.index=index;
        this.arrayQueue =arrayQueue;
    }

    @Override
    public boolean hasNext() {
        return (index != arrayQueue.getTailIndex());
    }
    @Override
    public Cloneable next(){
        return arrayQueue.getElement(index+1);
    }
}
