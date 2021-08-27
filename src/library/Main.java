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
					LIST_CURRENT_LOANS();
					break;
					
				case "P": 
					PAY_FINES();
					break;
					
				case "T": 
					INCREMENT_DATE();
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

	
	private static void PAY_FINES() {
		new PayFineUI(new pAY_fINE_cONTROL()).RuN();		
	}


	private static void LIST_CURRENT_LOANS() {
		output("");
		for (Loan loan : LIB.lISt_CuRrEnT_LoAnS()) {
			output(loan + "\n");
		}		
	}



	private static void LIST_BOOKS() {
		output("");
		for (Book book : LIB.lIsT_BoOkS()) {
			output(book + "\n");
		}		
	}



	private static void LIST_MEMBERS() {
		output("");
		for (Member member : LIB.lIsT_MeMbErS()) {
			output(member + "\n");
		}		
	}



	private static void BORROW_BOOK() {
		new BorrowBookUI(new bORROW_bOOK_cONTROL()).RuN();		
	}


	private static void RETURN_BOOK() {
		new ReturnBookUI(new rETURN_bOOK_cONTROL()).RuN();		
	}


	private static void FIX_BOOKS() {
		new FixBookUI(new fIX_bOOK_cONTROL()).RuN();		
	}


	private static void INCREMENT_DATE() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			CAL.incrementDate(days);
			LIB.cHeCk_CuRrEnT_LoAnS();
			output(SDF.format(CAL.gEt_DaTe()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void ADD_BOOK() {
		
		String AuThOr = input("Enter author: ");
		String TiTlE  = input("Enter title: ");
		String CaLl_NuMbEr = input("Enter call number: ");
		Book BoOk = LIB.aDd_BoOk(AuThOr, TiTlE, CaLl_NuMbEr);
		output("\n" + BoOk + "\n");
		
	}

	
	private static void ADD_MEMBER() {
		try {
			String LaSt_NaMe = input("Enter last name: ");
			String FiRsT_NaMe  = input("Enter first name: ");
			String EmAiL_AdDrEsS = input("Enter email address: ");
			int PhOnE_NuMbEr = Integer.valueOf(input("Enter phone number: ")).intValue();
			Member MeMbEr = LIB.aDd_MeMbEr(LaSt_NaMe, FiRsT_NaMe, EmAiL_AdDrEsS, PhOnE_NuMbEr);
			output("\n" + MeMbEr + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
