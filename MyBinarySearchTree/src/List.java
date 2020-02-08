public class List< Item > implements ListInterface< Item > {
   // Implements a list node as an inner class.
   protected class ListNode {
      private Item value = null;

      public Item getValue( ) {
         return value;
      }

      public void setValue( Item newValue ) {
         value = newValue;
      }

      private ListNode next = null;

      public ListNode getNext( ) {
         return next;
      }

      public void setNext( ListNode nextNode ) {
         next = nextNode;
      }
   }

   // Private Data Members *Needs setters and getters*
   protected ListNode head = null;
   protected ListNode cur  = null;
   protected int              size = 0;

   // add after cur
   @Override
   public void add( Item value ) {
      ListNode temp = new ListNode( );
      temp.setValue( value );
      size++ ;
      if (isEmpty( )) {
         head = temp;
         cur = temp;
      } else {
         temp.setNext( cur.getNext( ) );
         cur.setNext( temp );
         cur = temp;
      }
   }

   // insert before cur
   @Override
   public void insert( Item value ) {
      ListNode temp = new ListNode( );
      temp.setValue( value );
      size++ ;
      if (isEmpty( )) {
         head = temp;
         cur = temp;
      } else if (head == cur) {
         head = temp;
         head.setNext( cur );
         cur = head;
      } else {
         ListNode prev = head;
         while( prev.getNext( ) != cur ) {
            prev = prev.getNext( );
         }
         temp.setNext( prev.getNext( ) );
         prev.setNext( temp );
         cur = temp;
      }
   }

   // remove item at cur
   // cur gets moved to previous node
   @Override
   public Item remove( ) {
      Item result = null;
      if (isEmpty( )) {
         return null;
      }
      if (head == cur) {
         head = head.getNext( );
         cur.setNext( null );
         result = cur.getValue( );
         cur = head;
         size-- ;
      } else {
         // Find previous node
         ListNode prev = head;
         while( prev.getNext( ) != cur ) {
            prev = prev.getNext( );
         }
         prev.setNext( cur.getNext( ) );
         cur.setNext( null );
         result = cur.getValue( );
         cur = prev;
         size-- ;
      }
      return result;
   }

   // get current item
   @Override
   public Item get( ) {
      if (isEmpty( )) {
         return null;
      }
      return cur.getValue( );
   }

   // get next item, moving cur
   // return that item
   @Override
   public Item next( ) {
      if (isEmpty( )) {
         return null;
      }
      if (!hasNext( )) {
         return null;
      }
      cur = cur.getNext( );
      return cur.getValue( );
   }

   // Returns true if there exists a next node in the list
   public boolean hasNext( ) {
      return( ( !isEmpty( ) ) && ( cur.getNext( ) != null ) );
   }

   // get previous item, moving cur
   // return that item
   @Override
   public Item prev( ) {
      if (isEmpty( )) {
         return null;
      }
      if (!hasPrev( )) {
         return null;
      }
      ListNode prev = head;
      while( prev.getNext( ) != cur ) {
         prev = prev.getNext( );
      }
      cur = prev;
      return cur.getValue( );
   }

   // Returns true if there exists a previous node in the list
   public boolean hasPrev( ) {
      return( ( !isEmpty( ) ) && ( cur != head ) );
   }

   // move cur to head
   @Override
   public void reset( ) {
      cur = head;
   }

   // number of items in list
   @Override
   public int size( ) {
      return size;
   }

   // True if the list empty; False otherwise
   @Override
   public boolean isEmpty( ) {
      return( head == null );
   }

   public String toString( ) {
      ListNode foo = ( ListNode ) cur;
      String result = "[ ";
      reset( );
      if (!isEmpty( )) {
         try {
            result += get( ) + ", ";
         } catch( Exception e ) {
         }
      }
      while( hasNext( ) ) {
         try {
            next( );
            result += get( ) + ", ";
         } catch( Exception e ) {
         }
      }
      cur = foo;
      return result + "]";
   }
}