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
import java.io.Writer;

/**
 * See <a href="https://html.spec.whatwg.org/multipage/syntax.html#normal-elements">13.1.2 Elements / Normal elements</a>.
 *
 * @param  <PC>  The parent content model this element is within
 * @param  <__>  This content model, which will be the parent content model of child elements
 * @param  <_c>  This content model as {@link Closeable}, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
abstract public class Normal<
	E  extends Normal<E, PC, __, _c>,
	PC extends Content<PC>,
	__ extends Content<__>, // Transparent can have any: Normal__<PC, __>,
	// Would prefer "_c extends __ & Closeable<PC>", but "a type variable may not be followed by other bounds"
	_c extends Normal_c<PC, _c>
> extends Element<E, PC> {

	protected Normal(Document document, PC pc) {
		super(document, pc);
	}

	/**
	 * Determines if this element has indented content.
	 *
	 * @return {@code true} - defaults to indenting
	 */
	protected boolean isContentIndented() {
		return true;
	}

	/**
	 * Called after opening tag is completed, {@linkplain Document#incDepth() indentation depth is increased}
	 * (if {@link #isContentIndented()}), and before the body is invoked.
	 * <p>
	 * An common use-case is to call {@link Document#autoNl()} to begin body on the next line.
	 * </p>
	 */
	@SuppressWarnings("NoopMethodInAbstractClass")
	protected void doBeforeBody(Writer out) throws IOException {
		// Do nothing by default
	}

	/**
	 * Writes the closing tag.
	 *
	 * @param  closeAttributes  When {@code true}, must end attributes with {@code '>'} before writing the closing tag.
	 *                          These are expected to be combined to a single write.
	 */
	abstract protected void writeClose(Writer out, boolean closeAttributes) throws IOException;

	/**
	 * Ends attributes, invokes the body, then closes this element.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable> PC __(IORunnableE<Ex> body) throws IOException, Ex {
		Writer out = document.getUnsafe(null);
		if(body != null) {
			document.autoIndent(out).unsafe(out, '>');
			boolean contentIndented = isContentIndented();
			if(contentIndented) document.incDepth();
			doBeforeBody(out);
			body.run();
			if(contentIndented) document.decDepth();
			writeClose(out, false);
		} else {
			writeClose(out, true);
		}
		return pc;
	}

	/**
	 * Ends attributes, invokes the body, then closes this element.
	 *
	 * @return  The parent content model this element is within
	 *
	 * @see  #new__()
	 */
	public <Ex extends Throwable> PC __(IOConsumerE<? super __, Ex> body) throws IOException, Ex {
		Writer out = document.getUnsafe(null);
		if(body != null) {
			document.autoIndent(out).unsafe(out, '>');
			boolean contentIndented = isContentIndented();
			if(contentIndented) document.incDepth();
			doBeforeBody(out);
			body.accept(new__());
			if(contentIndented) document.decDepth();
			writeClose(out, false);
		} else {
			writeClose(out, true);
		}
		return pc;
	}

	/**
	 * Ends attributes then closes this element without any body.
	 *
	 * @return  The parent content model this element is within
	 */
	public PC __() throws IOException {
		writeClose(document.getUnsafe(null), true);
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
	 * @see  #new_c()
	 */
	public _c _c() throws IOException {
		Writer out = document.getUnsafe(null);
		document.autoIndent(out).unsafe(out, '>');
		if(isContentIndented()) document.incDepth();
		doBeforeBody(out);
		return new_c();
	}

	/**
	 * Creates a new instance of uncloseable content.
	 *
	 * @see  #__(com.aoindustries.io.function.IOConsumerE)
	 */
	protected abstract __ new__();

	/**
	 * Creates a new instance of closeable content.
	 *
	 * @see  #_c()
	 */
	protected abstract _c new_c();
}
