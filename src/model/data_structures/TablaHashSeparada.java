package model.data_structures;

import java.util.ArrayList;
import java.util.Iterator;

public class TablaHashSeparada <K extends Comparable<K>,V>
{ 
	private static final int INIT_CAPACITY = 4;

	//Atributos

	private int tamanho;                                

	private int tamDos;                                

	private NodoST<K, V>[] nodo;  

	private int totalRehash; 



	//Metodos 

	/**
	 * 
	 */
	public TablaHashSeparada() {
		this(INIT_CAPACITY);
	} 

	/**
	 * 
	 */
	public TablaHashSeparada(int m) 
	{
		this.tamDos = m;
		nodo = (NodoST<K, V>[]) new NodoST[m];

		for (int i = 0; i < m; i++)
		{
			nodo[i] = new NodoST<>();	
		}
	} 

	/**
	 * 
	 * @param key
	 * @return
	 */
	private int hash(K key) 
	{
		return (key.hashCode() & 0x7fffffff) % tamDos;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() 
	{
		return size() == 0;
	}

	/**
	 * 
	 * @param chains
	 */
	private void resize(int chains) 
	{
		TablaHashSeparada<K, V> temporal = new TablaHashSeparada<K, V>(chains);

		for (int i = 0; i < tamDos; i++) 
		{
			for (K key : nodo[i].keys()) 
			{
				temporal.put(key, nodo[i].get(key));
			}
		}

		this.tamDos  = temporal.tamDos;
		this.tamanho  = temporal.tamanho;
		this.nodo = temporal.nodo;

		totalRehash++;
	}

	/**
	 * 
	 */
	public int size() 
	{
		return tamanho;
	} 
	
	/**
	 * 
	 * @return
	 */
	public int tamanoFinal()
	{
		return tamDos;
	}

	/**
	 * 
	 */
	public boolean contains(K key) 
	{
		if (key == null) 
		{
			throw new IllegalArgumentException("El argumento es nulo");
		}

		return get(key) != null;
	} 
	
	/**
	 * 
	 * @return
	 */
	public int tamanoInicial()
	{
		return INIT_CAPACITY;
	}

	/**
	 * 
	 * @return
	 */
	public float factorDeCargaFinal()
	{
		return tamanho/tamDos;
	}

	/**
	 * 
	 * @return
	 */
	public double totalRehashes()
	{
		return totalRehash;
	}

	/**
	 * 
	 */
	public V get(K key) 
	{
		if (key == null)
		{
			throw new IllegalArgumentException("argument to get() is null");
		}

		int i = hash(key);
		return nodo[i].get(key);
	} 

	/**
	 * 
	 */
	public void put(K key, V val) 
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

		if ( tamanho/tamDos >= 5.0) resize(2*tamDos);

		int i = hash(key);

		if (!nodo[i].contains(key))
		{
			tamanho++;
		}

		nodo[i].put(key, val);
	} 

	/**
	 * 
	 */
	public void delete(K key) 
	{
		if (key == null)
		{
			throw new IllegalArgumentException("argument to delete() is null");
		}

		int i = hash(key);

		if (nodo[i].contains(key))
		{
			tamanho--;
		}

		nodo[i].delete(key);


		if (tamDos > INIT_CAPACITY && tamanho <= 2*tamDos)
		{
			resize(tamDos/2);
		}
	} 

	/**
	 * 
	 * @return
	 */
	public Iterable<K> keys() 
	{
		Queue<K> queue = new Queue<>();

		for (int i = 0; i < tamDos; i++) 
		{
			for (K key : nodo[i].keys())
			{
				queue.enqueue(key);
			}
		}
		return (Iterable<K>) queue;
	}
} 
