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


class ListNode {
    ListNode link = null;
    Item data = new Item();

    public ListNode() {
        link = null;
        data.procLabel = " ";
        data.vt = 0;
    }

    public ListNode(Item data, ListNode link) {
        this.data = data;
        this.link = null;
    }

    public ListNode getLink() {
        return link;
    }

    public void setLink(ListNode link) {
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
	

	ListNode LinkedList = null;
	ListNode end = null;
	int caoacity = 0;	
	
    public OrderedLinkedListRQ() {
        // Implement me
    	LinkedList = null;
    	end = null;
    	caoacity = 0;
    }  // end of OrderedLinkedList()


    public int findItemPosition(Item item) {
    	if(LinkedList == null)
    		return 0;
    	if(LinkedList.getData().vt >= item.vt)
    		return 0;
        if(LinkedList.getLink() != null && end.getData().vt >= item.vt)
    		return caoacity;
        
    	ListNode findItemStart = LinkedList;
    	int pos = 1;
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
        ListNode nptr = new ListNode(item, null);
        if (LinkedList == null) {
            LinkedList = nptr;
            end = LinkedList;
        } else {
            nptr.setLink(LinkedList);
            LinkedList = nptr;
        }
        caoacity++;
    }

    public void addTail(Item item) {
        ListNode nptr = new ListNode(item, null);
        if (LinkedList == null) {
            LinkedList = nptr;
            end = nptr;
        } else {
            end.setLink(nptr);
            end = nptr;
        }
        caoacity++;
    }
    
    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
    	Item addItem = new Item();
    	addItem.procLabel = procLabel;
    	addItem.vt = vt;    	
    	if(LinkedList == null)
    		addHead(addItem);
    	else {
    		int itemPosition = findItemPosition(addItem);
    		if(itemPosition == -1)
    			itemPosition = caoacity;
    		if(itemPosition == 0)
    			addHead(addItem);
    		else {
    			ListNode nptr = new ListNode(addItem, null);
    	        ListNode ptr = LinkedList;
    	        for (int i = 1; i <= caoacity; i++) {
    	            if (i == itemPosition) {
    	                ListNode temp = ptr.getLink();
    	                ptr.setLink(nptr);
    	                nptr.setLink(temp);
    	                break;
    	            }
    	            ptr = ptr.getLink();
    	        }
    	        caoacity++;
    		}
    	}
    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
    	if (LinkedList == null) {
            return "";
        }
    	
    	ListNode linkListStart = LinkedList;
    	ListNode temp = linkListStart.getLink();
    	LinkedList = temp;
        caoacity--;

        return  linkListStart.getData().procLabel; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        // Implement me
    	ListNode processsItem = LinkedList;
    	if(processsItem.getData().procLabel.compareToIgnoreCase(procLabel) == 0)
    		return true;
    	
    	processsItem = LinkedList.getLink();
        while (processsItem != null && processsItem.getLink() != null) {
        	if(processsItem.getData().procLabel.compareToIgnoreCase(procLabel) == 0) 
        		return true;
        	processsItem = processsItem.getLink();
        }
        return false; // placeholder, modify this
    } // end of findProcess()


    @Override
    public boolean removeProcess(String procLabel) {
        // Implement me
    	if (LinkedList.getData().procLabel.compareToIgnoreCase(procLabel) == 0 && LinkedList.getLink() == null) {
            LinkedList = null;
            end = null;
            caoacity--;
            return true;
        }

        if (LinkedList.getData().procLabel.compareToIgnoreCase(procLabel) == 0 && LinkedList.getLink() != null) {
            LinkedList = LinkedList.getLink();
            caoacity--;
            return true;
        }

//        if (end.getData().procLabel.compareTo(procLabel) == 0) {
//            ListNode linkListStart = LinkedList;
//            ListNode linkListend = LinkedList;
//
//            linkListStart = linkListStart.getLink();
//            while (startPtr.getLink() != null) {
//                linkListend = linkListStart;
//                linkListStart = linkListStart.getLink();
//            }
//            end = linkListend;
//            end.setLink(null);
//            caoacity--;
//            return true;
//        }

        ListNode startPtr = LinkedList;
        ListNode prevLink = startPtr;
        startPtr = startPtr.getLink();
        while (startPtr != null && startPtr.getData().procLabel.compareToIgnoreCase(procLabel) != 0  && startPtr.getLink() != null) {
            prevLink = startPtr;
            startPtr = startPtr.getLink();
        }
        if (startPtr != null && startPtr.getData().procLabel.compareToIgnoreCase(procLabel) == 0) {
            ListNode temp = prevLink.getLink();
            temp = temp.getLink();
            prevLink.setLink(temp);
            caoacity--;
            return true;
        }
        
        return false; // placeholder, modify this
    } // End of removeProcess()


    @Override
    public int precedingProcessTime(String procLabel) {
        // Implement me
		int sum = 0;
		ListNode processsItem = LinkedList;
		if(processsItem.getData().procLabel.compareToIgnoreCase(procLabel) == 0)
			return processsItem.getData().vt;
		else
			sum = processsItem.getData().vt;
		
		processsItem = LinkedList.getLink();
	    while (processsItem != null && processsItem.getLink() != null) {
	    	if(processsItem.getData().procLabel.compareToIgnoreCase(procLabel) == 0) 
	    		return sum;
	    	sum = sum + processsItem.getData().vt;
	    	processsItem = processsItem.getLink();
	    }    	
        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me
    	int sum = 0;
		ListNode processsItem = LinkedList;
		if(processsItem.getData().procLabel.compareToIgnoreCase(procLabel) == 0)
			return processsItem.getData().vt;
		else
			sum = processsItem.getData().vt;
		
		processsItem = LinkedList.getLink();
		boolean find = false;
	    while (processsItem != null && processsItem.getLink() != null) {
	    	if(processsItem.getData().procLabel.compareToIgnoreCase(procLabel) == 0) 
	    		find = true;
	    	if(find)
	    		sum = sum + processsItem.getData().vt;
	    	processsItem = processsItem.getLink();
	    } 
	    if(find)
	    	return sum;
        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        //Implement me
    	if (LinkedList == null) {
            return;
        }

        if (LinkedList.getLink() == null) {
            System.out.println(LinkedList.getData().procLabel);
            return;
        }

        ListNode printItem = LinkedList;
        System.out.print(printItem.getData().procLabel + "  ");
        printItem = LinkedList.getLink();
        while (printItem.getLink() != null) {
            System.out.print(printItem.getData().procLabel + "  ");
            printItem = printItem.getLink();
        }
        System.out.println(printItem.getData().procLabel + "\n");
    } // end of printAllProcess()

} // end of class OrderedLinkedListRQ
