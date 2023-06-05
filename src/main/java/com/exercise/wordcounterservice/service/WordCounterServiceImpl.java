package com.exercise.wordcounterservice.service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WordCounterServiceImpl implements WordCounterService {

	@Autowired
	Translator translator;

	static Map<String, Integer> wordMap = new ConcurrentHashMap<>();

	@Override
	public String addWord(List<String> wordList) {

		wordList.parallelStream().peek(s -> validateInputData(s)).map(s -> translator.translate(s)).forEach(s -> {
			wordMap.put(s, wordMap.getOrDefault(s, 0) + 1);
		});
		return "Word/Words Added successfully!";
	}

	private void validateInputData(String word) {
		if (word == null || word.isEmpty() || word.isBlank() || !word.matches("[a-zA-Z]+")) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	public long getWordCount(String word) {
		validateInputData(word);
		return wordMap.getOrDefault(word, 0);
	}

	public int getWordCounterSize() {
		return wordMap.size();
	}

	public void clearWordCounter() {
		wordMap.clear();
	}
}
