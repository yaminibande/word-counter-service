package com.exercise.wordcounterservice.service;

import java.util.List;

public interface WordCounterService {

	public void addWord(List<String> wordList);

	public Integer getWordCount(String word);
	

}
