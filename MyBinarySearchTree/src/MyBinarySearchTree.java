/**
 * Program 2
 * CS2321 R01
 * Fall 2014
 * @author Trevor Young
 */

public class MyBinarySearchTree<Key extends Comparable<Key>, Value extends Comparable<Value>> implements BinarySearchTreeInterface<Key, Value> {

	private int size = 0;
	private Node root;

	private class Node{
		Node leftChild = null;
		Node rightChild = null;
		Node parent = null;
		KeyValuePair<Key, Value> data = null;
		boolean nullChild;

		public Node(KeyValuePair<Key, Value> data, Node leftChild, Node rightChild, Node parent, boolean nullChild){
			this.data = data;
			this.leftChild = leftChild;
			this.rightChild = rightChild;
			this.parent = parent;
			this.nullChild = nullChild;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + ((data == null) ? 0 : data.hashCode());
			result = prime * result + (nullChild ? 1231 : 1237);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			}
			if (obj == null) {
				return false;
			}
			Node other = (Node) obj;
			if (!getOuterType().equals(other.getOuterType())) {
				return false;
			}
			if (data == null) {
				if (other.data != null) {
					return false;
				}
			} else if (!data.equals(other.data)) {
				return false;
			}
			if (nullChild != other.nullChild) {
				return false;
			}
			return true;
		}

		private MyBinarySearchTree getOuterType() {
			return MyBinarySearchTree.this;
		}

	}

	/**
	 * Search method that is given the a key, current node, previous node, and array of nodes. Returns the array with the current node at index = 1 and
	 * the previous node at index = 0
	 * 
	 * @param key A key to sort the binary tree with.
	 * @param prev The previous node.
	 * @param cur The current node.
	 * @param result Array to store the previous and current node.
	 * 
	 * @return An array with the current node at index = 1 and the previous node at index = 0;
	 */
	private Node[] search(Key key, Node prev, Node cur, Node[] result){
		if(cur == null){
			result[0] = prev;
			result[1] = null;
			return result;
		}

		if(cur.leftChild == null && cur.rightChild == null){
			if(cur.data.getKey().compareTo(key) == 0){
				result[0] = prev;
				result[1] = cur;
				return result;
			}

			result[0] = cur;
			result[1] = null;
			return result;
		}

		if(cur.data.getKey().compareTo(key) == 0){
			result[0] = prev;
			result[1] = cur;
			return result;
		}

		if(cur.data.getKey().compareTo(key) > 0){
			return search(key, cur, cur.leftChild, result);
		}

		if(cur.data.getKey().compareTo(key) < 0){
			return search(key, cur, cur.rightChild, result);
		}

		result[0] = prev;
		result[1] = null;
		return result;
	}

	/**
	 * Returns the value with the associated key or null if key does not exist in the tree.
	 * 
	 * @param key The key that is being searched for.
	 * 
	 * @return The value of the node with the associated key or null if key does not exist in the tree.
	 */
	@Override
	public Value get(Key key) {
		@SuppressWarnings("unchecked")
		Node[] val = (Node[])new MyBinarySearchTree.Node[2];
		val = search(key, null, root, val);

		if(val[1] == null){
			return null;
		}

		return val[1].data.getValue();
	}

	/**
	 * Put's a given value into the node with the given key or adds a new node if the key does not exist in the tree
	 * 
	 * @param key The key being searched for.
	 * @param value The value to be associated with the key.
	 * 
	 * @return Returns null if the key did not already exist or the previous value if the key already existed and the value was replaced.
	 */
	@Override
	public Value put(Key key, Value value) {
		@SuppressWarnings("unchecked")
		Node[] val = (Node[])new MyBinarySearchTree.Node[2];
		val = search(key, root, root, val);
		KeyValuePair<Key, Value> data = new KeyValuePair<Key,Value>();
		data.setKey(key);
		data.setValue(value);
		Value temp;

		if(size == 0){
			root = new Node(data, null, null, null, false);
			size++;

			return null;
		}

		if(val[1] == null && val[0].data.getKey().compareTo(key) > 0){
			val[0].leftChild = new Node(data, null, null, val[0], false);
			size++;


			return null;
		}

		if(val[1] == null && val[0].data.getKey().compareTo(key) < 0){
			val[0].rightChild = new Node(data, null, null, val[0], false);
			size++;

			return null;
		}

		if(val[1] != null){
			temp = val[1].data.getValue();
			val[1].data.setValue(value);
			return temp;
		}

		return null;
	}

	/**
	 * Removes the node with the given key
	 * 
	 * @param key The key that is being searched for.
	 * 
	 * @return Returns the value removed or null if the key did not exist in the tree.
	 */
	@Override
	public Value remove(Key key) {
		@SuppressWarnings("unchecked")
		Node[] val = (Node[])new MyBinarySearchTree.Node[2];
		val = search(key, null, root, val);
		Node cur;
		Node temp;

		if(val[1] == null){
			return null;
		}

		temp = val[1];
		size--;


		if(val[1].leftChild == null && val[1].rightChild == null){

			if(val[1] != root){

				if(val[1].data.getKey().compareTo(val[1].parent.data.getKey()) < 0){
					val[1].parent.leftChild = null;
				}

				if(val[1].data.getKey().compareTo(val[1].parent.data.getKey()) > 0){
					val[1].parent.rightChild = null;
				}

				val[1].parent = null;
			}

			if(val[1] == root){
				val[1] = null;
			}

			return temp.data.getValue();
		}

		if(val[1].leftChild != null && val[1].rightChild == null){

			if(val[1].data.getKey().compareTo(val[1].parent.data.getKey()) < 0){
				val[1].parent.leftChild = val[1].leftChild;
			}

			if(val[1].data.getKey().compareTo(val[1].parent.data.getKey()) > 0){
				val[1].parent.rightChild = val[1].leftChild;
			}

			val[1].leftChild.parent = val[1].parent;

			return temp.data.getValue();
		}

		if(val[1].leftChild == null && val[1].rightChild != null){

			if(val[1] != root){
				if(val[1].data.getKey().compareTo(val[1].parent.data.getKey()) < 0){
					val[1].parent.leftChild = val[1].rightChild;
				}

				if(val[1].data.getKey().compareTo(val[1].parent.data.getKey()) > 0){
					val[1].parent.rightChild = val[1].rightChild;
				}
			}
			val[1].rightChild.parent = val[1].parent;

			return temp.data.getValue();
		}

		cur = val[1].leftChild;

		while(cur.rightChild != null){
			cur = cur.rightChild;
		}

		if(cur.leftChild != null){
			cur.leftChild.parent = cur.parent;
		}

		cur.parent.rightChild = cur.leftChild;

		if(val[1].data.getKey().compareTo(val[1].parent.data.getKey()) < 0){
			val[1].parent.leftChild = cur;
		}

		if(val[1].data.getKey().compareTo(val[1].parent.data.getKey()) > 0){
			val[1].parent.rightChild = cur;
		}

		cur.parent = val[1].parent;

		if(val[1].leftChild != null){
			val[1].leftChild.parent = cur;
		}

		if(val[1].rightChild != null){
			val[1].rightChild.parent = cur;
		}

		return temp.data.getValue();
	}

	// Helper method for recursively iterating through the list.
	private void preOrder(List<KeyValuePair<Key, Value>> list, Node cur){
		if(cur == null){
			return;
		}

		list.add(cur.data);
		preOrder(list, cur.leftChild);
		preOrder(list, cur.rightChild);
	}

	/**
	 * Returns a pre-order traversal of the tree stored in a list.
	 * 
	 * @return Returns the traversal stored in a list.
	 */
	@Override
	public List<KeyValuePair<Key, Value>> preOrderTraversal() {
		List<KeyValuePair<Key, Value>> list = new List<KeyValuePair<Key, Value>>();
		preOrder(list, root);
		return list;
	}

	// Helper method for iterating recursively through the list.
	private void inOrder(List<KeyValuePair<Key, Value>> list, Node cur){
		if(cur == null){
			return;
		}

		inOrder(list, cur.leftChild);
		list.add(cur.data);
		inOrder(list, cur.rightChild);
	}

	/**
	 * Returns a in-order traversal of the tree stored in a list.
	 * 
	 * @return Returns the traversal stored in a list.
	 */
	@Override
	public List<KeyValuePair<Key, Value>> inOrderTraversal() {
		List<KeyValuePair<Key, Value>> list = new List<KeyValuePair<Key, Value>>();
		inOrder(list, root);
		return list;
	}

	// Helper method for iterating recursively through the list.
	private void postOrder(List<KeyValuePair<Key, Value>> list, Node cur){
		if(cur == null){
			return;
		}

		postOrder(list, cur.leftChild);
		postOrder(list, cur.rightChild);
		list.add(cur.data);
	}

	/**
	 * Returns a post-order traversal of the tree stored in a list.
	 * 
	 * @return Returns the traversal stored in a list.
	 */
	@Override
	public List<KeyValuePair<Key, Value>> postOrderTraversal() {
		List<KeyValuePair<Key, Value>> list = new List<KeyValuePair<Key, Value>>();
		postOrder(list, root);
		return list;
	}

	private int depthFinder(Node data, int depth){
		int leftDepth = depth;
		int rightDepth = depth;

		if(data.leftChild != null){
			leftDepth = depthFinder(data.leftChild, depth+1);
		}

		if(data.rightChild != null){
			rightDepth = depthFinder(data.rightChild, depth+1);
		}

		if(leftDepth > rightDepth){
			return leftDepth;
		}

		else{
			return rightDepth;
		}
	}
	
	// method to find the last Node in a tree
	private KeyValuePair<Key, Value> lastFinder(){
		List<Node> list = new List<Node>();
		Node temp;
		list.add(root);
		
		while(list.size() != 0){
			temp = list.head.getValue();

			while(list.hasNext() == true){
				list.next();
			}
			
			if(temp.data.getKey() != null && temp.leftChild != null){
				list.add(temp.leftChild);
			}
			
			if(temp.data.getKey() != null && temp.rightChild != null){
				list.add(temp.rightChild);
			}
			
			list.reset();
			
			if(list.size() == 1 && list.cur.getValue().leftChild == null && list.cur.getValue().rightChild == null){
				return temp.data;
			}
			list.remove();
		}
		
		return null;
	}

	/**
	 * Returns a breadth first traversal of the tree stored in an array where missing children are noted by "null" place holders.
	 * 
	 * @return Returns the traversal stored in an array.
	 */
	@SuppressWarnings("unchecked")
	@Override
	public KeyValuePair<Key, Value>[] breadthFirstTraversal() {
		List<Node> list = new List<Node>();
		KeyValuePair<Key,Value> last;
		last = lastFinder();
		int index = 0;
		int maxSize = 1;
		int depth = depthFinder(root, 0);
		KeyValuePair<Key, Value> data = new KeyValuePair<Key, Value>();
		data.setKey(null);
		data.setValue(null);
		Node placeHolder  = new Node(data, null, null, null, false);
		Node nullHolder = new Node(data, null, null, null, true);
		Node temp;
		list.add(root);

		for(index = 0;index<depth+1;index++){
			maxSize = maxSize*2;
		}

		@SuppressWarnings("unchecked")
		KeyValuePair<Key, Value>[] result = new KeyValuePair[maxSize-1];

		index = 0;

		while(list.size() > 0 && index<result.length){
			list.reset();
			temp = list.remove();

			if(temp.data.equals(last)){
				result[index] = temp.data;
				break;
			}
			
			if(temp.equals(nullHolder)){
				result[index] = null;
			}

			else if(temp.equals(placeHolder)){
				while(list.hasNext() == true){
					list.next();
				}
				
				list.add(new Node(data, null, null, null, true));
				list.add(new Node(data, null, null, null, true));
				result[index] = null;
			}
			
			else{
				if(temp.leftChild != null){
					while(list.hasNext() == true){
						list.next();
					}
					
					list.add(temp.leftChild);
				}
				
				else{
					while(list.hasNext() == true){
						list.next();
					}
					list.add(new Node(data, null, null, null, false));
				}
				
				if(temp.rightChild != null){
					while(list.hasNext() == true){
						list.next();
					}
					
					list.add(temp.rightChild);
				}
				
				else{
					while(list.hasNext() == true){
						list.next();
					}
					list.add(new Node(data, null, null, null, false));
				}
				
				result[index] = temp.data;
			}
			
			index++;
		}
		
		KeyValuePair<Key, Value>[] finalResult = new KeyValuePair[index+1];
		
		for(int indexToo = 0; indexToo < index+1;indexToo++){
			finalResult[indexToo] = result[indexToo];
		}

		return finalResult;
	}

	/**
	 * Returns the size of the tree.
	 * 
	 * @return Returns the size of the tree.
	 */
	@Override
	public int getSize() {
		return size;
	}

	/**
	 * Returns true if the tree is empty, and false if not.
	 * 
	 * @return Returns true if the tree is empty, and false if not.
	 */
	@Override
	public boolean isEmpty() {
		if(root == null){
			return true;
		}

		return false;
	}

	/**
	 * Returns the results of the breadthFirstTraversal method in the form of a string.
	 * 
	 * @return Returns the results of the breadthFirstTraversal method in the form of a string.
	 */
	public String toString(){
		KeyValuePair<Key, Value>[] result;
		result = breadthFirstTraversal();
		String resultString = "[";
		String comma = "";

		for(int index = 0;index<result.length;index++){
			if(result[index] == null){
				resultString = resultString+comma+"null";
			}
			else{
				resultString = resultString+comma+result[index].toString();
			}

			comma = ", ";
		}

		resultString = resultString+"]";
		return resultString;
	}

}