package library.payfine;
import library.entities.Library;
import library.entities.Member;

public class payFineControl {//change pAY_fINE_cONTROL to payFineControl
	
	private PayFineUI ui;//changed Ui to ui
	private enum ControlState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };//chnaged cOnTrOl_sTaTe to ControlState
	private ControlState state;//chnaged cOnTrOl_sTaTe to ControlState, StAtE to state
	
	private Library library;// changed LiBrArY to library
	private Member member;// changed MeMbEr to member



	public payFineControl() {////change pAY_fINE_cONTROL to payFineControl
		this.library = Library.getInstance();//changed LiBrArY to library, GeTiNsTaNcE to getInstance
		StAtE = ControlState.INITIALISED;//changed StAtE to state, cOnTrOl_sTaTeto ControlState 
	}
	
	
	public void setUI(PayFineUI ui) {//changed SeT_uI to setUI, uITO ui
		if (!state.equals(ControlState.INITIALISED)) {//changed StAtEto state, cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;//changed Ui to ui,uI to ui 
		ui.setState(PayFineUI.uiState.READY);//changed Ui to ui,uI to ui , SeT_StAtE to setState, uI_sTaTe to uiState
		state = ControlState.READY;//	changed StAtE to state, cOnTrOl_sTaTe to ControlState	
	}


	public void cardSwiped(int memberId) {// changed CaRd_sWiPeD to cardSwiped, MeMbEr_Id to memberId
		if (!state.equals(ControlState.READY)) //changed StAtE to state, cOnTrOl_sTaTe to ControlState
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		member = library.getMember(memberId);//changed MeMbEr to member, LiBrArY to library, gEt_MeMbEr to getMember, MeMbEr_Id to memberId 
		
		if (member == null) {//changed MeMbEr to member
			ui.display("Invalid Member Id");//changed Ui to ui, DiSplAY to display
			return;
		}
		ui.display(member.toString());////changed Ui to ui, DiSplAY to display, MeMbEr to member
		ui.setState(PayFineUI.uiState.PAYING);//changed Ui to ui, SeT_StAtE to setState, uI_sTaTe to uiState
		state = ControlState.PAYING;//changed StAtE to state, cOnTrOl_sTaTe to ControlState
	}

	
	public void cancel() {//changed CaNcEl to cancel
		ui.setState(PayFineUI.uiState.CANCELLED);//changed Uito ui, SeT_StAtE to setState, uI_sTaTe to uiState
		state = ControlState.CANCELLED;//changed StAtE to state, cOnTrOl_sTaTe to ControlState
	}


	public double PaY_FiNe(double AmOuNt) {//changed PaY_FiNeto payFine,AmOuNtto amount 
		if (!StAtE.equals(cOnTrOl_sTaTe.PAYING)) //changed StAtE to state, cOnTrOl_sTaTeto ControlState
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double ChAnGe = MeMbEr.PaY_FiNe(AmOuNt);//changed ChAnGe to change , MeMbErto member, PaY_FiNe to payFine
		if (change > 0) //changed ChAnGe to change
			ui.display(String.format("Change: $%.2f", change)); //changed Ui to ui, DiSplAY to display ChAnGe to change
		
		ui.display(member.toString());//changed Ui to ui, DiSplAY to display MeMbEr to member
		ui.setState(PayFineUI.uiState.COMPLETED);//changed Ui to ui, SeT_StAtE to setState, uI_sTaTe TO uiState
		state = ControlState.COMPLETED;//changed StAtE to state, cOnTrOl_sTaTe to ControlState
		return change;//changed ChAnGe to change
	}
	


}

}
