package net.lanternmc.AllUtils.JavaUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class ReflectUtil {
    public static Field makeField(Class<?> paramClass, String paramString) {
        try {
            return paramClass.getDeclaredField(paramString);
        } catch (NoSuchFieldException noSuchFieldException) {
            throw new RuntimeException(noSuchFieldException);
        } 
    }
    
    public static void setField(Field paramField, Object paramObject1, Object paramObject2) {
        paramField.setAccessible(true);
        try {
            paramField.set(paramObject1, paramObject2);
        } catch (IllegalArgumentException|IllegalAccessException illegalArgumentException) {
            throw new RuntimeException(illegalArgumentException);
        } 
    }
    
    public static Method makeMethod(Class<?> paramClass, String paramString, Class<?>... paramVarArgs) {
        try {
            return paramClass.getDeclaredMethod(paramString, paramVarArgs);
        } catch (NoSuchMethodException noSuchMethodException) {
            throw new RuntimeException(noSuchMethodException);
        } 
    }
    
    public static <T> T callMethod(Method paramMethod, Object paramObject, Object... paramVarArgs) {
        paramMethod.setAccessible(true);
        try {
            return (T)paramMethod.invoke(paramObject, paramVarArgs);
        } catch (IllegalArgumentException|IllegalAccessException illegalArgumentException) {
            throw new RuntimeException(illegalArgumentException);
        } catch (InvocationTargetException invocationTargetException) {
            throw new RuntimeException(invocationTargetException.getCause());
        } 
    }
    
    public static void putInPrivateStaticMap(Class<?> paramClass, String paramString, Object paramObject1, Object paramObject2) throws Exception {
        Field field = paramClass.getDeclaredField(paramString);
        field.setAccessible(true);
        Map<Object, Object> map = (Map)field.get(null);
        map.put(paramObject1, paramObject2);
    }
    
    public static void setPrivateField(Class<?> paramClass, Object paramObject1, String paramString, Object paramObject2) throws Exception {
        Field field = paramClass.getDeclaredField(paramString);
        field.setAccessible(true);
        field.set(paramObject1, paramObject2);
    }
    
    public static Object getPrivateField(Class<?> paramClass, Object paramObject, String paramString) throws Exception {
        Field field = paramClass.getDeclaredField(paramString);
        field.setAccessible(true);
        return field.get(paramObject);
    }
    
    public static <T> void setField(String paramString, T paramT1, Class<?> paramClass, T paramT2) {
        try {
            Field field = paramClass.getDeclaredField(paramString);
            field.setAccessible(true);
            field.set(paramClass.cast(paramT2), paramT1);
        } catch (NoSuchFieldException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
        } catch (SecurityException securityException) {
            securityException.printStackTrace();
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } 
    }
    
    public static <T> void setFinalField(String paramString, T paramT1, Class<?> paramClass, T paramT2) {
        try {
            Field field1 = paramClass.getDeclaredField(paramString);
            field1.setAccessible(true);
            Field field2 = Field.class.getDeclaredField("modifiers");
            field2.setAccessible(true);
            field2.setInt(field1, field1.getModifiers() & 0xFFFFFFEF);
            field1.set(paramClass.cast(paramT2), paramT1);
        } catch (NoSuchFieldException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
        } catch (SecurityException securityException) {
            securityException.printStackTrace();
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } 
    }
    
    private static <T> boolean isFieldExists(String paramString, Class<?> paramClass, T paramT) {
        try {
            paramClass.getDeclaredField(paramString);
        } catch (NoSuchFieldException noSuchFieldException) {
            return false;
        } catch (SecurityException securityException) {
            securityException.printStackTrace();
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        } 
        return true;
    }
    
    public static <T, A> A getField(String paramString, Class<?> paramClass, T paramT) {
        try {
            Field field = paramClass.getDeclaredField(paramString);
            field.setAccessible(true);
            return (A)field.get(paramT).getClass().cast(field.get(paramT));
        } catch (NoSuchFieldException noSuchFieldException) {
            noSuchFieldException.printStackTrace();
        } catch (SecurityException securityException) {
            securityException.printStackTrace();
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            return null;
        } 
        return null;
    }
    
    public static <T, A> A get(String paramString1, String paramString2, Class<?> paramClass, T paramT) {
        try {
            String str = isFieldExists(paramString1, paramClass, paramT) ? paramString1 : paramString2;
            Field field = paramClass.getDeclaredField(str);
            field.setAccessible(true);
            return (A)field.get(paramT).getClass().cast(field.get(paramT));
        } catch (NoSuchFieldException noSuchFieldException) {
            System.out.println("None of the two fields found: " + paramString1 + " and " + paramString2);
            noSuchFieldException.printStackTrace();
        } catch (SecurityException securityException) {
            securityException.printStackTrace();
        } catch (IllegalArgumentException illegalArgumentException) {
            illegalArgumentException.printStackTrace();
        } catch (IllegalAccessException illegalAccessException) {
            illegalAccessException.printStackTrace();
        } catch (NullPointerException nullPointerException) {
            return null;
        } 
        return null;
    }
}
