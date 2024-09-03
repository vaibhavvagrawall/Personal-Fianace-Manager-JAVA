import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class FinanceManager implements Serializable {
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(double amount, String category, String type) {
        transactions.add(new Transaction(amount, category, type));
    }

    public double calculateBalance() {
        return transactions.stream()
            .mapToDouble(transaction -> transaction.getType().equals("income") ? transaction.getAmount() : -transaction.getAmount())
            .sum();
    }

    public void generateReport() {
        System.out.println("\nFinancial Report:");
        transactions.forEach(System.out::println);
        System.out.printf("Current Balance: %.2f\n", calculateBalance());
    }
}
