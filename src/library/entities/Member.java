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
		  .append(String.format("  Fines Owed :  $%.2f", FiNeS_OwInG))
		  .append("\n");
		
		for (Loan LoAn : cUrReNt_lOaNs.values()) {
			sb.append(LoAn).append("\n");
		}		  
		return sb.toString();
	}

	
	public int GeT_ID() {
		return MeMbEr_Id;
	}

	
	public List<Loan> GeT_LoAnS() {
		return new ArrayList<Loan>(cUrReNt_lOaNs.values());
	}

	
	public int gEt_nUmBeR_Of_CuRrEnT_LoAnS() {
		return cUrReNt_lOaNs.size();
	}

	
	public double FiNeS_OwEd() {
		return FiNeS_OwInG;
	}

	
	public void TaKe_OuT_LoAn(Loan lOaN) {
		if (!cUrReNt_lOaNs.containsKey(lOaN.GeT_Id())) 
			cUrReNt_lOaNs.put(lOaN.GeT_Id(), lOaN);
		
		else 
			throw new RuntimeException("Duplicate loan added to member");
				
	}

	
	public String GeT_LaSt_NaMe() {
		return LaSt_NaMe;
	}

	
	public String GeT_FiRsT_NaMe() {
		return FiRsT_NaMe;
	}


	public void AdD_FiNe(double fine) {
		FiNeS_OwInG += fine;
	}
	
	public double PaY_FiNe(double AmOuNt) {
		if (AmOuNt < 0) 
			throw new RuntimeException("Member.payFine: amount must be positive");
		
		double change = 0;
		if (AmOuNt > FiNeS_OwInG) {
			change = AmOuNt - FiNeS_OwInG;
			FiNeS_OwInG = 0;
		}
		else 
			FiNeS_OwInG -= AmOuNt;
		
		return change;
	}


	public void dIsChArGeLoAn(Loan LoAn) {
		if (cUrReNt_lOaNs.containsKey(LoAn.GeT_Id())) 
			cUrReNt_lOaNs.remove(LoAn.GeT_Id());
		
		else 
			throw new RuntimeException("No such loan held by member");
				
	}

}
