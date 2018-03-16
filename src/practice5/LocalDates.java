package practice5;

import java.time.*;
import java.time.temporal.*;

public class LocalDates {
	public static void main(String[] args) {
		LocalDate today = LocalDate.now(); // Today's date
		System.out.println("today: " + today);

		LocalDate MemorialDay = LocalDate.of(2017, Month.MAY, 29);
		System.out.println("Until memorial day: "
				+ today.until(MemorialDay, ChronoUnit.DAYS));
		
		LocalDate endPayDay = LocalDate.of(2017, 1, 1).plusWeeks(26);
		System.out.println("End of payday: " + endPayDay +", " + endPayDay.getDayOfWeek());

		LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
		alonzosBirthday = LocalDate.of(1963, Month.JUNE, 23);
		// Uses the Month enumeration
		System.out.println("alonzosBirthday: " + alonzosBirthday + ", " + alonzosBirthday.getDayOfWeek());

		LocalDate programmersDay = LocalDate.of(2014, 1, 1).plusDays(255);
		// September 13, but in a leap year it would be September 12
		System.out.println("programmersDay: " + programmersDay);

		LocalDate independenceDay = LocalDate.of(2014, Month.JULY, 4);
		LocalDate christmas = LocalDate.of(2014, Month.DECEMBER, 25);

		System.out.println("Until christmas: "
				+ independenceDay.until(christmas)); // P5M21D
		System.out.println("Until christmas: "
				+ independenceDay.until(christmas, ChronoUnit.DAYS));

		System.out.println(LocalDate.of(2016, 1, 31).plusMonths(1)); // 2016-02-29
		System.out.println(LocalDate.of(2016, 3, 31).minusMonths(1)); // 2016-02-29

		DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1, 1)
				.getDayOfWeek(); // MONDAY
		System.out.println("startOfLastMillennium: " + startOfLastMillennium);
		System.out.println(startOfLastMillennium.getValue()); // 1
		System.out.println(DayOfWeek.SATURDAY.plus(3)); // TUESDAY
	}
}
