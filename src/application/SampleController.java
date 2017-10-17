package application;

import java.io.*;
import java.util.ArrayList;

import com.jfoenix.controls.*;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

	public class SampleController {
		
		String nombre = "urls.txt";
		BufferedWriter bw = null;
		
		BufferedReader br = null;
		
	    @FXML
	    private JFXButton btnExcel;
	    
	    @FXML
	    private TableView<StringProperty> tablaWeb;
	    @FXML
	    private TableColumn<StringProperty, String> colum;

	    @FXML
	    private JFXButton btnWord;

	    @FXML
	    private JFXButton btnPower;

	    @FXML
	    private JFXTextField textNavegar;

	    @FXML
	    private JFXButton btnNavegar;
	    
	    @FXML
	    private JFXTextArea textArea;
	    
	    private main main;
	    
	    
	    	    
	    @FXML
	    void excel(MouseEvent event) {
	    	try {
				new ProcessBuilder("C:\\Program Files\\Microsoft Office\\Office16\\EXCEL.exe").start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void word(MouseEvent event) {
	    	try {
				new ProcessBuilder("C:\\Program Files\\Microsoft Office\\Office16\\WINWORD.exe").start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void Power(MouseEvent event) {
	    	try {
	    		
				new ProcessBuilder("C:\\Program Files\\Microsoft Office\\Office16\\POWERPNT.exe").start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    @FXML
	    void navegar(MouseEvent event) {
	    	  try {
				Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + textNavegar.getText());
			} catch (IOException e) {
				e.printStackTrace();
			}
	    	escribirUrls();

	    }
	    
		 @FXML
	    private void initialize() {
	        // Initialize the table with the one column.
			 colum.setCellValueFactory(
	                cellData -> cellData.getValue());

	        // Listen for selection changes and show the person details when changed.
			 tablaWeb.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
					try {
						Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + (newValue.get()));
					} catch (IOException e) {
						e.printStackTrace();
					}
				});						
    	} 

		private void escribirUrls() {
			boolean esta = false;
	    	  try { 
				bw = new BufferedWriter(new FileWriter(nombre,true));
				for (int i = 0; i < main.getArrayUrls().size(); i++) {
					if (colum.getCellData(i).equals(textNavegar.getText()) ) {
						esta = true;
					}
				}
				if (!esta) {
					bw.write(textNavegar.getText());
					bw.newLine();
					main.getArrayUrls().add(new SimpleStringProperty(textNavegar.getText()));
				}					

			}catch (IOException e) {
				e.printStackTrace();
			}finally {
				if (bw != null) {
					try {
						bw.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		public void inicializarTabla(main main) {
			this.main = main;

	        // Add observable list data to the table
	        tablaWeb.setItems(main.getArrayUrls());
			
		}

}
