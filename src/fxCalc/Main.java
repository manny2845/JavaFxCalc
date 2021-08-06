package fxCalc;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class Main extends Application{

    Button [] numButtons;

    Insets insets = new Insets(5,5,5,5);

    TextField resultsTextField;

    HBox topRow,middleRow,lowerRow,bottomRow;

    Scene mainScene;

    String[] template = {"7","8","9","/",
            "4","5","6","*",
            "1","2","3","-",
            "0",".","=","+",

    };

    int num1;

    String currentOperation;



    @Override
    public void start(Stage primaryStage)throws Exception{

        primaryStage.setTitle("Javafx Calc");
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();

        GridPane mainPane = new GridPane();

        mainScene = new Scene(mainPane);


        resultsTextField = new TextField();
        resultsTextField.setPrefSize(100, 50);
        mainPane.setPadding(insets);
        mainPane.setConstraints(resultsTextField,0,0);

        //adds buttons 7-9 and division sign to top row and adds top row to

        topRow = new HBox(5);
        topRow.setAlignment(Pos.CENTER);
        topRow.setPadding(insets);
        numButtons = new Button[17];


        for(int x=0; x < 4; x++){
            numButtons[x] = new Button();
            numButtons[x].setText(template[x]);
            topRow.getChildren().addAll(numButtons[x]);


        }
        mainPane.setConstraints(topRow,0,1);

        //adds buttons 4-6 and multiplication sign to middle row and adds middle

        middleRow = new HBox(5);
        middleRow.setAlignment(Pos.CENTER);
        middleRow.setPadding(insets);
        for(int x=4; x < 8; x++){
            numButtons[x] = new Button();
            numButtons[x].setText(template[x]);
            middleRow.getChildren().addAll(numButtons[x]);
        }

        mainPane.setConstraints(middleRow,0,2);

        //adds 1-3 and adds minus sign to lowerRow and adds lower row to mainPane
        lowerRow = new HBox(5);
        lowerRow.setAlignment(Pos.CENTER);
        lowerRow.setPadding(insets);
        for(int x=8; x < 12; x++){
            numButtons[x] = new Button();
            numButtons[x].setText(template[x]);
            lowerRow.getChildren().addAll(numButtons[x]);
        }

        mainPane.setConstraints(lowerRow,0,3);

        //adds 0, . , = and + to the bottom row and adds the bottom row to the

        bottomRow = new HBox(5);
        bottomRow.setAlignment(Pos.CENTER);
        bottomRow.setPadding(insets);
        for(int x=12; x < 16; x++){
            numButtons[x] = new Button();
            numButtons[x].setText(template[x]);
            bottomRow.getChildren().addAll(numButtons[x]);
        }

        mainPane.setConstraints(bottomRow,0,4);


        //sets preffered size for all buttons and adds action listeners to each

        for(int x =0; x < 16; x++){

            numButtons[x].setPrefSize(50,30);
        }

        for (int x = 0; x < 14; x++) {
            // We need a final variable for the event handler
            final int index = x;
            if(x == 3 || x == 7 || x == 11) {
                // Skip the operation buttons, we won't hit 15 (+) here since the loop only goes to 13 (.)
                continue;
            }
            // This will create one unique event handler for each button.
            // Since we have the final index variable here we don't need to
            // iterate over the buttons to find the correct template position - we already know it
            numButtons[x].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String numOne = resultsTextField.getText();
                    resultsTextField.setText(numOne + template[index]);
                }
            });
        }

        // Add action handlers for the operation buttons
        for (int x : new int[]{3, 7, 11, 15}) {
            numButtons[x].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent actionEvent) {
                    String firstNum = resultsTextField.getText();
                    num1 = Integer.parseInt(firstNum);
                    resultsTextField.setText("");
                    currentOperation = ((Button) actionEvent.getSource()).getText();
                }
            });
        }

        // Add action handler for the equals button
        numButtons[14].setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent event) {
                String secondNum = resultsTextField.getText();
                int num2 = Integer.parseInt(secondNum);

                switch (currentOperation) {
                    case "+":
                        num1 = num1 + num2;
                        resultsTextField.setText(String.valueOf(num1));
                        break;
                    case "-":
                        num1 = num1 - num2;
                        resultsTextField.setText(String.valueOf(num1));
                        break;
                    case "*":
                        num1 = num1 * num2;
                        resultsTextField.setText(String.valueOf(num1));
                        break;
                    case "/":
                        num1 = num1 / num2;
                        resultsTextField.setText(String.valueOf(num1));
                        break;
                    default:
                        break;
                }
            }
        });


        mainPane.getChildren().addAll(resultsTextField,topRow
                ,middleRow,lowerRow,bottomRow);


        primaryStage.setScene(mainScene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }

}