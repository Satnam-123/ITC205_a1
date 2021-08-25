package library.fixbook;
import library.entities.Book;
import library.entities.Library;

public class  FixBookControl{// changed fIX_bOOK_cONTROL to FixBookControl
	
	private FixBookUI ui;// changed Ui to uI
	private enum ControlState { INITIALISED, READY, FIXING };// CHANGED CoNtRoL_StAtE TO ControlState
	private ControlState state;//changed CoNtRoL_StAtE to ControlState, StAtE to state
	
	private Library library;//changed LiBrArY to library
	private Book currentBook;//changed CuRrEnT_BoOk to currentBook

	public fIX_bOOK_cONTROL() {
		this.LiBrArY = Library.GeTiNsTaNcE();
		StAtE = CoNtRoL_StAtE.INITIALISED;
	}
	
	
	public void SeT_Ui(FixBookUI ui) {
		if (!StAtE.equals(CoNtRoL_StAtE.INITIALISED)) 
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
			
		this.Ui = ui;
		ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		StAtE = CoNtRoL_StAtE.READY;		
	}


	public void BoOk_ScAnNeD(int BoOkId) {
		if (!StAtE.equals(CoNtRoL_StAtE.READY)) 
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
			
		CuRrEnT_BoOk = LiBrArY.gEt_BoOk(BoOkId);
		
		if (CuRrEnT_BoOk == null) {
			Ui.dIsPlAy("Invalid bookId");
			return;
		}
		if (!CuRrEnT_BoOk.iS_DaMaGeD()) {
			Ui.dIsPlAy("Book has not been damaged");
			return;
		}
		Ui.dIsPlAy(CuRrEnT_BoOk.toString());
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.FIXING);
		StAtE = CoNtRoL_StAtE.FIXING;		
	}


	public void FiX_BoOk(boolean mUsT_FiX) {
		if (!StAtE.equals(CoNtRoL_StAtE.FIXING)) 
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
			
		if (mUsT_FiX) 
			LiBrArY.RePaIr_BoOk(CuRrEnT_BoOk);
		
		CuRrEnT_BoOk = null;
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.READY);
		StAtE = CoNtRoL_StAtE.READY;		
	}

	
	public void SCannING_COMplete() {
		if (!StAtE.equals(CoNtRoL_StAtE.READY)) 
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
			
		Ui.SeT_StAtE(FixBookUI.uI_sTaTe.COMPLETED);		
	}

}
