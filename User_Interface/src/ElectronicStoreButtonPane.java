import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class ElectronicStoreButtonPane extends Pane{
    private Button addCart, removeCart, completeSale,resetStore;
    public ElectronicStoreButtonPane(){
        Pane innerPane = new Pane();

        addCart = new Button("Add to Cart");
        addCart.setStyle("-fx-font: 14 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        addCart.relocate(210, 0);
        addCart.setPrefSize(130,40);

        removeCart = new Button("Remove from Cart");
        removeCart.setStyle("-fx-font: 14 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        removeCart.relocate(461, 0);
        removeCart.setPrefSize(160,40);

        completeSale = new Button("Complete Sale");
        completeSale.setStyle("-fx-font: 14 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        completeSale.relocate(620, 0);
        completeSale.setPrefSize(140,40);

        resetStore = new Button("Reset Store");
        resetStore.setStyle("-fx-font: 14 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        resetStore.relocate(0, 0);
        resetStore.setPrefSize(130,40);

        innerPane.getChildren().addAll(addCart, removeCart, completeSale,resetStore);

        getChildren().addAll(innerPane);//, titleLabel);
    }
    public Button getAddCart(){return addCart;}
    public Button getRemoveCart(){return removeCart;}
    public Button getCompleteSale(){return completeSale;}
    public Button getResetStore(){return resetStore;}




}
