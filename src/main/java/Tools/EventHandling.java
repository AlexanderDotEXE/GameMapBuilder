package Tools;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class EventHandling {
    public Image selectedTile;
    public ImageView[][] drawBoardImages;
    public Image[] mapTiles;
    public ImageView currentTile;

    public EventHandling(Image selectedTile,ImageView[][] drawBoardImages,Image[] mapTiles,ImageView currentTile){
        this.selectedTile = selectedTile;
        this.drawBoardImages = drawBoardImages;
        this.mapTiles = mapTiles;
        this.currentTile = currentTile;
    }

    public javafx.event.EventHandler<MouseEvent> tileDragged =  new javafx.event.EventHandler<MouseEvent>() {
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

    public javafx.event.EventHandler<MouseEvent> imageClicked = new javafx.event.EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent e) {
            String source = e.getPickResult().getIntersectedNode().getId(); //returns JUST the id of the object that was clicked

            selectedTile = mapTiles[Integer.parseInt(source)];
            currentTile.setImage(selectedTile);
            System.out.println("Tile Number: " + source);
        }
    };

    public javafx.event.EventHandler<MouseEvent> tileClicked = new javafx.event.EventHandler<MouseEvent>() {
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
