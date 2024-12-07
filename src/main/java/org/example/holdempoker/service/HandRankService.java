package org.example.holdempoker.service;

import java.util.Collections;
import java.util.List;
import org.example.holdempoker.model.PokerHand;
import org.springframework.stereotype.Service;

@Service
public class HandRankService {
    public List<String> sort(List<String> hands) {
        return hands.stream()
            .map(PokerHand::new)
            .sorted(Collections.reverseOrder())
            .map(PokerHand::toString)
            .toList();
    }
}
