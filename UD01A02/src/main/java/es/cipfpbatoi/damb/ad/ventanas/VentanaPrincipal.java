package es.cipfpbatoi.damb.ad.ventanas;

import java.io.File;

import es.cipfpbatoi.damb.ad.procesador.rutas.ConfiguracionBusqueda;
import es.cipfpbatoi.damb.ad.procesador.rutas.ConfiguracionBusquedaReader;
import es.cipfpbatoi.damb.ad.procesador.rutas.ConfiguracionBusquedaWritter;
import es.cipfpbatoi.damb.ad.procesador.rutas.ProcesadorRutas;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class VentanaPrincipal {

	private Stage ventana;
	
	private VBox contenedorPrincipal;
	
	private TextField textFieldRuta;
	private Button buttonSeleccionarDirectorio;
	
	private CheckBox checkBuscarRecursivamente;
	private CheckBox checkMostrarArchivosOcultos;
	
	private TextField textFieldFiltroNombre;
	
	private Button buttonProcesarRuta;
	private TextArea textAreaFicherosEncontrados;

	private ConfiguracionBusqueda configuracionBusqueda;
	

	public VentanaPrincipal(Stage stage) {
		this.ventana = stage;
		this.configuracionBusqueda = ConfiguracionBusquedaReader.read();
	}

	public void pintarVentana() {
		this.ventana.setTitle("JavaFX App");

		this.contenedorPrincipal = new VBox();
		Scene scene = new Scene(this.contenedorPrincipal, 800, 600);
		
		this.contenedorPrincipal.setPadding(new Insets(10, 10, 10, 10));
		this.contenedorPrincipal.setSpacing(10);

		this.ventana.setScene(scene);
		this.ventana.show();

		this.addSeleccionRuta();
		this.addProcesamiento();
	}

	private void addSeleccionRuta() {
		
		HBox contenedorRuta = new HBox();
		this.contenedorPrincipal.getChildren().add(contenedorRuta);
		
		contenedorRuta.setSpacing(10);
		
		this.textFieldRuta = new TextField();
		contenedorRuta.getChildren().add(this.textFieldRuta);
		
		this.textFieldRuta.setPrefWidth(500);
		this.textFieldRuta.setEditable(false);
		
		this.textFieldRuta.setText(this.configuracionBusqueda.getRutaInicial());
		
		this.buttonSeleccionarDirectorio = new Button("Selecciona directorio");
		contenedorRuta.getChildren().add(this.buttonSeleccionarDirectorio);
		
		this.buttonSeleccionarDirectorio.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonSeleccionarDirectorio();
		});
		
	}
	
	private void addProcesamiento() {
		
		HBox contenedorCheckBoxes = new HBox();
		this.contenedorPrincipal.getChildren().add(contenedorCheckBoxes);
		
		contenedorCheckBoxes.setSpacing(10);
		
		this.checkBuscarRecursivamente = new CheckBox("Buscar dentro de los directorios");
		contenedorCheckBoxes.getChildren().add(this.checkBuscarRecursivamente);
		
		this.checkBuscarRecursivamente.setSelected(this.configuracionBusqueda.isBuscarRecursivamente());
		
		this.checkMostrarArchivosOcultos = new CheckBox("Mostrar archivos ocultos");
		contenedorCheckBoxes.getChildren().add(this.checkMostrarArchivosOcultos);
		
		this.checkMostrarArchivosOcultos.setSelected(this.configuracionBusqueda.isMostrarArchivosOcultos());
		
		HBox contenedorFiltros = new HBox();
		this.contenedorPrincipal.getChildren().add(contenedorFiltros);
		
		contenedorFiltros.setSpacing(10);
		
		Label label = new Label("El nombre contiene: ");
		contenedorFiltros.getChildren().add(label);
		
		this.textFieldFiltroNombre = new TextField();
		contenedorFiltros.getChildren().add(this.textFieldFiltroNombre);
		
		this.textFieldFiltroNombre.setPrefWidth(350);
		this.textFieldFiltroNombre.setText(this.configuracionBusqueda.getFiltroNombre());
		
		this.buttonProcesarRuta = new Button("Buscar ficheros");
		this.contenedorPrincipal.getChildren().add(this.buttonProcesarRuta);
		
		this.textAreaFicherosEncontrados = new TextArea();
		this.contenedorPrincipal.getChildren().add(this.textAreaFicherosEncontrados);
		
		this.textAreaFicherosEncontrados.setEditable(false);
		this.textAreaFicherosEncontrados.setText("Esperando instrucciones.\n");
		
		this.buttonProcesarRuta.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonProcesarRuta();
		});
	}

	private void onClickButtonSeleccionarDirectorio() {
		File rutaInicial = new File(this.textFieldRuta.getText());
		
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setInitialDirectory(rutaInicial);

		File selectedDirectory = directoryChooser.showDialog(this.ventana);
		if(selectedDirectory != null) {
			this.textFieldRuta.setText(selectedDirectory.getAbsolutePath());
		}
	}

	private void onClickButtonProcesarRuta() {
		this.configuracionBusqueda.setRutaInicial(this.textFieldRuta.getText());
		this.configuracionBusqueda.setFiltroNombre(this.textFieldFiltroNombre.getText());
		this.configuracionBusqueda.setBuscarRecursivamente(this.checkBuscarRecursivamente.isSelected());
		this.configuracionBusqueda.setMostrarArchivosOcultos(this.checkMostrarArchivosOcultos.isSelected());
		
		ConfiguracionBusquedaWritter.save(configuracionBusqueda);
		
		StringBuilder resultadoProceso = ProcesadorRutas.procesarRuta(configuracionBusqueda);
		
		this.textAreaFicherosEncontrados.appendText(resultadoProceso.toString());
	}
}
