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
package com.aoindustries.html.any;

import com.aoindustries.encoding.MediaEncoder;
import com.aoindustries.encoding.MediaType;
import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.encoding.MediaWriter;
import com.aoindustries.io.function.IOSupplierE;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

/**
 * A media writer streaming through to a {@link AnyDocument Document}.
 *
 * @author  AO Industries, Inc.
 */
// TODO: Take parent context <PC> and implement a __() to close?
public class DocumentMediaWriter<D extends AnyDocument<D>> extends MediaWriter {

	private final D document;

	public DocumentMediaWriter(D document, MediaEncoder encoder, Writer out) {
		super(document.encodingContext, encoder, out);
		this.document = document;
	}

	public DocumentMediaWriter(D document, MediaEncoder encoder) {
		this(document, encoder, document.getUnsafe(null));
	}

	public D getDocument() {
		return document;
	}

	@Override
	protected DocumentMediaWriter<D> getTextWriter() throws UnsupportedEncodingException {
		if(textWriter == null) {
			MediaEncoder textEncoder = MediaEncoder.getInstance(document.encodingContext, MediaType.TEXT, getEncoder().getValidMediaInputType());
			textWriter = (textEncoder == null) ? this : new DocumentMediaWriter<>(document, textEncoder, this);
		}
		return this;
	}

	@Override
	public DocumentMediaWriter<D> append(char c) throws IOException {
		super.append(c);
		return this;
	}

	@Override
	public DocumentMediaWriter<D> append(CharSequence csq) throws IOException {
		super.append(csq);
		return this;
	}

	@Override
	public DocumentMediaWriter<D> append(CharSequence csq, int start, int end) throws IOException {
		super.append(csq, start, end);
		return this;
	}

	// Not delegating to AnyDocument.nl(), because the newlines themselves may need to be encoded.
	@Override
	public DocumentMediaWriter<D> nl() throws IOException {
		super.nl();
		return this;
	}

	// Not delegating to AnyDocument.nli(), because the newlines and tabs themselves may need to be encoded.
	@Override
	public DocumentMediaWriter<D> nli() throws IOException {
		super.nli();
		return this;
	}

	// Not delegating to AnyDocument.nli(int), because the newlines and tabs themselves may need to be encoded.
	@Override
	public DocumentMediaWriter<D> nli(int depthOffset) throws IOException {
		super.nli(depthOffset);
		return this;
	}

	// Not delegating to AnyDocument.indent(), because the tabs themselves may need to be encoded.
	@Override
	public DocumentMediaWriter<D> indent() throws IOException {
		super.indent();
		return this;
	}

	// Not delegating to AnyDocument.indent(int), because the tabs themselves may need to be encoded.
	@Override
	public DocumentMediaWriter<D> indent(int depthOffset) throws IOException {
		super.indent(depthOffset);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link AnyDocument#getIndent()}.
	 * </p>
	 */
	@Override
	public boolean getIndent() {
		return document.getIndent();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link AnyDocument#setIndent(boolean)}.
	 * </p>
	 */
	@Override
	public DocumentMediaWriter<D> setIndent(boolean indent) {
		document.setIndent(indent);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link AnyDocument#getDepth()}.
	 * </p>
	 */
	@Override
	public int getDepth() {
		return document.getDepth();
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link AnyDocument#setDepth(int)}.
	 * </p>
	 */
	@Override
	public DocumentMediaWriter<D> setDepth(int depth) {
		document.setDepth(depth);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link AnyDocument#incDepth()}.
	 * </p>
	 */
	@Override
	public DocumentMediaWriter<D> incDepth() {
		document.incDepth();
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * Delegates to {@link AnyDocument#decDepth()}.
	 * </p>
	 */
	@Override
	public DocumentMediaWriter<D> decDepth() {
		document.decDepth();
		return this;
	}

	// Not delegating to AnyDocument.sp(), because the spaces themselves may need to be encoded.
	@Override
	public DocumentMediaWriter<D> sp() throws IOException {
		super.sp();
		return this;
	}

	// Not delegating to AnyDocument.sp(int), because the spaces themselves may need to be encoded.
	@Override
	public DocumentMediaWriter<D> sp(int count) throws IOException {
		super.sp(count);
		return this;
	}

	@Override
	public DocumentMediaWriter<D> nbsp() throws IOException {
		super.nbsp();
		return this;
	}

	@Override
	public DocumentMediaWriter<D> nbsp(int count) throws IOException {
		super.nbsp(count);
		return this;
	}

	@Override
	public DocumentMediaWriter<D> text(char ch) throws IOException {
		super.text(ch);
		return this;
	}

	// TODO: codePoint?

	@Override
	public DocumentMediaWriter<D> text(char[] cbuf) throws IOException {
		super.text(cbuf);
		return this;
	}

	@Override
	public DocumentMediaWriter<D> text(char[] cbuf, int offset, int len) throws IOException {
		super.text(cbuf, offset, len);
		return this;
	}

	@Override
	public DocumentMediaWriter<D> text(CharSequence csq) throws IOException {
		super.text(csq);
		return this;
	}

	@Override
	public DocumentMediaWriter<D> text(CharSequence csq, int start, int end) throws IOException {
		super.text(csq, start, end);
		return this;
	}

	@Override
	public DocumentMediaWriter<D> text(Object text) throws IOException {
		super.text(text);
		return this;
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	public <Ex extends Throwable> DocumentMediaWriter<D> text(IOSupplierE<?, Ex> text) throws IOException, Ex {
		super.text(text);
		return this;
	}

	/**
	 * @param  <Ex>  An arbitrary exception type that may be thrown
	 */
	@Override
	public <Ex extends Throwable> DocumentMediaWriter<D> text(MediaWritable<Ex> text) throws IOException, Ex {
		try (DocumentMediaWriter<D> tw = text()) {
			if(text != null) {
				text.writeTo(tw);
			}
		}
		return this;
	}

	@Override
	public DocumentMediaWriter<D> text() throws IOException {
		DocumentMediaWriter<D> tw = getTextWriter();
		if(tw != this) textWriter.getEncoder().writePrefixTo(this);
		return new DocumentMediaWriter<D>(
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
