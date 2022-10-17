package reserveSeats;
import java.io.File;  // Import the File class
import java.util.NoSuchElementException;
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.PrintWriter;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.InputMismatchException; // Import this class to handle errors

// A java skill warm up project: the aim is to create a system that reserves seats for a 5 x 5 Grid layout.
// The system must show what seats are available, which unavailable, and which names are reserving the seats.
// The project should also store seats in a file for future access, as well as let someone move their seat.
public class reserver {
	//class type with constructor
	class Reservation {
		String Name = "";
		Reservation (String rName){
			Name = rName;
			}
		public void display() {
			System.out.print(Name);
			}
	}
	
	public static File getFile() {
		return new File("./src/reserveSeats/chairHist" );
	}
	
	
	public static void main(String[] args) {
		// create reserve and fill it with empty reservations or load from past list
		Scanner myReader = null;
		PrintWriter writer = null;
		try {
		 myReader = new Scanner( getFile() );
		}
		catch (FileNotFoundException e){
			System.out.println("File not found exception.");
		}
		Reservation reserv[][] = new Reservation[5][5];
		for (int i = 0; i <= 4; i++) {
			for (int j = 0; j <= 4; j++) {
				reserv[i][j]= new reserver().new Reservation("");
				}
			}
		// load data from file
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					try {
						reserv[i][j].Name = myReader.nextLine();
					}
					catch (NoSuchElementException e) {
						reserv[i][j].Name = "";
					}
					}
				}
			myReader = new Scanner(System.in);
			
		
		// loop that runs program
		boolean x = true;
		while(x) {
			int Choice = 0;
			int Row = 0;
			int Column = 0;
			String userName = "";
			System.out.println("============================");
			System.out.println("SEATING: O = Available, X = Taken");
			System.out.println("============================");
			System.out.println("");
			System.out.println("    1 2 3 4 5");
			System.out.println("   ----------");
			// This loop displays the seating
			for (int i = 0; i < 5; i++) {
				System.out.print((i+1) + " | ");
				for (int j = 0; j < 5; j++) {
					if (reserv[i][j].Name == "")
						System.out.print("O ");
					else
						System.out.print("X ");
					if (j == 4)
						System.out.println(" ");
					}
				}
			System.out.println("");
			System.out.println("What would you like to do?:");
			System.out.println("1- Reserve Seat");
			System.out.println("2- View booking");
			System.out.println("3- Remove booking");
			System.out.println("4- Change Seating");
			System.out.println("5- Save and Exit");
			
			try {
			Choice = Integer.parseInt(myReader.nextLine());
			}
			catch (InputMismatchException e) {
				System.out.println("Wrong input!");
			}
			switch (Choice) {
			case 1: // Reserve Seat
				System.out.println("Reservation name: ");
				userName = myReader.nextLine();
				System.out.println("Seat Row: ");
				try {
					Row = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				System.out.println("Seat Column: ");
				try {
					Column = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				if (Row > 4|| Column > 4 || Row < 0 || Column < 0) {
					System.out.println("Out of bounds! Please input a valid row or column.");
					break;
				}
				else if (reserv[Row][Column].Name != "") {
					System.out.println("This seat is currently occupied. Please choose another.");
					break;
				}
				reserv[Row][Column].Name = userName;
				System.out.println("Reservation by " + userName + " Added to Row " + (Row + 1) + " And Column "+ (Column + 1));
				break;
			case 2: // View booking
				System.out.println("Seat Row: ");
				try {
					Row = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				System.out.println("Seat Column: ");
				try {
					Column = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				if (Row > 4|| Column > 4 || Row < 0 || Column < 0) {
					System.out.println("Out of bounds! Please input a valid row or column.");
					break;
				}
				if (reserv[Row][Column].Name == "") {
					System.out.println("This seat is currently empty.");
					break;
				}
				System.out.println("The seat is currently being occupied by: ");
				reserv[Row][Column].display();
				System.out.println("");
				break;
			case 3: // Remove Booking
				System.out.println("Seat Row to remove: ");
				try {
					Row = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				System.out.println("Seat Column to remove: ");
				try {
					Column = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				if (Row > 4|| Column > 4 || Row < 0 || Column < 0) {
					System.out.println("Out of bounds! Please input a valid row or column.");
					break;
				}
				if (reserv[Row][Column].Name == "") {
					System.out.println("This seat is already empty!");
					break;
				}
				reserv[Row][Column].Name = "";
				System.out.println("Reservation at Row " + (Row + 1) + " And Column "+ (Column + 1) + " Has been removed.");
				System.out.println("");
				break;
			case 4: // Change Seating
				int tempRow, tempColumn;
				String tempName;
				System.out.println("Seat Row to change: ");
				try {
					Row = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				System.out.println("Seat Column to change: ");
				try {
					Column = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				if (Row > 4|| Column > 4 || Row < 0 || Column < 0) {
					System.out.println("Out of bounds! Please input a valid row or column.");
					break;
				}
				if (reserv[Row][Column].Name == "") {
					System.out.println("This seat is currently empty!");
					break;
				}
				tempName = reserv[Row][Column].Name;
				tempRow = Row;
				tempColumn = Column;
				System.out.println("Acquired reservation of " + tempName + " in Row " + tempRow + " and Column " + tempColumn + ". Where would you like to move it to?");
				System.out.println("Seat Row to move to: ");
				try {
					Row = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				System.out.println("Seat Column to move to: ");
				try {
					Column = Integer.parseInt(myReader.nextLine()) - 1;
					}
					catch (InputMismatchException e) {
						System.out.println("Wrong input!");
						break;
					}
				if (Row > 4|| Column > 4 || Row < 0 || Column < 0) {
					System.out.println("Out of bounds! Please input a valid row or column.");
					break;
				}
				if (reserv[Row][Column].Name != "") {
					System.out.println("This seat is in use! Please choose a different seat.");
					break;
				}
				reserv[Row][Column].Name = tempName;
				reserv[tempRow][tempColumn].Name = "";
				System.out.println("You were moved successfully!");
				break;
			case 5: // Save and Exit
				System.out.println("Saving...");
				try {
					 writer = new PrintWriter( getFile() );
				}
				catch (FileNotFoundException e){
					System.out.println("File not found exception. Save failed.");
					break;
				}
				
				//Delete old contents, then save array data to file
				writer.print("");
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 5; j++) {
						try {
							writer.println(reserv[i][j].Name);
							System.out.println(reserv[i][j].Name);
						}
						catch (NoSuchElementException e) {
							writer.println("");
							System.out.println("");
						}
						}
					}
				System.out.println("Save successful!");
				x = false;
				writer.flush();
				break;
			}
		}
	}
}
