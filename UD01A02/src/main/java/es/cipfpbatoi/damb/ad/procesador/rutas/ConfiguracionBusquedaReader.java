package es.cipfpbatoi.damb.ad.procesador.rutas;

import java.io.File;

public class ConfiguracionBusquedaReader {

	public static ConfiguracionBusqueda read() {
		ConfiguracionBusqueda configuracionBusqueda = new ConfiguracionBusqueda();
		
		File f = new File(".");
		configuracionBusqueda.setRutaInicial(f.getAbsolutePath());
		
		configuracionBusqueda.setFiltroNombre("prueba");
		configuracionBusqueda.setMostrarArchivosOcultos(true);
		configuracionBusqueda.setBuscarRecursivamente(true);
		
		return configuracionBusqueda;
	}

}
