# Framework Secimi ve Karsilastirmasi

Proje: Automation Exercise UI test otomasyonu
Tarih: Kasim 2026
Secilen: **Selenium 4 + Java 17 + TestNG + Allure**

## 1. Amac

UI test otomasyonu icin kullanilabilecek frameworkleri karsilastirmak ve secimin gerekcesini yazmak. Uc framework karsilastirildi: Selenium 4, Playwright ve Cypress. **Projede yalnizca Selenium kullanildi**; Playwright ve Cypress sutunlari genel bilgi ve resmi dokumantasyona dayanir, bu projede bu iki frameworkle olcum yapilmadi.

## 2. Karsilastirma

| Kriter | Selenium 4 | Playwright | Cypress |
|---|---|---|---|
| Desteklenen diller | Java, Python, C#, JavaScript, Ruby, Kotlin | JavaScript/TypeScript, Python, Java, .NET | Yalnizca JavaScript/TypeScript |
| Tarayicilar | Chrome, Edge, Firefox, Safari (gercek tarayicilar, WebDriver ile) | Chromium, Firefox, WebKit | Chrome ailesi, Firefox, Edge, Electron |
| Locator ozellikleri | id, name, className, tagName, linkText, CSS, XPath, relative locator | CSS, XPath, metin, rol (`getByRole`), etiket, test id, zincirleme ve filtreleme | Agirlikla CSS (`cy.get`), metin (`cy.contains`); XPath ve rol tabanli locator icin eklenti |
| Bekleme mekanizmasi | Elementler icin otomatik bekleme yok: implicit, explicit (`WebDriverWait`), fluent wait | Yerlesik otomatik bekleme (islemden once uygunluk kontrolleri) | Komut ve dogrulamalari zaman asimina kadar kendiliginden tekrar dener |
| Assertion | Yerlesik yok; TestNG, JUnit ya da AssertJ ile | Yerlesik, otomatik yeniden deneyen `expect` | Yerlesik (Chai, jQuery) |
| Raporlama | Yerlesik rapor yok; Allure, ExtentReports ya da TestNG raporu | Yerlesik HTML rapor, trace goruntuleyici, video | Ekran goruntusu ve video yerlesik; rapor icin eklenti ya da bulut hizmeti |
| Hata ani kaniti | Kendimiz yazariz (ekran goruntusu kodu) | Yerlesik ekran goruntusu, video, trace | Yerlesik ekran goruntusu ve video |
| Paralel calistirma | TestNG/JUnit + Selenium Grid | Yerlesik (worker) | CI'da bolme ya da bulut hizmeti |
| Bakim | Daha cok altyapi kodu gerekir; tam esneklik, cok genis topluluk | Daha az kod, modern API | Kolay baslangic; bazi senaryolarda kisitlar (ornegin coklu sekme) |

Not: Tablo genel bilgidir; surumlere gore degisebilir. Kesin ozellikler icin selenium.dev, playwright.dev ve cypress.io dokumanlarina bakilmalidir.

## 3. Neden Selenium + Java?

1. **Standart ve yaygin:** Selenium tarayiciyi W3C WebDriver standardiyla yonetir; sektorde en yaygin ve en uzun omurlu araclardan biridir. Ogrenilen beceri is ortaminda dogrudan kullanilabilir.
2. **Bekleme stratejilerini ogrenmeyi zorunlu kildi:** Selenium'da otomatik bekleme olmadigi icin statik ve dinamik beklemenin farkini bizzat yasadik ve `WaitHelper` sinifini kendimiz yazdik. Odevin bekleme maddesi icin bu bir avantajdi.
3. **Gercek tarayicilarda calisir:** Chrome'u gercek bir kullanici gibi yonetir; Selenium Grid ile ileride paralel ve dagitik calistirmaya uygundur (`DriverFactory` bunun icin `ThreadLocal` kullanir).
4. **Java'nin guclu tip denetimi ve TestNG/Allure ekosistemi:** Yazim hatalari testler calismadan derleme sirasinda yakalanir; TestNG ile `DataProvider`, gruplar (karantina) ve dinleyiciler, Allure ile gruplanmis rapor kullanildi.

## 4. Bu projede yasadiklarimiz

Selenium'da hazir gelmeyen ve bizim kurdugumuz parcalar:

| Ihtiyac | Selenium'da ne yaptik? | Playwright'ta genel olarak |
|---|---|---|
| Bekleme | `WaitHelper`: `visible`, `clickable`, `invisible`, `urlContains`; implicit wait kapali. Olcum: ayni adim dinamik beklemeyle 53 ms, 5000 ms'lik statik beklemeyle 5067 ms | Yerlesik otomatik bekleme |
| Hata ani kaniti | `BaseTest.tearDown`: ekran goruntusu, URL, tarayici konsolu, ag kaydi | Yerlesik ekran goruntusu, video, trace |
| Rapor | Allure (`@Step`, `@Epic`, `@Feature`, `@Severity`), `mvn allure:serve` | Yerlesik HTML rapor |
| Gecici site hatasinda yeniden deneme | `RetryListener` ve `EnvironmentRetryAnalyzer`: yalnizca "ORTAM SORUNU" isaretli hatalar en fazla 2 kez | Yerlesik `retries` ayari |
| Reklam engelleme | `DriverFactory`: CDP `Network.setBlockedURLs` ile 5 reklam adresi | `page.route` ile |
| Surucu yonetimi | Selenium Manager surucuyu otomatik indirir | Tarayicilar frameworkle birlikte indirilir |
| Paralel calistirma | `ThreadLocal<WebDriver>` ile hazirlik yapildi; paralel calistirma denenmedi | Yerlesik worker'lar |

**Bedeli:** Playwright'ta hazir gelen bu ozellikleri bizim yazmamiz gerekti; bu zaman aldi ve kod miktarini artirdi. Karsiliginda her parcanin nasil calistigini ayrintisiyla ogrendik.

## 5. Alternatiflerin farklari

- **Playwright:** Otomatik bekleme, yerlesik rapor ve trace ile ayni isi daha az kodla yapmak mumkun. Kucuk bir ekip icin ya da hizli kurulum istenen bir projede daha uygun olabilir. Dezavantaji: daha yeni, dil destegi Selenium kadar genis degil.
- **Cypress:** Gelistirici dostu, etkilesimli bir calistirici sunar ve JavaScript projeleri icin dogal bir secimdir. Yalnizca JavaScript/TypeScript desteklemesi ve bazi senaryolardaki kisitlari (ornegin coklu sekme) nedeniyle genel amacli bir UI otomasyonu icin Selenium kadar esnek degildir.

## 6. Sonuc

Bu odev icin **Selenium 4 + Java** secildi: yaygin bir standart, gercek tarayicilarda calismasi ve bekleme/raporlama gibi konulari ayrintisiyla ogrenmeyi saglamasi nedeniyle. Yeni ve kucuk bir proje baslatilsaydi Playwright da guclu bir adaydi; bunu bu projede denemedigimiz icin kesin bir ustunluk iddia etmiyoruz.


