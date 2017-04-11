import javax.swing.JOptionPane;
import java.util.Scanner;

class dayOfTheWeek 
{
	public static final int	NORMAL_MONTH_LENGTH = 31;
	public static final int	ABNORMAL_MONTH_LENGTH = 30;
	public static final int	NORMAL_LENGTH_FEBRUARY = 28;
	public static final int	LEAP_YEAR_LENGTH_FEBRUARY = 29;
	public static final int MAX_MONTH = 12;
	public static final int MIN_MONTH = 1;
	public static final String NORMAL_NUMBER_SUFFIX = "th";
	public static final String ONE_SUFFIX = "st";
	public static final String TWO_SUFFIX = "nd";
	public static final String THREE_SUFFIX = "rd";
	public static final String[] daysArray = {"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
	
	public static void main(String[] args)
	{
		try
		{
			String input = JOptionPane.showInputDialog("Enter date (day/month/year):");
			Scanner scanner = new Scanner( input );
			scanner.useDelimiter("/");
			int date = scanner.nextInt();
			int month = scanner.nextInt();
			int year = scanner.nextInt();
			int count=0;
			while(!validDate(date,month,year))
			{
				count++;
				System.out.println("Invalid date, please enter a date using (date/month/year) format: ");
				scanner.useDelimiter("/");
				date = scanner.nextInt();
				month = scanner.nextInt();
				year = scanner.nextInt();
				if(count==5)System.out.println("Remember, february only has 29 days in a leap year!");
				if(count==6)System.out.println("Remember, most months have 31 days, but April, June, September and November have 30");
				if(count==10)System.out.println("Look it's not that hard, just enter a proper date like the example!");
				if(count==15)System.out.println("Oh, maybe you're foreign, or... an alien. Here:\n http://www.timeanddate.com/calendar/gregorian-calendar.html \n http://www.collinsdictionary.com/dictionary/english");
			}
			System.out.println("The date you entered was "+dayOfTheWeek(date,month,year)+" "+date+dateSuffix(date)+" "+monthName(month)+" "+year+".");
		}
		catch (NullPointerException exception)
		{
		}
		catch (java.util.NoSuchElementException exception)
		{
			JOptionPane.showMessageDialog(null, "invalid data entered.","Error", JOptionPane.ERROR_MESSAGE);
		}

	}
		
		public static boolean isLeapYear(int year)
		{
			if (year%4==0 & year%100!=0 | year%400==0)
			{
				return true;
			}
			else return false;
		}
		
		public static int daysInMonth(int month,int year)							//Returns the days in the input month
		{
			if(month == 2)
			{
				if(isLeapYear(year)) return	LEAP_YEAR_LENGTH_FEBRUARY;
				else return NORMAL_LENGTH_FEBRUARY;
			}
			else if(month==4|month==6|month==9|month==11) return ABNORMAL_MONTH_LENGTH;
			else if(1<=month & month<=12) return NORMAL_MONTH_LENGTH;
			else return 0;
		}
		
		public static boolean validDate(int date,int month,int year)					//Checks if date is valid
		{
			if(1<=date & date<=daysInMonth(month,year) & 0<year) return true;
			else return false;
		}
		
		public static String dayOfTheWeek(int date,int month,int year) 					//uses the modified gaussian algorithm to calculate which day of the week the date date/month/year was
			{
				int Y;
				int sept;
				if(month==1 | month==2) Y = year-1;
				else Y=year;
				int y = Y%100;
				int c = Y-(Y%100);
				double w = ((date + Math.floor(2.6 * (((month + 9) % 12) + 1) - 0.2) + y + Math.floor(y/4) + Math.floor(c/4) - 2*c)%7);
				
				if(year<2000) w++; 
				else if(year>3000) w--;
				
				w=(w+7)%7;
				int index = (int)w;
				index=index%7;
				return daysArray[index];
			}
		
		public static String dateSuffix(int date)										//takes integer date as input and outputs the corresponding ending (ie: 1st, 2nd, 3rd, 4th ...)
		{
			if(date==11 | date==12 | date==13) return NORMAL_NUMBER_SUFFIX;
			else
			{
				switch(date%10)
					{
						case 1:return ONE_SUFFIX;
						case 2:return TWO_SUFFIX;
						case 3:return THREE_SUFFIX;
						default: return NORMAL_NUMBER_SUFFIX;
					}
			}
		}
		
		public static String monthName(int month)	//returns the name of the entered month
		{
			switch(month)
			{
				case 1: return "January";
				case 2: return "February";
				case 3: return "March";
				case 4: return "April";
				case 5: return "May";
				case 6: return "June";
				case 7: return "July";
				case 8: return "August";
				case 9: return "September";
				case 10: return"October";
				case 11: return "November";
				case 12: return "December";
				default: return "";	//I don't think default is really necessary here, because the way the program will execute, valid date will prevent month being 12, but it doesn't seem to compile without it
			}
		}	


}