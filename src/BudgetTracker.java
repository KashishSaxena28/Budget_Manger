import javax.swing.*;
import java.awt.*;

public class BudgetTracker {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BudgetTracker().createAndShowGUI();
        });
    }

    private void createAndShowGUI() {
        JFrame frame = new JFrame("Budget Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        ExpenseModel expenseModel = new ExpenseModel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Expenses", new ExpensePanel(expenseModel));
        tabbedPane.addTab("Budget", new BudgetPanel());
        tabbedPane.addTab("Remaining Amount" , new RemainingAmountPanel(expenseModel));

        frame.add(tabbedPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
