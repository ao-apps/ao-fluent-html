/*
 * ao-fluent-html - Fluent Java DSL for high-performance HTML generation.
 * Copyright (C) 2021, 2022  AO Industries, Inc.
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
 * along with ao-fluent-html.  If not, see <https://www.gnu.org/licenses/>.
 */

package com.aoapps.html;

import com.aoapps.html.any.AnyINPUT;
import com.aoapps.html.any.AnyUnion_Interactive_Phrasing;
import com.aoapps.html.any.Suppliers;
import com.aoapps.lang.io.function.IOConsumerE;
import com.aoapps.lang.io.function.IOSupplierE;
import java.io.IOException;

/**
 * Elements that are common to both {@link InteractiveContent} and {@link PhrasingContent}.
 *
 * @param  <__>  This content model, which will be the parent content model of child elements
 *
 * @author  AO Industries, Inc.
 */
public interface Union_Interactive_Phrasing<
    __ extends Union_Interactive_Phrasing<__>
    > extends AnyUnion_Interactive_Phrasing<Document, __>,
    //
    // Unions:
    //
    Union_Embedded_Interactive<__> {

  //
  // Content models:
  //
  // Inherited: Content<__>

  //
  // Factories:
  //
  // <editor-fold defaultstate="collapsed" desc="A">
  @Override
  @SuppressWarnings("deprecation")
  default A<__> a() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    return new A<>(document, pc).writeOpen(document.getRawUnsafe(null));
  }

  @Override
  default A<__> a(String href) throws IOException {
    return a().href(href);
  }

  /**
   * {@inheritDoc}
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   */
  @Override
  default <Ex extends Throwable> A<__> a(IOSupplierE<? extends String, Ex> href) throws IOException, Ex {
    return a().href(href);
  }

  @Override
  default A_c<__> a_c() throws IOException {
    return a()._c();
  }

  // </editor-fold>
  // Inherited: AUDIO
  // <editor-fold defaultstate="collapsed" desc="TODO: BUTTON">
  // </editor-fold>
  // Inherited: EMBED
  // <editor-fold defaultstate="collapsed" desc="INPUT">
  /**
   * Specialized input implementations.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/input.html#the-input-element">4.10.5 The input element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/input">&lt;input&gt;: The Input (Form Input) element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_input.asp">HTML input tag</a>.</li>
   * </ul>
   *
   * @param  <__>  This content model, which will be the parent content model of child elements
   */
  public static final class InputFactory<
      __ extends Union_Interactive_Phrasing<__>
      > extends AnyUnion_Interactive_Phrasing.InputFactory<Document, __> {

    InputFactory(Document document, __ pc) {
      super(document, pc);
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Dynamic<__> dynamic() throws IOException {
      return new INPUT.Dynamic<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Dynamic<__> dynamic(String type) throws IOException {
      return new INPUT.Dynamic<>(document, pc, type).writeOpen(document.getRawUnsafe(null));
    }

    /**
     * {@inheritDoc}
     *
     * @param  <Ex>  An arbitrary exception type that may be thrown
     */
    @Override
    public <Ex extends Throwable> INPUT.Dynamic<__> dynamic(Suppliers.String<Ex> type) throws IOException, Ex {
      return dynamic((type == null) ? null : type.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Dynamic<__> dynamic(AnyINPUT.Dynamic.Type type) throws IOException {
      return new INPUT.Dynamic<>(document, pc, type).writeOpen(document.getRawUnsafe(null));
    }

    /**
     * {@inheritDoc}
     *
     * @param  <Ex>  An arbitrary exception type that may be thrown
     */
    @Override
    public <Ex extends Throwable> INPUT.Dynamic<__> dynamic(IOSupplierE<? extends AnyINPUT.Dynamic.Type, Ex> type) throws IOException, Ex {
      return dynamic((type == null) ? null : type.get());
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Button<__> button() throws IOException {
      return new INPUT.Button<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Checkbox<__> checkbox() throws IOException {
      return new INPUT.Checkbox<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Color<__> color() throws IOException {
      return new INPUT.Color<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Date<__> date() throws IOException {
      return new INPUT.Date<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.DatetimeLocal<__> datetimeLocal() throws IOException {
      return new INPUT.DatetimeLocal<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Email<__> email() throws IOException {
      return new INPUT.Email<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.File<__> file() throws IOException {
      return new INPUT.File<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Hidden<__> hidden() throws IOException {
      return new INPUT.Hidden<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Image<__> image() throws IOException {
      return new INPUT.Image<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Month<__> month() throws IOException {
      return new INPUT.Month<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Number<__> number() throws IOException {
      return new INPUT.Number<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Password<__> password() throws IOException {
      return new INPUT.Password<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Radio<__> radio() throws IOException {
      return new INPUT.Radio<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Range<__> range() throws IOException {
      return new INPUT.Range<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Reset<__> reset() throws IOException {
      return new INPUT.Reset<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Search<__> search() throws IOException {
      return new INPUT.Search<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Submit<__> submit() throws IOException {
      return new INPUT.Submit<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Tel<__> tel() throws IOException {
      return new INPUT.Tel<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Text<__> text() throws IOException {
      return new INPUT.Text<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Time<__> time() throws IOException {
      return new INPUT.Time<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Url<__> url() throws IOException {
      return new INPUT.Url<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }

    @Override
    @SuppressWarnings("deprecation")
    public INPUT.Week<__> week() throws IOException {
      return new INPUT.Week<>(document, pc).writeOpen(document.getRawUnsafe(null));
    }
  }

  @Override
  default InputFactory<__> input() {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    return new InputFactory<>(getDocument(), pc);
  }

  // </editor-fold>
  // Inherited: IFRAME
  // Inherited: IMG
  // <editor-fold defaultstate="collapsed" desc="LABEL">
  @Override
  @SuppressWarnings("deprecation")
  default LABEL<__> label() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    return new LABEL<>(document, pc).writeOpen(document.getRawUnsafe(null));
  }

  /**
   * Creates a label element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/forms.html#the-label-element">4.10.4 The label element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/label">&lt;label&gt;: The Input Label element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_label.asp">HTML label tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ label__(IOConsumerE<? super LABEL__<__>, Ex> label) throws IOException, Ex {
    return label().__(label);
  }

  @Override
  default LABEL_c<__> label_c() throws IOException {
    return label()._c();
  }

  // </editor-fold>
  // Inherited: OBJECT
  // <editor-fold defaultstate="collapsed" desc="SELECT">
  @Override
  @SuppressWarnings("deprecation")
  default SELECT<__> select() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    return new SELECT<>(document, pc).writeOpen(document.getRawUnsafe(null));
  }

  /**
   * Creates a select element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-select-element">4.10.7 The select element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/select">&lt;section&gt;: The HTML Select element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_select.asp">HTML select tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ select__(IOConsumerE<? super SELECT__<__>, Ex> select) throws IOException, Ex {
    return select().__(select);
  }

  @Override
  default SELECT_c<__> select_c() throws IOException {
    return select()._c();
  }

  // </editor-fold>
  // <editor-fold defaultstate="collapsed" desc="TEXTAREA">
  @Override
  @SuppressWarnings("deprecation")
  default TEXTAREA<__> textarea() throws IOException {
    @SuppressWarnings("unchecked")
    __ pc = (__) this;
    Document document = getDocument();
    return new TEXTAREA<>(document, pc).writeOpen(document.getRawUnsafe(null));
  }

  /**
   * Creates a textarea element with no attributes and the given body.
   * <ul>
   * <li>See <a href="https://html.spec.whatwg.org/multipage/form-elements.html#the-textarea-element">4.10.11 The textarea element</a>.</li>
   * <li>See <a href="https://developer.mozilla.org/en-US/docs/Web/HTML/Element/textarea">&lt;textarea&gt;: The Textarea element</a>.</li>
   * <li>See <a href="https://www.w3schools.com/tags/tag_textarea.asp">HTML textarea tag</a>.</li>
   * </ul>
   *
   * @param  <Ex>  An arbitrary exception type that may be thrown
   *
   * @return  This content model, which will be the parent content model of child elements
   */
  default <Ex extends Throwable> __ textarea__(IOConsumerE<? super TEXTAREA__<__>, Ex> textarea) throws IOException, Ex {
    return textarea().__(textarea);
  }

  @Override
  default TEXTAREA_c<__> textarea_c() throws IOException {
    return textarea()._c();
  }
  // </editor-fold>
  // Inherited: VIDEO
}
