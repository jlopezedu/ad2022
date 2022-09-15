package es.cipfpbatoi.damb.ad.ventanas;

import java.io.File;

import es.cipfpbatoi.damb.ad.procesador.rutas.ProcesadorRutas;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
	
	private Button buttonProcesarRuta;
	private TextArea textAreaFicherosEncontrados;
	

	public VentanaPrincipal(Stage stage) {
		this.ventana = stage;
	}

	public void pintarVentana() {
		this.ventana.setTitle("JavaFX App");

		this.contenedorPrincipal = new VBox();
		Scene scene = new Scene(this.contenedorPrincipal, 800, 600);
		
		this.contenedorPrincipal.setPadding(new Insets(10, 10, 10, 10));
		this.contenedorPrincipal.setSpacing(10);

		this.ventana.setScene(scene);
		this.ventana.show();

		this.addBotonSeleccionarDir();
		this.addTextareaMensajes();
	}

	private void addBotonSeleccionarDir() {
		
		HBox contenedorRuta = new HBox();
		this.contenedorPrincipal.getChildren().add(contenedorRuta);
		
		contenedorRuta.setSpacing(10);
		
		this.textFieldRuta = new TextField();
		contenedorRuta.getChildren().add(this.textFieldRuta);
		
		this.textFieldRuta.setPrefWidth(500);
		this.textFieldRuta.setEditable(false);
		
		File rutaInicial = new File(".");
		this.textFieldRuta.setText(rutaInicial.getAbsolutePath());
		
		this.buttonSeleccionarDirectorio = new Button("Selecciona directorio");
		contenedorRuta.getChildren().add(this.buttonSeleccionarDirectorio);
		
		this.buttonSeleccionarDirectorio.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonSeleccionarDirectorio();
		});
		
	}
	
	private void addTextareaMensajes() {
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
		String ruta = this.textFieldRuta.getText();
		StringBuilder resultadoProceso = ProcesadorRutas.procesarRuta(ruta);
		
		this.textAreaFicherosEncontrados.appendText(resultadoProceso.toString());
	}
}
