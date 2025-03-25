package utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Functions {
	public static int getNextInt(Scanner scanner) {
	    int option = -1;
	    
	    while (option == -1) {
	        try {
	        	option = scanner.nextInt();
	        } catch (InputMismatchException e) {
	            System.out.println("Erro: Digite apenas números!");
	            System.out.println("Para exibir novamente as opções digite [7]");
	            scanner.nextLine();
	        }
	    }
	    
	    return option;
	}
	
	public static double getNextDouble(Scanner scanner) {
	    double option = -1;
	    
	    while (option == -1) {
	        try {
	        	option = scanner.nextDouble();
	            } catch (InputMismatchException e) {
	            System.out.println("Erro: Digite apenas números!");
	            scanner.nextLine();
	        }
	    }
	    
	    return option;
	}
	
	public static String getNextLine(Scanner scanner) {
		String line = "";
		
		do {
			line = scanner.nextLine();
		} while (line.isEmpty());
		return line;
	}
}