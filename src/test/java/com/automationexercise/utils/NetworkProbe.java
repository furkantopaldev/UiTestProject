package com.automationexercise.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

/**
 * Tanilama araci: sayfadaki tiklamalari ve XHR (AJAX) isteklerini kaydeder.
 * Davranisi degistirmez; yalnizca bir test basarisiz olunca "tiklama sayfaya ulasti mi,
 * istek gitti mi, yanit geldi mi, sayfa arada yenilendi mi" sorularina cevap verir.
 */
public final class NetworkProbe {

    private record Installation(WebDriver driver, String origin, String error) {
    }

    private static final ThreadLocal<Installation> LAST = new ThreadLocal<>();

    private static final String INSTALL_SCRIPT = """
            if (!window.__probeInstalled) {
              window.__probeInstalled = true;
              window.__probeClicks = [];
              window.__probeNet = [];
              document.addEventListener('click', function (e) {
                var t = e.target;
                var id = t.getAttribute ? t.getAttribute('data-product-id') : '';
                window.__probeClicks.push((t.tagName || '?') + '.' + (t.className || '') + ' id=' + id);
              }, true);
              var open = XMLHttpRequest.prototype.open;
              var send = XMLHttpRequest.prototype.send;
              XMLHttpRequest.prototype.open = function (method, url) {
                this.__probeLabel = method + ' ' + url;
                return open.apply(this, arguments);
              };
              XMLHttpRequest.prototype.send = function () {
                var entry = { req: this.__probeLabel, state: 'beklemede', t0: Date.now() };
                window.__probeNet.push(entry);
                var xhr = this;
                this.addEventListener('loadend', function () {
                  entry.state = 'bitti status=' + xhr.status;
                  entry.ms = Date.now() - entry.t0;
                });
                return send.apply(this, arguments);
              };
            }
            return String(performance.timeOrigin);
            """;

    private static final String DUMP_SCRIPT = """
            return JSON.stringify({
              clicks: window.__probeClicks || null,
              net: window.__probeNet || null,
              readyState: document.readyState,
              href: location.href
            });
            """;

    private static final String ORIGIN_SCRIPT = "return String(performance.timeOrigin);";

    private NetworkProbe() {
    }

    /** Sayfaya kayit kodunu yerlestirir. Ayni belgede tekrar cagrilmasi zararsizdir. */
    public static void install(WebDriver driver) {
        try {
            Object origin = ((JavascriptExecutor) driver).executeScript(INSTALL_SCRIPT);
            LAST.set(new Installation(driver, String.valueOf(origin), null));
        } catch (Exception e) {
            LAST.set(new Installation(driver, null, e.getClass().getSimpleName() + ": " + e.getMessage()));
        }
    }

    /** Kaydedilen tiklama ve istekleri, kurulum durumuyla birlikte metin olarak dondurur. */
    public static String dump(WebDriver driver) {
        Installation installation = LAST.get();
        String status;
        if (installation == null || installation.driver() != driver) {
            status = "KURULUM HIC CAGRILMADI";
        } else if (installation.error() != null) {
            status = "KURULUM HATASI: " + installation.error();
        } else {
            status = "kurulum yapildi";
        }
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            if (installation != null && installation.driver() == driver && installation.origin() != null) {
                String current = String.valueOf(js.executeScript(ORIGIN_SCRIPT));
                status += current.equals(installation.origin())
                        ? " | AYNI BELGE (sayfa yenilenmemis)"
                        : " | BELGE DEGISMIS (kurulumdan sonra sayfa yenilendi ya da yonlendi)";
            }
            return status + " | " + js.executeScript(DUMP_SCRIPT);
        } catch (Exception e) {
            return status + " | okunamadi: " + e.getMessage();
        }
    }
}