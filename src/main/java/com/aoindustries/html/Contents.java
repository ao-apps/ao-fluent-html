/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021  AO Industries, Inc.
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
import com.aoindustries.encoding.MediaWritable;
import com.aoindustries.io.function.IOConsumerE;
import com.aoindustries.io.function.IORunnableE;
import com.aoindustries.io.function.IOSupplierE;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Shape;
import java.io.IOException;

/**
 * Interfaces for each type of element when used as content.
 * <ul>
 * <li>See <a href="https://html.spec.whatwg.org/#content-models">3.2.5 Content models</a>.</li>
 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/Guide/HTML/Content_categories">Content categories - Developer guides</a>.</li>
 * </ul>
 *
 * @author  AO Industries, Inc.
 */
// Note: Document directly implements every interface here.
//       When adding an interface, also add to Document.
// TODO: Should these be renamed "Factories", with each named "AFactory", "ImgFactory", ...?
// TODO: Funnel annotation here, too?
public class Contents {

	/** Make no instances. */
	private Contents() {}

	/**
	 * See <a href="https://html.spec.whatwg.org/#the-root-element">4.1 The document element</a>.
	 */
	public static class Document {

		/** Make no instances. */
		private Document() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-html-element">4.1.1 The html element</a>.
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Html<Document> extends Content {
			// TODO
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#document-metadata">4.2 Document metadata</a>.
	 */
	public static class Metadata {

		/** Make no instances. */
		private Metadata() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-head-element">4.2.1 The head element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends HtmlContent<C>>
		public static interface Head<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-title-element">4.2.2 The title element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Title<C extends MetadataContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-base-element">4.2.3 The base element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Base<C extends MetadataContent<C>> extends Content {
			/**
			 * Opens a new base element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-base-element">4.2.3 The base element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Base<C> base() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Base<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Shortcut to create a base with href only.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-base-element">4.2.3 The base element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C base__(String href) throws IOException {
				return base().href(href).__();
			}
			// TODO: IOSupplierE version like A? (review others, too)
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-link-element">4.2.4 The link element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link">&lt;link&gt; - HTML: Hypertext Markup Language</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Link<C extends UnionContent.Metadata_Phrasing<C>> extends Content {
			/**
			 * Opens a new link element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-link-element">4.2.4 The link element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link">&lt;link&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.</li>
			 * </ul>
			 */
			// TODO: Variants of Link by Rel, with per-implementation attributes like Input?
			default com.aoindustries.html.Link<C> link() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Link<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Opens a new link element with the given rel attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-link-element">4.2.4 The link element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/link">&lt;link&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.</li>
			 * </ul>
			 *
			 * @see #link()
			 * @see com.aoindustries.html.Link#rel(java.lang.Enum)
			 */
			default com.aoindustries.html.Link<C> link(com.aoindustries.html.Link.Rel rel) throws IOException {
				return link().rel(rel);
			}

			// No link__(), since either rel or itemprop is required
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Meta<C extends UnionContent.Metadata_Phrasing<C>> extends Content {
			/**
			 * Opens a new meta element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Meta<C> meta() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Meta<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Opens a new meta element with the given name attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
			 * </ul>
			 *
			 * @see #meta()
			 * @see com.aoindustries.html.Meta#name(java.lang.Enum)
			 */
			default com.aoindustries.html.Meta<C> meta(com.aoindustries.html.Meta.Name name) throws IOException {
				return meta().name(name);
			}

			/**
			 * Opens a new meta element with the given name http-equiv.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
			 * </ul>
			 *
			 * @see #meta()
			 * @see com.aoindustries.html.Meta#httpEquiv(java.lang.Enum)
			 */
			default com.aoindustries.html.Meta<C> meta(com.aoindustries.html.Meta.HttpEquiv httpEquiv) throws IOException {
				return meta().httpEquiv(httpEquiv);
			}

			/**
			 * Opens a new meta element with the given charset attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
			 * </ul>
			 *
			 * @see #meta()
			 * @see com.aoindustries.html.Meta#charset(java.lang.Enum)
			 */
			default com.aoindustries.html.Meta<C> meta(Attributes.Enum.Charset.Value charset) throws IOException {
				return meta().charset(charset);
			}

			// No meta__(), since either name, http-equiv, or itemprop is required (TODO: confirm itemprop-only metas?)
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Style<C extends MetadataContent<C>> extends Content {
			/**
			 * Opens a new style element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * </ul>
			 *
			 * @see Doctype#styleType(java.lang.Appendable)
			 */
			default com.aoindustries.html.Style<C> style() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Style<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Opens a new style element of the given type.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Style<C> style(String type) throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Style<>(getDocument(), pc, type).writeOpen();
			}

			/**
			 * Opens a new style element of the given type.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> com.aoindustries.html.Style<C> style(Suppliers.String<Ex> type) throws IOException, Ex {
				return style((type == null) ? null : type.get());
			}

			/**
			 * Opens a new style element of the given type.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Style<C> style(com.aoindustries.html.Style.Type type) throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Style<>(getDocument(), pc, type).writeOpen();
			}

			/**
			 * Opens a new style element of the given type.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> com.aoindustries.html.Style<C> style(IOSupplierE<? extends com.aoindustries.html.Style.Type, Ex> type) throws IOException, Ex {
				return style((type == null) ? null : type.get());
			}

			// TODO: style__() - go directly to out, since no attributes? Lambda versions, too

			// TODO: A version called HtmlWriter that extends ChainWriter to avoid all this passing of appendables?
			// TODO: html.input.style.type().print("...").__().  How far do we take this?
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#sections">4.3 Sections</a>.
	 */
	public static class Sections {

		/** Make no instances. */
		private Sections() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-body-element">4.3.1 The body element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends HtmlContent<C>>
		public static interface Body<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Article<C extends SectioningContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-section-element">4.3.3 The section element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Section<C extends SectioningContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-nav-element">4.3.4 The nav element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Nav<C extends SectioningContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-aside-element">4.3.5 The aside element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Aside<C extends SectioningContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface H1<C extends HeadingContent<C>> extends Content {

			/**
			 * Opens a new h1 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			default com.aoindustries.html.H1<C> h1() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.H1<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an h1 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h1__(IORunnableE<Ex> h1) throws IOException, Ex {
				return h1().__(h1);
			}

			/**
			 * Creates an h1 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h1__(IOConsumerE<? super com.aoindustries.html.H1.H1Content<C>, Ex> h1) throws IOException, Ex {
				return h1().__(h1);
			}

			/**
			 * Creates an h1 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h1__(Object text) throws IOException {
				return h1().__(text);
			}

			/**
			 * Creates an empty h1 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h1__() throws IOException {
				return h1().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface H2<C extends HeadingContent<C>> extends Content {

			/**
			 * Opens a new h2 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			default com.aoindustries.html.H2<C> h2() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.H2<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an h2 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h2__(IORunnableE<Ex> h2) throws IOException, Ex {
				return h2().__(h2);
			}

			/**
			 * Creates an h2 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h2__(IOConsumerE<? super com.aoindustries.html.H2.H2Content<C>, Ex> h2) throws IOException, Ex {
				return h2().__(h2);
			}

			/**
			 * Creates an h2 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h2__(Object text) throws IOException {
				return h2().__(text);
			}

			/**
			 * Creates an empty h2 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h2__() throws IOException {
				return h2().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface H3<C extends HeadingContent<C>> extends Content {

			/**
			 * Opens a new h3 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			default com.aoindustries.html.H3<C> h3() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.H3<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an h3 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h3__(IORunnableE<Ex> h3) throws IOException, Ex {
				return h3().__(h3);
			}

			/**
			 * Creates an h3 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h3__(IOConsumerE<? super com.aoindustries.html.H3.H3Content<C>, Ex> h3) throws IOException, Ex {
				return h3().__(h3);
			}

			/**
			 * Creates an h3 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h3__(Object text) throws IOException {
				return h3().__(text);
			}

			/**
			 * Creates an empty h3 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h3__() throws IOException {
				return h3().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface H4<C extends HeadingContent<C>> extends Content {

			/**
			 * Opens a new h4 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			default com.aoindustries.html.H4<C> h4() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.H4<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an h4 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default<Ex extends Throwable> C h4__(IORunnableE<Ex> h4) throws IOException, Ex {
				return h4().__(h4);
			}

			/**
			 * Creates an h4 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h4__(IOConsumerE<? super com.aoindustries.html.H4.H4Content<C>, Ex> h4) throws IOException, Ex {
				return h4().__(h4);
			}

			/**
			 * Creates an h4 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h4__(Object text) throws IOException {
				return h4().__(text);
			}

			/**
			 * Creates an empty h4 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h4__() throws IOException {
				return h4().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface H5<C extends HeadingContent<C>> extends Content {

			/**
			 * Opens a new h5 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			default com.aoindustries.html.H5<C> h5() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.H5<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an h5 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h5__(IORunnableE<Ex> h5) throws IOException, Ex {
				return h5().__(h5);
			}

			/**
			 * Creates an h5 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h5__(IOConsumerE<? super com.aoindustries.html.H5.H5Content<C>, Ex> h5) throws IOException, Ex {
				return h5().__(h5);
			}

			/**
			 * Creates an h5 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h5__(Object text) throws IOException {
				return h5().__(text);
			}

			/**
			 * Creates an empty h5 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h5__() throws IOException {
				return h5().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface H6<C extends HeadingContent<C>> extends Content {

			/**
			 * Opens a new h6 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			default com.aoindustries.html.H6<C> h6() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.H6<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an h6 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h6__(IORunnableE<Ex> h6) throws IOException, Ex {
				return h6().__(h6);
			}

			/**
			 * Creates an h6 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C h6__(IOConsumerE<? super com.aoindustries.html.H6.H6Content<C>, Ex> h6) throws IOException, Ex {
				return h6().__(h6);
			}

			/**
			 * Creates an h6 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h6__(Object text) throws IOException {
				return h6().__(text);
			}

			/**
			 * Creates an empty h6 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C h6__() throws IOException {
				return h6().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-hgroup-element">4.3.7 The hgroup element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Hgroup<C extends HeadingContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-header-element">4.3.8 The header element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Header<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-footer-element">4.3.9 The footer element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Footer<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-address-element">4.3.10 The address element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Address<C extends PalpableContent<C>> extends Content {
			// TODO
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#grouping-content">4.4 Grouping content</a>.
	 */
	public static class Grouping {

		/** Make no instances. */
		private Grouping() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface P<C extends PalpableContent<C>> extends Content {

			/**
			 * Opens a new p element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 */
			default com.aoindustries.html.P<C> p() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.P<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a p element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C p__(IORunnableE<Ex> p) throws IOException, Ex {
				return p().__(p);
			}

			/**
			 * Creates a p element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C p__(IOConsumerE<? super com.aoindustries.html.P.PContent<C>, Ex> p) throws IOException, Ex {
				return p().__(p);
			}

			/**
			 * Creates a p element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C p__(Object text) throws IOException {
				return p().__(text);
			}

			/**
			 * Creates an empty p element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C p__() throws IOException {
				return p().__();
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-hr-element">4.4.2 The hr element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Hr<C extends FlowContent<C>> extends Content {
			/**
			 * Opens a new hr element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-hr-element">4.4.2 The hr element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Hr<C> hr() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Hr<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an empty hr element with no attributes.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-hr-element">4.4.2 The hr element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C hr__() throws IOException {
				return hr().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-pre-element">4.4.3 The pre element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Pre<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Blockquote<C extends PalpableContent<C>> extends Content {

			/**
			 * Opens a new blockquote element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Blockquote<C> blockquote() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Blockquote<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a blockquote element with no attributes and the given body.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C blockquote__(IORunnableE<Ex> blockquote) throws IOException, Ex {
				return blockquote().__(blockquote);
			}

			/**
			 * Creates a blockquote element with no attributes and the given body.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C blockquote__(IOConsumerE<? super com.aoindustries.html.Blockquote.BlockquoteContent<C>, Ex> blockquote) throws IOException, Ex {
				return blockquote().__(blockquote);
			}

			/**
			 * Creates a blockquote element with no attributes and a text body.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C blockquote__(Object text) throws IOException {
				return blockquote().__(text);
			}

			/**
			 * Creates an empty blockquote element with no attributes.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/blockquote">&lt;blockquote&gt;: The Block Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C blockquote__() throws IOException {
				return blockquote().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-ol-element">4.4.5 The ol element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Ol<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-ul-element">4.4.6 The ul element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Ul<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-menu-element">4.4.7 The menu element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Menu<C extends InteractiveContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-li-element">4.4.8 The li element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends ListContent<C>>
		public static interface Li<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dl-element">4.4.9 The dl element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Dl<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dt-element">4.4.10 The dt element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends TODO<C>>
		public static interface Dt<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dd-element">4.4.11 The dd element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends TODO<C>>
		public static interface Dd<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-figure-element">4.4.12 The figure element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Figure<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-figcaption-element">4.4.13 The figcaption element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends FigcaptionContent<C>> (where FigcaptionContent extends FlowContent + Figcaption)
		public static interface Figcaption<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-main-element">4.4.14 The main element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Main<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-div-element">4.4.15 The div element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Div<C extends PalpableContent<C>> extends Content {
			// TODO
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#text-level-semantics">4.5 Text-level semantics</a>.
	 */
	public static class Text {

		/** Make no instances. */
		private Text() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface A<C extends UnionContent.Interactive_Phrasing<C>> extends Content {
			/**
			 * Opens a new a element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 */
			default com.aoindustries.html.A<C> a() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.A<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Opens a new a element with the given href attribute.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 */
			default com.aoindustries.html.A<C> a(String href) throws IOException {
				return a().href(href);
			}

			/**
			 * Opens a new a element with the given href attribute.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 */
			default <Ex extends Throwable> com.aoindustries.html.A<C> a(IOSupplierE<? extends java.lang.String, Ex> href) throws IOException, Ex {
				return a().href(href);
			}

			/**
			 * Creates an a element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C a__(IORunnableE<Ex> a) throws IOException, Ex {
				return a().__(a);
			}

			/**
			 * Creates an a element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			// TODO: How to limit content to not have interactive elements?
			default <Ex extends Throwable> C a__(IOConsumerE<? super C, Ex> a) throws IOException, Ex {
				return a().__(a);
			}

			/**
			 * Creates an a element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C a__(Object text) throws IOException {
				return a().__(text);
			}

			/**
			 * Creates an empty a element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C a__() throws IOException {
				return a().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-em-element">4.5.2 The em element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Em<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-strong-element">4.5.3 The strong element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Strong<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-small-element">4.5.4 The small element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Small<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-s-element">4.5.5 The s element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface S<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-cite-element">4.5.6 The cite element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Cite<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-q-element">4.5.7 The q element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element - HTML: HyperText Markup Language</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Q<C extends UnionContent.Palpable_Phrasing<C>> extends Content {

			/**
			 * Opens a new q element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-q-element">4.5.7 The q element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Q<C> q() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Q<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a q element with no attributes and the given body.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-q-element">4.5.7 The q element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C q__(IORunnableE<Ex> q) throws IOException, Ex {
				return q().__(q);
			}

			/**
			 * Creates a q element with no attributes and the given body.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-q-element">4.5.7 The q element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C q__(IOConsumerE<? super com.aoindustries.html.Q.QContent<C>, Ex> q) throws IOException, Ex {
				return q().__(q);
			}

			/**
			 * Creates a q element with no attributes and a text body.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-q-element">4.5.7 The q element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C q__(Object text) throws IOException {
				return q().__(text);
			}

			/**
			 * Creates an empty q element with no attributes.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-q-element">4.5.7 The q element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/q">&lt;q&gt;: The Inline Quotation element - HTML: HyperText Markup Language</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C q__() throws IOException {
				return q().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dfn-element">4.5.8 The dfn element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Dfn<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-abbr-element">4.5.9 The abbr element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Abbr<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-ruby-element">4.5.10 The ruby element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Ruby<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-rt-element">4.5.11 The rt element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends RubyContent<C>>
		public static interface Rt<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-rp-element">4.5.12 The rp element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends RubyContent<C>>
		public static interface Rp<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-data-element">4.5.13 The data element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Data<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-time-element">4.5.14 The time element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Time<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-code-element">4.5.15 The code element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Code<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-var-element">4.5.16 The var element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Var<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-samp-element">4.5.17 The samp element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Samp<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-kbd-element">4.5.18 The kbd element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Kbd<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Sub<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Sup<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface I<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			/**
			 * Opens a new i element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 */
			default com.aoindustries.html.I<C> i() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.I<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an i element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C i__(IORunnableE<Ex> i) throws IOException, Ex {
				return i().__(i);
			}

			/**
			 * Creates an i element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C i__(IOConsumerE<? super com.aoindustries.html.I.IContent<C>, Ex> i) throws IOException, Ex {
				return i().__(i);
			}

			/**
			 * Creates an i element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C i__(Object text) throws IOException {
				return i().__(text);
			}

			/**
			 * Creates an empty i element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C i__() throws IOException {
				return i().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface B<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			/**
			 * Opens a new b element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 */
			default com.aoindustries.html.B<C> b() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.B<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a b element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C b__(IORunnableE<Ex> b) throws IOException, Ex {
				return b().__(b);
			}

			/**
			 * Creates a b element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C b__(IOConsumerE<? super com.aoindustries.html.B.BContent<C>, Ex> b) throws IOException, Ex {
				return b().__(b);
			}

			/**
			 * Creates a b element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C b__(Object text) throws IOException {
				return b().__(text);
			}

			/**
			 * Creates an empty b element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C b__() throws IOException {
				return b().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface U<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			/**
			 * Opens a new u element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 */
			default com.aoindustries.html.U<C> u() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.U<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a u element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C u__(IORunnableE<Ex> u) throws IOException, Ex {
				return u().__(u);
			}

			/**
			 * Creates a u element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C u__(IOConsumerE<? super com.aoindustries.html.U.UContent<C>, Ex> u) throws IOException, Ex {
				return u().__(u);
			}

			/**
			 * Creates a u element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C u__(Object text) throws IOException {
				return u().__(text);
			}

			/**
			 * Creates an empty u element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C u__() throws IOException {
				return u().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-mark-element">4.5.23 The mark element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Mark<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-bdi-element">4.5.24 The bdi element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Bdi<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-bdo-element">4.5.25 The bdo element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Bdo<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Span<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-br-element">4.5.27 The br element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Br<C extends PhrasingContent<C>> extends Content {
			/**
			 * Opens a new br element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-br-element">4.5.27 The br element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Br<C> br() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Br<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a br element with no attributes.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-br-element">4.5.27 The br element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C br__() throws IOException {
				return br().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-wbr-element">4.5.28 The wbr element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Wbr<C extends PhrasingContent<C>> extends Content {
			// TODO
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#edits">4.7 Edits</a>.
	 */
	public static class Edits {

		/** Make no instances. */
		private Edits() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-ins-element">4.7.1 The ins element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Ins<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-del-element">4.7.2 The del element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Del<C extends PhrasingContent<C>> extends Content {
			// TODO
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#embedded-content">4.8 Embedded content</a>.
	 */
	public static class Embedded {

		/** Make no instances. */
		private Embedded() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-picture-element">4.8.1 The picture element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Picture<C extends EmbeddedContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-source-element">4.8.2 The source element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends TODO<C>>
		public static interface Source<C extends Content> extends Content {
			// TODO
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-img-element">4.8.3 The img element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Img<C extends UnionContent.Embedded_Interactive<C>> extends Content {
			/**
			 * Opens a new img element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-img-element">4.8.3 The img element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Img<C> img() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Img<>(getDocument(), pc).writeOpen();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-iframe-element">4.8.5 The iframe element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Iframe<C extends UnionContent.Embedded_Interactive<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-embed-element">4.8.6 The embed element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Embed<C extends UnionContent.Embedded_Interactive<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-object-element">4.8.7 The object element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Object<C extends UnionContent.Embedded_Interactive<C>> extends Content {
			// TODO
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-param-element">4.8.8 The param element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		// TODO: <C extends ObjectContent<C>>
		public static interface Param<C extends Content> extends Content {
			/**
			 * Opens a new param element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-param-element">4.8.8 The param element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Param<C> param() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Param<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a param element with the given name and value.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-param-element">4.8.8 The param element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C param__(java.lang.Object name, java.lang.Object value) throws IOException {
				return param().name(name).value(value).__();
			}

			// TODO: More types like supported by ao-taglib (ParamsTag.java), including collection types, as "params__"?
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-video-element">4.8.9 The video element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Video<C extends UnionContent.Embedded_Interactive<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-audio-element">4.8.10 The audio element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Audio<C extends UnionContent.Embedded_Interactive<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-track-element">4.8.11 The track element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends MediaContent<C>>
		public static interface Track<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Map<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-area-element">4.8.14 The area element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Area<C extends PhrasingContent<C>> extends Content {
			/**
			 * Opens a new area element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Area<C> area() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Area<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Area<C> area(Rectangle rect) throws IOException {
				return area().shape(com.aoindustries.html.Area.Shape.RECT).coords(rect);
			}

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> com.aoindustries.html.Area<C> area(Suppliers.Rectangle<Ex> rect) throws IOException, Ex {
				return area().shape(com.aoindustries.html.Area.Shape.RECT).coords(rect);
			}

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Area<C> area(Circle circle) throws IOException {
				return area().shape(com.aoindustries.html.Area.Shape.CIRCLE).coords(circle);
			}

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> com.aoindustries.html.Area<C> area(Suppliers.Circle<Ex> circle) throws IOException, Ex {
				return area().shape(com.aoindustries.html.Area.Shape.CIRCLE).coords(circle);
			}

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Area<C> area(Polygon poly) throws IOException {
				return area().shape(com.aoindustries.html.Area.Shape.POLY).coords(poly);
			}

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> com.aoindustries.html.Area<C> area(Suppliers.Polygon<Ex> poly) throws IOException, Ex {
				return area().shape(com.aoindustries.html.Area.Shape.POLY).coords(poly);
			}

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Area<C> area(Shape shape) throws IOException {
				if(shape == null) return area();
				if(shape instanceof Rectangle) return area((Rectangle)shape);
				if(shape instanceof Circle) return area((Circle)shape);
				if(shape instanceof Polygon) return area((Polygon)shape);
				// Pass-through in a way that must result in an exception for the unknown type instead of duplicating long exception message here
				area().coords(shape);
				throw new AssertionError("IllegalArgumentException must have been thrown by coords for invalid Shape");
			}

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> com.aoindustries.html.Area<C> area(Suppliers.Shape<Ex> shape) throws IOException, Ex {
				return area(shape == null ? null : shape.get());
			}
		}

		// TODO: MathML math: 4.8.16 MathML: https://html.spec.whatwg.org/#mathml

		// TODO: SVG svg: 4.8.17 SVG: https://html.spec.whatwg.org/#svg-0
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#tables">4.9 Tabular data</a>.
	 */
	public static class Tabular {

		/** Make no instances. */
		private Tabular() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-table-element">4.9.1 The table element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Table<C extends PalpableContent<C>> extends Content {

			/**
			 * Opens a new table element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-table-element">4.9.1 The table element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Table<C> table() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Table<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a table element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-table-element">4.9.1 The table element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C table__(IORunnableE<Ex> table) throws IOException, Ex {
				return table().__(table);
			}

			/**
			 * Creates a table element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-table-element">4.9.1 The table element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C table__(IOConsumerE<? super com.aoindustries.html.Table.TableContent<C>, Ex> table) throws IOException, Ex {
				return table().__(table);
			}

			/**
			 * Creates an empty table element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-table-element">4.9.1 The table element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C table__() throws IOException {
				return table().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-caption-element">4.9.2 The caption element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Caption<C extends TableContent<C>> extends Content {

			/**
			 * Opens a new caption element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-caption-element">4.9.2 The caption element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Caption<C> caption() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Caption<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a caption element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-caption-element">4.9.2 The caption element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C caption__(IORunnableE<Ex> caption) throws IOException, Ex {
				return caption().__(caption);
			}

			/**
			 * Creates a caption element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-caption-element">4.9.2 The caption element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C caption__(IOConsumerE<? super com.aoindustries.html.Caption.CaptionContent<C>, Ex> caption) throws IOException, Ex {
				return caption().__(caption);
			}

			/**
			 * Creates a caption element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-caption-element">4.9.2 The caption element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C caption__(Object text) throws IOException {
				return caption().__(text);
			}

			/**
			 * Creates an empty caption element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-caption-element">4.9.2 The caption element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C caption__() throws IOException {
				return caption().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-colgroup-element">4.9.3 The colgroup element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Colgroup<C extends TableContent<C>> extends Content {

			/**
			 * Opens a new colgroup element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-colgroup-element">4.9.3 The colgroup element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Colgroup<C> colgroup() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Colgroup<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a colgroup element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-colgroup-element">4.9.3 The colgroup element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C colgroup__(IORunnableE<Ex> colgroup) throws IOException, Ex {
				return colgroup().__(colgroup);
			}

			/**
			 * Creates a colgroup element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-colgroup-element">4.9.3 The colgroup element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C colgroup__(IOConsumerE<? super com.aoindustries.html.Colgroup.ColgroupContent<C>, Ex> colgroup) throws IOException, Ex {
				return colgroup().__(colgroup);
			}

			/**
			 * Creates an empty colgroup element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-colgroup-element">4.9.3 The colgroup element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C colgroup__() throws IOException {
				return colgroup().__();
			}
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-col-element">4.9.4 The col element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/col">&lt;col&gt; - HTML: Hypertext Markup Language</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_col.asp">HTML col tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Col<C extends ColgroupContent<C>> extends Content {
			/**
			 * Opens a new col element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-col-element">4.9.4 The col element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/col">&lt;col&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_col.asp">HTML col tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Col<C> col() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Col<>(getDocument(), pc).writeOpen();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Tbody<C extends TableContent<C>> extends Content {

			/**
			 * Opens a new tbody element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Tbody<C> tbody() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Tbody<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a tbody element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C tbody__(IORunnableE<Ex> tbody) throws IOException, Ex {
				return tbody().__(tbody);
			}

			/**
			 * Creates a tbody element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C tbody__(IOConsumerE<? super com.aoindustries.html.Tbody.TbodyContent<C>, Ex> tbody) throws IOException, Ex {
				return tbody().__(tbody);
			}

			/**
			 * Creates an empty tbody element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C tbody__() throws IOException {
				return tbody().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Thead<C extends TableContent<C>> extends Content {

			/**
			 * Opens a new thead element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Thead<C> thead() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Thead<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a thead element with no attributes and the given head.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C thead__(IORunnableE<Ex> thead) throws IOException, Ex {
				return thead().__(thead);
			}

			/**
			 * Creates a thead element with no attributes and the given head.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C thead__(IOConsumerE<? super com.aoindustries.html.Thead.TheadContent<C>, Ex> thead) throws IOException, Ex {
				return thead().__(thead);
			}

			/**
			 * Creates an empty thead element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C thead__() throws IOException {
				return thead().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Tfoot<C extends TableContent<C>> extends Content {

			/**
			 * Opens a new tfoot element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Tfoot<C> tfoot() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Tfoot<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a tfoot element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C tfoot__(IORunnableE<Ex> tfoot) throws IOException, Ex {
				return tfoot().__(tfoot);
			}

			/**
			 * Creates a tfoot element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C tfoot__(IOConsumerE<? super com.aoindustries.html.Tfoot.TfootContent<C>, Ex> tfoot) throws IOException, Ex {
				return tfoot().__(tfoot);
			}

			/**
			 * Creates an empty tfoot element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C tfoot__() throws IOException {
				return tfoot().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-tr-element">4.9.8 The tr element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Tr<C extends TbodyTheadTfootContent<C>> extends Content {

			/**
			 * Opens a new tr element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tr-element">4.9.8 The tr element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Tr<C> tr() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Tr<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a tr element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tr-element">4.9.8 The tr element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C tr__(IORunnableE<Ex> tr) throws IOException, Ex {
				return tr().__(tr);
			}

			/**
			 * Creates a tr element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tr-element">4.9.8 The tr element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C tr__(IOConsumerE<? super com.aoindustries.html.Tr.TrContent<C>, Ex> tr) throws IOException, Ex {
				return tr().__(tr);
			}

			/**
			 * Creates an empty tr element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-tr-element">4.9.8 The tr element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C tr__() throws IOException {
				return tr().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-td-element">4.9.9 The td element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Td<C extends TrContent<C>> extends Content {

			/**
			 * Opens a new td element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-td-element">4.9.9 The td element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Td<C> td() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Td<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a td element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-td-element">4.9.9 The td element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C td__(IORunnableE<Ex> td) throws IOException, Ex {
				return td().__(td);
			}

			/**
			 * Creates a td element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-td-element">4.9.9 The td element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C td__(IOConsumerE<? super com.aoindustries.html.Td.TdContent<C>, Ex> td) throws IOException, Ex {
				return td().__(td);
			}

			/**
			 * Creates a td element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-td-element">4.9.9 The td element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C td__(Object text) throws IOException {
				return td().__(text);
			}

			/**
			 * Creates an empty td element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-td-element">4.9.9 The td element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C td__() throws IOException {
				return td().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-th-element">4.9.10 The th element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Th<C extends TrContent<C>> extends Content {

			/**
			 * Opens a new th element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-th-element">4.9.10 The th element</a>.
			 * </p>
			 */
			default com.aoindustries.html.Th<C> th() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Th<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates a th element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-th-element">4.9.10 The th element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C th__(IORunnableE<Ex> th) throws IOException, Ex {
				return th().__(th);
			}

			/**
			 * Creates a th element with no attributes and the given foot.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-th-element">4.9.10 The th element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default <Ex extends Throwable> C th__(IOConsumerE<? super com.aoindustries.html.Th.ThContent<C>, Ex> th) throws IOException, Ex {
				return th().__(th);
			}

			/**
			 * Creates a th element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-th-element">4.9.10 The th element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C th__(Object text) throws IOException {
				return th().__(text);
			}

			/**
			 * Creates an empty th element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-th-element">4.9.10 The th element</a>.
			 * </p>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C th__() throws IOException {
				return th().__();
			}
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#forms">4.10 Forms</a>.
	 */
	public static class Forms {

		/** Make no instances. */
		private Forms() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-form-element">4.10.3 The form element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Form<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-label-element">4.10.4 The label element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Label<C extends UnionContent.Interactive_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
		 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Input<C extends UnionContent.Interactive_Phrasing<C>> extends Content {

			/**
			 * Specialized input implementations.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
			 * </ul>
			 *
			 * @param  <C>  This content model, which will be the parent content model of child elements
			 */
			public static class Type<C extends UnionContent.Interactive_Phrasing<C>> {

				private final com.aoindustries.html.Document document;
				private final C pc;

				public Type(com.aoindustries.html.Document document, C pc) {
					this.document = document;
					this.pc = pc;
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Dynamic<C> dynamic() throws IOException {
					return new com.aoindustries.html.Input.Dynamic<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Dynamic<C> dynamic(String type) throws IOException {
					return new com.aoindustries.html.Input.Dynamic<>(document, pc, type).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.</li>
				 * </ul>
				 */
				// TODO: Move these type Input.type only?
				public <Ex extends Throwable> com.aoindustries.html.Input.Dynamic<C> dynamic(Suppliers.String<Ex> type) throws IOException, Ex {
					return dynamic((type == null) ? null : type.get());
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Dynamic<C> dynamic(com.aoindustries.html.Input.Dynamic.Type type) throws IOException {
					return new com.aoindustries.html.Input.Dynamic<>(document, pc, type).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.</li>
				 * </ul>
				 */
				// TODO: Move these type Input.type only?
				public <Ex extends Throwable> com.aoindustries.html.Input.Dynamic<C> dynamic(IOSupplierE<? extends com.aoindustries.html.Input.Dynamic.Type, Ex> type) throws IOException, Ex {
					return dynamic((type == null) ? null : type.get());
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_button.asp">HTML input type="button"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Button<C> button() throws IOException {
					return new com.aoindustries.html.Input.Button<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_checkbox.asp">HTML input type="checkbox"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Checkbox<C> checkbox() throws IOException {
					return new com.aoindustries.html.Input.Checkbox<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_color.asp">HTML input type="color"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Color<C> color() throws IOException {
					return new com.aoindustries.html.Input.Color<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_date.asp">HTML input type="date"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Date<C> date() throws IOException {
					return new com.aoindustries.html.Input.Date<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_datetime-local.asp">HTML input type="datetime-local"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.DatetimeLocal<C> datetimeLocal() throws IOException {
					return new com.aoindustries.html.Input.DatetimeLocal<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_email.asp">HTML input type="email"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Email<C> email() throws IOException {
					return new com.aoindustries.html.Input.Email<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_file.asp">HTML input type="file"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.File<C> file() throws IOException {
					return new com.aoindustries.html.Input.File<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_hidden.asp">HTML input type="hidden"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Hidden<C> hidden() throws IOException {
					return new com.aoindustries.html.Input.Hidden<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_image.asp">HTML input type="image"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Image<C> image() throws IOException {
					return new com.aoindustries.html.Input.Image<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_month.asp">HTML input type="month"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Month<C> month() throws IOException {
					return new com.aoindustries.html.Input.Month<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_number.asp">HTML input type="number"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Number<C> number() throws IOException {
					return new com.aoindustries.html.Input.Number<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_password.asp">HTML input type="password"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Password<C> password() throws IOException {
					return new com.aoindustries.html.Input.Password<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_radio.asp">HTML input type="radio"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Radio<C> radio() throws IOException {
					return new com.aoindustries.html.Input.Radio<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_range.asp">HTML input type="range"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Range<C> range() throws IOException {
					return new com.aoindustries.html.Input.Range<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_reset.asp">HTML input type="reset"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Reset<C> reset() throws IOException {
					return new com.aoindustries.html.Input.Reset<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_search.asp">HTML input type="search"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Search<C> search() throws IOException {
					return new com.aoindustries.html.Input.Search<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Submit<C> submit() throws IOException {
					return new com.aoindustries.html.Input.Submit<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.</li>
				 * </ul>
				 *
				 * @return  This content model, which will be the parent content model of child elements
				 */
				public C submit__(Object value) throws IOException {
					return submit().value(value).__();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.</li>
				 * </ul>
				 *
				 * @return  This content model, which will be the parent content model of child elements
				 */
				public <Ex extends Throwable> C submit__(IOSupplierE<?, Ex> value) throws IOException, Ex {
					return submit().value(value).__();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.</li>
				 * </ul>
				 *
				 * @return  This content model, which will be the parent content model of child elements
				 */
				public <Ex extends Throwable> C submit__(MediaWritable<Ex> value) throws IOException, Ex {
					return submit().value(value).__();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_tel.asp">HTML input type="tel"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Tel<C> tel() throws IOException {
					return new com.aoindustries.html.Input.Tel<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_text.asp">HTML input type="text"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Text<C> text() throws IOException {
					return new com.aoindustries.html.Input.Text<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_time.asp">HTML input type="time"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Time<C> time() throws IOException {
					return new com.aoindustries.html.Input.Time<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_url.asp">HTML input type="url"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Url<C> url() throws IOException {
					return new com.aoindustries.html.Input.Url<>(document, pc).writeOpen();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
				 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_week.asp">HTML input type="week"</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Week<C> week() throws IOException {
					return new com.aoindustries.html.Input.Week<>(document, pc).writeOpen();
				}
			}

			/**
			 * Specialized input implementations.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
			 * </ul>
			 */
			default Type<C> input() {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new Type<>(getDocument(), pc);
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-button-element">4.10.6 The button element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Button<C extends UnionContent.Interactive_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Select<C extends UnionContent.Interactive_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-datalist-element">4.10.8 The datalist element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Datalist<C extends PhrasingContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-optgroup-element">4.10.9 The optgroup element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends SelectContent<C>>
		public static interface Optgroup<C extends Content> extends Content {
			// TODO
		}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-option-element">4.10.10 The option element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		// TODO: <C extends TODO<C>>
		public static interface Option<C extends Content> extends Content {
			/**
			 * Opens a new option element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-option-element">4.10.10 The option element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Option<C> option() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Option<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Creates an empty option element with no attributes.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-option-element">4.10.10 The option element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.</li>
			 * </ul>
			 *
			 * @return  This content model, which will be the parent content model of child elements
			 */
			default C option__() throws IOException {
				return option().__();
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-textarea-element">4.10.11 The textarea element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Textarea<C extends UnionContent.Interactive_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-output-element">4.10.12 The output element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Output<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-progress-element">4.10.13 The progress element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Progress<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-meter-element">4.10.14 The meter element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Meter<C extends UnionContent.Palpable_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-fieldset-element">4.10.15 The fieldset element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Fieldset<C extends PalpableContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-legend-element">4.10.16 The legend element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends FieldsetContent<C>>
		public static interface Legend<C extends Content> extends Content {
			// TODO
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#interactive-elements">4.11 Interactive elements</a>.
	 */
	public static class Interactive {

		/** Make no instances. */
		private Interactive() {}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-details-element">4.11.1 The details element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Details<C extends InteractiveContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-summary-element">4.11.2 The summary element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		// TODO: <C extends DetailsContent<C>>
		public static interface Summary<C extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dialog-element">4.11.4 The dialog element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Dialog<C extends FlowContent<C>> extends Content {
			// TODO
		}
	}

	/**
	 * See <a href="https://html.spec.whatwg.org/#scripting-3">4.12 Scripting</a>.
	 */
	public static class Scripting {

		/** Make no instances. */
		private Scripting() {}

		/**
		 * <ul>
		 * <li>See <a href="https://html.spec.whatwg.org/#the-script-element">4.12.1 The script element</a>.</li>
		 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
		 * </ul>
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		public static interface Script<C extends ScriptSupportingContent<C>> extends Content {
			/**
			 * Opens a new script element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-script-element">4.12.1 The script element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * </ul>
			 *
			 * @see Doctype#scriptType(java.lang.Appendable)
			 */
			default com.aoindustries.html.Script<C> script() throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Script<>(getDocument(), pc).writeOpen();
			}

			/**
			 * Opens a new script element of the given type.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-script-element">4.12.1 The script element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Script<C> script(String type) throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Script<>(getDocument(), pc, type).writeOpen();
			}

			/**
			 * Opens a new script element of the given type.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-script-element">4.12.1 The script element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> com.aoindustries.html.Script<C> script(Suppliers.String<Ex> type) throws IOException, Ex {
				return script((type == null) ? null : type.get());
			}

			/**
			 * Opens a new script element of the given type.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-script-element">4.12.1 The script element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
			 * </ul>
			 */
			default com.aoindustries.html.Script<C> script(com.aoindustries.html.Script.Type type) throws IOException {
				@SuppressWarnings("unchecked") C pc = (C)this;
				return new com.aoindustries.html.Script<>(getDocument(), pc, type).writeOpen();
			}

			/**
			 * Opens a new script element of the given type.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/#the-script-element">4.12.1 The script element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
			 * </ul>
			 */
			default <Ex extends Throwable> com.aoindustries.html.Script<C> script(IOSupplierE<? extends com.aoindustries.html.Script.Type, Ex> type) throws IOException, Ex {
				return script((type == null) ? null : type.get());
			}
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-noscript-element">4.12.2 The noscript element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Noscript<C extends UnionContent.Metadata_Phrasing<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-template-element">4.12.3 The template element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Template<C extends UnionContent.Colgroup_ScriptSupporting<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-slot-element">4.12.4 The slot element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Slot<C extends PhrasingContent<C>> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-canvas-element">4.12.5 The canvas element</a>.
		 *
		 * @param  <C>  This content model, which will be the parent content model of child elements
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Canvas<C extends UnionContent.Embedded_Palpable_Phrasing<C>> extends Content {
			// TODO
		}
	}

	// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
}
