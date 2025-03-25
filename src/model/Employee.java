package model;

public class Employee {
	String name;
	String function;
	double wage;
	String sector;
	
	public Employee(String name, String function,double wage, String sector){
		this.name = name;
		this.wage = wage;
		this.function = function;
		this.sector = sector;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSector() { 
		return sector; 
	}
	
    public void setSector(String sector) { 
    	this.sector = sector;
    }
    
    public Double getWage() { 
    	return wage;
    }
    
    public void setWage(Double wage) { 
    	this.wage = wage; 
    }

    public String getFunction() { 
    	return function;
    }
    
    public void setFunction(String function) { 
    	this.function = function; 
    }
    
    public String toStringResum() {
    	return "Nome: " + name + ", Função: " + function;
    }
    
    @Override
    public String toString() {
        return "Nome: " + name + ", Função: " + function + ", Salário: " + wage + ", Setor: " + sector;
    }
}