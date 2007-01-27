/**
 * 
 */
package org.mbs3.widgets.datepicker;

import javax.swing.table.DefaultTableCellRenderer;
import java.util.Calendar;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Component;

/**
 * @author Martin
 * 
 */
public class DateCellRenderer extends DefaultTableCellRenderer {

	Calendar calendar = null;

	public DateCellRenderer(Calendar calendar) {
		super();
		this.calendar = calendar;
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean selected, boolean hasFocus, int row, int column) {
		// if you put nothing in the cell, we probably shouldn't try to mess
		// with it
		if (value == null)
			return this;

		// get the calendar for the cell we're in, set the text of the cell
		super.setText(value.toString());

		if (value.getClass() != PickerCalendar.class) {
			super.getTableCellRendererComponent(table, value, selected,
					hasFocus, row, column);
			return this;
		}

		PickerCalendar currentCellCalendar = (PickerCalendar) value;
		super.setToolTipText(currentCellCalendar.tooltipString());

		// if the cell is selected, adjust background accordingly
		if (selected)
			super.setBackground(table.getSelectionBackground());
		else
			super.setBackground(table.getBackground());

		// if the cell contains a calendar that isn't the current month
		// we're viewing, make its foreground a bright red color
		if (calendar.get(Calendar.MONTH) != currentCellCalendar
				.get(Calendar.MONTH))
			setForeground(Color.red);
		else
			setForeground(table.getForeground());
		return this;

	}
}
