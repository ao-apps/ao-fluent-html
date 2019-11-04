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

import com.aoindustries.encoding.Coercion;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import static com.aoindustries.html.ApplicationResources.accessor;
import com.aoindustries.html.Style.Type;
import com.aoindustries.lang.LocalizedIllegalStateException;
import com.aoindustries.util.StringUtility;
import java.io.IOException;

/**
 * See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.
 *
 * @author  AO Industries, Inc.
 */
public class Link extends EmptyElement<Link> implements
	Attributes.Enum.Crossorigin<Link,Link.Crossorigin>,
	Attributes.Url.Href<Link>,
	Attributes.Text.Media<Link>,
	Attributes.Enum.Rel<Link,Link.Rel>,
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.Mouse.Events<Link>
{

	public Link(Html html) {
		super(html);
	}

	@Override
	protected Link open() throws IOException {
		html.out.write("<link");
		return this;
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
	 */
	public enum Crossorigin implements StringSupplier<RuntimeException> {
		ANONYMOUS(
			NO_VALUE,
			"anonymous"
		),
		USE_CREDENTIALS(
			"use-credentials",
			"use-credentials"
		);

		private final String sgml;
		private final String xml;

		private Crossorigin(String sgml, String xml) {
			this.sgml = sgml;
			this.xml = xml;
		}

		@Override
		public String toString() {
			return xml;
		}

		@Override
		public String get(Serialization serialization, Doctype doctype) {
			if(serialization == Serialization.SGML) {
				return sgml;
			} else {
				assert serialization == Serialization.XML;
				return xml;
			}
		}
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 */
	private Object itemprop;

	// TODO: Is global property, move there and add See comment, still checking for link-specific rules here
	// TODO: Attributes...itemprop
	public Link itemprop(Object itemprop) throws IOException {
		itemprop = Coercion.trimNullIfEmpty(itemprop);
		if(itemprop != null) {
			if(this.itemprop != null) {
				throw new LocalizedIllegalStateException(
					accessor,
					"Html.duplicateAttribute",
					"link",
					"itemprop",
					Coercion.toString(this.itemprop),
					Coercion.toString(itemprop)
				);
			}
			this.itemprop = itemprop;
			if(this.rel != null) {
				throw new LocalizedIllegalStateException(
					accessor,
					"Item.relOrItemprop"
				);
			}
			html.out.write(" itemprop=\"");
			Coercion.write(itemprop, textInXhtmlAttributeEncoder, html.out);
			html.out.write('"');
		}
		return this;
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.
	 * See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.
	 */
	public enum Rel implements StringSupplier<RuntimeException> {
		ALTERNATE("alternate"),
		AUTHOR("author"), // w3schools only
		CANONICAL("canonical"), // TODO: This is not in the last.  Should we support arbitrary String values, like Script.type?
		DNS_PREFETCH("dns-prefetch"),
		HELP("help"), // w3schools only
		ICON("icon"),
		LICENSE("license"), // w3schools only
		MODULEPRELOAD("modulepreload"), // HTML Standard only
		NEXT("next"),
		PINGBACK("pingback"),
		PRECONNECT("preconnect"),
		PREFETCH("prefetch"),
		PRELOAD("preload"),
		PRERENDER("prerender"),
		PREV("prev"), // w3schools only
		SEARCH("search"),
		STYLESHEET("stylesheet");

		private final java.lang.String value;
		// TODO: Verify values by doctype

		private Rel(java.lang.String value) {
			this.value = value;
		}

		@Override
		public java.lang.String toString() {
			return value;
		}

		@Override
		public java.lang.String get(Serialization serialization, Doctype doctype) {
			return value;
		}
	}

	private String rel;

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 *
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.
	 * See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.
	 */
	@Override
	public Link rel(String rel) throws IOException {
		rel = StringUtility.trimNullIfEmpty(rel);
		if(rel != null) {
			if(this.rel != null) {
				throw new LocalizedIllegalStateException(
					accessor,
					"Html.duplicateAttribute",
					"link",
					"rel",
					Coercion.toString(this.rel),
					Coercion.toString(rel)
				);
			}
			this.rel = rel;
			if(this.itemprop != null) {
				throw new LocalizedIllegalStateException(
					accessor,
					"Item.relOrItemprop"
				);
			}
			Attributes.Enum.Rel.super.rel(rel);
		}
		return this;
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 *
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.
	 * See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.
	 */
	@Override
	public <Ex extends Throwable> Link rel(StringSupplier<Ex> rel) throws IOException, Ex {
		return Attributes.Enum.Rel.super.rel(rel);
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 *
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.
	 * See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.
	 */
	@Override
	public Link rel(Rel rel) throws IOException {
		return Attributes.Enum.Rel.super.rel(rel);
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 *
	 * See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.
	 * See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.
	 */
	@Override
	public <Ex extends Throwable> Link rel(EnumSupplier<Rel,Ex> rel) throws IOException, Ex {
		return Attributes.Enum.Rel.super.rel(rel);
	}

	private String type;

	/**
	 * If the rel is {@link Rel#STYLESHEET}, the type is {@link Type#TEXT_CSS},
	 * and the {@link Doctype} is {@link Doctype#HTML5}, skips writing
	 * the type.
	 *
	 * See <a href="https://www.w3schools.com/tags/att_link_type.asp">HTML link type Attribute</a>.
	 */
	// TODO: Attributes...type
	public Link type(String type) throws IOException {
		type = StringUtility.trimNullIfEmpty(type);
		this.type = type;
		if(
			type != null
			&& !(
				html.doctype == Doctype.HTML5
				&& rel != null
				&& rel.equals(Rel.STYLESHEET.toString())
				&& Type.TEXT_CSS.getContentType().equalsIgnoreCase(type)
			)
		) {
			html.out.write(" type=\"");
			encodeTextInXhtmlAttribute(type, html.out);
			html.out.write('"');
		}
		return this;
	}

	/**
	 * If the rel is {@link Rel#STYLESHEET}, a {@linkplain #type(java.lang.String) type}
	 * has not been written, and the {@link Doctype} is not {@link Doctype#HTML5},
	 * writes the default type {@link Type#TEXT_CSS} for backward compatibility.
	 * <p>
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * </p>
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 */
	@Override
	public Html __() throws IOException {
		if(
			type == null
			&& html.doctype != Doctype.HTML5
			&& rel != null
			&& rel.equals(Rel.STYLESHEET.toString())
		) {
			html.out.write(" type=\"");
			html.out.write(Type.TEXT_CSS.getContentType());
			html.out.write('"');
		}
		super.__();
		if(rel == null && itemprop == null) {
			throw new LocalizedIllegalStateException(
				accessor,
				"Item.relOrItemprop"
			);
		}
		return html;
	}
}
