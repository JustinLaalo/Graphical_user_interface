import javafx.collections.FXCollections;
import javafx.event.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.util.*;

public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreView view;



    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView(model);




    }

    public void start(Stage primaryStage) {
        Pane aPane = new Pane();

        view.getStockList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update();

                                                  }
                                              });

        view.getCartList().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update();
            }
        });
        view.getButtonPane().getAddCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                handleAdd();

            }
        });
        view.getButtonPane().getRemoveCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                handleRemove();
            }
        });
        view.getButtonPane().getCompleteSale().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent){
                handleCompleteSale();

            }
        });
        view.getButtonPane().getResetStore().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent){
                handleReset();


                //update(getTotalCartPrice());

            }
        });
        primaryStage.setTitle("Electronic Store Application");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(view,800,400));
        primaryStage.show();
        view.update();

    }

    public static void main(String[] args) {
        launch(args);
    }


    public void handleReset(){
        model.reset();
        //model = ElectronicStore.createStore();
        view.update();
    }
    public void handleAdd(){
        model.add(view.getStockList().getSelectionModel().getSelectedIndex());
        model.totalAddingPrice();
        view.update();

    }
    public void handleCompleteSale(){
        model.completeSale();
        model.totalAddingPrice();
        view.update();
    }

    public void handleRemove() {
        int cartIndex = view.getCartList().getSelectionModel().getSelectedIndex();
        model.totalReductionPrice(cartIndex);
        model.removeFromCart(cartIndex);
        view.update();

    }






}