package org.example.holdempoker.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.holdempoker.service.HandRankService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
class PokerHandController {
    private final HandRankService handRankService;

    @PostMapping("/cards/sort")
    public List<String> sort(@RequestBody List<String> hands) {
        return handRankService.sort(hands);
    }
}
