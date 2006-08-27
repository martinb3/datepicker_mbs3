/*
 *   Copyright 2006 Martin B. Smith
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.mbs.java.swing.widgets.datepicker;

import java.util.*;

public class CalendarDemo {

	public static void main(String[] args) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.MONTH, Calendar.APRIL);
		

		System.out.println("ERA: " + calendar.get(Calendar.ERA));
		System.out.println("YEAR: " + (calendar.get(Calendar.YEAR)));
		System.out.println("MONTH: " + calendar.get(Calendar.MONTH));
		System.out.println("WEEK_OF_YEAR: "
				+ calendar.get(Calendar.WEEK_OF_YEAR));
		System.out.println("WEEK_OF_MONTH: "
				+ calendar.get(Calendar.WEEK_OF_MONTH));
		System.out.println("Actual MAX WEEK_OF_MONTH: "
				+ calendar.getActualMaximum(Calendar.WEEK_OF_MONTH));

		System.out.println("DATE: " + calendar.get(Calendar.DATE));
		System.out.println("DAY_OF_MONTH: "
				+ calendar.get(Calendar.DAY_OF_MONTH));
		System.out
				.println("DAY_OF_YEAR: " + calendar.get(Calendar.DAY_OF_YEAR));
		System.out.println("DAY_OF_WEEK: " + calendar.get(Calendar.DAY_OF_WEEK));
		System.out.println("DAY_OF_WEEK_IN_MONTH: "
				+ calendar.get(Calendar.DAY_OF_WEEK_IN_MONTH));
		System.out.println("AM_PM: " + calendar.get(Calendar.AM_PM));
		System.out.println("HOUR: " + calendar.get(Calendar.HOUR));
		System.out
				.println("HOUR_OF_DAY: " + calendar.get(Calendar.HOUR_OF_DAY));
		System.out.println("MINUTE: " + calendar.get(Calendar.MINUTE));
		System.out.println("SECOND: " + calendar.get(Calendar.SECOND));
		System.out
				.println("MILLISECOND: " + calendar.get(Calendar.MILLISECOND));
		System.out.println("ZONE_OFFSET: "
				+ (calendar.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000)));
		System.out.println("DST_OFFSET: "
				+ (calendar.get(Calendar.DST_OFFSET) / (60 * 60 * 1000)));

		int month = calendar.get(Calendar.MONTH);

		System.out.println("\tSU\tMO\tTU\tWE\tTH\tFR\tSA");

		// skip print up to this month
		for (int i = 1; i < calendar.get(Calendar.DAY_OF_WEEK); i++) {
			System.out.print("\tx");
		}

		while (month == calendar.get(Calendar.MONTH)) {
			System.out.print("\t" + calendar.get(Calendar.DAY_OF_MONTH));
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			if (calendar.get(Calendar.DAY_OF_WEEK) == calendar.getFirstDayOfWeek())
				System.out.println();
		}
		System.out.println();
	}

}
