package com.example.wordle.it;

import com.example.util.BaseTestCase;
import com.example.wordle.element.BoardTileElement;
import com.example.wordle.element.WordlePageElement;
import com.example.wordle.type.DataState;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
public class PlayWordleIT extends BaseTestCase {

    private WordlePageElement wordlePageElement;

    @BeforeClass
    @Override
    protected void initWebDriverManager() {
        super.initWebDriverManager();
    }

    @BeforeMethod
    @Override
    protected void setup() {
        super.setup();
        wordlePageElement = $(WordlePageElement.class).onPage().waitForFirst();
    }

    private void dismissAnyModalOverlay() {
        var modalOverlay = wordlePageElement.getModalOverlay();
        if (modalOverlay != null) {
            log.trace("Dismissing modal overlay");
            modalOverlay.dismiss();
        }
    }

    /**
     * Test keyboard - type and delete each letter.
     */
    @Test
    public void typingTest() {
        dismissAnyModalOverlay();

        log.trace("Testing editing");
        IntStream.range('a', 'z')
                .mapToObj(idx -> (char) idx)
                .forEach(ch -> {
                    BoardTileElement boardTileElement;

                    log.trace("Testing pressing '{}' key", ch);
                    wordlePageElement.pressKey(ch);
                    boardTileElement = wordlePageElement.getBoardRowTiles(0).get(0);
                    assertThat(boardTileElement.getDataState()).isEqualTo(DataState.TBD);
                    assertThat(boardTileElement.getLetter()).isEqualTo(Character.toUpperCase(ch));

                    wordlePageElement.pressDeleteKey();
                    boardTileElement = wordlePageElement.getBoardRowTiles(0).get(0);
                    assertThat(boardTileElement.getDataState()).isEqualTo(DataState.EMPTY);
                    assertThat(boardTileElement.getLetter()).isEqualTo(' ');
                });
    }

    /**
     * Test word entry.
     */
    @Test
    public void wordEntryTest() {
        dismissAnyModalOverlay();

        log.trace("Testing word entry");
        testWord(0, "saute");
        testWord(1, "irony");
    }

    private void testWord(int wordNum, String word) {
        log.trace("Testing word: \"{}\"", word);
        var boardTileElements = wordlePageElement.playWord(wordNum, word);
        IntStream.range(0, WordlePageElement.WORD_LENGTH).forEach(idx -> {
            var wordLetter = word.charAt(idx);
            log.trace("  Testing letter: \"{}\"", wordLetter);
            var boardTileElement = boardTileElements.get(idx);

            assertThat(boardTileElement.getDataState()).isNotEqualTo(DataState.EMPTY);

            assertThat(boardTileElement.getLetter()).isEqualTo(Character.toUpperCase(wordLetter));
        });

    }

    @AfterMethod
    @Override
    protected void teardown() {
        super.teardown();
    }

}
