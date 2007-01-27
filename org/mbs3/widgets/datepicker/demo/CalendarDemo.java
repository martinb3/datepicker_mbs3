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
package org.mbs3.widgets.datepicker.demo;

import java.util.*;

/*
 * This class demos calendar layout textually so that the developer
 * can plan for the eventual graphical calendar layout
 */
public class CalendarDemo {

	public static void main(String[] args) {
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);
		
		calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
		for(int i = 0; i < 7; i++)
		{
			System.out.print("\t" + String.format("%1$ta", calendar));
			calendar.add(Calendar.DAY_OF_MONTH, 1);
		}
		System.out.println(); 
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		while(calendar.get(Calendar.DAY_OF_WEEK) != calendar.getFirstDayOfWeek())
			calendar.add(Calendar.DAY_OF_YEAR, -1);

		// skip print up to this month
		while(calendar.get(Calendar.DAY_OF_MONTH) != calendar.getMinimum(Calendar.DAY_OF_MONTH)) {
			System.out.print("\tx");
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}

		int month = calendar.get(Calendar.MONTH);
		while (month == calendar.get(Calendar.MONTH)) {
			System.out.print("\t" + calendar.get(Calendar.DAY_OF_MONTH));
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			if (calendar.get(Calendar.DAY_OF_WEEK) == calendar.getFirstDayOfWeek())
				System.out.println();
		}
		
		while(calendar.get(Calendar.DAY_OF_WEEK) != calendar.getFirstDayOfWeek()) {
			System.out.print("\tx");
			calendar.add(Calendar.DAY_OF_YEAR, 1);
		}
		System.out.println();

	}

}
