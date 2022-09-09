package com.example.wordle.element;

import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.annotations.Attribute;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.NoSuchElementException;

/**
 * Page object for board.
 *
 * <pre>
 *   <div class="Board-module_board__lbzlf" style="width: 350px; height: 420px;">
 * </pre>
 */
@Slf4j
@Attribute(name = "class", contains = "Board-module_board__lbzlf")
public class BoardElement extends DivElement {

    /**
     * Return the requested board row element.
     *
     * @param rowNum the (zero-based) number of the board row
     * @return the requested board row element if it is present
     * or null otherwise.
     */
    protected BoardRowElement getBoardRow(int rowNum) {
        try {
            return $(BoardRowElement.class).get(rowNum);
        }
        catch (NoSuchElementException e) {
            log.warn("Couldn't find BoardElement::getBoardRow({}).", rowNum);
            return null;
        }
    }

}
