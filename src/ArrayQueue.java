import java.util.Iterator;

public class ArrayQueue<E extends Cloneable> implements Queue{
    Cloneable[] queueArray;
    int index = 0;
    int maxCapacity;
    int head = 0;

    /**
     * @param maxCapacity - length of line
     * @throws NegativeCapacityException - if maxCapacitiy is negative
     */
    public ArrayQueue(int maxCapacity) throws NegativeCapacityException{
        if(maxCapacity < 0){
            throw new NegativeCapacityException();
        }
        this.maxCapacity = maxCapacity;
        this.queueArray = new Cloneable[maxCapacity];
    }

    //TODO

    /**
     * checks for room in the queue and if there is available room, enter the element to the end of the queue
     * @param element - element to enter into the queue
     * @throws QueueOverflowException - queue is full and we try to enter a new element
     */
    @Override
    public void enqueue(Cloneable element) throws QueueOverflowException{
        // if null then the queue is empty. if not null then the tail has reached the head
        if(this.index == this.head && this.queueArray[this.index]!=null){
            throw new QueueOverflowException();
        }
        this.queueArray[index] = element;
        this.index = (this.index+1)%this.maxCapacity;
    }

    /**
     * saving the element from head, and change the head index.
     * @return head element
     * @throws EmptyQueueException
     */
    @Override
    public E dequeue()throws EmptyQueueException{
        if(this.size() == 0){throw new EmptyQueueException();}
        E element = (E)queueArray[head];
        queueArray[head] =null;
        if (head+1==maxCapacity){head=0;} //If we got to the max capacity return head to first cell.
        else {head++;}//If there is still room in the array, promote head to next.
        return element;
    }

    @Override
    public E peek(){return (E)this.queueArray[0];}

    /**
     * calculate queue elements amount, if the index is bigger than the head- calculate the difference
     * if the index is smaller than the head- add maxCapacity and to find the difference
     * @return queue size
     */
    @Override
    public int size(){
        if(head == index) return 0;
        if(head<index){return index-head-1;} //-1 cause index shows the next free spot
        else{return (index+maxCapacity) - head - 1;} // adding maxCapacity to get the index to be infront
    }
    @Override
    public boolean isEmpty(){return true;}
    @Override
    public ArrayQueue<E> clone(){return this;}
    @Override
    public Iterator iterator() {
        return null;
    }
}
