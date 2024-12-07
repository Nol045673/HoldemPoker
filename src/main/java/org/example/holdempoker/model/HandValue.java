package org.example.holdempoker.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HandValue {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    private final String value;

    public static HandValue valueOf(Character character) {
        return switch (character) {
            case '2' -> TWO;
            case '3' -> THREE;
            case '4' -> FOUR;
            case '5' -> FIVE;
            case '6' -> SIX;
            case '7' -> SEVEN;
            case '8' -> EIGHT;
            case '9' -> NINE;
            case 'T' -> TEN;
            case 'J' -> JACK;
            case 'Q' -> QUEEN;
            case 'K' -> KING;
            case 'A' -> ACE;
            default -> throw new IllegalArgumentException("Invalid card value: " + character);
        };
    }
}
