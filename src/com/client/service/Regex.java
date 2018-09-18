package com.client.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
	public static String regexChecker(String theRegex, String str2Check) {
		Pattern checkRegex = Pattern.compile(theRegex);

		Matcher regexMatcher = checkRegex.matcher(str2Check);

		while (regexMatcher.find()) {
			if (regexMatcher.group().length() != 0) {
				return regexMatcher.group().trim();
			}
			System.out.println("start index" + regexMatcher.start());
			System.out.println("End index" + regexMatcher.end());
		}
		return regexMatcher.group().trim();
	}

	public static String regexReplaceNumbers(String str2Replace) {
		Pattern replace = Pattern.compile("\\d+");
		return replace.matcher(str2Replace).replaceAll("");
		
	}
	
}
