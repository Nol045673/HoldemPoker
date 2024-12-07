package org.example.holdempoker.Service;

import static org.example.holdempoker.model.HandRank.FLUSH;
import static org.example.holdempoker.model.HandRank.FOUR_OF_A_KIND;
import static org.example.holdempoker.model.HandRank.FULL_HOUSE;
import static org.example.holdempoker.model.HandRank.HIGH_CARD;
import static org.example.holdempoker.model.HandRank.STRAIGHT_FLUSH;
import static org.example.holdempoker.model.HandRank.TWO_PAIR;
import org.example.holdempoker.service.PokerHandService;
import java.util.ArrayList;
import java.util.Collections;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class PokerHandServiceTest {

    @Test
    public void testHandRanking() {
        PokerHandService hand1 = new PokerHandService("KS 2H 5C JD TD"); // High card
        PokerHandService hand2 = new PokerHandService("2C 3C AC 4C 5C"); // Straight flush

        assertTrue(hand2.compareTo(hand1) > 0); // hand2 stronger than hand1
    }

    @Test
    public void testSortingHands() {
        ArrayList<PokerHandService> hands = new ArrayList<>();
        hands.add(new PokerHandService("KS JH QC 9D 8D"));
        hands.add(new PokerHandService("AS 2H 5C 4D 6D"));
        hands.add(new PokerHandService("2C 3C KC 4C 5C"));
        hands.add(new PokerHandService("2H 3H AH 4H 5H"));
        hands.add(new PokerHandService("2D 5H 5S 5C 5H"));
        hands.add(new PokerHandService("4D 4H 4S 4C AH"));
        hands.add(new PokerHandService("5D 5H AS AC 5H"));
        hands.add(new PokerHandService("6D 6H 7S 7C 7H"));
        hands.add(new PokerHandService("4H 5H 6H 7H 8H"));
        hands.add(new PokerHandService("5C 6C 7C 8C 9C"));
        hands.add(new PokerHandService("JH JS QH QS 2C"));
        hands.add(new PokerHandService("JC JD QC QD 8C"));

        hands.sort(Collections.reverseOrder());

        assertEquals(STRAIGHT_FLUSH, hands.get(0).getRank());
        assertEquals(STRAIGHT_FLUSH, hands.get(1).getRank());
        assertEquals(FOUR_OF_A_KIND, hands.get(2).getRank());
        assertEquals(FOUR_OF_A_KIND, hands.get(3).getRank());
        assertEquals(FULL_HOUSE, hands.get(4).getRank());
        assertEquals(FULL_HOUSE, hands.get(5).getRank());
        assertEquals(FLUSH, hands.get(6).getRank());
        assertEquals(FLUSH, hands.get(7).getRank());
        assertEquals(TWO_PAIR, hands.get(8).getRank());
        assertEquals(TWO_PAIR, hands.get(9).getRank());
        assertEquals(HIGH_CARD, hands.get(10).getRank());
        assertEquals(HIGH_CARD, hands.get(11).getRank());
    }
}
