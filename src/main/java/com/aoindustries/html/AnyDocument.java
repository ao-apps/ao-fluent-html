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

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.EncodingContext;
import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.encoding.Serialization;
import static com.aoindustries.encoding.TextInXhtmlEncoder.encodeTextInXhtml;
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.encoding.WriterUtil;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.io.Writable;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Coercion;
import com.aoindustries.lang.LocalizedIllegalStateException;
import com.aoindustries.lang.Throwables;
import com.aoindustries.util.i18n.MarkupCoercion;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

/**
 * Fluent Java DSL for high-performance HTML generation.
 * <p>
 * This class implements all content interfaces and supports all element types.
 * </p>
 * <p>
 * See also <a href="https://github.com/xmlet/HtmlFlow">HtmlFlow</a>.
 * </p>
 *
 * @param  <D>   This document type
 *
 * @author  AO Industries, Inc.
 */
public abstract class AnyDocument<D extends AnyDocument<D>> implements AnyContent<D, D> {

	/**
	 * The default, and recommended, encoding for (X)HTML.
	 */
	public static final Charset ENCODING = StandardCharsets.UTF_8;

	static final com.aoindustries.i18n.Resources RESOURCES = com.aoindustries.i18n.Resources.getResources(AnyDocument.class);

	public final EncodingContext encodingContext;
	// TODO: Remove this and just use encodingContext?
	public final Serialization serialization;
	// TODO: Remove this and just use encodingContext?
	public final Doctype doctype;

	/**
	 * Writer for raw output.
	 * <p>
	 * TODO: This field will possibly become "protected" once the full set of HTML tags have been implemented.
	 * </p>
	 *
	 * @deprecated  Direct use of this output may throw-off the automatic newlines and indentation.  Please use any of the
	 *              {@code unsafe(…)} methods.
	 *
	 * @see  #unsafe()
	 * @see  #unsafe(java.lang.CharSequence)
	 * @see  #unsafe(com.aoindustries.io.function.IOSupplierE)
	 * @see  #unsafe(java.lang.Object)
	 * @see  #unsafe(com.aoindustries.io.Writable)
	 * @see  #unsafe(char)
	 * @see  #unsafe(char[])
	 * @see  #unsafe(java.lang.CharSequence, int, int)
	 * @see  #unsafe(char[], int, int)
	 * @see  #getUnsafe()
	 * @see  #getUnsafe(java.lang.Boolean)
	 * @see  #setOut(java.io.Writer)
	 */
	// TODO: Wrap this writer in XhtmlValidator if is not already validating XHTML?
	//       If wrapping, consider uses of this losing access to this wrapping, such as NoCloseWriter
	// TODO: Make this be a ChainWriter?  This might be incorrect as it would encourage using html.out instead of elements and attributes
	@Deprecated
	@SuppressWarnings("PublicField") // TODO: Should this be final again?  Will we always need setOut, such as opening and closing tag separate processing in legacy taglibs?
	public Writer out;

	/**
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #setOut(java.io.Writer)
	 */
	protected AnyDocument(EncodingContext encodingContext, Serialization serialization, Doctype doctype, Writer out) {
		this.encodingContext = encodingContext;
		this.serialization = serialization;
		this.doctype = doctype;
		this.out = out;
	}

	/**
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #setOut(java.io.Writer)
	 */
	protected AnyDocument(EncodingContext encodingContext, Writer out) {
		this(
			encodingContext,
			encodingContext.getSerialization(),
			encodingContext.getDoctype(),
			out
		);
	}

	/**
	 * Replaces the writer this document is writing to.
	 *
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #getUnsafe()
	 * @see  #getUnsafe(java.lang.Boolean)
	 */
	public void setOut(Writer out) {
		this.out = out;
	}

	// <editor-fold desc="Unsafe">
	@Override
	public Writer getUnsafe(Boolean endsNewline) throws IllegalStateException {
		Writer unsafe = out;
		if(unsafe == null) throw new LocalizedIllegalStateException(RESOURCES, "getUnsafe.noOut");
		if(endsNewline != null) setAtnl(endsNewline);
		return unsafe;
	}

	@Override
	public Writer getUnsafe() throws IllegalStateException {
		return getUnsafe(false);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D unsafe(char ch) throws IOException {
		return unsafe(getUnsafe(null), ch);
	}

	D unsafe(Writer out, char ch) throws IOException {
		out.append(ch);
		return setAtnl(ch == NL);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D unsafe(char[] cbuf) throws IOException {
		if(cbuf != null) {
			int len = cbuf.length;
			if(len > 0) {
				unsafe(
					getUnsafe(null),
					cbuf,
					cbuf[len - 1] == NL
				);
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * Performs raw output of a non-empty array.
	 *
	 * @param  endsNewline  Declares the array ends in a newline.
	 *                      Calls {@link #setAtnl(boolean)} after the write.
	 *                      This is an optimization that is verified when assertions are enabled.
	 *
	 * @return  {@code this} document
	 */
	D unsafe(Writer out, char[] cbuf, boolean endsNewline) throws IOException {
		assert cbuf.length > 0;
		out.write(cbuf);
		assert endsNewline == (cbuf[cbuf.length - 1] == NL);
		return setAtnl(endsNewline);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D unsafe(char[] cbuf, int offset, int len) throws IOException {
		if(cbuf != null && len > 0) {
			unsafe(
				getUnsafe(null),
				cbuf,
				offset,
				len,
				cbuf[offset + len - 1] == NL
			);
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * Performs raw output of a non-empty array.
	 *
	 * @param  endsNewline  Declares the array ends in a newline.
	 *                      Calls {@link #setAtnl(boolean)} after the write.
	 *                      This is an optimization that is verified when assertions are enabled.
	 *
	 * @return  {@code this} document
	 */
	D unsafe(Writer out, char[] cbuf, int offset, int len, boolean endsNewline) throws IOException {
		assert len > 0;
		out.write(cbuf, offset, len);
		assert endsNewline == (cbuf[offset + len - 1] == NL);
		return setAtnl(endsNewline);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D unsafe(CharSequence csq) throws IOException {
		if(csq != null) {
			int len = csq.length();
			if(len > 0) {
				unsafe(
					getUnsafe(null),
					csq,
					csq.charAt(len - 1) == NL
				);
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * Performs raw output of a non-empty sequence.
	 *
	 * @param  endsNewline  Declares the sequence ends in a newline.
	 *                      Calls {@link #setAtnl(boolean)} after the write.
	 *                      This is an optimization that is verified when assertions are enabled.
	 *
	 * @return  {@code this} document
	 */
	D unsafe(Writer out, CharSequence csq, boolean endsNewline) throws IOException {
		assert csq.length() > 0;
		out.append(csq);
		assert endsNewline == (csq.charAt(csq.length() - 1) == NL);
		return setAtnl(endsNewline);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D unsafe(CharSequence csq, int start, int end) throws IOException {
		if(csq != null && end > start) {
			unsafe(
				getUnsafe(null),
				csq,
				start,
				end,
				csq.charAt(end - 1) == NL
			);
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * Performs raw output of a non-empty sequence.
	 *
	 * @param  endsNewline  Declares the sequence ends in a newline.
	 *                      Calls {@link #setAtnl(boolean)} after the write.
	 *                      This is an optimization that is verified when assertions are enabled.
	 *
	 * @return  {@code this} document
	 */
	D unsafe(Writer out, CharSequence csq, int start, int end, boolean endsNewline) throws IOException {
		assert end > start;
		out.append(csq, start, end);
		assert endsNewline == (csq.charAt(end - 1) == NL);
		return setAtnl(endsNewline);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D unsafe(Object unsafe) throws IOException {
		return unsafe(getUnsafe(null), unsafe);
	}

	@SuppressWarnings("UseSpecificCatch")
	D unsafe(Writer out, Object unsafe) throws IOException {
		// Support Optional
		while(unsafe instanceof Optional) {
			unsafe = ((Optional<?>)unsafe).orElse(null);
		}
		while(unsafe instanceof IOSupplierE<?, ?>) {
			try {
				unsafe = ((IOSupplierE<?, ?>)unsafe).get();
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		if(unsafe != null) {
			if(unsafe instanceof char[]) {
				char[] cbuf = (char[])unsafe;
				int len = cbuf.length;
				if(len > 0) {
					return unsafe(
						out,
						cbuf,
						cbuf[len - 1] == NL
					);
				} else {
					// Nothing to write
					@SuppressWarnings("unchecked") D d = (D)this;
					return d;
				}
			}
			if(unsafe instanceof CharSequence) {
				CharSequence csq = (CharSequence)unsafe;
				int len = csq.length();
				if(len > 0) {
					return unsafe(
						out,
						csq,
						csq.charAt(len - 1) == NL
					);
				} else {
					// Nothing to write
					@SuppressWarnings("unchecked") D d = (D)this;
					return d;
				}
			}
			if(unsafe instanceof Writable) {
				return unsafe(out, (Writable)unsafe);
			}
			// Allow no markup from translations
			Coercion.write(unsafe, out);
			clearAtnl(); // Unknown, safe to assume not at newline
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  {@code this} document
	 */
	@Override
	public <Ex extends Throwable> D unsafe(IOSupplierE<?, Ex> unsafe) throws IOException, Ex {
		return unsafe(getUnsafe(null), unsafe);
	}

	<Ex extends Throwable> D unsafe(Writer out, IOSupplierE<?, Ex> unsafe) throws IOException, Ex {
		return unsafe(out, (unsafe == null) ? null : unsafe.get());
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D unsafe(Writable unsafe) throws IOException {
		return unsafe(getUnsafe(null), unsafe);
	}

	D unsafe(Writer out, Writable unsafe) throws IOException {
		if(unsafe != null) {
			if(unsafe.isFastToString()) {
				String str = unsafe.toString();
				int len = str.length();
				if(len > 0) {
					unsafe(
						out,
						str,
						str.charAt(len - 1) == NL
					);
				}
			} else {
				try (Writer _out = unsafe(out)) {
					unsafe.writeTo(_out);
				}
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public Writer unsafe() throws IOException {
		return unsafe(getUnsafe(null));
	}

	Writer unsafe(Writer out) throws IOException {
		clearAtnl(); // Unknown, safe to assume not at newline
		return new NoCloseWriter(out);
	}

	// TODO: Include a new interface similar to TextContent called "Unsafe" that Content would also extend?
	// </editor-fold>

	/**
	 * @see Doctype#xmlDeclaration(com.aoindustries.encoding.Serialization, java.lang.String, java.lang.Appendable)
	 */
	public D xmlDeclaration(String documentEncoding) throws IOException {
		if(doctype.xmlDeclaration(serialization, documentEncoding, getUnsafe(null))) {
			setAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * @see Doctype#doctype(com.aoindustries.encoding.Serialization, java.lang.Appendable)
	 */
	public D doctype() throws IOException {
		if(doctype.doctype(serialization, getUnsafe(null))) {
			setAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public D getDocument() {
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// <editor-fold desc="Automatic Newline and Indentation">
	/**
	 * Is automatic newline and indenting enabled?
	 */
	private boolean autonli;

	@Override
	public boolean getAutonli() {
		return autonli;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D setAutonli(boolean autonli) {
		this.autonli = autonli;
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * Is the output currently at a newline?
	 */
	private boolean atnl;

	@Override
	public boolean getAtnl() {
		return atnl;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D setAtnl() {
		this.atnl = true;
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D setAtnl(boolean atnl) {
		this.atnl = atnl;
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D clearAtnl() {
		this.atnl = false;
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D autoNl() throws IOException {
		return autoNl(getUnsafe(null));
	}

	D autoNl(Writer out) throws IOException {
		if(getAutonli() && !getAtnl()) {
			out.append(NL);
			setAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D autoNli() throws IOException {
		return autoNli(getUnsafe(null), 0);
	}

	D autoNli(Writer out) throws IOException {
		return autoNli(out, 0);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D autoNli(int depthOffset) throws IOException {
		return autoNli(getUnsafe(null), depthOffset);
	}

	D autoNli(Writer out, int depthOffset) throws IOException {
		if(getAutonli()) {
			if(getAtnl()) {
				// Already at newline, indentation only
				int d;
				if(getIndent() && (d = getDepth() + depthOffset) > 0) {
					WriterUtil.indent(out, d);
					clearAtnl();
				}
			} else {
				// Combined newline and indentation
				int d;
				if(getIndent() && (d = getDepth() + depthOffset) > 0) {
					WriterUtil.nli(out, d);
					// Already not at newline: clearAtnl();
				} else {
					out.append(NL);
					setAtnl();
				}
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D autoIndent() throws IOException {
		return autoIndent(getUnsafe(null), 0);
	}

	D autoIndent(Writer out) throws IOException {
		return autoIndent(out, 0);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public D autoIndent(int depthOffset) throws IOException {
		return autoIndent(getUnsafe(null), depthOffset);
	}

	D autoIndent(Writer out, int depthOffset) throws IOException {
		int depth_;
		if(getAutonli() && getIndent() && getAtnl() && (depth_ = getDepth() + depthOffset) > 0) {
			WriterUtil.indent(out, depth_);
			clearAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}
	// </editor-fold>

	/**
	 * Is indenting enabled?
	 */
	// Matches MediaWriter.indent
	private boolean indent;

	/**
	 * Current indentation level.
	 */
	// Matches MediaWriter.depth
	private int depth;

	// Matches MediaWriter.nl()
	@Override
	public D nl() throws IOException {
		return nl(getUnsafe(null));
	}

	D nl(Writer out) throws IOException {
		out.append(NL);
		setAtnl();
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// Matches MediaWriter.nli()
	@Override
	public D nli() throws IOException {
		return nli(getUnsafe(null), 0);
	}

	D nli(Writer out) throws IOException {
		return nli(out, 0);
	}

	// Matches MediaWriter.nli(int)
	@Override
	public D nli(int depthOffset) throws IOException {
		return nli(getUnsafe(null), depthOffset);
	}

	D nli(Writer out, int depthOffset) throws IOException {
		int depth_;
		if(getIndent() && (depth_ = getDepth() + depthOffset) > 0) {
			WriterUtil.nli(out, depth_);
			clearAtnl();
		} else {
			out.append(NL);
			setAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// Matches MediaWriter.indent()
	@Override
	public D indent() throws IOException {
		return indent(getUnsafe(null), 0);
	}

	D indent(Writer out) throws IOException {
		return indent(out, 0);
	}

	// Matches MediaWriter.indent(int)
	@Override
	public D indent(int depthOffset) throws IOException {
		return indent(getUnsafe(null), depthOffset);
	}

	D indent(Writer out, int depthOffset) throws IOException {
		int depth_;
		if(getIndent() && (depth_ = getDepth() + depthOffset) > 0) {
			WriterUtil.indent(out, depth_);
			clearAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// Matches MediaWriter.getIndent()
	@Override
	public boolean getIndent() {
		return indent;
	}

	// Matches MediaWriter.setIndent(int)
	@Override
	public D setIndent(boolean indent) {
		this.indent = indent;
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// Matches MediaWriter.getDepth()
	@Override
	public int getDepth() {
		return depth;
	}

	// Matches MediaWriter.setDepth(int)
	@Override
	public D setDepth(int depth) {
		if(depth < 0) throw new IllegalArgumentException("depth < 0: " + depth);
		this.depth = depth;
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// Matches MediaWriter.incDepth()
	@Override
	public D incDepth() {
		if(getIndent()) {
			int d = ++depth;
			if(d < 0) depth = Integer.MAX_VALUE;
		}
		assert depth >= 0;
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// Matches MediaWriter.decDepth()
	@Override
	public D decDepth() {
		if(getIndent()) {
			int d = --depth;
			if(d < 0) depth = 0;
		}
		assert depth >= 0;
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// Matches MediaWriter.sp()
	@Override
	public D sp() throws IOException {
		return sp(getUnsafe(null));
	}

	D sp(Writer out) throws IOException {
		out.append(SPACE);
		clearAtnl();
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// Matches MediaWriter.sp(int)
	@Override
	public D sp(int count) throws IOException {
		return sp(getUnsafe(null), count);
	}

	D sp(Writer out, int count) throws IOException {
		if(count > 0) {
			WriterUtil.sp(out, count);
			clearAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public D nbsp() throws IOException {
		return nbsp(getUnsafe(null));
	}

	D nbsp(Writer out) throws IOException {
		out.append(NBSP);
		clearAtnl();
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public D nbsp(int count) throws IOException {
		return nbsp(getUnsafe(null), count);
	}

	D nbsp(Writer out, int count) throws IOException {
		if(count > 0) {
			WriterUtil.nbsp(out, count);
			clearAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public D text(char ch) throws IOException {
		return text(getUnsafe(null), ch);
	}

	D text(Writer out, char ch) throws IOException {
		if(ch == NL) {
			out.write(NL);
			setAtnl();
		} else {
			autoIndent(out);
			encodeTextInXhtml(ch, out);
			clearAtnl();
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	// TODO: codePoint?

	@Override
	public D text(char[] cbuf) throws IOException {
		return text(getUnsafe(null), cbuf);
	}

	D text(Writer out, char[] cbuf) throws IOException {
		if(cbuf != null) {
			int len = cbuf.length;
			if(len > 0) {
				if(cbuf[len - 1] == NL) {
					if(len == 1) {
						out.write(NL);
					} else {
						autoIndent(out);
						encodeTextInXhtml(cbuf, out);
					}
					setAtnl();
				} else {
					autoIndent(out);
					encodeTextInXhtml(cbuf, out);
					clearAtnl();
				}
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public D text(char[] cbuf, int offset, int len) throws IOException {
		return text(getUnsafe(null), cbuf, offset, len);
	}

	D text(Writer out, char[] cbuf, int offset, int len) throws IOException {
		if(cbuf != null && len > 0) {
			if(cbuf[offset + len - 1] == NL) {
				if(len == 1) {
					out.write(NL);
				} else {
					autoIndent(out);
					encodeTextInXhtml(cbuf, offset, len, out);
				}
				setAtnl();
			} else {
				autoIndent(out);
				encodeTextInXhtml(cbuf, offset, len, out);
				clearAtnl();
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public D text(CharSequence csq) throws IOException {
		return text(getUnsafe(null), csq);
	}

	D text(Writer out, CharSequence csq) throws IOException {
		if(csq != null) {
			int len = csq.length();
			if(len > 0) {
				if(csq.charAt(len - 1) == NL) {
					if(len == 1) {
						out.write(NL);
					} else {
						autoIndent(out);
						encodeTextInXhtml(csq, out);
					}
					setAtnl();
				} else {
					autoIndent(out);
					encodeTextInXhtml(csq, out);
					clearAtnl();
				}
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public D text(CharSequence csq, int start, int end) throws IOException {
		return text(getUnsafe(null), csq, start, end);
	}

	D text(Writer out, CharSequence csq, int start, int end) throws IOException {
		if(csq != null && end > start) {
			int last = end - 1;
			if(csq.charAt(last) == NL) {
				if(start == last) {
					out.write(NL);
				} else {
					autoIndent(out);
					encodeTextInXhtml(csq, start, end, out);
				}
				setAtnl();
			} else {
				autoIndent(out);
				encodeTextInXhtml(csq, start, end, out);
				clearAtnl();
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public D text(Object text) throws IOException {
		return text(getUnsafe(null), text);
	}

	@SuppressWarnings("UseSpecificCatch")
	D text(Writer out, Object text) throws IOException {
		// Support Optional
		while(text instanceof Optional) {
			text = ((Optional<?>)text).orElse(null);
		}
		while(text instanceof IOSupplierE<?, ?>) {
			try {
				text = ((IOSupplierE<?, ?>)text).get();
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		if(text != null) {
			if(text instanceof char[]) {
				return text(out, (char[])text);
			}
			if(text instanceof CharSequence) {
				return text(out, (CharSequence)text);
			}
			if(text instanceof Writable) {
				Writable writable = (Writable)text;
				if(writable.isFastToString()) {
					return text(out, writable.toString());
				}
			}
			if(text instanceof MediaWritable) {
				try {
					return text(out, (MediaWritable<?>)text);
				} catch(Throwable t) {
					throw Throwables.wrap(t, IOException.class, IOException::new);
				}
			}
			// Allow text markup from translations
			autoIndent(out);
			MarkupCoercion.write(text, MarkupType.XHTML, false, textInXhtmlEncoder, false, out);
			clearAtnl(); // Unknown, safe to assume not at newline
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  {@code this} document
	 */
	@Override
	public <Ex extends Throwable> D text(IOSupplierE<?, Ex> text) throws IOException, Ex {
		return text(getUnsafe(null), text);
	}

	<Ex extends Throwable> D text(Writer out, IOSupplierE<?, Ex> text) throws IOException, Ex {
		return text(out, (text == null) ? null : text.get());
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 *
	 * @return  {@code this} document
	 */
	@Override
	public <Ex extends Throwable> D text(MediaWritable<Ex> text) throws IOException, Ex {
		return text(getUnsafe(null), text);
	}

	<Ex extends Throwable> D text(Writer out, MediaWritable<Ex> text) throws IOException, Ex {
		if(text != null) {
			try (DocumentMediaWriter<D> _out = text(out)) {
				text.writeTo(_out);
			}
		}
		@SuppressWarnings("unchecked") D d = (D)this;
		return d;
	}

	@Override
	public DocumentMediaWriter<D> text() throws IOException {
		return text(getUnsafe(null));
	}

	DocumentMediaWriter<D> text(Writer out) throws IOException {
		autoIndent(out);
		clearAtnl(); // Unknown, safe to assume not at newline
		@SuppressWarnings("unchecked") D d = (D)this;
		return new DocumentMediaWriter<>(
			d,
			textInXhtmlEncoder,
			new NoCloseWriter(out)
		);
	}

	// TODO: A set of per-type methods, like xml(), script(), style(), ...

	// TODO: A set of out()/unsafe() methods that take MediaType and value

	// TODO: comments
}
