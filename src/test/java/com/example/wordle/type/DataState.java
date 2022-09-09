package com.example.wordle.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

@RequiredArgsConstructor
@Getter
public enum DataState {
    EMPTY("empty"),
    TBD("tbd"),
    ABSENT("absent"),
    PRESENT("present"),
    CORRECT("correct");

    private final String caption;

    public static DataState fromCaption(String caption) {
        return Arrays.stream(values())
                .filter(dataState -> dataState.caption.equals(caption))
                .findFirst()
                .orElse(null);
    }

}
