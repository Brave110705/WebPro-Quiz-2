package quiz2.model;

public class Medicine {
    private int id;
    private String name;
    private double price;
    private int categoryId;
    private int stock;
    private String categoryName;

    public Medicine() {}

    public Medicine(int id, String name, double price, int categoryId, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "Medicine{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", categoryId=" + categoryId +
                ", stock=" + stock +
                '}';
    }

    public String getCategoryName() { return categoryName; }
    
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }
}
