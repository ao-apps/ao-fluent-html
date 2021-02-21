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
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Html<PC extends Content> extends Content {
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
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Head<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-title-element">4.2.2 The title element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Title<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-base-element">4.2.3 The base element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Base<PC extends Content> extends Content {
			/**
			 * Opens a new base element.
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Base<PC> base() throws IOException;

			/**
			 * Shortcut to create a base with href only.
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base">&lt;base&gt;: The Document Base URL element</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_base.asp">HTML base tag</a>.</li>
			 * </ul>
			 *
			 * @return  The parent content model this element is within
			 */
			PC base__(String href) throws IOException;
			// TODO: IOSupplierE version like A? (review others, too)
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-link-element">4.2.4 The link element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Link<PC extends Content> extends Content {
			/**
			 * Opens a new link element.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_link.asp">HTML link tag</a>.
			 * </p>
			 */
			// TODO: Variants of Link by Rel, with per-implementation attributes like Input?
			com.aoindustries.html.Link<PC> link() throws IOException;

			/**
			 * Opens a new link element with the given rel attribute.
			 *
			 * @see #link()
			 * @see com.aoindustries.html.Link#rel(java.lang.Enum)
			 */
			com.aoindustries.html.Link<PC> link(com.aoindustries.html.Link.Rel rel) throws IOException;

			// No link__(), since either rel or itemprop is required
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-meta-element">4.2.5 The meta element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Meta<PC extends Content> extends Content {
			/**
			 * Opens a new meta element.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_meta.asp">HTML meta tag</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/meta">&lt;meta&gt;: The Document-level Metadata element</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Meta<PC> meta() throws IOException;

			/**
			 * Opens a new meta element with the given name attribute.
			 *
			 * @see #meta()
			 * @see com.aoindustries.html.Meta#name(java.lang.Enum)
			 */
			com.aoindustries.html.Meta<PC> meta(com.aoindustries.html.Meta.Name name) throws IOException;

			/**
			 * Opens a new meta element with the given name http-equiv.
			 *
			 * @see #meta()
			 * @see com.aoindustries.html.Meta#httpEquiv(java.lang.Enum)
			 */
			com.aoindustries.html.Meta<PC> meta(com.aoindustries.html.Meta.HttpEquiv httpEquiv) throws IOException;

			/**
			 * Opens a new meta element with the given charset attribute.
			 *
			 * @see #meta()
			 * @see com.aoindustries.html.Meta#charset(java.lang.Enum)
			 */
			com.aoindustries.html.Meta<PC> meta(Attributes.Enum.Charset.Value charset) throws IOException;

			// No meta__(), since either name, http-equiv, or itemprop is required (TODO: confirm itemprop-only metas?)
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-style-element">4.2.6 The style element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Style<PC extends Content> extends Content {
			/**
			 * Opens a new style element.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.
			 * </p>
			 *
			 * @see Doctype#styleType(java.lang.Appendable)
			 */
			com.aoindustries.html.Style<PC> style() throws IOException;

			/**
			 * Opens a new style element of the given type.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Style<PC> style(String type) throws IOException;

			/**
			 * Opens a new style element of the given type.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
			 * </ul>
			 */
			<Ex extends Throwable> com.aoindustries.html.Style<PC> style(Suppliers.String<Ex> type) throws IOException, Ex;

			/**
			 * Opens a new style element of the given type.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Style<PC> style(com.aoindustries.html.Style.Type type) throws IOException;

			/**
			 * Opens a new style element of the given type.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_style.asp">HTML style tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_style_type.asp">HTML style type Attribute</a>.</li>
			 * </ul>
			 */
			<Ex extends Throwable> com.aoindustries.html.Style<PC> style(IOSupplierE<? extends com.aoindustries.html.Style.Type, Ex> type) throws IOException, Ex;

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
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Body<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-article-element">4.3.2 The article element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Article<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-section-element">4.3.3 The section element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Section<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-nav-element">4.3.4 The nav element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Nav<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-aside-element">4.3.5 The aside element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Aside<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface H1<PC extends Content> extends Content {

			/**
			 * Opens a new h1 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			com.aoindustries.html.H1<PC> h1() throws IOException;

			/**
			 * Creates an h1 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC h1__(IORunnableE<Ex> h1) throws IOException, Ex;

			/**
			 * Creates an h1 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, H1Content extends PhrasingContent<H1Content>> PC h1__(IOConsumerE<? super H1Content, Ex> h1) throws IOException, Ex;

			/**
			 * Creates an h1 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h1__(Object text) throws IOException;

			/**
			 * Creates an empty h1 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h1__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface H2<PC extends Content> extends Content {

			/**
			 * Opens a new h2 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			com.aoindustries.html.H2<PC> h2() throws IOException;

			/**
			 * Creates an h2 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC h2__(IORunnableE<Ex> h2) throws IOException, Ex;

			/**
			 * Creates an h2 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, H2Content extends PhrasingContent<H2Content>> PC h2__(IOConsumerE<? super H2Content, Ex> h2) throws IOException, Ex;

			/**
			 * Creates an h2 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h2__(Object text) throws IOException;

			/**
			 * Creates an empty h2 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h2__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface H3<PC extends Content> extends Content {

			/**
			 * Opens a new h3 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			com.aoindustries.html.H3<PC> h3() throws IOException;

			/**
			 * Creates an h3 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC h3__(IORunnableE<Ex> h3) throws IOException, Ex;

			/**
			 * Creates an h3 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, H3Content extends PhrasingContent<H3Content>> PC h3__(IOConsumerE<? super H3Content, Ex> h3) throws IOException, Ex;

			/**
			 * Creates an h3 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h3__(Object text) throws IOException;

			/**
			 * Creates an empty h3 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h3__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface H4<PC extends Content> extends Content {

			/**
			 * Opens a new h4 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			com.aoindustries.html.H4<PC> h4() throws IOException;

			/**
			 * Creates an h4 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC h4__(IORunnableE<Ex> h4) throws IOException, Ex;

			/**
			 * Creates an h4 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, H4Content extends PhrasingContent<H4Content>> PC h4__(IOConsumerE<? super H4Content, Ex> h4) throws IOException, Ex;

			/**
			 * Creates an h4 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h4__(Object text) throws IOException;

			/**
			 * Creates an empty h4 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h4__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface H5<PC extends Content> extends Content {

			/**
			 * Opens a new h5 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			com.aoindustries.html.H5<PC> h5() throws IOException;

			/**
			 * Creates an h5 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC h5__(IORunnableE<Ex> h5) throws IOException, Ex;

			/**
			 * Creates an h5 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, H5Content extends PhrasingContent<H5Content>> PC h5__(IOConsumerE<? super H5Content, Ex> h5) throws IOException, Ex;

			/**
			 * Creates an h5 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h5__(Object text) throws IOException;

			/**
			 * Creates an empty h5 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h5__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface H6<PC extends Content> extends Content {

			/**
			 * Opens a new h6 element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 */
			com.aoindustries.html.H6<PC> h6() throws IOException;

			/**
			 * Creates an h6 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC h6__(IORunnableE<Ex> h6) throws IOException, Ex;

			/**
			 * Creates an h6 element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, H6Content extends PhrasingContent<H6Content>> PC h6__(IOConsumerE<? super H6Content, Ex> h6) throws IOException, Ex;

			/**
			 * Creates an h6 element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h6__(Object text) throws IOException;

			/**
			 * Creates an empty h6 element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-h1,-h2,-h3,-h4,-h5,-and-h6-elements">4.3.6 The h1, h2, h3, h4, h5, and h6 elements</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC h6__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-hgroup-element">4.3.7 The hgroup element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Hgroup<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-header-element">4.3.8 The header element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Header<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-footer-element">4.3.9 The footer element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Footer<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-address-element">4.3.10 The address element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Address<PC extends Content> extends Content {
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
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface P<PC extends Content> extends Content {

			/**
			 * Opens a new p element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 */
			com.aoindustries.html.P<PC> p() throws IOException;

			/**
			 * Creates a p element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC p__(IORunnableE<Ex> p) throws IOException, Ex;

			/**
			 * Creates a p element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, PContent extends PhrasingContent<PContent>> PC p__(IOConsumerE<? super PContent, Ex> p) throws IOException, Ex;

			/**
			 * Creates a p element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC p__(Object text) throws IOException;

			/**
			 * Creates an empty p element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-p-element">4.4.1 The p element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC p__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-hr-element">4.4.2 The hr element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Hr<PC extends Content> extends Content {
			/**
			 * Opens a new hr element.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.
			 * </p>
			 */
			com.aoindustries.html.Hr<PC> hr() throws IOException;

			/**
			 * Creates an empty hr element with no attributes.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_hr.asp">HTML hr tag</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC hr__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-pre-element">4.4.3 The pre element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Pre<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-blockquote-element">4.4.4 The blockquote element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Blockquote<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-ol-element">4.4.5 The ol element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Ol<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-ul-element">4.4.6 The ul element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Ul<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-menu-element">4.4.7 The menu element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Menu<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-li-element">4.4.8 The li element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Li<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dl-element">4.4.9 The dl element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Dl<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dt-element">4.4.10 The dt element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Dt<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dd-element">4.4.11 The dd element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Dd<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-figure-element">4.4.12 The figure element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Figure<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-figcaption-element">4.4.13 The figcaption element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Figcaption<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-main-element">4.4.14 The main element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Main<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-div-element">4.4.15 The div element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Div<PC extends Content> extends Content {
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
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface A<PC extends Content> extends Content {
			/**
			 * Opens a new a element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 */
			com.aoindustries.html.A<PC> a() throws IOException;

			/**
			 * Opens a new a element with the given href attribute.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 */
			com.aoindustries.html.A<PC> a(String href) throws IOException;

			/**
			 * Opens a new a element with the given href attribute.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 */
			<Ex extends Throwable> com.aoindustries.html.A<PC> a(IOSupplierE<? extends java.lang.String, Ex> href) throws IOException, Ex;

			/**
			 * Creates an a element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC a__(IORunnableE<Ex> a) throws IOException, Ex;

			/**
			 * Creates an a element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			// TODO: How to limit content to not have interactive elements?
			<Ex extends Throwable> PC a__(IOConsumerE<? super PC, Ex> a) throws IOException, Ex;

			/**
			 * Creates an a element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC a__(Object text) throws IOException;

			/**
			 * Creates an empty a element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-a-element">4.5.1 The a element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC a__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-em-element">4.5.2 The em element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Em<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-strong-element">4.5.3 The strong element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Strong<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-small-element">4.5.4 The small element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Small<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-s-element">4.5.5 The s element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface S<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-cite-element">4.5.6 The cite element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Cite<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-q-element">4.5.7 The q element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Q<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dfn-element">4.5.8 The dfn element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Dfn<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-abbr-element">4.5.9 The abbr element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Abbr<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-ruby-element">4.5.10 The ruby element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Ruby<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-rt-element">4.5.11 The rt element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Rt<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-rp-element">4.5.12 The rp element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Rp<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-data-element">4.5.13 The data element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Data<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-time-element">4.5.14 The time element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Time<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-code-element">4.5.15 The code element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Code<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-var-element">4.5.16 The var element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Var<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-samp-element">4.5.17 The samp element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Samp<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-kbd-element">4.5.18 The kbd element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Kbd<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Sub<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-sub-and-sup-elements">4.5.19 The sub and sup elements</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Sup<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface I<PC extends Content> extends Content {
			/**
			 * Opens a new i element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 */
			com.aoindustries.html.I<PC> i() throws IOException;

			/**
			 * Creates an i element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC i__(IORunnableE<Ex> i) throws IOException, Ex;

			/**
			 * Creates an i element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, IContent extends PhrasingContent<IContent>> PC i__(IOConsumerE<? super IContent, Ex> i) throws IOException, Ex;

			/**
			 * Creates an i element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC i__(Object text) throws IOException;

			/**
			 * Creates an empty i element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-i-element">4.5.20 The i element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC i__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface B<PC extends Content> extends Content {
			/**
			 * Opens a new b element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 */
			com.aoindustries.html.B<PC> b() throws IOException;

			/**
			 * Creates a b element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC b__(IORunnableE<Ex> b) throws IOException, Ex;

			/**
			 * Creates a b element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, BContent extends PhrasingContent<BContent>> PC b__(IOConsumerE<? super BContent, Ex> b) throws IOException, Ex;

			/**
			 * Creates a b element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC b__(Object text) throws IOException;

			/**
			 * Creates an empty b element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-b-element">4.5.21 The b element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC b__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface U<PC extends Content> extends Content {
			/**
			 * Opens a new u element.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 */
			com.aoindustries.html.U<PC> u() throws IOException;

			/**
			 * Creates a u element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable> PC u__(IORunnableE<Ex> u) throws IOException, Ex;

			/**
			 * Creates a u element with no attributes and the given body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			<Ex extends Throwable, UContent extends PhrasingContent<UContent>> PC u__(IOConsumerE<? super UContent, Ex> u) throws IOException, Ex;

			/**
			 * Creates a u element with no attributes and a text body.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC u__(Object text) throws IOException;

			/**
			 * Creates an empty u element with no attributes.
			 * <p>
			 * See <a href="https://html.spec.whatwg.org/#the-u-element">4.5.22 The u element</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC u__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-mark-element">4.5.23 The mark element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Mark<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-bdi-element">4.5.24 The bdi element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Bdi<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-bdo-element">4.5.25 The bdo element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Bdo<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-span-element">4.5.26 The span element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Span<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-br-element">4.5.27 The br element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Br<PC extends Content> extends Content {
			/**
			 * Opens a new br element.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.
			 * </p>
			 */
			com.aoindustries.html.Br<PC> br() throws IOException;

			/**
			 * Creates a br element with no attributes.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_br.asp">HTML br tag</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC br__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-wbr-element">4.5.28 The wbr element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Wbr<PC extends Content> extends Content {
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
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Ins<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-del-element">4.7.2 The del element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Del<PC extends Content> extends Content {
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
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Picture<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-source-element">4.8.2 The source element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Source<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-img-element">4.8.3 The img element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Img<PC extends Content> extends Content {
			/**
			 * Opens a new img element.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_img.asp">HTML img tag</a>.
			 * </p>
			 */
			com.aoindustries.html.Img<PC> img() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-iframe-element">4.8.5 The iframe element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Iframe<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-embed-element">4.8.6 The embed element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Embed<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-object-element">4.8.7 The object element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Object<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-param-element">4.8.8 The param element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Param<PC extends Content> extends Content {
			/**
			 * Opens a new param element.
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Param<PC> param() throws IOException;

			/**
			 * Creates a param element with the given name and value.
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/param">&lt;param&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_param.asp">HTML param tag</a>.</li>
			 * </ul>
			 *
			 * @return  The parent content model this element is within
			 */
			PC param__(java.lang.Object name, java.lang.Object value) throws IOException;

			// TODO: More types like supported by ao-taglib (ParamsTag.java), including collection types, as "params__"?
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-video-element">4.8.9 The video element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Video<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-audio-element">4.8.10 The audio element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Audio<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-track-element">4.8.11 The track element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Track<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-map-element">4.8.13 The map element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Map<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-area-element">4.8.14 The area element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Area<PC extends Content> extends Content {
			/**
			 * Opens a new area element.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Area<PC> area() throws IOException;

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Area<PC> area(Rectangle rect) throws IOException;

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			<Ex extends Throwable> com.aoindustries.html.Area<PC> area(Suppliers.Rectangle<Ex> rect) throws IOException, Ex;

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Area<PC> area(Circle circle) throws IOException;

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			<Ex extends Throwable> com.aoindustries.html.Area<PC> area(Suppliers.Circle<Ex> circle) throws IOException, Ex;

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Area<PC> area(Polygon poly) throws IOException;

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			<Ex extends Throwable> com.aoindustries.html.Area<PC> area(Suppliers.Polygon<Ex> poly) throws IOException, Ex;

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Area<PC> area(Shape shape) throws IOException;

			/**
			 * Opens a new area element with the given coords attribute.
			 * <ul>
			 * <li>See <a href="https://html.spec.whatwg.org/multipage/image-maps.html#the-area-element">HTML Standard</a>.</li>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/area">&lt;area&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_area.asp">HTML area tag</a>.</li>
			 * </ul>
			 */
			<Ex extends Throwable> com.aoindustries.html.Area<PC> area(Suppliers.Shape<Ex> shape) throws IOException, Ex;
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
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Table<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-caption-element">4.9.2 The caption element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Caption<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-colgroup-element">4.9.3 The colgroup element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Colgroup<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-col-element">4.9.4 The col element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Col<PC extends Content> extends Content {
			/**
			 * Opens a new col element.
			 * <ul>
			 * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/col">&lt;col&gt; - HTML: Hypertext Markup Language</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_col.asp">HTML col tag</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Col<PC> col() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-tbody-element">4.9.5 The tbody element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Tbody<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-thead-element">4.9.6 The thead element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Thead<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-tfoot-element">4.9.7 The tfoot element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Tfoot<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-tr-element">4.9.8 The tr element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Tr<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-td-element">4.9.9 The td element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Td<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-th-element">4.9.10 The th element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Th<PC extends Content> extends Content {
			// TODO
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
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Form<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-label-element">4.10.4 The label element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Label<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-input-element">4.10.5 The input element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Input<PC extends Content> extends Content {

			/**
			 * Specialized input implementations.
			 *
			 * @param  <PC>  The parent content model this element is within
			 */
			public static class Type<PC extends Content> {

				private final com.aoindustries.html.Document document;

				public Type(com.aoindustries.html.Document document) {
					this.document = document;
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.
				 */
				public com.aoindustries.html.Input.Dynamic<PC> dynamic() throws IOException {
					return new com.aoindustries.html.Input.Dynamic<PC>(document).open();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Dynamic<PC> dynamic(String type) throws IOException {
					return new com.aoindustries.html.Input.Dynamic<PC>(document, type).open();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.</li>
				 * </ul>
				 */
				// TODO: Move these type Input.type only?
				public <Ex extends Throwable> com.aoindustries.html.Input.Dynamic<PC> dynamic(Suppliers.String<Ex> type) throws IOException, Ex {
					return dynamic((type == null) ? null : type.get());
				}

				/**
				 * <ul>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.</li>
				 * </ul>
				 */
				public com.aoindustries.html.Input.Dynamic<PC> dynamic(com.aoindustries.html.Input.Dynamic.Type type) throws IOException {
					return new com.aoindustries.html.Input.Dynamic<PC>(document, type).open();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type.asp">HTML input type Attribute</a>.</li>
				 * </ul>
				 */
				// TODO: Move these type Input.type only?
				public <Ex extends Throwable> com.aoindustries.html.Input.Dynamic<PC> dynamic(IOSupplierE<? extends com.aoindustries.html.Input.Dynamic.Type, Ex> type) throws IOException, Ex {
					return dynamic((type == null) ? null : type.get());
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_button.asp">HTML input type="button"</a>.
				 */
				public com.aoindustries.html.Input.Button<PC> button() throws IOException {
					return new com.aoindustries.html.Input.Button<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_checkbox.asp">HTML input type="checkbox"</a>.
				 */
				public com.aoindustries.html.Input.Checkbox<PC> checkbox() throws IOException {
					return new com.aoindustries.html.Input.Checkbox<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_color.asp">HTML input type="color"</a>.
				 */
				public com.aoindustries.html.Input.Color<PC> color() throws IOException {
					return new com.aoindustries.html.Input.Color<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_date.asp">HTML input type="date"</a>.
				 */
				public com.aoindustries.html.Input.Date<PC> date() throws IOException {
					return new com.aoindustries.html.Input.Date<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_datetime-local.asp">HTML input type="datetime-local"</a>.
				 */
				public com.aoindustries.html.Input.DatetimeLocal<PC> datetimeLocal() throws IOException {
					return new com.aoindustries.html.Input.DatetimeLocal<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_email.asp">HTML input type="email"</a>.
				 */
				public com.aoindustries.html.Input.Email<PC> email() throws IOException {
					return new com.aoindustries.html.Input.Email<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_file.asp">HTML input type="file"</a>.
				 */
				public com.aoindustries.html.Input.File<PC> file() throws IOException {
					return new com.aoindustries.html.Input.File<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_hidden.asp">HTML input type="hidden"</a>.
				 */
				public com.aoindustries.html.Input.Hidden<PC> hidden() throws IOException {
					return new com.aoindustries.html.Input.Hidden<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_image.asp">HTML input type="image"</a>.
				 */
				public com.aoindustries.html.Input.Image<PC> image() throws IOException {
					return new com.aoindustries.html.Input.Image<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_month.asp">HTML input type="month"</a>.
				 */
				public com.aoindustries.html.Input.Month<PC> month() throws IOException {
					return new com.aoindustries.html.Input.Month<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_number.asp">HTML input type="number"</a>.
				 */
				public com.aoindustries.html.Input.Number<PC> number() throws IOException {
					return new com.aoindustries.html.Input.Number<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_password.asp">HTML input type="password"</a>.
				 */
				public com.aoindustries.html.Input.Password<PC> password() throws IOException {
					return new com.aoindustries.html.Input.Password<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_radio.asp">HTML input type="radio"</a>.
				 */
				public com.aoindustries.html.Input.Radio<PC> radio() throws IOException {
					return new com.aoindustries.html.Input.Radio<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_range.asp">HTML input type="range"</a>.
				 */
				public com.aoindustries.html.Input.Range<PC> range() throws IOException {
					return new com.aoindustries.html.Input.Range<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_reset.asp">HTML input type="reset"</a>.
				 */
				public com.aoindustries.html.Input.Reset<PC> reset() throws IOException {
					return new com.aoindustries.html.Input.Reset<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_search.asp">HTML input type="search"</a>.
				 */
				public com.aoindustries.html.Input.Search<PC> search() throws IOException {
					return new com.aoindustries.html.Input.Search<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.
				 */
				public com.aoindustries.html.Input.Submit<PC> submit() throws IOException {
					return new com.aoindustries.html.Input.Submit<PC>(document).open();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.</li>
				 * </ul>
				 *
				 * @return  The parent content model this element is within
				 */
				public PC submit__(Object value) throws IOException {
					return submit().value(value).__();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.</li>
				 * </ul>
				 *
				 * @return  The parent content model this element is within
				 */
				public <Ex extends Throwable> PC submit__(IOSupplierE<?, Ex> value) throws IOException, Ex {
					return submit().value(value).__();
				}

				/**
				 * <ul>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_type_submit.asp">HTML input type="submit"</a>.</li>
				 * <li>See <a href="https://www.w3schools.com/tags/att_input_value.asp">HTML input value Attribute</a>.</li>
				 * </ul>
				 *
				 * @return  The parent content model this element is within
				 */
				public <Ex extends Throwable> PC submit__(MediaWritable<Ex> value) throws IOException, Ex {
					return submit().value(value).__();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_tel.asp">HTML input type="tel"</a>.
				 */
				public com.aoindustries.html.Input.Tel<PC> tel() throws IOException {
					return new com.aoindustries.html.Input.Tel<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_text.asp">HTML input type="text"</a>.
				 */
				public com.aoindustries.html.Input.Text<PC> text() throws IOException {
					return new com.aoindustries.html.Input.Text<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_time.asp">HTML input type="time"</a>.
				 */
				public com.aoindustries.html.Input.Time<PC> time() throws IOException {
					return new com.aoindustries.html.Input.Time<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_url.asp">HTML input type="url"</a>.
				 */
				public com.aoindustries.html.Input.Url<PC> url() throws IOException {
					return new com.aoindustries.html.Input.Url<PC>(document).open();
				}

				/**
				 * See <a href="https://www.w3schools.com/tags/att_input_type_week.asp">HTML input type="week"</a>.
				 */
				public com.aoindustries.html.Input.Week<PC> week() throws IOException {
					return new com.aoindustries.html.Input.Week<PC>(document).open();
				}
			}

			/**
			 * Specialized input implementations.
			 */
			Type<PC> input();
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-button-element">4.10.6 The button element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Button<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-select-element">4.10.7 The select element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Select<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-datalist-element">4.10.8 The datalist element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Datalist<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-optgroup-element">4.10.9 The optgroup element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Optgroup<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-option-element">4.10.10 The option element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Option<PC extends Content> extends Content {
			/**
			 * Opens a new option element.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.
			 * </p>
			 */
			com.aoindustries.html.Option<PC> option() throws IOException;

			/**
			 * Creates an empty option element with no attributes.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_option.asp">HTML option tag</a>.
			 * </p>
			 *
			 * @return  The parent content model this element is within
			 */
			PC option__() throws IOException;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-textarea-element">4.10.11 The textarea element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Textarea<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-output-element">4.10.12 The output element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Output<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-progress-element">4.10.13 The progress element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Progress<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-meter-element">4.10.14 The meter element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Meter<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-fieldset-element">4.10.15 The fieldset element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Fieldset<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-legend-element">4.10.16 The legend element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Legend<PC extends Content> extends Content {
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
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Details<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-summary-element">4.11.2 The summary element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Summary<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-dialog-element">4.11.4 The dialog element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Dialog<PC extends Content> extends Content {
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
		 * See <a href="https://html.spec.whatwg.org/#the-script-element">4.12.1 The script element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		public static interface Script<PC extends Content> extends Content {
			/**
			 * Opens a new script element.
			 * <p>
			 * See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.
			 * </p>
			 *
			 * @see Doctype#scriptType(java.lang.Appendable)
			 */
			com.aoindustries.html.Script<PC> script() throws IOException;

			/**
			 * Opens a new script element of the given type.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Script<PC> script(String type) throws IOException;

			/**
			 * Opens a new script element of the given type.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
			 * </ul>
			 */
			<Ex extends Throwable> com.aoindustries.html.Script<PC> script(Suppliers.String<Ex> type) throws IOException, Ex;

			/**
			 * Opens a new script element of the given type.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
			 * </ul>
			 */
			com.aoindustries.html.Script<PC> script(com.aoindustries.html.Script.Type type) throws IOException;

			/**
			 * Opens a new script element of the given type.
			 * <ul>
			 * <li>See <a href="https://www.w3schools.com/tags/tag_script.asp">HTML script tag</a>.</li>
			 * <li>See <a href="https://www.w3schools.com/tags/att_script_type.asp">HTML script type Attribute</a>.</li>
			 * </ul>
			 */
			<Ex extends Throwable> com.aoindustries.html.Script<PC> script(IOSupplierE<? extends com.aoindustries.html.Script.Type, Ex> type) throws IOException, Ex;
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-noscript-element">4.12.2 The noscript element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Noscript<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-template-element">4.12.3 The template element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Template<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-slot-element">4.12.4 The slot element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Slot<PC extends Content> extends Content {
			// TODO
		}

		/**
		 * See <a href="https://html.spec.whatwg.org/#the-canvas-element">4.12.5 The canvas element</a>.
		 *
		 * @param  <PC>  The parent content model this element is within
		 */
		@SuppressWarnings("MarkerInterface") // TODO
		public static interface Canvas<PC extends Content> extends Content {
			// TODO
		}
	}

	// TODO: autonomous custom elements: 4.13 Custom elements: https://html.spec.whatwg.org/#custom-elements
}
