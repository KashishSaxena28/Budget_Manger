import java.util.ArrayList;
import java.util.List;

public class Expense {
    private String name;
    private double amount;
    private String category;

    public Expense(String name, double amount, String category){
        this.name = name;
        this.amount = amount;
        this.category = category;
    }

        public double getAmount(){
        return amount;
        }

        public String getCategory(){
        return category;
        }
}

class ExpenseModel{
    private List<Expense> expenses = new ArrayList<>();

    public void addExpense(Expense expense){
        expenses.add(expense);
    }

    public List<Expense> getExpenses(){
        return expenses;
    }
}