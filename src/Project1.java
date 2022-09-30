//Project1.java
//Quynh Vo
import java.util.Scanner;
public class Project1 {

	public static void main(String[] args) {
		final int MAX = 5000;
		
		String mainMenuChoice;
		String[] nameInput;
		String idInput;
		String rankInput;
		String departmentInput;
		String statusInput;
		float GPAinput;
		int numCreditInput;
		Scanner input = new Scanner(System.in);
		Person personList[] = new Person[MAX];
		
		System.out.println("\t\t\t\t\t\tWelcome to my Personal Management Program");
		System.out.println("Choose one of the options:");
		
		//loop thru main menu until the user exits or the maximum number of people reached.
		while (true) {			
			System.out.println("1. Enter the information of a faculty");
			System.out.println("2. Enter the inforamtion of a student");
			System.out.println("3. Print tuition invoice for a student");
			System.out.println("4. Print faculty information");
			System.out.println("5. Enter the information of a staff member");
			System.out.println("6. Print the information of a staff member");
			System.out.println("7. Exit Program");
			System.out.print("\n   Enter your selection: ");
			mainMenuChoice = input.nextLine();
			
			if (mainMenuChoice.equals("7"))
				break;
			
			//the user's main menu choices
			switch(mainMenuChoice) {
			case "1":
				//ask for faculty info: name, id, rank, department
				System.out.println("Enter the faculty info:");
				System.out.print("\t\tName of the faculty: ");
				nameInput = input.nextLine().split(" "); 
				nameInput[0] = Person.fixName(nameInput);
				
				System.out.print("\t\tID: ");
				idInput = input.nextLine();
				
				rankInput = Person.verifyInputs("rank");				
				departmentInput = Person.verifyInputs("department");
				
				
				//create new faculty
				personList[Person.amount] = new Faculty(nameInput[0], idInput, rankInput, departmentInput);
				System.out.println("Faculty Added!");
				Person.amount++;
				break;
			case "2":
				//ask for student info: name, id, gpa, credit taken
				System.out.println("Enter the student info: ");
				System.out.print("\t\tName of Student: ");
				nameInput = input.nextLine().split(" "); 
				nameInput[0] = Person.fixName(nameInput);
				
				System.out.print("\t\tID: ");
				idInput = input.nextLine();
				
				System.out.print("\t\tGPA: ");
				GPAinput = input.nextFloat();
				input.nextLine();
				
				System.out.print("\t\tCredit hours: ");
				numCreditInput = input.nextInt();
				input.nextLine();
				
				//create new student
				personList[Person.amount] = new Student(nameInput[0], idInput, GPAinput, numCreditInput);
				System.out.println("Student Added!");
				Person.amount++;
				break;
			case "3":
				System.out.print("\t\tEnter the Student's ID: ");
				idInput = input.nextLine();
				Person.findPerson(personList, idInput, "student");
				break;
			case "4":
				//find the faculty by using id
				System.out.print("\t\tEnter the Faculty's ID: ");
				idInput = input.nextLine();
				Person.findPerson(personList, idInput, "faculty");
				break;
			case "5":
				//ask for staff info: name, id, department, status
				System.out.print("\t\tName of the staff member: ");		
				nameInput = input.nextLine().split(" "); 
				nameInput[0] = Person.fixName(nameInput);
				
				System.out.print("\t\tEnter the ID: ");
				idInput = input.nextLine();
				
				departmentInput = Person.verifyInputs("department");
				statusInput = Person.verifyInputs("status");
				
				//create new staff
				personList[Person.amount] = new Staff(nameInput[0], idInput, departmentInput, statusInput);
				System.out.print("Staff member added!");
				Person.amount++;
				break;
			case "6":
				//find the staff by using id
				System.out.print("\t\tEnter the Staff's ID: ");
				idInput = input.nextLine();
				Person.findPerson(personList, idInput, "staff");
				break;
			default:
				System.out.println("Invalid entry- please try again\n");
			}
			System.out.println("\n");
			
			
		}
		System.out.println("Goodbye!");

	}


}
//---------------------------
abstract class Person{
	private String name;
	private String id;
	public static int amount = 0;
	
	
	
	public Person (String name, String id) {
		this.name = name;
		this.id = id;
	}
	
	public abstract void print();
	
	//getters
	public String getName() {
		return name;
	}
	public String getID() {
		return id;
	}
	
	//find person using user's input
	public static void findPerson(Person[] personList, String idInput, String inputCode) {
		boolean found = false;
		for (int i = 0; i < personList.length; i++) {
			//System.out.printf("%s\n", personList[i].id);
			if (personList[i] == null )
				break;
			else if ( idInput.equals(personList[i].id)){
				found = true;
				personList[i].print();
				break;
			}
		}
		if (!found && inputCode.equals("student"))
			System.out.printf("No Student matched!");
		else if (!found && inputCode.equals("staff"))
			System.out.printf("No Staff Member matched!");
		else if (!found && inputCode.equals("faculty"))
			System.out.printf("No Faculty matched!");
	}
	
	//ensure to save proper string format - capitalize first letter only
	public static String fixString(String userInput) {
		userInput = userInput.toLowerCase();
		return String.format(userInput.substring(0, 1).toUpperCase() + userInput.substring(1));
	}
	
	//makes sure names of all lengths are capitalized
	public static String fixName(String[] nameInput) {
		for (int i = 0; i < nameInput.length; i++) {
			nameInput[i] = fixString(nameInput[i]);
			if (i > 0)
				nameInput[0] += " " + nameInput[i];
		}
		return nameInput[0];
	}
	
	//verify user inputs for: rank, department, status	
	public static String verifyInputs(String inputCode) {
		String userInput = "";
		Scanner input = new Scanner(System.in);
		while (true) {
			switch (inputCode) {
			case "status":	
				System.out.print("\t\tStatus, Enter P for Part Time, or Enter F for Full Time:");
				userInput = input.nextLine();
				if (userInput.toUpperCase().equals("P") || userInput.toUpperCase().equals("F"))
					return fixString(userInput);
				break;
			case "department":
				System.out.print("\t\tDepartment:");
				userInput = input.nextLine();
				if (userInput.toUpperCase().equals("MATHEMATICS") || userInput.toUpperCase().equals("ENGINEERING") || userInput.toUpperCase().equals("SCIENCES"))
					return fixString(userInput);
				break;
			case "rank":
				System.out.print("\t\tRank:");
				userInput = input.nextLine();
				if (userInput.toUpperCase().equals("PROFESSOR") || userInput.toUpperCase().equals("ADJUNCT"))
					return fixString(userInput);
				break;

			}
			System.out.printf("\t\t     \"%s\" is invalid\n", userInput);
		}
		
	}
}

//---------------------------
class Student extends Person{
	private float gpa;
	private int creditTaken;
	
	public Student (String name, String id, float gpa, int creditTaken) {
		super(name, id);
		this.gpa = gpa;
		this.creditTaken = creditTaken;
	}
	
	@Override
	public void print(){
		//calculate tuition
		double tuitionTotal = this.creditTaken * 236.45 + 52;
		double discount = 0;
		if (this.gpa >= 3.85) {
			discount = tuitionTotal*0.25;
			tuitionTotal -= discount;
		}
		if (this.creditTaken == 0)
			tuitionTotal = 0;
		
		//print stuff
		System.out.printf("\ttuition invoice for %s:\n", this.getName());
		System.out.println("\t---------------------------------------------------------------------------");
		System.out.printf("\t%s\t\t\t%s\n", this.getName(), this.getID());
		System.out.printf("\tCredit Hours: %d\n", this.creditTaken);
		System.out.printf("\tFees: $52\n\n\n");
		System.out.printf("\tTotal payment (after discount): $%.2f\t\t($%.2f discount applied)\n", tuitionTotal, discount);
		System.out.println("\t---------------------------------------------------------------------------");
		
	}

	
}

//---------------------------
abstract class Employee extends Person{
	private String department;
	public Employee(String name, String id, String department){
		super(name, id);
		this.department = department;
	}
	
	public String getDepartment() {
		return department;
	}

}


//---------------------------
class Faculty extends Employee{
	private String rank;
	
	public Faculty (String name, String id, String rank, String department) {
		super(name, id, department);
		this.rank = rank;
	}
	
	@Override
	public void print(){
		System.out.println("\t---------------------------------------------------------------------------");
		System.out.printf("\t%s\t\t%s\n", this.getName(), this.getID());
		System.out.printf("\t%s Department, %s\n", this.getDepartment(), this.rank);
		System.out.println("\t---------------------------------------------------------------------------");
	}
	
}


//---------------------------
class Staff extends Employee{
	private String status;
	
	public Staff (String name, String id, String department, String status) {
		super(name, id, department);
		this.status = status;
	}
	
	
	@Override
	public void print(){
		String fullStatus;
		if (this.status.toUpperCase().equals("F"))
			fullStatus = "Full Time";
		else
			fullStatus = "Part Time";
		
		System.out.println("\t---------------------------------------------------------------------------");
		System.out.printf("\t%s\t\t%s\n", this.getName(), this.getID());
		System.out.printf("\t%s Department, %s\n", this.getDepartment(), fullStatus);
		System.out.println("\t---------------------------------------------------------------------------");
	}
}

