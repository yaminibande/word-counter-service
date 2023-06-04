package com.exercise.wordcounterservice;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.exercise.wordcounterservice.service.WordCounterServiceImpl;

@SpringBootTest
class WordCounterServiceApplicationTests {
	
	@Autowired
	WordCounterServiceImpl wCounter = new WordCounterServiceImpl();

	@Test
	void testThatUserIsAbleToAddOneWord() {
		List<String> word = new ArrayList<>();
		word.add("Word");
		wCounter.addWord(word);
	    assertTrue(wCounter.getWordCounterSize() == 1);
	    wCounter.clearWordCounter();
	}
	
	@Test
	void testThatUserIsAbleToAddMultipleWords() {
		List<String> word = new ArrayList<>();
		word.add("Word");
		word.add("Counter");
		word.add("Service");
		word.add("Multiple");
		wCounter.addWord(word);
	    assertTrue(wCounter.getWordCounterSize() == 4);
	    wCounter.clearWordCounter();
	}
	
	@Test
	void testThatUserIsUnableToAddAlphaNumbericWords() {
		List<String> word = new ArrayList<>();
		word.add("Word123");
		try {
			wCounter.addWord(word);
		}
		catch (Exception e) {
			assertThatIllegalArgumentException();
		}
	}
	

	@Test
	void testThatUserIsUnableToAddNull() {
		List<String> word = new ArrayList<>();
		word.add(null);
		try {
			wCounter.addWord(word);
		}
		catch (Exception e) {
			assertThatIllegalArgumentException();
		}
	}


	@Test
	void testThatUserIsUnableToAddEmptyString() {
		List<String> word = new ArrayList<>();
		word.add("");
		try {
			wCounter.addWord(word);
		}
		catch (Exception e) {
			assertThatIllegalArgumentException();
		}
	}
	

	@Test
	void testThatUserIsAbleToGetCountSingleInstance() {
		List<String> word = new ArrayList<>();
		word.add("word");
		word.add("counter");
		word.add("service");
		wCounter.addWord(word);
		assertTrue(wCounter.getWordCount("word") == 1, "The count should be 1");
		wCounter.clearWordCounter();
	}

	@Test
	void testThatUserIsAbleToGetCountMultipleInstance() {
		List<String> word = new ArrayList<>();
		word.add("word");
		word.add("word");
		word.add("word");
		word.add("counter");
		word.add("counter");
		word.add("counter");
		word.add("counter");
		word.add("service");
		wCounter.addWord(word);
		assertTrue(wCounter.getWordCount("word") == 3, "The count for word 'word' should be 3");
		assertTrue(wCounter.getWordCount("counter") == 4, "The count for word 'counter' should be 4");
		assertTrue(wCounter.getWordCount("service") == 1, "The count for word 'service' should be 1");
		wCounter.clearWordCounter();
	}

}
