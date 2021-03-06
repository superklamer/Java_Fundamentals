// ex 8.14

public class Date2 
{
   private int month; // 1-12
   private int day; // 1-31 based on month
   private int year; // any year
   
   private static final int[] daysInYear = 
	   {0,31,59,90,120,151,181,212,243,273,304,334,365};

   private static final int[] daysPerMonth = 
      {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
   
   // constructor: confirm proper value for month and day given the year
   public Date2(int month, int day, int year)
   {
      setMonth(month);
      setDay(day);
      setYear(year);

      System.out.printf(
         "Date object constructor for date %s%n", this);
   } 
   
   public Date2(String strMnth, int day, int year) {
	   Date2Enum monthNameToInt = Date2Enum.valueOf(strMnth.toUpperCase());
	      setMonth(monthNameToInt.getMonth());
	      setDay(day);
	      setYear(year);
   }
   
   public Date2(int dayInYear, int year) {
	   setMonth(getMonthBasedOnDayInYear(dayInYear));
	   setDay(getDayBasedOnDayInYear(dayInYear));
	   setYear(year);
   }
   
   private final int getDayBasedOnDayInYear(int dayInYear) {
	   int currentDay = 0;
	   for (int i = 1; i < daysInYear.length;i++) {
		   if (dayInYear >= daysInYear[i - 1] && dayInYear <= daysInYear[i]) {
			   return dayInYear - daysInYear[i - 1];
		   }
	   }
	   return currentDay;
   }
   
   private final int getMonthBasedOnDayInYear(int dayInYear) {
	   int i;
	   for (i = 1; i < daysInYear.length;i++) {
		   if (dayInYear >= daysInYear[i - 1] && dayInYear <= daysInYear[i]) {
			   return i;
		   }
	   }
	   return i;
   }
   
   public void incrementDay() {
	   setDay(++day % daysPerMonth[month]);
	   if (getDay() == 1)	// Roll over to the next month
		   incrementMonth();
   }
   
   private void incrementMonth(){
	   setMonth(++month % 12);
	   if (getMonth() == 1)		// Roll over to the next year
		   incrementYear();
   }
   
   private void incrementYear() {
	   setYear(++year);	// Roll over the year
   }
   
   public int getDay() {
	   return day;
   }
   
   public int getMonth() {
	   return month;
   }
   
   public int getYear()	 {
	   return year;
   }
   
   public final void setDay(int day) {
	// check if day in range for month
	      if (day <= 0 || 
	         (day > daysPerMonth[month] && !(month == 2 && day == 29)))
	         throw new IllegalArgumentException("day (" + day + 
	            ") out-of-range for the specified month and year");
	      
	      this.day = day;
   }

   public final void setMonth(int month) {
	      // check if month in range
	      if (month <= 0 || month > 12)
	         throw new IllegalArgumentException(
	            "month (" + month + ") must be 1-12");
	      
	      this.month = month;
   }
   
   public final void setYear(int year) {
		  // check if year in range
		  if (year <= 1900 || year > 3000)
			  throw new IllegalArgumentException(
				  "year (" + year + ") must be 1900-3000");
		  
	      // check for leap year if month is 2 and day is 29
	      if (month == 2 && day == 29 && !(year % 400 == 0 || 
	           (year % 4 == 0 && year % 100 != 0)))
	         throw new IllegalArgumentException("day (" + day +
	            ") out-of-range for the specified month and year");
	      
	      this.year = year;
   }
   
   // return a String of the form month/day/year
   public String toString()
   { 
      return String.format("%d/%d/%d", getMonth(), getDay(), getYear()); 
   }   
   
} // end class Date

