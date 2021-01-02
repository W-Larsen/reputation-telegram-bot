package com.telegram.rtb.controller;

import com.telegram.rtb.model.keyword.MinusKeywords;
import com.telegram.rtb.service.keyword.IKeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Minus keyword controller.
 *
 * @author Valentyn Korniienko
 */
@RestController
public class MinusKeywordController {

    @Autowired
    @Qualifier("minusKeywordService")
    private IKeywordService minusKeywordService;

    @GetMapping(value = "/keywords/minus")
    public MinusKeywords getAllMinusKeywordsByChatId(@RequestHeader Long chatId) {
        return (MinusKeywords) minusKeywordService.getKeywordsByChatId(chatId);
    }

    @PostMapping(value = "/keywords/minus")
    public void addMinusKeyword(@RequestHeader Long chatId, @RequestParam String keyword) {
        minusKeywordService.addKeyword(keyword, chatId);
    }

    @PostMapping(value = "/keywords/minus/batch")
    public void addBatchMinusKeywords(@RequestHeader Long chatId, @RequestParam List<String> keywords) {
        minusKeywordService.saveKeywords(keywords, chatId);
    }

    @DeleteMapping(value = "/keywords/minus")
    public void deleteMinusKeyword(@RequestHeader Long chatId, @RequestParam String keyword) {
        minusKeywordService.deleteKeyword(keyword, chatId);
    }

    @DeleteMapping(value = "/keywords/minus/all")
    public void deleteAllMinusKeywords(@RequestHeader Long chatId) {
        minusKeywordService.deleteAllKeywords(chatId);
    }

}
