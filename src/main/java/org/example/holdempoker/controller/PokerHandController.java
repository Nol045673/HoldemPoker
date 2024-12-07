package org.example.holdempoker.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.example.holdempoker.service.PokerHandService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
class PokerHandController {

    @PostMapping("/poker")
    public List<PokerHandService> evaluateHands(@RequestBody List<String> hands) {
        List<PokerHandService> pokerHands = new ArrayList<>();
        for (String hand : hands) {
            if (hand == null || hand.trim().split(" ").length != 5) {
                throw new IllegalArgumentException("Hand must contain exactly 5 cards.");
            }
            pokerHands.add(new PokerHandService(hand));
        }
        pokerHands.sort(Collections.reverseOrder());
        return pokerHands;
    }
}
