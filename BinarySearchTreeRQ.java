import java.io.PrintWriter;
import java.lang.String;

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
	
	class BstNode {

		private BstNode left;
		private BstNode right;
		private Item data;

		public BstNode(Item data) {
			this.data = data;
		}

		public BstNode getLeft() {
			return left;
		}
		public void setLeft(BstNode left) {
			this.left = left;
		}
		public BstNode getRight() {
			return right;
		}
		public void setRight(BstNode right) {
			this.right = right;
		}

		public Item getData() {
			return data;
		}

		public void setData(Item data) {
			this.data = data;
		}
	}
	
	class BinarySearchTreeImpl {

		private BstNode root;

		public boolean isEmpty() {

			return (this.root == null);
		}

		public BstNode getRoot() {
			return this.root;
		}

		public void insert(Item data) {

			System.out.print("[input: "+data.procLabel+"]");
			if(root == null) {
				this.root = new BstNode(data);
				System.out.println(" -> inserted: "+data.procLabel);
				return;
			}

			insertNode(this.root, data);
			System.out.print(" -> inserted: "+data.procLabel);
			System.out.println();
		}

		private BstNode insertNode(BstNode root, Item data) {

			BstNode tmpNode = null;
			System.out.print(" ->"+root.getData().procLabel+ "vt="+root.getData().vt);
			if(root.getData().vt >= data.vt) {
				System.out.print(" [L]");
				if(root.getLeft() == null) {
					root.setLeft(new BstNode(data));
					return root.getLeft();
				} else {
					tmpNode = root.getLeft();
				}
			} else {
				System.out.print(" [R]");
				if(root.getRight() == null) {
					root.setRight(new BstNode(data));
					return root.getRight();
				} else {
					tmpNode = root.getRight();
				}
			}

			return insertNode(tmpNode, data);
		}

		public void delete(Item data) {

			deleteNode(this.root, data);
		}

		public void deleteNode() {

			deleteNode(this.root, minValue(root));
		}
		
		private BstNode deleteNode(BstNode root, Item data) {

			if(root == null) return root;

			System.out.println("ondition is " + data.vt + " ==> " + root.getData().vt);
			if(data.vt < root.getData().vt) {
				root.setLeft(deleteNode(root.getLeft(), data));
			} else if(data.vt > root.getData().vt) {
				root.setRight(deleteNode(root.getRight(), data));
			} else {
				// node with no leaf nodes
				if(root.getLeft() == null && root.getRight() == null) {
					System.out.println("deleting "+data.procLabel+ "vt="+data.vt);
					return null;
				} else if(root.getLeft() == null) {
					// node with one node (no left node)
					System.out.println("deleting "+data.procLabel+ "vt="+data.vt);
					return root.getRight();
				} else if(root.getRight() == null) {
					// node with one node (no right node)
					System.out.println("deleting "+data.procLabel+ "vt="+data.vt);
					return root.getLeft();
				} else {
					// nodes with two nodes
					// search for min number in right sub tree
					Item minValue = minValue(root.getRight());
					root.setData(minValue);
					root.setRight(deleteNode(root.getRight(), minValue));
					System.out.println("deleting "+data.procLabel+ "vt="+root.getData().vt);
				}
			}

			return root;
		}

		private Item minValue(BstNode node) {

			if(node.getLeft() != null) {
				return minValue(node.getLeft());
			}
			return node.getData();
		}

		public void inOrderTraversal() {
			doInOrder(this.root);
		}

		private void doInOrder(BstNode root) {

			if(root == null) return;
			doInOrder(root.getLeft());
			System.out.print(root.getData().procLabel+" ");
			doInOrder(root.getRight());
		}
	}
	
	//private TreeNode root;
	BinarySearchTreeImpl bst = new BinarySearchTreeImpl();
    public BinarySearchTreeRQ() {
        // Implement Me
    }  // end of BinarySearchTreeRQ()


    @Override
    public void enqueue(String procLabel, int vt) {
        // Implement me
    	bst.insert(new Item(procLabel,vt));
//		bst.insert(10);
//		bst.insert(14);
//		bst.insert(3);
//		bst.insert(6);
//		bst.insert(7);
//		bst.insert(1);
//		bst.insert(4);
//		bst.insert(13);
//		System.out.println("-------------------");
//		System.out.println("In Order Traversal");
//		bst.inOrderTraversal();
//		System.out.println();
//		bst.delete(13);
//		bst.inOrderTraversal();
//		System.out.println();
//		bst.delete(14);
//		bst.inOrderTraversal();
    	//root = addNode(root ,addItem);
 
    } // end of enqueue()


    @Override
    public String dequeue() {
        // Implement me
    	bst.deleteNode();
        return ""; // placeholder, modify this
    } // end of dequeue()


    @Override
    public boolean findProcess(String procLabel) {
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
    } // end of precedingProcessTime()


    @Override
    public int succeedingProcessTime(String procLabel) {
        // Implement me

        return -1; // placeholder, modify this
    } // end of precedingProcessTime()


    @Override
    public void printAllProcesses(PrintWriter os) {
        // Implement me
    	//printInOrder(root);
    	bst.inOrderTraversal();
    	System.out.println("");
    } // end of printAllProcess()

} // end of class BinarySearchTreeRQ
