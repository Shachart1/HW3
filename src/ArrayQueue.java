import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Iterator;

public class ArrayQueue<E extends Cloneable> implements Queue{
    Cloneable[] queueArray;
    int tail = 0;
    int head = 0;
    int queueSize = 0;


    /**
     * constructor that creates a queue with max capacity - maxCapacity
     * @param maxCapacity - length of line
     * @param elements - list of elements to enter to the queue by order
     * @throws NegativeCapacityException - if maxCapacitiy is negative
     */
    public ArrayQueue(int maxCapacity, E... elements) {// לא צריך להצהיר בגלל שזה חריגה בלתי מסומנת יעני runtime
        if(maxCapacity < 0){
            throw new NegativeCapacityException();
        }
        //this.maxCapacity = maxCapacity;
        this.queueArray = new Cloneable[maxCapacity];
        // if some elements were provided add them to the queue
        for(E element: elements){
            this.enqueue(element);
        }
    }

    public void setHead(int head){
        this.head = head;
    }

    public void setTail(int tail){
        this.tail = tail;
    }

    /**
     * Getters
     */

    protected E getElement(int index){
        return (E) queueArray[index%queueArray.length];
    }

    protected int getTailIndex(){
        return this.tail;
    }

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
     * @throws EmptyQueueException - trying to take out an element from an empty queue
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

    /**
     * gets the value of the first element in the queue without removing it
     * @return value of the first element in the queue
     * @throws EmptyQueueException - trying to peek at an empty queue
     */
    @Override
    public E peek(){
        if(queueSize == 0){
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
    }

    /**
     * checking if there are elements in the queue
     * @return True if the queue is empty, false otherwise
     */
    @Override
    public boolean isEmpty(){
        return size()==0;
    }


    /**
     * cloning each element using "invoke" and then adding it to the new cloned queue in order
     * @return deep clone of the original queue
     */
    @Override
    public ArrayQueue<E> clone(){
        // Initialize the cloned array
        ArrayQueue cloned = new ArrayQueue(this.queueArray.length);
        // Clone the elements
        for(Object element: this){
            try {
                Method cloneMethod = element.getClass().getMethod("clone");
                Cloneable temp = (Cloneable) cloneMethod.invoke(element);// upcasting in order to insert to queue
                cloned.enqueue(temp);
            }
            // Can't happen since E extends Cloneable
            catch(NoSuchMethodException e){return null;}
            // If there is no access then the element is not cloneable to us, so we can't add it to the cloned queue
            catch(IllegalAccessException e){return null;}
            // Can't be since E extends Cloneable and thus needs to be able to be cloned
            catch (InvocationTargetException e){return null;}
        }
        return cloned;
    }

    /**
     * Create iterator to go over this ArrayQueue
     * @return ArrayQueueIterator to go over this specific ArrayQueue
     */
    @Override
    public Iterator iterator() {
        return new ArrayQueueIterator(this.head, this, this.queueArray.length);
    }


    //CHANGES
    // כל החריגות שלנו הן בלתי מסומנות אז הורדתי את ההצהרות שלהן

}
