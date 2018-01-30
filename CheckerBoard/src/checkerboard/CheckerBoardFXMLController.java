/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checkerboard;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author hunterginther
 */
public class CheckerBoardFXMLController implements Initializable, Startable {
    
    private int numRows = 8;
    private int numCols = 8;
    private Color lightColor = Color.RED;
    private Color darkColor = Color.BLACK;
    
    private Stage stage;
    
    @FXML
    private AnchorPane anchorPane;
    
    @FXML
    private VBox vbox;
    
    @FXML
    private MenuBar menuBar;

    @FXML private MenuItem grid16;
    @FXML private MenuItem grid10;
    @FXML private MenuItem grid8;
    @FXML private MenuItem grid3;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        
        ChangeListener<Number> lambdaChangeListener = (ObservableValue<? extends Number> observable, Number oldValue, final Number newValue) -> {
            refreshBoard();
        };
        this.stage.widthProperty().addListener(lambdaChangeListener);
        this.stage.heightProperty().addListener(lambdaChangeListener);
        
        refreshBoard();
    }
    
    private void refreshBoard() {
        double boardWidth = this.vbox.getWidth();
        double boardHeight = this.vbox.getHeight() - menuBar.getHeight();
        
        CheckerBoard cb = new CheckerBoard(numRows, numCols, boardWidth, boardHeight, lightColor, darkColor);
        AnchorPane ap = cb.build();
         
        anchorPane.getChildren().clear();
        
        anchorPane.getChildren().addAll(ap);
    }
    
    @FXML 
    private void handleGridSize(ActionEvent event) {
        MenuItem sizeSelection = (MenuItem)(event.getSource()); 
        
        switch (sizeSelection.getId()) {
            case "grid16":
                numRows = 16;
                numCols = 16;
                break;
            case "grid10":
                numRows = 10;
                numCols = 10;
                break;
            case "grid3":
                numRows = 3;
                numCols = 3;
                break;
            default:
                numRows = 8;
                numCols = 8;
                break;
        }
        
        refreshBoard();
    }
    
    @FXML
    private void handleColorDefault(ActionEvent event) {
        lightColor = Color.RED;
        darkColor = Color.BLACK;
        
        refreshBoard();
    }
    
    @FXML
    private void handleColorBlue(ActionEvent event) {
        lightColor = Color.SKYBLUE;
        darkColor = Color.DARKBLUE;
        
        refreshBoard();
    }
}
