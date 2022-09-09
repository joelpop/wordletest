package com.example.wordle.element;

import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.annotations.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;

/**
 * Page object for keyboard.
 *
 * <pre>
 *   <div class="Keyboard-module_keyboard__1HSnn">
 * </pre>
 */
@Slf4j
@Attribute(name = "class", contains = "Keyboard-module_keyboard__1HSnn")
public class KeyboardElement extends DivElement {

    /**
     * Return the requested key element.
     *
     * @param ch the character of the key
     * @return the requested key element if it is present
     * or null otherwise.
     */
    protected KeyElement getKey(char ch) {
        try {
            return $(KeyElement.class)
                    .attribute("data-key", String.valueOf(ch))
                    .first();
        }
        catch (NoSuchElementException e) {
            log.warn("Couldn't find WordlePageElement::getKey({}).", ch);
            return null;
        }
    }

    /**
     * Press the requested key.
     *
     * @param ch the character of the key
     * @return the requested key element if it is present
     * or null otherwise.
     */
    protected KeyElement pressKey(char ch) {
        var keyElement = getKey(ch);
        keyElement.click();
        return keyElement;
    }

    /**
     * Press the delete key.
     *
     * @return the delete key element if it is present
     * or null otherwise.
     */
    protected KeyElement pressDeleteKey() {
        return pressKey('←');
    }

    /**
     * Press the enter key.
     *
     * @return the enter key element if it is present
     * or null otherwise.
     */
    protected KeyElement pressEnterKey() {
        return pressKey('↵');
    }

}
