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

import java.io.Serializable;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import javax.swing.JButton;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;

public class DatePicker extends javax.swing.JPanel implements Serializable {
	private JButton jButton1;

	private JButton jButton2;

	private JScrollPane jScrollPane1;

	private JButton jButton3;

	private JLabel jLabel1;

	private JTable jTable1;

	private Popup p;

	private Calendar currentCalendar = new PickerCalendar();

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DatePicker());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public DatePicker() {
		super();
		initGUI();
		createAndSetNewTableModel();
	}

	private void initGUI() {
		try {
			int rowFactor = 20;
			int colFactor = 15;
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
			thisLayout.rowHeights = new int[] { 25, 6 * rowFactor, 25 };
			thisLayout.columnWeights = new double[] { 5.0, 10.0, 5.0 };
			thisLayout.columnWidths = new int[] { 40, 175, 40 };
			this.setLayout(thisLayout);
			// this.setPreferredSize(new java.awt.Dimension(258, 159));
			this.setBackground(new java.awt.Color(255, 255, 255));
			{
				jButton1 = new JButton();
				this.add(jButton1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0),
						0, 0));
				jButton1.setText("<");
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton1ActionPerformed(evt);
					}
				});
			}
			{
				jButton2 = new JButton();
				this.add(jButton2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0),
						0, 0));
				jButton2.setText(">");
				jButton2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton2ActionPerformed(evt);
					}
				});
			}
			{
				jLabel1 = new JLabel();
				this.add(jLabel1, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.NONE,
						new Insets(0, 0, 0, 0), 0, 0));

			}
			{
				jButton3 = new JButton();
				this.add(jButton3, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0,
						GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0),
						0, 0));
				jButton3.setText("Today");
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton3ActionPerformed(evt);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, new GridBagConstraints(0, 1, 3, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
				jScrollPane1.setWheelScrollingEnabled(false);
				jScrollPane1.setPreferredSize(new java.awt.Dimension(
						currentCalendar.getActualMaximum(Calendar.DAY_OF_WEEK)
								* colFactor, 6 * rowFactor));
				jScrollPane1
						.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
				jScrollPane1
						.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				{

					jTable1 = new JTable() {
						public boolean isCellEditable(int row, int column) {
							return false;
						}
					};
					jScrollPane1.setViewportView(jTable1);
					jTable1.getParent().setBackground(
							new java.awt.Color(255, 255, 255));

					jTable1.setPreferredScrollableViewportSize(jTable1
							.getPreferredSize());
					jTable1.setShowHorizontalLines(false);
					jTable1.setShowVerticalLines(false);
					jTable1.setRequestFocusEnabled(false);
					jTable1.setRowSelectionAllowed(false);
					jTable1.setCellSelectionEnabled(true);

				}

				Dimension topButtonDimensions = jButton1.getPreferredSize();
				Dimension bottomButtonDimensions = jButton3.getPreferredSize();
				Dimension scrollPaneDimensions = jScrollPane1
						.getPreferredSize();

				int h = topButtonDimensions.height
						+ bottomButtonDimensions.height
						+ scrollPaneDimensions.height;
				int w = 2 * topButtonDimensions.width
						+ bottomButtonDimensions.width
						+ scrollPaneDimensions.width;

				this.setPreferredSize(new Dimension(w, h));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createAndSetNewTableModel() {
		// make a copy of the current calendar object being displayed
		Calendar calendar = (PickerCalendar) currentCalendar.clone();

		// our new copy starts at the first of the month
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.setFirstDayOfWeek(Calendar.SUNDAY);

		// make a second copy of the calendar used for day names
		Calendar names = (Calendar) calendar.clone();

		// set the month name & year at the top
		jLabel1.setText(String.format("%1$tB %1$tY", names));

		// put the days of the week in a String array
		String[] daysOfWeek = new String[names
				.getActualMaximum(Calendar.DAY_OF_WEEK)];
		names.set(Calendar.DAY_OF_WEEK, names.getFirstDayOfWeek());
		int count = 0;
		for (int i = names.getActualMinimum(Calendar.DAY_OF_WEEK); i <= names
				.getActualMaximum(Calendar.DAY_OF_WEEK); i++) {
			daysOfWeek[count] = String.format("%1$ta", names);
			names.add(Calendar.DAY_OF_MONTH, 1);
			count++;
		}

		// create the table data model
		TableModel jTable1Model = new DefaultTableModel(daysOfWeek, calendar
				.getActualMaximum(Calendar.WEEK_OF_MONTH));

		// configure a counter for the dimensions of the table
		int cols = jTable1Model.getColumnCount();
		int rowcount = 0;
		int colcount = 0;
		int month = calendar.get(Calendar.MONTH);

		int currentDayRow = -1;
		int currentDayColumn = -1;

		// roll the calendar back to the first day of the week
		// which will probably be in the last month
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		while (calendar.get(Calendar.DAY_OF_WEEK) != calendar
				.getFirstDayOfWeek()) {
			calendar.add(Calendar.DAY_OF_YEAR, -1);
		}

		// set last month's days until we get back up to the
		// first day of this month
		while (calendar.get(Calendar.DAY_OF_MONTH) != calendar
				.getMinimum(Calendar.DAY_OF_MONTH)) {
			jTable1Model.setValueAt(calendar.clone(), rowcount, colcount);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			colcount++;
		}

		// while we're in the same month, roll it forward and
		// fill in the days on the table
		while (month == calendar.get(Calendar.MONTH)) {
			Calendar toInsert = (Calendar) calendar.clone();
			jTable1Model.setValueAt(toInsert, rowcount, colcount);

			Calendar now = Calendar.getInstance();
			if (now.get(Calendar.YEAR) == toInsert.get(Calendar.YEAR)
					&& now.get(Calendar.DAY_OF_YEAR) == toInsert
							.get(Calendar.DAY_OF_YEAR)) {
				currentDayRow = rowcount;
				currentDayColumn = colcount;
			}

			colcount++;
			if (colcount >= cols)
				colcount -= cols;
			// System.out.println("Day of month: " +
			// calendar.get(Calendar.DAY_OF_MONTH) + ", colct =" + colcount);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			if (calendar.get(Calendar.DAY_OF_WEEK) == calendar
					.getFirstDayOfWeek())
				rowcount++;

		}

		// fill in up to the first day of the next week
		while (calendar.get(Calendar.DAY_OF_WEEK) != calendar
				.getFirstDayOfWeek()) {
			jTable1Model.setValueAt(calendar.clone(), rowcount, colcount);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			colcount++;
		}

		jTable1.setModel(jTable1Model);
		jTable1.setCellSelectionEnabled(true);
		// jTable1.setColumnSelectionAllowed(false);
		// jTable1.setRowSelectionAllowed(false);

		jTable1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jTable1.getColumnModel().getSelectionModel().setSelectionMode(
				ListSelectionModel.SINGLE_SELECTION);

		for (int i = 0; i < jTable1.getColumnCount(); i++) {
			jTable1.getColumnModel().getColumn(i).setCellRenderer(
					new DateCellRenderer((Calendar) currentCalendar.clone()));
		}

		// set our focus to the current day, if we saw it on the calendar
		if (currentDayRow != 1 && currentDayColumn != -1) {
			jTable1.getColumnModel().getSelectionModel().setSelectionInterval(
					currentDayColumn, currentDayColumn);
			jTable1.getSelectionModel().setSelectionInterval(currentDayRow,
					currentDayRow);
			// System.out.println("row" + currentDayRow);
			// System.out.println("col" + currentDayColumn);
		}

		class MyMouseListener implements java.awt.event.MouseListener {
			public void mousePressed(MouseEvent me) {

			}

			public void mouseReleased(MouseEvent me) {

			}

			public void mouseClicked(MouseEvent me) {
				java.awt.Point p = me.getPoint();
				// System.out.println("Row: " + jTable1.rowAtPoint(p));
				// System.out.println("Col: " + jTable1.columnAtPoint(p));
				myValueChanged(jTable1.rowAtPoint(p), jTable1.columnAtPoint(p));
			}

			public void mouseExited(MouseEvent me) {

			}

			public void mouseEntered(MouseEvent me) {

			}
		}
		;

		jTable1.addMouseListener(new MyMouseListener());

		jTable1.invalidate();
	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		// System.out.println("jButton2.actionPerformed, event=" + evt);
		this.currentCalendar.add(Calendar.MONTH, 1);
		createAndSetNewTableModel();
	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		// System.out.println("jButton1.actionPerformed, event=" + evt);
		this.currentCalendar.add(Calendar.MONTH, -1);
		createAndSetNewTableModel();
	}

	private void jButton3ActionPerformed(ActionEvent evt) {
		// System.out.println("jButton3.actionPerformed, event=" + evt);
		this.currentCalendar = null;
		this.currentCalendar = new PickerCalendar();
		createAndSetNewTableModel();
	}

	public void doPop(JComponent parent) {
		PopupFactory pf = PopupFactory.getSharedInstance();
		if (p != null) {
			p.hide();
			p = null;
		}

		int x = 0;
		int y = 0;

		if (parent != null) {
			x = (int) parent.getLocationOnScreen().getX()
					+ (parent.getWidth() / 2);
			y = (int) parent.getLocationOnScreen().getY()
					+ (parent.getHeight() / 2);
		}

		p = pf.getPopup(parent, this, x, y);
		p.show();

	}

	public void myValueChanged(int row, int col) {

		if (((Calendar) jTable1.getValueAt(row, col)).get(Calendar.MONTH) != currentCalendar.get(Calendar.MONTH)) {
			jTable1.getSelectionModel().clearSelection();
			return;
		}

		firePropertyChange("Calendar", null, jTable1.getValueAt(row, col));
		if (p != null) {
			p.hide();
			p = null;
		}
	}

}
