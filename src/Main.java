import java.util.Scanner;
import utils.Functions;
import controller.EmployeeController;

public class Main {
		
	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		
		try {			
			while(true) {
				System.out.println("Escolha uma opção:");
				System.out.println("[1] - Listar Funcionarios");
				System.out.println("[2] - Cadastrar Funcionario");
				System.out.println("[3] - Alterar Funcionario");
				System.out.println("[4] - Consultar Funcionario");
				System.out.println("[5] - Deletar Funcionario");
				System.out.println("[6] - Sair");
				
				int option = Functions.getNextInt(scanner);
				
				switch (option) {
					case 1: {
						EmployeeController.getAllEmployee();
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						scanner.nextLine();
						continue;
					}
					case 2:{
						EmployeeController.putEmployee(scanner);
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						continue;
					}
					case 3:{
						System.out.println("Digite o nome do funcionario");
						scanner.nextLine();
						String nome = scanner.nextLine();
						EmployeeController.pushEmployee(nome, scanner);
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						continue;
					}
					case 4:{
						System.out.println("Digite o nome do funcionario");
						scanner.nextLine();
						String nome = scanner.nextLine();
						EmployeeController.getEmployee(nome);
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						continue;
					}
					case 5:{
						System.out.println("Digite o nome do funcionario para deletar");
						scanner.nextLine();
						String nome = scanner.nextLine();
						EmployeeController.deleteEmployee(nome);
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						continue;
					}
					case 6:{
						System.out.println("Saindo...");
						break;
					}
					case 7:{
						continue;
					}
					default:
						System.out.println("Favor digitar uma opção valida!");
						System.out.println("Precione Enter para continuar...");
						scanner.nextLine();
						scanner.nextLine();
						continue;
				}
				
				if (option == 6) {
					break;
				}
				
			}
		} finally {			
			scanner.close();
		}
	}
}