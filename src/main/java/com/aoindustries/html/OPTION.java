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
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.encoding.TextInXhtmlEncoder.textInXhtmlEncoder;
import com.aoindustries.io.NoCloseWriter;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Throwables;
import com.aoindustries.util.i18n.MarkupCoercion;
import com.aoindustries.util.i18n.MarkupType;
import java.io.IOException;
import java.io.Writer;

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
public class OPTION<PC extends Union_DATALIST_OPTGROUP<PC>> extends Element<OPTION<PC>, PC> implements
	com.aoindustries.html.attributes.Boolean.Disabled<OPTION<PC>>,
	com.aoindustries.html.attributes.Text.Label<OPTION<PC>>,
	com.aoindustries.html.attributes.Boolean.Selected<OPTION<PC>>,
	com.aoindustries.html.attributes.Text.Value<OPTION<PC>>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	AlmostGlobalAttributes<OPTION<PC>>
{

	public OPTION(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected OPTION<PC> writeOpen(Writer out) throws IOException {
		document.autoNli(out).unsafe(out, "<option", false);
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
	public OPTION<PC> label(Object label) throws IOException {
		return com.aoindustries.html.attributes.Text.Label.super.label(label);
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
	public <Ex extends Throwable> OPTION<PC> label(IOSupplierE<?, Ex> label) throws IOException, Ex {
		return com.aoindustries.html.attributes.Text.Label.super.label(label);
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
	public <Ex extends Throwable> OPTION<PC> label(MediaWritable<Ex> label) throws IOException, Ex {
		return com.aoindustries.html.attributes.Text.Label.super.label(label);
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/att_option_value.asp">HTML option value Attribute</a>.
	 * <p>
	 * An empty value must still be specified for &lt;option&gt;, as it overrides the
	 * default behavior of using the tag's text content as the value.
	 * </p>
	 */
	@Override
	public OPTION<PC> value(Object value) throws IOException {
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
		if(text == null) {
			return __();
		} else {
			Writer out = document.getUnsafe(null);
			document.autoIndent(out).unsafe(out, '>').incDepth();
			boolean oldAutonli = document.getAutonli();
			if(oldAutonli) document.setAutonli(false);
			boolean oldIndent = document.getIndent();
			if(oldIndent) document.setIndent(false);
			try {
				// TODO: Only allow markup when the value has been set (auto-set value from text like ao-taglib?)
				// Allow text markup from translations
				MarkupCoercion.write(text, MarkupType.TEXT, true, textInXhtmlEncoder, false, out);
			} finally {
				document
					.setIndent(oldIndent)
					.setAutonli(oldAutonli);
			}
			document
				// Set in "unsafe" below: .clearAtnl() // Unknown, safe to assume not at newline
				.decDepth()
				// Assumed not at newline: .autoIndent()
				.unsafe(out, "</option>", false)
				.autoNl(out);
			return pc;
		}
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
		if(text == null) {
			return __();
		} else {
			Writer out = document.getUnsafe(null);
			document.autoIndent(out).unsafe(out, '>').incDepth();
			boolean oldAutonli = document.getAutonli();
			if(oldAutonli) document.setAutonli(false);
			boolean oldIndent = document.getIndent();
			if(oldIndent) document.setIndent(false);
			try {
				text.writeTo(
					new DocumentMediaWriter(
						document,
						textInXhtmlEncoder,
						new NoCloseWriter(out)
					)
				);
			} finally {
				document
					.setIndent(oldIndent)
					.setAutonli(oldAutonli);
			}
			document
				// Set in "unsafe" below: .clearAtnl() // Unknown, safe to assume not at newline
				.decDepth()
				// Assumed not at newline: .autoIndent()
				.unsafe(out, "</option>", false).
				autoNl(out);
			return pc;
		}
	}

	/**
	 * Writes a text body then closes this element.
	 * Does not perform any translation markups.
	 * This is well suited for use in a try-with-resources block.
	 */
	// TODO: __() method on DocumentMediaWriter to end text?  Call it "ContentWriter"?
	public DocumentMediaWriter text__() throws IOException {
		Writer out = document.getUnsafe(null);
		document.autoIndent(out).unsafe(out, '>').incDepth();
		boolean oldAutonli = document.getAutonli();
		if(oldAutonli) document.setAutonli(false);
		boolean oldIndent = document.getIndent();
		if(oldIndent) document.setIndent(false);
		return new DocumentMediaWriter(document, textInXhtmlEncoder, out) {
			@Override
			public void close() throws IOException {
				// Get a new "out", just in case changed before closing, such as in legacy JSP taglibs
				Writer out = document.getUnsafe(null);
				document
					.setIndent(oldIndent)
					.setAutonli(oldAutonli)
					// Set in "unsafe" below: .clearAtnl() // Unknown, safe to assume not at newline
					.decDepth()
					// Assumed not at newline: .autoIndent()
					.unsafe(out, "</option>", false)
					.autoNl(out);
			}
		};
	}

	/**
	 * Closes this element to form an empty option.
	 *
	 * @return  The parent content model this element is within
	 */
	public PC __() throws IOException {
		Writer out = document.getUnsafe(null);
		document.autoIndent(out).unsafe(out, "></option>", false).autoNl(out);
		return pc;
	}
}
