package org.example.holdempoker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper=false)
public class Card implements Comparable<Card> {
    @JsonProperty
    private final HandValue value;
    @JsonProperty
    private final char suit;

    public Card(String card) {
        this.value = HandValue.valueOf(card.charAt(0));
        this.suit = card.charAt(1);
    }

    @Override
    public int compareTo(Card other) {
        return this.value.compareTo(other.value);
    }

    @Override
    public String toString() {
        return value.getValue() + suit;
    }
}
