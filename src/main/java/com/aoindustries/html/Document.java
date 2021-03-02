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
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Throwables;
import com.aoindustries.util.i18n.MarkupCoercion;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

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
	MetadataContent<Document>,
	FlowContent<Document>,
	// Inherited: SectioningContent<Document>
	// Inherited: HeadingContent<Document>
	// Inherited: PhrasingContent<Document>
	// Inherited: EmbeddedContent<Document>
	// Inherited: InteractiveContent<Document>
	// Inherited: PalpableContent<Document>
	// Inherited: ScriptSupportingContent<Document>
	// Inherited: TextContent<Document>
	//
	// Per-element content models:
	//
	COLGROUP_content<Document>,
	TABLE_content<Document>,
	TR_content<Document>,
	//
	// Factories:
	//
	HTML_factory,
	HEAD_factory<Document>,
	// Inherited: TITLE_factory<Document>
	// Inherited: BASE_factory<Document>
	// Inherited: LINK_factory<Document>
	// Inherited: META_factory<Document>
	// Inherited: STYLE_factory<Document>
	BODY_factory<Document>,
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
	LI_factory<Document>,
	// Inherited: DL_factory<Document>
	DT_factory<Document>,
	DD_factory<Document>,
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
	OPTGROUP_factory<Document>,
	OPTION_factory<Document>,
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
	// Inherited: // TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
	//
	// Others:
	//
	// Inherited: WhitespaceWriter<Document>
{

	/**
	 * The default, and recommended, encoding for (X)HTML.
	 */
	public static final Charset ENCODING = StandardCharsets.UTF_8;

	public final EncodingContext encodingContext;
	// TODO: Remove this and just use encodingContext?
	public final Serialization serialization;
	// TODO: Remove this and just use encodingContext?
	public final Doctype doctype;

	/**
	 * Writer for raw output.
	 * <p>
	 * TODO: This field will possibly become "protected" (or deprecated to minimize direct usage) once the full set of HTML tags have been implemented.
	 *       Or, access to it will be provided either directly or through methods named "unsafe".
	 * </p>
	 */
	// TODO: Wrap this writer in XhtmlValidator if is not already validating XHTML?
	//       If wrapping, consider uses of this losing access to this wrapping, such as NoCloseWriter
	// TODO: Make this be a ChainWriter?  This might be incorrect as it would encourage using html.out instead of elements and attributes
	public final Writer out;

	public Document(EncodingContext encodingContext, Serialization serialization, Doctype doctype, Writer out) {
		this.encodingContext = encodingContext;
		this.serialization = serialization;
		this.doctype = doctype;
		this.out = out;
	}

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

	public Document(EncodingContext encodingContext, Writer out) {
		this(
			encodingContext,
			encodingContext.getSerialization(),
			encodingContext.getDoctype(),
			out
		);
	}

	/**
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
	 * @see Doctype#xmlDeclaration(com.aoindustries.encoding.Serialization, java.lang.String, java.lang.Appendable)
	 */
	// TODO: Define here only since depends on both serialization and doctype
	public Document xmlDeclaration(String documentEncoding) throws IOException {
		doctype.xmlDeclaration(serialization, documentEncoding, out);
		return this;
	}

	/**
	 * @see Doctype#doctype(com.aoindustries.encoding.Serialization, java.lang.Appendable)
	 */
	// TODO: Define here only since depends on both serialization and doctype
	public Document doctype() throws IOException {
		doctype.doctype(serialization, out);
		return this;
	}

	/**
	 * @see Serialization#selfClose(java.lang.Appendable)
	 *
	 * @deprecated  Please use specific tag implementations
	 */
	// TODO: Remove this method once no longer used
	@Deprecated
	public Document selfClose() throws IOException {
		serialization.selfClose(out);
		return this;
	}

	@Override
	public Document getDocument() {
		return this;
	}

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

	// Matches MediaWriter.START_NLI_LENGTH
	private static final int START_NLI_LENGTH = 8;

	/**
	 * Newline combined with any number of {@link #INDENT} characters.
	 * Doubled in size as-needed.
	 */
	// Matches MediaWriter.nliChars
	private String nliChars = new String(new char[] {
		NL,     INDENT, INDENT, INDENT,
		INDENT, INDENT, INDENT, INDENT
	});
	{
		assert nliChars.length() == START_NLI_LENGTH : "Starts at length " + START_NLI_LENGTH;
	}

	/**
	 * Any number of {@link #INDENT} characters.
	 * Doubled in size as-needed.
	 */
	// Matches MediaWriter.indentChars
	private String indentChars = new String(new char[] {
		INDENT, INDENT, INDENT, INDENT,
		INDENT, INDENT, INDENT, INDENT
	});
	{
		assert nliChars.length() == START_NLI_LENGTH : "Starts at length " + START_NLI_LENGTH;
	}

	// Matches MediaWriter.nl()
	@Override
	public Document nl() throws IOException {
		out.write(NL);
		return this;
	}

	// Matches MediaWriter.nli()
	@Override
	public Document nli() throws IOException {
		return nli(0);
	}

	// Matches MediaWriter.nli(int)
	@Override
	public Document nli(int depthOffset) throws IOException {
		if(getIndent()) {
			int d = getDepth();
			assert d >= 0;
			d += depthOffset
				// Make room for the beginning newline
				+ 1;
			if(d > 1) {
				String nli = nliChars;
				int nliLen = nli.length();
				// Expand in size as-needed
				if(d > nliLen) {
					do {
						int bigger = nliLen << 1;
						if(bigger < nliLen) throw new ArithmeticException("integer overflow");
						nliLen = bigger;
					} while(d > nliLen);
					char[] newChars = new char[nliLen];
					newChars[0] = NL;
					Arrays.fill(newChars, 1, nliLen, INDENT);
					nliChars = nli = new String(newChars);
				}
				out.write(nli, 0, d);
			} else {
				out.write(NL);
			}
		} else {
			out.write(NL);
		}
		return this;
	}

	// Matches MediaWriter.indent()
	@Override
	public Document indent() throws IOException {
		return indent(0);
	}

	// Matches MediaWriter.indent(int)
	@Override
	public Document indent(int depthOffset) throws IOException {
		if(getIndent()) {
			int d = getDepth();
			assert d >= 0;
			d += depthOffset;
			if(d > 1) {
				String i = indentChars;
				int iLen = i.length();
				// Expand in size as-needed
				if(d > iLen) {
					do {
						int bigger = iLen << 1;
						if(bigger < iLen) throw new ArithmeticException("integer overflow");
						iLen = bigger;
					} while(d > iLen);
					char[] newChars = new char[iLen];
					Arrays.fill(newChars, 0, iLen, INDENT);
					indentChars = i = new String(newChars);
				}
				out.write(i, 0, d);
			} else if(d == 1) {
				out.write(INDENT);
			}
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

	@Override
	public Document text(char ch) throws IOException {
		encodeTextInXhtml(ch, out);
		return this;
	}

	// TODO: codePoint?

	@Override
	public Document text(char[] cbuf) throws IOException {
		encodeTextInXhtml(cbuf, out);
		return this;
	}

	@Override
	public Document text(char[] cbuf, int offset, int len) throws IOException {
		encodeTextInXhtml(cbuf, offset, len, out);
		return this;
	}

	// TODO: text(CharSequence)?
	// TODO: text(CharSequence, int, int)?

	@Override
	@SuppressWarnings("UseSpecificCatch")
	public Document text(Object text) throws IOException {
		while(text instanceof IOSupplierE<?, ?>) {
			try {
				text = ((IOSupplierE<?, ?>)text).get();
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		if(text instanceof char[]) {
			return text((char[])text);
		}
		if(text instanceof MediaWritable) {
			try {
				return text((MediaWritable<?>)text);
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		// Allow text markup from translations
		MarkupCoercion.write(text, MarkupType.XHTML, false, textInXhtmlEncoder, false, out);
		return this;
	}

	@Override
	public <Ex extends Throwable> Document text(IOSupplierE<?, Ex> text) throws IOException, Ex {
		return text((text == null) ? null : text.get());
	}

	@Override
	public <Ex extends Throwable> Document text(MediaWritable<Ex> text) throws IOException, Ex {
		if(text != null) {
			try (DocumentMediaWriter _out = text()) {
				text.writeTo(_out);
			}
		}
		return this;
	}

	@Override
	public DocumentMediaWriter text() throws IOException {
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
