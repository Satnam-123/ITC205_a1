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
		CurrENT_loan = lIbRaRy.GeT_LoAn_By_BoOkId(bOoK_iD);	
		double Over_Due_Fine = 0.0;
		if (CurrENT_loan.Is_OvEr_DuE()) 
			Over_Due_Fine = lIbRaRy.CaLcUlAtE_OvEr_DuE_FiNe(CurrENT_loan);
		
		Ui.DiSpLaY("Inspecting");
		Ui.DiSpLaY(cUrReNt_bOoK.toString());
		Ui.DiSpLaY(CurrENT_loan.toString());
		
		if (CurrENT_loan.Is_OvEr_DuE()) 
			Ui.DiSpLaY(String.format("\nOverdue fine : $%.2f", Over_Due_Fine));
		
		Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.INSPECTING);
		sTaTe = cOnTrOl_sTaTe.INSPECTING;		
	}


	public void sCaNnInG_cOmPlEtE() {
		if (!sTaTe.equals(cOnTrOl_sTaTe.READY)) 
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
			
		Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.COMPLETED);		
	}


	public void dIsChArGe_lOaN(boolean iS_dAmAgEd) {
		if (!sTaTe.equals(cOnTrOl_sTaTe.INSPECTING)) 
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		
		lIbRaRy.DiScHaRgE_LoAn(CurrENT_loan, iS_dAmAgEd);
		CurrENT_loan = null;
		Ui.sEt_sTaTe(ReturnBookUI.uI_sTaTe.READY);
		sTaTe = cOnTrOl_sTaTe.READY;				
	}


}
