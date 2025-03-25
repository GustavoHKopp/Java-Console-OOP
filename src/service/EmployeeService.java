package service;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import model.Employee;

public class EmployeeService {

	private static List<Employee> employees = new ArrayList<>(Arrays.asList(
			new Employee("Gustavo", "Back-end", 7800, "Programador"),
			new Employee("Jonas", "Telefonista", 1420, "Administrativo"),
			new Employee("Vitor", "Relações pessoais", 2600, "Administrativo"),
			new Employee("Gabriel", "Full-Steck", 9350, "Programador"),
			new Employee("Deyvin", "Ruby", 9500, "Programador")));

	public static void saveEmployee(Employee employee) {
		employees.add(employee);
	}

	public static ArrayList<String> showAllEmployee() {
		ArrayList<String> result = new ArrayList<>();
		for (Employee f : employees) {
			result.add(f.toStringResum());
		}

		return result;
	}

	public static String showOneEmployee(String nome) {
		String result = "";
		for (Employee f : employees) {
			if (f.getName().equalsIgnoreCase(nome)) {
				result = f.toString();
				break;
			}
		}

		return result;
	}

	public static Employee getEmployee(String nome){
		Employee employee = null;

		for (Employee f : employees) {
			if (f.getName().equalsIgnoreCase(nome)) {
				employee = f;
				break;
			}
		}

		return employee;
	}

	public static void deleteEmployee(String nome) {
		employees.removeIf(employee -> employee.getName().equalsIgnoreCase(nome));
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
