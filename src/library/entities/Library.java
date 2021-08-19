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


	public List<Book> lIsT_BoOkS() {		
		return new ArrayList<Book>(CaTaLoG.values()); 
	}


	public List<Loan> lISt_CuRrEnT_LoAnS() {
		return new ArrayList<Loan>(CuRrEnT_LoAnS.values());
	}


	public Member aDd_MeMbEr(String lastName, String firstName, String email, int phoneNo) {		
		Member member = new Member(lastName, firstName, email, phoneNo, gEt_NeXt_MeMbEr_Id());
		MeMbErS.put(member.GeT_ID(), member);		
		return member;
	}

	
	public Book aDd_BoOk(String a, String t, String c) {		
		Book b = new Book(a, t, c, gEt_NeXt_BoOk_Id());
		CaTaLoG.put(b.gEtId(), b);		
		return b;
	}

	
	public Member gEt_MeMbEr(int memberId) {
		if (MeMbErS.containsKey(memberId)) 
			return MeMbErS.get(memberId);
		return null;
	}

	
	public Book gEt_BoOk(int bookId) {
		if (CaTaLoG.containsKey(bookId)) 
			return CaTaLoG.get(bookId);		
		return null;
	}

	
	public int gEt_LoAn_LiMiT() {
		return lOaNlImIt;
	}

	
	
	public boolean cAn_MeMbEr_BoRrOw(Member member) {		
		if (member.gEt_nUmBeR_Of_CuRrEnT_LoAnS() == lOaNlImIt ) 
			return false;
				
		if (member.FiNeS_OwEd() >= maxFinesOwed) 
			return false;
				
		for (Loan loan : member.GeT_LoAnS()) 
			if (loan.Is_OvEr_DuE()) 
				return false;
			
		return true;
	}

	
	public int gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr(Member MeMbEr) {		
		return lOaNlImIt - MeMbEr.gEt_nUmBeR_Of_CuRrEnT_LoAnS();
	}

	
	public Loan iSsUe_LoAn(Book book, Member member) {
		Date dueDate = Calendar.gEtInStAnCe().gEt_DuE_DaTe(loanPeriod);
		Loan loan = new Loan(gEt_NeXt_LoAn_Id(), book, member, dueDate);
		member.TaKe_OuT_LoAn(loan);
		book.BoRrOw();
		LoAnS.put(loan.GeT_Id(), loan);
		CuRrEnT_LoAnS.put(book.gEtId(), loan);
		return loan;
	}
	
	
	public Loan GeT_LoAn_By_BoOkId(int bookId) {
		if (CuRrEnT_LoAnS.containsKey(bookId)) 
			return CuRrEnT_LoAnS.get(bookId);
		
		return null;
	}

	
	public double CaLcUlAtE_OvEr_DuE_FiNe(Loan LoAn) {
		if (LoAn.Is_OvEr_DuE()) {
			long DaYs_OvEr_DuE = Calendar.gEtInStAnCe().GeT_DaYs_DiFfErEnCe(LoAn.GeT_DuE_DaTe());
			double fInE = DaYs_OvEr_DuE * FiNe_PeR_DaY;
			return fInE;
		}
		return 0.0;		
	}


	public void DiScHaRgE_LoAn(Loan cUrReNt_LoAn, boolean iS_dAmAgEd) {
		Member mEmBeR = cUrReNt_LoAn.GeT_MeMbEr();
		Book bOoK  = cUrReNt_LoAn.GeT_BoOk();
		
		double oVeR_DuE_FiNe = CaLcUlAtE_OvEr_DuE_FiNe(cUrReNt_LoAn);
		mEmBeR.AdD_FiNe(oVeR_DuE_FiNe);	
		
		mEmBeR.dIsChArGeLoAn(cUrReNt_LoAn);
		bOoK.ReTuRn(iS_dAmAgEd);
		if (iS_dAmAgEd) {
			mEmBeR.AdD_FiNe(damageFee);
			DaMaGeD_BoOkS.put(bOoK.gEtId(), bOoK);
		}
		cUrReNt_LoAn.DiScHaRgE();
		CuRrEnT_LoAnS.remove(bOoK.gEtId());
	}


	public void cHeCk_CuRrEnT_LoAnS() {
		for (Loan lOaN : CuRrEnT_LoAnS.values()) 
			lOaN.cHeCk_OvEr_DuE();
				
	}


	public void RePaIr_BoOk(Book cUrReNt_BoOk) {
		if (DaMaGeD_BoOkS.containsKey(cUrReNt_BoOk.gEtId())) {
			cUrReNt_BoOk.RePaIr();
			DaMaGeD_BoOkS.remove(cUrReNt_BoOk.gEtId());
		}
		else 
			throw new RuntimeException("Library: repairBook: book is not damaged");
		
		
	}
	
	
}
