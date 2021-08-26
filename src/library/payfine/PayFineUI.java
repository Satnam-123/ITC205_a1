package library.payfine;
import java.util.Scanner;


public class PayFineUI {


	public static enum uiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };//changed uI_sTaTe to uiState

	private PayFineControl CoNtRoL;//changed pAY_fINE_cONTROL to PayFineControl
	private Scanner input;
	private uiState state;//changed uI_sTaTe to uiState, StAtE to state

	
	public PayFineUI(PayFineControl control) {//changed pAY_fINE_cONTROL to PayFineControl
		this.control = control;//changed CoNtRoL to control
		input = new Scanner(System.in);
		state = uiState.INITIALISED;//changed StAtE to state, uI_sTaTe to uiState
		control.setUI(this);//changed SeT_uI to setUI
	}
	
	
	public void setState(uiState state) {//Changed SeT_StAtE to setState, uI_sTaTe to uiState
		this.state = state;//Changed StAtE to state, 
	}



	public void run() {//changed RuN to run
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {//changed StAtE to state
			
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");//changed Mem_Str to memStr
				if (memStr.length() == 0) {//changed Mem_Str to memStr
					control.cancel();//changed CoNtRoL to control, CaNcEl to cancel
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();//changed Member_ID to memberId, Mem_Str to memStr
					control.cardSwiped(memberId);//changed CoNtRoL to control, CaRd_sWiPeD to cardSwiped, Member_ID to memberId
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;

				
			case PAYING:
				double AmouNT = 0;
				String Amt_Str = input("Enter amount (<Enter> cancels) : ");
				if (Amt_Str.length() == 0) {
					CoNtRoL.CaNcEl();
					break;
				}
				try {
					AmouNT = Double.valueOf(Amt_Str).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (AmouNT <= 0) {
					output("Amount must be positive");
					break;
				}
				CoNtRoL.PaY_FiNe(AmouNT);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + StAtE);			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object object) {
		System.out.println(object);
	}	
			

	public void DiSplAY(Object object) {
		output(object);
	}


}
