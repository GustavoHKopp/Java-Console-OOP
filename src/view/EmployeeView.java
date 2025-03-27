package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.function.Function;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import service.EmployeeService;
import model.Employee;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.BorderLayout;

public class EmployeeView {
    private JFrame frmEmployeeSystem;
    private DefaultTableModel model;
    private JTabbedPane tabbedPane;
    private JPanel Acesso;
    private JPanel Cadastro;
    private JScrollPane scrollPane;
    private JTable table;
    private JTextField txtAcessoSetor;
    private JLabel lblNewLabel;
    private JTextField txtNome;
    private JTextField txtSetor;
    private JTextField txtFuncao;
    private JButton btnIncluir;
    private JFormattedTextField txtSalario;
    private JButton btnConfirmar;
    private JButton btnDesistir;
    private Employee funcionario = null;
    private JButton btnConsultar;
    

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
    
    private void addEventHandlerConsulta() {
    	btnConsultar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		searchData(txtAcessoSetor.getText());
        	}
        });
    }
    
    private void addEventHandlerExcluir(JButton btnExcluir) {
    	btnExcluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int rowIndex = table.getSelectedRow();
        		
        		if (rowIndex != -1) {
        			String nome = table.getValueAt(rowIndex, 0).toString();
        			JOptionPane.showMessageDialog(null, "Tem certeza que deseja excluir o funcionario: "+nome, "Atenção", JOptionPane.WARNING_MESSAGE);
        			EmployeeService.deleteEmployee(nome);
        			DefaultTableModel model = (DefaultTableModel) table.getModel();
        		    model.removeRow(rowIndex);
        		} else {
        			JOptionPane.showMessageDialog(null, "Nenhum registro selecionado para excluir", "Atenção", JOptionPane.WARNING_MESSAGE);
        		}
        	}
        });
    }
    
    private void edicaoCadastro(Boolean editando) {
    	Function<Boolean, Integer> tab = e -> e ? 1 : 0;
    	tabbedPane.setSelectedIndex(tab.apply(editando));
		txtNome.setEnabled(editando);
		txtSetor.setEnabled(editando);
		txtFuncao.setEnabled(editando);
		txtSalario.setEnabled(editando);
		if (!editando) {
			txtNome.setText("");
			txtSetor.setText("");
			txtFuncao.setText("");
			txtSalario.setValue(0);
			btnConsultar.doClick();
		}
    }
    
    private void addEventHandlerEditar(JButton btnEditar){
    	btnEditar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
				int rowIndex = table.getSelectedRow();
        		
        		if (rowIndex != -1) {
        			String nome = table.getValueAt(rowIndex, 0).toString();
        			Employee employee = EmployeeService.getEmployee(nome);
        			txtNome.setText(employee.getName());
        			txtSetor.setText(employee.getSector());
        			txtFuncao.setText(employee.getFunction());
        			txtSalario.setValue(employee.getWage());
        			funcionario = employee;
        			edicaoCadastro(true);        			
        		} else {
        			JOptionPane.showMessageDialog(null, "Nenhum registro selecionado para editar", "Atenção", JOptionPane.WARNING_MESSAGE);
        		}
        	}
        });
    }
    
    private void addEventHandlerIncluir(JButton btnIncluir) {
    	btnIncluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		edicaoCadastro(true);
        	}
        });
    }
    
    private void addEventHandlerConfirmar(JButton btnIncluir) {
    	btnIncluir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (funcionario != null) {
        			funcionario.setName(txtNome.getText());
        			funcionario.setSector(txtSetor.getText());
        			funcionario.setFunction(txtFuncao.getText());
        			funcionario.setWage(Double.parseDouble(txtSalario.getText()));
        		} else {
        			Employee employee = new Employee(txtNome.getText(), txtFuncao.getText(), Double.parseDouble(txtSalario.getText()), txtSetor.getText());
        			EmployeeService.saveEmployee(employee);
        		}
        		edicaoCadastro(false);
        	}
        });
    }
    
    private void addEventHandlerDesistir(JButton btnDesistir) {
    	btnDesistir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		edicaoCadastro(false);
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
        
        Acesso = new JPanel();
        Acesso.setToolTipText("");
        tabbedPane.addTab("Acesso", null, Acesso, null);
        Acesso.setLayout(null);
        
        scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 35, 681, 402);
        Acesso.add(scrollPane);
        
        table = new JTable(model);
        scrollPane.setViewportView(table);
        
        JLabel lblSetor = new JLabel("Setor");
        lblSetor.setBounds(10, 10, 35, 14);
        Acesso.add(lblSetor);
        
        txtAcessoSetor = new JTextField();
        txtAcessoSetor.setBounds(52, 7, 86, 20);
        Acesso.add(txtAcessoSetor);
        txtAcessoSetor.setColumns(10);
        
        btnConsultar = new JButton("Consultar");
        addEventHandlerConsulta();
        btnConsultar.setBounds(148, 6, 89, 23);
        Acesso.add(btnConsultar);
        
        JButton btnEditar = new JButton("Editar");
        addEventHandlerEditar(btnEditar);
        btnEditar.setBounds(346, 6, 89, 23);
        Acesso.add(btnEditar);
        
        JButton btnExcluir = new JButton("Excluir");
        addEventHandlerExcluir(btnExcluir);
        btnExcluir.setBounds(445, 6, 89, 23);
        Acesso.add(btnExcluir);
        
        btnIncluir = new JButton("Incluir");
        addEventHandlerIncluir(btnIncluir);
        btnIncluir.setBounds(247, 6, 89, 23);
        Acesso.add(btnIncluir);
        
        Cadastro = new JPanel();
        tabbedPane.addTab("Cadastro", null, Cadastro, null);
        Cadastro.setLayout(null);
        
        lblNewLabel = new JLabel("Nome");
        lblNewLabel.setBounds(10, 11, 46, 14);
        Cadastro.add(lblNewLabel);
        
        txtNome = new JTextField();
        txtNome.setEnabled(false);
        txtNome.setBounds(66, 11, 332, 20);
        Cadastro.add(txtNome);
        txtNome.setColumns(10);
        
        JLabel lblSetor_1 = new JLabel("Setor");
        lblSetor_1.setBounds(10, 39, 46, 14);
        Cadastro.add(lblSetor_1);
        
        txtSetor = new JTextField();
        txtSetor.setEnabled(false);
        txtSetor.setColumns(10);
        txtSetor.setBounds(66, 39, 332, 20);
        Cadastro.add(txtSetor);
        
        JLabel lblSetor_1_1 = new JLabel("Função");
        lblSetor_1_1.setBounds(10, 67, 46, 14);
        Cadastro.add(lblSetor_1_1);
        
        txtFuncao = new JTextField();
        txtFuncao.setEnabled(false);
        txtFuncao.setColumns(10);
        txtFuncao.setBounds(66, 67, 332, 20);
        Cadastro.add(txtFuncao);
        
        JLabel lblSetor_1_1_1 = new JLabel("Salario");
        lblSetor_1_1_1.setBounds(10, 102, 46, 14);
        Cadastro.add(lblSetor_1_1_1);
        
        NumberFormatter numberFormatter = new NumberFormatter(new DecimalFormat("#0.##"));
        numberFormatter.setAllowsInvalid(false);
        
        txtSalario = new JFormattedTextField(numberFormatter);
        txtSalario.setEnabled(false);
        txtSalario.setBounds(66, 99, 163, 20);
        Cadastro.add(txtSalario);
        
        btnConfirmar = new JButton("Confirmar");
        addEventHandlerConfirmar(btnConfirmar);
        btnConfirmar.setBounds(10, 403, 105, 23);
        Cadastro.add(btnConfirmar);
        
        btnDesistir = new JButton("Desistir");
        addEventHandlerDesistir(btnDesistir);
        btnDesistir.setBounds(125, 403, 110, 23);
        Cadastro.add(btnDesistir);

        searchData("");
    }
}
