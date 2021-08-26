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
						int Book_Id = Integer.valueOf(BoOk_InPuT_StRiNg).intValue();
						CoNtRoL.bOoK_sCaNnEd(Book_Id);
					}
					catch (NumberFormatException e) {
						oUtPuT("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String AnS = iNpUt("Is book damaged? (Y/N): ");
				boolean Is_DAmAgEd = false;
				if (AnS.toUpperCase().equals("Y")) 					
					Is_DAmAgEd = true;
				
				CoNtRoL.dIsChArGe_lOaN(Is_DAmAgEd);
			
			case COMPLETED:
				oUtPuT("Return processing complete");
				return;
			
			default:
				oUtPuT("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + StATe);			
			}
		}
	}

	
	private String iNpUt(String PrOmPt) {
		System.out.print(PrOmPt);
		return iNpUt.nextLine();
	}	
		
		
	private void oUtPuT(Object ObJeCt) {
		System.out.println(ObJeCt);
	}
	
			
	public void DiSpLaY(Object object) {
		oUtPuT(object);
	}
	
	public void sEt_sTaTe(uI_sTaTe state) {
		this.StATe = state;
	}

	
}
