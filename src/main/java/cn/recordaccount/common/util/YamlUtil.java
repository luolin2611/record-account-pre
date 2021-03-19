package cn.recordaccount.common.util;

import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author rollin
 * @date 2021-02-25 14:24:12
 */
@Slf4j
public class YamlUtil {
    private static Map<String, Map<String, Object>> ymlMap = new HashMap<>();

    static  {
        Yaml yaml = new Yaml();
        String activeProfile = SpringContextUtil.getActiveProfile();
        InputStream in = YamlUtil.class.getClassLoader().getResourceAsStream("application-" + activeProfile + ".yml");
        try {
            ymlMap = yaml.loadAs(in, HashMap.class);
        } catch (Exception e) {
            log.error("读取yml配置文件报错！！");
            e.printStackTrace();
        }
    }

    public static String getValue(String key) {
        String separator = ".";
        String[] separatorKeys = null;
        if (key.contains(separator)) {
            separatorKeys = key.split("\\.");
        } else {
            return ymlMap.get(key).toString();
        }
        Map<String, Map<String, Object>> finalValue = new HashMap<>();
        for (int i = 0; i < separatorKeys.length - 1; i++) {
            if (i == 0) {
                finalValue = (Map) ymlMap.get(separatorKeys[i]);
                continue;
            }
            if (finalValue == null) {
                break;
            }
            finalValue = (Map) finalValue.get(separatorKeys[i]);
        }
        return finalValue == null ? null : (finalValue.get(separatorKeys[separatorKeys.length - 1])+"");
    }
}
