package application;
	
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.fxml.FXMLLoader;


public class main extends Application {
	private AnchorPane root;

	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();
			//AnchorPane root = (AnchorPane)loader.load(getClass().getResource("Sample.fxml"));
			
			 loader.setLocation(main.class.getResource("Sample.fxml"));
	         root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
			
			//Scene scene = new Scene(root,600,400);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Práctica Final Juan Raúl");
			primaryStage.show();
			
			SampleController controller = loader.getController();
			leerUrls();
            controller.inicializarTabla(this);

		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//public 	ObservableList<String> arrayUrls = FXCollections.observableArrayList();
	private ObservableList<StringProperty> arrayUrls = FXCollections.observableArrayList();
	
	public void leerUrls() {
	
		String nombre = "urls.txt";
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(nombre));
			
			StringProperty linea = null;
			while((linea = new SimpleStringProperty(br.readLine())).getValue() !=  null){
				arrayUrls.add(linea);
		      }
		} catch (FileNotFoundException e) {
			System.out.println("Fichero no ha sido encontrado");
		} catch (IOException e) {
			System.out.println("Error IO: " + e.getMessage());
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	
	public ObservableList<StringProperty> getArrayUrls() {
		return arrayUrls;
	}
	
	public void setArrayUrls(ObservableList<StringProperty> arrayUrls) {
		this.arrayUrls = arrayUrls;
	}


	public static void main(String[] args) {
		launch(args);
	}
	
}