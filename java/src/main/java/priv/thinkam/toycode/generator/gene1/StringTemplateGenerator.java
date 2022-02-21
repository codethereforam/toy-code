package priv.thinkam.toycode.generator.gene1;

import com.google.common.base.Splitter;
import priv.thinkam.toycode.util.StringUtil;

/**
 * 根据字符串模板生成代码
 *
 * @author yanganyu
 * @date 2022/2/11
 */
public class StringTemplateGenerator {
    static String str = "皮肤光滑度,肌肤敏感度,痘痘整体情况";
    static String template = "private static String {}(SkinReportDTO skinReportDTO) {\n" +
            "        return null;\n" +
            "    }";

    public static void main(String[] args) {
        for (String str : Splitter.on(",")
                .trimResults()
                .omitEmptyStrings()
                .split(str)) {
            System.out.println(StringUtil.format(template, str));
            System.out.println();
        }
    }
}
