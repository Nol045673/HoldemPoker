package org.example.holdempoker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Card implements Comparable<Card> {
    @JsonProperty
    private final int value;
    @JsonProperty
    @EqualsAndHashCode.Exclude
    private final char suit;

    public Card(String card) {
        this.value = parseValue(card.charAt(0));
        this.suit = card.charAt(1);
    }

    private int parseValue(char valueChar) {
        return switch (valueChar) {
            case '2' -> 2;
            case '3' -> 3;
            case '4' -> 4;
            case '5' -> 5;
            case '6' -> 6;
            case '7' -> 7;
            case '8' -> 8;
            case '9' -> 9;
            case 'T' -> 10;
            case 'J' -> 11;
            case 'Q' -> 12;
            case 'K' -> 13;
            case 'A' -> 14;
            default -> throw new IllegalArgumentException("Invalid card value: " + valueChar);
        };
    }

    @Override
    public int compareTo(Card other) {
        return Integer.compare(this.value, other.value);
    }

    @Override
    public String toString() {
        return value + String.valueOf(suit);
    }
}
