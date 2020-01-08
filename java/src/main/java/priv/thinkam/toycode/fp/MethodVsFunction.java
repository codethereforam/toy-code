package priv.thinkam.toycode.fp;

import java.util.HashMap;
import java.util.Map;

/**
 * 方法和函数对比
 *
 * @author yanganyu
 * @date 2020/1/6 15:31
 */
public class MethodVsFunction {
    // 方法
    public static Map<String, Object> addParamMethod(Map<String, Object> paramMap) {
        paramMap.put("status", 1);
        paramMap.put("mark", 0);
        return paramMap;
    }

    // 函数
    public static Map<String, Object> addParamFunction(Map<String, Object> map) {
        Map<String, Object> result = new HashMap<>(map);
        result.put("status", 1);
        result.put("mark", 0);
        return result;
    }

    // 函数
    public static Map<String, Object> getOtherConditionParam() {
        Map<String, Object> result = new HashMap<>(2);
        result.put("status", 1);
        result.put("mark", 0);
        return result;
    }

    public static void main(String[] args) {
        Map<String, Object> paramMap1 = new HashMap<>(2);
        paramMap1.put("type", 1);
        Map<String, Object> usedParamMap1 = addParamMethod(paramMap1);
        // paramMap1已被修改，产生了副作用，

        Map<String, Object> paramMap2 = new HashMap<>(2);
        paramMap2.put("type", 1);
        Map<String, Object> usedParamMap2 = addParamFunction(paramMap2);

        Map<String, Object> paramMap3 = new HashMap<>(2);
        paramMap3.put("type", 1);
        paramMap3.putAll(getOtherConditionParam());
    }
}