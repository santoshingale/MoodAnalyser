package com.bridgelabz;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MoodAnalyzerReflector {

    public static MoodAnalyzer createMoodAnalyser(String message) {
        try {
            Class<?> moodAnalyserClass = Class.forName("com.bridgelabz.MoodAnalyzer");
            Constructor<?> moodConstructor = moodAnalyserClass.getConstructor(String.class);
            Object moodObj = moodConstructor.newInstance(message);
            MoodAnalyzer moodAnalyzer = (MoodAnalyzer) moodObj;
            return moodAnalyzer;

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Constructor getConstructor(Class<?>... parameter) throws NoSuchMethodException, ClassNotFoundException {

        Class<?> moodAnalyserClass = Class.forName("com.bridgelabz.MoodAnalyzer");
        Constructor<?> moodConstructor = moodAnalyserClass.getConstructor(parameter);
        return moodConstructor;

    }

    public static Object getObject(Constructor constructor, String... message) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        Object moodObj = constructor.newInstance(message);
        return moodObj;
    }

    public static Object getMethod(Object object, String methodName) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> objectClass = object.getClass();
        Method method = objectClass.getMethod(methodName);
        Object obj = method.invoke(object);
        return obj;
    }
}
