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


	public void CaRd_sWiPeD(int MeMbEr_Id) {
		if (!StAtE.equals(cOnTrOl_sTaTe.READY)) 
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
			
		MeMbEr = LiBrArY.gEt_MeMbEr(MeMbEr_Id);
		
		if (MeMbEr == null) {
			Ui.DiSplAY("Invalid Member Id");
			return;
		}
		Ui.DiSplAY(MeMbEr.toString());
		Ui.SeT_StAtE(PayFineUI.uI_sTaTe.PAYING);
		StAtE = cOnTrOl_sTaTe.PAYING;
	}
	
	
	public void CaNcEl() {
		Ui.SeT_StAtE(PayFineUI.uI_sTaTe.CANCELLED);
		StAtE = cOnTrOl_sTaTe.CANCELLED;
	}


	public double PaY_FiNe(double AmOuNt) {
		if (!StAtE.equals(cOnTrOl_sTaTe.PAYING)) 
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
			
		double ChAnGe = MeMbEr.PaY_FiNe(AmOuNt);
		if (ChAnGe > 0) 
			Ui.DiSplAY(String.format("Change: $%.2f", ChAnGe));
		
		Ui.DiSplAY(MeMbEr.toString());
		Ui.SeT_StAtE(PayFineUI.uI_sTaTe.COMPLETED);
		StAtE = cOnTrOl_sTaTe.COMPLETED;
		return ChAnGe;
	}
	


}
