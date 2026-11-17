# Bekleme Stratejileri

Uygulama: https://automationexercise.com

Bu dokuman statik ve dinamik bekleme kavramlarini, projede kullanilan bekleme yardimcilarini ve `WaitComparisonTest` ile olculen gercek sureleri icerir.

## 1. Kavramlar

| Kavram | Aciklama | Ornek |
|---|---|---|
| Statik bekleme | Kosula bakmadan sabit sure uyumak | `Thread.sleep(5000)` |
| Dinamik bekleme | Bir kosul saglanana kadar kisa araliklarla kontrol edip, saglanir saglanmaz devam etmek | `WebDriverWait` + `ExpectedConditions` |
| Implicit wait | Her `findElement` cagrisina uygulanan genel bekleme | `driver.manage().timeouts().implicitlyWait(...)` |
| Explicit wait | Belirli bir kosul icin yazilan bekleme | `new WebDriverWait(driver, ...).until(...)` |

Projede implicit wait **bilincli olarak kapali** (`Duration.ZERO`, `DriverFactory`). Implicit ve explicit wait birlikte kullanilirsa bekleme sureleri ongorulemez sekilde toplanabilir. Butun beklemeler explicit wait ile yonetilir.

## 2. Projedeki bekleme kosullari (WaitHelper)

| Metot | Beklenen kosul | Kullanim amaci |
|---|---|---|
| `visible(By)` | Element DOM'da var ve gorunur | Elementin bulunmasi ve gorunur olmasi |
| `clickable(By)` | Element gorunur ve etkilesime hazir | Tiklamadan once |
| `invisible(By)` | Element gorunmez ya da DOM'dan kalkmis | Elementin kaybolmasi, "hata mesaji olmamali" kontrolu |
| `urlContains(String)` | URL verilen parcayi iceriyor | Sayfa/URL degisiminin beklenmesi |

Zaman asimi olursa `WaitHelper` su bilgileri iceren tek bir hata mesaji uretir: kac saniye beklendi, hangi kosul saglanmadi, hangi element, **o anki URL** ve **sayfa basligi**.

### Koddaki kullanim yerleri

| Yer | Kullanilan bekleme | Neden |
|---|---|---|
| `BasePage.click` | `clickable` | Gorunur ve tiklanabilir olmadan tiklanmaz |
| `BasePage.type`, `getText` | `visible` | Gorunmeyen alana yazilmaz, gorunmeyen metin okunmaz |
| `BasePage.isDisplayed` | `visible` | Gorunurse true, zaman asiminda false |
| `BasePage.isNotDisplayed` | `invisible` | "Elementin olmadigini" dogrulamak icin |
| `HomePage.openLoginPage` | `urlContains("/login")` | Menu tiklamasindan sonra sayfa degisimini beklemek |
| `SignupPage.waitUntilLoaded` | `visible` (sifre alani) | Hesap bilgileri sayfasinin yuklendigini anlamak |
| `Header.getLoggedInUsername` | `visible` | Giris sonrasi menunun gelmesini beklemek |
| `Header.deleteAccount` | `clickable` | Silme baglantisina tiklamadan once |

## 3. Olcum: statik ve dinamik bekleme karsilastirmasi

Senaryo: Kayitsiz bir e-posta ile giris denenir, Login'e tiklanir ve cikan hata mesaji okunur. Ayni adim uc farkli yontemle calistirildi (`WaitComparisonTest`).

| Yontem | Olculen sure | Sonuc |
|---|---|---|
| Dinamik (WaitHelper) | 53 ms | Mesaj bulundu |
| Statik 100 ms | 176 ms | Mesaj bulundu |
| Statik 5000 ms | 5067 ms | Mesaj bulundu |

Her yontem icin tek olcum alindi; sureler her calistirmada degisir.

### Yorum

1. **Dinamik bekleme yaklasik 95 kat daha hizli** (53 ms - 5067 ms). Ikisi de ayni sonuca ulasti, aradaki fark tamamen bos beklemeden gelen suredir. 5 saniyelik beklemenin 5014 ms'si bosa harcandi.
2. **Bu senaryoda uc yontem de basarili oldu.** Login butonuna tiklamak sayfa yenilemesi baslatir ve Selenium sayfa yuklenene kadar bekler; bu yuzden hata mesaji, tiklama donduginde zaten sayfadaydi. Yani bu olcum statik beklemenin *yavasligini* gosteriyor, *kirilganligini* degil.
3. **100 ms'in yetmesi onun guvenilir oldugunu gostermez.** Gecen sure, sunucunun o anki hizina bagli. Sunucu yavaslarsa ayni 100 ms yetmez ve test hatali sekilde kalir. Tek bir basarili calistirma bu riski ortadan kaldirmaz.
4. Statik beklemenin kirilganligini en net gosterecek durum, sayfa yenilenmeden gelen mesajlardir (ornegin abonelik formundaki basari mesaji). Bu senaryo Subscription testi yazildiginda ayrica olculecek.

### Hangi yontem daha guvenilir?

Dinamik bekleme. Gerekce:

| Durum | Statik | Dinamik |
|---|---|---|
| Element hizli gelirse | Yine de tum sureyi bekler (zaman kaybi) | Gelir gelmez devam eder |
| Element yavas gelirse | Sure biter, element yok, test kalir | Zaman asimina kadar bekler, bulunca devam eder |
| Zaman asimi / hata | Genellikle `NoSuchElementException`, nedeni belirsiz | Neyin, nerede beklendigini soyleyen anlasilir mesaj |
| Sonuc | Yavas ve kararsiz (flaky) | Hizli ve guvenilir |

## 4. Gercek bir olay: hata mesajinin yetersizligi

`RegisterTest` bir calistirmada su hatayla kaldi: "Logged in as ... gorunur olmadi". Ekran goruntusu aldigimizda sitenin "This website is under heavy load (queue full)" sayfasi gosterdigini gorduk; yani sorun test kodunda degil, test ortaminda (halka acik demo sitenin asiri yuku) idi.

Mesaj, sorunun nedenini degil yalnizca belirtisini soyluyordu. Bunun uzerine `WaitHelper` zaman asimi mesajina o anki URL ve sayfa basligini ekledi. Boylece bir bekleme basarisiz oldugunda, ekran goruntusune bakmadan da tarayicinin hangi sayfada oldugu gorulebilir.

Cikarim: Iyi bir bekleme mekanizmasi yalnizca beklemekle kalmaz, basarisiz oldugunda **teshise yardim eden bilgi** de uretir.

## 5. Kurallar

- Projede `Thread.sleep` yalnizca `WaitComparisonTest` icinde, ornek amacli bulunur.
- Zaman asimi suresi `config.properties` icindeki `explicit.wait.seconds` degerinden gelir (varsayilan 10 sn); kodun icine sabit sure yazilmaz.
- "Element olmamali" kontrolu icin `isDisplayed` yerine `isNotDisplayed` (`invisible`) kullanilir. Aksi halde element zaten olmadigi icin test, zaman asimi suresi kadar bosuna bekler.
- Sayfa degisiminden sonra sonraki adima gecmeden once o sayfaya ozgu bir element ya da URL beklenir.