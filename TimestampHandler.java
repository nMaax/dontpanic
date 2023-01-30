package dontpanic;

/**
 * <i>Autogenerated code by <a href="https://chat.openai.com/chat">CHATGPT</a></i> on 28 Jan 2023
 * <br><br>
 * A Java class that handles String timestamps passed as parameter in a format specified by the user.
 * <br>
 * The format must include "YYYY" for 4-digit years, "MM" for 2-digit months, "DD" for 2-digit days.
 * <br>
 * The format can eventually include "hh" for 2-digit hours, "mm" for 2-digit minutes, and "ss" for 2-digit seconds.
 * <br><br>
 * This class will consider the differences in leap years and days in each specific month, 
 * use TimestampHandlerLite if you want a class that do not do it.
 * <br><br>
 * Note: This class will <i><b>not always</b></i> check if you passed the right parameters or right values. Some checks are done in setters, constructor and similar methods but not everything is covered.
 * Consider that in case you didn't pass right parameters the class could both show you wrong data or raise errors.
 * @version 22.23
 * @author ChatGPT
 * @author nMaax (Massimiliano)
 * @author Alcun* ragazz* del corso di OOP al Politecnico di Torino (A.A. 2022/2023)
 */
public class TimestampHandler implements Comparable<TimestampHandler>{
	
	/**A string rappresenting the iso format: YYYY-MM-DD hh:mm:ss*/
	public static String ISO_FORMAT_PLAIN = "YYYY-MM-DD hh:mm:ss";
    
    /**A string rappresenting the iso format compatible with java built-in static method Strig.format()*/
    public static String ISO_FORMAT_JAVAF = "%04d-%02d-%02d %02d:%02d:%02d";
    
    /**An array of iteger that rappresents the number of days for each month, if month = n then the index of its corresponding days is at index = n - 1*/
    public static int[] daysInMonthArray = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    
    private String format;

    private int year;
    private int month;	
    private int day;
    private int hour;
    private int minute;
    private int second;
    
    /**
     * Constructor that takes in a timestamp in the format specified by the user, the format is specified with the second parameter.
     * The format must include "YYYY" for the year, "MM" for the month and "DD" for the day. 
     * Eventually can include "hh" for the hour, "mm" for the minute, and "ss" for the second.
     * <br><br>
     * e.g
     * <br><br>
     * <code>TimestampHandler timestamp = new TimestampHandler("2024-02-25 10:23:45", "YYYY-MM-DD hh:mm:ss")</code> creates a timestamp at 25 Feb 2024 at 10:23:45
     * <br><br>
     * <code>TimestampHandler timestamp = new TimestampHandler("2024-02-25", "YYYY-MM-DD")</code> creates a timestamp at 25 Feb 2024, with no time (more technically the time is set to midnight: 00:00:00)
     * <br><br>
     * <code>TimestampHandler timestamp = new TimestampHandler("2024-25-01 15:30:23", "YYYY-DD-MM ss:mm:hh")</code> creates a timestamp at 25 Jan 2024 at 23:30:15
     * @param timestamp the timestamp in the specified format
     * @param format the format of the timestamp, mandatory including "YYYY", "MM", "DD", and eventually including any of "hh", "mm", and "ss"
     * @throws IllegalArgumentException if the any of the values passed is not in the acceptable corrispondent range:
     * <ul>
     * <li>0-9999 for Year (YYYY)</li>
     * <li>0-12 for Month (MM)</li>
     * <li>0-28/29/30/31 for Day (DD) based on the specific month and/or leap year</li>
     * <li>0-23 for Hours (hh)</li>
     * <li>0-59 for Minutes (mm)</li>
     * <li>0-59 for Seconds (ss)</li>
     * </ul>
     */
    public TimestampHandler(String timestamp, String format) {
    
    	this.format = format;
        
        int yearIndex = format.indexOf("YYYY");
        int monthIndex = format.indexOf("MM");
        int dayIndex = format.indexOf("DD");
        int hourIndex = format.indexOf("hh");
        int minuteIndex = format.indexOf("mm");
        int secondIndex = format.indexOf("ss");

        this.year = Integer.parseInt(timestamp.substring(yearIndex, yearIndex + 4));
        this.month = Integer.parseInt(timestamp.substring(monthIndex, monthIndex + 2));
        this.day = Integer.parseInt(timestamp.substring(dayIndex, dayIndex + 2));
        
        if (hourIndex == -1) {
        	this.hour = 0;
        } else {
        	this.hour = Integer.parseInt(timestamp.substring(hourIndex, hourIndex + 2));

        }
        
        if (minuteIndex == -1) {
        	this.minute = 0;
        } else {
        	this.minute = Integer.parseInt(timestamp.substring(minuteIndex, minuteIndex + 2));
        }
        
        if (secondIndex == -1) {
        	this.second = 0;
        } else {
	        this.second = Integer.parseInt(timestamp.substring(secondIndex, secondIndex + 2));

        }
        
        boolean isLeapYear = false;
        if ((this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0)) {
        	isLeapYear = true;
        }
        
        int[] daysInMonthCatalog = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int daysInMonth = 0;
        try {
        	daysInMonth = daysInMonthCatalog[month - 1];
        } catch(ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Invalid month value: " + this.month);
        }
        
		if (month == 2 && isLeapYear) {
        	daysInMonth++;
        }
        
        if (this.year < 0 || this.year > 9999) {
            throw new IllegalArgumentException("Invalid year value: " + this.year);
        }
        if (this.month < 1 || this.month > 12) {
            throw new IllegalArgumentException("Invalid month value: " + this.month);
        }
        if (this.day < 1 || this.day > daysInMonth) {
        	String leapYearErrMsg = "";
        	if (!isLeapYear && this.month == 2 && this.day == 29){
        		leapYearErrMsg = ", " + this.year + " is not a leap year.";
        	}
            throw new IllegalArgumentException("Invalid day value: " + this.day + " for month: " + this.month + leapYearErrMsg);
        }
        if (this.hour < 0 || this.hour > 23) {
            throw new IllegalArgumentException("Invalid hour value: " + this.hour);
        }
        if (this.minute < 0 || this.minute > 59) {
            throw new IllegalArgumentException("Invalid minute value: " + this.minute);
        }
        if (this.second < 0 || this.second > 59) {
            throw new IllegalArgumentException("Invalid second value: " + this.second);
        }
        
    }

    /**
     * Constructor that takes in a timestamp in ISO format, consisting of only one parameter.
     * The timestamp should be in the format "YYYY-MM-DD hh:mm:ss".
     * @param timestamp the timestamp in ISO format
     */
    public TimestampHandler(String timestamp) {
        this(timestamp, ISO_FORMAT_PLAIN);
    }
    
    /**
     * Returns the format of the timestamp stored in the object.
     * @return the format as a String
     */
    public String getFormat() {
    	return format;
    }
    
    /**
     * Returns the value of the year.
     * @return the year as an int
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year value.
     * @param year the year as an int
     * @throws IllegalArgumentException if the value passed is not in the acceptable range of years (0-9999)
     */
    public void setYear(int year) {
        this.year = year;
        if (this.year < 0 || this.year > 9999) {
            throw new IllegalArgumentException("Invalid year value: " + this.year);
        }
    }

    /**
     * Returns the year as a string in format "YYYY".
     * @return the year as a string in format "YYYY"
     */
    public String getYearAsString() {
        return String.format("%04d", year);
    }

    /**
     * Returns the value of the month.
     * @return the month as an int
     */
    public int getMonth() {
        return month;
    }

    /**
     * Sets the month value.
     * @param month the month as an int
     * @throws IllegalArgumentException if the value passed is not in the acceptable range of hours (0-12)
     */
    public void setMonth(int month) {
        this.month = month;
        if (this.month < 1 || this.month > 12) {
            throw new IllegalArgumentException("Invalid month value: " + this.month);
        }
    }

    /**
     * Returns the month as a string in format "MM".
     * @return the month as a string in format "MM"
     */
    public String getMonthAsString() {
        return String.format("%02d", month);
    }

    /**
     * Returns the value of the day.
     * @return the day as an int
     */
    public int getDay() {
        return day;
    }

    /**
     * Sets the day value.
     * @param day the day as an int
     * @throws IllegalArgumentException if the value passed is not in the acceptable range of days (0-28, 0-29, 0-30 or 0-31) depending on wich month is the instance setted to
     */
    public void setDay(int day) {
        this.day = day;
        if (this.day < 1 || this.day > getDaysInMonth()) {
        	String leapYearErrMsg = "";
        	if (!isLeapYear() && this.month == 2 && this.day == 29){
        		leapYearErrMsg = ", " + this.year + " is not a leap year.";
        	}
            throw new IllegalArgumentException("Invalid day value: " + this.day + " for month: " + this.month + leapYearErrMsg);
        }
        
    }

    /**
     * Returns the day as a string in format "DD".
     * @return the day as a string in format "DD"
     */
    public String getDayAsString() {
        return String.format("%02d", day);
    }

    /**
     * Returns the value of the hour.
     * @return the hour as an int
     */
    public int getHour() {
        return hour;
    }

    /**
     * Sets the hour value.
     * @param hour the hour as an int
     * @throws IllegalArgumentException if the value passed is not in the acceptable range of hours (0-23)
     */
    public void setHour(int hour) {
        this.hour = hour;
        if (this.hour < 0 || this.hour > 23) {
            throw new IllegalArgumentException("Invalid hour value: " + this.hour);
        }
    }

    /**
     * Returns the hour as a string in format "hh".
     * @return the hour as a string in format "hh"
     */
    public String getHourAsString() {
        return String.format("%02d", hour);
    }

    /**
     * Returns the value of the minute.
     * @return the minute as an int
     */
    public int getMinute() {
        return minute;
    }

    /**
     * Sets the minute value.
     * @param minute the minute as an int
     * @throws IllegalArgumentException if the value passed is not in the acceptable range of minutes (0-59)
     */
    public void setMinute(int minute) {
        this.minute = minute;
        if (this.minute < 0 || this.minute > 59) {
            throw new IllegalArgumentException("Invalid minute value: " + this.minute);
        }
    }
    
    /**
     * Returns the minute as a string in format "mm".
     * @return the minute as a string in format "mm"
     */
    public String getMinuteAsString() {
        return String.format("%02d", minute);
    }
    
    /**
     * Returns the value of the second.
     * @return the second as an int
     */
    public int getSecond() {
        return second;
    }
    
    /**
     * Sets the second value.
     * @param second the minute as an int
     * @throws IllegalArgumentException if the value passed is not in the acceptable range of seconds (0-59)
     */
    public void setSecond(int second) {
        this.second = second;
        if (this.second < 0 || this.second > 59) {
            throw new IllegalArgumentException("Invalid second value: " + this.second);
        }
    }
    
    /**
     * Returns the second as a string in format "ss".
     * @return the second as a string in format "ss"
     */
    public String getSecondAsString() {
        return String.format("%02d", second);
    }
    
    /**
     * Returns the number of days in the current month of the timestamp.
     * <br>
     * If the value 0 is passed it will returns the days in december (as for month = 12), for -1 will return as for month = 11 etc.
     * <br>
     * Respectivelly if the value is 13 it will return as for month = 1, for 14 will return as for month = 2 etc. 
     *
     * @return the number of days in the current month
     */
    public int getDaysInMonth() {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int index = month - 1;
        while (index < 0) {
        	index = daysInMonth.length + index;
            //index = daysInMonth.length + index + 1;
        }
        while (index >= daysInMonth.length) {
            index = index % daysInMonth.length;
        }
        
        month = index + 1;
        int days = daysInMonth[index];
        if (this.month == 2 && isLeapYear()) {
            days++;
        }
        return days;
    }
    
    /**
     * Returns the number of days in the month passed as parameter 
     * considering if the year passed as parameter is leap or not.
     * <br>
     * If the value 0 is passed it will returns the days in december (as for month = 12), for -1 will return as for month=11 etc.
     * <br>
     * Respectivelly if the value is 13 it will return as for month = 1 etc. 
     * 
     * @param month The month as an integer, from 1 o 12
     * @param year The year as an integer
     * @return the number of days in the current month
     */
    public static int getDaysInMonth(int month, int year) {
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        
        int index = month -1;
        while (index < 0) {
        	index = daysInMonth.length + index;
            //index = daysInMonth.length + index + 1;
        }
        while (index >= daysInMonth.length) {
            index = index % daysInMonth.length;
        }
        
        month = index + 1;
        int days = daysInMonth[index];
        if (month == 2 && TimestampHandler.isLeapYear(year)) {
            days++;
        }
        return days;
    }
    
    /**
     * Returns whether the current year of the timestamp is a leap year or not.
     *
     * @return true if the current year is a leap year, false otherwise
     */
    public boolean isLeapYear() {
        return (this.year % 4 == 0 && this.year % 100 != 0) || (this.year % 400 == 0);
    }
    
    /**
     * Returns whether the current year of the timestamp is a leap year or not.
     * 
     * @param year The year that is needed to be check
     * @return true if the current year is a leap year, false otherwise
     */
    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
    
    /**
    * Compares this TimestampHandler to another TimestampHandler and returns true if this TimestampHandler is after the provided TimestampHandler
    * @param sOther the TimestampHandler to compare to
    * @param format the format of the provided TimestampHandler
    * @return true if this TimestampHandler is after the provided TimestampHandler
    */
    public boolean isAfter(String sOther) {
        TimestampHandler other = new TimestampHandler(sOther, this.format); 
        return this.compareTo(other) > 0;
    }

    /**
    * Compares this TimestampHandler to a string representation of a TimestampHandler and returns true if this TimestampHandler is after the provided TimestampHandler
    * @param sOther the string representation of the TimestampHandler to compare to
    * @param format the format of the provided TimestampHandler
    * @return true if this TimestampHandler is after the provided TimestampHandler
    */
    public boolean isAfter(String sOther, String format) {
        TimestampHandler other = new TimestampHandler(sOther, format); 
        return this.compareTo(other) > 0;
    }

    /**
    * Compares this TimestampHandler to another TimestampHandler and returns true if this TimestampHandler is after the provided TimestampHandler
    * @param other the TimestampHandler to compare to
    * @return true if this TimestampHandler is after the provided TimestampHandler
    */
    public boolean isAfter(TimestampHandler other) {
        return this.compareTo(other) > 0;
    }

    /**
    * Compares this TimestampHandler to another TimestampHandler and returns true if this TimestampHandler is after or equal to the provided TimestampHandler
    * @param sOther the TimestampHandler to compare to
    * @param format the format of the provided TimestampHandler
    * @return true if this TimestampHandler is after or equal to the provided TimestampHandler
    */
    public boolean isAfterOrEqual(String sOther) {
        TimestampHandler other = new TimestampHandler(sOther, this.format); 
        return this.compareTo(other) >= 0;
    }

    /**
    * Compares this TimestampHandler to a string representation of a TimestampHandler and returns true if this TimestampHandler is after or equal to the provided TimestampHandler
    * @param sOther the string representation of the TimestampHandler to compare to
    * @param format the format of the provided TimestampHandler
    * @return true if this TimestampHandler is after or equal to the provided TimestampHandler
    */
    public boolean isAfterOrEqual(String sOther, String format) {
        TimestampHandler other = new TimestampHandler(sOther, format); 
        return this.compareTo(other) >= 0;
    }

    /**
    * Compares this TimestampHandler to another TimestampHandler and returns true if this TimestampHandler is after or equal to the provided TimestampHandler
    * @param other the TimestampHandler to compare to
    * @return true if this TimestampHandler is after or equal to the provided TimestampHandler
    */
    public boolean isAfterOrEqual(TimestampHandler other) {
        return this.compareTo(other) >= 0;
    }

    /**
     * Compares this TimestampHandler to a string representation of a timestamp and returns true if this TimestampHandler is before the provided timestamp
     * @param sOther the string representation of the timestamp to compare to
     * @return true if this TimestampHandler is before the provided timestamp
     */
    public boolean isBefore(String sOther) {
        TimestampHandler other = new TimestampHandler(sOther, this.format); 
        return this.compareTo(other) < 0;
    }

    /**
     * Compares this TimestampHandler to a string representation of a timestamp in a specific format and returns true if this TimestampHandler is before the provided timestamp
     * @param sOther the string representation of the timestamp to compare to
     * @param format the format of the provided timestamp
     * @return true if this TimestampHandler is before the provided timestamp
     */
    public boolean isBefore(String sOther, String format) {
        TimestampHandler other = new TimestampHandler(sOther, format); 
        return this.compareTo(other) < 0;
    }

    /**
     * Compares this TimestampHandler to another TimestampHandler and returns true if this TimestampHandler is before the provided TimestampHandler
     * @param other the TimestampHandler to compare to
     * @return true if this TimestampHandler is before the provided TimestampHandler
     */
    public boolean isBefore(TimestampHandler other) {
        return this.compareTo(other) < 0;
    }

    /**
     * Compares this TimestampHandler to a string representation of a timestamp and returns true if this TimestampHandler is before or equal to the provided timestamp
     * @param sOther the string representation of the timestamp to compare to
     * @return true if this TimestampHandler is before or equal to the provided timestamp
     */
    public boolean isBeforeOrEqual(String sOther) {
        TimestampHandler other = new TimestampHandler(sOther, this.format); 
        return this.compareTo(other) <= 0;
    }

    /**
     * Compares this TimestampHandler to a string representation of a timestamp in a specific format and returns true if this TimestampHandler is before or equal to the provided timestamp
     * @param sOther the string representation of the timestamp to compare to
     * @param format the format of the provided timestamp
     * @return true if this TimestampHandler is before or equal to the provided timestamp
     */
    public boolean isBeforeOrEqual(String sOther, String format) {
        TimestampHandler other = new TimestampHandler(sOther, format); 
        return this.compareTo(other) <= 0;
    }

    /**
     * Compares this TimestampHandler to another TimestampHandler and returns true if this TimestampHandler is before or equal to the provided TimestampHandler
     * @param other the TimestampHandler to compare to
     * @return true if this TimestampHandler is before or equal to the provided TimestampHandler
     */
    public boolean isBeforeOrEqual(TimestampHandler other) {
        return this.compareTo(other) <= 0;
    }
    
    /**
     * Compares this TimestampHandler with the specified String 
     * passed as parameter rappresenting a timestamp in the same format 
     * as the one used to create this TimestampHandler istance
     * 
     * @param timestamp The timestamp to be compared
     * @return a negative integer, zero, or a positive integer as this {@link TimestampHandler}
     * is less than, equal to, or greater than the specified {@link TimestampHandler}.
     * */
    public int compareTo(String timestamp) {
    	TimestampHandler other = new TimestampHandler(timestamp, this.format);
    	return this.compareTo(other);
    }
    
    /**
     * Compares this TimestampHandler with the specified String 
     * passed as parameter rappresenting a timestamp in the format 
     * passed as second parameter
     * 
     * @param timestamp The timestamp to be compared
     * @param format The format of the timestamp to be compared
     * @return a negative integer, zero, or a positive integer as this {@link TimestampHandler}
     * is less than, equal to, or greater than the specified {@link TimestampHandler}.
     * */
    public int compareTo(String timestamp, String format) {
    	TimestampHandler other = new TimestampHandler(timestamp, format);
    	return this.compareTo(other);
    }
    
    /**
    * Compares this {@link TimestampHandler} with the specified {@link TimestampHandler} for order.
    * Returns a negative integer, zero, or a positive integer as this {@link TimestampHandler}
    * is less than, equal to, or greater than the specified {@link TimestampHandler}.
    *
    * @param other the {@link TimestampHandler} to be compared.
    * @return a negative integer, zero, or a positive integer as this {@link TimestampHandler}
    * is less than, equal to, or greater than the specified {@link TimestampHandler}.
    */
    @Override
    public int compareTo(TimestampHandler other) {
         
         if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        } else if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        } else if (this.day != other.day) {
            return Integer.compare(this.day, other.day);
        } else if (this.hour != other.hour) {
            return Integer.compare(this.hour, other.hour);
        } else if (this.minute != other.minute) {
            return Integer.compare(this.minute, other.minute);
        } else {
            return Integer.compare(this.second, other.second);
        }
	
    }
    
    /**
     * Compares this {@link TimestampHandler} with the specified timestamp and format for order.
     * Returns {@code true} if this {@link TimestampHandler} is equal to the specified timestamp and format, {@code false} otherwise.
     * @param timestamp the timestamp to be compared.
     * @param format the format of the timestamp to be compared.
     * @return {@code true} if this object is the same as the timestamp and format argument, {@code false} otherwise.
     * */
    public boolean equals(String timestamp, String format) {
    	TimestampHandler other = new TimestampHandler(timestamp, format);
    	return equals(other);
    }
    
    /**
     *  Compares this {@link TimestampHandler} with the specified timestamp for order.
     *  Returns {@code true} if this {@link TimestampHandler} is equal to the specified timestamp, {@code false} otherwise.
     *  @param timestamp the timestamp to be compared.
     *  @return {@code true} if this object is the same as the timestamp argument, {@code false} otherwise.
     *  */
    public boolean equals(String timestamp) {
    	TimestampHandler other = new TimestampHandler(timestamp, this.format);
    	return equals(other);
    }
    
    /**
    * Indicates whether some other object is "equal to" this one.
    *
    * @param obj the reference object with which to compare.
    * @return {@code true} if this object is the same as the obj argument; {@code false} otherwise.
    */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TimestampHandler other = (TimestampHandler) obj;
        return year == other.year &&
               month == other.month &&
               day == other.day &&
               hour == other.hour &&
               minute == other.minute &&
               second == other.second;
    }
    
    /**
     * Returns the timestamp in the same format as the one specified by the user in the constructor.
     * @return the timestamp in the specified format
     */
    @Override
    public String toString() {
        return format.replace("YYYY", String.format("%04d", year))
                .replace("MM", String.format("%02d", month))
                .replace("DD", String.format("%02d", day))
                .replace("hh", String.format("%02d", hour))
                .replace("mm", String.format("%02d", minute))
                .replace("ss", String.format("%02d", second));
    }
    
    /**
     * Returns the timestamp in the specified format
     * @param format the format of the timestamp
     * @return the timestamp in the specified format
     */
    public String toString(String format) {
        return format.replace("YYYY", String.format("%04d", year))
                .replace("MM", String.format("%02d", month))
                .replace("DD", String.format("%02d", day))
                .replace("hh", String.format("%02d", hour))
                .replace("mm", String.format("%02d", minute))
                .replace("ss", String.format("%02d", second));
    }
    
    /**
     * Returns the timestamp in the ISO format
     * @return the timestamp in the ISO format
     */
    public String toStringISO() {
        return String.format(ISO_FORMAT_JAVAF, year, month, day, hour, minute, second);
    }
    
    /**
	 * Increases the second of the timestamp by the given number of seconds.
	 *
	 * @param seconds the number of seconds to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
    public void increaseSeconds(int seconds) {
    	
    	if (seconds < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + seconds);
    	
    	this.second += seconds;
        if (this.second >= 60) {
            int extraMinutes = this.second / 60;
            this.second %= 60;
            increaseMinutes(extraMinutes);
        }
    }

    /**
	 * Increases the minute of the timestamp by the given number of minutes.
	 *
	 * @param minutes the number of minutes to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
    public void increaseMinutes(int minutes) {
    	
    	if (minutes < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + minutes);
    	
    	this.minute += minutes;
        if (this.minute >= 60) {
            int extraHours = this.minute / 60;
            this.minute %= 60;
            increaseHours(extraHours);
        }
    }
    
    /**
	 * Increases the hour of the timestamp by the given number of hours.
	 *
	 * @param hours the number of hours to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
    public void increaseHours(int hours) {
    	
    	if (hours < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + hours);
    	
        this.hour += hours;
        if (this.hour >= 24) {
            int extraDays = this.hour / 24;
            this.hour %= 24;
            increaseDays(extraDays);
        }
    }

    /**
	 * Increases the day of the timestamp by the given number of days.
	 *
	 * @param days the number of days to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void increaseDays(int days) {
		
		if (days < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + days);
		
		int daysInMonth = getDaysInMonth();
		this.day += days;
		while (this.day > daysInMonth) {
			this.day -= daysInMonth;
			increaseMonths(1);
			daysInMonth = getDaysInMonth();
		}
	}

	/**
	 * Increases the month of the timestamp by the given number of months.
	 *
	 * @param months the number of months to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void increaseMonths(int months) {
		
		if (months < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + months);
		
		this.month += months;
		while (this.month > 12) {
			this.month -= 12;
			increaseYears(1);
		}
	}

	/**
	 * Increases the year of the timestamp by the given number of years.
	 *
	 * @param years the number of years to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void increaseYears(int years) {
		
		if (years < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + years);
		
		this.year += years;
		if (this.year > 9999) {
			this.year = 9999;
			this.month = 12;
			this.day = 31;
			this.hour = 23;
			this.minute = 59;
			this.second = 59;
		}
	}
	
	/**
	 * Decreases the second of the timestamp by the given number of seconds.
	 *
	 * @param seconds the number of seconds to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void decreaseSeconds(int seconds) {
		
		if (seconds < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + seconds);
		
	    this.second -= seconds;
	    if (this.second < 0) {
	        int extraMinutes = (this.second / 60) - 1;
	        this.second += -(extraMinutes) * 60;
	        decreaseMinutes(extraMinutes * -1);
	    }
	}
	
	/**
	 * Decreases the minute of the timestamp by the given number of minutes.
	 *
	 * @param minutes the number of minutes to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void decreaseMinutes(int minutes) {
		
		if (minutes < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + minutes);
		
	    this.minute -= minutes;
	    if (this.minute < 0) {
	        int extraHours = (this.minute / 60) - 1;
	        this.minute += -(extraHours) * 60;
	        decreaseHours(extraHours * -1);
	    }
	}
	
	/**
	 * Decreases the hour of the timestamp by the given number of hours.
	 *
	 * @param hours the number of hours to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void decreaseHours(int hours) {
		
		if (hours < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + hours);
		
	    this.hour -= hours;
	    if (this.hour < 0) {
	        int extraDays = (this.hour / 24) - 1;
	        this.hour += -(extraDays) * 24;
	        decreaseDays(extraDays * -1);
	    }
	}
	
	/**
	 * Decreases the day of the timestamp by the given number of days.
	 *
	 * @param days the number of days to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void decreaseDays(int days) {
		
		if (days < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + days);
		
	    this.day -= days;
	    while (this.day <= 0) {
	    	this.day += getDaysInMonth(this.month-1, this.year);
	    	decreaseMonths(1);
	    }
	}
	
	/**
	 * Decreases the month of the timestamp by the given number of months.
	 *
	 * @param months the number of months to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void decreaseMonths(int months) {
		
		if (months < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + months);
		
	    this.month -= months;
	    while (this.month <= 0) {
	        this.month += 12;
	        decreaseYears(1);
	    }
	}
	
	/**
	 * Decreases the year of the timestamp by the given number of years.
	 *
	 * @param years the number of years to increase the timestamp by
	 * @throws IllegalArgumentException if the value passed as parameter is negative
	 */
	public void decreaseYears(int years) {
		
		if (years < 0) throw new IllegalArgumentException("Cannot modify with a negative parameter: " + years);
		
	    this.year -= years;
	    if (this.year < 0) {
	        this.year = 0;
	        this.month = 1;
	        this.day = 1;
	        this.hour = 0;
	        this.minute = 0;
	        this.second = 0;
	    }
	}

}
