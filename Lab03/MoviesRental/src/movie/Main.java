/**
 * <h1>JavaFX Movie Rental System</h1>
 * <p>This programs helps in storing and recording all the movies a vendor may have
 *@author  Joyce Bodo
 *@version 1.0
 */
package movie;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class Main extends Application {
    /**
     * This method is used to display the entire GUI on a stage
     * @Override
     */
    @Override
    public void start(Stage stage) {
        //creating label Name
        Text text1 = new Text("Name:");
        //creating label Registered
        Text text2 = new Text("Registered:");

        //Creating Text Filed for Name
        TextField textField1 = new TextField();

        ComboBox comboBox = new ComboBox();

        //Creating Buttons
        Button button1 = new Button("Save");
        Button button2 = new Button("Remove");

        //Creating a Grid Pane
        GridPane gridPane = new GridPane();
        gridPane.setMinSize (600, 400);
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        //Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(10);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        //Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(button1, 1, 1);

        gridPane.add(text2, 0, 2);
        gridPane.add(comboBox, 1, 2);
        gridPane.add(button2, 1, 3);

        //Styling nodes
        button1.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        button2.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");

        text1.setStyle("-fx-font: normal bold 20px 'serif' ");
        text2.setStyle("-fx-font: normal bold 20px 'serif' ");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        //Creating a scene object
        Scene scene = new Scene(gridPane);

        //Setting title to the Stage
        stage.setTitle("Movie Library System");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
