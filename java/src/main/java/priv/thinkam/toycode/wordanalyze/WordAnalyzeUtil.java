package priv.thinkam.toycode.wordanalyze;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author yanganyu
 * @date 2023/8/20 21:24
 */
public class WordAnalyzeUtil {

    static List<String> findDuplicateWord(String filePath) throws IOException {
        Set<String> uniqueWords = new HashSet<>();
        List<String> duplicateWords = new ArrayList<>();
        for (String word : Files.readAllLines(Path.of(filePath))) {
            if (StringUtils.isBlank(word)) {
                // skip
            } else {
                String trimmedLowerCaseWord = word.trim().toLowerCase();
                if (uniqueWords.add(trimmedLowerCaseWord)) {
                    // 不用返回
                } else {
                    duplicateWords.add(word);
                }
            }
        }
        return duplicateWords;
    }

    static List<String> findUniqueWordsInOrder(String filePath) throws IOException {
        Set<String> uniqueWordSet = new HashSet<>();
        List<String> uniqueWordList = new ArrayList<>();
        for (String word : Files.readAllLines(Path.of(filePath))) {
            if (StringUtils.isBlank(word)) {
                // skip
            } else {
                String trimmedLowerCaseWord = word.trim().toLowerCase();
                if (uniqueWordSet.add(trimmedLowerCaseWord)) {
                    uniqueWordList.add(word.trim());
                } else {

                }
            }
        }
        return uniqueWordList;
    }

    static List<String> findIn(String pathA, String pathB, boolean isIn) throws IOException {
        Set<String> referenceWordSet = getUniqueWordsSet(pathB);
        return findIn(pathA, referenceWordSet, isIn);
    }

    static List<String> findIn(String pathA, Set<String> referenceWordSet, boolean isIn) throws IOException {
        List<String> allWords = Files.readAllLines(Path.of(pathA));
        return findIn(allWords, referenceWordSet, isIn);
    }

    static List<String> findIn(Collection<String> allWords, Set<String> referenceWordSet, boolean isIn) {
        List<String> resultWords = new ArrayList<>();
        for (String word : allWords) {
            if (StringUtils.isBlank(word)) {
                // skip
            } else {
                String trimmedLowerCaseWord = word.trim().toLowerCase();
                if (referenceWordSet.contains(trimmedLowerCaseWord)) {
                    if (isIn) {
                        resultWords.add(word);
                    }
                } else {
                    if (!isIn) {
                        resultWords.add(word.trim());
                    }
                }
            }
        }
        return resultWords;
    }

    static Set<String> getUniqueWordsSet(String pathB) throws IOException {
        return Files.readAllLines(Path.of(pathB)).stream()
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
    }

    static List<String> getUniqueWordsList(String filePath) throws IOException {
        return Files.readAllLines(Path.of(filePath)).stream()
                .filter(StringUtils::isNotBlank)
                .map(String::trim)
                .map(String::toLowerCase)
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(findDuplicateWord("C:\\Users\\thinkam\\Downloads\\COCA_20000.txt"));
//        System.out.println(getUniqueWordsSet("C:\\Users\\thinkam\\Downloads\\COCA_20000 改.txt").size());
//        System.out.println(findIn("C:\\Users\\thinkam\\Downloads\\my words.txt",
//                "C:\\Users\\thinkam\\Downloads\\COCA_20000 改.txt", true).size());
//        Files.write(Paths.get("C:\\Users\\thinkam\\Downloads\\my words uk.txt"), findUniqueWordsInOrder("C:\\Users\\thinkam\\Downloads\\my words.txt"), Charsets.UTF_8);

//        System.out.println(findIn("C:\\Users\\thinkam\\Downloads\\COCA_20000 改.txt",
//                "C:\\Users\\thinkam\\Downloads\\my words.txt", false).size());
        Files.write(Paths.get("C:\\Users\\thinkam\\Downloads\\COCA_20000 改 500.txt"), findIn("C:\\Users\\thinkam\\Downloads\\COCA_20000 改.txt",
                "C:\\Users\\thinkam\\Downloads\\my words.txt", true), Charsets.UTF_8);
//         Files.write(Paths.get("C:\\Users\\thinkam\\Downloads\\COCA_20000 改1.txt"), findIn("C:\\Users\\thinkam\\Downloads\\COCA_20000 改.txt",
//                 "C:\\Users\\thinkam\\Downloads\\my words.txt", false), Charsets.UTF_8);

    }

}
