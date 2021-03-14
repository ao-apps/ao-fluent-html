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

import com.aoindustries.html.any.AnySCRIPT;
import java.io.IOException;
import java.io.Writer;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/multipage/scripting.html#the-script-element">4.12.1 The script element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: Extend RawTextElement: https://html.spec.whatwg.org/multipage/syntax.html#raw-text-elements
public class SCRIPT<
	PC extends ScriptSupportingContent<PC>
> extends
	AnySCRIPT<Document, PC, SCRIPT<PC>> {

	protected SCRIPT(Document document, PC pc) {
		super(document, pc);
	}

	protected SCRIPT(Document document, PC pc, String type) {
		super(document, pc, type);
	}

	protected SCRIPT(Document document, PC pc, Type type) {
		super(document, pc, type);
	}

	// Expose to this package, avoiding public to keep a clean API for optimal code assist
	@Override
	protected SCRIPT<PC> writeOpen(Writer out) throws IOException {
		return super.writeOpen(out);
	}
}
