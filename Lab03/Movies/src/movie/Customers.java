/**
 * <h1>JavaFX Movie Rental System</h1>
 * <p>This is the customer data
 *@author  Joyce Bodo
 *@version 1.0
 */
package movie;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;



public class Customers extends Application {
    @Override
    public void start(Stage stage) {
        try {

            Text text1 = new Text("Name:");
            Text text2 = new Text("phone:");
            Text text3 = new Text("Email");
            Text text4 = new Text("Registered");


            TextField textfield1= new TextField();
            TextField textfield2= new TextField();
            TextField textfield3= new TextField();

            ComboBox combobox= new ComboBox();

            Button button1= new Button("save Customer");
            Button button2= new Button("remove Customer");

            GridPane gridepane= new GridPane();
            gridepane.setMaxSize(600,400);
            gridepane.setPadding(new Insets (10,10,10,10));
            gridepane.setVgap(10);
            gridepane.setHgap(10);

            gridepane.setAlignment(Pos.CENTER);
            gridepane.add(text1,0,0);
            gridepane.add(textfield1,1,0);
            gridepane.add(button1,0,0);

            gridepane.add(text2,0,0);
            gridepane.add(textfield2,1,0);

            gridepane.add(text3,0,0);
            gridepane.add(textfield3,1,0);


            gridepane.add(button1,0,0);

            gridepane.add(text4,0,0);
            gridepane.add(combobox,0,0);


            gridepane.add(button2,0,0);



            button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;-fx-font-size:13pt");
            button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;-fx-font-size:13pt");

            text1.setStyle("-fx-font:normal bold 20px 'serif'");
            text2.setStyle("-fx-font:normal bold 20px 'serif'");
            gridepane.setStyle("-fx-background-color: BEIGE");


            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("movie.fxml").toExternalForm());
            stage.setTitle("Customers");
            stage.setScene(scene);
            stage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}