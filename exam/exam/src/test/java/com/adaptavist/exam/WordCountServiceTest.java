package com.adaptavist.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

class WordCountServiceTest {

	WordCountService wordCountService;
	@Mock
	BufferedReader bufferedReader;
	final String line1 = "Hello World, Hello Hi, this is the first line of a sentence\n" +
						 "but there's a another line we want to test here. End.";
	final String line2 = "What's Up, Doc, Hello Hi.";

	@BeforeEach
	void init() throws Exception {
		MockitoAnnotations.openMocks(this);
		wordCountService = spy(new WordCountService());
		doReturn(line1).doReturn(line2).doReturn(null).when(bufferedReader).readLine();
	}

	@Test
	void thatGetsWordCount() throws Exception {
		doReturn(bufferedReader).when(wordCountService).createBuffferedReader("test.txt");
		Map<String, Integer> wordCount = wordCountService.countWords("test.txt");
		assertEquals(23, wordCount.size());
		assertTrue(wordCount.containsKey("hello"));
		assertEquals(3, wordCount.get("hello"));
		assertTrue(wordCount.containsKey("world"));
		assertEquals(1, wordCount.get("world"));
		assertTrue(wordCount.containsKey("hi"));
		assertEquals(2, wordCount.get("hi"));
		assertTrue(wordCount.containsKey("theres"));
		assertEquals(1, wordCount.get("theres"));
	}

	@Test
	void thatSorted() throws Exception {
		doReturn(bufferedReader).when(wordCountService).createBuffferedReader("test.txt");
		Map<String, Integer> wordCount = wordCountService.countWords("test.txt");
		List<Integer> values = wordCount.values().stream().toList();
		assertEquals(3, values.get(0));
	}

	@Test
	void thatThrowsException() {
		assertThrows(IOException.class, () ->  wordCountService.countWords("test.txt"));
	}

}
