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


	
	public boolean Is_OvEr_DuE() {
		return StAtE == lOaN_sTaTe.OVER_DUE;
	}

	
	public Integer GeT_Id() {
		return LoAn_Id;
	}


	public Date GeT_DuE_DaTe() {
		return DaTe;
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(LoAn_Id).append("\n")
		  .append("  Borrower ").append(MeMbEr.GeT_ID()).append(" : ")
		  .append(MeMbEr.GeT_LaSt_NaMe()).append(", ").append(MeMbEr.GeT_FiRsT_NaMe()).append("\n")
		  .append("  Book ").append(BoOk.gEtId()).append(" : " )
		  .append(BoOk.gEtTiTlE()).append("\n")
		  .append("  DueDate: ").append(sdf.format(DaTe)).append("\n")
		  .append("  State: ").append(StAtE);		
		return sb.toString();
	}


	public Member GeT_MeMbEr() {
		return MeMbEr;
	}


	public Book GeT_BoOk() {
		return BoOk;
	}


	public void DiScHaRgE() {
		StAtE = lOaN_sTaTe.DISCHARGED;		
	}

}
