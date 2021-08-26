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




	public void RuN() {		
		oUtPuT("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (StATe) {
			
			case INITIALISED:
				break;
				
			case READY:
				String BoOk_InPuT_StRiNg = iNpUt("Scan Book (<enter> completes): ");
				if (BoOk_InPuT_StRiNg.length() == 0) 
					CoNtRoL.sCaNnInG_cOmPlEtE();
				
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
