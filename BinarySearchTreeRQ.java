import java.io.PrintWriter;
import java.lang.String;
import java.util.Arrays;

/**
 * Implementation of the Runqueue interface using a Binary Search Tree.
 *
 * Your task is to complete the implementation of this class.
 * You may add methods and attributes, but ensure your modified class compiles and runs.
 *
 * @author Sajal Halder, Minyi Li, Jeffrey Chan
 */
public class BinarySearchTreeRQ implements Runqueue {

    /**
     * Constructs empty queue
     */
	
	class TreeNode {

		private TreeNode left;
		private TreeNode right;
		private Item data;
		int count;
		String[] Labels;

		public TreeNode(Item value) {
			left = null;
			right = null;
			count = 1;
			Labels = new String[count];
			Labels[0] = value.procLabel;
			data = value;
		}

		public void IncreaseArray() {
			Labels = Arrays.copyOf(Labels, count);	
		}
		
		public void DecreaseArray() {
			Labels = Arrays.copyOfRange(Labels, 1, Labels.length);	
		}
		
		public TreeNode getLeft() {
			return left;
		}
		public void setLeft(TreeNode value) {
			left = value;
		}
		public TreeNode getRight() {
			return right;
		}
		public void setRight(TreeNode value) {
			right = value;
		}

		public Item getData() {
			return data;
		}

		public void setData(Item value) {
			data = value;
		}		
		
		public int getCount() {
			return count;
		}

		public void setCount(int value) {
			count = value;
		}

		public void increaseCount() {
			count++;
		}
		
		public void decreaseCount() {
			count--;
			if(count < 0)
				count = 0;
			Labels = Arrays.copyOfRange(Labels, 1, Labels.length);
		}

		public String[] getLabels() {
			return Labels;
		}
		public String getLabel() {
			return Labels[0];
		}

		public void setLabels(String value) {
			Labels = Arrays.copyOf(Labels, count);
			Labels[count - 1] = value;
		}}
	
	class BinarySearchTree {

		private TreeNode root;

		public boolean isEmpty() {

			return (root == null);
		}

		public TreeNode getRoot() {
			return root;
		}

		public void insert(Item data) 
		{
			System.out.print("[input: "+data.procLabel+"]");
			if(root == null) {
				root = new TreeNode(data);
				System.out.println(" -> inserted: "+data.procLabel+ " c=> " +root.getCount());
				return;
			}

			insertNode(root, data);
			System.out.print(" -> inserted: "+data.procLabel+ " c=> " +root.getCount());
			System.out.println();
		}

		private TreeNode insertNode(TreeNode root, Item data) 
		{
			TreeNode tmpNode = null;
			if(root.getData().vt == data.vt)
			{
				root.increaseCount();
				root.setLabels(data.procLabel);				
				return root;
			}
			System.out.print(" ->"+root.getData().procLabel+ "vt="+root.getData().vt+ " c=> " +root.getCount());
			if(root.getData().vt >= data.vt) {
				System.out.print(" [L]");
				if(root.getLeft() == null) {
					root.setLeft(new TreeNode(data));
					return root.getLeft();
				} else {
					tmpNode = root.getLeft();
				}
			} else {
				System.out.print(" [R]");
				if(root.getRight() == null) {
					root.setRight(new TreeNode(data));
					return root.getRight();
				} else {
					tmpNode = root.getRight();
				}
			}

			return insertNode(tmpNode, data);
		}

		public void delete(Item data) 
		{
			deleteNode(root, data);
		}

		
		public void deleteNode() 
		{
			Item item = minValue(root);			
		 	deleteNode(root, item);
		}
		
		private TreeNode deleteNode(TreeNode root, Item data) 
		{
			deletedItem = "";
			if(root == null) return root;

			System.out.println("ondition is " + data.vt + " ==> " + root.getData().vt);
			if(data.vt < root.getData().vt) {
				root.setLeft(deleteNode(root.getLeft(), data));
			} else if(data.vt > root.getData().vt) {
				root.setRight(deleteNode(root.getRight(), data));
			} else {
				
				if(root.getCount() > 1)
				{
					root.decreaseCount();
					Item item = root.getData();
					deletedItem = root.getData().procLabel;
					item.procLabel = root.getLabel();
					root.setData(item);
					return root;
				}
				
				if(root.getLeft() == null && root.getRight() == null) {
					System.out.println("deleting "+data.procLabel+ "vt="+data.vt);
					return null;
				} else if(root.getLeft() == null) {
					System.out.println("deleting "+data.procLabel+ "vt="+data.vt);
					return root.getRight();
				} else if(root.getRight() == null) {
					System.out.println("deleting "+data.procLabel+ "vt="+data.vt);
					return root.getLeft();
				} else {
					Item minValue = minValue(root.getRight());
					root.setData(minValue);
					root.setRight(deleteNode(root.getRight(), minValue));
					System.out.println("deleting "+data.procLabel+ "vt="+root.getData().vt);
				}
			}

			return root;
		}

		private Item minValue(TreeNode node) 
		{
			if(node.getLeft() != null) {
				return minValue(node.getLeft());
			}
			return node.getData();
		}

		public void inOrderTraversal() 
		{
			doInOrder(root);
		}

		private void doInOrder(TreeNode root) 
		{
			if(root == null) return;
			doInOrder(root.getLeft());

			if(root.getCount() > 1) {
			String[] lbl = root.getLabels();
			for (int i = 0; i < lbl.length; i++) 
				System.out.print(lbl[i] + "  ");
			}
			else {
				System.out.print(root.getData().procLabel+"=>"+root.getCount()+" ");
			}
			doInOrder(root.getRight());
		}
		
		public boolean findProcess(String procLabel)
		{
			return dofindProcess(root,procLabel);
		}

		private boolean dofindProcess(TreeNode root,String procLabel) 
		{
			if(root == null)
				return false;
			else if(root.getCount() > 1) {
				boolean find = false;
				String[] lbl = root.getLabels();
				for (int i = 0; i < lbl.length; i++) 
					if(lbl[i].compareToIgnoreCase(procLabel) == 0) {
						find = true;
						break;
					}
				return find;
				}
			else
				if(root.getData().procLabel.compareToIgnoreCase(procLabel) == 0) 
					return true;			
			else if(root.getData().procLabel.compareToIgnoreCase(procLabel) < 0)
				return dofindProcess(root.getLeft(),procLabel);
			else
				return dofindProcess(root.getRight(),procLabel);
		}
	}
	
	BinarySearchTree myBinarySearchTree;
	String deletedItem = "";
    public BinarySearchTreeRQ() {
        // Implement Me
    	myBinarySearchTree = new BinarySearchTree();
    }  // end of BinarySearchTreeRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
    	myBinarySearchTree.insert(new Item(procLabel,vt));
 
    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
    	myBinarySearchTree.deleteNode();
    	if(deletedItem != "")
    		return deletedItem;
        return ""; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
        // Implement me
    	return myBinarySearchTree.findProcess(procLabel);
        //return false; // placeholder, modify this
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
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        // Implement me
    	myBinarySearchTree.inOrderTraversal();
    	System.out.println("");
    } // end of printAllProcess()

} // end of class BinarySearchTreeRQ
