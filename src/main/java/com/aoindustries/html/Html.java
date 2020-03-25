/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019, 2020  AO Industries, Inc.
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

import com.aoindustries.encoding.Coercion;
import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.MediaWriter;
import com.aoindustries.encoding.Serialization;
import static com.aoindustries.encoding.TextInXhtmlEncoder.encodeTextInXhtml;
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.exception.WrappedException;
import com.aoindustries.io.NoCloseWriter;
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
 * See also <a href="https://github.com/xmlet/HtmlFlow">HtmlFlow</a>.
 * </p>
 *
 * @author  AO Industries, Inc.
 */
public class Html {

	/**
	 * The default, and recommended, encoding for (X)HTML.
	 */
	public static final Charset ENCODING = StandardCharsets.UTF_8;

	public final Serialization serialization;
	public final Doctype doctype;

	/**
	 * Writer for raw output.
	 * <p>
	 * TODO: This field will possibly become "protected" (or deprecated to minimize direct usage) once the full set of HTML tags have been implemented.
	 * </p>
	 */
	public final Writer out;

	public Html(Serialization serialization, Doctype doctype, Writer out) {
		this.serialization = serialization;
		this.doctype = doctype;
		this.out = out;
	}

	/**
	 * @see Doctype#xmlDeclaration(com.aoindustries.encoding.Serialization, java.lang.String, java.lang.Appendable)
	 */
	// TODO: Define here only since depends on both serialization and doctype
	public Html xmlDeclaration(String documentEncoding) throws IOException {
		doctype.xmlDeclaration(serialization, documentEncoding, out);
		return this;
	}

	/**
	 * @see Doctype#doctype(com.aoindustries.encoding.Serialization, java.lang.Appendable)
	 */
	// TODO: Define here only since depends on both serialization and doctype
	public Html doctype() throws IOException {
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
	public Html selfClose() throws IOException {
		serialization.selfClose(out);
		return this;
	}

	/**
	 * Writes the given text with proper encoding.
	 * Does not perform any translation markups.
	 */
	public Html text(char ch) throws IOException {
		encodeTextInXhtml(ch, out);
		return this;
	}

	/**
	 * Writes the given text with proper encoding.
	 * Does not perform any translation markups.
	 */
	public Html text(char[] cbuf) throws IOException {
		encodeTextInXhtml(cbuf, out);
		return this;
	}

	/**
	 * Writes the given text with proper encoding.
	 * Does not perform any translation markups.
	 */
	public Html text(char[] cbuf, int start, int len) throws IOException {
		encodeTextInXhtml(cbuf, start, len, out);
		return this;
	}

	// TODO: text(CharSequence)?
	// TODO: text(CharSequence, int, int)?

	/**
	 * Writes the given text with proper encoding.
	 * Supports translation markup type {@link MarkupType#XHTML}.
	 */
	public Html text(Object text) throws IOException {
		while(text instanceof Supplier<?,?>) {
			try {
				text = ((Supplier<?,?>)text).get();
			} catch(Error|RuntimeException|IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		if(text instanceof char[]) {
			return text((char[])text);
		}
		if(text instanceof TextWriter) {
			try {
				return text((TextWriter<?>)text);
			} catch(Error|RuntimeException|IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		// Allow text markup from translations
		Coercion.write(text, MarkupType.XHTML, textInXhtmlEncoder, false, out);
		return this;
	}

	public <Ex extends Throwable> Html text(Supplier<?,Ex> text) throws IOException, Ex {
		return text((text == null) ? null : text.get());
	}

	public <Ex extends Throwable> Html text(TextWriter<Ex> text) throws IOException, Ex {
		if(text != null) {
			text.writeText(
				new MediaWriter(
					textInXhtmlEncoder,
					new NoCloseWriter(out)
				)
			);
		}
		return this;
	}

	/**
	 * Writes the given text with proper encoding.
	 * Does not perform any translation markups.
	 * This is well suited for use in a try-with-resources block.
	 */
	public MediaWriter text() throws IOException {
		return new MediaWriter(
			textInXhtmlEncoder,
			new NoCloseWriter(out)
		);
	}

	// TODO: comments

	/**
	 * This is {@code '\n'} on all platforms.  If a different newline is required,
	 * such as {@code "\r\n"} for email, filter the output.
	 */
	public Html nl() throws IOException {
		out.write('\n');
		return this;
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public Area area() throws IOException {
		return new Area(this).open();
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public Area area(Rectangle rect) throws IOException {
		return area().shape(Area.Shape.RECT).coords(rect);
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public <Ex extends Throwable> Area area(Suppliers.Rectangle<Ex> rect) throws IOException, Ex {
		return area().shape(Area.Shape.RECT).coords(rect);
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public Area area(Circle circle) throws IOException {
		return area().shape(Area.Shape.CIRCLE).coords(circle);
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public <Ex extends Throwable> Area area(Suppliers.Circle<Ex> circle) throws IOException, Ex {
		return area().shape(Area.Shape.CIRCLE).coords(circle);
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public Area area(Polygon poly) throws IOException {
		return area().shape(Area.Shape.POLY).coords(poly);
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public <Ex extends Throwable> Area area(Suppliers.Polygon<Ex> poly) throws IOException, Ex {
		return area().shape(Area.Shape.POLY).coords(poly);
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public Area area(Shape shape) throws IOException {
		if(shape == null) return area();
		if(shape instanceof Rectangle) return area((Rectangle)shape);
		if(shape instanceof Circle) return area((Circle)shape);
		if(shape instanceof Polygon) return area((Polygon)shape);
		// Pass-through in a way that must result in an exception for the unknown type instead of duplicating long exception message here
		area().coords(shape);
		throw new AssertionError("IllegalArgumentException must have been thrown by coords for invalid Shape");
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.
	 */
	public <Ex extends Throwable> Area area(Suppliers.Shape<Ex> shape) throws IOException, Ex {
		return area(shape == null ? null : shape.get());
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.
	 */
	public Base base() throws IOException {
		return new Base(this).open();
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.
	 */
	public Html base__(String href) throws IOException {
		return base().href(href).__();
	}

	protected Br br;

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.
	 */
	public Br br() throws IOException {
		if(br == null) br = new Br(this);
		return br.open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.
	 */
	public Html br__() throws IOException {
		return br().__();
	}

	protected Hr hr;

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.
	 */
	public Hr hr() throws IOException {
		if(hr == null) hr = new Hr(this);
		return hr.open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.
	 */
	public Html hr__() throws IOException {
		return hr().__();
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/col">&lt;col&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_col.asp">HTML col tag</a>.
	 */
	public Col col() throws IOException {
		return new Col(this).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.
	 */
	public Img img() throws IOException {
		return new Img(this).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 */
	public com.aoindustries.html.Input.Dynamic input() throws IOException {
		return new com.aoindustries.html.Input.Dynamic(this).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	public com.aoindustries.html.Input.Dynamic input(String type) throws IOException {
		return new com.aoindustries.html.Input.Dynamic(this, type).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	// TODO: Move these type Input.type only?
	public <Ex extends Throwable> com.aoindustries.html.Input.Dynamic input(Suppliers.String<Ex> type) throws IOException, Ex {
		return input((type == null) ? null : type.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	public com.aoindustries.html.Input.Dynamic input(com.aoindustries.html.Input.Dynamic.Type type) throws IOException {
		return new com.aoindustries.html.Input.Dynamic(this, type).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	// TODO: Move these type Input.type only?
	public <Ex extends Throwable> com.aoindustries.html.Input.Dynamic input(Supplier<? extends com.aoindustries.html.Input.Dynamic.Type,Ex> type) throws IOException, Ex {
		return input((type == null) ? null : type.get());
	}

	public static class Input {

		private final Html html;

		public Input(Html html) {
			this.html = html;
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_button.asp">HTML input type="button"</a>.
		 */
		public com.aoindustries.html.Input.Button button() throws IOException {
			return new com.aoindustries.html.Input.Button(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_checkbox.asp">HTML input type="checkbox"</a>.
		 */
		public com.aoindustries.html.Input.Checkbox checkbox() throws IOException {
			return new com.aoindustries.html.Input.Checkbox(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_color.asp">HTML input type="color"</a>.
		 */
		public com.aoindustries.html.Input.Color color() throws IOException {
			return new com.aoindustries.html.Input.Color(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_date.asp">HTML input type="date"</a>.
		 */
		public com.aoindustries.html.Input.Date date() throws IOException {
			return new com.aoindustries.html.Input.Date(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_datetime-local.asp">HTML input type="datetime-local"</a>.
		 */
		public com.aoindustries.html.Input.DatetimeLocal datetimeLocal() throws IOException {
			return new com.aoindustries.html.Input.DatetimeLocal(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_email.asp">HTML input type="email"</a>.
		 */
		public com.aoindustries.html.Input.Email email() throws IOException {
			return new com.aoindustries.html.Input.Email(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_file.asp">HTML input type="file"</a>.
		 */
		public com.aoindustries.html.Input.File file() throws IOException {
			return new com.aoindustries.html.Input.File(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_hidden.asp">HTML input type="hidden"</a>.
		 */
		public com.aoindustries.html.Input.Hidden hidden() throws IOException {
			return new com.aoindustries.html.Input.Hidden(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_image.asp">HTML input type="image"</a>.
		 */
		public com.aoindustries.html.Input.Image image() throws IOException {
			return new com.aoindustries.html.Input.Image(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_month.asp">HTML input type="month"</a>.
		 */
		public com.aoindustries.html.Input.Month month() throws IOException {
			return new com.aoindustries.html.Input.Month(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_number.asp">HTML input type="number"</a>.
		 */
		public com.aoindustries.html.Input.Number number() throws IOException {
			return new com.aoindustries.html.Input.Number(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_password.asp">HTML input type="password"</a>.
		 */
		public com.aoindustries.html.Input.Password password() throws IOException {
			return new com.aoindustries.html.Input.Password(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_radio.asp">HTML input type="radio"</a>.
		 */
		public com.aoindustries.html.Input.Radio radio() throws IOException {
			return new com.aoindustries.html.Input.Radio(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_range.asp">HTML input type="range"</a>.
		 */
		public com.aoindustries.html.Input.Range range() throws IOException {
			return new com.aoindustries.html.Input.Range(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_reset.asp">HTML input type="reset"</a>.
		 */
		public com.aoindustries.html.Input.Reset reset() throws IOException {
			return new com.aoindustries.html.Input.Reset(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_search.asp">HTML input type="search"</a>.
		 */
		public com.aoindustries.html.Input.Search search() throws IOException {
			return new com.aoindustries.html.Input.Search(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.
		 */
		public com.aoindustries.html.Input.Submit submit() throws IOException {
			return new com.aoindustries.html.Input.Submit(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 */
		public Html submit__(Object value) throws IOException {
			return submit().value(value).__();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 */
		public <Ex extends Throwable> Html submit__(Supplier<?,Ex> value) throws IOException, Ex {
			return submit().value(value).__();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.
		 * See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.
		 */
		public <Ex extends Throwable> Html submit__(AttributeWriter<Ex> value) throws IOException, Ex {
			return submit().value(value).__();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_tel.asp">HTML input type="tel"</a>.
		 */
		public com.aoindustries.html.Input.Tel tel() throws IOException {
			return new com.aoindustries.html.Input.Tel(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_text.asp">HTML input type="text"</a>.
		 */
		public com.aoindustries.html.Input.Text text() throws IOException {
			return new com.aoindustries.html.Input.Text(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_time.asp">HTML input type="time"</a>.
		 */
		public com.aoindustries.html.Input.Time time() throws IOException {
			return new com.aoindustries.html.Input.Time(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_url.asp">HTML input type="url"</a>.
		 */
		public com.aoindustries.html.Input.Url url() throws IOException {
			return new com.aoindustries.html.Input.Url(html).open();
		}

		/**
		 * See <a href="https://www.w3schools.com/tags/att_input_type_week.asp">HTML input type="week"</a>.
		 */
		public com.aoindustries.html.Input.Week week() throws IOException {
			return new com.aoindustries.html.Input.Week(html).open();
		}
	}

	public final Input input = new Input(this);

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.
	 */
	// TODO: Variants of Link by Rel, with per-implementation attributes like Input?
	public Link link() throws IOException {
		return new Link(this).open();
	}

	/**
	 * @see #link()
	 * @see Link#rel(java.lang.Enum)
	 */
	public Link link(Link.Rel rel) throws IOException {
		return link().rel(rel);
	}

	// No link__(), since either rel or itemprop is required

	/**
	 * <ul>
	 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
	 * </ul>
	 */
	public Meta meta() throws IOException {
		return new Meta(this).open();
	}

	/**
	 * @see #meta()
	 * @see Meta#name(java.lang.Enum)
	 */
	public Meta meta(Meta.Name name) throws IOException {
		return meta().name(name);
	}

	/**
	 * @see #meta()
	 * @see Meta#httpEquiv(java.lang.Enum)
	 */
	public Meta meta(Meta.HttpEquiv httpEquiv) throws IOException {
		return meta().httpEquiv(httpEquiv);
	}

	/**
	 * @see #meta()
	 * @see Meta#charset(java.lang.Enum)
	 */
	public Meta meta(Attributes.Enum.Charset.Value charset) throws IOException {
		return meta().charset(charset);
	}

	// No meta__(), since either name, http-equiv, or itemprop is required (TODO: confirm itemprop-only metas?)

	protected Option option;

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.
	 */
	public Option option() throws IOException {
		if(option == null) option = new Option(this);
		return option.open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.
	 */
	public Html option__() throws IOException {
		return option().__();
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.
	 */
	public Param param() throws IOException {
		return new Param(this).open();
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.
	 * See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.
	 */
	public Html param__(Object name, Object value) throws IOException {
		return param().name(name).value(value).__();
	}

	// TODO: More types like supported by ao-taglib (ParamsTag.java), including collection types, as "params__"?

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.
	 *
	 * @see Doctype#scriptType(java.lang.Appendable)
	 */
	public Script script() throws IOException {
		return new Script(this).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 */
	public Script script(String type) throws IOException {
		return new Script(this, type).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 */
	public <Ex extends Throwable> Script script(Suppliers.String<Ex> type) throws IOException, Ex {
		return script((type == null) ? null : type.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 */
	public Script script(Script.Type type) throws IOException {
		return new Script(this, type).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.
	 */
	public <Ex extends Throwable> Script script(Supplier<? extends Script.Type,Ex> type) throws IOException, Ex {
		return script((type == null) ? null : type.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.
	 *
	 * @see Doctype#styleType(java.lang.Appendable)
	 */
	public Style style() throws IOException {
		return new Style(this).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.
	 */
	public Style style(String type) throws IOException {
		return new Style(this, type).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.
	 */
	public <Ex extends Throwable> Style style(Suppliers.String<Ex> type) throws IOException, Ex {
		return style((type == null) ? null : type.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.
	 */
	public Style style(Style.Type type) throws IOException {
		return new Style(this, type).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.
	 */
	public <Ex extends Throwable> Style style(Supplier<? extends Style.Type,Ex> type) throws IOException, Ex {
		return style((type == null) ? null : type.get());
	}

	// TODO: style__() - go directly to out, since no attributes? Lambda versions, too

	// TODO: A version called HtmlWriter that extends ChainWriter to avoid all this passing of appendables?
	// TODO: html.input.style.type().print("...").__().  How far do we take this?
}
