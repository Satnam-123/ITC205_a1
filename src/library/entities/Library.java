package library.entities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Library implements Serializable {
	
	private static final String library_file = "library.obj";// changed lIbRaRyFile to Library_file
	private static final int loan_limit = 2;//changed lOaNlImIt to loan_limit
	private static final int loan_period = 2; // changed loanPeriod to loan_period
	private static final double fine_per_day = 1.0; //changed FiNe_PeR_DaY to fine_per_day
	private static final double max_fines_owed = 1.0;//changed maxFinesOwed to max_fines_owed
	private static final double damage_fee = 2.0;// changed damageFee to damage_fee
	

	private static Library seLf; 
	private int bookId; 
	private int memberId; 
	private int loanId; 
	private Date loanDate;
  
	private static Library seLf; // Changed 'Self' to 'self'
	private int bookId; // Changed 'bOoK_Id' to 'bookId'
	private int memberId; // Changed 'mEmBeR_Id' to 'memberId'
	private int loanId; // Changed 'lOaN_Id' to 'loanId'
	private Date loanDate; // Changed 'lOaN_DaTe' to 'loanDate'

	
	private Map<Integer, Book> catalog;   // changed 'CaTaLoG' to 'catalog'
	private Map<Integer, Member> members;  // changed 'MeMbErS to 'members'
	private Map<Integer, Loan> loans;   // changed 'LoAnS' 'to loans' 
	private Map<Integer, Loan>currentloans;  //changed 'CuRrEnT_LoAnS' to 'currentloans' 
	private Map<Integer, Book> damagedbooks;   //changes 'DaMaGeD_BoOkS' to 'damagedbooks'
	

	private Library() {
		catalog = new HashMap<>();//changed CaTaLoG to catalog
		members = new HashMap<>();// changed MeMbErS to members
		loans = new HashMap<>();// changed LoAnS to loans
		currentloans = new HashMap<>();// changed CuRrEnT_LoAnS to currentloans
		damagedbooks = new HashMap<>();//changed DaMaGeD_BoOkS to damagedbooks
		bookId = 1;
		memberId = 1;		
		loanId = 1;		
	}

	
	public static synchronized Library getInstance() {// changed GeTiNsTaNcE to getInstance		
		if (self == null) {// changed SeLf to self
			Path PATH = Paths.get(libraryFile);// changed lIbRaRyFiLe to libraryFile			
			if (Files.exists(path)) {// changed PATH to path	
				try (ObjectInputStream LiBrArY_FiLe = new ObjectInputStream(new FileInputStream(lIbRaRyFiLe));) {//changed LiBrArY_FiLe to libraryFile ,changed lIbRaRyFiLe to library_file
			    
					SeLf = (Library) LiBrArY_FiLe.readObject();// changed LiBrArY_FiLe to library_file
					Calendar.getInstance().setDate(self.loanDate);//changed gEtInStAnCe to getInstance and SeT_DaTe to setDate ,SeLf.lOaN_DaTe to self.loanDate
					LiBrArY_FiLe.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else Self = new Library();
		}
		return Self;
	}

	
	public static synchronized void Save() {
		if (Self != null) {
			self.loanDate = Calendar.getInstance().getDate();
			try (ObjectOutputStream LibraryFile = new ObjectOutputStream(new FileOutputStream(library_File));) {
				library_File.writeObject(SeLf);
				library_File.flush();
				library_File.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int getBookId() {//changed gEt_BoOkId to getBookId
		return bOoK_Id;// changed  bOoK_Id to bookId
	}

	
	public int getMemberId() {
		return memberId;//Changed mEmBeR_Id to memberId
	}
	   

	
	
	private int getNextBookId() {//Changed gEt_NeXt_BoOk_Id() to getNextBookId()
		return bookId++;//changed bOoK_Id to bookId
	}
	


	
	private int getNextBookId() {
		return memberId++;
	}

	
	private int getNextLoanId() {
		return loanId++;
	}

	
	public List<Member> listMembers() {// 	Changed lIsT_MeMbErS() to listMembers()		
		return new ArrayList<Member>(members.values()); //Changed MeMbErS() to members()
	
	}


	public List<Book>  listBooks() {//Changed lIsT_BoOkS() to listBooks()			
		return new ArrayList<Book>( catalog .values());//Changed CaTaLoG to catalog 
	}


	public List<Loan> listCurrentLoans() { //Changed lISt_CuRrEnT_LoAnS() to listCurrentLoans()
		return new ArrayList<Loan>listCurrentLoans() { //Changed lISt_CuRrEnT_LoAnS() to listCurrentLoans()}
	}


	public Member addMember(String lastName, String firstName, String email, int phoneNo) { //changed aDd_MeMbEr to addMember		
		Member member = new Member(lastName, firstName, email, phoneNo, getNextMemberId()); // changed gEt_NeXt_MeMbEr_Id to getNextMemberId()
		member.put(member.getId(), member);	//Changed GeT_ID to getId()	MeMbErS to member
		return member;
	}

	
	public Book addBook(String a, String t, String c) {//Changed aDd_BoOk	to addBook	
		Book b = new Book(a, t, c, getNextBookId());//changed gEt_NeXt_BoOk_Id to getNextBookId
		catalog.put(b.getId(), b);	//changed CaTaLoG to catalog and changed gEtId	to getId
		return b;
	}

	
	public Member getMember(int memberId) {// changed gEt_MeMbEr to getMember
		if (members.containsKey(memberId)) //changed MeMbErS to members
			return members.get(memberId);//changed MeMbErS to members
		return null;
	}

	
	public Book getBook(int bookId) {// changed gEt_BoOk to getBook
		if (catalog.containsKey(bookId)) // changed CaTaLoG to catalog
			return catalog.get(bookId);		// changed CaTaLoG to catalog
		return null;
	}

	
	public int getLoanLimit() {// changed gEt_LoAn_LiMiT to getLoanLimit
		return lOaNlImIt;// Changed lOaNlImIt to loanLimit
	}

	
	
	public boolean canMemberBorrow(Member member) {// changed cAn_MeMbEr_BoRrOw to canMemberBorrow		
		if (member.gEt_nUmBeR_Of_CuRrEnT_LoAnS() == lOaNlImIt ) // changed gEt_nUmBeR_Of_CuRrEnT_LoAnS to getNumberOfCurrentLoans and lOaNlImIt to loanLimit
			return false;
				
		if (member.finesOwed() >= maxFinesOwed) // changed FiNeS_OwEd to finesOwed
			return false;
				
		for (Loan loan : member.getLoans()) // changed GeT_LoAnS to getLoans
			if (loan.isOverDue()) // changed Is_OvEr_DuE to isOverDue
				return false;
			
		return true;
	}

	
	public int getNumberOfLoansRemainingForMember(Member member) {//changed gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr to getNumberOfLoansRemainingForMember	and MeMbEr TO member	
		return loanLimit - member.getNumberOfCurrentLoans(); // changed lOaNlImIt to loanLimit and MeMbEr to member and gEt_nUmBeR_Of_CuRrEnT_LoAnS to getNumberOfCurrentLoans
	}
	
	public Loan issueLoan(Book book, Member member) {//changed iSsUe_LoAn to issueLoan
		Date dueDate = Calendar.gEtInStAnCe().gEt_DuE_DaTe(loanPeriod);//changed gEtInStAnCe to getInstance and gEt_DuE_DaTe to getDueDate
		Loan loan = new Loan(gEt_NeXt_LoAn_Id(), book, member, dueDate); //changed gEt_NeXt_LoAn_Id to getNextLoanId
		member.takeOutLoan(loan);// changed TaKe_OuT_LoAn to takeOutLoan
		book.borrow();// changed BoRrOw to borrow 
		loans.put(loan.getId(), loan);// changed LoAnS to loans and GeT_Id to getId
		currentLoans.put(book.getId(), loan);// changed CuRrEnT_LoAnS to currentLoans, gEtId to getId
		return loan;
	}
	
	
	public Loan getLoanByBookId(int bookId) { //changed GeT_LoAn_By_BoOkId to getLoanByBookId
		if (currentLoans.containsKey(bookId)) //changed CuRrEnT_LoAnS to currentLoans
			return currentLoans.get(bookId);//changed CuRrEnT_LoAnS to currentLoans
		
		return null;
	}

	
	public double calculateOverDueFine(Loan loan) {// changed CaLcUlAtE_OvEr_DuE_FiNe to calculateOverDueFine and LoAn to loan
		if (LoAn.isOverDue()) {//changed LoAn to loan and Is_OvEr_DuE to isOverDue
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());// Changed DaYs_OvEr_DuE to daysOverDue, gEtInStAnCe to getInstance, GeT_DaYs_DiFfErEnCe to getDaysDifference, LoAn to loan, GeT_DuE_DaTe to getDueDate
			double fine = daysOverDue * finePerDay;// changed fInE to fine, DaYs_OvEr_DuE to daysOverDue, FiNe_PeR_DaY to finePerDay
			return fine;//changed fInE to fine
		}
		return 0.0;	
	}


	public void dischargeLoan(Loan currentLoan, boolean isDamaged) {//changed DiScHaRgE_LoAn to dischargeLoan, cUrReNt_LoAn to currentLoan, iS_dAmAgEd to isDamaged
		Member member = currentLoan.getMember();// changed mEmBeR to member, cUrReNt_LoAn to currentLoan, GeT_MeMbEr to getMember
		Book book  = currentLoan.getBook();//changed bOoK to book, cUrReNt_LoAn to currentLoan, GeT_BoOk to getBook
		
		double overDueFine = calculateOverDueFine(currentLoan);// changed oVeR_DuE_FiNe to overDueFine, CaLcUlAtE_OvEr_DuE_FiNe to calculateOverDueFine, cUrReNt_LoAn TO currentLoan
		member.addFine(overDueFine);	// changed mEmBeR to member, AdD_FiNe to addFine and oVeR_DuE_FiNe to OverDueFine
		
		member.dischargeLoan(currentLoan);// changed mEmBeR to member, dIsChArGeLoAn to dischargeLoan, cUrReNt_LoAn to currentLoan
		book.Return(isDamaged);//changed bOoK to book, ReTuRn to Return , iS_dAmAgEd to isDamaged
		if (isDamaged) {// changed iS_dAmAgEd to isDamaged
			member.addFine(damageFee);//changed mEmBeR to member, AdD_FiNe to addFine
			damagedBooks.put(book.getId(), book);// Changed bOoK to book, gEtId to getId
		}
		currentLoan.discharge();// changed cUrReNt_LoAn to currentLoan, DiScHaRgE to discharge
		currentLoan.remove(book.getId()); // changed cUrReNt_LoAn to currentLoan, bOoK to book, gEtId to getId
	}

	public void checkCurrentLoans() {// changed cHeCk_CuRrEnT_LoAnS to checkCurrentLoans
		for (Loan loan : currentLoans.values()) // changed lOaN to loan, CuRrEnT_LoAnS to currentLoans
			loan.checkOverDue();// changed lOaN to loan, cHeCk_OvEr_DuE to checkOverDue
				
	}


	public void repairBook(Book currentBook) { // changed RePaIr_BoOk to repairBook, cUrReNt_BoOk to currentBook
		if (damagedBooks.containsKey(currentBook.getId())) { // changed DaMaGeD_BoOkS to damagedBooks, cUrReNt_BoOk to currentBook, gEtId to getId
			currentBook.repair();// changed cUrReNt_BoOk to currentBook, RePaIr to repair
			damagedBooks.remove(currentBook.getId());// changed DaMaGeD_BoOkS to damagedBooks, cUrReNt_BoOk to currentBook, gEtId to getId
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
}
