import java.util.Iterator;

public class ArrayQueue<E extends Cloneable> implements Queue{
    Cloneable[] queueArray;
    int tail = 0;  //שיניתי שם ל index
    //int maxCapacity; במקום פשוט אורך המארך
    int head = 0;
    int queueSize = 0;

    /**
     * @param maxCapacity - length of line
     * @throws NegativeCapacityException - if maxCapacitiy is negative
     */
    public ArrayQueue(int maxCapacity) {// לא צריך להצהיר בגלל שזה חריגה בלתי מסומנת יעני runtime
        if(maxCapacity < 0){
            throw new NegativeCapacityException();
        }
        //this.maxCapacity = maxCapacity;
        this.queueArray = new Cloneable[maxCapacity];
    }

    //TODO

    /**
     * checks for room in the queue and if there is available room, enter the element to the end of the queue
     * @param element - element to enter into the queue
     * @throws QueueOverflowException - queue is full and we try to enter a new element
     */
    @Override
    public void enqueue(Cloneable element){
        // if null then the queue is empty. if not null then the tail has reached the head

        if (this.queueSize == queueArray.length){  //במקום אינדקס ולהסתמך על זה שזה לא null
            throw new QueueOverflowException();
        }
//        if(this.tail == this.head && this.queueArray[this.tail]!=null){}
        this.queueArray[tail] = element;
        this.tail = (this.tail +1)%this.queueArray.length;
        this.queueSize++;
    }

    /**
     * saving the element from head, and change the head index.
     * @return head element
     * @throws EmptyQueueException
     */
    @Override
    public E dequeue(){
        if(size() == 0){throw new EmptyQueueException();}
        E element = (E)queueArray[head];
        //queueArray[head] =null;    אין צורך כי אנחנו עובדים עם queueSize
        this.head = (this.head +1)%this.queueArray.length;
        queueSize--;
        //if (head+1==queueArray.length){head=0;} //If we got to the max capacity return head to first cell.
       // else {head++;}//If there is still room in the array, promote head to next.
        return element;
    }

    @Override
    public E peek(){
        if(queueSize ==0 ){
            throw new EmptyQueueException();
        }
        return (E)this.queueArray[head];}

    /**
     * calculate queue elements amount, if the index is bigger than the head- calculate the difference
     * if the index is smaller than the head- add maxCapacity and to find the difference
     * @return queue size
     */
    @Override
    public int size(){
        return this.queueSize;
        //לא צריךךךך
        // if(head == tail) return 0;
       // if(head< tail){return tail -head-1;} //-1 cause index shows the next free spot
        //else{return (tail +maxCapacity) - head - 1;} // adding maxCapacity to get the index to be infront
    }
    @Override
    public boolean isEmpty(){
        return size()==0;
    }

    //TODO
    @Override
    public ArrayQueue<E> clone(){return this;}
    @Override
    public Iterator iterator() {
        return null;
    }

    //CHANGES
    // כל החריכות שלנו הן בלתי מסומנות אז הורדתי את ההצהרות שלהן
    public static class Main {
        public static void main(String[] args) {
            MyCloneable mycloneable = new MyCloneable(1);
            ArrayQueue testqueuearr = new ArrayQueue<MyCloneable>(4);
            testqueuearr.enqueue(mycloneable);

        }
    }


}
