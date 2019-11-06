/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2019  AO Industries, Inc.
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
	 * Content type for HTML.
	 */
	public static final String CONTENT_TYPE_HTML = "text/html";

	/**
	 * Content type for XHTML.
	 */
	public static final String CONTENT_TYPE_XHTML = "application/xhtml+xml";

	/**
	 * The default, and recommended, encoding for (X)HTML.
	 */
	public static final Charset ENCODING = StandardCharsets.UTF_8;

	public final Serialization serialization;
	public final Doctype doctype;
	protected final Writer out;

	public Html(Serialization serialization, Doctype doctype, Writer out) {
		this.serialization = serialization;
		this.doctype = doctype;
		this.out = out;
	}

	/**
	 * @see Doctype#xmlDeclaration(com.aoindustries.html.Serialization, java.lang.String, java.lang.Appendable)
	 */
	// TODO: Define here only since depends on both serialization and doctype
	public Html xmlDeclaration(String documentEncoding) throws IOException {
		doctype.xmlDeclaration(serialization, documentEncoding, out);
		return this;
	}

	/**
	 * @see Doctype#doctype(com.aoindustries.html.Serialization, java.lang.Appendable)
	 */
	// TODO: Define here only since depends on both serialization and doctype
	public Html doctype() throws IOException {
		doctype.doctype(serialization, out);
		return this;
	}

	/**
	 * @see Serialization#selfClose(java.lang.Appendable)
	 */
	public Html selfClose() throws IOException {
		serialization.selfClose(out);
		return this;
	}

	public Html nl() throws IOException {
		out.write('\n');
		return this;
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
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 */
	public Input input() throws IOException {
		return new Input(this).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	public Input input(String type) throws IOException {
		return new Input(this, type).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	public <Ex extends Throwable> Input input(StringSupplier<Ex> type) throws IOException, Ex {
		return input((type == null) ? null : type.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	public Input input(Input.Type type) throws IOException {
		return new Input(this, type).open();
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
	 * See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.
	 */
	public <Ex extends Throwable> Input input(Supplier<? extends Input.Type,Ex> type) throws IOException, Ex {
		return input((type == null) ? null : type.get());
	}

	/**
	 * See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.
	 */
	// TODO: Variants of Link by Rel, with per-implementation attributes like Input?
	public Link link() throws IOException {
		return new Link(this).open();
	}

	// No link__(), since either rel or itemprop is required

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
	public <Ex extends Throwable> Script script(StringSupplier<Ex> type) throws IOException, Ex {
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
	public <Ex extends Throwable> Style style(StringSupplier<Ex> type) throws IOException, Ex {
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
