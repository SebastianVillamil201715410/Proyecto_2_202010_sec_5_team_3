package controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.data_structures.Lista;
import model.data_structures.Queue;
import model.data_structures.Stack;
import model.logic.Comparendo;
import model.logic.Modelo;
import view.View;

public class Controller {


	/* Instancia del Modelo*/
	private Modelo modelo;

	/* Instancia de la Vista*/
	private View view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller ()
	{
		view = new View();
		modelo = new Modelo();
	}

	public void run() 
	{
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String respuesta = "";
		Lista<Comparendo> lista = new Lista<Comparendo>();
		Stack<Comparendo> pila = new Stack<Comparendo>();
		Queue<Comparendo> cola = new Queue<Comparendo>();

		while( !fin ){
			view.printMenu();

			int option = lector.nextInt();
			switch(option){
			case 1:
				view.printMessage("--------- \nIniciar carga de datos: ");
				modelo = new Modelo();

				long start = System.currentTimeMillis();
				modelo.cargarDatos();
				long end = System.currentTimeMillis();
				view.printMessage("Tiempo de carga (seg): " + (end-start)/1000.0);
				view.printMessage("Datos cargados: " + modelo.darTamano()+ "\n");
				view.printMessage("Primer dato cargado: " +modelo.primero() + "\n"); //imprime el primer elemento de la cola
				break;

			case 2:
				view.printMessage("--------- \nReq A1: Ingrese cantidad de comparendos a analizar: ");
				int numUno = Integer.parseInt(lector.next());
				Queue resp = modelo.Requerimiento1A(numUno);
				for (int i = 0; i < modelo.darTamano(); i++) {
					Comparendo act = (Comparendo) resp.dequeue();
					view.printMessage(act.darId() + act.darTipoServicio() + act.darInfraccion() + act.darFechaHora() + act.darClaseVehiculo());
				}
				break;

			case 3:
				view.printMessage("--------- \nReq A2: Ingrese numero del mes (1-12): ");
				int num = Integer.parseInt(lector.next());
				view.printMessage("--------- \nIngrese dia de la semana (L,M,I,J,V,S,D): ");
				String string = lector.next();
				Queue respUno = modelo.Requerimiento2A(num, string);
				for (int i = 0; i < respUno.getSize(); i++) 
				{
					Comparendo act = (Comparendo) respUno.dequeue();
					view.printMessage(act.darId() + act.darTipoServicio() + act.darInfraccion() + act.darFechaHora() + act.darClaseVehiculo());
				}
				break;
				
			case 4: 
				view.printMessage("--------- \nReq A3: Ingrese primera fecha: ");	
				Date un = lector.next();
				view.printMessage("--------- \nIngrese segunda fecha: ");
				Date dos = lector.next();
				view.printMessage("Ingrese nombre de la localidad: ");
				String tres = lector.next();
				Queue respi = modelo.Requerimiento3A(un, dos, tres);
				for (int i = 0; i < respi.getSize(); i++) 
				{
					Comparendo act = (Comparendo) respUno.dequeue();
					view.printMessage(act.darId() + act.darTipoServicio() + act.darInfraccion() + act.darFechaHora() + act.darClaseVehiculo());
				}
				break;
				
			case 5:
				view.printMessage("--------- \nReq B1: Ingrese cantidad de comparendos a buscar: ");
				String numB = lector.next();
				Queue respB = modelo.Requerimiento1B(numB);
				for (int i = 0; i < modelo.darTamano(); i++) {
					Comparendo act = (Comparendo) resp.dequeue();
					view.printMessage(act.darId() + act.darTipoServicio() + act.darInfraccion() + act.darFechaHora() + act.darClaseVehiculo());
				}
				break;
			case 6:
				view.printMessage("--------- \nReq B2: Ingrese medio de deteccion: ");
				String bUno = lector.next();
				view.printMessage("Ingrese clase del vehiculo: ");
				String bDos = lector.next();
				view.printMessage("Ingrese el tipo de servicio: " );
				String bTres = lector.next();
				view.printMessage("Ingrese la localidad: ");
				String bCua = lector.next();
				Queue respo = modelo.Requerimiento2B(bUno, bDos, bTres, bCua);
				for (int i = 0; i < respo.getSize(); i++) 
				{
					Comparendo act = (Comparendo) respUno.dequeue();
					view.printMessage(act.darId() + act.darTipoServicio() + act.darInfraccion() + act.darFechaHora() + act.darClaseVehiculo());
				}
				break;

			case 7:
				view.printMessage("--------- \nReq B3: Ingrese vehiculo: ");
				String cUno = lector.next();
				view.printMessage("Ingrese latitud baja: ");
				String cDos = lector.next();
				view.printMessage("Ingrese latitud alta: " );
				String cTres = lector.next();
				Queue respq = modelo.Requerimiento3B(cUno, cDos, cTres);
				for (int i = 0; i < respq.getSize(); i++) 
				{
					Comparendo act = (Comparendo) respUno.dequeue();
					view.printMessage(act.darId() + act.darTipoServicio() + act.darInfraccion() + act.darFechaHora() + act.darClaseVehiculo());
				}
				break;
				
			case 8: 
				view.printMessage("--------- \nReq C1: Ingrese el numero maximo de dias: ");
				int nume = Integer.parseInt(lector.next());
				Lista respy = modelo.Requerimiento1C(nume);
				
			case 9: 
				view.printMessage("--------- \nReq C2: ");
				modelo.requerimiento2C(); // imprime el costo total y la tabla ASCII
			
			case 10: 
				view.printMessage("--------- \n Hasta pronto !! \n---------"); 
				lector.close();
				fin = true;
				break;
				
			default: 
				view.printMessage("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}	
}
