/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.rpgwizard.editor.editors;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import javax.swing.JPanel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.rpgwizard.common.assets.AbstractAsset;
import org.rpgwizard.common.assets.AssetDescriptor;
import org.rpgwizard.common.assets.Program;
import org.rpgwizard.editor.ui.AssetEditorWindow;
import org.rpgwizard.editor.ui.resources.Icons;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 * Built in program editor for JavaScript files used in the engine.
 *
 * @author Joshua Michael Daly
 */
public class ProgramEditor extends AssetEditorWindow {

	private final Program program;

	private RSyntaxTextArea textArea;

	public ProgramEditor(Program program) {
		super("Untitled", true, true, true, true, Icons.getIcon("program"));

		this.program = program;

		if (program.getDescriptor() == null) {
			init(program, "Untitled");
		} else {
			init(program, new File(program.getDescriptor().getURI()).getName());
		}
	}

	@Override
	public AbstractAsset getAsset() {
		return program;
	}

	@Override
	public void save() throws Exception {
		program.update(textArea.getText());
		save(program);
	}

	@Override
	public void saveAs(File file) throws Exception {
		program.setDescriptor(new AssetDescriptor(file.toURI()));
		setTitle(file.getName());
		save();
	}

	private void init(Program program, String fileName) {
		JPanel panel = new JPanel(new BorderLayout());

		String code = program.getProgramBuffer().toString();
		textArea = new RSyntaxTextArea(code, 30, 120);
		textArea.setCaretPosition(0);
		textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_JAVASCRIPT);
		textArea.setCodeFoldingEnabled(true);
		textArea.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				setNeedSave(true);
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				setNeedSave(true);
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				setNeedSave(true);
			}
		});

		RTextScrollPane scrollPane = new RTextScrollPane(textArea);
		panel.add(scrollPane);

		setContentPane(panel);
		setTitle(fileName);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}

}