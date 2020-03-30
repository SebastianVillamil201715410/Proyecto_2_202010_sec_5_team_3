package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

public class TablaHashSeparada <K extends Comparable<K>,V>
{ 
	private final static int DEFAULT_SIZE = 2;
	
	private ArrayList<NodoSC<K, V>> arreglo; 

	private int M; 

	private int N; 

	public TablaHashSeparada() 
	{ 
		arreglo = new ArrayList<>(); 
		this.M = DEFAULT_SIZE; 
		this.N = 0; 

		for (int i = 0; i < M; i++) 
			arreglo.add(null); 
	} 
	
	public TablaHashSeparada(int m) 
	{ 
		arreglo = new ArrayList<>(); 
		this.M = m; 
		this.N = 0; 

		for (int i = 0; i < m; i++) 
			arreglo.add(null); 
	} 

	public int size() 
	{ 
		return N; 
	} 

	public boolean isEmpty() 
	{ 
		return size() == 0;
	}

	public int darM ()
	{
		return M;
	}
	
	private int hash(K key) 
	{ 
		return (key.hashCode() & 0x7fffffff) % M;
	}

	private void rehash()
	{
		if ((double)N/(double)M <= 5.0) 
		{
			ArrayList<NodeSC<K, V>> temp = arreglo; 
			arreglo = new ArrayList<>(); 
			M = 2 * M; 
			N = 0; 
			for (int i = 0; i < M; i++) 
				arreglo.add(null); 
			for (int i = 0; i < temp.size(); i++) 
			{ 
				NodeSC<K, V> tempHead = arreglo.get(i); 			
				while (tempHead != null) 
				{ 
					put(tempHead.darKey(), tempHead.darValue()); 
					tempHead = tempHead.darNext();
				} 
			} 
		}
	}

	public void put(K key, V value) 
	{ 
		int pos = hash(key); 
		NodeSC<K, V> head = arreglo.get(pos); 

		while (head != null) 
		{ 
			if (head.darKey().equals(key)) 
			{ 
				head.cambiarValue(value);
				return; 
			} 
			head = head.darNext(); 
		} 

		N++; 
		head = arreglo.get(pos); 
		NodeSC<K, V> nuevo = new NodeSC<K, V>(key, value); 
		nuevo.cambiarNext(head); 
		arreglo.set(pos, nuevo); 

		rehash();
	}
	
	public void putInSet(K key, V value)
	{
		int pos = hash(key); 
		NodeSC<K, V> head = arreglo.get(pos); 

		while (head != null) 
		{ 
			if (head.darKey().equals(key)) 
			{ 
				head.agregarValor(value);
				return; 
			} 
			head = head.darNext(); 
		} 

		N++; 
		head = arreglo.get(pos); 
		NodeSC<K, V> nuevo = new NodeSC<K, V>(key, value); 
		nuevo.cambiarNext(head); 
		arreglo.set(pos, nuevo); 
		rehash();
	}

	public V get(K key) 
	{ 
		int pos = hash(key); 
		NodeSC<K, V> head = arreglo.get(pos); 

		while (head != null) 
		{ 
			if (head.darKey().equals(key)) 
				return head.darValue(); 
			head = head.darNext(); 
		} 
		return null; 
	}
	
	public Iterator<V> getSet(K key)
	{
		int pos = hash(key); 
		NodeSC<K, V> head = arreglo.get(pos); 

		while (head != null) 
		{ 
			if (head.darKey().equals(key)) 
				return (Iterator<V>) head.darValues(); 
			head = head.darNext(); 
		} 
		return null;
	}
	
	public V delete	(K key) 
	{ 
		int pos = hash(key); 

		NodeSC<K, V> head = arreglo.get(pos); 	
		NodeSC<K, V> prev = null; 
		while (head != null) 
		{ 
			if (head.darKey().equals(key)) 
				break; 

			prev = head; 
			head = head.darNext(); 
		} 

		if (head == null) 
			return null;  

		N--;
		if (prev != null) 
			prev.cambiarNext(head.darNext()); 
		else
			arreglo.set(pos, head.darNext()); 


		return head.darValue(); 
	}
	
	public Iterator<V> deleteSet(K key)
	{
		int pos = hash(key); 

		NodeSC<K, V> head = arreglo.get(pos); 	
		NodeSC<K, V> prev = null; 
		while (head != null) 
		{ 
			if (head.darKey().equals(key)) 
				break; 

			prev = head; 
			head = head.darNext(); 
		} 

		if (head == null) 
			return null;  

		N--;
		if (prev != null) 
			prev.cambiarNext(head.darNext()); 
		else
			arreglo.set(pos, head.darNext()); 


		return (Iterator<V>) head.darValues(); 
	}
	
	public Iterable<K> keys() 
	{
        ILista<K> keys = new Lista<K>();
        for (int i = 0; i < arreglo.size(); i++)
        {
        	NodeSC<K, V> head = arreglo.get(i);
        	while (head != null)
        	{
        		keys.agregar(head.darKey());
        		head = head.darNext();
        	}
        }     
        return (Iterable<K>) keys;
    }
