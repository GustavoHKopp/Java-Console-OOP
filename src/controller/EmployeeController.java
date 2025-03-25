package controller;

import java.util.ArrayList;
import java.util.Scanner;

import model.Employee;
import service.EmployeeService;
import utils.Functions;

public class EmployeeController {
	public static void getAllEmployee() {
		ArrayList<String> employees = new ArrayList<>(EmployeeService.showAllEmployee());
		for (int i = 0; i < employees.size(); i++) {
			System.out.println(employees.get(i));
			System.out.println("**************************");
		}
	}

	public static void getEmployee(String name) {
		if (EmployeeService.employeeExistent(name)) {
			System.out.println("**************************");
			System.out.println(EmployeeService.showOneEmployee(name));
			System.out.println("**************************");
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
		Employee employee = new Employee(name, function, wage, sector);
		EmployeeService.saveEmployee(employee);
		System.out.println("**************************");
		System.out.println("Funcionário adicionado com sucesso!");
		System.out.println("**************************");
	}

	public static void deleteEmployee(String nome) {
		if (EmployeeService.employeeExistent(nome)) {
			EmployeeService.deleteEmployee(nome);
			System.out.println("**************************");
			System.out.println("Funcionário removido!");
			System.out.println("**************************");
		} else {
			System.out.println("**************************");
			System.out.println("Funcionario não cadastrado");
			System.out.println("**************************");
		}
	}

	public static void postEmployee(Employee employee, Scanner scanner) {
		int option = 0;

		while (option != 5) {
			System.out.printf("Qual campo deseja alterar do funcionario: %s%n", employee.getName());
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
					String name = Functions.getNextLine(scanner);
					employee.setName(name);
					break;
				}
				case 2: {
					System.out.println("Digite a nova função do funcionario");
					scanner.nextLine();
					String function = Functions.getNextLine(scanner);
					employee.setFunction(function);
					break;
				}
				case 3: {
					System.out.println("Digite o novo nome do funcionario");
					scanner.nextLine();
					String sector = Functions.getNextLine(scanner);
					employee.setSector(sector);
					break;
				}
				case 4: {
					System.out.println("Digite o novo salario do Funcionario");
					double wage = Functions.getNextDouble(scanner);
					employee.setWage(wage);
					break;
				}
				case 5: {
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
	}

}