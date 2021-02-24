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

import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.encoding.MediaWriter;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Throwables;
import com.aoindustries.util.i18n.MarkupCoercion;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-option-element">4.10.10 The option element</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
// TODO: <PC extends TODO<PC>>
public class Option<PC extends Content<PC>> extends Element<Option<PC>, PC> implements
	Attributes.Boolean.Disabled<Option<PC>>,
	Attributes.Text.Label<Option<PC>>,
	Attributes.Boolean.Selected<Option<PC>>,
	Attributes.Text.Value<Option<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Option<PC>>
{

	public Option(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected Option<PC> writeOpen() throws IOException {
		document.out.write("<option");
		return this;
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_label.asp">HTML option label Attribute</a>.
	 *
	 * @deprecated  Although still part of the HTML specification, there is a
	 *              <a href="https://bugzilla.mozilla.org/show_bug.cgi?id=40545">20-year old Firefox bug</a>
	 *              that the label attribute is not supported.  We are deprecating
	 *              this method to make it clear it should probably not be used, as the
	 *              effect of label can be attained through the value attribute and
	 *              tag body anyway.
	 */
	@Deprecated
	@Override
	public Option<PC> label(Object label) throws IOException {
		return Attributes.Text.Label.super.label(label);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_label.asp">HTML option label Attribute</a>.
	 *
	 * @see #label(java.lang.Object)
	 *
	 * @deprecated  Although still part of the HTML specification, there is a
	 *              <a href="https://bugzilla.mozilla.org/show_bug.cgi?id=40545">20-year old Firefox bug</a>
	 *              that the label attribute is not supported.  We are deprecating
	 *              this method to make it clear it should probably not be used, as the
	 *              effect of label can be attained through the value attribute and
	 *              tag body anyway.
	 */
	@Deprecated
	@Override
	public <Ex extends Throwable> Option<PC> label(IOSupplierE<?, Ex> label) throws IOException, Ex {
		return Attributes.Text.Label.super.label(label);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_label.asp">HTML option label Attribute</a>.
	 *
	 * @see #label(java.lang.Object)
	 *
	 * @deprecated  Although still part of the HTML specification, there is a
	 *              <a href="https://bugzilla.mozilla.org/show_bug.cgi?id=40545">20-year old Firefox bug</a>
	 *              that the label attribute is not supported.  We are deprecating
	 *              this method to make it clear it should probably not be used, as the
	 *              effect of label can be attained through the value attribute and
	 *              tag body anyway.
	 */
	@Deprecated
	@Override
	public <Ex extends Throwable> Option<PC> label(MediaWritable<Ex> label) throws IOException, Ex {
		return Attributes.Text.Label.super.label(label);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_value.asp">HTML option value Attribute</a>.
	 * <p>
	 * An empty value must still be specified for &lt;option&gt;, as it overrides the
	 * default behavior of using the tag's text content as the value.
	 * </p>
	 */
	@Override
	public Option<PC> value(Object value) throws IOException {
		return Attributes.Text.attribute(this, "value", MarkupType.NONE, value, false, false, textInXhtmlAttributeEncoder);
	}

	/**
	 * Writes the text body then closes this element.
	 * Supports translation markup type {@link MarkupType#XHTML}.
	 *
	 * @return  The parent content model this element is within
	 */
	@SuppressWarnings("UseSpecificCatch")
	public PC text__(Object text) throws IOException {
		while(text instanceof IOSupplierE<?, ?>) {
			try {
				text = ((IOSupplierE<?, ?>)text).get();
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		if(text instanceof MediaWritable) {
			try {
				return text__((MediaWritable<?>)text);
			} catch(Throwable t) {
				throw Throwables.wrap(t, IOException.class, IOException::new);
			}
		}
		document.out.write('>');
		// TODO: Only allow markup when the value has been set (auto-set value from text like ao-taglib?)
		// Allow text markup from translations
		MarkupCoercion.write(text, MarkupType.TEXT, true, textInXhtmlEncoder, false, document.out);
		document.out.write("</option>");
		return pc;
	}

	/**
	 * Writes the text body then closes this element.
	 * Supports translation markup type {@link MarkupType#XHTML}.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable> PC text__(IOSupplierE<?, Ex> text) throws IOException, Ex {
		return text__((text == null) ? null : text.get());
	}

	/**
	 * Writes the text body then closes this element.
	 * Does not perform any translation markups.
	 *
	 * @return  The parent content model this element is within
	 */
	public <Ex extends Throwable> PC text__(MediaWritable<Ex> text) throws IOException, Ex {
		document.out.write('>');
		if(text != null) {
			text.writeTo(
				new MediaWriter(
					document.encodingContext,
					textInXhtmlEncoder,
					new NoCloseWriter(document.out)
				)
			);
		}
		document.out.write("</option>");
		return pc;
	}

	/**
	 * Writes a text body then closes this element.
	 * Does not perform any translation markups.
	 * This is well suited for use in a try-with-resources block.
	 */
	// TODO: __() method to end text?  Call it "ContentWriter"?
	public MediaWriter text__() throws IOException {
		document.out.write('>');
		return new MediaWriter(
			document.encodingContext,
			textInXhtmlEncoder,
			document.out
		) {
			@Override
			public void close() throws IOException {
				document.out.write("</option>");
			}
		};
	}

	/**
	 * Closes this element to form an empty option.
	 *
	 * @return  The parent content model this element is within
	 */
	public PC __() throws IOException {
		document.out.write("></option>");
		return pc;
	}
}
