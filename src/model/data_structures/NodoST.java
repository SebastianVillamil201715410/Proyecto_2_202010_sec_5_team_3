package model.data_structures;

public class NodoST<K extends Comparable<K>, Value> 
{

	//----------------------
	//Clase de apoyo
	//-----------------------

	private class Node 
	{
		private K key;
		private Value val;
		private Node next;

		public Node(K key, Value val, Node next)  
		{
			this.key  = key;
			this.val  = val;
			this.next = next;
		}
	}

	//-------------------------------
	//Atributos 

	private int total;           

	private Node first;      


	// Metodos

	/**
	 * 
	 */
	public NodoST() 
	{

	}

	/**
	 * 
	 */
	public int size() 
	{
		return total;
	}

	/**
	 * 
	 */
	public boolean isEmpty() 
	{
		return size() == 0;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public boolean contains(K key) 
	{
		if (key == null)
		{
			throw new IllegalArgumentException("argument to contains() is null");
		}

		return get(key) != null;
	}

	/**
	 * 
	 * @param key
	 * @return
	 */
	public Value get(K key) {
		if (key == null)
		{
			throw new IllegalArgumentException("argument to get() is null"); 
		}

		for (Node x = first; x != null; x = x.next) 
		{
			if (key.equals(x.key))
			{
				return x.val;
			}
		}
		return null;
	}

	/**
	 * 
	 * @param key
	 * @param val
	 */
	public void put(K key, Value val) 
	{
		if (key == null) 
		{
			throw new IllegalArgumentException("first argument to put() is null"); 
		}

		if (val == null) 
		{
			delete(key);
			return;
		}

		for (Node x = first; x != null; x = x.next) 
		{
			if (key.equals(x.key)) 
			{
				x.val = val;
				return;
			}
		}

		first = new Node(key, val, first);
		total++;
	}

	/**
	 * 
	 * @param key
	 */
	public void delete(K key) 
	{
		if (key == null) 
		{
			throw new IllegalArgumentException("argument to delete() is null"); 
		}

		first = delete(first, key);
	}

	/**
	 * 
	 * @param x
	 * @param key
	 * @return
	 */
	private Node delete(Node x, K key) 
	{
		if (x == null)
		{
			return null;
		}

		if (key.equals(x.key)) 
		{
			total--;
			return x.next;
		}

		x.next = delete(x.next, key);
		return x;
	}


	/**
	 * 
	 * @return
	 */
	public Iterable<K> keys()  
	{
		Queue<K> queue = new Queue<K>();
		
		for (Node x = first; x != null; x = x.next)
		{
			queue.enqueue(x.key);
		}
	
		return (Iterable<K>) queue;
	}
}