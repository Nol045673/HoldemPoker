package org.example.holdempoker.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Getter;

@Getter
public class PokerHand implements Comparable<PokerHand> {
    @JsonProperty
    private List<Card> cards;
    @JsonProperty
    private HandRank rank;

    public PokerHand(String hand) {
        this.cards = parseCards(hand);
    }

    public List<Card> parseCards(String hand) {
        List<Card> cards = Arrays.stream(hand.split(" "))
            .map(Card::new)
            .collect(Collectors.toList());
        return getValueCounts(cards);
    }

    private boolean isFlush(List<Card> cards) {
        return cards.stream().map(Card::getSuit).distinct().count() == 1;
    }

    private boolean isStraight(List<Card> cards) {
        cards.sort(Comparator.reverseOrder());
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getValue().ordinal() - cards.get(i + 1).getValue().ordinal() != 1) {
                return false;
            }
        }
        return true;
    }

    private HandRank rankDifferentCard(List<Card> cards) {
        boolean isStraight = isStraight(cards);
        if (isFlush(cards)) {
            return isStraight ? HandRank.STRAIGHT_FLUSH : HandRank.FLUSH;
        }
        return isStraight ? HandRank.STRAIGHT : HandRank.HIGH_CARD;
    }

    private List<Card> getValueCounts(List<Card> cards) {
        Map<HandValue, List<Card>> valueCounts = cards.stream()
            .collect(Collectors.groupingBy(Card::getValue));
        List<Map.Entry<HandValue, List<Card>>> entryList = valueCounts.entrySet().stream()
            .sorted((entry1, entry2) -> {
                int i = entry2.getValue().size() - entry1.getValue().size();
                return i != 0 ? i : entry2.getKey().getValue().compareTo(entry1.getKey().getValue());
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
            this.rank = rankDifferentCard(cards);
        }
        return entryList.stream()
            .map(Map.Entry::getValue)
            .flatMap(Collection::stream)
            .toList();
    }

    @Override
    public int compareTo(PokerHand other) {
        int rankComparison = this.rank.compareTo(other.rank);
        if (rankComparison != 0) {
            return rankComparison;
        }
        for (int i = 0; i < this.cards.size(); i++) {
            int comparison = this.cards.get(i).getValue().compareTo(other.cards.get(i).getValue());
            if (comparison != 0) {
                return comparison;
            }
        }
        return 0;
    }

    @Override
    public String toString() {
        return cards.stream()
            .map(Card::toString)
            .collect(Collectors.joining(" "));
    }

}
