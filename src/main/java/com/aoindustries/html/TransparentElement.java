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
 * See <a href="https://html.spec.whatwg.org/#transparent-content-models">3.2.5.3 Transparent content models</a>.
 *
 * @param  <PC>  The parent content model this element is within,
 *               which will also be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
abstract public class TransparentElement<
	E  extends TransparentElement<E, PC>,
	PC extends Content
> extends Element<E, PC> {

	protected TransparentElement(Document document, PC pc) {
		super(document, pc);
	}

	/**
	 * Writes the closing tag.
	 */
	abstract protected void writeClose() throws IOException;

	/**
	 * Ends attributes, invokes the body, then closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable> PC __(IORunnableE<Ex> body) throws IOException, Ex {
		document.out.write('>');
		if(body != null) body.run();
		writeClose();
		return pc;
	}

	/**
	 * Ends attributes, invokes the body, then closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable> PC __(IOConsumerE<? super PC, Ex> body) throws IOException, Ex {
		document.out.write('>');
		if(body != null) body.accept(pc);
		writeClose();
		return pc;
	}

	/**
	 * Ends attributes then closes this element without any body.
	 *
	 * @return  The parent content model this element is within
	 */
	public PC __() throws IOException {
		document.out.write('>');
		writeClose();
		return pc;
	}
}
