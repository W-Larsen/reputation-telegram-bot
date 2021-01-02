package com.telegram.rtb.controller;

import com.telegram.rtb.model.keyword.PlusKeywords;
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
 * Plus keyword controller.
 *
 * @author Valentyn Korniienko
 */
@RestController
public class PlusKeywordController {

    @Autowired
    @Qualifier("plusKeywordService")
    private IKeywordService plusKeywordService;

    @GetMapping(value = "/keywords/plus")
    public PlusKeywords getAllPlusKeywords(@RequestHeader Long chatId) {
        return (PlusKeywords) plusKeywordService.getKeywordsByChatId(chatId);
    }

    @PostMapping(value = "/keywords/plus")
    public void addPlusKeyword(@RequestHeader Long chatId, @RequestParam String keyword) {
        plusKeywordService.addKeyword(keyword, chatId);
    }

    @PostMapping(value = "/keywords/plus/batch")
    public void addBatchPlusKeywords(@RequestHeader Long chatId, @RequestParam List<String> keywords) {
        plusKeywordService.saveKeywords(keywords, chatId);
    }

    @DeleteMapping(value = "/keywords/plus")
    public void deletePlusKeyword(@RequestHeader Long chatId, @RequestParam String keyword) {
        plusKeywordService.deleteKeyword(keyword, chatId);
    }

    @DeleteMapping(value = "/keywords/plus/all")
    public void deleteAllPlusKeywords(@RequestHeader Long chatId) {
        plusKeywordService.deleteAllKeywords(chatId);
    }

}
