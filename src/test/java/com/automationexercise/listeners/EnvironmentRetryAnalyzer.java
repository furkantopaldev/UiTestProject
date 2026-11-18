package com.automationexercise.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Yalnizca ortam kaynakli (site asiri yuk sayfasi dondurdu) hatalarda testi yeniden dener.
 * Gercek dogrulama hatalari (assertion) hic yeniden denenmez.
 */
public class EnvironmentRetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRIES = 2;
    private static final String ENVIRONMENT_MARKER = "ORTAM SORUNU";
    private static final Map<String, Integer> ATTEMPTS = new ConcurrentHashMap<>();

    @Override
    public boolean retry(ITestResult result) {
        if (!isEnvironmentFailure(result.getThrowable())) {
            return false;
        }
        String key = result.getMethod().getQualifiedName() + Arrays.toString(result.getParameters());
        int used = ATTEMPTS.merge(key, 1, Integer::sum);
        if (used > MAX_RETRIES) {
            return false;
        }
        System.out.println("[YENIDEN DENEME] " + key + " | deneme " + used + "/" + MAX_RETRIES
                + " | neden: site asiri yuk hatasi verdi");
        return true;
    }

    private boolean isEnvironmentFailure(Throwable throwable) {
        Throwable current = throwable;
        while (current != null) {
            String message = current.getMessage();
            if (message != null && message.contains(ENVIRONMENT_MARKER)) {
                return true;
            }
            current = current.getCause();
        }
        return false;
    }
}