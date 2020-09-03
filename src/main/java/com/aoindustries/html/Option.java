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
import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.encoding.MediaWriter;
import com.aoindustries.encoding.Supplier;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.exception.WrappedException;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Option extends Element<Option> implements
	Attributes.Boolean.Disabled<Option>,
	Attributes.Text.Label<Option>,
	Attributes.Boolean.Selected<Option>,
	Attributes.Text.Value<Option>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<Option>
{

	public Option(Html html) {
		super(html);
	}

	@Override
	protected Option open() throws IOException {
		html.out.write("<option");
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
	public Option label(Object label) throws IOException {
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
	public <Ex extends Throwable> Option label(Supplier<?,Ex> label) throws IOException, Ex {
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
	public <Ex extends Throwable> Option label(MediaWritable<Ex> label) throws IOException, Ex {
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
	public Option value(Object value) throws IOException {
		return Attributes.Text.attribute(this, "value", MarkupType.NONE, value, false, false, textInXhtmlAttributeEncoder);
	}

	/**
	 * Writes the text body and closes the tag.
	 * Supports translation markup type {@link MarkupType#XHTML}.
	 */
	@SuppressWarnings("UseSpecificCatch")
	public Html text__(Object text) throws IOException {
		while(text instanceof Supplier<?,?>) {
			try {
				text = ((Supplier<?,?>)text).get();
			} catch(Error|RuntimeException|IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		if(text instanceof MediaWritable) {
			try {
				return text__((MediaWritable<?>)text);
			} catch(Error|RuntimeException|IOException e) {
				throw e;
			} catch(Throwable t) {
				throw new WrappedException(t);
			}
		}
		html.out.write('>');
		// TODO: Only allow markup when the value has been set (auto-set value from text like ao-taglib?)
		// Allow text markup from translations
		Coercion.write(text, MarkupType.TEXT, textInXhtmlEncoder, false, html.out);
		html.out.write("</option>");
		return html;
	}

	public <Ex extends Throwable> Html text__(Supplier<?,Ex> text) throws IOException, Ex {
		return text__((text == null) ? null : text.get());
	}

	public <Ex extends Throwable> Html text__(MediaWritable<Ex> text) throws IOException, Ex {
		html.out.write('>');
		if(text != null) {
			text.writeTo(
				new MediaWriter(
					html.encodingContext,
					textInXhtmlEncoder,
					new NoCloseWriter(html.out)
				)
			);
		}
		html.out.write("</option>");
		return html;
	}

	/**
	 * Writes the text body and closes the tag.
	 * Does not perform any translation markups.
	 * This is well suited for use in a try-with-resources block.
	 */
	public MediaWriter text__() throws IOException {
		html.out.write('>');
		return new MediaWriter(
			html.encodingContext,
			textInXhtmlEncoder,
			html.out
		) {
			@Override
			public void close() throws IOException {
				html.out.write("</option>");
			}
		};
	}

	/**
	 * Closes to form an empty option.
	 */
	public Html __() throws IOException {
		html.out.write("></option>");
		return html;
	}
}
