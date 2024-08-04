import javax.swing.*;
import java.awt.*;
import java.util.List;

class RemainingAmountPanel extends JPanel {
    private ExpenseModel expenseModel;
    private JTextField budgetField;
    private JLabel remainingAmountLabel;

    public RemainingAmountPanel(ExpenseModel expenseModel) {
        this.expenseModel = expenseModel;
        setLayout(new GridLayout(2, 2));

        JLabel budgetLabel = new JLabel("Enter Budget Amount:");
        budgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        budgetField = new JTextField();
        JButton calculateButton = new JButton("Calculate Remaining Amount");
        remainingAmountLabel = new JLabel("Remaining Amount: $0");

        add(budgetLabel);
        add(budgetField);
        add(calculateButton);
        add(remainingAmountLabel);

        calculateButton.addActionListener(e -> calculateRemainingAmount());
    }

    private void calculateRemainingAmount() {
        String budgetText = budgetField.getText();
        if (!budgetText.isEmpty()) {
            try {
                double budget = Double.parseDouble(budgetText);
                double totalExpenses = 0;

                List<Expense> expenses = expenseModel.getExpenses();
                for (Expense expense : expenses) {
                    totalExpenses += expense.getAmount();
                }

                double remainingAmount = budget - totalExpenses;
                remainingAmountLabel.setText("Remaining Amount: $" + remainingAmount);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid budget amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter a budget amount", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

