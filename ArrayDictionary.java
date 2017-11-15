/*
 * A program that sorts objects by value and key (Dictionary)
 * using an array
 * @author Grant Okamoto
 * @version 1.0
 * 
 */

import java.util.Iterator;

public class ArrayDictionary<K extends Comparable<? super K>, V> implements DictionaryInterface<K, V> 
{

	private static final int DEFAULT_CAPACITY = 10;
	private Entry<K, V>[] dictionary;
	private int numOfEntries;
	
	public ArrayDictionary()
	{
		this(DEFAULT_CAPACITY);
	}
	
	public ArrayDictionary(int desiredSize)
	{
		@SuppressWarnings("unchecked")
		Entry<K, V>[] tempDictionary = (Entry<K, V>[])new Entry[desiredSize]; 
		dictionary = tempDictionary;
		numOfEntries = 0;
		
	}
	
	public V add(K key, V value) 
	{
		V result = null;
		int topIndex = numOfEntries - 1;
		
		Entry<K, V> newEntry = new Entry<K, V>();
		newEntry.setKey(key);
		newEntry.setValue(value);
		
		if(numOfEntries == 0)
		{
			dictionary[0] = newEntry;
			numOfEntries++;
		}
		else
		{
			for(int i = topIndex; i >= 0; i--)
			{
				if(key.compareTo(dictionary[i].getKey()) > 0)
				{
					if(topIndex == i)
					{
						dictionary[i + 1] = newEntry;
					}
					else
					{
						while(topIndex >= i)
						{
							dictionary[topIndex + 1] = dictionary[topIndex];
							topIndex--;
						}
						dictionary[i] = newEntry;
					}
					numOfEntries++;
					return result;
				}
				else if(key.equals(dictionary[i].getKey()))
				{
					dictionary[i].setKey(key);
					result = dictionary[i].getValue();
					return result;
				}
			}
			topIndex = numOfEntries - 1;
			while(topIndex >= 0)
			{
				dictionary[topIndex + 1] = dictionary[topIndex];
				topIndex--;
			}
			dictionary[0] = newEntry;
			numOfEntries++;
		}
		return result;
	}

	public V remove(K key) 
	{
		V tempValue = null;
		int currentIndex = numOfEntries - 1;
		for(int i = currentIndex; i >= 0; i--)
		{
			if(dictionary[i].getKey() == key)
			{
				tempValue = dictionary[i].getValue();
				dictionary[i] = null;
				while(dictionary[currentIndex] != dictionary[i])
				{
					dictionary[currentIndex - 1] = dictionary[currentIndex];
					currentIndex--;
				}
			}
		} 
		
		return tempValue;
	}

	public V getValue(K key) {
		int currentIndex = numOfEntries - 1;
		for(int i = currentIndex; i >= 0; i--)
		{
			if(dictionary[i].getKey() == key)
			{
				return dictionary[i].getValue();
			}
		}
		return null;
	}

	public boolean contains(K key) {
		int currentIndex = numOfEntries - 1;
		for(int i = currentIndex; i >= 0; i--)
		{
			if(dictionary[i].getKey() == key)
			{
				return true;
			}
		}
		return false;
	}

	public Iterator<K> getKeyIterator() 
	{
		
		return new ArrayKeyIterator();
	}

	public Iterator<V> getValueIterator() 
	{
		return new ArrayValueIterator();
	}

	public boolean isEmpty()
	{
		return numOfEntries == 0;
	}

	public int getSize() 
	{
		return numOfEntries;
	}

	public void clear() 
	{
		for(int i = 0; i < numOfEntries; i++)
		{
			dictionary[i] = null;
		}
	}
	public class ArrayKeyIterator implements Iterator<K>
	{
		private int currentIndex;;
		
		public ArrayKeyIterator()
		{
			currentIndex = -1;
		}
		
		public boolean hasNext()
		{
			return dictionary[currentIndex + 1] != null;
		}
		
		public K next()
		{
			currentIndex++;
			return dictionary[currentIndex].getKey();
		}
		
	}
	
	public class ArrayValueIterator implements Iterator<V>
	{
		private int currentIndex;
		
		public ArrayValueIterator()
		{
			currentIndex = -1;
		}
		
		public boolean hasNext()
		{
			return dictionary[currentIndex + 1] != null;
		}
		
		public V next()
		{
			currentIndex++;
			return dictionary[currentIndex].getValue();
		}
		
	}
	
}
