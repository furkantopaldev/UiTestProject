# Bilinen Sorunlar ve Acik Arastirmalar

## KS-001: Sepete ekleme adimi aralikli olarak tamamlanmiyor

Durum: **Karantinada (arastirma acik)**. Test kodu silinmedi; `known-issue` grubuna alindi ve toplu calistirmadan cikarildi.

Etkilenen testler: CartTest (CART-001) ve CheckoutTest (CHK-002). Ikisi de urunu "Add to cart" ile sepete ekleyen ayni adimi kullanir.

### Belirti

Test bazen geciyor, bazen sepete ekleme adiminda zaman asimina dusuyor. Hata kalici degil, aralikli.

### Olcumler (CartTest tek basina)

| Seri | Ayar | Gecen | Kalan |
|---|---|---|---|
| A | bekleme 10 sn | 2 | 3 |
| B | bekleme 30 sn | 4 | 1 |
| D | bekleme 10 sn (tanilama araci acik) | 3 | 2 |
| Toplam | | 9 | 6 |

Tam suite calistirmalarinda CartTest ve CheckoutTest ayni adimda (sepete ekleme sonrasi "View Cart") kaldi.

### Gozlemler

- Kalan calistirmalar ayni yerde kalmiyor. Uc farkli sekil goruldu:
  1. Urun sayfasinda "Added!" penceresi acilmadi (ekran goruntusunde kartin ustundeki hover katmani acik, pencere yok).
  2. Tarayici sepet sayfasinda (/view_cart) kaldi. Hata mesaji kayda alinmadi.
  3. Tarayici ana sayfada kaldi; urun sayfasina hic gecilememis, yani hata sepet adimindan once.
- 30 saniyelik beklemede kalan oran dustu (3/5 yerine 1/5) ama sifirlanmadi; orneklem kucuk, fark tesadufi olabilir.
- Calisan calistirmalar normal govde suresinde (13-23 sn) bitiyor.
- Kalan calistirmalarda sayfa basligi dolu ve "ORTAM SORUNU" (heavy load) uyarisi yok; yani site yuk sayfasi gostermiyor.
- Tarayici konsolunda sepet adimiyla iliskili basarisiz istek kaydi bulunmadi. Gorulen tek 503, ProductsTest'teki bir arama istegine aitti ve o testte site yuk sayfasi gosteriyordu.

### Kanitlanamayanlar

- Sorunun sitede mi, test kodunda mi oldugu bilinmiyor.
- Kalici bir locator hatasi olsaydi test hic gecmezdi; ancak "yanlis locator" ihtimali tamamen elenmis degil.
- Urunun (Blue Top) nedeni olup olmadigi test edilmedi.

### Hipotezler (kanitlanmadi)

1. Site aralikli yavas yanit veriyor ya da istegi dusuruyor.
2. Tiklama hover katmaninda kayboluyor (Add to cart butonu gorunur alanin alt kenarinda yarim kesik duruyor).
3. Selenium 4.25.0 ile Chrome 158 surum farki beklenmedik davranis yaratiyor (konsolda CDP surum uyarisi var).
4. Reklam engelleme (DriverFactory) sayfanin bazi betiklerini bozuyor.

### Denenmeyen deneyler

- Selenium surumunu guncellemek (pom.xml, selenium.version).
- Karti tiklamadan once ekranin ortasina kaydirmak.
- Baska bir urunle denemek.
- Reklam engellemeyi kapatip denemek.
- JavaScript ile tiklamak (dikkat: gercek kullanicinin tiklayamayacagi butonu da tiklanmis sayar, testin kanitladigi seyi zayiflatir).

### Denenenler

- Bekleme suresi 30 saniyeye cikarildi (Olcum B).
- Tiklama ve XHR kayit araci (NetworkProbe) eklendi; sepet sayfasina gecildigi icin kayit kayboluyor, bu yuzden kesin sonuc vermedi.
- Basarisiz testte ekran goruntusu, URL, tarayici konsolu ve ag kaydi toplaniyor (BaseTest).

### Mentora sorulacaklar

1. Hover ile acilan katmandaki butonlar icin onerilen yontem nedir?
2. Halka acik demo sitelerdeki aralikli yanit sorununda hangi hatalar yeniden denenmeli, hangileri denenmemeli?
3. Selenium ile Chrome surum uyumsuzlugu bu tur aralikli sorunlara yol acar mi; guncel surumle denemeli miyim?
4. Kararsiz bir test sirket pratiginde nasil karantinaya aliniyor ve ne zaman geri aciliyor?

### Karantina nasil calisir

- `CartTest` ve `CheckoutTest` metotlari `groups = "known-issue"` ile isaretli.
- `testng.xml` icindeki Cart ve Checkout bloklari bu grubu disarida birakir; toplu calistirmada bu testler kosmaz.
- Tek basina calistirmak icin: `mvn test -Dtest=CartTest`.
- Sorun cozulunce grup isareti ve `exclude` satirlari kaldirilir.
