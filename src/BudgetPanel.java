import javax.swing.*;
import java.awt.*;

class BudgetPanel extends JPanel {
    private JTextField budgetField;
    private JLabel budgetLabel;

    public BudgetPanel(){
        setLayout(new GridLayout(2,2));

        JLabel setBudgetLabel = new JLabel("Set Budget Amount:");
        setBudgetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(setBudgetLabel);
        budgetField = new JTextField();
        add(budgetField);

        JButton setBudgetButton = new JButton("Set Budget");
        add(setBudgetButton);

        budgetLabel = new JLabel("Current Budget: ₹0");
        add(budgetLabel);

        setBudgetButton.addActionListener(e -> setBudget());
    }

    private void setBudget(){
        String budget = budgetField.getText();
        if(!budget.isEmpty()){
            budgetLabel.setText("Current Budget: ₹" + budget);
        }else {
            JOptionPane.showMessageDialog(this,"Please Enter Budget Amount",
                    "Error",JOptionPane.ERROR_MESSAGE);
        }
    }
}