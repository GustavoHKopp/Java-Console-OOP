import java.util.Scanner;
import utils.Functions;
import controller.EmployeeController;
import model.Employee;
import service.EmployeeService;

public class Main {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int option = -1;
		try {
			while (option != 6) {
				System.out.println("Escolha uma opção:");
				System.out.println("[1] - Listar Funcionarios");
				System.out.println("[2] - Cadastrar Funcionario");
				System.out.println("[3] - Alterar Funcionario");
				System.out.println("[4] - Consultar Funcionario");
				System.out.println("[5] - Deletar Funcionario");
				System.out.println("[6] - Sair");

				option = Functions.getNextInt(scanner);

				switch (option) {
					case 1: {
						EmployeeController.getAllEmployee();
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						scanner.nextLine();
						break;
					}
					case 2: {
						EmployeeController.putEmployee(scanner);
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						break;
					}
					case 3: {
						System.out.println("Digite o nome do funcionario");
						scanner.nextLine();
						String name = scanner.nextLine();
						if (!EmployeeService.employeeExistent(name)) {
							System.out.println("**************************");
							System.out.println("Funcionario não cadastrado");
							System.out.println("**************************");
							return;
						}
						Employee employee = EmployeeService.getEmployee(name);
						EmployeeController.postEmployee(employee, scanner);
						System.out.println("**************************");
						System.out.println("Alteração concluida!");
						System.out.println("**************************");
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						break;
					}
					case 4: {
						System.out.println("Digite o nome do funcionario");
						scanner.nextLine();
						String nome = scanner.nextLine();
						EmployeeController.getEmployee(nome);
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						break;
					}
					case 5: {
						System.out.println("Digite o nome do funcionario para deletar");
						scanner.nextLine();
						String nome = scanner.nextLine();
						EmployeeController.deleteEmployee(nome);
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						break;
					}
					case 6: {
						System.out.println("Saindo...");
						break;
					}
					case 7: {
						break;
					}
					default:
						System.out.println("Favor digitar uma opção valida!");
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						scanner.nextLine();
						break;
				}
			}
		} finally {
			scanner.close();
		}
	}
}