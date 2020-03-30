package model.logic;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import model.data_structures.ArregloDinamico;
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
 *
 */
public class Modelo {

	//ATRIBUTOS

	public static String PATH = "./data/comparendos_dei_2018_small.geojson";
	//public static String PATH = "./data/comparendos_dei_2018.geojson";

	public Lista<Comparendo> lista;	//Lista enlazada de comparendos

	private Stack<Comparendo> pila;	//Pila de comparendos

	private Queue<Comparendo> cola;	//Cola de comparendos
	
	private IArregloDinamico<Comparendo> datos;

	//METODOS

	public Modelo()
	{
		lista = new Lista<Comparendo>();
		pila = new Stack<Comparendo>();
		cola = new Queue<Comparendo>();
		datos = new ArregloDinamico();
	}

	public ArregloDinamico<Comparendo> cargarDatos() {

		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(PATH));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonArray e2 = elem.getAsJsonObject().get("features").getAsJsonArray();


			SimpleDateFormat parser=new SimpleDateFormat("yyyy/MM/dd");

			for(JsonElement e: e2) {
				int OBJECTID = e.getAsJsonObject().get("properties").getAsJsonObject().get("OBJECTID").getAsInt();

				String s = e.getAsJsonObject().get("properties").getAsJsonObject().get("FECHA_HORA").getAsString();	
				Date FECHA_HORA = parser.parse(s); 

				String MEDIO_DETE = e.getAsJsonObject().get("properties").getAsJsonObject().get("MEDIO_DETE").getAsString();
				String CLASE_VEHI = e.getAsJsonObject().get("properties").getAsJsonObject().get("CLASE_VEHI").getAsString();
				String TIPO_SERVI = e.getAsJsonObject().get("properties").getAsJsonObject().get("TIPO_SERVI").getAsString();
				String INFRACCION = e.getAsJsonObject().get("properties").getAsJsonObject().get("INFRACCION").getAsString();
				String DES_INFRAC = e.getAsJsonObject().get("properties").getAsJsonObject().get("DES_INFRAC").getAsString();	
				String LOCALIDAD = e.getAsJsonObject().get("properties").getAsJsonObject().get("LOCALIDAD").getAsString();

				double longitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(0).getAsDouble();

				double latitud = e.getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray()
						.get(1).getAsDouble();

				Comparendo c = new Comparendo(OBJECTID, FECHA_HORA, DES_INFRAC, MEDIO_DETE, CLASE_VEHI, TIPO_SERVI, INFRACCION, LOCALIDAD, longitud, latitud);

				lista.agregar(c);
			}

		} catch (FileNotFoundException | ParseException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return (ArregloDinamico<Comparendo>) datos;
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
}

