package es.cipfpbatoi.damb.ad.procesador.rutas;

public class ConfiguracionBusqueda {
	
	private String rutaInicial;
	private String filtroNombre;
	
	private boolean buscarRecursivamente;
	private boolean mostrarArchivosOcultos;
	
	public ConfiguracionBusqueda() {
		super();
	}
	
	public String getRutaInicial() {
		return this.rutaInicial;
	}
	public void setRutaInicial(String rutaInicial) {
		this.rutaInicial = rutaInicial;
	}
	
	public String getFiltroNombre() {
		return this.filtroNombre;
	}
	public void setFiltroNombre(String filtroNombre) {
		this.filtroNombre = filtroNombre;
	}
	
	public boolean isBuscarRecursivamente() {
		return this.buscarRecursivamente;
	}
	public void setBuscarRecursivamente(boolean buscarRecursivamente) {
		this.buscarRecursivamente = buscarRecursivamente;
	}
	
	public boolean isMostrarArchivosOcultos() {
		return this.mostrarArchivosOcultos;
	}
	public void setMostrarArchivosOcultos(boolean mostrarArchivosOcultos) {
		this.mostrarArchivosOcultos = mostrarArchivosOcultos;
	}
}
