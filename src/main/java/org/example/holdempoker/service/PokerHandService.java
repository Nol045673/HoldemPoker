package org.example.holdempoker.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.Getter;
import org.example.holdempoker.model.Card;
import org.example.holdempoker.model.HandRank;

@Getter
public class PokerHandService implements Comparable<PokerHandService> {
    @JsonProperty
    private List<Card> cards;
    @JsonProperty
    private HandRank rank;

    public PokerHandService(String hand) {
        this.cards = parseCards(hand);
    }

    public List<Card> parseCards(String hand) {
        List<Card> cards = new ArrayList<>();
        for (String cardStr : hand.trim().split(" ")) {
            if (cardStr.length() != 2) {
                throw new IllegalArgumentException("Invalid card: " + cardStr);
            }
            cards.add(new Card(cardStr));
        }
        cards.sort(Collections.reverseOrder());
        this.cards = cards;
        return getValueCounts();
    }

    private boolean isFlush() {
        return cards.stream().map(Card::getSuit).distinct().count() == 1;
    }

    private boolean isStraight() {
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue() - cards.get(i + 1).getValue() != 1) {
                return false;
            }
        }
        return true;
    }

    private HandRank rankDifferentCard() {
        boolean isStraight = isStraight();
        if (isFlush()) {
            if (isStraight) {
                return HandRank.STRAIGHT_FLUSH;
            } else {
                return HandRank.FLUSH;
            }
        }
        return isStraight ? HandRank.STRAIGHT: HandRank.HIGH_CARD;
    }

    private List<Card> getValueCounts() {
        Map<Card, List<Card>> valueCounts = new HashMap<>();
        for (Card card : cards) {
            List<Card> cards1 = valueCounts.get(card);
            if (cards1 == null) {
                valueCounts.put(card, new ArrayList<>(List.of(card)));
            } else {
                cards1.add(card);
            }
        }
        List<Map.Entry<Card, List<Card>>> entryList = valueCounts.entrySet().stream()
            .sorted((entry1, entry2) -> {
                int i = entry2.getValue().size() - entry1.getValue().size();
                if (i != 0) {
                    return i;
                }
                return entry2.getKey().getValue() - entry1.getKey().getValue();
            })
            .toList();
        if (entryList.get(0).getValue().size() == 4) {
            this.rank = HandRank.FOUR_OF_A_KIND;
        } else if (entryList.get(0).getValue().size() == 3 && entryList.get(1).getValue().size() == 2) {
            this.rank = HandRank.FULL_HOUSE;
        } else if (entryList.get(0).getValue().size() == 3) {
            this.rank = HandRank.THREE_OF_A_KIND;
        } else if (entryList.size() == 3) {
            this.rank = HandRank.TWO_PAIR;
        } else if (entryList.get(0).getValue().size() == 2) {
            this.rank = HandRank.ONE_PAIR;
        } else {
            this.rank = rankDifferentCard();
        }
        List<Card> newCards = entryList.stream()
            .map(Map.Entry::getValue)
            .flatMap(Collection::stream)
            .toList();
        return newCards;
    }

    @Override
    public int compareTo(PokerHandService other) {
        int rankComparison = this.rank.compareTo(other.rank);
        if (rankComparison != 0) {
            return rankComparison;
        }

        for (int i = 0; i < this.cards.size(); i++) {
            int comparison = Integer.compare(this.cards.get(i).getValue(), other.cards.get(i).getValue());
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return "PokerHand{" + "cards=" + cards + ", rank=" + rank + '}';
    }

}
