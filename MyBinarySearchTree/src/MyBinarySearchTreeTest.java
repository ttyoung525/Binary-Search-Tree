import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Program 2
 * CS2321 R01
 * Fall 2014
 * @author Trevor Young
 */

public class MyBinarySearchTreeTest {

	@Test
	public void putTest() {
		MyBinarySearchTree<String, Integer> tree = new MyBinarySearchTree<String, Integer>();
		tree.put("P", 16);
		tree.put("B", 2);
		tree.put("C", 3);
		tree.put("A", 1);
		tree.put("D", 8);
		tree.put("Y", 25);
		tree.put("T", 20);
		tree.put("D", 4);
		
		if(!tree.toString().equals("[<P, 16>, <B, 2>, <Y, 25>, <A, 1>, <C, 3>, <T, 20>, null, null, null, null, <D, 4>]")){
			fail("Order Wrong");
		}
		
		if(!tree.preOrderTraversal().toString().equals("[ <P, 16>, <B, 2>, <A, 1>, <C, 3>, <D, 4>, <Y, 25>, <T, 20>, ]")){
			fail("Order Wrong");
		}
		
		if(!tree.postOrderTraversal().toString().equals("[ <A, 1>, <D, 4>, <C, 3>, <B, 2>, <T, 20>, <Y, 25>, <P, 16>, ]")){
			fail("Order Wrong");
		}
		
		if(!tree.inOrderTraversal().toString().equals("[ <A, 1>, <B, 2>, <C, 3>, <D, 4>, <P, 16>, <T, 20>, <Y, 25>, ]")){
			fail("Order Wrong");
		}
	}
	
	@Test
	public void getTest() {
		MyBinarySearchTree<String, Integer> tree = new MyBinarySearchTree<String, Integer>();
		tree.put("P", 16);
		tree.put("Y", 25);
		tree.put("A", 1);
		
		if(tree.get("Y") != 25){
			fail("Returned Wrong Value");
		}
	}

	@Test
	public void removeTest() {
		MyBinarySearchTree<String, Integer> tree = new MyBinarySearchTree<String, Integer>();
		tree.put("P", 16);
		tree.put("T", 20);
		tree.put("A", 1);
		
		if(tree.remove("T") != 20){
			fail("Wrong Value removed");
		}
	}
	
	@Test
	public void removeEmptyTest(){
		MyBinarySearchTree<String, Integer> tree = new MyBinarySearchTree<String, Integer>();
		
		if(tree.remove("A") != null){
			fail("Remove Did Not Return Null");
		}
	}
	
	@Test
	public void sizeTest() {
		MyBinarySearchTree<String, Integer> tree = new MyBinarySearchTree<String, Integer>();
		tree.put("A", 1);
		tree.put("D", 4);
		tree.put("C", 3);
		tree.put("B", 2);
		tree.put("P", 16);
		tree.put("Y", 25);
		tree.put("T", 20);
		tree.remove("T");
		tree.remove("A");
		
		if(tree.getSize() != 5){
			fail("Size Wrong");
		}
	}
	
	@Test
	public void isEmptyTest() {
		MyBinarySearchTree<String, Integer> tree = new MyBinarySearchTree<String, Integer>();
		
		if(tree.isEmpty() != true){
			fail("isEmpty Wrong");
		}
	}
	
	@Test
	public void isEmptyTestToo() {
		MyBinarySearchTree<String, Integer> tree = new MyBinarySearchTree<String, Integer>();
		tree.put("A", 1);
		
		if(tree.isEmpty() == true){
			fail("isEmpty Wrong");
		}
	}
}
