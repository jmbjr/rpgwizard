/**
 * Copyright (c) 2015, rpgtoolkit.net <help@rpgtoolkit.net>
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.rpgwizard.editor.ui.listeners;

import java.util.EventListener;
import org.rpgwizard.editor.editors.tileset.TileRegionSelectionEvent;
import org.rpgwizard.editor.editors.tileset.TileSelectionEvent;

/**
 *
 *
 * @author Joshua Michael Daly
 */
public interface TileSelectionListener extends EventListener {

	/**
	 *
	 * @param e
	 */
	public void tileSelected(TileSelectionEvent e);

	/**
	 *
	 * @param e
	 */
	public void tileRegionSelected(TileRegionSelectionEvent e);

}
