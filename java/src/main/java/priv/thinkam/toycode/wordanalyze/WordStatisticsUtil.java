package priv.thinkam.toycode.wordanalyze;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yanganyu
 * @date 2023/8/20 21:24
 */
public class WordStatisticsUtil {

    @Deprecated
    static List<String> extractWordsDeprecated(String filePath) throws IOException {
        String article = Files.readString(Path.of(filePath));
        StringTokenizer t = new StringTokenizer(article);
        List<String> words = new ArrayList<>();
        while (t.hasMoreTokens()) {
            words.add(t.nextToken());
        }
        return words;
    }

    static Set<String> extractWords(String filePath) throws IOException {
        String article = Files.readString(Path.of(filePath));
        LinkedHashSet<String> words = new LinkedHashSet<>();

        // 使用正则表达式匹配单词
        Pattern pattern = Pattern.compile("\\b[a-zA-Z'-]+\\b");
        Matcher matcher = pattern.matcher(article);

        while (matcher.find()) {
            String word = matcher.group();
            word = StringUtils.removeEndIgnoreCase(word, "'s");
            word = StringUtils.removeEndIgnoreCase(word, "won't");
            word = StringUtils.removeEndIgnoreCase(word, "n't");
            word = StringUtils.removeEndIgnoreCase(word, "'m");
            word = StringUtils.removeEndIgnoreCase(word, "'t");
            word = StringUtils.removeEndIgnoreCase(word, "'re");
            word = StringUtils.removeEndIgnoreCase(word, "'d");
            word = StringUtils.removeEndIgnoreCase(word, "'ve");
            if (StringUtils.length(word) > 1) {
                words.add(word);
            } else {
                // not add
            }
        }

        return words;
    }


    public static void main(String[] args) throws Exception {
        String filePath = "C:\\Users\\anyu.yang\\Downloads\\soho, new york - 英语 (自动生成).txt";
//        extractWords(filePath).forEach(System.out::println);
//        WordAnalyzeUtil.findIn( extractWords(filePath),
//                WordAnalyzeUtil.getUniqueWordsSet("C:\\Users\\anyu.yang\\Downloads\\my words.txt")
//                , false)
//                .forEach(System.out::println);

        WordAnalyzeUtil.findIn( extractWords(filePath),
                        WordAnalyzeUtil.getUniqueWordsSet("C:\\Users\\anyu.yang\\Downloads\\COCA_20000.txt")
                        , false)
                .forEach(System.out::println);
    }

}
