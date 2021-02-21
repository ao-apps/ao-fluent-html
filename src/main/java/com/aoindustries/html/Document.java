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
import com.aoindustries.encoding.Supplier;
import static com.aoindustries.encoding.TextInXhtmlEncoder.encodeTextInXhtml;
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.lang.RunnableE;
import com.aoindustries.lang.Throwables;
import com.aoindustries.util.function.ConsumerE;
import com.aoindustries.util.i18n.MarkupCoercion;
import com.aoindustries.util.i18n.MarkupType;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Fluent Java DSL for high-performance HTML generation.
 * <p>
 * This class implements all content interfaces, and is the only class to implement them.
 * </p>
 * <p>
 * Because this is the only class to implement all <code>*Content</code> interfaces, it is safe to cast
 * between all <code>*Content</code> when needing to break out of the expected content model.
 * However, the recommended approach is to use {@link #getDocument()}, which supports all element types.
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
		while(text instanceof Supplier<?, ?>) {
			try {
				text = ((Supplier<?, ?>)text).get();
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
	public <Ex extends Throwable> Document text(Supplier<?, Ex> text) throws IOException, Ex {
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

	// TODO: A set of out() methods that take MediaType and value

	// TODO: comments

	protected A<Document> a;

	@Override
	public A<Document> a() throws IOException {
		if(a == null) a = new A<>(this);
		return a.open();
	}

	@Override
	public A<Document> a(String href) throws IOException {
		return a().href(href);
	}

	@Override
	public <Ex extends Throwable> A<Document> a(Supplier<? extends String, Ex> href) throws IOException, Ex {
		return a().href(href);
	}

	@Override
	public <Ex extends Throwable> Document a__(RunnableE<Ex> a) throws IOException, Ex {
		return a().__(a);
	}

	@Override
	public <Ex extends Throwable> Document a__(ConsumerE<? super Document, Ex> a) throws IOException, Ex {
		return a().__(a);
	}

	@Override
	public Document a__(Object text) throws IOException {
		return a().__(text);
	}

	@Override
	public Document a__() throws IOException {
		return a().__();
	}

	@Override
	public Area<Document> area() throws IOException {
		return new Area<Document>(this).open();
	}

	@Override
	public Area<Document> area(Rectangle rect) throws IOException {
		return area().shape(Area.Shape.RECT).coords(rect);
	}

	@Override
	public <Ex extends Throwable> Area<Document> area(Suppliers.Rectangle<Ex> rect) throws IOException, Ex {
		return area().shape(Area.Shape.RECT).coords(rect);
	}

	@Override
	public Area<Document> area(Circle circle) throws IOException {
		return area().shape(Area.Shape.CIRCLE).coords(circle);
	}

	@Override
	public <Ex extends Throwable> Area<Document> area(Suppliers.Circle<Ex> circle) throws IOException, Ex {
		return area().shape(Area.Shape.CIRCLE).coords(circle);
	}

	@Override
	public Area<Document> area(Polygon poly) throws IOException {
		return area().shape(Area.Shape.POLY).coords(poly);
	}

	@Override
	public <Ex extends Throwable> Area<Document> area(Suppliers.Polygon<Ex> poly) throws IOException, Ex {
		return area().shape(Area.Shape.POLY).coords(poly);
	}

	@Override
	public Area<Document> area(Shape shape) throws IOException {
		if(shape == null) return area();
		if(shape instanceof Rectangle) return area((Rectangle)shape);
		if(shape instanceof Circle) return area((Circle)shape);
		if(shape instanceof Polygon) return area((Polygon)shape);
		// Pass-through in a way that must result in an exception for the unknown type instead of duplicating long exception message here
		area().coords(shape);
		throw new AssertionError("IllegalArgumentException must have been thrown by coords for invalid Shape");
	}

	@Override
	public <Ex extends Throwable> Area<Document> area(Suppliers.Shape<Ex> shape) throws IOException, Ex {
		return area(shape == null ? null : shape.get());
	}

	protected B<Document> b;

	@Override
	public B<Document> b() throws IOException {
		if(b == null) b = new B<>(this);
		return b.open();
	}

	@Override
	public <Ex extends Throwable> Document b__(RunnableE<Ex> b) throws IOException, Ex {
		return b().__(b);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, BContent extends PhrasingContent<BContent>> Document b__(ConsumerE<? super BContent, Ex> b) throws IOException, Ex {
		return b().__((ConsumerE)b);
	}

	@Override
	public Document b__(Object text) throws IOException {
		return b().__(text);
	}

	@Override
	public Document b__() throws IOException {
		return b().__();
	}

	@Override
	public Base<Document> base() throws IOException {
		return new Base<Document>(this).open();
	}

	@Override
	public Document base__(String href) throws IOException {
		return base().href(href).__();
	}

	protected Br<Document> br;

	@Override
	public Br<Document> br() throws IOException {
		if(br == null) br = new Br<>(this);
		return br.open();
	}

	@Override
	public Document br__() throws IOException {
		return br().__();
	}

	@Override
	public Col<Document> col() throws IOException {
		return new Col<Document>(this).open();
	}

	protected H1<Document> h1;

	@Override
	public H1<Document> h1() throws IOException {
		if(h1 == null) h1 = new H1<>(this);
		return h1.open();
	}

	@Override
	public <Ex extends Throwable> Document h1__(RunnableE<Ex> h1) throws IOException, Ex {
		return h1().__(h1);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, H1Content extends PhrasingContent<H1Content>> Document h1__(ConsumerE<? super H1Content, Ex> h1) throws IOException, Ex {
		return h1().__((ConsumerE)h1);
	}

	@Override
	public Document h1__(Object text) throws IOException {
		return h1().__(text);
	}

	@Override
	public Document h1__() throws IOException {
		return h1().__();
	}

	protected H2<Document> h2;

	@Override
	public H2<Document> h2() throws IOException {
		if(h2 == null) h2 = new H2<>(this);
		return h2.open();
	}

	@Override
	public <Ex extends Throwable> Document h2__(RunnableE<Ex> h2) throws IOException, Ex {
		return h2().__(h2);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, H2Content extends PhrasingContent<H2Content>> Document h2__(ConsumerE<? super H2Content, Ex> h2) throws IOException, Ex {
		return h2().__((ConsumerE)h2);
	}

	@Override
	public Document h2__(Object text) throws IOException {
		return h2().__(text);
	}

	@Override
	public Document h2__() throws IOException {
		return h2().__();
	}

	protected H3<Document> h3;

	@Override
	public H3<Document> h3() throws IOException {
		if(h3 == null) h3 = new H3<>(this);
		return h3.open();
	}

	@Override
	public <Ex extends Throwable> Document h3__(RunnableE<Ex> h3) throws IOException, Ex {
		return h3().__(h3);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, H3Content extends PhrasingContent<H3Content>> Document h3__(ConsumerE<? super H3Content, Ex> h3) throws IOException, Ex {
		return h3().__((ConsumerE)h3);
	}

	@Override
	public Document h3__(Object text) throws IOException {
		return h3().__(text);
	}

	@Override
	public Document h3__() throws IOException {
		return h3().__();
	}

	protected H4<Document> h4;

	@Override
	public H4<Document> h4() throws IOException {
		if(h4 == null) h4 = new H4<>(this);
		return h4.open();
	}

	@Override
	public <Ex extends Throwable> Document h4__(RunnableE<Ex> h4) throws IOException, Ex {
		return h4().__(h4);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, H4Content extends PhrasingContent<H4Content>> Document h4__(ConsumerE<? super H4Content, Ex> h4) throws IOException, Ex {
		return h4().__((ConsumerE)h4);
	}

	@Override
	public Document h4__(Object text) throws IOException {
		return h4().__(text);
	}

	@Override
	public Document h4__() throws IOException {
		return h4().__();
	}

	protected H5<Document> h5;

	@Override
	public H5<Document> h5() throws IOException {
		if(h5 == null) h5 = new H5<>(this);
		return h5.open();
	}

	@Override
	public <Ex extends Throwable> Document h5__(RunnableE<Ex> h5) throws IOException, Ex {
		return h5().__(h5);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, H5Content extends PhrasingContent<H5Content>> Document h5__(ConsumerE<? super H5Content, Ex> h5) throws IOException, Ex {
		return h5().__((ConsumerE)h5);
	}

	@Override
	public Document h5__(Object text) throws IOException {
		return h5().__(text);
	}

	@Override
	public Document h5__() throws IOException {
		return h5().__();
	}

	protected H6<Document> h6;

	@Override
	public H6<Document> h6() throws IOException {
		if(h6 == null) h6 = new H6<>(this);
		return h6.open();
	}

	@Override
	public <Ex extends Throwable> Document h6__(RunnableE<Ex> h6) throws IOException, Ex {
		return h6().__(h6);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, H6Content extends PhrasingContent<H6Content>> Document h6__(ConsumerE<? super H6Content, Ex> h6) throws IOException, Ex {
		return h6().__((ConsumerE)h6);
	}

	@Override
	public Document h6__(Object text) throws IOException {
		return h6().__(text);
	}

	@Override
	public Document h6__() throws IOException {
		return h6().__();
	}

	protected Hr<Document> hr;

	@Override
	public Hr<Document> hr() throws IOException {
		if(hr == null) hr = new Hr<>(this);
		return hr.open();
	}

	@Override
	public Document hr__() throws IOException {
		return hr().__();
	}

	protected I<Document> i;

	@Override
	public I<Document> i() throws IOException {
		if(i == null) i = new I<>(this);
		return i.open();
	}

	@Override
	public <Ex extends Throwable> Document i__(RunnableE<Ex> i) throws IOException, Ex {
		return i().__(i);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, IContent extends PhrasingContent<IContent>> Document i__(ConsumerE<? super IContent, Ex> i) throws IOException, Ex {
		return i().__((ConsumerE)i);
	}

	@Override
	public Document i__(Object text) throws IOException {
		return i().__(text);
	}

	@Override
	public Document i__() throws IOException {
		return i().__();
	}

	@Override
	public Img<Document> img() throws IOException {
		return new Img<Document>(this).open();
	}

	protected Contents.Forms.Input.Type<Document> input;

	@Override
	public Contents.Forms.Input.Type<Document> input() {
		if(input == null) input = new Contents.Forms.Input.Type<>(this);
		return input;
	}

	// TODO: Variants of Link by Rel, with per-implementation attributes like Input?
	@Override
	public Link<Document> link() throws IOException {
		return new Link<Document>(this).open();
	}

	@Override
	public Link<Document> link(Link.Rel rel) throws IOException {
		return link().rel(rel);
	}

	// No link__(), since either rel or itemprop is required

	@Override
	public Meta<Document> meta() throws IOException {
		return new Meta<Document>(this).open();
	}

	@Override
	public Meta<Document> meta(Meta.Name name) throws IOException {
		return meta().name(name);
	}

	@Override
	public Meta<Document> meta(Meta.HttpEquiv httpEquiv) throws IOException {
		return meta().httpEquiv(httpEquiv);
	}

	@Override
	public Meta<Document> meta(Attributes.Enum.Charset.Value charset) throws IOException {
		return meta().charset(charset);
	}

	// No meta__(), since either name, http-equiv, or itemprop is required (TODO: confirm itemprop-only metas?)

	protected Option<Document> option;

	@Override
	public Option<Document> option() throws IOException {
		if(option == null) option = new Option<>(this);
		return option.open();
	}

	@Override
	public Document option__() throws IOException {
		return option().__();
	}

	protected P<Document> p;

	@Override
	public P<Document> p() throws IOException {
		if(p == null) p = new P<>(this);
		return p.open();
	}

	@Override
	public <Ex extends Throwable> Document p__(RunnableE<Ex> p) throws IOException, Ex {
		return p().__(p);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, PContent extends PhrasingContent<PContent>> Document p__(ConsumerE<? super PContent, Ex> p) throws IOException, Ex {
		return p().__((ConsumerE)p);
	}

	@Override
	public Document p__(Object text) throws IOException {
		return p().__(text);
	}

	@Override
	public Document p__() throws IOException {
		return p().__();
	}

	@Override
	public Param<Document> param() throws IOException {
		return new Param<Document>(this).open();
	}

	@Override
	public Document param__(Object name, Object value) throws IOException {
		return param().name(name).value(value).__();
	}

	// TODO: More types like supported by ao-taglib (ParamsTag.java), including collection types, as "params__"?

	@Override
	public Script<Document> script() throws IOException {
		return new Script<Document>(this).open();
	}

	@Override
	public Script<Document> script(String type) throws IOException {
		return new Script<Document>(this, type).open();
	}

	@Override
	public <Ex extends Throwable> Script<Document> script(Suppliers.String<Ex> type) throws IOException, Ex {
		return script((type == null) ? null : type.get());
	}

	@Override
	public Script<Document> script(Script.Type type) throws IOException {
		return new Script<Document>(this, type).open();
	}

	@Override
	public <Ex extends Throwable> Script<Document> script(Supplier<? extends Script.Type, Ex> type) throws IOException, Ex {
		return script((type == null) ? null : type.get());
	}

	@Override
	public Style<Document> style() throws IOException {
		return new Style<Document>(this).open();
	}

	@Override
	public Style<Document> style(String type) throws IOException {
		return new Style<Document>(this, type).open();
	}

	@Override
	public <Ex extends Throwable> Style<Document> style(Suppliers.String<Ex> type) throws IOException, Ex {
		return style((type == null) ? null : type.get());
	}

	@Override
	public Style<Document> style(Style.Type type) throws IOException {
		return new Style<Document>(this, type).open();
	}

	@Override
	public <Ex extends Throwable> Style<Document> style(Supplier<? extends Style.Type, Ex> type) throws IOException, Ex {
		return style((type == null) ? null : type.get());
	}

	// TODO: style__() - go directly to out, since no attributes? Lambda versions, too

	// TODO: A version called HtmlWriter that extends ChainWriter to avoid all this passing of appendables?
	// TODO: html.input.style.type().print("...").__().  How far do we take this?

	protected U<Document> u;

	@Override
	public U<Document> u() throws IOException {
		if(u == null) u = new U<>(this);
		return u.open();
	}

	@Override
	public <Ex extends Throwable> Document u__(RunnableE<Ex> u) throws IOException, Ex {
		return u().__(u);
	}

	@Override
	@SuppressWarnings("unchecked")
	public <Ex extends Throwable, UContent extends PhrasingContent<UContent>> Document u__(ConsumerE<? super UContent, Ex> u) throws IOException, Ex {
		return u().__((ConsumerE)u);
	}

	@Override
	public Document u__(Object text) throws IOException {
		return u().__(text);
	}

	@Override
	public Document u__() throws IOException {
		return u().__();
	}
}
