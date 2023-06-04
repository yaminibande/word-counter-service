package com.exercise.wordcounterservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordCounterServiceImpl implements WordCounterService{
	
	@Autowired
	Translator translator;
	
	static Map<String,Integer> wordMap = new HashMap<>();

	@Override
	public void addWord(List<String> wordList) {

		validateAddWordInputData(wordList);
		wordList.stream().map(s -> translator.translate(s.strip())).forEach(s -> {
			synchronized (s) {
				wordMap.put(s, wordMap.getOrDefault(s, 0) + 1);
			}
		});
	}
	
	private void validateAddWordInputData(List<String> wordList) {
		wordList.parallelStream().map(s -> s.strip()).forEach(s -> {
			if (s == null || s.isEmpty() || s.isBlank() || !s.matches("[a-zA-Z]+")) {
				throw new IllegalArgumentException();
			}
		});
	}
	
	private void validateWordCountInputData(String word) {
		if (word == null || word.isEmpty() || word.isBlank() || !word.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public Integer getWordCount(String word) {
		validateWordCountInputData(word);
		return wordMap.getOrDefault(word, 0);
	}

	public int getWordCounterSize() {
		return wordMap.size();
	}
	
	public void clearWordCounter() {
		wordMap.clear();
	}
}
