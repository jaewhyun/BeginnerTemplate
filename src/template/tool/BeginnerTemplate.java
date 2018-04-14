/**
 * This tool sets up a basic template for beginners in Java Mode
 *
 * (c) 2015
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General
 * Public License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place, Suite 330,
 * Boston, MA  02111-1307  USA
 *
 * @author   Jae Hyun
 * @modified April 14th, 2018
 * @version  1.0.0
 */

package template.tool;

import processing.app.Base;
import processing.app.tools.Tool;
import processing.app.SketchCode;
import processing.app.Sketch;
import processing.app.ui.Editor;
import processing.core.PApplet;

import javax.swing.*;
import javax.swing.JComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;

import java.io.*;
import java.util.*;

public class BeginnerTemplate implements Tool, ActionListener {
	Base base;
	private JFrame currentframe;
	private SketchCode current;
	private int currentIndex;
	private SketchCode[] code;

	public String getMenuTitle() {
		return "A Template for Beginners";
	}

	public void init(Base base) {
		// Store a reference to the Processing application itself
		this.base = base;
	}

	public void run() {
		createChoices();
		// Fill in author.name, author.url, tool.prettyVersion and
		// project.prettyName in build.properties for them to be auto-replaced here.
		System.out.println("Beginner Templates 1.0.0 by Jae Hyun");
	}

	public void actionPerformed(ActionEvent e) {
		if(base == null) {
			return;
		}

		String selected = e.getActionCommand();

		if(selected.equals("Basic Template")) {
			String[] options = new String[] {"New Sketch", "Overwrite"};
			int response = JOptionPane.showOptionDialog(null, "This action will overwrite your current tab. \nIf not, please open up a new sketch and try again. \nWould you like to continue?", "Warning", JOptionPane.DEFAULT_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

			String basicTemplate = 	"/*\n"+
									" * {title of your project}\n"+
									" * by {your name}\n" +
									" *\n" +
									" * {description of your project}\n"+
									" *\n"+
									" */\n"+
									"\n"+
									"\n"+
									"void setup() {"+"\n"+
									"	size({fill in});    //set window size\n"+
									"}\n"+
									"void draw(){"+"\n"+
									"	background({fill in});    //set background\n"+
									"}\n";

			if(response == 0) {
				base.handleNew();
				Editor editor = base.getActiveEditor();
				Sketch currentSketch = editor.getSketch();
				currentIndex = currentSketch.getCurrentCodeIndex();

				editor.setText(basicTemplate);
			} else {
				Editor editor = base.getActiveEditor();
				editor.setText(basicTemplate);
			}

		} else if(selected.equals("Class Template")) {
			Editor editor = base.getActiveEditor();
			Sketch currentSketch = editor.getSketch();
			currentSketch.handleNewCode();

			code = currentSketch.getCode();
			currentIndex = currentSketch.getCurrentCodeIndex();
			if(code.length > 1) {
				String tabname = code[currentIndex].getPrettyName();

				String classTemplate = 	"/*\n"+
										" * {description of current class}\n"+
										" * by {your name}\n"+
										" *\n"+
										" */\n"+
										"\n"+
										"\n"+
										"class "+tabname+"{"+"\n"+
										"	//initiate variables here"+"\n"+
										"\n"+
										"	"+tabname+"(){"+"\n"+
										"		//Write your code here\n"+
										"	}\n"+
										"\n"+
										"}";
				editor.setText(classTemplate);
			}
		}
	}

	private void createChoices() {
		if(currentframe != null) {
			currentframe.setVisible(true);
			return;
		}

		currentframe = new JFrame("Beginner Templates");
		JPanel panelOptions = new JPanel(new BorderLayout());

		JButton addBasic = new JButton("Basic Template");
		addBasic.addActionListener(this);
		addBasic.setActionCommand("Basic Template");

		JButton addClass = new JButton("Class Template");
		addClass.addActionListener(this);
		addClass.setActionCommand("Class Template");

		panelOptions.add(addBasic, BorderLayout.WEST);
		panelOptions.add(addClass, BorderLayout.EAST);


		//currentframe.setContentPane();

		currentframe.setSize(285, 80);
		currentframe.setVisible(true);

		currentframe.requestFocusInWindow();

		currentframe.add(panelOptions);
	}
}
