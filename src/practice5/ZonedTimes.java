package practice5;

import java.time.*;

public class ZonedTimes {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ZonedDateTime apollo11launch = ZonedDateTime.of(1969, 7, 16, 9, 32, 0,
				0, ZoneId.of("America/New_York"));
		// 1969-07-16T09:32-04:00[America/New_York]
		System.out.println("apollo11launch: " + apollo11launch);

		Instant instant = apollo11launch.toInstant();//utc time
		System.out.println("instant: " + instant); //1969-07-16T13:32:00Z

		//1969-07-16T13:32Z[UTC]
		ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("UTC"));
		System.out.println("UTC zonedDateTime: " + zonedDateTime);
		//1969-07-16T09:32-04:00[America/New_York]
		zonedDateTime = instant.atZone(ZoneId.of("America/New_York"));
		System.out.println("New York zonedDateTime: " + zonedDateTime);

		ZonedDateTime skipped = ZonedDateTime.of(LocalDate.of(2013, 3, 31),
				LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
		// 2013-03-31T03:30+02:00[Europe/Berlin]
		System.out.println("skipped: " + skipped);

		ZonedDateTime ambiguous = ZonedDateTime.of(LocalDate.of(2013, 10, 27), // End
																				// of
																				// daylight
																				// savings
																				// time
				LocalTime.of(2, 30), ZoneId.of("Europe/Berlin"));
		// 2013-10-27T02:30+02:00[Europe/Berlin]
		System.out.println("ambiguous: " + ambiguous);
		ZonedDateTime anHourLater = ambiguous.plusHours(1);
		//2013-10-27T02:30+01:00[Europe/Berlin]
		System.out.println("anHourLater: " + anHourLater);

		ZonedDateTime meeting = ZonedDateTime.of(LocalDate.of(2013, 10, 31),
				LocalTime.of(14, 30), ZoneId.of("America/Los_Angeles"));
		//2013-10-31T14:30-07:00[America/Los_Angeles]
		System.out.println("meeting: " + meeting);
		
		//Caution! Won't work with daylight savings time
		ZonedDateTime nextMeeting = meeting.plus(Duration.ofDays(7));
		//2013-11-07T13:30-08:00[America/Los_Angeles]
		System.out.println("nextMeeting: " + nextMeeting);
		
		nextMeeting = meeting.plus(Period.ofDays(7)); // OK
		//2013-11-07T14:30-08:00[America/Los_Angeles]
		System.out.println("nextMeeting: " + nextMeeting);
	}
}
