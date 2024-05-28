package com.adaptavist.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class WordCountRunner implements CommandLineRunner {

	Logger logger = LoggerFactory.getLogger(WordCountRunner.class);

	private final WordCountService wordCountService;

	public WordCountRunner(WordCountService wordCountService) {
		this.wordCountService = wordCountService;
	}

	@Override
	public void run(String... args) throws IOException {

		if (args.length != 1 || args[0].isBlank()) {
			logger.error("No File Path Provided. Please provide the file path as an argument.");
			return;
		}
		logger.info("EXECUTING : command line runner");
		String filePath = args[0];
		Map<String, Integer> wordCountMap = wordCountService.countWords(filePath);
		wordCountMap.forEach((word, count) -> System.out.println(word + ": " + count));
	}
}
