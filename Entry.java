/*
 * A Dictionary entry for dictionaries using arrays
 * @author Grant Okamoto
 * @version 1.0
 * 
 */
public class Entry<K, V> {
	private K key;
	private V value;
	
	public Entry()
	{
		key = null;
		value = null;
	}
	public K getKey()
	{
		return key;
	}
	
	public V getValue()
	{
		return value;
	}
	
	public void setKey(K newKey)
	{
		key = newKey;
	}
	public void setValue(V newValue)
	{
		value = newValue;
	}
	
}
