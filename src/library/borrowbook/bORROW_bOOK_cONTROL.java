package library.borrowbook;
import java.util.ArrayList;
import java.util.List;

import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;
import library.entities.Member;

public class BorrowBookControl {// changed bORROW_bOOK_cONTROL to BorrowBookControl
	
	private BorrowBookUI ui;// changed uI to ui
	
	private Library library;// changed lIbRaRy to library
	private Member member;//changed mEmBeR to member
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// changed CONTROL_STATE to ControlState
	private ControlState state;// changed CONTROL_STATE to ControlState, sTaTe to state
	
	
	
	private List<Book> pendingList;// changed pEnDiNg_LiSt to pendingList
	private List<Loan> completedList;//changed cOmPlEtEd_LiSt to completedList
	private Book book;// changed bOoK to book
	
	
	public BorrowBookControl() {// changed bORROW_bOOK_cONTROL to BorrowBookControl
		this.lIbRaRy = Library.getInstance();// changed lIbRaRy to library, GeTiNsTaNcE to getInstance
		state = ControlState.INITIALISED;// changed sTaTe to state, changed CONTROL_STATE to ControlState
	}
	

	public void setUI(BorrowBookUI ui) {//changed SeT_Ui to setUI, Ui to ui
		if (!state.equals(ControlState.INITIALISED)) // changed sTaTe to state, changed CONTROL_STATE to ControlState
			throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			
		this.ui = ui;// changed uI to ui, Ui to ui
		ui.setState(BorrowBookUI.uiState.READY);//changed Ui to ui, SeT_StAtE to setState, uI_STaTe to uiState
		state = ControlState.READY;// changed sTaTe to state , CONTROL_STATE to controlState		
	}

		
	public void swiped(int memberId) {//changed SwIpEd to swiped, mEmBeR_Id to memberId
		if (!state.equals(ControlState.READY)) // changed sTaTe to state, CONTROL_STATE to controlState
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);// changed mEmBeR to member, lIbRaRy to library, gEt_MeMbEr to getMember, mEmBeR_Id to memberId
		if (member == null) {// changed mEmBeR to member
			ui.display("Invalid memberId");//changed uI to ui, DiSpLaY to display
			return;
		}
		if (library.canMemberBorrow(member)) {// changed lIbRaRy to library, cAn_MeMbEr_BoRrOw to canMemberBorrow, mEmBeR to member
			pendingList = new ArrayList<>();// changed pEnDiNg_LiStto pendingList
			ui.setState(BorrowBookUI.uiState.SCANNING);// changed uI to ui , SeT_StAtE to setState, uI_STaTeto uiState
			state = ControlState.SCANNING; //changed sTaTe to state , CONTROL_STATE to controlState
		}
		else {
			ui.display("Member cannot borrow at this time");//changed uI to ui, DiSpLaY to display
			uI.setState(BorrowBookUI.uI_STaTe.RESTRICTED); //changed uI to ui, SeT_StAtE to setState
		}
	}
	
	
	public void scanned(int bookId) {//changed ScAnNeD to scanned, bOoKiD to bookId
		book = null;// changed bOoK to book
		if (!state.equals(controlState.SCANNING)) //changed sTaTe to state, CONTROL_STATE to controlState
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
			
		book = library.getBook(bookId);//changed bOoK to book, lIbRaRy to library, gEt_BoOk to getBook, bOoKiD to bookId
		if (book == null) {//changed bOoK to book
			ui.display("Invalid bookId");// changed uI to ui, DiSpLaY to display
			return;
		}
		if (!book.isAvailable()) {// changed bOoK to book, iS_AvAiLaBlE to isAvailable
			ui.display("Book cannot be borrowed");//changed uI to ui, DiSpLaY to display
			return;
		}
		pendingList.add(book);// changed pEnDiNg_LiSt to pendingList, bOoK to book
		for (Book B : pendingList) // changed pEnDiNg_LiSt to pendingList
			uI.display(B.toString());//changed uI to ui , DiSpLaY to display
		
		if (library.getNumberOfLoansRemainingForMember(member) - pendingList.size() == 0) {// changed lIbRaRy to library, gEt_NuMbEr_Of_LoAnS_ReMaInInG_FoR_MeMbEr to getNumberOfLoansRemainingForMember, mEmBeR to member, pEnDiNg_LiSt to pendingList
			ui.display("Loan limit reached");// changed uI to ui, DiSpLaYto display
			complete();//changed CoMpLeTe to complete
		}
	}
	
	
	public void complete() {//changed CoMpLeTe to complete
		if (pendingList.size() == 0) //changed pEnDiNg_LiSt to pendingList
			cancel();// changed CaNcEl to cancel
		
		else {
			ui.display("\nFinal Borrowing List");// changed uI to ui, DiSpLaY to display
			for (Book book : pendingList) //changed bOoK to book, pEnDiNg_LiSt to pendingList
				ui.display(book.toString());//changed uI to ui,  DiSpLaY to display, bOoK to book
			
			
			cOmPlEtEd_LiSt = new ArrayList<Loan>();
			uI.SeT_StAtE(BorrowBookUI.uI_STaTe.FINALISING);
			sTaTe = CONTROL_STATE.FINALISING;
		}
	}


	public void CoMmIt_LoAnS() {
		if (!sTaTe.equals(CONTROL_STATE.FINALISING)) 
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
			
		for (Book B : pEnDiNg_LiSt) {
			Loan lOaN = lIbRaRy.iSsUe_LoAn(B, mEmBeR);
			cOmPlEtEd_LiSt.add(lOaN);			
		}
		uI.DiSpLaY("Completed Loan Slip");
		for (Loan LOAN : cOmPlEtEd_LiSt) 
			uI.DiSpLaY(LOAN.toString());
		
		uI.SeT_StAtE(BorrowBookUI.uI_STaTe.COMPLETED);
		sTaTe = CONTROL_STATE.COMPLETED;
	}

	
	public void CaNcEl() {
		uI.SeT_StAtE(BorrowBookUI.uI_STaTe.CANCELLED);
		sTaTe = CONTROL_STATE.CANCELLED;
	}
	
	
}
