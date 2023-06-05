package com.exercise.wordcounterservice.service;

import java.util.List;

public interface WordCounterService {

	public String addWord(List<String> wordList);

	public long getWordCount(String word);
	

}
