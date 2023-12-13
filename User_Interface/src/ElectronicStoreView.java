import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.HashMap;

public class ElectronicStoreView extends Pane {

    private ListView<String> popList, stockList, cartList;
    private TextField salesTextField, revenueTextField, amountTextField;
    private ElectronicStoreButtonPane buttonPane;
    private ElectronicStore model;
    private Label currentStock;


    public ElectronicStoreView(ElectronicStore iModel) {
        model = iModel;


        Label sales = new Label("# Sales:");
        sales.relocate(20, 50);
        sales.setStyle("-fx-font: 12 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        sales.setPrefSize(80, 30);

        Label revenue = new Label("Revenue:");
        revenue.relocate(15, 90);
        revenue.setStyle("-fx-font: 12 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        revenue.setPrefSize(80, 30);

        Label amount = new Label("$ / Sale:");
        amount.relocate(23, 130);
        amount.setStyle("-fx-font: 12 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        amount.setPrefSize(100, 30);

        Label storeSummary = new Label("Store Summary:");
        storeSummary.relocate(30, 20);
        storeSummary.setStyle("-fx-font: 14 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        storeSummary.setPrefSize(110, 30);

        Label storeStock = new Label("Store Stock:");
        storeStock.relocate(270, 20);
        storeStock.setStyle("-fx-font: 14 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        storeStock.setPrefSize(80, 30);

        currentStock = new Label("Current Cart:");
        currentStock.relocate(600, 20);
        currentStock.setStyle("-fx-font: 14 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        currentStock.setPrefSize(200, 30);

        Label popularItems = new Label("Most Popular Items:");
        popularItems.relocate(20, 170);
        popularItems.setStyle("-fx-font: 14 arial; -fx-base: rgb(230,230,230); -fx-text-fill: rgb(0,0,0);");
        popularItems.setPrefSize(130, 30);


        salesTextField = new TextField();
        salesTextField.relocate(70, 50);
        salesTextField.setPrefSize(90, 35);

        revenueTextField = new TextField();
        revenueTextField.relocate(70, 90);
        revenueTextField.setPrefSize(90, 35);

        amountTextField = new TextField();
        amountTextField.relocate(70, 130);
        amountTextField.setPrefSize(90, 35);


        popList = new ListView<String>();
        popList.relocate(10, 200);
        popList.setPrefSize(150, 150);
        getChildren().add(popList);

        stockList = new ListView<String>();
        stockList.relocate(170, 50);
        stockList.setPrefSize(300, 300);
        getChildren().add(stockList);

        cartList = new ListView<String>();
        cartList.relocate(480, 50);
        cartList.setPrefSize(300, 300);
        getChildren().add(cartList);

        buttonPane = new ElectronicStoreButtonPane();
        buttonPane.relocate(20, 353);
        buttonPane.setPrefSize(300, 30);

        getChildren().addAll(sales, revenue, amount, storeSummary, storeStock, currentStock, popularItems, salesTextField, revenueTextField, amountTextField, buttonPane);

    }


    public ElectronicStoreButtonPane getButtonPane() {
        return buttonPane;
    }


    public ListView<String> getStockList() {
        return stockList;
    }

    public ListView<String> getCartList() {
        return cartList;
    }


    public void update() {
        ArrayList<String> newProductList = new ArrayList<>();
        for (Product product : model.getStock()) {
            newProductList.add(product.toString());
        }
        stockList.setItems(FXCollections.observableArrayList(newProductList));


        ArrayList<String> currentProductList = new ArrayList<>();


        for (HashMap.Entry<Product, Integer> e : model.getCurrentCart().entrySet()) {
            currentProductList.add(String.format("%d x %s", e.getValue(), e.getKey()));

        }
        cartList.setItems(FXCollections.observableArrayList(currentProductList));
        currentStock.setText(String.format("Current Cart ($%.2f):", model.getTotalCartPrice()));

        ArrayList<String> popularProducts = new ArrayList<>();
        if (model.getTopThree().isEmpty()){
            for(int i = 0; i<3; i++){
                popularProducts.add(model.getStock()[i].toString());
            }

        }else{
            for (Product product : model.getTopThree()) {
                popularProducts.add(product.toString());

            }
        }
        popList.setItems(FXCollections.observableArrayList(popularProducts));


        if (model.getCurrentCart().isEmpty()) {
            buttonPane.getCompleteSale().setDisable(true);
        } else {
            buttonPane.getCompleteSale().setDisable(false);
        }
        if (stockList.getSelectionModel().getSelectedIndex() >= 0) {
                buttonPane.getAddCart().setDisable(false);
        }else {
            buttonPane.getAddCart().setDisable(true);
        }
        if (cartList.getSelectionModel().getSelectedIndex() >= 0) {
                    buttonPane.getRemoveCart().setDisable(false);
        }else {
            buttonPane.getRemoveCart().setDisable(true);
        }
        if (model.getNumberCompleteSales() <= 0) {
                    salesTextField.setText(String.valueOf(0));
                    revenueTextField.setText(String.valueOf(0.00));
                    amountTextField.setText("N/A");
        }else {
                    salesTextField.setText(String.valueOf(model.getNumberCompleteSales()));
                    revenueTextField.setText(String.valueOf(model.getRevenue()));
                    amountTextField.setText(String.valueOf(model.getAverageSale()));
        }


            }
        }


