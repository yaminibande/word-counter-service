package com.exercise.wordcounterservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exercise.wordcounterservice.service.WordCounterService;
@CrossOrigin
@RestController
@RequestMapping("/api/words")
public class WordCounterController {
	
	@Autowired
	WordCounterService wordCounterService;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello";
	}
	
	@PostMapping(value="/add")
	public ResponseEntity<String> addWord(@RequestBody List<String> wordList){
		wordCounterService.addWord(wordList);
		return new ResponseEntity<>("Added successfully!", HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/count")
	public ResponseEntity<Integer> getWordCount(@RequestParam String word) {
		Integer count=wordCounterService.getWordCount(word);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}

}
