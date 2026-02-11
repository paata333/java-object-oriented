
public class Device {
    private String name;
    private int price;
    private String date; // Changed year to date

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getDate() {  // Updated method name
        return date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDate(String date) {  // Updated method name
        this.date = date;
    }
}
