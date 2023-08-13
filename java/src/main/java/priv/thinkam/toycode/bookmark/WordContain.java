package priv.thinkam.toycode.bookmark;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 找出文章中存在的单词
 *
 * @date 2023-08-13
 */
public class WordContain {
	private static final String ARTICLE_FILE_PATH = "";
	private static final String WORDS_FILE_PATH = "";
	private static final Pattern PATTERN = Pattern.compile("\\.\\s((\\w|\\s)+)");

	public static void main(String[] args) throws  Exception {
		String article = Files.readString(Path.of(ARTICLE_FILE_PATH));
		File wordsFile = new File(WORDS_FILE_PATH);
		Set<String> existentWords = new HashSet<>();
		for (String wordFileLine : Files.readAllLines(wordsFile.toPath())) {
			if (StringUtils.isNotBlank(wordFileLine)) {
				String word = extractWord(wordFileLine);
				if (StringUtils.isBlank(word)) {
					throw new IllegalStateException("存在行解析不出单词");
				} else {
					if (existentWords.contains(word)) {
						// 重复的单词不需要
					} else {
						if (contains(article, word)) {
							existentWords.add(word);
							System.out.println(wordFileLine);
						} else {
							// 不包含就不要
						}
					}
				}
			}
		}
	}


	private static String extractWord(String line) {
		Matcher matcher = PATTERN.matcher(line);
		if (matcher.find()) {
			return matcher.group(1).trim();
		} else {
			return null;
		}
	}

	private static boolean contains(String article, String word) {
		return article.contains(word);
	}
}
