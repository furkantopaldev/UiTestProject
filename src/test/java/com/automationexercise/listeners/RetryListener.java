package com.automationexercise.listeners;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

/** Her teste ortam hatasi icin yeniden deneme mekanizmasini baglar (testlerde ayri ayri yazmaya gerek kalmaz). */
public class RetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        annotation.setRetryAnalyzer(EnvironmentRetryAnalyzer.class);
    }
}