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
import com.aoindustries.encoding.MediaWriter;
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
	// Content models:
	//
	// Inherited from FlowContent and MetadataContent: Content
	MetadataContent<Document>,
	FlowContent<Document>,
	// Inherited from FlowContent: SectioningContent<Document>
	// Inherited from FlowContent: HeadingContent<Document>
	// Inherited from FlowContent: PhrasingContent<Document>
	// Inherited from FlowContent: EmbeddedContent<Document>
	// Inherited from FlowContent: InteractiveContent<Document>
	// Inherited from FlowContent: PalpableContent<Document>
	//
	// Content types:
	//
	Contents.Document.Html<Document>,
	Contents.Metadata.Head<Document>,
	// Inherited from MetadataContent: Contents.Metadata.Title<Document>
	// Inherited from MetadataContent: Contents.Metadata.Base<Document>
	// Inherited from FlowContent and MetadataContent: Contents.Metadata.Link<Document>
	// Inherited from FlowContent and MetadataContent: Contents.Metadata.Meta<Document>
	// Inherited from MetadataContent: Contents.Metadata.Style<Document>
	Contents.Sections.Body<Document>,
	// Inherited from FlowContent: Contents.Sections.Article<Document>
	// Inherited from FlowContent: Contents.Sections.Section<Document>
	// Inherited from FlowContent: Contents.Sections.Nav<Document>
	// Inherited from FlowContent: Contents.Sections.Aside<Document>
	// Inherited from FlowContent: Contents.Sections.H1<Document>
	// Inherited from FlowContent: Contents.Sections.H2<Document>
	// Inherited from FlowContent: Contents.Sections.H3<Document>
	// Inherited from FlowContent: Contents.Sections.H4<Document>
	// Inherited from FlowContent: Contents.Sections.H5<Document>
	// Inherited from FlowContent: Contents.Sections.H6<Document>
	// Inherited from FlowContent: Contents.Sections.Hgroup<Document>
	// Inherited from FlowContent: Contents.Sections.Header<Document>
	// Inherited from FlowContent: Contents.Sections.Footer<Document>
	// Inherited from FlowContent: Contents.Sections.Address<Document>
	// Inherited from FlowContent: Contents.Grouping.P<Document>
	// Inherited from FlowContent: Contents.Grouping.Hr<Document>
	// Inherited from FlowContent: Contents.Grouping.Pre<Document>
	// Inherited from FlowContent: Contents.Grouping.Blockquote<Document>
	// Inherited from FlowContent: Contents.Grouping.Ol<Document>
	// Inherited from FlowContent: Contents.Grouping.Ul<Document>
	// Inherited from FlowContent: Contents.Grouping.Menu<Document>
	Contents.Grouping.Li<Document>,
	// Inherited from FlowContent: Contents.Grouping.Dl<Document>
	Contents.Grouping.Dt<Document>,
	Contents.Grouping.Dd<Document>,
	// Inherited from FlowContent: Contents.Grouping.Figure<Document>
	Contents.Grouping.Figcaption<Document>,
	// Inherited from FlowContent: Contents.Grouping.Main<Document>
	// Inherited from FlowContent: Contents.Grouping.Div<Document>
	// Inherited from FlowContent: Contents.Text.A<Document>
	// Inherited from FlowContent: Contents.Text.Em<Document>
	// Inherited from FlowContent: Contents.Text.Strong<Document>
	// Inherited from FlowContent: Contents.Text.Small<Document>
	// Inherited from FlowContent: Contents.Text.S<Document>
	// Inherited from FlowContent: Contents.Text.Cite<Document>
	// Inherited from FlowContent: Contents.Text.Q<Document>
	// Inherited from FlowContent: Contents.Text.Dfn<Document>
	// Inherited from FlowContent: Contents.Text.Abbr<Document>
	// Inherited from FlowContent: Contents.Text.Ruby<Document>
	Contents.Text.Rt<Document>,
	Contents.Text.Rp<Document>,
	// Inherited from FlowContent: Contents.Text.Data<Document>
	// Inherited from FlowContent: Contents.Text.Time<Document>
	// Inherited from FlowContent: Contents.Text.Code<Document>
	// Inherited from FlowContent: Contents.Text.Var<Document>
	// Inherited from FlowContent: Contents.Text.Samp<Document>
	// Inherited from FlowContent: Contents.Text.Kbd<Document>
	// Inherited from FlowContent: Contents.Text.Sub<Document>
	// Inherited from FlowContent: Contents.Text.Sup<Document>
	// Inherited from FlowContent: Contents.Text.I<Document>
	// Inherited from FlowContent: Contents.Text.B<Document>
	// Inherited from FlowContent: Contents.Text.U<Document>
	// Inherited from FlowContent: Contents.Text.Mark<Document>
	// Inherited from FlowContent: Contents.Text.Bdi<Document>
	// Inherited from FlowContent: Contents.Text.Bdo<Document>
	// Inherited from FlowContent: Contents.Text.Span<Document>
	// Inherited from FlowContent: Contents.Text.Br<Document>
	// Inherited from FlowContent: Contents.Text.Wbr<Document>
	// Inherited from FlowContent: Contents.Edits.Ins<Document>
	// Inherited from FlowContent: Contents.Edits.Del<Document>
	// Inherited from FlowContent: Contents.Embedded.Picture<Document>
	Contents.Embedded.Source<Document>,
	// Inherited from FlowContent: Contents.Embedded.Img<Document>
	// Inherited from FlowContent: Contents.Embedded.Iframe<Document>
	// Inherited from FlowContent: Contents.Embedded.Embed<Document>
	// Inherited from FlowContent: Contents.Embedded.Object<Document>
	Contents.Embedded.Param<Document>,
	// Inherited from FlowContent: Contents.Embedded.Video<Document>
	// Inherited from FlowContent: Contents.Embedded.Audio<Document>
	Contents.Embedded.Track<Document>,
	// Inherited from FlowContent: Contents.Embedded.Map<Document>
	// Inherited from FlowContent: Contents.Embedded.Area<Document>
	// Inherited from FlowContent: // TODO: MathML math
	// Inherited from FlowContent: // TODO: SVG svg
	// Inherited from FlowContent: Contents.Tabular.Table<Document>
	Contents.Tabular.Caption<Document>,
	Contents.Tabular.Colgroup<Document>,
	Contents.Tabular.Col<Document>,
	Contents.Tabular.Tbody<Document>,
	Contents.Tabular.Thead<Document>,
	Contents.Tabular.Tfoot<Document>,
	Contents.Tabular.Tr<Document>,
	Contents.Tabular.Td<Document>,
	Contents.Tabular.Th<Document>,
	// Inherited from FlowContent: Contents.Forms.Form<Document>
	// Inherited from FlowContent: Contents.Forms.Label<Document>
	// Inherited from FlowContent: Contents.Forms.Input<Document>
	// Inherited from FlowContent: Contents.Forms.Button<Document>
	// Inherited from FlowContent: Contents.Forms.Select<Document>
	// Inherited from FlowContent: Contents.Forms.Datalist<Document>
	Contents.Forms.Optgroup<Document>,
	Contents.Forms.Option<Document>,
	// Inherited from FlowContent: Contents.Forms.Textarea<Document>
	// Inherited from FlowContent: Contents.Forms.Output<Document>
	// Inherited from FlowContent: Contents.Forms.Progress<Document>
	// Inherited from FlowContent: Contents.Forms.Meter<Document>
	// Inherited from FlowContent: Contents.Forms.Fieldset<Document>
	Contents.Forms.Legend<Document>,
	// Inherited from FlowContent: Contents.Interactive.Details<Document>
	Contents.Interactive.Summary<Document>
	// Inherited from FlowContent: Contents.Interactive.Dialog<Document>
	// Inherited from FlowContent and MetadataContent: Contents.Scripting.Script<Document>
	// Inherited from FlowContent and MetadataContent: Contents.Scripting.Noscript<Document>
	// Inherited from FlowContent and MetadataContent: Contents.Scripting.Template<Document>
	// Inherited from FlowContent: Contents.Scripting.Slot<Document>
	// Inherited from FlowContent: Contents.Scripting.Canvas<Document>
	// Inherited from FlowContent: // TODO: autonomous custom elements
	// Inherited from FlowContent: TextContent<Document>
	// TODO: Whitespace?
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
	// TODO:     If wrapping, consider uses of this losing access to this wrapping, such as NoCloseWriter

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
			try (MediaWriter _out = text()) {
				text.writeTo(_out);
			}
		}
		return this;
	}

	@Override
	public MediaWriter text() throws IOException {
		return new MediaWriter(
			encodingContext,
			textInXhtmlEncoder,
			new NoCloseWriter(out)
		);
	}

	@Override
	public Document nl() throws IOException {
		out.write('\n');
		return this;
	}

	// TODO: A set of per-type methods, like xml(), script(), style(), ...

	// TODO: A set of out()/unsafe() methods that take MediaType and value

	// TODO: comments
}
