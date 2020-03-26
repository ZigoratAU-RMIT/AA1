import java.io.PrintWriter;
import java.lang.String;

/**
 * Implementation of the run queue interface using an Ordered Link List.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan.
 */


class Node {
    Node link = null;
    Item data = new Item();

    public Node() {
        link = null;
        data.procLabel = " ";
        data.vt = 0;
    }

    public Node(Item data, Node link) {
        this.data = data;
        this.link = null;
    }

    public Node getLink() {
        return link;
    }

    public void setLink(Node link) {
        this.link = link;
    }

    public Item getData() {
        return data;
    }

    public void setData(Item data) {
        this.data = data;
    }

}


public class OrderedLinkedListRQ implements Runqueue {

    /**
     * Constructs empty linked list
     */
	

	Node start = null;
	Node end = null;
	int size = 0;	
	
    public OrderedLinkedListRQ() {
        // Implement me
    	start = null;
    	end = null;
    	size = 0;
    }  // end of OrderedLinkedList()


    public int findItemPosition(Item item) {
    	if(start == null)
    		return 0;
    	if(start.getData().vt >= item.vt)
    		return 0;
        if(start.getLink() != null && end.getData().vt >= item.vt)
    		return size;
        
    	Node findItemStart = start;
    	int pos = 0;
    	while(findItemStart.getLink() != null) {
    		++pos;
    		if(findItemStart.getData().vt >= item.vt) 	    			
    			break;
    		findItemStart = findItemStart.getLink();
    	}
    	if(findItemStart.getData().vt >= item.vt)
    		return pos;
    	return -1;
    }
    
    public void addHead(Item item) {
        Node nptr = new Node(item, null);
        if (start == null) {
            start = nptr;
            end = start;
        } else {
            nptr.setLink(start);
            start = nptr;
        }
        size++;
    }

    public void addTail(Item item) {
        Node nptr = new Node(item, null);
        if (start == null) {
            start = nptr;
            end = nptr;
        } else {
            end.setLink(nptr);
            end = nptr;
        }
        size++;
    }
    
    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
    	Item addItem = new Item();
    	addItem.procLabel = procLabel;
    	addItem.vt = vt;    	
    	if(start == null)
    		addHead(addItem);
    	else {
    		int itemPosition = findItemPosition(addItem);
    		if(itemPosition == -1)
    			itemPosition = size;
    		if(itemPosition == 0)
    			addHead(addItem);
    		else {
    			Node nptr = new Node(addItem, null);
    	        Node ptr = start;
    	        for (int i = 1; i <= size; i++) {
    	            if (i == itemPosition) {
    	                Node temp = ptr.getLink();
    	                ptr.setLink(nptr);
    	                nptr.setLink(temp);
    	                break;
    	            }
    	            ptr = ptr.getLink();
    	        }
    	        size++;
    		}
    	}
    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
    	if (start == null) {
            return "";
        }
    	
    	Node startPtr = start;
    	Node temp = startPtr.getLink();
    	start = temp;
        size--;

        return  startPtr.getData().procLabel; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        // Implement me
    	Node processsItem = start;
    	if(processsItem.getData().procLabel.compareTo(procLabel) == 0)
    		return true;
    	
    	processsItem = start.getLink();
        while (processsItem != null && processsItem.getLink() != null) {
        	if(processsItem.getData().procLabel.compareTo(procLabel) == 0) 
        		return true;
        	processsItem = processsItem.getLink();
        }
        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
    	if (start.getData().procLabel.compareTo(procLabel) == 0 && start.getLink() == null) {
            start = null;
            end = null;
            size--;
            return true;
        }

        if (start.getData().procLabel.compareTo(procLabel) == 0 && start.getLink() != null) {
            start = start.getLink();
            size--;
            return true;
        }

//        if (end.getData().procLabel.compareTo(procLabel) == 0) {
//            Node startPtr = start;
//            Node endPtr = start;
//
//            startPtr = startPtr.getLink();
//            while (startPtr.getLink() != null) {
//                endPtr = startPtr;
//                startPtr = startPtr.getLink();
//            }
//            end = endPtr;
//            end.setLink(null);
//            size--;
//            return true;
//        }

        Node startPtr = start;
        Node prevLink = startPtr;
        startPtr = startPtr.getLink();
        while (startPtr != null && startPtr.getData().procLabel.compareTo(procLabel) != 0  && startPtr.getLink() != null) {
            prevLink = startPtr;
            startPtr = startPtr.getLink();
        }
        if (startPtr != null && startPtr.getData().procLabel.compareTo(procLabel) == 0) {
            Node temp = prevLink.getLink();
            temp = temp.getLink();
            prevLink.setLink(temp);
            size--;
            return true;
        }
        
        return false; // placeholder, modify this
    } // End of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me
    	if (start == null) {
            return;
        }

        if (start.getLink() == null) {
            System.out.println(start.getData().procLabel);
            return;
        }

        Node printItem = start;
        System.out.print(printItem.getData().procLabel + "  ");
        printItem = start.getLink();
        while (printItem.getLink() != null) {
            System.out.print(printItem.getData().procLabel + "  ");
            printItem = printItem.getLink();
        }
        System.out.println(printItem.getData().procLabel + "\n");
    } // end of printAllProcess()

} // end of class OrderedLinkedListRQ
