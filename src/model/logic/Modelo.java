package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.JsonParseException;
import com.google.gson.*;

import model.data_structures.AlgoritmosOrdenamiento;
import model.data_structures.ArbolNegroRojo;
import model.data_structures.ArregloDinamico;
import model.data_structures.HeapSort;
import model.data_structures.IArregloDinamico;
import model.data_structures.IteradorLista;
import model.data_structures.Lista;
import model.data_structures.MaxColaCP;
import model.data_structures.Nodo;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.logic.Comparendo;

/**
 * Definicion del modelo del mundo
 * @param <V>
 *
 */
public class Modelo{

	//ATRIBUTOS

	//public static String PATH = "./data/comparendos_dei_2018_small.geojson";
	//public static String PATH = "./data/comparendos_dei_2018.geojson";
	//public static String PATH = "./data/Comparendos_DEI_2018_Bogot�_D.C_50000_.geojson";
	public static String PATH = "./data/Comparendos_DEI_2018_Bogot�_D.C_small_50000_sorted.geojson";


	public Lista<Comparendo> lista;	//Lista enlazada de comparendos

	private Stack<Comparendo> pila;	//Pila de comparendos

	private Queue<Comparendo> cola;	//Cola de comparendos

	private IArregloDinamico<Comparendo> datos;

	//private ArbolNegroRojo<Comparendo,V> arbolrn;

	//METODOS

	public Modelo()
	{
		lista = new Lista<Comparendo>();
		pila = new Stack<Comparendo>();
		cola = new Queue<Comparendo>();
		datos = new ArregloDinamico();
	}

	/**
	 * 
	 * @return
	 */
	public ArregloDinamico<Comparendo> cargarDatos() 
	{
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();

			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Comparendo mayor = null;
			
			for(JsonElement e: e2) 
			{
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				String[] s1 = s.split("T");
				String[] s2 = s1[0].split("-");
				String s3 = s2[0]+"/"+s2[1]+"/"+s2[2]+" "+s1[1];
				Date FECHA_HORA = parser.parse(s3); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETECCION").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHICULO").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVICIO").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRACCION").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();
				String MUNICIPIO = e.getAsJsonObject().get("properties").getAsJsonObject().get("MUNICIPIO").getAsString();
				
				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, DES_INFRAC, LOCALIDAD, MUNICIPIO, longitud, latitud);
				lista.agregar(c);
				cola.enqueue(c);
			
			}
			e2 =null;
			return (ArregloDinamico<Comparendo>) datos;
		} 
		catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}	
	}
	
	public int darTamano()
	{
		return lista.darTamano();
	}

	public Comparendo primero()
	{
		return lista.darElemento(0);
	}

	/**
	 * Mezcla los elementos de la lista 
	 * @param list
	 */
	public static void shuffle(List<Comparendo> list) 
	{
		Random random = new Random(); 
		int count = list.size() - 1;

		for (int i = count; i > 1; i--) 
		{
			Collections.swap(list, i, random.nextInt(i));
		} 
	}

	public String cargaDeDatosHashLinearProbing()
	{
		String s ="nada";

		ArregloDinamico<Comparendo> comparendos = new ArregloDinamico<Comparendo>();
		ArregloDinamico<String> comparendosLlaves = new ArregloDinamico<String>();
		ArregloDinamico<String> tresPartes = new ArregloDinamico<String>();

		SimpleDateFormat formatoFechas = new SimpleDateFormat("yyyy/mm/dd");

		for (int i = 0; i < (cargarDatos()).darTamano(); i++) 
		{
			Comparendo x = ( cargarDatos()).get(i);
			String fecha = formatoFechas.format(x.darFechaHora()); 
			((List<Comparendo>) comparendos).add(x);

			tresPartes.add(comparendos.get(i).darFechaHora());
			tresPartes.add(comparendos.get(i).darClaseVehiculo());
			tresPartes.add(comparendos.get(i).darInfraccion());
		}


		tresPartes.add(comparendos.get(1).darClaseVehiculo());
		comparendosLlaves.add(comparendos.get(1).darClaseVehiculo());
		comparendos.get(1);



		return s;
	}

	public IArregloDinamico<String> comparendosConInfraccion(String pFecha) throws java.text.ParseException
	{
		ArregloDinamico<Comparendo> comparendos = new ArregloDinamico<Comparendo>();
		ArregloDinamico<String> infracciones = new ArregloDinamico<String>();
		String s = "No se encontro comparendo en la localidad";
		SimpleDateFormat formatoFechas= new SimpleDateFormat("yyyy/mm/dd");
		for(int i = 0; i<(cargarDatos()).darTamano() ; i++)
		{
			Comparendo x = cargarDatos().get(i);
			String fecha = formatoFechas.format(x.darFechaHora());
			if(fecha.equals(pFecha));
			{
				comparendos.add(cargarDatos().get(i));

			}
		}
		AlgoritmosOrdenamiento.quicksort(comparendos,0,comparendos.darTamano()-1);
		for(int i = 0; i < comparendos.darTamano(); i ++)
		{
			s = "El comparendo tiene como objectid:" + cargarDatos().get(i).darId() + ", la fecha del comparendo es:" + cargarDatos().get(i).darFechaHora() +", la infraccion del comparendo es"
					+ cargarDatos().get(i).darInfraccion() +", la case del vehiculo es:" +cargarDatos().get(i).darClaseVehiculo() + 
					", el tipo de servicio es:" + cargarDatos().get(i).darTipoServicio() +"y la localidad del comparendo es en:" +cargarDatos().get(i).darLocalidad();
			infracciones.add(s);
		}

		return infracciones;
	}


	//-------------------------------------------
	// Requerimientos
	//-------------------------------------------


	//============
	//PARTE A 
	//============

	/**
	 * 
	 * @param m
	 * @return
	 */
	public Queue<Comparendo> Requerimiento1A (int m)
	{
		Queue<Comparendo> x = new Queue<Comparendo>();
		int numero = 0;
		int num=0;
		Comparendo c = cola.getFirst();
		AlgoritmosOrdenamiento a = new AlgoritmosOrdenamiento();
		for(int i=0; i< m; i++)
		{
			for(int j=0; j <datos.darTamano(); j++)
			{
				a.mergeSort(datos);
				if(c.darTipoServicio().equalsIgnoreCase("Publico"))
				{
					x.enqueue(c);
				}
			}
			if(c != null)
			{
				a.mergeSort(datos);
			}
			for(int j=0; j <datos.darTamano(); j++)
			{
				a.mergeSort(datos);
				if(c.darTipoServicio().equalsIgnoreCase("Oficial"))
				{
					x.enqueue(c);
				}
			}
			if(c != null)
			{
				a.mergeSort(datos);
			}
			for(int j=0; j <datos.darTamano(); j++)
			{
				a.mergeSort(datos);
				if(c.darTipoServicio().equalsIgnoreCase("Particular"))
				{
					x.enqueue(c);
				}
			}
			if(c != null)
			{
				a.mergeSort(datos);
			}
		}
		return x;

	}

	/**
	 * 
	 * @param m
	 * @param s
	 * @return
	 */
	public Queue<Comparendo> Requerimiento2A (int m, String s)
	{
		Queue<Comparendo> x= new Queue<Comparendo>();
		Comparendo c = cola.getFirst();
		AlgoritmosOrdenamiento a = new AlgoritmosOrdenamiento();
		a.mergeSort(datos);
		if( m>12 || m<1)
		{
			System.out.println("Ingreso un numero de mes invalido");
		}
		if(!s.equalsIgnoreCase("L") || !s.equalsIgnoreCase("M") || !s.equalsIgnoreCase("I") || !s.equalsIgnoreCase("J") || !s.equalsIgnoreCase("V") || !s.equalsIgnoreCase("S") || !s.equalsIgnoreCase("D"))
		{
			System.out.println("Ingreso un dia de la semana invalido");
		}
		for(int i=0; i<datos.darTamano() ; i++)
		{
			if(m>9)
			{	
				if(c.darFechaHoraDate().equals("yyyy/"+ m +"/dd-HH:MM:ss"));
				{
					if(s.equalsIgnoreCase("D"))
					{
						if(c.darFechaHoraDate().getDay()==0)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("L"))
					{
						if(c.darFechaHoraDate().getDay()==1)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("M"))
					{
						if(c.darFechaHoraDate().getDay()==2)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("I"))
					{
						if(c.darFechaHoraDate().getDay()==3)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("J"))
					{
						if(c.darFechaHoraDate().getDay()==4)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("V"))
					{
						if(c.darFechaHoraDate().getDay()==5)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("S"))
					{
						if(c.darFechaHoraDate().getDay()==6)
							x.enqueue(c);
					}
				}
			}
			else
			{
				if(c.darFechaHoraDate().equals("yyyy/M"+ m +"/dd-HH:MM:ss"));
				{
					if(s.equalsIgnoreCase("D"))
					{
						if(c.darFechaHoraDate().getDay()==0)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("L"))
					{
						if(c.darFechaHoraDate().getDay()==1)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("M"))
					{
						if(c.darFechaHoraDate().getDay()==2)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("I"))
					{
						if(c.darFechaHoraDate().getDay()==3)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("J"))
					{
						if(c.darFechaHoraDate().getDay()==4)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("V"))
					{
						if(c.darFechaHoraDate().getDay()==5)
							x.enqueue(c);
					}
					if(s.equalsIgnoreCase("S"))
					{
						if(c.darFechaHoraDate().getDay()==6)
							x.enqueue(c);
					}
				}
			}
		}

		return x;
	}

	/**
	 * 
	 * @param aa
	 * @param bb
	 * @param localidad
	 * @return
	 */
	public Queue<Comparendo> Requerimiento3A (String aa , String bb, String localidad)
	{
		Queue<Comparendo> x = new Queue<Comparendo>();
		Comparendo c = cola.getFirst();
		AlgoritmosOrdenamiento a = new AlgoritmosOrdenamiento();
		a.mergeSort(datos);
		
		String fechaA = aa.replace("\\", "");
		SimpleDateFormat sdfA = new SimpleDateFormat("yyyy/MM/dd-HH:MM:ss");
		Date FechaDateA = null;
		try {
			FechaDateA = sdfA.parse(fechaA);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String fechaB = bb.replace("\\", "");
		SimpleDateFormat sdfB = new SimpleDateFormat("yyyy/MM/dd-HH:MM:ss");
		Date FechaDateB = null;
		try {
			FechaDateB = sdfB.parse(fechaB);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i=0; i<datos.darTamano(); i++)
		{
			if(c.darFechaHoraDate().after(FechaDateA))
				if(c.darFechaHoraDate().before(FechaDateB))
					if(c.darLocalidad().equalsIgnoreCase(localidad))
						x.enqueue(c);
		}
		return x;
	}

	//====================
	// Parte B
	//====================

	/**
	 * 
	 * @param M
	 * @return
	 * @throws ParseException
	 */
	public Queue<Comparendo> Requerimiento1B (String M) throws ParseException
	{
		Queue<Comparendo> x = new Queue<Comparendo>();
		Comparendo c = cola.getFirst();
		AlgoritmosOrdenamiento a = new AlgoritmosOrdenamiento();
		a.mergeSort(datos);
		double latitudOf = Double.parseDouble(c.darLatitud());
		double longitudOf= Double.parseDouble(c.darLongitud());
		double LatFinal =4.647586;
		double LonFinal =-74.078122;

		double dis=distance(latitudOf, longitudOf, LatFinal, LonFinal);

		int subM = Integer.parseInt(M);

		boolean b =false;
		int count=0;

		for (int i = 0; i <cargarDatos().darTamano() && !b ; i++) 
		{
			Comparendo comparar = cargarDatos().get(i);
			double LatInicial = Double.parseDouble(comparar.darLatitud());
			double LongInicial = Double.parseDouble(comparar.darLongitud());
			if(distance(LatInicial, LongInicial, LatFinal, LonFinal)<=dis)
			{
				dis=distance(LatInicial, LongInicial, LatFinal, LonFinal);
				if(count!=subM)
				{
					count++;
					x.enqueue(comparar);
				}
				else
				{
					b=true;
				}
			}
		}
		return x;
	}

	public  double distance(double LatInicial, double LongInicial,
			double LatFinal, double LonFinal) 
	{
		double radioTierra = 6371;
		double dLat  = Math.toRadians((LatFinal - LatInicial));
		double dLong = Math.toRadians((LonFinal - LongInicial));

		LatInicial = Math.toRadians(LatInicial);
		LatFinal   = Math.toRadians(LatFinal);

		double a = x(dLat) + Math.cos(LatInicial) * Math.cos(LatFinal) * x(dLong);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

		return radioTierra * c; // <-- d
	}

	public double x(double val)
	{
		return Math.pow(Math.sin(val/2), 2);
	}


	/**
	 * 
	 * @param pMedioDeteccion
	 * @param pClaseVehiculo
	 * @param pTipoServicio
	 * @param pLocali
	 * @return
	 * @throws ParseException
	 */
	public Queue<Comparendo> Requerimiento2B(String pMedioDeteccion, String pClaseVehiculo, String pTipoServicio, String pLocali) throws ParseException
	{
		Queue<Comparendo> x = new Queue<Comparendo>();
		Comparendo c = cola.getFirst();
		AlgoritmosOrdenamiento a = new AlgoritmosOrdenamiento();
		a.mergeSort(datos);
		for (int i = 0; i < cargarDatos().darTamano(); i++)
		{
			if(c.darMedio().equals(pMedioDeteccion) && c.darClaseVehiculo().equals(pClaseVehiculo) && c.darTipoServicio().equals(pTipoServicio) && c.darLocalidad().equals(pLocali))
				x.enqueue(c);
		}	
		return x;
	}


	/**
	 * 
	 * @param pVehiculo
	 * @param pLimite_bajo
	 * @param pLimite_alto
	 * @return
	 * @throws ParseException
	 */
	public Queue<Comparendo> Requerimiento3B(String pVehiculo, String pLimite_bajo, String pLimite_alto) throws ParseException
	{
		Queue<Comparendo> x = new Queue<Comparendo>();
		Comparendo c = cola.getFirst();
		AlgoritmosOrdenamiento a = new AlgoritmosOrdenamiento();
		a.mergeSort(datos);
		for (int i = 0; i < cargarDatos().darTamano(); i++) 
		{
			double latitudOf = Double.parseDouble(c.darLatitud());
			double latB = Double.parseDouble(pLimite_bajo);
			double latA = Double.parseDouble(pLimite_alto);
			if(c.darClaseVehiculo().equals(pVehiculo))
			{
				if(latitudOf >= latB && latitudOf<=latA)
				{
					x.enqueue(c);;
				}
			}
		}
		return x;
	}


	//===============
	// Parte C
	//===============

	/**
	 * 
	 * @param rango
	 * @return
	 */
	public Lista Requerimiento1C(int rango)
	{
		AlgoritmosOrdenamiento<Comparendo> orden = new AlgoritmosOrdenamiento<Comparendo>();

		orden.shellSort(lista); // Ordena la lista para mejorar la busqueda

		try
		{
			Lista respuesta = new Lista(); 
			int contador = 0;

			SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
			Date dateM = parser.parse("2018/01/01");
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(dateM);
			calendario.add(Calendar.DATE, 6);
			Date maxima = calendario.getTime(); // Parsea las fechas para poder compararlas 


			for(Comparable comparado : lista) // Recorre los comparendos ordenados 
			{
				System.out.println("Rango de fechas		| Comparendos durante el a�o  \n ---------------------------------------");
				
				if(comparado!=null) 
				{
					Date actual = ((Comparendo) comparado).darFechaHoraDate(); // 

					if(actual.compareTo(maxima)>0)
					{
						respuesta.agregar(contador); // Agrega a la tabla 
						contador = 0; // Reinicia la  variable contador 
						calendario.setTime(maxima); 
						calendario.add(Calendar.DATE, 1);
						dateM = calendario.getTime();
						calendario.add(Calendar.DATE, rango-1);
						maxima = calendario.getTime();
					}

					if(actual.compareTo(dateM)>=0 && actual.compareTo(maxima)<=0)
					{
						contador++;
					}
				}
			}
			return respuesta;
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 */
	public void requerimiento2C()
	{
		Lista respuesta = new Lista<Comparendo>();
		int costoTotal = 0;
		SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd");
		Date dateM;
		String asteriscos = "";
		String hashtag = "";
		
		
		try {
			
			dateM = parser.parse("2018/01/01");
			Calendar calendario = Calendar.getInstance();
			calendario.setTime(dateM);
			calendario.add(Calendar.DATE, 6);
			Date maxima = calendario.getTime(); // Parsea las fechas para poder compararlas
			
			for (int i = 0; i < lista.darTamano(); i++) //recorre para la suma de costo total
			{
				Comparendo actual = lista.darElemento(i);
				
				if(actual.darFechaHoraDate().compareTo(dateM)== 0) //si el comparendo actual es de 2018
				{
					if(actual.darInfraccion().equalsIgnoreCase("SERA INMOVILIZADO"))
					{
						costoTotal += 400;
					}
					else if(actual.darInfraccion().equalsIgnoreCase("LICENCIA DE CONDUCCI�N"))
					{
						costoTotal += 40;
					}
					else
					{
						costoTotal += 4;
					}
				}
			}
			System.out.println("El costo total de Infracciones de 2018 es: " + costoTotal + "\n"); //primera parte dar el costo total
			
			// Histograma ASCII 
			
			int enEspera = 0; 
			int procesados = 0;
			String fechaImprimir = ""; // fecha para incluir en tabla ASCII 
			
			System.out.println("Fecha		| Comparendos Procesados	***");
			System.out.println("			| Comparendos en espera 	***");
			
			for (int i = 0; i < cola.getSize(); i++) 
			{
				Comparendo actual = cola.dequeue();
				
				if(actual.darFechaHoraDate().after(maxima)) // compara con la fecha actual 
				{
					enEspera++; //No ha sido procesado
					hashtag += "*";
				}
				else
				{
					procesados++; //ya fue procesado
					asteriscos += "#";
				}
				fechaImprimir = actual.darFechaHora();
			}
			
			System.out.println(fechaImprimir+"    | " + asteriscos);
			System.out.println("          | "+ hashtag);
			
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	

}

