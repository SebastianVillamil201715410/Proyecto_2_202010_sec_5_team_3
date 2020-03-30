	package model.data_structures;

import model.logic.Comparendo;

public class AlgoritmosOrdenamiento <T extends Comparable<T>>
	{
		private T[] auxiliar;

		public AlgoritmosOrdenamiento()
		{
			//
		}

		public void shellSort(IArregloDinamico<T> arreglo)
		{
			int N = arreglo.darTamano();
			int h = 1;
			while (h < N/3)
				h = 3*h + 1;
			while (h >= 1)
			{
				for(int i = h; i < N; i++)
				{
					for (int j = i; j >= h && arreglo.darElemento(j).compareTo(arreglo.darElemento(j - h)) < 0; j -= h)
						arreglo.exch(j, j - h);
				}
				h = h/3;
			}
		}

		public void mergeSort(IArregloDinamico<T> arreglo) 
		{
			auxiliar = 	((T[])new Comparable[arreglo.darTamano()]);
			sortM(arreglo, 0, arreglo.darTamano() - 1);
		}

		private void sortM(IArregloDinamico<T> arreglo, int lo, int hi) 
		{
			if (hi <= lo) 
				return; 
			int mid = lo + (hi - lo)/2;
			sortM(arreglo, lo, mid);
			sortM(arreglo, mid+1, hi);
			merge(arreglo, lo, mid, hi);
		}

		private void merge(IArregloDinamico<T> arreglo, int lo, int mid, int hi)
		{
			int i = lo;
			int j = mid+1;
			for (int k = lo; k <= hi; k++)
				auxiliar[k] = (T) arreglo.darElemento(k);
			for (int k = lo; k <= hi; k++)
			{
				if (i > mid) 
					arreglo.set(k, auxiliar[j++]);
				else if (j > hi ) 
					arreglo.set(k, auxiliar[i++]);
				else if (auxiliar[j].compareTo(auxiliar[i]) < 0) 
					arreglo.set(k, auxiliar[j++]);
				else 
					arreglo.set(k, auxiliar[i++]);
			}
		}

		public static void quicksortP (ArregloDinamico<Comparendo> lista1, int izq, int der)
		{
	        int i=izq;
	        int j=der;
	        int pivote=(i+j)/2;
	        do {
	            while (lista1.get(i).darInfraccion().compareToIgnoreCase(lista1.get(pivote).darInfraccion())<0){
	                i++;
	            }
	            while (lista1.get(j).darInfraccion().compareToIgnoreCase(lista1.get(pivote).darInfraccion())>0){
	                j--;
	            }
	            if (i<=j){
	                Comparendo aux=lista1.get(i);
	                lista1.set(i, lista1.get(j));;
	                lista1.set(j, aux);;
	                i++;
	                j--;
	            }
	        }while(i<=j);
	        if (izq<j){
	            quicksortP(lista1, izq, j);
	        }
	        if (i<der){
	            quicksortP(lista1, i, der);
	        }
	    }
		public static void quicksort(ArregloDinamico<Comparendo> m, int inferior, int superior) {

int izquierda, derecha;
Comparendo mitad;
Comparendo x;
izquierda = inferior;
derecha = superior;
mitad = m.get((izquierda + derecha) / 2);

do {
while (m.get(izquierda).darInfraccion().compareToIgnoreCase(mitad.darInfraccion()) < 0 && izquierda < superior) {
izquierda++;
}
while (mitad.darInfraccion().compareToIgnoreCase(m.get(derecha).darInfraccion()) < 0 && derecha > inferior) {
derecha--;
}

if (izquierda <= derecha) {

x = m.get(izquierda);
m.set(izquierda, m.get(derecha));;
m.set(derecha, x);

izquierda++;
derecha--;

}

} while (izquierda <= derecha);

if (inferior < derecha) {
quicksort(m, inferior, derecha);
}

if (izquierda < superior) {
quicksort(m, izquierda, superior);
}

}
		public static void quicksortFecha(ArregloDinamico<Comparendo> m, int inferior, int superior) {

			int izquierda, derecha;
			Comparendo mitad;
			Comparendo x;
			izquierda = inferior;
			derecha = superior;
			mitad = m.get((izquierda + derecha) / 2);

			do {
			while (m.get(izquierda).darInfraccion().compareToIgnoreCase(mitad.darInfraccion()) < 0 && izquierda < superior) {
			izquierda++;
			}
			while (mitad.darInfraccion().compareToIgnoreCase(m.get(derecha).darInfraccion()) < 0 && derecha > inferior) {
			derecha--;
			}

			if (izquierda <= derecha) {

			x = m.get(izquierda);
			m.set(izquierda, m.get(derecha));;
			m.set(derecha, x);

			izquierda++;
			derecha--;

			}

			} while (izquierda <= derecha);

			if (inferior < derecha) {
			quicksort(m, inferior, derecha);
			}

			if (izquierda < superior) {
			quicksort(m, izquierda, superior);
			}

			}
		private void sortQ(IArregloDinamico<T> arreglo, int lo, int hi)
		{
			if (hi <= lo) 
				return;
			int j = partition(arreglo, lo, hi);
			sortQ(arreglo, lo, j-1); 
			sortQ(arreglo, j+1, hi);
		}

		private int partition(IArregloDinamico<T> arreglo, int lo, int hi)
		{
			int i = lo;
			int j = hi+1;
			T piv = (T) arreglo.darElemento(lo);
			while (true)
			{
				while (arreglo.darElemento(++i).compareTo((String) piv) < 0)
					if (i == hi) 
						break;
				while (piv.compareTo((T) arreglo.darElemento(--j)) < 0)
					if (j == lo) 
						break;
				if (i >= j) 
					break; 
				arreglo.exch(i, j);
			}
			arreglo.exch(lo, j);
			return j;
		}
		public static void quicksortMenorAMayor(ArregloDinamico<Comparendo> m, int inferior, int superior) {

			int izquierda, derecha;
			Comparendo mitad;
			Comparendo x;
			izquierda = inferior;
			derecha = superior;
			mitad = m.get((izquierda + derecha) / 2);

			do {
				while (m.get(izquierda).darFechaHora().compareTo(mitad.darFechaHora()) < 0 && izquierda < superior) {
					izquierda++;
				}
				while (mitad.darFechaHora().compareTo(m.get(derecha).darFechaHora()) < 0 && derecha > inferior) {
					derecha--;
				}

				if (izquierda >= derecha) {

					x = m.get(izquierda);
					m.set(izquierda, m.get(derecha));;
					m.set(derecha, x);

					izquierda++;
					derecha--;

				}

			} while (izquierda >= derecha);

			if (inferior > derecha) {
				quicksortMenorAMayor(m, inferior, derecha);
			}

			if (izquierda > superior) {
				quicksortMenorAMayor(m, inferior, superior);
			}

	}
	}

