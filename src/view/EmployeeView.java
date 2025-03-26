package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service.EmployeeService;
import model.Employee;

public class EmployeeView {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel model;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EmployeeView window = new EmployeeView();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EmployeeView() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        model = new DefaultTableModel(new Object[]{"Nome", "Setor", "Função", "Salário"}, 0);
        table = new JTable(model);
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        ArrayList<Employee> list = new ArrayList<>(EmployeeService.getAllEmployee());

        for (Employee employee : list) {
            model.addRow(new Object[]{
        		employee.getName(),
        		employee.getSector(),
        		employee.getFunction(),
        		employee.getWage()
            });
        }

        model.fireTableStructureChanged();
    }
}
