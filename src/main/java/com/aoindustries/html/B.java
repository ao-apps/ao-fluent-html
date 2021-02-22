/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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

import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class B<PC extends UnionContent.Palpable_Phrasing<PC>> extends Element<B<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<B<PC>>
{

	public B(Document document) {
		super(document);
	}

	@Override
	protected B<PC> open() throws IOException {
		document.out.write("<b");
		return this;
	}

	/**
	 * Invokes the body then closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable> PC __(IORunnableE<Ex> b) throws IOException, Ex {
		if(b != null) {
			document.out.write('>');
			b.run();
			document.out.write("</b>");
		} else {
			document.out.write("></b>");
		}
		@SuppressWarnings("unchecked") PC pc = (PC)document;
		return pc;
	}

	/**
	 * Invokes the body then closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable, BContent extends PhrasingContent<BContent>> PC __(IOConsumerE<? super BContent, Ex> b) throws IOException, Ex {
		if(b != null) {
			document.out.write('>');
			@SuppressWarnings("unchecked") BContent c = (BContent)document;
			b.accept(c);
			document.out.write("</b>");
		} else {
			document.out.write("></b>");
		}
		@SuppressWarnings("unchecked") PC pc = (PC)document;
		return pc;
	}

	/**
	 * Writes a text body then closes this element.
	 *
	 * @return  The parent content model this element is within
	 *
	 * @see  Document#text(java.lang.Object)
	 */
	public PC __(Object text) throws IOException {
		if(text != null) {
			document.out.write('>');
			document.text(text);
			document.out.write("</b>");
		} else {
			document.out.write("></b>");
		}
		@SuppressWarnings("unchecked") PC pc = (PC)document;
		return pc;
	}

	/**
	 * Closes this element without any body.
	 *
	 * @return  The parent content model this element is within
	 */
	public PC __() throws IOException {
		document.out.write("></b>");
		@SuppressWarnings("unchecked") PC pc = (PC)document;
		return pc;
	}
}
