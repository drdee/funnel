/**
 *Copyright (C) 2012  Wikimedia Foundation
 *
 *This program is free software; you can redistribute it and/or
 *modify it under the terms of the GNU General Public License
 *as published by the Free Software Foundation; either version 2
 *of the License, or (at your option) any later version.
 *
 *This program is distributed in the hope that it will be useful,
 *but WITHOUT ANY WARRANTY; without even the implied warranty of
 *MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *GNU General Public License for more details.
 *
 *You should have received a copy of the GNU General Public License
 *along with this program; if not, write to the Free Software
 *Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * @version $Id: $Id
 */

package org.wikimedia.analytics.kraken.exceptions;

// TODO: Auto-generated Javadoc
/**
 * The Class MalformedFunnelException.
 */
public class MalformedFunnelException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * The Constructor.
	 */
	public MalformedFunnelException() {
	}

	/**
	 * The Constructor.
	 *
	 * @param message the message
	 */
	public MalformedFunnelException(String message) {
		super(message);
	}

	/**
	 * The Constructor.
	 *
	 * @param cause the cause
	 */
	public MalformedFunnelException(Throwable cause) {
		super(cause);
	}

	/**
	 * The Constructor.
	 *
	 * @param message the message
	 * @param cause the cause
	 */
	public MalformedFunnelException(String message, Throwable cause) {
		super(message, cause);
	}

}
