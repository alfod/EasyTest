package me.alfod.easytest;

import com.alibaba.fastjson.JSON;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Yang Dong
 * @createTime 2017/7/10  18:37
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2017/7/10  18:37
 * @note
 */
public class EasyTest {
    private static String defaStr = "test";
    private static int defaInt = 0;
    private static long defaLong = 0l;
    private static double defaDouble = 0.0;
    private static BigDecimal defaBigDecimal = new BigDecimal(0);

    public static String emerge(Object object) {
        Class<?> clazz = object.getClass();

        Field[] fields = getAllFields(clazz);
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.getType() == Integer.class) {
                    field.set(object, defaInt);
                }
                if (field.getType() == String.class) {
                    field.set(object, defaStr);
                }
                if (field.getType() == Date.class) {
                    field.set(object, new Date());
                }
                if (field.getType() == Long.class) {
                    field.set(object, defaLong);
                }
                if (field.getType() == Double.class) {
                    field.set(object, defaDouble);
                }
                if (field.getType() == BigDecimal.class) {
                    field.set(object, defaBigDecimal);
                }
            }
            return JSON.toJSONString(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Field[] getAllFields(Class<?> clazzType) {
        List<Field> fields = new LinkedList<>();
        while (true) {
            fields.addAll(Arrays.asList(clazzType.getDeclaredFields()));
            if (clazzType.getSuperclass() != null
                    && !clazzType.getSimpleName().equals("Object")) {
                clazzType = clazzType.getSuperclass();
            } else {
                break;
            }
        }
        return fields.toArray(new Field[]{});
    }
}
