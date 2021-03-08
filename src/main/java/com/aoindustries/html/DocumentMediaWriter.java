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

import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaType;
import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.encoding.MediaWriter;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * A media writer streaming through to a {@link Document}.
 *
 * @author  AO Industries, Inc.
 */
// TODO: Take parent context <PC> and implement a __() to close?
public class DocumentMediaWriter extends MediaWriter {

	private final Document document;

	public DocumentMediaWriter(Document document, MediaEncoder encoder, Writer out) {
		super(document.encodingContext, encoder, out);
		this.document = document;
	}

	public DocumentMediaWriter(Document document, MediaEncoder encoder) {
		this(document, encoder, document.getUnsafe(null));
	}

	public Document getDocument() {
		return document;
	}

	@Override
	protected DocumentMediaWriter getTextWriter() throws UnsupportedEncodingException {
		if(textWriter == null) {
			MediaEncoder textEncoder = MediaEncoder.getInstance(document.encodingContext, MediaType.TEXT, getEncoder().getValidMediaInputType());
			textWriter = (textEncoder == null) ? this : new DocumentMediaWriter(document, textEncoder, this);
		}
		return (DocumentMediaWriter)textWriter;
	}

	@Override
	public DocumentMediaWriter append(char c) throws IOException {
		super.append(c);
		return this;
	}

	@Override
	public DocumentMediaWriter append(CharSequence csq) throws IOException {
		super.append(csq);
		return this;
	}

	@Override
	public DocumentMediaWriter append(CharSequence csq, int start, int end) throws IOException {
		super.append(csq, start, end);
		return this;
	}

	// Not delegating to Document.nl(), because the newlines themselves may need to be encoded.
	@Override
	public DocumentMediaWriter nl() throws IOException {
		super.nl();
		return this;
	}

	// Not delegating to Document.nli(), because the newlines and tabs themselves may need to be encoded.
	@Override
	public DocumentMediaWriter nli() throws IOException {
		super.nli();
		return this;
	}

	// Not delegating to Document.nli(int), because the newlines and tabs themselves may need to be encoded.
	@Override
	public DocumentMediaWriter nli(int depthOffset) throws IOException {
		super.nli(depthOffset);
		return this;
	}

	// Not delegating to Document.indent(), because the tabs themselves may need to be encoded.
	@Override
	public DocumentMediaWriter indent() throws IOException {
		super.indent();
		return this;
	}

	// Not delegating to Document.indent(int), because the tabs themselves may need to be encoded.
	@Override
	public DocumentMediaWriter indent(int depthOffset) throws IOException {
		super.indent(depthOffset);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#getIndent()}.
	 * </p>
	 */
	@Override
	public boolean getIndent() {
		return document.getIndent();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#setIndent(boolean)}.
	 * </p>
	 */
	@Override
	public DocumentMediaWriter setIndent(boolean indent) {
		document.setIndent(indent);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#getDepth()}.
	 * </p>
	 */
	@Override
	public int getDepth() {
		return document.getDepth();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#setDepth(int)}.
	 * </p>
	 */
	@Override
	public DocumentMediaWriter setDepth(int depth) {
		document.setDepth(depth);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#incDepth()}.
	 * </p>
	 */
	@Override
	public DocumentMediaWriter incDepth() {
		document.incDepth();
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link Document#decDepth()}.
	 * </p>
	 */
	@Override
	public DocumentMediaWriter decDepth() {
		document.decDepth();
		return this;
	}

	// Not delegating to Document.sp(), because the spaces themselves may need to be encoded.
	@Override
	public DocumentMediaWriter sp() throws IOException {
		super.sp();
		return this;
	}

	// Not delegating to Document.sp(int), because the spaces themselves may need to be encoded.
	@Override
	public DocumentMediaWriter sp(int count) throws IOException {
		super.sp(count);
		return this;
	}

	@Override
	public DocumentMediaWriter nbsp() throws IOException {
		super.nbsp();
		return this;
	}

	@Override
	public DocumentMediaWriter nbsp(int count) throws IOException {
		super.nbsp(count);
		return this;
	}

	@Override
	public DocumentMediaWriter text(char ch) throws IOException {
		super.text(ch);
		return this;
	}

	// TODO: codePoint?

	@Override
	public DocumentMediaWriter text(char[] cbuf) throws IOException {
		super.text(cbuf);
		return this;
	}

	@Override
	public DocumentMediaWriter text(char[] cbuf, int offset, int len) throws IOException {
		super.text(cbuf, offset, len);
		return this;
	}

	@Override
	public DocumentMediaWriter text(CharSequence csq) throws IOException {
		super.text(csq);
		return this;
	}

	@Override
	public DocumentMediaWriter text(CharSequence csq, int start, int end) throws IOException {
		super.text(csq, start, end);
		return this;
	}

	@Override
	public DocumentMediaWriter text(Object text) throws IOException {
		super.text(text);
		return this;
	}

	@Override
	public <Ex extends Throwable> DocumentMediaWriter text(IOSupplierE<?,Ex> text) throws IOException, Ex {
		super.text(text);
		return this;
	}

	@Override
	public <Ex extends Throwable> DocumentMediaWriter text(MediaWritable<Ex> text) throws IOException, Ex {
		try (DocumentMediaWriter tw = text()) {
			if(text != null) {
				text.writeTo(tw);
			}
		}
		return this;
	}

	@Override
	public DocumentMediaWriter text() throws IOException {
		DocumentMediaWriter tw = getTextWriter();
		if(tw != this) textWriter.getEncoder().writePrefixTo(this);
		return new DocumentMediaWriter(
			document,
			tw.getEncoder(),
			tw.out
		) {
			@Override
			public void close() throws IOException {
				if(tw != this) textWriter.getEncoder().writeSuffixTo(this);
			}
		};
	}

	// TODO: A set of per-type methods, like xml(), script(), style(), ...

	// TODO: A set of out() methods that take MediaType and value

	// TODO: comments

}
