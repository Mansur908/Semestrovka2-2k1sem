package firstSocketExample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientSomthing extends Application {

    private String name;
    private String sign;
    private Tile[][] board = new Tile[3][3];
    private ListView listView = new ListView();
    private ListView listLable = new ListView();
    private TextField textField = new TextField();
    private TextField textField1 = new TextField();
    private Button button = new Button();
    private Button button1 = new Button();
    private Label label = new Label();
    private Label nameLabel = new Label();
    List<String> list = FXCollections.observableArrayList();
    private Stage stage1;
    private String msg = "";
    private boolean isEnd = false;
    private boolean isStart = false;

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
        label.setTranslateX(140);
        label.setTranslateY(170);
        label.setText("Введите имя");
        root1.getChildren().add(label);
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
        listLable.setTranslateX(180);
        listLable.setTranslateY(55);
        listLable.setPrefWidth(140);
        listLable.setPrefHeight(25);
        if (sign.equals("X")) {
            setLable("Ждите второго игрока");
        }
        root.getChildren().add(listLable);
        nameLabel.setTranslateX(200);
        nameLabel.setTranslateY(30);
        if (sign.equals("X"))
            nameLabel.setText("Вы первый игрок");
        else
            nameLabel.setText("Вы второй игрок");
        root.getChildren().add(nameLabel);

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
        new ReadMsg().start();
        primaryStage.setScene(new Scene(contentFirst()));
        primaryStage.setOnCloseRequest(event -> downService());
        stage1 = primaryStage;
        primaryStage.show();
    }

    public void msgAction(javafx.event.ActionEvent action){
        String text = textField.getText();
        System.out.println(text);
        try {
            out.write(name+":"+text + "\n");
            out.flush();
        }
        catch (IOException e){
        }
        listView.setItems((ObservableList) list);
    }

    public void nameAction(javafx.event.ActionEvent action) {
        String text = textField1.getText();
        name = text;
        System.out.println(name);
        this.stage1.close();
        if (sign.equals("O")) {
            try {
                out.write("connected\n");
                out.flush();
            } catch (IOException e) {
            }
            setLable("       Ход противника");
            isStart = true;
        }
        Stage stage = new Stage();
        stage.setScene(new Scene(contentSecond()));
        stage.setOnCloseRequest(event -> downService());
        stage.show();

    }

    public void setLable(String str){
        List<String> l = FXCollections.observableArrayList();
        l.add(str);
        listLable.setItems((ObservableList) l);
    }


    private class Tile extends StackPane {

        private Text text = new Text();

        public String check(){
            if (this == board[0][0])
                return "A1";
            if (this == board[1][0])
                return "A2";
            if (this == board[2][0])
                return "A3";
            if (this == board[0][1])
                return "B1";
            if (this == board[1][1])
                return "B2";
            if (this == board[2][1])
                return "B3";
            if (this == board[0][2])
                return "C1";
            if (this == board[1][2])
                return "C2";
            if (this == board[2][2])
                return "C3";
            return "";
        }

        public boolean moveX(){
            int count = 0;
            for (Tile[] i : board){
                for (Tile j : i){
                    if (!j.text.getText().equals(""))
                        count++;
                }
            }
            if (count % 2 == 0)
                return true;
            else
                return false;
        }

        public Tile() {
            Rectangle border = new Rectangle(100, 100);
            border.setFill(null);
            border.setStroke(Color.BLACK);

            text.setFont(Font.font(72));

            setAlignment(Pos.CENTER);
            getChildren().addAll(border, text);

            setOnMouseClicked(event -> {

                if (event.getButton() == MouseButton.PRIMARY) {
                    if (isEnd)
                        return;
                    if (!isStart)
                        return;
                    if (!this.text.getText().equals(""))
                        return;
                    if (sign.equals("X")) {
                        if (!moveX()) {
                            return;
                        }
                        drawX();
                        try {
                            out.write(check()+"\n");
                            out.flush();
                        } catch (IOException e) {
                        }
                        setLable("  Ход противника");
                    }
                    else {
                        if (moveX()) {
                            return;
                        }
                        drawO();
                        try {
                            out.write(check()+"\n");
                            out.flush();
                        } catch (IOException e) {
                        }
                        setLable("  Ход противника");
                    }
                }
            });
        }

        private void drawX () {
            text.setText("X");
        }

        private void drawO() {
            text.setText("O");
        }
    }

    public static void main(String[] args) throws Exception {
        launch(args);
    }


    private String addr = "localhost";
    private int port = 8084;
    private Socket socket;
    private BufferedReader in;
    private BufferedWriter out;

    {
        try {
            socket = new Socket(addr, port);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doOnGet(String str){
        switch (str) {
            case ("connected"):
                setLable("             Ваш ход");
                isStart = true;
                break;
            case ("ничья"):
                    setLable("             Ничья");
                break;
            case ("X win"):
                isEnd = true;
                if (sign.equals("X"))
                    setLable("      Вы победили");
                else
                    setLable("      Вы проиграли");
                break;
            case ("O win"):
                isEnd = true;
                if (sign.equals("X"))
                    setLable("      Вы проиграли");
                else
                    setLable("      Вы победили");
                break;
            case ("A1"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[0][0].drawO();
                }else {
                    board[0][0].drawX();
                }
                break;
            case ("A2"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[1][0].drawO();
                }else {
                    board[1][0].drawX();
                }
                break;
            case ("A3"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[2][0].drawO();
                }else {
                    board[2][0].drawX();
                }
                break;
            case ("B1"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[0][1].drawO();
                }else {
                    board[0][1].drawX();
                }
                break;
            case ("B2"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[1][1].drawO();
                }else {
                    board[1][1].drawX();
                }
                break;
            case ("B3"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[2][1].drawO();
                }else {
                    board[2][1].drawX();
                }
                break;
            case ("C1"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[0][2].drawO();
                }else{
                    board[0][2].drawX();
                }
                break;
            case ("C2"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[1][2].drawO();
                }else {
                    board[1][2].drawX();
                }
                break;
            case ("C3"):
                setLable("             Ваш ход");
                if (sign.equals("X")) {
                    board[2][2].drawO();
                }else {
                    board[2][2].drawX();
                }
                break;
            case ("X"):
                sign = "X";
                break;
            case ("O"):
                sign = "O";
                break;
            default:
                list.add(str);
                listView.setItems((ObservableList) list);
        }
    }

    private void downService() {
        try {
            if (!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                System.exit(0);
            }
        } catch (IOException ignored) {}
    }

    private class ReadMsg extends Thread {
        @Override
        public void run() {
            String str;
            try {
                while (true) {
                    Thread.sleep(1000);
                    str = in.readLine();
                    String str1 = str;
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            doOnGet(str1);
                        }
                    });
                }
            } catch (IOException | InterruptedException e) {
                ClientSomthing.this.downService();
            }
        }
    }
}
