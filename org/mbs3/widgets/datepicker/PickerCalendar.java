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
package org.mbs3.widgets.datepicker;

import java.util.GregorianCalendar;

/*
 * This class barely extends the Gregorian calendar so that toString()
 * and tooltipString() methods return something useful for this component
 */
public class PickerCalendar extends GregorianCalendar {

	public String toString()
	{
		return String.format("%1$te", (java.util.Calendar)this);
	}
	
	public String tooltipString()
	{
		return String.format("%1$tA, %1$tB %1$te, %1$tY", (java.util.Calendar)this);
	}
}
