package es.cipfpbatoi.damb.ad.ventanas;

import java.util.List;

import es.cipfpbatoi.damb.ad.modelo.Persona;
import es.cipfpbatoi.damb.ad.persistencia.IOPersonasDataStream;
import es.cipfpbatoi.damb.ad.persistencia.IOPersonasJSon;
import es.cipfpbatoi.damb.ad.persistencia.IOPersonasObjectStream;
import es.cipfpbatoi.damb.ad.persistencia.IOPersonasXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VentanaPrincipal {

	private Stage ventana;
	
	private VBox contenedorPrincipal;
	private TableView<Persona> tableViewPersonas;
	
	private TextField textFieldId;
	private TextField textFieldNombre;
	private TextField textFieldApellido1;
	private TextField textFieldApellido2;
	private TextField textFieldAltura;
	private TextField textFieldPeso;
	
	private Button buttonAnyadir;
	
	private Button buttonCargarDOS;
	private Button buttonGuardarDOS;
	
	private Button buttonCargarOOS;
	private Button buttonGuardarOOS;
	
	private Button buttonCargarXML;
	private Button buttonGuardarXML;
	
	private Button buttonCargarJSon;
	private Button buttonGuardarJSon;
	
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
		
		this.pintarFormulario();
		this.pintarTablaPersonas();
	}

	private void pintarFormulario() {
		
		HBox contenedorHorizontal = new HBox();
		this.contenedorPrincipal.getChildren().add(contenedorHorizontal);
		
		contenedorHorizontal.setSpacing(10);
		
		Label label = new Label("Id:");
		contenedorHorizontal.getChildren().add(label);
		
		label.setPrefWidth(80);
		
		this.textFieldId = new TextField();
		contenedorHorizontal.getChildren().add(this.textFieldId);
		
		contenedorHorizontal = new HBox();
		this.contenedorPrincipal.getChildren().add(contenedorHorizontal);
		
		contenedorHorizontal.setSpacing(10);
		
		label = new Label("Nombre:");
		contenedorHorizontal.getChildren().add(label);
		
		label.setPrefWidth(80);
		
		this.textFieldNombre = new TextField();
		contenedorHorizontal.getChildren().add(this.textFieldNombre);
		
		this.textFieldNombre.setPrefWidth(150);
		
		label = new Label("Apellido 1:");
		contenedorHorizontal.getChildren().add(label);
		
		label.setPrefWidth(80);
		
		this.textFieldApellido1 = new TextField();
		contenedorHorizontal.getChildren().add(this.textFieldApellido1);
		
		this.textFieldApellido1.setPrefWidth(150);
		
		label = new Label("Apellido 2:");
		contenedorHorizontal.getChildren().add(label);
		
		label.setPrefWidth(80);
		
		this.textFieldApellido2 = new TextField();
		contenedorHorizontal.getChildren().add(this.textFieldApellido2);
		
		this.textFieldApellido2.setPrefWidth(150);
		
		contenedorHorizontal = new HBox();
		this.contenedorPrincipal.getChildren().add(contenedorHorizontal);
		
		contenedorHorizontal.setSpacing(10);
		
		label = new Label("Estatura:");
		contenedorHorizontal.getChildren().add(label);
		
		label.setPrefWidth(80);
		
		this.textFieldAltura = new TextField();
		contenedorHorizontal.getChildren().add(this.textFieldAltura);
		
		this.textFieldAltura.setPrefWidth(150);
		
		label = new Label("Peso:");
		contenedorHorizontal.getChildren().add(label);
		
		label.setPrefWidth(80);
		
		this.textFieldPeso = new TextField();
		contenedorHorizontal.getChildren().add(this.textFieldPeso);
		
		this.textFieldPeso.setPrefWidth(150);
		
		this.buttonAnyadir = new Button("Añadir persona");
		this.contenedorPrincipal.getChildren().add(this.buttonAnyadir);
		
		this.buttonAnyadir.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonAnyadirPersona();
		});
	}

	private void pintarTablaPersonas() {
		this.tableViewPersonas = new TableView<Persona>();
		this.contenedorPrincipal.getChildren().add(this.tableViewPersonas);

		TableColumn<Persona, Integer> columnaId = new TableColumn<>("Id");
		this.tableViewPersonas.getColumns().add(columnaId);
		
		columnaId.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn<Persona, String> columnaNombre = new TableColumn<>("Nombre");
		this.tableViewPersonas.getColumns().add(columnaNombre);
		
		columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		
		TableColumn<Persona, String> columnaApellido1 = new TableColumn<>("Apellido 1");
		this.tableViewPersonas.getColumns().add(columnaApellido1);
		
		columnaApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
		
		TableColumn<Persona, String> columnaApellido2 = new TableColumn<>("Apellido 2");
		this.tableViewPersonas.getColumns().add(columnaApellido2);
		
		columnaApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
		
		TableColumn<Persona, Integer> columnaAltura = new TableColumn<>("Estatura");
		this.tableViewPersonas.getColumns().add(columnaAltura);
		
		columnaAltura.setCellValueFactory(new PropertyValueFactory<>("altura"));
		
		TableColumn<Persona, Double> columnaPeso = new TableColumn<>("Peso");
		this.tableViewPersonas.getColumns().add(columnaPeso);
		
		columnaPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));

		HBox contenedorHorizontal = new HBox();
		this.contenedorPrincipal.getChildren().add(contenedorHorizontal);
		
		contenedorHorizontal.setSpacing(10);
		
		this.buttonGuardarOOS = new Button("Guardar OOS");
		contenedorHorizontal.getChildren().add(this.buttonGuardarOOS);
		
		this.buttonGuardarOOS.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonGuardarOOS();
		});
		
		this.buttonCargarOOS = new Button("Cargar OOS");
		contenedorHorizontal.getChildren().add(this.buttonCargarOOS);
		
		this.buttonCargarOOS.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonCargarOOS();
		});
		
		this.buttonGuardarDOS = new Button("Guardar DOS");
		contenedorHorizontal.getChildren().add(this.buttonGuardarDOS);
		
		this.buttonGuardarDOS.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonGuardarDOS();
		});
		
		this.buttonCargarDOS = new Button("Cargar DOS");
		contenedorHorizontal.getChildren().add(this.buttonCargarDOS);
		
		this.buttonCargarDOS.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonCargarDOS();
		});
		
		this.buttonGuardarJSon = new Button("Guardar JSon");
		contenedorHorizontal.getChildren().add(this.buttonGuardarJSon);
		
		this.buttonGuardarJSon.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonGuardarJSon();
		});
		
		this.buttonCargarJSon = new Button("Cargar JSon");
		contenedorHorizontal.getChildren().add(this.buttonCargarJSon);
		
		this.buttonCargarJSon.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonCargarJSon();
		});
		
		this.buttonGuardarXML = new Button("Guardar XML");
		contenedorHorizontal.getChildren().add(this.buttonGuardarXML);
		
		this.buttonGuardarXML.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonGuardarXML();
		});
		
		this.buttonCargarXML = new Button("Cargar XML");
		contenedorHorizontal.getChildren().add(this.buttonCargarXML);
		
		this.buttonCargarXML.setOnAction(e -> {
			VentanaPrincipal.this.onClickButtonCargarXML();
		});
	}

	private void onClickButtonAnyadirPersona() {
		Persona persona = new Persona();
		persona.setId(Integer.parseInt(this.textFieldId.getText()));
		persona.setNombre(this.textFieldNombre.getText());
		persona.setApellido1(this.textFieldApellido1.getText());
		persona.setApellido2(this.textFieldApellido2.getText());
		persona.setAltura(Integer.parseInt(this.textFieldAltura.getText()));
		persona.setPeso(Double.parseDouble(this.textFieldPeso.getText()));
		
		this.tableViewPersonas.getItems().add(persona);
	}
	
	private void onClickButtonGuardarOOS() {
		List<Persona> personas = this.tableViewPersonas.getItems();
		IOPersonasObjectStream.save(personas);
	}
	
	private void onClickButtonCargarOOS() {
		List<Persona> personas = IOPersonasObjectStream.load();
		this.tableViewPersonas.getItems().addAll(personas);
	}

	private void onClickButtonGuardarDOS() {
		List<Persona> personas = this.tableViewPersonas.getItems();
		IOPersonasDataStream.save(personas);
	}

	private void onClickButtonCargarDOS() {
		List<Persona> personas = IOPersonasDataStream.load();
		this.tableViewPersonas.getItems().addAll(personas);
	}

	private void onClickButtonGuardarJSon() {
		List<Persona> personas = this.tableViewPersonas.getItems();
		IOPersonasJSon.save(personas);
	}

	private void onClickButtonCargarJSon() {
		List<Persona> personas = IOPersonasJSon.load();
		this.tableViewPersonas.getItems().addAll(personas);
	}

	private void onClickButtonGuardarXML() {
		List<Persona> personas = this.tableViewPersonas.getItems();
		IOPersonasXML.save(personas);
	}

	private void onClickButtonCargarXML() {
		List<Persona> personas = IOPersonasXML.load();
		this.tableViewPersonas.getItems().addAll(personas);
	}
	
}
