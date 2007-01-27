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

import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import java.util.*;
import javax.swing.*;

import org.mbs3.widgets.datepicker.DatePicker;

public class DatePickerDemo extends javax.swing.JFrame {

	private JButton jButton1;

	private JTextField jTextField1;

	private DatePicker dp = new DatePicker();

	/**
	 * Auto-generated main method to display this JFrame
	 */
	public static void main(String[] args) {
		DatePickerDemo inst = new DatePickerDemo();
		inst.setVisible(true);
	}

	public DatePickerDemo() {
		super();
		initGUI();
	}

	private void initGUI() {
		try {
			GridLayout thisLayout = new GridLayout(2, 3);
			thisLayout.setHgap(5);
			thisLayout.setVgap(5);
			thisLayout.setColumns(3);
			thisLayout.setRows(2);
			getContentPane().setLayout(thisLayout);
			this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

			jTextField1 = new JTextField();
			getContentPane().add(jTextField1);
			jTextField1.setText("jTextField1");
			jTextField1.setPreferredSize(new java.awt.Dimension(160, 77));

			jButton1 = new JButton();
			getContentPane().add(jButton1);
			jButton1.setText("jButton1");
			jButton1.setPreferredSize(new java.awt.Dimension(392, 23));
			jButton1.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent evt) {
					jButton1MouseClicked(evt);
				}
			});

			this.setSize(227, 150);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jButton1MouseClicked(MouseEvent evt) {
		// System.out.println("jButton1.mouseClicked, event=" + evt);
		dp.addPropertyChangeListener("Calendar",
				new java.beans.PropertyChangeListener() {
					public void propertyChange(
							java.beans.PropertyChangeEvent pce) {
						// System.out.println(pce);
						Calendar cal = (Calendar) pce.getNewValue();
						jTextField1.setText(String.format("%1$tD", cal));
					}
				});
		dp.doPop(jButton1);
	}
}
