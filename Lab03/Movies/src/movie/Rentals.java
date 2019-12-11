/**
 * <h1>JavaFX Movie Rental System</h1>
 * <p>This shows the movies rented out
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



public class Rentals extends Application {
    @Override
    public void start(Stage stage) {
        try {

            Text text1 = new Text("Customers:");
            Text text2 = new Text("Genre:");
            Text text3 = new Text("Movies");
            Text text4 = new Text("Borrowed");
            Text text5 = new Text("Returned");



            ComboBox combobox1= new ComboBox();
            ComboBox combobox2= new ComboBox();
            ComboBox combobox3= new ComboBox();
            ComboBox combobox4= new ComboBox();
            ComboBox combobox5= new ComboBox();


            Button button1= new Button("save Rentals");
            Button button2= new Button("remove Rentals");

            GridPane gridepane= new GridPane();
            gridepane.setMaxSize(400,400);
            gridepane.setPadding(new Insets (10,10,10,10));
            gridepane.setVgap(10);
            gridepane.setHgap(10);

            gridepane.setAlignment(Pos.CENTER);
            gridepane.add(text1,0,0);
            gridepane.add(combobox1,0,0);

            gridepane.add(text2,0,0);
            gridepane.add(combobox2,0,0);

            gridepane.add(text3,0,0);
            gridepane.add(combobox3,0,0);


            gridepane.add(button1,0,0);

            gridepane.add(text4,0,0);
            gridepane.add(combobox4,0,0);

            gridepane.add(button2,0,0);

            gridepane.add(text5,0,0);
            gridepane.add(combobox5,0,0);


            button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;-fx-font-size:13pt");
            button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;-fx-font-size:13pt");

            text1.setStyle("-fx-font:normal bold 20px 'serif'");
            text2.setStyle("-fx-font:normal bold 20px 'serif'");
            gridepane.setStyle("-fx-background-color: BEIGE");


            BorderPane root = new BorderPane();
            Scene scene = new Scene(root,400,400);
            scene.getStylesheets().add(getClass().getResource("movie.fxml").toExternalForm());
            stage.setTitle("RENTALS");
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