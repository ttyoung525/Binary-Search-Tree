
public interface BinarySearchTreeInterface< Key extends Comparable< Key >, Value extends Comparable< Value > > {
   // Return the value at the tree node indicated by the key.
   // If none exists, return null;
   public Value get( Key key );

   // Insert or replace the value at the tree node indicated by key.
   // Return the previous value or null if none existed.
   public Value put( Key key, Value value );

   // Remove the tree node indicated by key.
   // Return the previous value or null if none existed.
   public Value remove( Key key );

   // Return the list of KeyValuePairs produced by a pre-order traversal of the tree.
   // Return an empty list of the tree is empty.
   public List<KeyValuePair<Key,Value>> preOrderTraversal( );

   // Return the list of KeyValuePairs produced by an in-order traversal of the tree.
   // Return an empty list of the tree is empty.
   public List<KeyValuePair<Key,Value>> inOrderTraversal( );

   // Return the list of KeyValuePairs produced by a post-order traversal of the tree.
   // Return an empty list of the tree is empty.
   public List<KeyValuePair<Key,Value>> postOrderTraversal( );

   // Return an array of KeyValuePairs produced by a breadth-first traversal of the tree.
   // Return an empty array of the tree is empty.
   // Missing children should be represented by a null entry in the array.
   public KeyValuePair< Key, Value > [ ] breadthFirstTraversal( );
   
   // Returns the number of nodes in tree.
   public int getSize( );

   // Return true if there are no nodes in the tree
   public boolean isEmpty( );

   // Return a String representation of the tree.
   // Nodes should be ordered as if by breadth-first traversal.
   public String toString( );
}