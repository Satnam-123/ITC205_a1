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

	public Integer getId() {// changed gEtId to getId
		return id; // changed iD to id
	}

	public String getTitle() {// changed gEtTiTlE to getTitle
		return title; // changed tItLe to title
	}


	
	public boolean isAvailable() {// changed iS_AvAiLaBlE to isAvailable
		return state == State.AVAILABLE;// changed StAtE to state, sTaTe to State
	}

	
	public boolean isOnLoan() {// changed iS_On_LoAn to isOnLoan
		return state == State.ON_LOAN;// changed StAtE to state, sTaTe to State
	}

	
	public boolean isDamaged() {// changed iS_DaMaGeD to isDamaged
		return state == State.DAMAGED;// changed StAtE to state, sTaTe to State
	}

	
	public void borrow() {// changed BoRrOw to borrow
		if (state.equals(State.AVAILABLE)) //changed StAtE to state, sTaTe to State
			state = State.ON_LOAN;// //changed StAtE to state, sTaTe to State
		
		else 
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));//changed StAtE to state
		
		
	}


	public void Return(boolean damaged) {// changed ReTuRn to Return, DaMaGeD to damaged
		if (state.equals(State.ON_LOAN)) // changed StAtE to state, DaMaGeD to damaged
			if (DAMAGED) //changed DaMaGeD to DAMAGED
				state = State.DAMAGED; //changed StAtE to state, sTaTe to State
			
			else 
				state = State.AVAILABLE; //changed StAtE to state, sTaTe to State
			
		
		else 
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));//changed StAtE to state
				
	}

	
	public void repair() {// changed RePaIr to repair
		if (state.equals(State.DAMAGED))//  //changed StAtE to state, sTaTe to State
			state = State.AVAILABLE;//changed StAtE to state, sTaTe to State
		
		else 
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));//changed StAtE to state
		
	}


}
