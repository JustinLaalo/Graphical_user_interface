//Base class for all products the store will sell
public abstract class Product {
    private double price;
    private int stockQuantity;
    private int soldQuantity;
    private int cartAmount;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
        cartAmount = 0;
    }

    public int compareTo(Product p){return (int) soldQuantity-p.soldQuantity;}

    public int getStockQuantity() {
        return stockQuantity;
    }

    public int getSoldQuantity() {
        return soldQuantity;
    }

    public double getPrice() {
        return price;
    }

    public void setStockQuantity(int stockQuantity) {this.stockQuantity = stockQuantity;}

    public int getCartAmount() {return cartAmount;}
    public void setCartAmount(int cartAmount) {this.cartAmount = cartAmount;}

    public void setSoldQuantity(int soldQuantity) {this.soldQuantity = soldQuantity;}

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }
}