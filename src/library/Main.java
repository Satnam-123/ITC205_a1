package library;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import library.borrowbook.BorrowBookUI;
import library.borrowbook.bORROW_bOOK_cONTROL;
import library.entities.Book;
import library.entities.Calendar;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;
import library.fixbook.FixBookUI;
import library.fixbook.fIX_bOOK_cONTROL;
import library.payfine.PayFineUI;
import library.payfine.pAY_fINE_cONTROL;
import library.returnBook.ReturnBookUI;
import library.returnBook.rETURN_bOOK_cONTROL;


public class Main {
	
	private static Scanner IN;//changed IN to in
	private static Library LIB;//changed LIB to lib
	private static String MENU;//changed MENU to menu
	private static Calendar CAL;//changed CAL to cal
	private static SimpleDateFormat SDF;//changed SDF to sdf
	
	private static String Get_menu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		  .append("  M  : add member\n")
		  .append("  LM : list members\n")
		  .append("\n")
		  .append("  B  : add book\n")
		  .append("  LB : list books\n")
		  .append("  FB : fix books\n")
		  .append("\n")
		  .append("  L  : take out a loan\n")
		  .append("  R  : return a loan\n")
		  .append("  LL : list loans\n")
		  .append("\n")
		  .append("  P  : pay fine\n")
		  .append("\n")
		  .append("  T  : increment date\n")
		  .append("  Q  : quit\n")
		  .append("\n")
		  .append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {			
			in = new Scanner(System.in);//changed IN to in
			lib = Library.getInstance();//changed LIB  to lib, GeTiNsTaNcEto getInstance
			cal = Calendar.gEtInStAnCe();//changed CAL to cal, gEtInStAnCe to getInstance
			sdf = new SimpleDateFormat("dd/MM/yyyy");// changed SDF to sdf
	
		for (Member m : lib.listMembers()) {//changed lIsT_MeMbErS to listMembers, LIB to lib
				output(m);
			}
			output(" ");
			for (Book b : lib.listBooks()) {////changed lIsT_BoOkS to listBooks, LIB to lib
				output(b);
			}
						
			menu = getMenu();//changed MENU to menu, Get_menu to getMenu
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + SDF.format(CAL.gEt_DaTe()));//changed SDF  to sdf, CAL to cal, gEt_DaTe to getDate
				String c = input(MENU);//changed MENUto menu
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();//changed ADD_MEMBERto addMember
					break;
					
				case "LM": 
					listMembers();//changed LIST_MEMBERS to listMembers
					break;
					
				case "B": 
					addBook();//changed ADD_BOOK to addBook
					break;
					
				case "LB": 
					listBooks();//changed LIST_BOOKS to listBooks
					break;
					
				case "FB": 
					fixBooks();//changed FIX_BOOKSto fixBooks
					break;
					
				case "L": 
					borrowbook();//changed BORROW_BOOK to borrowbook
					break;
					
				case "R": 
					returnBook();//changed RETURN_BOOK to returnBook
					break;
				case "LL": 
					listCurrentLoans();//changed LIST_CURRENT_LOANS to listCurrentLoans
					break;
					
				case "P": 
					payFines();//changed PAY_FINES to payFines
					break;
					
				case "T": 
					incrementDate();//changed INCREMENT_DATE to incrementDate
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				
				Library.SaVe();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	
	
	private static void payFines() {//changed PAY_FINES to payFines
		new PayFineUI(new PayFineControl()).run();//changed pAY_fINE_cONTROL to PayFineControl, RuN to run		
	}


	private static void listCurrentLoans() {//changed LIST_CURRENT_LOANS to listCurrentLoans
		output("");
		for (Loan loan : lib.listCurrentLoans()) {//changed LIB to lib , lISt_CuRrEnT_LoAnSto listCurrentLoans
			output(loan + "\n");
		}		
	}



	private static void listBooks() {//changed LIST_BOOKS to listBooks
		output("");
		for (Book book : lib.listBooks()) {// changed LIB to lib, lIsT_BoOkS to listBooks
			output(book + "\n");
		}		
	}



	
	private static void listMembers() {//changed LIST_MEMBERS to listMembers
		output("");
		for (Member member : lib.listMembers()) {//changed LIB to lib ,lIsT_MeMbErS to listMembers 
			output(member + "\n");
		}		
	}



	private static void borrowBook() {//changed BORROW_BOOK to borrowBook
		new BorrowBookUI(new borrowBookControl()).run();	//changed bORROW_bOOK_cONTROL to borrowBookControl, RuN to run	
	}


	private static void returnBook() {//changed RETURN_BOOK to returnBook
		new ReturnBookUI(new returnBookControl()).run();	//chnaged rETURN_bOOK_cONTROL to returnBookControl, RuN to run	
	}


	private static void fixBooks() {//changed FIX_BOOKS to fixBooks
		new FixBookUI(new FixBookControl()).run();//changed fIX_bOOK_cONTROL to FixBookControl, RuN to run		
	}

	private static void incrementDate() {//changed INCREMENT_DATE to incrementDate
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			cal.incrementDate(days);//changed CAL to cal
			lib.checkControlLoans();//changed LIB to lib, cHeCk_CuRrEnT_LoAnS to checkControlLoans
			output(sdf.format(cal.getDate()));//changed SDF to sdf, CAL to cal,gEt_DaTe to getDate 
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {//changed ADD_BOOK to addBook
		
		String author = input("Enter author: ");//chnaged AuThOr to author
		String title  = input("Enter title: ");//changed TiTlE to title
		String callNumber = input("Enter call number: ");//changed CaLl_NuMbEr to callNumber
		Book book = lib.addBook(author, title, callNumber);//changed BoOkto book, LIBto lib, aDd_BoOk to addBook, AuThOr to author, TiTlE to title,CaLl_NuMbEr to callNumber 
		output("\n" + book + "\n");//changed BoOkto book
		
	}

	
	private static void ADD_MEMBER() {
	private static void addMember() {//changed ADD_MEMBER to addMember
		try {
			String lastName = input("Enter last name: ");//changed LaSt_NaMe to lastName
			String firstName  = input("Enter first name: ");//changed FiRsT_NaMe to firstName
			String emailAddress = input("Enter email address: ");//changed EmAiL_AdDrEsSto emailAdress
			int phoneNumber = Integer.valueOf(input("Enter phone number: ")).intValue();//changed PhOnE_NuMbEr to phoneNumber
			Member member = lib.addMember(lastName, firstName, emailAddress, phoneNumber);//changed MeMbEr to member, LIB to lib, aDd_MeMbEr to addMember, LaSt_NaMe to lastName, FiRsT_NaMe to firstName, EmAiL_AdDrEsS to emailAddress, PhOnE_NuMbEr to phoneNumber
			output("\n" + MeMbEr + "\n");//changed MeMbEr to member
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();//changed IN to in
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}

}
