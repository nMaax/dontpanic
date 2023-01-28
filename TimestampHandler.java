package dontpanic;

/**
 * <strong>Autogenerated code by <a href="https://chat.openai.com/chat">CHATGPT</a> &copy</strong> on 28 Jan 2023
 * <br><br>
 * A Java class that handles String timestamps passed as parameter in a format specified by the user.
 * The format can include "YYYY" for 4-digit years, "MM" for 2-digit months, "DD" for 2-digit days,
 * "hh" for 2-digit hours, "mm" for 2-digit minutes, and "ss" for 2-digit seconds.
 */
public class TimestampHandler implements Comparable<TimestampHandler>{
	
    public static String ISO_FORMAT_PLAIN = "YYYY-MM-DD hh:mm:ss";
    public static String ISO_FORMAT_PERCENT = "%04d-%02d-%02d %02d:%02d:%02d";
    private String format;

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;
    
    /**
     * Constructor that takes in a timestamp in the format specified by the user and a format string.
     * The timestamp should include "YYYY" for the year, "MM" for the month, "DD" for the day,
     * "hh" for the hour, "mm" for the minute, and "ss" for the second.
     * <br><br>
     * <mark>If you want to handle dates only, set hh:mm:ss to 00:00:00</mark>
     * @param timestamp the timestamp in the specified format
     * @param format the format of the timestamp, including "YYYY", "MM", "DD", "hh", "mm", and "ss"
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
        this.hour = Integer.parseInt(timestamp.substring(hourIndex, hourIndex + 2));
        this.minute = Integer.parseInt(timestamp.substring(minuteIndex, minuteIndex + 2));
        this.second = Integer.parseInt(timestamp.substring(secondIndex, secondIndex + 2));

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
     * Returns the value of the year.
     * @return the year as an int
     */
    public int getYear() {
        return year;
    }

    /**
     * Sets the year value.
     * @param year the year as an int
     */
    public void setYear(int year) {
        this.year = year;
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
     */
    public void setMonth(int month) {
        this.month = month;
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
     */
    public void setDay(int day) {
        this.day = day;
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
     */
    public void setHour(int hour) {
        this.hour = hour;
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
     */
    public void setMinute(int minute) {
        this.minute = minute;
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
     */
    public void setSecond(int second) {
        this.second = second;
    }
    
    /**
     * Returns the second as a string in format "ss".
     * @return the second as a string in format "ss"
     */
    public String getSecondAsString() {
        return String.format("%02d", second);
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
     * Computes the difference in seconds between this TimestampHandler and another TimestampHandler.
     * If the TimestampHandler passed as parameter is after the object where this method is called it will return a negative value, positive otherwise.
     * (Same logic as in compareTo())
     * <br><br>
     * <i><b>Note</b>: This method does not consider leap years: all years are considered having 365 days</i>
     * @param other The TimestampHandler to compare to
     */
    public double secondDifference(TimestampHandler other) {
    	double diffYears = this.year - other.year;
    	double diffMonths = this.month - other.month;
    	double diffDays = this.day - other.day;
    	double diffHours = this.hour - other.hour;
    	double diffMinutes = this.minute - other.minute;
    	double diffSeconds = this.second - other.second;
		
		return diffYears*365.0
				+ diffMonths*365.0/12.0
				+ diffDays*24.0*60.0*60.0
				+ diffHours*60.0*60.0
				+ diffMinutes*60.0
				+ diffSeconds;
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
    public String toISOString() {
        return String.format(ISO_FORMAT_PERCENT, year, month, day, hour, minute, second);
    }
}
