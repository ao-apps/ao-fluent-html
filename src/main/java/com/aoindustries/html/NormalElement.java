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
 * See <a href="https://html.spec.whatwg.org/#normal-elements">13.1.2 Elements / Normal elements</a>.
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <C>   This content model, which will be the parent content model of child elements
 * @param  <CC>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
abstract public class NormalElement<
	E  extends NormalElement<E, PC, C, CC>,
	PC extends Content,
	C  extends NormalElement.NormalContent<PC> /*Content*/,
	// Would prefer "CC extends C & Closeable<PC>", but "a type variable may not be followed by other bounds"
	CC extends NormalElement.NormalContent<PC> & Closeable<PC>
> extends Element<E, PC> {

	public static abstract class NormalContent<PC extends Content>
		implements Content {

		protected final NormalElement<?, PC, ?, ?> element;

		protected NormalContent(NormalElement<?, PC, ?, ?> element) {
			this.element = element;
		}

		@Override
		public Document getDocument() {
			return element.document;
		}
	}

	public static abstract class CloseableNormalContent<PC extends Content>
		extends NormalContent<PC>
		implements Closeable<PC> {

		protected CloseableNormalContent(NormalElement<?, PC, ?, ?> element) {
			super(element);
		}

		@Override
		public PC __() throws IOException {
			element.writeClose();
			return element.pc;
		}
	}

	protected NormalElement(Document document, PC pc) {
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
	 *
	 * @see  #newC()
	 */
	public <Ex extends Throwable> PC __(IOConsumerE<? super C, Ex> body) throws IOException, Ex {
		document.out.write('>');
		if(body != null) body.accept(newC());
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

	/**
	 * Ends attributes then begins element content
	 *
	 * @return  The content model of this element, which will be the parent content model of child elements.
	 *          This must be {@linkplain Closeable#__() ended} or {@linkplain Closeable#close() closed} in order to end
	 *          the tag.  This is well suited for use in a try-with-resources block.
	 *
	 * @see  Closeable#__()
	 * @see  Closeable#close()
	 * @see  #newCC()
	 */
	public CC content() throws IOException {
		document.out.write('>');
		return newCC();
	}

	/**
	 * Creates a new instance of uncloseable content.
	 *
	 * @see  #__(com.aoindustries.io.function.IOConsumerE)
	 */
	protected abstract C newC();

	/**
	 * Creates a new instance of closeable content.
	 *
	 * @see  #content()
	 */
	protected abstract CC newCC();
}
