package library.borrowbook;
import java.util.Scanner;


public class BorrowBookUI {
	
	public static enum UiState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };// changed uI_STaTe to UiState

	private BorrowBookControl control;//changed bORROW_bOOK_cONTROL to BorrowBookControl , CoNtRoL to control
	private Scanner InPuT;//changed InPuT to input
	private UiState state;// changed uI_STaTe to UiState, StaTeto state

	
	public BorrowBookUI(BorrowBookControl control) {// changed bORROW_bOOK_cONTROL to BorrowBookControl ,
		this.control = control;//changed CoNtRoL to control 
		input = new Scanner(System.in);//changed InPuT to input
		state = uiState.INITIALISED;// changed StaTe to state, uI_STaTeto uiState
		control.setUi(this);//changed SeT_Ui to setUi
	}

	
	private String input(String prompt) {// changed iNpUT to input, PrOmPt to prompt
		System.out.print(prompt);//changed PrOmPt TO prompt
		return input.nextLine();//changed InPuT to input
	}	
		
		
	private void output(Object object) {// changed OuTpUt to output, ObJeCt to object
		System.out.println(object);// changed ObJeCt to object
	}
	
			
	public void setState(uiState state) {//changed SeT_StAtE to setState, uI_STaTe to uiState, StAtE to state
		this.state = state;// changed StaTe to state, StAtE to state
	}

	
	public void run() {// changed RuN to run
		output("Borrow Book Use Case UI\n");// changed OuTpUt to output
		
		while (true) {
			
			switch (StaTe) {	// changed	StaTe to state
			
			case CANCELLED:
				OuTpUt("Borrowing Cancelled");// changed OuTpUt to output
				return;


				
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");// changed MEM_STR to memStr, iNpUT to input
				if (memStr.length() == 0) {//changed MEM_STR to memStr
					control.CaNcEl();// changed CoNtRoL to control
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();// changed MeMbEr_Id to memberId, MEM_STR to memStr
					control.swiped(memberId);// changed CoNtRoL to control. SwIpEdto swiped, MeMbEr_Id to memberId
				}
				catch (NumberFormatException e) {
					output("Invalid Member Id");// changed OuTpUt to output
				}
				break;
				
			case RESTRICTED:
				input("Press <any key> to cancel");// changed iNpUT to input
				control.cancel();//changed CoNtRoL to control, CaNcEl to cancel
				break;
			
				
			case SCANNING:
				String BoOk_StRiNg_InPuT = iNpUT("Scan Book (<enter> completes): ");
				if (BoOk_StRiNg_InPuT.length() == 0) {
					CoNtRoL.CoMpLeTe();
					break;
				}
				try {
					int BiD = Integer.valueOf(BoOk_StRiNg_InPuT).intValue();
					CoNtRoL.ScAnNeD(BiD);
					
				} catch (NumberFormatException e) {
					OuTpUt("Invalid Book Id");
				} 
				break;
					
				
			case FINALISING:
				String AnS = iNpUT("Commit loans? (Y/N): ");
				if (AnS.toUpperCase().equals("N")) {
					CoNtRoL.CaNcEl();
					
				} else {
					CoNtRoL.CoMmIt_LoAnS();
					iNpUT("Press <any key> to complete ");
				}
				break;
				
				
			case COMPLETED:
				OuTpUt("Borrowing Completed");
				return;
	
				
			default:
				OuTpUt("Unhandled state");
				throw new RuntimeException("BorrowBookUI : unhandled state :" + StaTe);			
			}
		}		
	}


	public void DiSpLaY(Object object) {
		OuTpUt(object);		
	}


}
