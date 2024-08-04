import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

class ExpensePanel extends JPanel {
    private JTextField expenseNameField;
    private JTextField expenseAmountField;
    private JComboBox<String> categoryComboBox;
    private JButton addButton;
    private JButton exportButton;
    private DefaultListModel<String> expenseListModel;
    private ExpenseModel expenseModel;

    public ExpensePanel(ExpenseModel expenseModel) {
        this.expenseModel = expenseModel;
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2));

        inputPanel.add(new JLabel("Expense Name:"));
        expenseNameField = new JTextField();
        inputPanel.add(expenseNameField);

        inputPanel.add(new JLabel("Expense Amount:"));
        expenseAmountField = new JTextField();
        inputPanel.add(expenseAmountField);

        inputPanel.add(new JLabel("Category:"));
        categoryComboBox = new JComboBox<>(new String[]{"Food", "Transport", "Utilities", "Entertainment", "Other"});
        inputPanel.add(categoryComboBox);

        addButton = new JButton("Add Expense");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);

        expenseListModel = new DefaultListModel<>();
        JList<String> expenseList = new JList<>(expenseListModel);
        add(new JScrollPane(expenseList), BorderLayout.CENTER);

        exportButton = new JButton("Export Data");
        add(exportButton, BorderLayout.SOUTH);

        addButton.addActionListener(e -> addExpense());
        exportButton.addActionListener(e -> exportData());
    }

    private void addExpense() {
        String name = expenseNameField.getText();
        String amountText = expenseAmountField.getText();
        String category = (String) categoryComboBox.getSelectedItem();
        if (!name.isEmpty() && !amountText.isEmpty() && category != null) {
            try {
                double amount = Double.parseDouble(amountText);
                Expense expense = new Expense(name, amount, category);
                expenseModel.addExpense(expense);
                expenseListModel.addElement(name + ": $" + amount + " [" + category + "]");
                expenseNameField.setText("");
                expenseAmountField.setText("");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid amount", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter name, amount, and select a category", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void exportData() {
        try {
            FileWriter writer = new FileWriter("expenses.csv");
            for (int i = 0; i < expenseListModel.getSize(); i++) {
                writer.write(expenseListModel.getElementAt(i) + "\n");
            }
            writer.close();
            JOptionPane.showMessageDialog(this, "Data exported successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
