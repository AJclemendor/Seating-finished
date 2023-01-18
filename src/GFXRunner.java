
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.*;
import java.io.IOException;

public class GFXRunner extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Optmial Seating");

        // Create the registration form grid pane
        GridPane gridPane = createGUIPane();
        // Add UI controls to the registration form grid pane
        addUIControls(gridPane);
        // Create a scene with registration form grid pane as the root node
        Scene scene = new Scene(gridPane, 800, 500);
        // Set the scene in primary stage
        primaryStage.setScene(scene);

        primaryStage.show();
    }


    private GridPane createGUIPane() {
        // Instantiate a new Grid Pane
        GridPane gridPane = new GridPane();

        // Position the pane at the center of the screen, both vertically and horizontally
        gridPane.setAlignment(Pos.CENTER);

        // Set a padding of 20px on each side
        gridPane.setPadding(new Insets(40, 40, 40, 40));

        // Set the horizontal gap between columns
        gridPane.setHgap(10);

        // Set the vertical gap between rows
        gridPane.setVgap(10);

        // Add Column Constraints

        // columnOneConstraints will be applied to all the nodes placed in column one.
        ColumnConstraints columnOneConstraints = new ColumnConstraints(100, 100, Double.MAX_VALUE);
        columnOneConstraints.setHalignment(HPos.RIGHT);

        // columnTwoConstraints will be applied to all the nodes placed in column two.
        ColumnConstraints columnTwoConstrains = new ColumnConstraints(200,200, Double.MAX_VALUE);
        columnTwoConstrains.setHgrow(Priority.ALWAYS);

        gridPane.getColumnConstraints().addAll(columnOneConstraints, columnTwoConstrains);

        return gridPane;
    }

    private void addUIControls(GridPane gridPane) {
        // Add Header
        Label headerLabel = new Label("Optmial Seating");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        gridPane.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Input Path Label
        Label inputPathLabel = new Label("Input Path: ");
        gridPane.add(inputPathLabel, 0,1);

        // Add Input Path Text Field
        TextField inputPathField = new TextField();
        inputPathField.setPrefHeight(40);
        gridPane.add(inputPathField, 1,1);



        // Add Export Path Label
        Label exportPathLabel = new Label("Export Path: ");
        gridPane.add(exportPathLabel, 0, 2);

        // Add Export Path Text Field
        TextField exportPathField = new TextField();
        exportPathField.setPrefHeight(40);
        gridPane.add(exportPathField, 1, 2);


        // Add intensity Label
        Label intensityLabel = new Label("Intensity 1-9 ");
        gridPane.add(intensityLabel, 0,3);

        // Add intensity Text Field
        TextField intensityField = new TextField();
        intensityField.setPrefHeight(40);
        gridPane.add(intensityField, 1,3);

        // Add weight Label
        Label weightLabel = new Label("Row weight 3-10");
        gridPane.add(weightLabel, 0,4);

        // Add weight Text Field
        TextField weightField = new TextField();
        weightField.setPrefHeight(40);
        gridPane.add(weightField, 1,4);

        // Add rows Label
        Label rowLabel = new Label("Rows ");
        gridPane.add(rowLabel, 0,5);

        // Add rows Text Field
        TextField rowField = new TextField();
        rowField.setPrefHeight(40);
        gridPane.add(rowField, 1,5);

        // Add rows Label
        Label colLabel = new Label("Cols ");
        gridPane.add(colLabel, 0,6);

        // Add rows Text Field
        TextField colField = new TextField();
        colField.setPrefHeight(40);
        gridPane.add(colField, 1,6);



        // Add Submit Button
        Button submitButton = new Button("Run");
        submitButton.setPrefHeight(40);
        submitButton.setDefaultButton(true);
        submitButton.setPrefWidth(100);
        gridPane.add(submitButton, 0, 7, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(inputPathField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your input path");
                    return;
                }
                if(exportPathField.getText().isEmpty()) {
                    showAlert(Alert.AlertType.ERROR, gridPane.getScene().getWindow(), "Form Error!", "Please enter your export path");
                    return;
                }


                try {
                    Seating.SeatingRunner(inputPathField.getText(), exportPathField.getText(), intensityField.getText(), weightField.getText(), rowField.getText(), colField.getText());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                showAlert(Alert.AlertType.CONFIRMATION, gridPane.getScene().getWindow(), "Complete -- add details aj", "You\'ll find the arrangement at " + exportPathField.getText() + "Data written to CSV file successfully!\n" +
                        "If you feel the chart was not good try increasing the intensity number or using a custom \n" +
                        " row weight the higher the weight the more the algo values students in the same row,\n" +
                        " 0 = no extra row value");
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}