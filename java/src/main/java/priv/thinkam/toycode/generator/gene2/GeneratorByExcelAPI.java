package priv.thinkam.toycode.generator.gene2;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 根据表格形式的接口文档生成代码
 *
 * @author yanganyu
 * @date 2021/11/15
 */
public class GeneratorByExcelAPI {
    private static Template temp;

    static {
        try {
            Configuration conf = new Configuration(Configuration.VERSION_2_3_29);
            conf.setDirectoryForTemplateLoading(new File("C:\\Users\\anyu.yang\\Desktop\\"));
            conf.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_29));
            temp = conf.getTemplate("entity.ftl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SneakyThrows
    public static void generator(Map<String, Object> map) {
        FileWriter fw = new FileWriter("C:\\Users\\anyu.yang\\Desktop\\Model.java");
        BufferedWriter bw = new BufferedWriter(fw);
        temp.process(map, bw);
        bw.flush();
        fw.close();
    }

    @SneakyThrows
    public static void main(String[] args) {
        ExcelImportResult<ExcelData> importResult = importFile(new FileInputStream("C:\\Users\\anyu.yang\\Desktop\\api-model.xlsx"), ExcelData.class);
        List<ExcelData> excelDataList = importResult.getList();
        System.out.println(excelDataList);
        List<ExcelData> handledList = handle(excelDataList);
        System.out.println(handledList);
        generator(ImmutableMap.of(
                "excelDataList", handledList,
                "date", LocalDate.now()));
    }

    private static final List<String> VARIABLE_NAME_FILTER_STRING = Lists.newArrayList("msg", "code", "data");

    private static List<ExcelData> handle(List<ExcelData> excelDataList) {
        List<ExcelData> list = new ArrayList<>();
        for (ExcelData excelData : excelDataList) {
            if (!VARIABLE_NAME_FILTER_STRING.contains(StringUtils.lowerCase(StringUtils.trim(excelData.getVariableName())))) {
                ExcelData d = new ExcelData();
                d.setVariableName(StringUtils.stripToEmpty(excelData.getVariableName()));
                d.setType(divideType(StringUtils.trimToEmpty(excelData.getType())));
                String remark = "";
                if (StringUtils.isNotBlank(excelData.getRemark())) {
                    remark = "(" + StringUtils.trimToEmpty(excelData.getRemark()) + ")";
                }
                d.setComment(StringUtils.trimToEmpty(excelData.getComment()) + remark);
                list.add(d);
            }
        }
        return list;
    }

    private static String divideType(String type) {
        if (StringUtils.equalsIgnoreCase(type, "string")) {
            return "String";
        } else if (StringUtils.equalsIgnoreCase(type, "number")) {
            return "Integer";
        } else if (StringUtils.contains(type, "array")) {
            return "List<String>";
        } else {
            return type;
        }
    }


    public static <D> ExcelImportResult<D> importFile(InputStream inputstream, Class<? super D> clazz) {
        try {
            return ExcelImportUtil.importExcelMore(inputstream, clazz, new ImportParams());
        } catch (Exception e) {
            throw new RuntimeException("读取excel文件报错", e);
        }
    }
}
