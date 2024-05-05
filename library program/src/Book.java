//
// Assignment 0
// Written by: Mateo Garzon Velasco 40277001
// Purpose: the Book class generates a book with author, title, ISBN and price.
// 			The driver creates an inventory of the books created that can be accessed by the user
import java.util.Scanner;
public class Book { //Class Part 1
	
	//attributes
	private String title;
	private String author;
	private long ISBN;
	private double price;
	private static int number_of_Books =0;
	private final static String password = "249";
	private static int password_attempts = 0; 
	
	//formal constructor 
	public Book(String title, String author, long ISBN, double price) {
		this.title = title;
		this.author = author;
		this.ISBN = ISBN;
		this.price = price;
		number_of_Books += 1;//adds a book to count
		
	}
	
	public Book() {//default constructor
		this("Works of Shakespeare", "Shakespeare",243455453,100.50);
		number_of_Books +=1;//adds a book to count
	}
	
	public Book(Book anotherBook) { //copy constructor
		this.title = anotherBook.title;
		this.author = anotherBook.author;
		this.ISBN = anotherBook.ISBN;
		this.price = anotherBook.price;
	}
	
	public String GetTitle() {//title getter
		return this.title;
	}
	public void SetTitle(String newTitle) {//title setter
		this.title = newTitle;
	}
	public String GetAuthor() {//author getter
		return this.author;
	}
	public void SetAuthor(String newAuthor) {//author setter
		this.author = newAuthor;
	}
	public long GetISBN() {//ISBN getter
		return this.ISBN;
	}
	public void SetISBN(long newISBN) {//ISBN setter
		this.ISBN = newISBN;
	}
	public double GetPrice() {//price getter
		return this.price;
	}
	public void SetPrice(double newPrice) {//price setter
		this.price = newPrice;
	}
	
	public int findNumberOfCreatedBooks() {//gets book count
		return number_of_Books;
	}
	
	public boolean equals(Book another_Book) {//compares two books by price & ISBN
		return (this.ISBN == another_Book.ISBN && this.price == another_Book.price);
	}
	
	public String toString() {//gets book full info
		
		String Book_Info =  "\tAuthor: " + title + "\n" +
							"\tTitle: "+author +"\n"+
							"\tISBN: isbn " + ISBN+"\n"+
							"\tPrice: $" + price;
		return Book_Info;
		
	}
	
	
	
	public static void main(String args[]) { //driver Part 2
		
		Scanner keys = new Scanner(System.in); //initializes scanner for user
		
		String welcome_message =
		"***********Welcome to Your Book Inventory***********" + "\n" +
		"----------------------------------------------------";
		
		System.out.println(welcome_message); //welcomes user
		
		System.out.println("What maximum number of books would you like?: ");
		int maxBooks = keys.nextInt();
		Book[] inventory = new Book[maxBooks]; //user decides inventory length
		
		int remaining_space = maxBooks - number_of_Books; //for adding new books
		
		String main_menu = //recurring menu for UI
		"\nWhat do you want to do?\n" +
		"\t1. Enter new books (password required)\n"+
		"\t2. Change information of a book (password required)\n" +
		"\t3. Display all books by a specific author\n"+
		"\t4. Display all books under a certain price\n"+
		"\t5. Quit\n"+
		"Please enter your choice >";
		int option;
		
		do {//for recurring menu
			System.out.println(main_menu);
			option = keys.nextInt();
			if (option >5 || option <1) {//for invalid options
				System.out.println("That's not an option. Try again!");
				}
			
			switch(option) { //goes through options
			case 1: //creates a book
			
				String password_entered =""; 
				int local_password_attempts =0; //for the 3 code attempts
				
				do {//adds password attempts and records them
					System.out.println("Enter the password: ");
					password_entered = keys.next();
					password_attempts +=1;
					local_password_attempts +=1;
					if (password_attempts ==12 && !password_entered.equals(password)) { //12 failed password attempts exit the program 
						System.out.println("Program detected suspicious activities and will terminate immediately!");
						System.exit(1);
						}
						
					}while(!password_entered.equals(password)&& local_password_attempts <3);
				
				int number_books_entered; 
				
				do {//creates books
					
					if(local_password_attempts >=3 && !password_entered.equals(password))//go back to menu with 3 failed password attempts
						break;
					
					System.out.println("How many books do you want to enter?:");
					number_books_entered = keys.nextInt();
					
					if (number_books_entered>remaining_space) { //for when space lacks
						System.out.println("You have space for "+ remaining_space + " more book(s). Try a lower number");
					}
					else {//creates books
						for(int i = 1; i<number_books_entered+1 ;i++) {
								System.out.println("\nBook number "+ (i) + " info:\n");//book # starts at 1
								System.out.println("Enter title:"); //title
								String booktitle = keys.next();
								System.out.println("Enter author:");//author
								String bookauthor = keys.next();
								System.out.println("Enter ISBN:");//ISBN
								long bookISBN = keys.nextLong();
								System.out.println("Enter price:");//price
								double bookprice = keys.nextDouble();					
								inventory[number_of_Books] = new Book(booktitle,bookauthor,bookISBN,bookprice);//constructor used, book added to list
								System.out.println("\nYou have added "+(i) +" book(s).\n");
							}
						}
					break;
				}while(number_books_entered<=remaining_space);
				
			break;
			case 2: //changes a book
				
				local_password_attempts =0;
				do {//checks for 3 failed attempts
					System.out.println("Enter the password: ");
					password_entered = keys.next();
					local_password_attempts +=1;
				}while(!password_entered.equals(password) && local_password_attempts <3);
				
				if(local_password_attempts >=3 && !password_entered.equals(password))//goes back to menu if failed attempts >=3
					break;
				
				int book_chosen= 0;//book to update
				int exception_choice = 0;//for when index is wrong
				do {
					System.out.println("Which book would you like to update? (enter a number from 1 to "+number_of_Books+"):");//what book to update
					book_chosen = keys.nextInt();
					
					if (book_chosen > (number_of_Books)) {//if the index is out of bounds go back to menu or try again
						
						System.out.println("This is not a valid index. Would you like to:\n"
										  +"\t1. Re-enter another book\n"
										  +"\t2. Return to the menu");
						
						exception_choice = keys.nextInt();
						if (exception_choice == 1)
							continue;
						if (exception_choice ==2)//breaks out of do loop
							break;
					}
				}while(book_chosen >(number_of_Books));
				if (exception_choice ==2) //breaks out of switch 
					break;
				
				System.out.println("\tBook #"+ (book_chosen) + "\n"); 
				System.out.println(inventory[book_chosen-1].toString());//prints book chosen
				String update_menu= 
						"What information would you like to change?"
						+ "\n\t1.\tauthor\n"
						+ "\t2.\ttitle\n\t"
						+ "3.\tISBN\n\t"
						+ "4.\tprice\n\t"
						+ "5.\tQuit\nEnter your choice>";
				int attribute_choice = 0;
				do { //loops until user quits, for changing book info (uses setters)
					System.out.println(update_menu);
					attribute_choice = keys.nextInt();
					switch(attribute_choice) {
					case 1://changes author
						System.out.println("Who is this book's author?:");
						String new_author = keys.next();
						inventory[book_chosen-1].SetAuthor(new_author);
					break;
					case 2: //changes title
						System.out.println("What is the title of this book?:");
						String new_title = keys.next();
						inventory[book_chosen-1].SetTitle(new_title);
					break;
					case 3://changes ISBN
						System.out.println("What is the ISBN of this book?:");
						Long new_ISBN = keys.nextLong();
						inventory[book_chosen-1].SetISBN(new_ISBN);
					break;
					case 4: //changes price
						System.out.println("What is the price of this book?:");
						double new_price = keys.nextDouble();
						inventory[book_chosen-1].SetPrice(new_price);
					break;
					case 5:
					break;
					default:
						System.out.println("That is not a valid number try again!");
					}
				}while(attribute_choice !=5);
				
				System.out.println("Book "+ book_chosen + " updated. \n"+inventory[book_chosen].toString());//prints book
				
			break;
			case 3: //displays books of an author
				System.out.println("Which author would you like to see books of?:");
				String author_chosen = keys.next();
				int author_mentions = 0;
				for (int i =0; i<inventory.length && inventory[i]!=null;i++) {//checks filled up spaces in inventory
					if ((inventory[i].GetAuthor()).equals(author_chosen)) {//checks for author
						System.out.println("\nBook " + (i+1) +inventory[i].toString());
						author_mentions +=1;
						}
					if (author_mentions ==0 ){//no author mentions
						System.out.println("There are no books by this author");
						break;
						}
				}
			break;
			case 4:
				System.out.println("Under what price would you like to see books?:");
				double price_chosen = keys.nextDouble();
				int low_price_books = 0;
				for (int i = 0; i < inventory.length && inventory[i]!=null;i++) {//goes through the inventory
					if (inventory[i].GetPrice()<price_chosen) {//checks for prices below the price chosen
						System.out.println("\nBook \n" + (i+1) + inventory[i].toString());
						low_price_books +=1;
					}
					if(low_price_books ==0) //no books below price chosen
						System.out.println("No books are below the price chosen.");
				}
			break;
			
			}
			
		}while(option !=5);
		
		System.out.println("*********Thank you for using the Book Inventory*********");//closing message
		keys.close();
		
		
		
		
	}

}
