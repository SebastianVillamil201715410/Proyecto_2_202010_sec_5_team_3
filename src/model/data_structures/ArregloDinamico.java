package model.data_structures;

import model.logic.Comparendo;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class ArregloDinamico <T extends Comparable <T>> implements IArregloDinamico <T>{
		/**
		 * Capacidad maxima del arreglo
		 */
        private int tamanoMax;
		/**
		 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        /**
         * Arreglo de elementos de tamaNo maximo
         */
        private T[] elementos;
        
    	private static final int TAMANIO_PROMEDIO = 5;


        /**
         * Construir un arreglo con la capacidad maxima inicial.
         * @param max Capacidad maxima inicial
         */
		public ArregloDinamico(  )
        {
			elementos = (T[]) new Comparable[TAMANIO_PROMEDIO];
			tamanoMax = TAMANIO_PROMEDIO;
			tamanoAct = 0;
        }
        
		public void agregar( String dato )
        {
               if ( tamanoAct == tamanoMax )
               {  // caso de arreglo lleno (aumentar tamaNo)
                    tamanoMax = 2 * tamanoMax;
                    String [ ] copia = (String[]) elementos;
                    elementos = (T[]) new String[tamanoMax];
                    for ( int i = 0; i < tamanoAct; i++)
                    {
                     	 elementos[i] = (T) copia[i];
                    } 
            	    System.out.println("Arreglo lleno: " + tamanoAct + " - Arreglo duplicado: " + tamanoMax);
               }	
               elementos[tamanoAct] = (T) dato;
               tamanoAct++;
       }

		public int darCapacidad() {
			return tamanoMax;
		}

		public int darTamano() {
			return tamanoAct;
		}

		/**
		 * 
		 */
		public String darElemento(int i) {
			
			for (int j = 0; j < elementos.length; j++) 
			{
				if(j == i)
				{
					return elementos[j] + "Posicion: " + j;
				}
			}
			return null;
		}

		/**
		 * 
		 */
		public String buscar(String dato) {
			
			for (int i = 0; i < tamanoAct; i++) {
				
				T actual = elementos[i];
				
				if(((String) actual).compareToIgnoreCase(dato) == 0)
				{
					return actual + " - Posicion: "+i;
				}
			}
			return "No se encontró el dato";
		}

		
		/**
		 * 
		 */
		public String eliminar(String dato) {
			
			String respuesta = "";
			boolean eliminado = false;
			
			for(int  i=0; i < tamanoAct && eliminado != true; i++) 
			{
				if(elementos[i]!= null && elementos[i].equals(dato)) 
				{
					respuesta = (String) elementos [i];
					elementos[i] = null;
					eliminado = true;
					elementos[i] = elementos[i+1];
				}	
			}
			return respuesta;
		}

		public T get(int i)
		{
			return elementos[i];
		}

		public void add( T dato)
	{
		if ( tamanoAct == tamanoMax )
		{  
			tamanoMax =  ((tamanoMax*3)/2)+1;
			T [ ] copia = elementos;
			elementos = (T[])new Comparable[tamanoMax];
			for ( int i = 0; i < tamanoAct; i++)
			{
				elementos[i] = copia[i];
			} 
		}	
		elementos[tamanoAct] = dato;
		tamanoAct++;	
	}

}
