import java.io.PrintWriter;
import java.lang.String;
import java.util.Arrays;


/**
 * Implementation of the Runqueue interface using an Ordered Array.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class OrderedArrayRQ implements Runqueue {

    /**
     * Constructs empty queue
     */
	
	private Item[] ArrayItems;
	private int capacity;
	private int storeItems;
	

    public OrderedArrayRQ() {
    	capacity = 20;
    	ArrayItems = new Item[capacity];
    	storeItems = 0;
//    	if (size >= data.length) {
//    		E[] newData = (E[])(new Object[size * 2 + 1]); 
    	//System.arraycopy(data, 0, newData, 0, size); 
    	//data = newData;
//    		}
    	
        // Implement Me

    }  // end of OrderedArrayRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
    	Item addItem = new Item();
    	addItem.procLabel = procLabel;
    	addItem.vt = vt;
    	
    	//Double if the array is full
    	if(storeItems == capacity) {
    		capacity = capacity * 2;
    		ArrayItems = Arrays.copyOf(ArrayItems, capacity);   		
    	}
    	if(storeItems == 0) {
    		ArrayItems[0] = addItem;
    	}
    	else {
    		int i = storeItems;
    		for(; (i>=1) && (ArrayItems[i-1].procLabel.compareTo(addItem.procLabel) < 0); i--) 
    			ArrayItems[i] = ArrayItems[i-1];
    		
    		ArrayItems[i] = addItem;
    	}
    	storeItems++;
    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me

        return ""; // placeholder,modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel){
        // Implement me

        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me

        return false; // placeholder, modify this
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    }// end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me

    } // end of printAllProcesses()

} // end of class OrderedArrayRQ
