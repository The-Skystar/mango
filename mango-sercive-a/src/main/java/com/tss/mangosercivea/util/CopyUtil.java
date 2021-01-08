package com.tss.mangosercivea.util;

import com.esotericsoftware.reflectasm.MethodAccess;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 基于ReflectASM的对象拷贝工具
 * Created by yangxiangjun on 2020/12/18.
 */
public class CopyUtil {
    private static ConcurrentMap<Class, MethodAccess> localCache = new ConcurrentHashMap<>();

    public static MethodAccess get(Class clazz) {
        if (localCache.containsKey(clazz)) {
            return localCache.get(clazz);
        }

        MethodAccess methodAccess = MethodAccess.get(clazz);
        localCache.putIfAbsent(clazz, methodAccess);
        return methodAccess;
    }

    public static void copyProperties(Object source, Object target) {
        copyProperties(source, target, false, (String[]) null);
    }

    public static void copyProperties(Object source, Object target, String... ignoreProperties) {
        copyProperties(source, target, false, ignoreProperties);
    }

    /**
     * 使用ReflectASM实现的拷贝方法
     * @param source
     * @param target
     * @param ignoreNull
     * @param ignoreProperties
     */
    public static void copyProperties(Object source, Object target, boolean ignoreNull, String... ignoreProperties) {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");

        MethodAccess sourceMethodAccess = get(source.getClass());
        MethodAccess targetMethodAccess = get(target.getClass());

        Field[] declaredFields = source.getClass().getDeclaredFields();
        List<String> ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        for (Field field : declaredFields) {
            if (targetMethodAccess != null && (ignoreList == null || !ignoreList.contains(field.getName()))) {
                //得到获取属性值得方法
                String getKey;
                if (field.getType() == boolean.class) {
                    getKey = "is" + StringUtils.capitalize(field.getName());
                } else {
                    getKey = "get" + StringUtils.capitalize(field.getName());
                }

                //忽略引用数据类型的空值
                Object value = sourceMethodAccess.invoke(source, getKey, null);
                if (ignoreNull && value == null) {
                    continue;
                }

                //将源目标的值写到目标类中
                String setKey = "set" + StringUtils.capitalize(field.getName());
                int index = targetMethodAccess.getIndex(setKey);
                targetMethodAccess.invoke(target, index, value);
            }
        }
    }

    /**
     * 改造BeanUtils的copyProperties方法
     * @param source
     * @param target
     * @param editable
     * @param ignoreNull
     * @param ignoreProperties
     * @throws BeansException
     */
    public static void copyProperties(Object source, Object target, @Nullable Class<?> editable, boolean ignoreNull, @Nullable String... ignoreProperties) throws BeansException {
        Assert.notNull(source, "Source must not be null");
        Assert.notNull(target, "Target must not be null");
        Class<?> actualEditable = target.getClass();
        if (editable != null) {
            if (!editable.isInstance(target)) {
                throw new IllegalArgumentException("Target class [" + target.getClass().getName() + "] not assignable to Editable class [" + editable.getName() + "]");
            }

            actualEditable = editable;
        }

        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(actualEditable);
        List<String> ignoreList = ignoreProperties != null ? Arrays.asList(ignoreProperties) : null;
        PropertyDescriptor[] var7 = targetPds;
        int var8 = targetPds.length;

        for(int var9 = 0; var9 < var8; ++var9) {
            PropertyDescriptor targetPd = var7[var9];
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && (ignoreList == null || !ignoreList.contains(targetPd.getName()))) {
                PropertyDescriptor sourcePd = BeanUtils.getPropertyDescriptor(source.getClass(), targetPd.getName());
                if (sourcePd != null) {
                    Method readMethod = sourcePd.getReadMethod();
                    if (readMethod != null && ClassUtils.isAssignable(writeMethod.getParameterTypes()[0], readMethod.getReturnType())) {
                        try {
                            if (!Modifier.isPublic(readMethod.getDeclaringClass().getModifiers())) {
                                readMethod.setAccessible(true);
                            }

                            Object value = readMethod.invoke(source);

                            if (ignoreNull && value == null){
                                continue;
                            }

                            if (!Modifier.isPublic(writeMethod.getDeclaringClass().getModifiers())) {
                                writeMethod.setAccessible(true);
                            }

                            writeMethod.invoke(target, value);
                        } catch (Throwable var15) {
                            throw new FatalBeanException("Could not copy property '" + targetPd.getName() + "' from source to target", var15);
                        }
                    }
                }
            }
        }

    }
}
