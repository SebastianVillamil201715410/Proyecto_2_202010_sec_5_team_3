package model.data_structures;

import java.util.Iterator;

public class Valor <V> implements Iterator<Valor<V>>
{
    private V value;
  
    private Valor<V> next; 
  
    public Valor(V value) 
    { 
        this.value = value;
        this.next = null;
    }
    
    public V darValor()
    {
    	return value;
    }
    
    public Valor<V> next()
    {
    	return next;
    }
    
    public void cambiarNext(Valor<V> pNext)
    {
    	next = pNext;
    }
    
    public void cambiarValor(V pValue)
    {
    	value = pValue;
    }
    
    public void agregarValor(V pValue)
    {
    	if (next == null)
    		next = new Valor<V>(pValue);
    	else 
    		next.agregarValor(pValue);
    }

	public boolean hasNext() 
	{
		return next != null;
	}
}
