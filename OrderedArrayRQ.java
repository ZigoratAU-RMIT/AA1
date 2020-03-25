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
    		for(; (i>=1) && (ArrayItems[i-1].vt > addItem.vt); i--) 
    			ArrayItems[i] = ArrayItems[i-1];
    		
    		ArrayItems[i] = addItem;
    	}
    	storeItems++;
    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
    	if(storeItems == 0)
    		return "";
    	else {    		
        	Item deleteItem = ArrayItems[0];
        	int i = 0;
        	for(; i < storeItems - 1  ; i++ )
        		ArrayItems[i] = ArrayItems[i+1];
        	
        	ArrayItems[storeItems - 1] = null;
        	
        	storeItems--;

            return deleteItem.procLabel; // placeholder,modify this    	
       }
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel){
        // Implement me
    	if(storeItems == 0)
    		return false;
    	else {
        	int i = 0;
        	for(; i < storeItems ; i++ ) 
        		if(ArrayItems[i].procLabel.compareTo(procLabel) == 0) {
        			return true;
        	}    		
    	}
        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
    	if(storeItems == 0)
    		return false;
    	else {
        	int i = 0;
        	for(; i < storeItems ; i++ ) 
        		if(ArrayItems[i].procLabel.compareTo(procLabel) == 0) {
        			int j = i;
        			ArrayItems[j] = null;
                	for(; j < storeItems - 1  ; j++ )
                		ArrayItems[j] = ArrayItems[j+1];
                	storeItems--;
        			return true;
        	}    		
    	}

        return false; // placeholder, modify this
    } // end of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {    	
        // Implement me
    	int sum = 0;
    	int i = 0;
    		for(; i < storeItems  ; i++ ) {
    			if(ArrayItems[i].procLabel.compareTo(procLabel) == 0) {
    				return sum;
    			}
    			else {
    				sum = sum + ArrayItems[i].vt;
    			}
    		}
        return -1; // placeholder, modify this
    }// end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me
    	int sum = 0;
    	int i = 0;
    		for(; i < storeItems  ; i++ ) {
    			if(ArrayItems[i].procLabel.compareTo(procLabel) == 0) {
    				int j = i;
    				j++;
                	for(; j < storeItems  ; j++ )
                		sum = sum + ArrayItems[j].vt;
                	return sum;
    			}
    		}
        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
    	int i = 0;
    	for(; i < storeItems  ; i++ )
    		System.out.print(ArrayItems[i].procLabel + "  ");
    	System.out.println("");
        //Implement me

    } // end of printAllProcesses()

} // end of class OrderedArrayRQ
