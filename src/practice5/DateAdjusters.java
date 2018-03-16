package practice5;

import java.time.*;
import java.time.temporal.*;

public class DateAdjusters {
	public static void main(String[] args) {
		int year = 2017;
		int month = 3;
		LocalDate firstTuesday = LocalDate.of(year, month, 1).with(
				TemporalAdjusters.nextOrSame(DayOfWeek.TUESDAY));
		System.out.println("firstTuesday: " + firstTuesday); //2017-03-07

		LocalDate today = LocalDate.of(2017, 3, 24); // Saturday
		//Functional interface
		TemporalAdjuster NEXT_WORKDAY = w ->
			{
				LocalDate result = (LocalDate) w;
				do {
					result = result.plusDays(1);
				} while (result.getDayOfWeek().getValue() >= 6);
				return result;
			};

		LocalDate backToWork = today.with(NEXT_WORKDAY);
		System.out.println("backToWork: " + backToWork);

		//ofDateAdjuster method that
		//expects a lambda of type UnaryOperator<LocalDate> - 
		//param type and returned type are LocalDate
		TemporalAdjuster NEXT_WORKDAY2 = TemporalAdjusters.ofDateAdjuster(w ->
			{
				LocalDate result = w; // No cast
				do {
					result = result.plusDays(1);
				} while (result.getDayOfWeek().getValue() >= 6);
				return result;
			});
		backToWork = today.with(NEXT_WORKDAY2);
		System.out.println("backToWork: " + backToWork);
	}
}
