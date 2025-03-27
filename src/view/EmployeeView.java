package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import service.EmployeeService;
import model.Employee;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;

public class EmployeeView {
    private JFrame frmEmployeeSystem;
    private DefaultTableModel model;
    private JTabbedPane tabbedPane;
    private JPanel panel;
    private JPanel panel_1;
    private JScrollPane scrollPane;
    private JTable table;
    private JTextField txtSetor;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                EmployeeView window = new EmployeeView();
                window.frmEmployeeSystem.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public EmployeeView() {
        initialize();
    }
    
    private void searchData(String sector) {
    	ArrayList<Employee> list = new ArrayList<>(EmployeeService.getAllEmployee());
    	model.setRowCount(0);
    	
        for (Employee employee : list) {
        	if (sector.isEmpty()) {
	            model.addRow(new Object[]{
	        		employee.getName(),
	        		employee.getSector(),
	        		employee.getFunction(),
	        		employee.getWage()
	            });
        	} else {
        		 if  (employee.getSector().equals(sector)) {
        			 model.addRow(new Object[]{
		        		employee.getName(),
		        		employee.getSector(),
		        		employee.getFunction(),
		        		employee.getWage()
		            });
        		 }
        	}
        }

        model.fireTableStructureChanged();
    }
    
    private void addEventHandler(JButton btnConsultar) {
    	btnConsultar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchData(txtSetor.getText());
        	}
        });
    }

    private void initialize() {
        frmEmployeeSystem = new JFrame();
        frmEmployeeSystem.setTitle("Employee System");
        frmEmployeeSystem.setFont(new Font("Tahoma", Font.PLAIN, 10));
        frmEmployeeSystem.setBounds(100, 100, 702, 504);
        frmEmployeeSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frmEmployeeSystem.getContentPane().setLayout(new BorderLayout(0, 0));
        
        tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        frmEmployeeSystem.getContentPane().add(tabbedPane, BorderLayout.CENTER);
        
        model = new DefaultTableModel(new Object[]{"Nome", "Setor", "Função", "Salário"}, 0){
        	@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	};
        	/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
        };
        
        panel = new JPanel();
        panel.setToolTipText("");
        tabbedPane.addTab("Acesso", null, panel, null);
        panel.setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 35, 681, 402);
        panel.add(scrollPane);
        
        table = new JTable(model);
        scrollPane.setViewportView(table);
        
        JLabel lblSetor = new JLabel("Setor");
        lblSetor.setBounds(10, 10, 35, 14);
        panel.add(lblSetor);
        
        txtSetor = new JTextField();
        txtSetor.setBounds(52, 7, 86, 20);
        panel.add(txtSetor);
        txtSetor.setColumns(10);
        
        JButton btnConsultar = new JButton("Consultar");
        addEventHandler(btnConsultar);
        btnConsultar.setBounds(148, 6, 89, 23);
        panel.add(btnConsultar);
        
        panel_1 = new JPanel();
        tabbedPane.addTab("Cadastro", null, panel_1, null);

        searchData("");
    }
}
