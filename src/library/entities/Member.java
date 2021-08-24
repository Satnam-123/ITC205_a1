package library.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class Member implements Serializable {

	
	private String lastName;// changed LaSt_NaMe to lastName
	private String firstName;//changed FiRsT_NaMe to firstName
	private String emaiAddress;//changed EmAiL_AdDrEsS to emailAddress
	private int phoneNumber;// changed PhOnE_NuMbEr to phoneNumber
	private int memberId;// changed MeMbEr_Id to memberId
	private double finesOwing;// changed FiNeS_OwInG to finesOwing

	
	private Map<Integer, Loan> currentLoans;//changed cUrReNt_lOaNs  to currentLoans

	
	public Member(String lastName, String firstName, String emailAddress, int phoneNumber, int memberId) {//changed LaSt_NaMe to lastName, fIrSt_nAmE to FirstName, eMaIl_aDdReSs to emailAddress, pHoNe_nUmBeR to phoneNumber, mEmBeR_iD to memberId
		this.lastName = lastName;//changed LaSt_NaMe to lastName
		this.firstName = firstName;//changed FiRsT_NaMe to firstName
		this.emaiAddress = emaiAddress;//changed EmAiL_AdDrEsS to emailAddress
		this.phoneNumber = phoneNumber;// changed PhOnE_NuMbEr to phoneNumber
		this.memberId = memberId;// changed MeMbEr_Id to memberId
		
		this.currentLoans = new HashMap<>();//changed cUrReNt_lOaNs  to currentLoans
	}

	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Member:  ").append(memberId).append("\n")//changed MeMbEr_Id to memberId
		  .append("  Name:  ").append(lastName).append(", ").append(firstName).append("\n")// changed LaSt_NaMe to lastName, FiRsT_NaMe to firstName
		  .append("  Email: ").append(emailAddress).append("\n")//changed EmAiL_AdDrEsS to emailAddress 
		  .append("  Phone: ").append(phoneNumber)//changed PhOnE_NuMbEr to phoneNumber
		  .append("\n")
		  .append(String.format("  Fines Owed :  $%.2f", finesOwing))//changed FiNeS_OwInG to finesOwing 
		  .append("\n");
		
		for (Loan loan : currentLoans.values()) {//changed LoAn to loan, cUrReNt_lOaNs to currentLoans
			sb.append(loan).append("\n");// changed LoAn to loan
		}		  
		return sb.toString();
	}

	
	public int getId() { //changed GeT_ID to getId
		return memberId;// changed MeMbEr_Id to memberId
	}

	
	public List<Loan> getLoans() {//changed GeT_LoAnS to getLoans
		return new ArrayList<Loan>(currentLoans.values());//changed cUrReNt_lOaNs to currentLoans
	}

	
	public int getNumberOfCurrentLoans() {//changed gEt_nUmBeR_Of_CuRrEnT_LoAnS to getNumberOfCurrentLoans
		return currentLoans.size();// changed cUrReNt_lOaNs to currentLoans
	}

	
	public double finesOwed() {// changed FiNeS_OwEd to finesOwed
		return finesOwing;// changed FiNeS_OwInG to finesOwing
	}

	
	public void takeOutLoan(Loan loan) {// changed TaKe_OuT_LoAn to takeOutLoan, lOaN to loan
		if (!currentLoans.containsKey(loan.getId())) //changed cUrReNt_lOaNs to currentLoans, lOaN to loan, GeT_Id to getId
			currentLoans.put(loan.getId(), loan);//changed cUrReNt_lOaNs to currentLoans, lOaN to loan, GeT_Id to getId
		
		else 
			throw new RuntimeException("Duplicate loan added to member");
				
	}


	
	public String getLastName() {// changed GeT_LaSt_NaMe to getLastName
		return lastName;// changed LaSt_NaMe to lastName
	}

	
	public String getFirstName() {// changed GeT_FiRsT_NaMe to getFirstName
		return firstName;// changed FiRsT_NaMe to firstName
	}


	public void addFine(double fine) {// changed AdD_FiNe TO addFine
		finesOwing += fine; // changed FiNeS_OwInG TO finesOwing
	}
	
	public double payFine(double amount) {// changed PaY_FiNe to payFine, AmOuNt to amount
		if (amount < 0) // changed  AmOuNt to amount
			throw new RuntimeException("Member.payFine: amount must be positive");
		
		double change = 0;
		if (amount > finesOwing) {// changed AmOuNt to amount, FiNeS_OwInG to finesOwing
			change = amount - finesOwing;//changed AmOuNt to amount, FiNeS_OwInG to finesOwing
			finesOwing = 0;//changed FiNeS_OwInG to finesOwing
		}
		else 
			finesOwing -= amount;//changed AmOuNt to amount, FiNeS_OwInG to finesOwing
		
		return change;
	}



	public void dIsChArGeLoAn(Loan LoAn) {
		if (cUrReNt_lOaNs.containsKey(LoAn.GeT_Id())) 
			cUrReNt_lOaNs.remove(LoAn.GeT_Id());
		
		else 
			throw new RuntimeException("No such loan held by member");
				
	}

}
