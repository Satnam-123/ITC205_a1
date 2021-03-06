package library.returnBook;
import library.entities.Book;
import library.entities.Library;
import library.entities.Loan;

public class ReturnBookControl {//changed rETURN_bOOK_cONTROL to ReturnBookControl

	private ReturnBookUI Ui;
	private enum ControlState { INITIALISED, READY, INSPECTING };//changed cOnTrOl_sTaTe to ControlState
	private ControlState state;//changed cOnTrOl_sTaTe to ControlState, sTaTe to state
	
	private Library library;//changed lIbRaRy to library
	private Loan currentLoan;//changed CurrENT_loan to currentLoan
	
	
public ReturnBookControl() {//changed rETURN_bOOK_cONTROL to ReturnBookControl
		this.library = Library.getInstance();//changed lIbRaRy to library, GeTiNsTaNcE to getInstance
		state = ControlState.INITIALISED;//changed sTaTe to state, cOnTrOl_sTaTe to ControlState
	}
	
	
	public void setUi(ReturnBookUI uI) {//changed sEt_uI to setUi, uI to ui
		if (!state.equals(ControlState.INITIALISED)) //changed sTaTe to state, cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		
		this.ui = ui;//changed Ui to ui,uI to ui 
		ui.setState(ReturnBookUI.uiState.READY);//changed uI to ui, sEt_sTaTe to setState , uI_sTaTe to uiState
		state = ControlState.READY;//	changed sTaTe to state, cOnTrOl_sTaTe to ControlState
	}


	public void bookScanned(int bookId) {//changed bOoK_sCaNnEd to bookScanned, bOoK_iD to bookId
		if (!state.equals(ControlState.READY)) //changed sTaTe to state, cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		
		Book currentBook = library.getBook(bookId);//changed cUrReNt_bOoK to currentBook, lIbRaRy to library, gEt_BoOk to getBook, bOoK_iD to bookId 
		 
		if (currentBook == null) {//changed cUrReNt_bOoK to currentBook
			ui.display("Invalid Book Id");//changed Ui to ui , DiSpLaY to display
			return;
		}
		if (!currentBook.isOnLoan()) {//changed cUrReNt_bOoK to currentBook, iS_On_LoAn to isOnLoan
			ui.display("Book has not been borrowed");// changed Ui to ui, DiSpLaYto display
			return;
		}		
		}		
		currentLoan = library.getLoanByBookId(bookId);//changed CurrENT_loan  to currentLoan, lIbRaRy to library, GeT_LoAn_By_BoOkId to getLoanByBookId, bOoK_iD to bookId	
		double overDueFine = 0.0;//changed Over_Due_Fine to overDueFine
		if (currentLoan.isOverDue()) // changed CurrENT_loan to currentLoan, Is_OvEr_DuE to isOverDue
			overDueFine = library.calculateOverDueFine(currentLoan);//changed Over_Due_Fine to overDueFine, lIbRaRy to library, CaLcUlAtE_OvEr_DuE_FiNe to calculateOverDueFine, CurrENT_loan to currentLoan
		
		ui.display("Inspecting");//changed Ui to ui, DiSpLaY to display
		ui.display(currentBook.toString());////changed Ui to ui, DiSpLaY to display, cUrReNt_bOoK to currentBook
		Ui.display(currentLoan.toString());//changed Ui to ui, DiSpLaY to display, CurrENT_loan to currentLoan
		
		if (currentLoan.isOverDue()) //changed CurrENT_loan to currentLoan, Is_OvEr_DuE to isOverDue
			ui.display(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));//Changed Ui to ui, DiSpLaY to display
		
		ui.setState(ReturnBookUI.uiState.INSPECTING);//changed Ui to ui, sEt_sTaTe to setState, uI_sTaTe to uiState
		state = ControlState.INSPECTING;//	sTaTe to state, cOnTrOl_sTaTe to ControlState	
	}


	public void scanningComplete() {//changed sCaNnInG_cOmPlEtE to scanningComplete
		if (!state.equals(ControlState.READY)) //changed sTaTe to state, cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
			
		ui.setState(ReturnBookUI.uiState.COMPLETED);//changed Ui to ui, sEt_sTaTe to setStae, uI_sTaTe to uiState		
	}


	public void dischargeLoan(boolean isDamaged) {//changed dIsChArGe_lOaN to dischargeLoan, iS_dAmAgEd to isDamaged
		if (!state.equals(ControlState.INSPECTING)) //changed sTaTe to state, cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		
		library.dischargeLoan(currentLoan, isDamaged);//changed lIbRaRy to library, DiScHaRgE_LoAn to dischargeLoan, CurrENT_loan to currentLoan, iS_dAmAgEd to isDamaged
		currentLoan = null;//changed CurrENT_loan to currentLoan
		ui.setStae(ReturnBookUI.uiState.READY);//changed Ui to ui, sEt_sTaTeto setState, uI_sTaTe to uiState
		state = ControlState.READY;	//changed sTaTe to state, cOnTrOl_sTaTe to ControlState			
	}



}
