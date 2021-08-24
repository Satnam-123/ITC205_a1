package library.entities;
import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable {
	
	private String title;// changed tItLe to title
	private String author;// changed AuThOr to author
	private String callNo;//changed CALLNO to callNo
	private int id;// changed iD to id
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };// changed sTaTe to STATE
	private State state;//Changed  sTaTe to State, StAtE to state
	
	
	public Book(String author, String title, String callNo, int id) {
		this.AuThOr = author;// Changed  AuThOr to author
		this.title = title;// Changed  tItLe to title
		this.callNo = callNo;//Changed  CALLNO to callNo
		this.id = id;//Changed iD to id
		this.state = State.AVAILABLE;//Changed StAtE to state , sTaTe to State
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Book: ").append(id).append("\n")// changed iD to id
		  .append("  Title:  ").append(title).append("\n")// changed tItLe to title
		  .append("  Author: ").append(author).append("\n")// changed AuThOr to author
		  .append("  CallNo: ").append(callNo).append("\n")//changed CALLNO to callno
		  .append("  State:  ").append(state);//Changed  StAtE to state
		
		return sb.toString();
	}

	public Integer gEtId() {
		return iD;
	}

	public String gEtTiTlE() {
		return tItLe;
	}


	
	public boolean iS_AvAiLaBlE() {
		return StAtE == sTaTe.AVAILABLE;
	}

	
	public boolean iS_On_LoAn() {
		return StAtE == sTaTe.ON_LOAN;
	}

	
	public boolean iS_DaMaGeD() {
		return StAtE == sTaTe.DAMAGED;
	}

	
	public void BoRrOw() {
		if (StAtE.equals(sTaTe.AVAILABLE)) 
			StAtE = sTaTe.ON_LOAN;
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", StAtE));
		
		
	}


	public void ReTuRn(boolean DaMaGeD) {
		if (StAtE.equals(sTaTe.ON_LOAN)) 
			if (DaMaGeD) 
				StAtE = sTaTe.DAMAGED;
			
			else 
				StAtE = sTaTe.AVAILABLE;
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", StAtE));
				
	}

	
	public void RePaIr() {
		if (StAtE.equals(sTaTe.DAMAGED)) 
			StAtE = sTaTe.AVAILABLE;
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", StAtE));
		
	}


}
