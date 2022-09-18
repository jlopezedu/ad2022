package es.cipfpbatoi.damb.ad.procesador.rutas;

public class ProcesadorRutas {
	
	public static StringBuilder procesarRuta(ConfiguracionBusqueda configuracionBusqueda) {
		
		StringBuilder resultadoProceso = new StringBuilder();
		
		resultadoProceso.append("Vamos a buscar en la ruta: " + configuracionBusqueda.getRutaInicial() + "\n");
		resultadoProceso.append("Procesando directorio...\n");
		
		return resultadoProceso;
	}

}
