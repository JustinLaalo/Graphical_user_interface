//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.*;

public class ElectronicStore {
    public final int MAX_PRODUCTS = 10; //Maximum number of products the store can have
    private int curProducts;
    private String name;
    private Product[] stock; //Array to hold all products
    private double revenue;

    private HashMap<Product,Integer> currentCart;
    private double totalCartPrice;
    private int numberCompleteSales;
    private double averageSale;
    private ArrayList<Product> popularList,orderProducts,topThree;
    private HashSet<Product> currentProducts;



    public ElectronicStore(String initName) {
        revenue = 0.0;
        name = initName;
        stock = new Product[MAX_PRODUCTS];
        curProducts = 0;

        currentCart = new HashMap<>();
        totalCartPrice = 0;
        numberCompleteSales = 0;
        averageSale = 0;
        popularList = new ArrayList<>();
        currentProducts =  new HashSet<>();
        topThree = new ArrayList<>();


    }

    public String getName() {
        return name;
    }

    //Adds a product and returns true if there is space in the array
    //Returns false otherwise
    public boolean addProduct(Product newProduct) {
        if (curProducts < MAX_PRODUCTS) {
            stock[curProducts] = newProduct;

            curProducts++;
            return true;
        }
        return false;
    }


    public Product[] getStock(){
        Product[] products = new Product[curProducts];
        for (int i=0; i<curProducts; i++)
            products[i] = stock[i];
        return products;
    }


    public int getCurProducts() {
        return curProducts;
    }

    public void setCurProducts(int curProducts) {
        this.curProducts = curProducts;
    }

    public void setStock(Product p, Integer index ) {
        this.stock[index] = p;
    }

    public double getRevenue() {return revenue;}
    public void setRevenue(double revenue) {this.revenue = revenue;}



    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }




    public void removeFromCart(int index){
        Product selectedCartProduct = new ArrayList<>(currentCart.keySet()).get(index);
        if (selectedCartProduct.getCartAmount() >= 2){
            currentCart.put(selectedCartProduct, selectedCartProduct.getCartAmount()-1);
            selectedCartProduct.setStockQuantity(selectedCartProduct.getStockQuantity()+1);
            selectedCartProduct.setSoldQuantity(selectedCartProduct.getSoldQuantity()-1);
            selectedCartProduct.setCartAmount(selectedCartProduct.getCartAmount()-1);
            if (containsProduct(selectedCartProduct) == false) {
                addProduct(selectedCartProduct);
            }

        }else{
            selectedCartProduct.setStockQuantity(selectedCartProduct.getStockQuantity()+1);
            selectedCartProduct.setSoldQuantity(selectedCartProduct.getSoldQuantity()-1);
            selectedCartProduct.setCartAmount(selectedCartProduct.getCartAmount()-1);
            currentCart.remove(selectedCartProduct);
            if (containsProduct(selectedCartProduct) == false) {
                addProduct(selectedCartProduct);
            }

        }


    }


    public void clearCart(){
        currentCart.clear();
        totalCartPrice = 0;
    }

    public void resetAmounts(){
        for(HashMap.Entry<Product,Integer> e: currentCart.entrySet()){
            e.getKey().setStockQuantity(10);
            e.getKey().setCartAmount(0);
        }

    }
    public void resetSoldQuantities() {
        for (HashMap.Entry<Product, Integer> e : currentCart.entrySet()) {
            e.getKey().setSoldQuantity(0);
        }
    }
    public double totalAddingPrice(){
        for(HashMap.Entry<Product,Integer> e: currentCart.entrySet()){
            totalCartPrice += e.getKey().getPrice();
        }return totalCartPrice;
    }
    public double totalReductionPrice(int index){
        totalCartPrice -= new ArrayList<>(currentCart.keySet()).get(index).getPrice();
        return totalCartPrice;
    }
    public ArrayList<Product> sorting(){
        popularList = new ArrayList<Product>(currentProducts);

        Collections.sort(popularList, new Comparator<Product>() {
            public int compare(Product product1, Product product2) {
                if (product1.compareTo(product2) >= 1) {
                    return -1;
                } else if (product1.compareTo(product2) == 0) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });

        return popularList;
    }

    public ArrayList<Product> topThreeProductSorter() {
        if(currentProducts.size()<=3) {
            topThree = sorting();
            return sorting();
        }else{
            orderProducts = new ArrayList<>();
            for(int i = 0; i < 3; i++){
                orderProducts.add(sorting().get(i));

            }topThree = orderProducts;
            return orderProducts;
        }

    }
    public HashSet<Product> getCurrentProducts(){
        for (Product p: currentCart.keySet()) {
            currentProducts.add(p);
        }return currentProducts;
    }

    public boolean containsProduct(Product product) {
        for (int i = 0; i < curProducts; i++) {
            if (stock[i] == product) {
                return true;
            }
        }return false;
    }
    public void movingToCartList(Product p){
        currentCart.put(p,p.getCartAmount()+1);
        p.setStockQuantity(p.getStockQuantity() - 1);
        p.setSoldQuantity(p.getSoldQuantity()+1);
        p.setCartAmount(p.getCartAmount()+1);
        if(p.getStockQuantity() <=0){
            remove(p);
        }
    }
    public boolean remove(Product product) {
            for (int i = 0; i < curProducts; i++) {
                if (stock[i] == product) {
                    setStock(stock[curProducts - 1], i);
                    curProducts--;
                    return true;
                }
            }
            return false;
        }


    public void add(int index) {
        if (index >= 0) {
            Product p = stock[index];

            if (currentCart.containsKey(p)) {
                if (p.getStockQuantity() > 0 && p.getStockQuantity() <= 10) {
                    movingToCartList(p);
                }


            } else {
                if (p.getStockQuantity() > 0 && p.getStockQuantity() <= 10) {
                    movingToCartList(p);
                }
            }
        }
    }
    public void completeSale(){
        setNumberCompleteSales(getNumberCompleteSales()+1);
        setRevenue(revenue+getTotalCartPrice());
        setAverageSale(revenue/getNumberCompleteSales());
        setCurrentProducts(getCurrentProducts());
        topThreeProductSorter();
        resetAmounts();
        clearCart();
    }

    public void reset(){
        setRevenue(0);

        getCurrentCart().clear();
        //model.set = 0;

        for (int i=0; i<curProducts; i++){
            remove(stock[i]);
        }


        Product[] e = createStore().getStock();
        curProducts = 0;
        for (int i=0; i<8; i++){
            if (containsProduct(e[i]) == false) {
                addProduct(e[i]);

            }
        }


        numberCompleteSales = 0;
        averageSale = 0;
        popularList.clear();
        if(orderProducts != null) {
            orderProducts.clear();
        }
        getTopThree().clear();
        currentProducts.clear();
    }




    public void setCurrentProducts(HashSet<Product> currentProducts) {this.currentProducts = currentProducts;}

    public ArrayList<Product> getTopThree(){return topThree;}
    public void setTopThree(ArrayList<Product> topThree){this.topThree = topThree;}

    public double getAverageSale() {return averageSale;}
    public void setAverageSale(double averageSale){this.averageSale = averageSale;}

    public double getTotalCartPrice() {return totalCartPrice;}
    public HashMap<Product,Integer> getCurrentCart(){return currentCart;}
    public void setNumberCompleteSales(int completeSales) {this.numberCompleteSales = completeSales;}
    public int getNumberCompleteSales() {return numberCompleteSales;}
} 