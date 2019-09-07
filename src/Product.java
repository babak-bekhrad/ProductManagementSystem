
public class Product {

    private final int id;
    private final String name;
    private final float price;
    private final String add_date;
    private final byte[] picture;

    public Product(int pid, String pname, float pprice, String paddDate, byte[] ppicture) {
        this.id = pid;
        this.name = pname;
        this.price = pprice;
        this.add_date = paddDate;
        this.picture = ppicture;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getAddDate() {
        return add_date;
    }

    public byte[] getPicture() {
        return picture;
    }

}
