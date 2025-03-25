package controller;

import java.util.Scanner;
import service.EmployeeService;
import utils.Functions;


public class EmployeeController {
	public static void getAllEmployee() {
		EmployeeService.showAllEmployee();
	}
	
	public static void getEmployee(String name) {
		if (EmployeeService.employeeExistent(name)) {			
			EmployeeService.showOneEmployee(name);
		} else {
			System.out.println("**************************");
			System.out.println("Funcionario não cadastrado");
			System.out.println("**************************");
		}
	}
	
	public static void putEmployee(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Nome:");
		String name = Functions.getNextLine(scanner);
		System.out.println("Setor:");
		String sector = Functions.getNextLine(scanner);
		System.out.println("Função:");
		String function = Functions.getNextLine(scanner);
		System.out.println("Salario:");
		double wage = Functions.getNextDouble(scanner);
		scanner.nextLine();
		EmployeeService.saveEmployee(name.trim(), function.trim(), wage, sector.trim());
	}
	
	public static void deleteEmployee(String nome) {
		if (EmployeeService.employeeExistent(nome)) {			
			EmployeeService.deleteEmployee(nome);
		} else {
			System.out.println("**************************");
			System.out.println("Funcionario não cadastrado");
			System.out.println("**************************");
		}
	}
	
	public static void pushEmployee(String nome, Scanner scanner) {
		int option = 0;
		String newName = "";
		String function = "";
		String sector = "";
		double wage = 0;
		
		if (!EmployeeService.employeeExistent(nome)) {
			System.out.println("**************************");
			System.out.println("Funcionario não cadastrado");
			System.out.println("**************************");
			return;
		}
		
		while (true) {			
			System.out.printf("Qual campo deseja alterar do funcionario: %s%n", nome);
			System.out.println("[1] - Nome");
			System.out.println("[2] - Função");
			System.out.println("[3] - Setor");
			System.out.println("[4] - Salario");
			System.out.println("[5] - Voltar ao Menu");
			
			option = Functions.getNextInt(scanner);
			
			switch (option) {
				case 1: {
					System.out.println("Digite o novo nome do funcionario");
					scanner.nextLine();
					newName = Functions.getNextLine(scanner);
					System.out.println("**************************");
					System.out.printf("Funcionario %s teve o nome alterado para %s%n", nome, newName);
					System.out.println("**************************");
					break;
				}
				case 2:{
					System.out.println("Digite a nova função do funcionario");
					scanner.nextLine();
					function = Functions.getNextLine(scanner);
					System.out.println("**************************");
					System.out.printf("Funcionario %s teve a função alterada para %s%n", nome, function);
					System.out.println("**************************");
					break;
				}
				case 3:{
					System.out.println("Digite o novo nome do funcionario");
					scanner.nextLine();
					sector = Functions.getNextLine(scanner);
					System.out.println("**************************");
					System.out.printf("Funcionario %s teve o setor alterado para %s%n", nome, sector);
					System.out.println("**************************");
					break;
				}
				case 4:{
					System.out.println("Digite o novo salario do Funcionario");
					wage =  Functions.getNextDouble(scanner);
					System.out.println("**************************");
					System.out.printf("Funcionario %s teve o slário alterado para %s%n", nome, wage);
					System.out.println("**************************");
					break;
				}
				case 5:{
					System.out.println("Voltando ao menu...");
					break;
				}
				case 7: {
					continue;
				}
				default:
					System.out.println("Favor digitar uma opção valida!");
					System.out.println("Precione Enter para continuar...");
					scanner.nextLine();
					scanner.nextLine();
					continue;
			}
			
			break;
		}
		
		EmployeeService.updateEmployee(nome, newName, function, wage, sector, option);
	}
	
}