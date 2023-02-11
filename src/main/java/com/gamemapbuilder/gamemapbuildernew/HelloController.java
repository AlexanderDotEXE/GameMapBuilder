package com.gamemapbuilder.gamemapbuildernew;

import Tools.EventHandling;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import static Tools.Tools.getSubImage;

public class HelloController {
    public static Stage stage;
    public static Scene scene;
    private File file;
    FileChooser fileChooser = new FileChooser();
    Image[] mapTiles = new Image[48];
    Image selectedTile;
    @FXML
    private Label fileName;
    @FXML
    AnchorPane anchorPane;
    @FXML
    ImageView currentTile;
    Image placeholder;
    ImageView[][] drawBoardImages;
    EventHandling eventHandling;

    public HelloController() throws FileNotFoundException{
        //String file = String.valueOf(HelloApplication.class.getResource("transparent.png"));
        //String file = HelloApplication.class.getResource("transparent.png").toURI().toURL();
        //InputStream stream = new FileInputStream(HelloApplication.class.getResource("transparent.png").toExternalForm().substring(9));
        InputStream stream = getClass().getResourceAsStream("/transparent.png");
        placeholder = new Image(stream);
        drawBoardImages = new ImageView[26][14];
        //createDrawBoard();
    }

    @FXML
    protected void onSelectTileMap() throws FileNotFoundException {
        fileChooser.setTitle("Select Map Tiles...");
        file = fileChooser.showOpenDialog(stage);
        //creating the image object
        InputStream stream = new FileInputStream(file.getAbsolutePath());
        fileName.setText(file.getName());
        Image image = new Image(stream);
        importMapTiles(image);
        addMapToAP();
        createDrawBoard();
    }

    @FXML
    public void onFileDownload(){
        FileChooser downloadFile = new FileChooser();
        fileChooser.setTitle("Select where to Save Image...");

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = downloadFile.showSaveDialog(stage);
        Tools.Tools.downlaodFile(file.getAbsolutePath(),this.drawBoardImages, this.mapTiles);
    }

    private void importMapTiles(Image bigMap){
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                mapTiles[index] = getSubImage(bigMap,i * 32, j * 32, 32, 32);
            }

    }

    private void createDrawBoard(){
        for(int j = 0; j < 14; j++){
            for(int i = 0; i < 26; i++){
                drawBoardImages[i][j] = new ImageView(placeholder);
                drawBoardImages[i][j].setX(i * 30);
                drawBoardImages[i][j].setY(j * 30 + 180);
                drawBoardImages[i][j].setPreserveRatio(false);
                drawBoardImages[i][j].setFitWidth(30);
                drawBoardImages[i][j].setFitHeight(30);
                drawBoardImages[i][j].setId("x"+i+"y"+j);
                drawBoardImages[i][j].setOnMouseClicked(tileClicked);
                drawBoardImages[i][j].setPickOnBounds(true);
                drawBoardImages[i][j].setOnMouseDragged(tileDragged);
                anchorPane.getChildren().add(drawBoardImages[i][j]);
            }
        }
    }



    public void addMapToAP(){
        for (int j = 0; j < 4; j++)
            for (int i = 0; i < 12; i++) {
                int index = j * 12 + i;
                ImageView imageView = new ImageView(mapTiles[index]);
                imageView.setX(i * 32);
                imageView.setY(14 + j * 32);
                imageView.setId(String.valueOf(index));
                imageView.setOnMouseClicked(imageClicked);
                imageView.setPickOnBounds(true);
                anchorPane.getChildren().add(imageView);
            }
    }

    private javafx.event.EventHandler<MouseEvent> tileDragged =  new javafx.event.EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            String source = mouseEvent.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked
            int y = source.indexOf('y');
            String xS = source.substring(1,y);
            String yS = source.substring(y + 1);
            if(selectedTile != null){
                drawBoardImages[Integer.parseInt(xS)][Integer.parseInt(yS)].setImage(selectedTile);
            }
            System.out.println("DrawBoard: " + xS + " " + yS);
        }
    };

    private javafx.event.EventHandler<MouseEvent> imageClicked = new javafx.event.EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            String source = e.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked

            selectedTile = mapTiles[Integer.parseInt(source)];
            currentTile.setImage(selectedTile);
            System.out.println("Tile Number: " + source);
        }
    };

    private javafx.event.EventHandler<MouseEvent> tileClicked = new javafx.event.EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            String source = e.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked
            int y = source.indexOf('y');
            String xS = source.substring(1,y);
            String yS = source.substring(y + 1);
            if(selectedTile != null){
                drawBoardImages[Integer.parseInt(xS)][Integer.parseInt(yS)].setImage(selectedTile);
            }
            System.out.println("DrawBoard: " + xS + " " + yS);
        }
    };

}