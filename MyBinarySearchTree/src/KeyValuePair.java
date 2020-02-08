
public class KeyValuePair< Key extends Comparable< Key >, Value extends Comparable<Value> > 
      implements Comparable< KeyValuePair<Key, Value> > {
   private Key key = null;
   public Key getKey( ) {
      return key;
   }
   public void setKey( Key newKey ) {
      key = newKey;
   }
   
   private Value value = null;
   public Value getValue( ) {
      return value;
   }
   public void setValue( Value newValue ) {
      value = newValue;
   }
   
   public String toString( ) {
      return "<" + getKey( ) + ", " + getValue( ) + ">";
   }
   
   // When comparing, compare keys
   @Override
   public int compareTo( KeyValuePair< Key, Value > kvp ) {
      return getKey( ).compareTo( kvp.getKey( ) );
   }
}