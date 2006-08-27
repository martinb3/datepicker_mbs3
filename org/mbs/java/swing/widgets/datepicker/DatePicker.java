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

import java.io.Serializable;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.BorderFactory;
import javax.swing.Popup;
import javax.swing.PopupFactory;

import javax.swing.JButton;

import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class DatePicker extends javax.swing.JPanel implements Serializable {
	private JButton jButton1;

	private JButton jButton2;
	private JScrollPane jScrollPane1;
	private JButton jButton3;

	private JLabel jLabel1;

	private JTable jTable1;
	
	Popup p;
	
	private Calendar currentCalendar = new PickerCalendar();

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
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
	}

	private void initGUI() {
		try {

			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] { 0.0, 0.0, 0.0 };
			thisLayout.rowHeights = new int[] { 33, 149, 29 };
			thisLayout.columnWeights = new double[] { 0.0, 0.0, 0.0 };
			thisLayout.columnWidths = new int[] { 27, 194, 38 };
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(299, 215));
			this.setBackground(new java.awt.Color(255,255,255));
			{
				jButton1 = new JButton();
				this.add(jButton1, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jButton1.setText("<-");
				jButton1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton1ActionPerformed(evt);
					}
				});
			}
			{
				jButton2 = new JButton();
				this.add(jButton2, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0,
						GridBagConstraints.CENTER, GridBagConstraints.BOTH,
						new Insets(0, 0, 0, 0), 0, 0));
				jButton2.setText("->");
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
				this.add(jButton3, new GridBagConstraints(0, 2, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				jButton3.setText("Today");
				jButton3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						jButton3ActionPerformed(evt);
					}
				});
			}
			{
				jScrollPane1 = new JScrollPane();
				this.add(jScrollPane1, new GridBagConstraints(0, 1, 3, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
				{

					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setRowSelectionAllowed(false);
					jTable1.setCellSelectionEnabled(true);
					createAndSetNewTableModel();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createAndSetNewTableModel() {
		Calendar calendar = (PickerCalendar)currentCalendar.clone(); 
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		jLabel1.setText(String.format("%1$tB %1$tY", calendar));
		
		TableModel jTable1Model = new DefaultTableModel(new String[] {
				"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday", "Saturday" }, calendar
				.getActualMaximum(Calendar.WEEK_OF_MONTH));

		int cols = jTable1Model.getColumnCount();
		int rowcount = 0;
		int colcount = calendar.get(Calendar.DAY_OF_WEEK) - 1;

		// for (int i = 1; i < calendar.get(Calendar.DAY_OF_WEEK); i++) {
		// jTable1Model.setValueAt("x", 0, i-1);
		// }

		int month = calendar.get(Calendar.MONTH);

		while (month == calendar.get(Calendar.MONTH)) {
			jTable1Model.setValueAt(calendar.clone(), //String.format("%1$te", calendar),
					rowcount, colcount);

			calendar.add(Calendar.DAY_OF_YEAR, 1);
			colcount++;
			if (calendar.get(Calendar.DAY_OF_WEEK) == calendar
					.getFirstDayOfWeek())
				rowcount++;

			if (colcount >= cols)
				colcount -= cols;

		}
		
		jTable1.setModel(jTable1Model);
		jTable1.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		final JTable tbl = jTable1; 
		jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent lse)
			{
				int r = jTable1.getSelectedRow();
				int c = jTable1.getSelectedColumn();
				
				if(r == -1 || c == -1)
					return;
				
				firePropertyChange("Calendar", null, tbl.getValueAt(r, c));
				if(p != null)
				{
					p.hide();
					p = null;
				}
			}
		}
		);
		jTable1.invalidate();
	}
	
	private void jButton2ActionPerformed(ActionEvent evt) {
		System.out.println("jButton2.actionPerformed, event=" + evt);
		this.currentCalendar.add(Calendar.MONTH, 1);
		createAndSetNewTableModel();
	}
	
	private void jButton1ActionPerformed(ActionEvent evt) {
		System.out.println("jButton1.actionPerformed, event=" + evt);
		this.currentCalendar.add(Calendar.MONTH, -1);
		createAndSetNewTableModel();
	}
	
	private void jButton3ActionPerformed(ActionEvent evt) {
		System.out.println("jButton3.actionPerformed, event=" + evt);
		this.currentCalendar = null;
		this.currentCalendar = new GregorianCalendar();
		createAndSetNewTableModel();
	}

	
	public void doPop(JComponent parent)
	{
		PopupFactory pf = PopupFactory.getSharedInstance();
		if(p != null)
		{
			p.hide();
			p = null;
		}
		p = pf.getPopup(parent, this, parent.getX(), parent.getY());
		p.show();

	}
}
