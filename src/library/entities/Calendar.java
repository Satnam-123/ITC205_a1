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
	public synchronized Date gEt_DaTe() {
		try {
	        cAlEnDaR.set(java.util.Calendar.HOUR_OF_DAY, 0);  
	        cAlEnDaR.set(java.util.Calendar.MINUTE, 0);  
	        cAlEnDaR.set(java.util.Calendar.SECOND, 0);  
	        cAlEnDaR.set(java.util.Calendar.MILLISECOND, 0);
			return cAlEnDaR.getTime();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}	
	}

	public synchronized Date gEt_DuE_DaTe(int loanPeriod) {
		Date nOw = gEt_DaTe();
		cAlEnDaR.add(java.util.Calendar.DATE, loanPeriod);
		Date dUeDaTe = cAlEnDaR.getTime();
		cAlEnDaR.setTime(nOw);
		return dUeDaTe;
	}
	
	public synchronized long GeT_DaYs_DiFfErEnCe(Date targetDate) {
		
		long Diff_Millis = gEt_DaTe().getTime() - targetDate.getTime();
	    long Diff_Days = TimeUnit.DAYS.convert(Diff_Millis, TimeUnit.MILLISECONDS);
	    return Diff_Days;
	}

}
