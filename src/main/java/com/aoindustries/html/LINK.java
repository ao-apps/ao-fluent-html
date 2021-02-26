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

import com.aoindustries.encoding.Doctype;
import com.aoindustries.encoding.Serialization;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.encodeTextInXhtmlAttribute;
import static com.aoindustries.encoding.TextInXhtmlAttributeEncoder.textInXhtmlAttributeEncoder;
import com.aoindustries.html.STYLE.Type;
import com.aoindustries.io.ContentType;
import com.aoindustries.io.function.IOSupplierE;
import com.aoindustries.lang.Coercion;
import com.aoindustries.lang.LocalizedIllegalStateException;
import com.aoindustries.lang.Strings;
import java.io.IOException;

/**
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#the-link-element">4.2.4 The link element</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link">&lt;link&gt; - HTML: Hypertext Markup Language</a>.</li>
 * <li>See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.</li>
 * </ul>
 *
 * @param  <PC>  The parent content model this element is within
 *
 * @author  AO Industries, Inc.
 */
public class LINK<PC extends UnionContent.Metadata_Phrasing<PC>> extends VoidElement<LINK<PC>, PC> implements
	// TODO: as
	// TODO: charset
	Attributes.Enum.Crossorigin<LINK<PC>, LINK.Crossorigin>,
	// https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link#attr-disabled
	Attributes.Boolean.Disabled<LINK<PC>>,
	Attributes.Url.Href<LINK<PC>>,
	Attributes.String.Hreflang<LINK<PC>>,
	Attributes.Text.Media<LINK<PC>>,
	Attributes.Enum.Rel<LINK<PC>, LINK.Rel>,
	// TODO: rev
	// TODO: sizes
	// TODO: target (not standardizes per MDN)
	// TODO: type
	// Global Event Attributes: https://www.w3schools.com/tags/ref_eventattributes.asp
	Attributes.Event.AlmostGlobal<LINK<PC>>,
	Attributes.Event.Window.Onerror<LINK<PC>>,
	Attributes.Event.Window.Onload<LINK<PC>>
{

	private static final com.aoindustries.i18n.Resources RESOURCES =
		com.aoindustries.i18n.Resources.getResources(LINK.class);

	public LINK(Document document, PC pc) {
		super(document, pc);
	}

	@Override
	protected LINK<PC> writeOpen() throws IOException {
		document.out.write("<link");
		return this;
	}

	/**
	 * See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_settings_attributes">The crossorigin attribute: Requesting CORS access to content</a>.
	 */
	public enum Crossorigin implements Attributes.Enum.EnumSupplier {
		ANONYMOUS(
			Attributes.NO_VALUE,
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
	// TODO: Attributes...itemprop in global
	public LINK<PC> itemprop(Object itemprop) throws IOException {
		itemprop = Coercion.trimNullIfEmpty(itemprop);
		if(itemprop != null) {
			if(this.itemprop != null) {
				throw new LocalizedIllegalStateException(
					Resources.PACKAGE_RESOURCES,
					"Document.duplicateAttribute",
					"link",
					"itemprop",
					Coercion.toString(this.itemprop),
					Coercion.toString(itemprop)
				);
			}
			this.itemprop = itemprop;
			if(this.rel != null) {
				throw new LocalizedIllegalStateException(RESOURCES, "relOrItemprop");
			}
			document.out.write(" itemprop=\"");
			Coercion.write(itemprop, textInXhtmlAttributeEncoder, document.out);
			document.out.write('"');
		}
		return this;
	}

	/**
	 * <ul>
	 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Link_types">Link types - HTML: Hypertext Markup Language</a>.</li>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 */
	public enum Rel implements Attributes.Enum.EnumSupplier {
		ALTERNATE("alternate"),
		/**
		 * @deprecated
		 */
		@Deprecated
		ARCHIVES("archives"), // MDN only
		AUTHOR("author"), // w3schools, MDN only
		CANONICAL("canonical"), // MDN only
		DNS_PREFETCH("dns-prefetch"),
		/**
		 * @deprecated
		 */
		@Deprecated
		FIRST("first"), // MDN only
		HELP("help"), // w3schools, MDN only
		ICON("icon"),
		IMPORT("import"), // MDN only
		/**
		 * @deprecated
		 */
		@Deprecated
		INDEX("index"), // MDN only
		/**
		 * @deprecated
		 */
		@Deprecated
		LAST("last"), // MDN only
		LICENSE("license"), // w3schools, MDN only
		MANIFEST("manifest"), // MDN only
		MODULEPRELOAD("modulepreload"),
		NEXT("next"),
		PINGBACK("pingback"),
		PRECONNECT("preconnect"),
		PREFETCH("prefetch"),
		PRELOAD("preload"),
		PRERENDER("prerender"),
		PREV("prev"), // w3schools, MDN only
		SEARCH("search"),
		SHORTLINK("shortlink"), // MDN only
		/**
		 * @deprecated
		 */
		@Deprecated
		SIDEBAR("sidebar"), // MDN only
		STYLESHEET("stylesheet"),
		/**
		 * @deprecated
		 */
		@Deprecated
		UP("up"), // MDN only

		/**
		 * <p>
		 * To specify an icon for a single webpage or replace the website icon with a webpage-specific icon, add a link element to the webpage.
		 * </p>
		 * <p>
		 * See <a href="https://developer.apple.com/library/archive/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html">Configuring Web Applications</a>.
		 * </p>
		 */
		APPLE_TOUCH_ICON("apple-touch-icon"),

		/**
		 * <p>
		 * On iOS, similar to native applications, you can specify a launch screen image that is displayed while your web application launches.
		 * </p>
		 * <p>
		 * See <a href="https://developer.apple.com/library/archive/documentation/AppleApplications/Reference/SafariWebContent/ConfiguringWebApplications/ConfiguringWebApplications.html">Configuring Web Applications</a>.
		 * </p>
		 */
		APPLE_TOUCH_STARTUP_IMAGE("apple-touch-startup-image");

		private final String value;
		// TODO: Verify values by doctype

		private Rel(String value) {
			this.value = value;
		}

		@Override
		public String toString() {
			return value;
		}

		@Override
		public String get(Serialization serialization, Doctype doctype) {
			return value;
		}
	}

	private String rel;

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 */
	@Override
	public LINK<PC> rel(String rel) throws IOException {
		rel = Strings.trimNullIfEmpty(rel);
		if(rel != null) {
			if(this.rel != null) {
				throw new LocalizedIllegalStateException(
					Resources.PACKAGE_RESOURCES,
					"Document.duplicateAttribute",
					"link",
					"rel",
					Coercion.toString(this.rel),
					Coercion.toString(rel)
				);
			}
			this.rel = rel;
			if(this.itemprop != null) {
				throw new LocalizedIllegalStateException(RESOURCES, "relOrItemprop");
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
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 *
	 * @see #rel(java.lang.String)
	 */
	@Override
	public <Ex extends Throwable> LINK<PC> rel(Suppliers.String<Ex> rel) throws IOException, Ex {
		return Attributes.Enum.Rel.super.rel(rel);
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 *
	 * @see #rel(java.lang.String)
	 */
	@Override
	public LINK<PC> rel(Rel rel) throws IOException {
		return Attributes.Enum.Rel.super.rel(rel);
	}

	/**
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 * <ul>
	 * <li>See <a href="https://html.spec.whatwg.org/multipage/semantics.html#attr-link-rel">HTML Standard</a>.</li>
	 * <li>See <a href="https://www.w3schools.com/tags/att_link_rel.asp">HTML link rel Attribute</a>.</li>
	 * </ul>
	 *
	 * @see #rel(com.aoindustries.html.LINK.Rel)
	 */
	@Override
	public <Ex extends Throwable> LINK<PC> rel(IOSupplierE<? extends Rel, Ex> rel) throws IOException, Ex {
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
	public LINK<PC> type(String type) throws IOException {
		type = Strings.trimNullIfEmpty(type);
		this.type = type;
		if(
			type != null
			&& !(
				document.doctype == Doctype.HTML5
				&& rel != null
				&& rel.equalsIgnoreCase(Rel.STYLESHEET.toString())
				&& ContentType.CSS.equalsIgnoreCase(type)
			)
		) {
			document.out.write(" type=\"");
			encodeTextInXhtmlAttribute(type, document.out);
			document.out.write('"');
		}
		return this;
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * If the rel is {@link Rel#STYLESHEET}, a {@linkplain #type(java.lang.String) type}
	 * has not been written, and the {@link Doctype} is not {@link Doctype#HTML5},
	 * writes the default type {@link Type#TEXT_CSS} for backward compatibility.
	 * </p>
	 * <p>
	 * <a href="https://html.spec.whatwg.org/multipage/semantics.html#the-link-element">HTML Standard</a>:
	 * </p>
	 * <blockquote>
	 *   A link element must have either a rel attribute or an itemprop attribute, but not both.
	 * </blockquote>
	 *
	 * @return  The parent content model this element is within
	 */
	@Override
	public PC __() throws IOException {
		if(
			type == null
			&& document.doctype != Doctype.HTML5
			&& rel != null
			&& rel.equalsIgnoreCase(Rel.STYLESHEET.toString())
		) {
			document.out.write(" type=\"");
			document.out.write(ContentType.CSS);
			document.out.write('"');
		}
		super.__();
		if(rel == null && itemprop == null) {
			throw new LocalizedIllegalStateException(RESOURCES, "relOrItemprop");
		}
		return pc;
	}
}
