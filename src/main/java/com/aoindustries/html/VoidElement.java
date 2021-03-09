/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020, 2021  AO Industries, Inc.
 *     support@aoindustries.com
 *     7262 Bull Pen Cir
 *     Mobile, AL 36695
 *
 * This file is part of ao-fluent-html.
 *
 * ao-fluent-html is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * ao-fluent-html is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with ao-fluent-html.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aoindustries.html;

import com.aoindustries.encoding.Serialization;
import java.io.IOException;
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/syntax.html#void-elements">13.1.2 Elements / Void elements</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
abstract public class VoidElement<E extends VoidElement<E, PC>, PC extends Content<PC>> extends Element<E, PC> {

	protected VoidElement(Document document, PC pc) {
		super(document, pc);
	}

	/**
	 * Closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	@SuppressWarnings("deprecation")
	public PC __() throws IOException {
		Writer out = document.getUnsafe(null);
		if(document.getAtnl()) {
			document.autoIndent(out);
			if(document.serialization == Serialization.SGML) {
				out.append('>');
			} else {
				assert document.serialization == Serialization.XML;
				out.write("/>");
			}
			document.clearAtnl();
		} else {
			document.serialization.selfClose(out);
		}
		assert !document.getAtnl();
		doAfterElement(out);
		return pc;
	}

	/**
	 * Called after the element is closed.
	 * <p>
	 * An common use is expected to be invoking {@link Document#autoNl(java.io.Writer)}.
	 * </p>
	 */
	protected void doAfterElement(Writer out) throws IOException {
		// Do nothing
	}
}
