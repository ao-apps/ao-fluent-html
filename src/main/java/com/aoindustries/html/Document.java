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

import com.aoindustries.encoding.ChainWriter;
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
 * @author  AO Industries, Inc.
 */
public class Document implements
	//
	// Unions:
	//
	// Inherited: Union_COLGROUP_ScriptSupporting<Document>
	// Inherited: Union_DATALIST_OPTGROUP<Document>
	// Inherited: Union_DIV_DL<Document>
	// Inherited: Union_DL_Palpable<Document>
	// Inherited: Union_Embedded_Interactive<Document>
	// Inherited: Union_Embedded_Palpable_Phrasing<Document>
	// Inherited: Union_Interactive_Phrasing<Document>
	// Inherited: Union_Metadata_Phrasing<Document>
	// Inherited: Union_Palpable_Phrasing<Document>
	// Inherited: Union_TBODY_THEAD_TFOOT<Document>

	//
	// Content models:
	//
	// Inherited: Content<Document>
	// Inherited: EmbeddedContent<Document>
	// Inherited: FlowContent<Document>
	// Inherited: HeadingContent<Document>
	// Inherited: InteractiveContent<Document>
	ListContent<Document>,
	MetadataContent<Document>,
	// Inherited: PalpableContent<Document>
	// Inherited: PhrasingContent<Document>
	// Inherited: ScriptSupportingContent<Document>
	// Inherited: SectioningContent<Document>
	// Inherited: TextContent<Document>

	//
	// Per-element content models:
	//
	COLGROUP_content<Document>,
	DATALIST_content<Document>,
	DIV_content<Document>,
	DL_content<Document>,
	HTML_content<Document>,
	// Inherited: OPTGROUP_content<Document>
	SELECT_content<Document>,
	TABLE_content<Document>,
	TR_content<Document>,

	//
	// Factories:
	//
	HTML_factory<Document>,
	// Inherited: HEAD_factory<Document>
	// Inherited: TITLE_factory<Document>
	// Inherited: BASE_factory<Document>
	// Inherited: LINK_factory<Document>
	// Inherited: META_factory<Document>
	// Inherited: STYLE_factory<Document>
	// Inherited: BODY_factory<Document>
	// Inherited: ARTICLE_factory<Document>
	// Inherited: SECTION_factory<Document>
	// Inherited: NAV_factory<Document>
	// Inherited: ASIDE_factory<Document>
	// Inherited: H1_factory<Document>
	// Inherited: H2_factory<Document>
	// Inherited: H3_factory<Document>
	// Inherited: H4_factory<Document>
	// Inherited: H5_factory<Document>
	// Inherited: H6_factory<Document>
	// Inherited: HGROUP_factory<Document>
	// Inherited: HEADER_factory<Document>
	// Inherited: FOOTER_factory<Document>
	// Inherited: ADDRESS_factory<Document>
	// Inherited: P_factory<Document>
	// Inherited: HR_factory<Document>
	// Inherited: PRE_factory<Document>
	// Inherited: BLOCKQUOTE_factory<Document>
	// Inherited: OL_factory<Document>
	// Inherited: UL_factory<Document>
	// Inherited: MENU_factory<Document>
	// Inherited: LI_factory<Document>
	// Inherited: DL_factory<Document>
	// Inherited: DT_factory<Document>
	// Inherited: DD_factory<Document>
	// Inherited: FIGURE_factory<Document>
	FIGCAPTION_factory<Document>,
	// Inherited: MAIN_factory<Document>
	// Inherited: DIV_factory<Document>
	// Inherited: A_factory<Document>
	// Inherited: EM_factory<Document>
	// Inherited: STRONG_factory<Document>
	// Inherited: SMALL_factory<Document>
	// Inherited: S_factory<Document>
	// Inherited: CITE_factory<Document>
	// Inherited: Q_factory<Document>
	// Inherited: DFN_factory<Document>
	// Inherited: ABBR_factory<Document>
	// Inherited: RUBY_factory<Document>
	RT_factory<Document>,
	RP_factory<Document>,
	// Inherited: DATA_factory<Document>
	// Inherited: TIME_factory<Document>
	// Inherited: CODE_factory<Document>
	// Inherited: VAR_factory<Document>
	// Inherited: SAMP_factory<Document>
	// Inherited: KBD_factory<Document>
	// Inherited: SUB_factory<Document>
	// Inherited: SUP_factory<Document>
	// Inherited: I_factory<Document>
	// Inherited: B_factory<Document>
	// Inherited: U_factory<Document>
	// Inherited: MARK_factory<Document>
	// Inherited: BDI_factory<Document>
	// Inherited: BDO_factory<Document>
	// Inherited: SPAN_factory<Document>
	// Inherited: BR_factory<Document>
	// Inherited: WBR_factory<Document>
	// Inherited: INS_factory<Document>
	// Inherited: DEL_factory<Document>
	// Inherited: PICTURE_factory<Document>
	SOURCE_factory<Document>,
	// Inherited: IMG_factory<Document>
	// Inherited: IFRAME_factory<Document>
	// Inherited: EMBED_factory<Document>
	// Inherited: OBJECT_factory<Document>
	PARAM_factory<Document>,
	// Inherited: VIDEO_factory<Document>
	// Inherited: AUDIO_factory<Document>
	TRACK_factory<Document>,
	// Inherited: MAP_factory<Document>
	// Inherited: AREA_factory<Document>
	// Inherited: // TODO: MathML math
	// Inherited: // TODO: SVG svg
	// Inherited: TABLE_factory<Document>
	// Inherited: CAPTION_factory<Document>
	// Inherited: COLGROUP_factory<Document>
	// Inherited: COL_factory<Document>
	// Inherited: TBODY_factory<Document>
	// Inherited: THEAD_factory<Document>
	// Inherited: TFOOT_factory<Document>
	// Inherited: TR_factory<Document>
	// Inherited: TD_factory<Document>
	// Inherited: TH_factory<Document>
	// Inherited: FORM_factory<Document>
	// Inherited: LABEL_factory<Document>
	// Inherited: INPUT_factory<Document>
	// Inherited: BUTTON_factory<Document>
	// Inherited: SELECT_factory<Document>
	// Inherited: DATALIST_factory<Document>
	// Inherited: OPTGROUP_factory<Document>
	// Inherited: OPTION_factory<Document>
	// Inherited: TEXTAREA_factory<Document>
	// Inherited: OUTPUT_factory<Document>
	// Inherited: PROGRESS_factory<Document>
	// Inherited: METER_factory<Document>
	// Inherited: FIELDSET_factory<Document>
	LEGEND_factory<Document>,
	// Inherited: DETAILS_factory<Document>
	SUMMARY_factory<Document>
	// Inherited: DIALOG_factory<Document>
	// Inherited: SCRIPT_factory<Document>
	// Inherited: NOSCRIPT_factory<Document>
	// Inherited: TEMPLATE_factory<Document>
	// Inherited: SLOT_factory<Document>
	// Inherited: CANVAS_factory<Document>
	// Inherited: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/multipage/custom-elements.html#custom-elements
	//
	// Others:
	//
	// Inherited: WhitespaceWriter<Document>
{

	/**
	 * The default, and recommended, encoding for (X)HTML.
	 */
	public static final Charset ENCODING = StandardCharsets.UTF_8;

	static final com.aoindustries.i18n.Resources RESOURCES = com.aoindustries.i18n.Resources.getResources(Document.class);

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
	public Document(EncodingContext encodingContext, Serialization serialization, Doctype doctype, Writer out) {
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
	public Document(Serialization serialization, Doctype doctype, Writer out) {
		this(
			new EncodingContext() {
				@Override
				public Serialization getSerialization() {
					return serialization;
				}
				@Override
				public Doctype getDoctype() {
					return doctype;
				}
			},
			serialization,
			doctype,
			out
		);
	}

	/**
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #setOut(java.io.Writer)
	 */
	public Document(EncodingContext encodingContext, Writer out) {
		this(
			encodingContext,
			encodingContext.getSerialization(),
			encodingContext.getDoctype(),
			out
		);
	}

	/**
	 * @param  out  May be {@code null}, but must be set to a non-null value again before any additional writes.
	 *              Not doing so may result in {@link IllegalStateException}.
	 *
	 * @see  #setOut(java.io.Writer)
	 * @see  EncodingContext#DEFAULT
	 */
	public Document(Writer out) {
		this(EncodingContext.DEFAULT, out);
	}

	/**
	 * Unwraps the given chain writer.
	 */
	public Document(ChainWriter out) {
		this(out.getEncodingContext(), out.getPrintWriter());
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
	public Document unsafe(char ch) throws IOException {
		return unsafe(getUnsafe(null), ch);
	}

	Document unsafe(Writer out, char ch) throws IOException {
		out.append(ch);
		return setAtnl(ch == NL);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document unsafe(char[] cbuf) throws IOException {
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
		return this;
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
	Document unsafe(Writer out, char[] cbuf, boolean endsNewline) throws IOException {
		assert cbuf.length > 0;
		out.write(cbuf);
		assert endsNewline == (cbuf[cbuf.length - 1] == NL);
		return setAtnl(endsNewline);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document unsafe(char[] cbuf, int offset, int len) throws IOException {
		if(cbuf != null && len > 0) {
			unsafe(
				getUnsafe(null),
				cbuf,
				offset,
				len,
				cbuf[offset + len - 1] == NL
			);
		}
		return this;
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
	Document unsafe(Writer out, char[] cbuf, int offset, int len, boolean endsNewline) throws IOException {
		assert len > 0;
		out.write(cbuf, offset, len);
		assert endsNewline == (cbuf[offset + len - 1] == NL);
		return setAtnl(endsNewline);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document unsafe(CharSequence csq) throws IOException {
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
		return this;
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
	Document unsafe(Writer out, CharSequence csq, boolean endsNewline) throws IOException {
		assert csq.length() > 0;
		out.append(csq);
		assert endsNewline == (csq.charAt(csq.length() - 1) == NL);
		return setAtnl(endsNewline);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document unsafe(CharSequence csq, int start, int end) throws IOException {
		if(csq != null && end > start) {
			unsafe(
				getUnsafe(null),
				csq,
				start,
				end,
				csq.charAt(end - 1) == NL
			);
		}
		return this;
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
	Document unsafe(Writer out, CharSequence csq, int start, int end, boolean endsNewline) throws IOException {
		assert end > start;
		out.append(csq, start, end);
		assert endsNewline == (csq.charAt(end - 1) == NL);
		return setAtnl(endsNewline);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document unsafe(Object unsafe) throws IOException {
		return unsafe(getUnsafe(null), unsafe);
	}

	@SuppressWarnings("UseSpecificCatch")
	Document unsafe(Writer out, Object unsafe) throws IOException {
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
					return this;
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
					return this;
				}
			}
			if(unsafe instanceof Writable) {
				return unsafe(out, (Writable)unsafe);
			}
			// Allow no markup from translations
			Coercion.write(unsafe, out);
			clearAtnl(); // Unknown, safe to assume not at newline
		}
		return this;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public <Ex extends Throwable> Document unsafe(IOSupplierE<?, Ex> unsafe) throws IOException, Ex {
		return unsafe(getUnsafe(null), unsafe);
	}

	<Ex extends Throwable> Document unsafe(Writer out, IOSupplierE<?, Ex> unsafe) throws IOException, Ex {
		return unsafe(out, (unsafe == null) ? null : unsafe.get());
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document unsafe(Writable unsafe) throws IOException {
		return unsafe(getUnsafe(null), unsafe);
	}

	Document unsafe(Writer out, Writable unsafe) throws IOException {
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
		return this;
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
	public Document xmlDeclaration(String documentEncoding) throws IOException {
		if(doctype.xmlDeclaration(serialization, documentEncoding, getUnsafe(null))) {
			setAtnl();
		}
		return this;
	}

	/**
	 * @see Doctype#doctype(com.aoindustries.encoding.Serialization, java.lang.Appendable)
	 */
	public Document doctype() throws IOException {
		if(doctype.doctype(serialization, getUnsafe(null))) {
			setAtnl();
		}
		return this;
	}

	@Override
	public Document getDocument() {
		return this;
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
	public Document setAutonli(boolean autonli) {
		this.autonli = autonli;
		return this;
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
	public Document setAtnl() {
		this.atnl = true;
		return this;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document setAtnl(boolean atnl) {
		this.atnl = atnl;
		return this;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document clearAtnl() {
		this.atnl = false;
		return this;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document autoNl() throws IOException {
		return autoNl(getUnsafe(null));
	}

	Document autoNl(Writer out) throws IOException {
		if(getAutonli() && !getAtnl()) {
			out.append(NL);
			setAtnl();
		}
		return this;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document autoNli() throws IOException {
		return autoNli(getUnsafe(null), 0);
	}

	Document autoNli(Writer out) throws IOException {
		return autoNli(out, 0);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document autoNli(int depthOffset) throws IOException {
		return autoNli(getUnsafe(null), depthOffset);
	}

	Document autoNli(Writer out, int depthOffset) throws IOException {
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
		return this;
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document autoIndent() throws IOException {
		return autoIndent(getUnsafe(null), 0);
	}

	Document autoIndent(Writer out) throws IOException {
		return autoIndent(out, 0);
	}

	/**
	 * @return  {@code this} document
	 */
	@Override
	public Document autoIndent(int depthOffset) throws IOException {
		return autoIndent(getUnsafe(null), depthOffset);
	}

	Document autoIndent(Writer out, int depthOffset) throws IOException {
		int d;
		if(getAutonli() && getIndent() && getAtnl() && (d = getDepth() + depthOffset) > 0) {
			WriterUtil.indent(out, d);
			clearAtnl();
		}
		return this;
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
	public Document nl() throws IOException {
		return nl(getUnsafe(null));
	}

	Document nl(Writer out) throws IOException {
		out.append(NL);
		setAtnl();
		return this;
	}

	// Matches MediaWriter.nli()
	@Override
	public Document nli() throws IOException {
		return nli(getUnsafe(null), 0);
	}

	Document nli(Writer out) throws IOException {
		return nli(out, 0);
	}

	// Matches MediaWriter.nli(int)
	@Override
	public Document nli(int depthOffset) throws IOException {
		return nli(getUnsafe(null), depthOffset);
	}

	Document nli(Writer out, int depthOffset) throws IOException {
		int d;
		if(getIndent() && (d = getDepth() + depthOffset) > 0) {
			WriterUtil.nli(out, d);
			clearAtnl();
		} else {
			out.append(NL);
			setAtnl();
		}
		return this;
	}

	// Matches MediaWriter.indent()
	@Override
	public Document indent() throws IOException {
		return indent(getUnsafe(null), 0);
	}

	Document indent(Writer out) throws IOException {
		return indent(out, 0);
	}

	// Matches MediaWriter.indent(int)
	@Override
	public Document indent(int depthOffset) throws IOException {
		return indent(getUnsafe(null), depthOffset);
	}

	Document indent(Writer out, int depthOffset) throws IOException {
		int d;
		if(getIndent() && (d = getDepth() + depthOffset) > 0) {
			WriterUtil.indent(out, d);
			clearAtnl();
		}
		return this;
	}

	// Matches MediaWriter.getIndent()
	@Override
	public boolean getIndent() {
		return indent;
	}

	// Matches MediaWriter.setIndent(int)
	@Override
	public Document setIndent(boolean indent) {
		this.indent = indent;
		return this;
	}

	// Matches MediaWriter.getDepth()
	@Override
	public int getDepth() {
		return depth;
	}

	// Matches MediaWriter.setDepth(int)
	@Override
	public Document setDepth(int depth) {
		if(depth < 0) throw new IllegalArgumentException("depth < 0: " + depth);
		this.depth = depth;
		return this;
	}

	// Matches MediaWriter.incDepth()
	@Override
	public Document incDepth() {
		if(getIndent()) {
			int d = ++depth;
			if(d < 0) depth = Integer.MAX_VALUE;
		}
		assert depth >= 0;
		return this;
	}

	// Matches MediaWriter.decDepth()
	@Override
	public Document decDepth() {
		if(getIndent()) {
			int d = --depth;
			if(d < 0) depth = 0;
		}
		assert depth >= 0;
		return this;
	}

	// Matches MediaWriter.sp()
	@Override
	public Document sp() throws IOException {
		return sp(getUnsafe(null));
	}

	Document sp(Writer out) throws IOException {
		out.append(SPACE);
		clearAtnl();
		return this;
	}

	// Matches MediaWriter.sp(int)
	@Override
	public Document sp(int count) throws IOException {
		return sp(getUnsafe(null), count);
	}

	Document sp(Writer out, int count) throws IOException {
		if(count > 0) {
			WriterUtil.sp(out, count);
			clearAtnl();
		}
		return this;
	}

	@Override
	public Document nbsp() throws IOException {
		return nbsp(getUnsafe(null));
	}

	Document nbsp(Writer out) throws IOException {
		out.append(NBSP);
		clearAtnl();
		return this;
	}

	@Override
	public Document nbsp(int count) throws IOException {
		return nbsp(getUnsafe(null), count);
	}

	Document nbsp(Writer out, int count) throws IOException {
		if(count > 0) {
			WriterUtil.nbsp(out, count);
			clearAtnl();
		}
		return this;
	}

	@Override
	public Document text(char ch) throws IOException {
		return text(getUnsafe(null), ch);
	}

	Document text(Writer out, char ch) throws IOException {
		if(ch == NL) {
			out.write(NL);
			setAtnl();
		} else {
			autoIndent(out);
			encodeTextInXhtml(ch, out);
			clearAtnl();
		}
		return this;
	}

	// TODO: codePoint?

	@Override
	public Document text(char[] cbuf) throws IOException {
		return text(getUnsafe(null), cbuf);
	}

	Document text(Writer out, char[] cbuf) throws IOException {
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
		return this;
	}

	@Override
	public Document text(char[] cbuf, int offset, int len) throws IOException {
		return text(getUnsafe(null), cbuf, offset, len);
	}

	Document text(Writer out, char[] cbuf, int offset, int len) throws IOException {
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
		return this;
	}

	@Override
	public Document text(CharSequence csq) throws IOException {
		return text(getUnsafe(null), csq);
	}

	Document text(Writer out, CharSequence csq) throws IOException {
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
		return this;
	}

	@Override
	public Document text(CharSequence csq, int start, int end) throws IOException {
		return text(getUnsafe(null), csq, start, end);
	}

	Document text(Writer out, CharSequence csq, int start, int end) throws IOException {
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
		return this;
	}

	@Override
	public Document text(Object text) throws IOException {
		return text(getUnsafe(null), text);
	}

	@SuppressWarnings("UseSpecificCatch")
	Document text(Writer out, Object text) throws IOException {
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
		return this;
	}

	@Override
	public <Ex extends Throwable> Document text(IOSupplierE<?, Ex> text) throws IOException, Ex {
		return text(getUnsafe(null), text);
	}

	<Ex extends Throwable> Document text(Writer out, IOSupplierE<?, Ex> text) throws IOException, Ex {
		return text(out, (text == null) ? null : text.get());
	}

	@Override
	public <Ex extends Throwable> Document text(MediaWritable<Ex> text) throws IOException, Ex {
		return text(getUnsafe(null), text);
	}

	<Ex extends Throwable> Document text(Writer out, MediaWritable<Ex> text) throws IOException, Ex {
		if(text != null) {
			try (DocumentMediaWriter _out = text(out)) {
				text.writeTo(_out);
			}
		}
		return this;
	}

	@Override
	public DocumentMediaWriter text() throws IOException {
		return text(getUnsafe(null));
	}

	DocumentMediaWriter text(Writer out) throws IOException {
		autoIndent(out);
		clearAtnl(); // Unknown, safe to assume not at newline
		return new DocumentMediaWriter(
			this,
			textInXhtmlEncoder,
			new NoCloseWriter(out)
		);
	}

	// TODO: A set of per-type methods, like xml(), script(), style(), ...

	// TODO: A set of out()/unsafe() methods that take MediaType and value

	// TODO: comments
}
