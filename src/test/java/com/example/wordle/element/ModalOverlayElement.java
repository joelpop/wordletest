package com.example.wordle.element;

import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.annotations.Attribute;
import lombok.extern.slf4j.Slf4j;

/**
 * Page object for the modal overlay.
 *
 * <pre>
 *     <div class="Modal-module_modalOverlay__81ZCi" data-testid="modal-overlay" role="button">
 *         <div class="Modal-module_content__s8qUZ">
 * </pre>
 */
@Slf4j
@Attribute(name = "data-testid", value = "modal-overlay")
public class ModalOverlayElement extends DivElement {

    /**
     * Dismiss the modal overlay element.
     *
     */
    public void dismiss() {
        click();
    }

}
