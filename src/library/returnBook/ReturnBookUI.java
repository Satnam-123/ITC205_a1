package library.returnBook;
import java.util.Scanner;


public class ReturnBookUI {

	public static enum UIState { INITIALISED, READY, INSPECTING, COMPLETED };//changed uI_sTaTe to UIState

	private ReturnBookControl control;//Changed rETURN_bOOK_cONTROL to ReturnBookControl, CoNtRoL to control
	private Scanner input;//changed iNpUt to input
	private UIState state;//changed uI_sTaTe to UIState, StATe to state

	
	public ReturnBookUI(ReturnBookControl control) {//Changed rETURN_bOOK_cONTROL to ReturnBookControl, CoNtRoL to control
		this.control = control;//changed CoNtRoL to control, cOnTrOL to control
		input = new Scanner(System.in);//changed iNpUt to input
		state = uI_sTaTe.INITIALISED;//changed StATe to state
		control.setUi(this);//changed cOnTrOL to control, sEt_uI to setUi
	}




	public void run() {		//changed RuN to run
		output("Return Book Use Case UI\n");//changed oUtPuT to output
		
		while (true) {
			
			switch (state) {//changed StATe to state
			
			case INITIALISED:
				break;
				
			case READY:
				String bookInputString = input("Scan Book (<enter> completes): ");//changed BoOk_InPuT_StRiNg to bookInputString, iNpUt to input
				if (bookInputString.length() == 0) //changed BoOk_InPuT_StRiNg to bookInputString
					control.scanningComplete();//changed CoNtRoL to control, sCaNnInG_cOmPlEtE to scanningComplete
				
				
				else {
					try {
						int bookId = Integer.valueOf(bookInputString).intValue();// changed Book_Id to bookId, BoOk_InPuT_StRiNg to bookInputString
						control.bookScanned(bookId);//changed CoNtRoL to control, bOoK_sCaNnEd to bookScanned, Book_Id to bookId
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");//changed oUtPuT to output
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");//changed AnS to ans, iNpUt to input
				boolean isDamaged = false;//changed Is_DAmAgEd to isDamaged
				if (ans.toUpperCase().equals("Y")) //changed AnS to ans					
					isDamaged = true;//changed Is_DAmAgEd to isDamaged
				
				
				CoNtRoL.dIsChArGe_lOaN(Is_DAmAgEd);
			
			case COMPLETED:
				output("Return processing complete");//changed oUtPuTto output
				return;
			
			default:
				output("Unhandled state");//changed oUtPuTto output
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
			}
		}
	}

	private String input(String prompt) {//changed iNpUt to input, PrOmPt to prompt
		System.out.print(prompt);//changed PrOmPt to prompt
		return input.nextLine();//changed iNpUt to input
	}	
		
		
	private void output(Object object) {//changed oUtPuT to output,  ObJeCt to object
		System.out.println(ObJeCt);//changed ObJeCt to object
	}
	
			
	public void display(Object object) {//changed DiSpLaY to display
		output(object);//changed oUtPuT to output
	}
	
	public void setState(uiState state) {//changed sEt_sTaTe to setState, uI_sTaTe to uiState
		this.StATe = state;//changed StATe to state
	}
	
}
