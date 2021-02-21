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

import com.aoindustries.lang.RunnableE;
import com.aoindustries.util.function.ConsumerE;
import java.io.IOException;

/**
 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class I<PC extends Content> extends Element<I<PC>> implements
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<I<PC>>
{

	public I(Document document) {
		super(document);
	}

	@Override
	protected I<PC> open() throws IOException {
		document.out.write("<i");
		return this;
	}

	/**
	 * Invokes the body then closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable> PC __(RunnableE<Ex> i) throws IOException, Ex {
		if(i != null) {
			document.out.write('>');
			i.run();
			document.out.write("</i>");
		} else {
			document.out.write("></i>");
		}
		@SuppressWarnings("unchecked") PC pc = (PC)document;
		return pc;
	}

	/**
	 * Invokes the body then closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable, IContent extends PhrasingContent<IContent>> PC __(ConsumerE<? super IContent, Ex> i) throws IOException, Ex {
		if(i != null) {
			document.out.write('>');
			@SuppressWarnings("unchecked") IContent c = (IContent)document;
			i.accept(c);
			document.out.write("</i>");
		} else {
			document.out.write("></i>");
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
			document.out.write("</i>");
		} else {
			document.out.write("></i>");
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
		document.out.write("></i>");
		@SuppressWarnings("unchecked") PC pc = (PC)document;
		return pc;
	}
}
