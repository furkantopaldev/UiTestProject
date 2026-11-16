# Automation Exercise UI Test Otomasyonu

[Automation Exercise](https://automationexercise.com/) uygulaması için Selenium + Java ile geliştirilmiş UI test otomasyon projesi.

## Kullanılan teknolojiler

Java 17, Maven, Selenium 4, TestNG ve Allure Report. Tarayıcı sürücüleri Selenium Manager tarafından otomatik indirilir.

## Kurulum

Gereksinimler: JDK 17 veya üzeri, Maven 3.9+ ve Google Chrome (Edge veya Firefox da kullanılabilir).

```bash
java -version
mvn -version

git clone <repo-url>
cd <repo-klasoru>
mvn clean compile test-compile
```

## Testleri çalıştırma

```bash
# Tüm testler (testng.xml)
mvn clean test

# Tek bir test sınıfı
mvn clean test -Dtest=HomePageTest

# Tek bir test metodu
mvn clean test -Dtest=HomePageTest#homePageShouldLoadSuccessfully

# Farklı tarayıcı / headless
mvn clean test -Dbrowser=edge -Dheadless=true
```

## Yapılandırma

Tüm ortam ayarları `src/test/resources/config.properties` dosyasındadır. Her değer komut satırından `-Danahtar=deger` ile ezilebilir.

| Anahtar | Varsayılan | Açıklama |
|---|---|---|
| base.url | https://automationexercise.com | Uygulama adresi |
| browser | chrome | chrome, edge, firefox |
| headless | false | Tarayıcıyı arayüzsüz çalıştırır |
| explicit.wait.seconds | 10 | Koşullu bekleme zaman aşımı |
| page.load.timeout | 30 | Sayfa yükleme zaman aşımı |

## Klasör yapısı

```
src/test/java/com/automationexercise/
├── base/      BaseTest: tarayıcı açma/kapama
├── config/    ConfigReader: merkezi ayar yönetimi
├── driver/    DriverFactory: tarayıcı oluşturma, reklam engelleme
├── pages/     Page Object sınıfları ve locator'lar
└── tests/     Test sınıfları
src/test/resources/
├── config.properties
├── allure.properties
└── testng.xml
docs/          Framework karşılaştırması, locator ve bekleme analizleri
```

## Raporlama

Allure entegrasyonu ilerleyen commit'lerde tamamlanacaktır.
