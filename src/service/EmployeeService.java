package service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import model.Employee;

public class EmployeeService {
	
	private static List<Employee> employees = new ArrayList<>(Arrays.asList(
			new Employee("Gustavo", "Back-end", 7800, "Programador"),
			new Employee("Jonas", "Telefonista",1420, "Administrativo"),
			new Employee("Vitor", "Relações pessoais", 2600, "Administrativo"),
			new Employee("Gabriel", "Full-Steck", 9350, "Programador"),
			new Employee("Deyvin", "Ruby", 9500, "Programador")
	));
	
	public static void saveEmployee(String nome, String funcao, double salario, String setor) {
		Employee employee = new Employee(nome, funcao, salario, setor);
		employees.add(employee);
        System.out.println("**************************");
        System.out.println("Funcionário adicionado com sucesso!");
        System.out.println("**************************");
	}
	
	public static void showAllEmployee() {
		for (Employee f : employees) {
			System.out.println(f.toStringResum());
			System.out.println("**************************");
		}
	}
	
	public static void showOneEmployee(String nome) {
		for (Employee f : employees) {
			if (f.getName().equalsIgnoreCase(nome)) {
				System.out.println("**************************");
				System.out.println(f.toString());
				System.out.println("**************************");
			}
		}
	}
	
	public static void updateEmployee(String nome, String novoNome, String funcao, double salario, String setor, int opcao) {
		for (Employee f : employees) {
			if (f.getName().equalsIgnoreCase(nome)) {
				switch (opcao) {
					case 1: {
						f.setName(novoNome);
						break;
					}
					case 2:{
						f.setFunction(funcao);
						break;
					}
					case 3:{
						f.setSector(setor);
						break;
					}
					case 4:{
						f.setWage(salario);
						break;
					}
				}
				break;
			}
		}
	}
	
	public static void deleteEmployee(String nome) {
		employees.removeIf(employee -> employee.getName().equalsIgnoreCase(nome));
        System.out.println("**************************");
        System.out.println("Funcionário removido!");
        System.out.println("**************************");
    }
	
	public static Boolean employeeExistent(String nome) {
		String func = "";
		for (Employee f : employees) {
			if (f.getName().equalsIgnoreCase(nome)) {
				func = f.toString();
			}
		}
		
		if (func.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}
}
