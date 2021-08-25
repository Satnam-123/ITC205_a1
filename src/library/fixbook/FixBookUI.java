package package library.fixbook;
import java.util.Scanner;


public class FixBookUI {

	public static enum UiState { INITIALISED, READY, FIXING, COMPLETED };//changed uI_sTaTe to UiState

	private FixBookControl control;//changed fIX_bOOK_cONTROL to FixBookControl ,  CoNtRoL to control
	private Scanner input;// changed InPuT to input
	private UiState state;//changed uI_sTaTe to UiState, StAtE to state

	
	public FixBookUI(FixBookControl control) {//changed fIX_bOOK_cONTROL to FixBookControl ,  CoNtRoL to control
		this.control = control;//changed  CoNtRoL to control
		input = new Scanner(System.in);// changed InPuT to input
		state = UiState.INITIALISED;//changed StAtE to state, uI_sTaTe  to UiState
		control.setUi(this);// changed CoNtRoL to control, SeT_Uito setUi
	}


	public void setState(UiState state) {//changed SeT_StAtE to setState, uI_sTaTe TO UiState
		this.state = state;//changed StAtE to state
	}

	
	public void run() {//changed RuN to run
		output("Fix Book Use Case UI\n");//changed OuTpUt to output
		
		while (true) {
			
			switch (StAtE) {// changed StAtE to state
			
			case READY:
				String bookEntryString = input("Scan Book (<enter> completes): ");// changed BoOk_EnTrY_StRiNg to bookEntryString, iNpUt to input 
				if (bookEntryString.length() == 0) // changed BoOk_EnTrY_StRiNg to bookEntryString
					control.scanningComplete();//changed CoNtRoL to control, SCannING_COMplete to scanningComplete
				
				else {
					try {
						int bookId = Integer.valueOf(bookEntryString).intValue();// changed BoOk_Id to bookId, BoOk_EnTrY_StRiNg to bookEntryString
						control.bookScanned(bookId);//changed CoNtRoL to control, BoOk_ScAnNeD to bookScanned, BoOk_Id to bookId
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");//changed OuTpUt to output
					}
				}
				break;	
				
			case FIXING:
				String ans = input("Fix Book? (Y/N) : ");//changed AnS to ans, iNpUt to input
				boolean fix = false;// changed FiX to fix
				if (ans.toUpperCase().equals("Y")) //changed AnS to ans
					fix = true;//changed FiX to fix
				
				control.fixBook(fix);//changed CoNtRoL TO control, FiX_BoOk to fixBook, FiX to fix
	
				
					break;
								
			case COMPLETED:
				output("Fixing process complete");// changed OuTpUt to output
				return;
			
			default:
				output("Unhandled state");// changed OuTpUt to output
				throw new RuntimeException("FixBookUI : unhandled state :" + state);// changed 	StAtEto state		
			
			}		
		}
		
	}

					

	
	private String iNpUt(String prompt) {
		System.out.print(prompt);
		return InPuT.nextLine();
	}	
		
		
	private void OuTpUt(Object object) {
		System.out.println(object);
	}
	

	public void dIsPlAy(Object object) {
		OuTpUt(object);
	}
	
	
}
