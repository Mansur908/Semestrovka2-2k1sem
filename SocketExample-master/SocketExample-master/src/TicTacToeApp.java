import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeApp extends Application {

    private String name;
    private boolean playable = true;
    private boolean turnX = true;
    private Tile[][] board = new Tile[3][3];
    private ListView listView = new ListView();
    private TextField textField = new TextField();
    private TextField textField1 = new TextField();
    private Button button = new Button();
    private Button button1 = new Button();
    List<String> list = FXCollections.observableArrayList();
    private Stage stage;

    private Pane root = new Pane();
    private Pane root1 = new Pane();

    private Parent contentFirst() {
        root1.setPrefSize(400, 400);
        textField1.setTranslateX(100);
        textField1.setTranslateY(200);
        button1.setTranslateX(300);
        button1.setTranslateY(200);
        button1.setText("Enter");
        button1.setOnAction(this::nameAction);
        root1.getChildren().add(textField1);
        root1.getChildren().add(button1);

        return root1;
    }

    private Parent contentSecond() {
        root.setPrefSize(800, 600);
        listView.setTranslateX(470);
        listView.setTranslateY(50);
        listView.setPrefWidth(290);
        root.getChildren().add(listView);
        textField.setTranslateX(500);
        textField.setTranslateY(500);
        root.getChildren().add(textField);
        button.setTranslateX(680);
        button.setTranslateY(500);
        button.setText("Send");
        button.setOnAction(this::msgAction);
        root.getChildren().add(button);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Tile tile = new Tile();
                tile.setTranslateX(j * 100 + 100);
                tile.setTranslateY(i * 100 + 100);

                root.getChildren().add(tile);

                board[j][i] = tile;
            }
        }
        return root;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(contentFirst()));
        stage = primaryStage;
        primaryStage.show();
    }

    public void msgAction(javafx.event.ActionEvent action) {
        String text = textField.getText();
        list.add(name+": "+text);
        System.out.println(text);
        listView.setItems((ObservableList) list);
    }

    public void nameAction(javafx.event.ActionEvent action) {
        String text = textField1.getText();
        name = text;
        System.out.println(name);
        this.stage.close();
        Stage stage = new Stage();
        stage.setScene(new Scene(contentSecond()));
        stage.show();

    }

    private class Tile extends StackPane {
        private Text text = new Text();

        public Tile() {
            Rectangle border = new Rectangle(100, 100);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(72));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {

                if (event.getButton() == MouseButton.PRIMARY) {
                    if (!turnX)
                        return;

                    drawX();
                    turnX = false;
                }
            });
        }

        public double getCenterX() {
            return getTranslateX();
        }

        public double getCenterY() {
            return getTranslateY();
        }

        public String getValue() {
            return text.getText();
        }

        private void drawX() {
            text.setText("X");
        }

        private void drawO() {
            text.setText("O");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

}