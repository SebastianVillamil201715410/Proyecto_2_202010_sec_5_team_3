package model.logic;


import java.util.Date;

public class Comparendo implements Comparable<Comparendo>{
	
	private int objectId;
	private Date fecha_hora;
	private String des_infrac;
	private String medio_dete;
	private String clase_vehi;
	private String tipo_servi;
	private String infraccion;
	private String localidad;

	private double latitud;
	private double longitud;
	
	public Comparendo(int objeId, Date fecha, String descripcion, String detencion, String claseVeh, String tipoSer, String codInfraccion, String localidadP, double lonP, double latP)
	{
		objectId = objeId;
		fecha_hora = fecha;
		des_infrac = descripcion;
		medio_dete = detencion;
		clase_vehi = claseVeh;
		tipo_servi = tipoSer;
		infraccion = codInfraccion;
		localidad = localidadP;
		longitud = lonP;
		latitud = latP;
	}
	
	public String darId()
	{
		return String.valueOf(objectId);
	}
	
	public String darFechaHora()
	{
		return String.valueOf(fecha_hora);
	}
	
	public String darDescripcion()
	{
		return String.valueOf(des_infrac);
	}
	
	public String darMedio()
	{
		return String.valueOf(medio_dete);
	}
	
	public String darClaseVehiculo()
	{
		return String.valueOf(clase_vehi);
	}
	
	public String darTipoServicio()
	{
		return String.valueOf(tipo_servi);
	}
	
	public String darInfraccion()
	{
		return String.valueOf(infraccion);
	}
	
	public String darLocalidad()
	{
		return String.valueOf(localidad);
	}
	
	public String darLatitudLongitud()
	{
		return String.valueOf(latitud) + String.valueOf(longitud);
	}
	
	public int compareTo(Comparendo o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public String toString() {
		return "Comparendo [OBJECTID=" + objectId + ", FECHA_HORA=" + fecha_hora + ", DES_INFRAC=" + des_infrac
				+ ", MEDIO_DETE=" + medio_dete + ", CLASE_VEHI=" + clase_vehi + ", TIPO_SERVI=" + tipo_servi
				+ ", INFRACCION=" + infraccion + ", LOCALIDAD=" + localidad + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}
	
}
