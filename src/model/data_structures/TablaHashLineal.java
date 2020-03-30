package model.data_structures;

import java.util.Iterator;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;

public class TablaHashLineal <K extends Comparable<K>, V>
{
private final static int DEFAULT_SIZE = 2;
	
    private int N;           
    private int M;           
    private K[] keys;
    private Valor<V>[] vals;

    public TablaHashLineal() 
    {
        N = 0;
        M = DEFAULT_SIZE;
        keys = (K[]) new Object[M];
        vals = new Valor[M];
    }
    
    public TablaHashLineal(int tam) 
    {
        N = 0;
        M = tam;
        keys = (K[]) new Object[M];
        vals = new Valor[M];
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
    
    public boolean contains(K key) 
    {
        if (key == null) throw new IllegalArgumentException("argument to contains() is null");
        return get(key) != null;
    }

    
    private void resize(int tam) 
    {
    	TablaHashLineal<K, V> temp = new TablaHashLineal<K, V>(tam);
        for (int i = 0; i < M; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], vals[i]);
            }
        }
        keys = temp.keys;
        vals = temp.vals;
        M    = temp.M;
    }

    public void put(K key, Valor<V> val) 
    {
        if (N >= M/2) 
        	resize(2*M);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) 
        {
            if (keys[i].equals(key)) 
            {
                vals[i] = val;
                return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }
    
    public void putInSet(K key, Valor<V> val) 
    {
        if (N >= M/2) 
        	resize(2*M);
        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % M) 
        {
            if (keys[i].equals(key))
            {
            	vals[i].cambiarNext(val);
            	return;
            }
        }
        keys[i] = key;
        vals[i] = val;
        N++;
    }

    public V get(K key) 
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return vals[i].darValor();
        return null;
    }

    public Iterator<V> getSet(K key) 
    {
        for (int i = hash(key); keys[i] != null; i = (i + 1) % M)
            if (keys[i].equals(key))
                return (Iterator<V>) vals[i];
        return null;
    }
    
    public V delete(K key) 
    {
        if (!contains(key)) 
        	return null;

        int i = hash(key);
        while (!key.equals(keys[i])) 
        {
            i = (i + 1) % M;
        }
        V retorno = get(key);
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        
        while (keys[i] != null) 
        {
            K keyToRehash = keys[i];
            Valor<V> valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }

        N--;
        if (N > 0 && N <= M/8) resize(M/2);
        return retorno;
    }

    public Iterator<V> deleteSet(K key) 
    {
        if (!contains(key)) return null;

        int i = hash(key);
        while (!key.equals(keys[i])) 
        {
            i = (i + 1) % M;
        }

        Iterator<V> retorno = getSet(key);
        keys[i] = null;
        vals[i] = null;
        i = (i + 1) % M;
        
        while (keys[i] != null) 
        {
            K keyToRehash = keys[i];
            Valor<V> valToRehash = vals[i];
            keys[i] = null;
            vals[i] = null;
            N--;
            put(keyToRehash, valToRehash);
            i = (i + 1) % M;
        }

        N--;
        if (N > 0 && N <= M/8) resize(M/2);
        return retorno;
    }
   
    public Iterable<K> keys() 
    {
        ILista<K> llaves = new Lista<K>();
        for (int i = 0; i < M; i++)
            if (keys[i] != null) 
            	llaves.agregar(keys[i]);
        return (Iterable<K>) llaves;
    }
}