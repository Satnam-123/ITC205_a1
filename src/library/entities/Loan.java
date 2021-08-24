package library.entities;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };//changed lOaN_sTaTe to LoanState
	
	private int loanId;// changed LoAn_Id to loanId
	private Book book;//changed BoOk to book
	private Member member;//changed  MeMbEr to member
	private Date date;// changed DaTe to date
	private LoanState state;// Changed lOaN_sTaTe to LoanState, StAtE to state

	
	public Loan(int loanId, Book book, Member member, Date dueDate) {// Changed bOoK to book, mEmBeR to member, DuE_dAtE to dueDate
		this.loanId = loanId;// changed LoAn_Id to LoanId
		this.book = book;//changed BoOk to book , bOoK to book
		this.member = member;//changed MeMbEr to member , mEmBeR to member
		this.date = dueDate;//changed DaTe to date, DuE_dAtE to dueDate
		this.state = LoanState.CURRENT;//chnaged StAtE to state, lOaN_sTaTe to LoanState
	}

	
	public void checkOverDue() {//changed cHeCk_OvEr_DuE to checkOverDue
		if (state == LoanState.CURRENT && // changed StAtE to state, lOaN_sTaTe to LoanState
			Calendar.getInstance().getDate().after(date))  // changed gEtInStAnCe to getInstance, gEt_DaTe to getDate, DaTe to date
			this.state = LoanState.OVER_DUE;	// 	changed	StAtE to state, lOaN_sTaTe to LoanState
		
	}


	
	public boolean isOverDue() {// changed Is_OvEr_DuE to isOverDue
		return state == LoanState.OVER_DUE;//changed StAtE to state, lOaN_sTaTe to LoanState
	}

	
	public Integer getId() {//changed GeT_Id to getId
		return loanId;// changed LoAn_Id to loanId
	}


	public Date getDueDate() {// changed GeT_DuE_DaTe to getDueDate
		return date;// changed DaTe to date
	}
	
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(loanId).append("\n")// changed LoAn_Id to loanId
		  .append("  Borrower ").append(member.getId()).append(" : ")// changed MeMbEr to member, GeT_ID to getId
		  .append(member.getLastName()).append(", ").append(member.getFirstName()).append("\n")// changed MeMbEr to member, GeT_LaSt_NaMe to getLastName, GeT_FiRsT_NaMe to getFirstName
		  .append("  Book ").append(book.getId()).append(" : " )// changed BoOk to book, gEtId to getId
		  .append(book.getTitle()).append("\n")//// changed BoOk to book, gEtTiTlE to getTitle
		  .append("  DueDate: ").append(sdf.format(date)).append("\n")//changed DaTe to date 
		  .append("  State: ").append(state);// changed StAtE to state 		
		return sb.toString();
	}


	public Member getMember() {// changed GeT_MeMbEr to getMember
		return member;// changed MeMbEr to member
	}


	public Book getBook() {// changed GeT_BoOk to getBook
		return book;// changed BoOk to book
	}


	public void discharge() {//changed DiScHaRgE to discharge
		state = LoanState.DISCHARGED;	// 	changed StAtE to state, lOaN_sTaTe to LoanState
	}

}
