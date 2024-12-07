package org.example.holdempoker.service;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringJUnitConfig(classes = {HandRankService.class})
public class HandRankServiceTest {
    @Autowired
    HandRankService handRankService;

    @Test
    public void sort() {
        List<String> hands = List.of(
            "KS JH QC 9D 8D",
            "AS 2H 5C 4D 6D",
            "2C 3C KC 4C 5C",
            "2H 3H AH 4H 5H",
            "2D 5H 5S 5C 5H",
            "4D 4H 4S 4C AH",
            "5D 5H AS AC 5H",
            "6D 6H 7S 7C 7H",
            "4H 5H 6H 7H 8H",
            "5C 6C 7C 8C 9C",
            "JH JS QH QS 2C",
            "JC JD QC QD 8C"
        );
        List<String> expectedHands = List.of(
            "9C 8C 7C 6C 5C",
            "8H 7H 6H 5H 4H",
            "5H 5S 5C 5H 2D",
            "4D 4H 4S 4C AH",
            "7S 7C 7H 6D 6H",
            "5D 5H 5H AS AC",
            "AH 5H 4H 3H 2H",
            "KC 5C 4C 3C 2C",
            "QC QD JC JD 8C",
            "QH QS JH JS 2C",
            "AS 6D 5C 4D 2H",
            "QC KS JH 9D 8D"
        );

        List<String> result = handRankService.sort(hands);

        Assertions.assertEquals(expectedHands, result);
    }
}
