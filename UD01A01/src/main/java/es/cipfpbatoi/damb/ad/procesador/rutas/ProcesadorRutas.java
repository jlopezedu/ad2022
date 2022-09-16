package es.cipfpbatoi.damb.ad.procesador.rutas;

public class ProcesadorRutas {
	
	public static StringBuilder procesarRuta(String ruta) {
		
		StringBuilder resultadoProceso = new StringBuilder();
		
		resultadoProceso.append("Vamos a buscar en la ruta: " + ruta + "\n");
		resultadoProceso.append("Procesando directorio...\n");
		
		return resultadoProceso;
	}

}
