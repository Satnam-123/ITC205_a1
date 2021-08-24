package library.entities;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Calendar {
	
	private static Calendar self;// changed sElF to self
	privattatic java.util.Calendar calendar; //changed cAlEnDaR to calendare s
	
	
	private Calendar() {
		calendar = java.util.Calendar.getInstance();//changed cAlEnDaR to calendar
	}
	
	public static Calendar getInstance() {// changed gEtInStAnCe to getInstance
		if (self == null) {// Changed sElF to self
			self = new Calendar();//Changed sElF to self
		}
		return self;//Changed sElF to self
	}

	public void incrementDate(int days) {
		calendar.add(java.util.Calendar.DATE, days);	//	changed cAlEnDaR to calendar
	}
	
	public synchronized void setDate(Date date) {// changed SeT_DaTe to setDate, DaTe to date
		try {
			calendar.setTime(date);// changed cAlEnDaR to calendar, DaTe to date
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0);  // changed cAlEnDaR to calendar
	        calendar.set(java.util.Calendar.MINUTE, 0);  // changed cAlEnDaR to calendar
	        calendar.set(java.util.Calendar.SECOND, 0);  // changed cAlEnDaR to calendar
	        calendar.set(java.util.Calendar.MILLISECOND, 0);// changed cAlEnDaR to calendar
		}

		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}
	public synchronized Date gEt_DaTe() {// changed gEt_DaTe to getDate
		try {
	        calendar.set(java.util.Calendar.HOUR_OF_DAY, 0); // changed cAlEnDaR to calendar 
	        calendar.set(java.util.Calendar.MINUTE, 0);  // changed cAlEnDaR to calendar 
	        calendar.set(java.util.Calendar.SECOND, 0);  // changed cAlEnDaR to calendar 
	        calendar.set(java.util.Calendar.MILLISECOND, 0);// changed cAlEnDaR to calendar 
			return calendar.getTime();// changed cAlEnDaR to calendar 
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}


	public synchronized Date gEt_DuE_DaTe(int loanPeriod) { // changed gEt_DuE_DaTe to getDueDate
		Date now = getDate();//changed nOw to now, gEt_DaTe to getDate
		calendar.add(java.util.Calendar.DATE, loanPeriod);// changed cAlEnDaR to calendar 
		Date dueDate = calendar.getTime();// changed dUeDaTe to dueDate, cAlEnDaR to calendar  
		calendar.setTime(now);// changed nOw to now, cAlEnDaR to calendar 
		return dueDate;// changed dUeDaTe to dueDate
	}
	
	public synchronized long getDaysDifference(Date targetDate) {// changed GeT_DaYs_DiFfErEnCe to getDaysDifference
		
		long DiffMillis = getDate().getTime() - targetDate.getTime();// changed gEt_DaTe to getDate, Diff_Millis to DiffMillis
	    long diffDays = TimeUnit.DAYS.convert(diffMillis, TimeUnit.MILLISECONDS);// changed Diff_Days to diffDays,  Diff_Millis to DiffMillis
	    return diffDays;// changed Diff_Days to diffDays
	}

}
