import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction implements Serializable {
    private double amount;
    private String category;
    private Date date;
    private String type; // "income" or "expense"

    public Transaction(double amount, String category, String type) {
        this.amount = amount;
        this.category = category;
        this.date = new Date();
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public Date getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return String.format("%s: %.2f - %s on %s", type, amount, category, sdf.format(date));
    }
}
