# Locator Analizi

Uygulama: https://automationeOKercise.com

Tum locator'lar Chrome DevTools'ta (F12, Elements, Ctrl+F) aranarak dogrulanir. Yalnizca **1 of 1** veren locator kullanilir.

Durum isaretleri:

- [OK] DevTools'ta dogrulandi
- [TEST] OKle dolayli dogrulandi (OKlerde calisiyor; DevTools sayisi kaydedilmedi)
- [ ] Henuz dogrulanmadi (aday)
- [X] Kullanilmaz

"Eslesme" sutunu:

- `1 of 1` = DevTools arama kutusunda gorulen sayi
- `HTML'den N` = benim verdigim HTML parcasindan cikarilan beklenti; DevTools sayisi kaydedilmedi
- `OK ile (sayi yok)` = locator OKlerde calisiyor ama sayi alinmadi

## 1. Locator secim kriterleri

| Kriter | Soru |
|---|---|
| Benzersizlik | Locator sayfada tam olarak 1 element buluyor mu? |
| Okunabilirlik | Koda bakan biri hangi elementi hedefledigini anliyor mu? |
| Dayaniklilik | Tasarim, metin veya sira degisince kirilir mi? |

Tercih sirasi: OK attribute (`data-qa`), benzersiz `id`, anlamli CSS, goreli OKPath. Mutlak OKPath, `nth-child` ve degisken degerler (token, otomatik id) kullanilmaz.

## 2. Ana sayfa ve ust menu (`/`)

| Durum | Element | Tur | Locator | Eslesme | Gerekce |
|---|---|---|---|---|---|
| [OK] | Logo | CSS (class + torun) | `.logo img` | OK ile (sayi yok) | Sayfada tek logo var; HomePageOK'te kullaniliyor |
| [OK] | Signup / Login menu baglantisi | CSS (href) | `a[href='/login']` | OK ile (sayi yok) | href sabit, gorunen metin degisse de calisir |
| [OK] | Products menu baglantisi | CSS (href) | `a[href='/products']` | OK ile (sayi yok) | href sabit, gorunen yazi ya da ikon degisse de calisir |
| [OK] | Logout baglantisi (giris yapilmisken) | CSS (href) | `a[href='/logout']` | OK ile (sayi yok) | href sabit |

## 3. Login / Signup sayfasi (`/login`)

| Durum | Element | Tur | Locator | Eslesme | Gerekce |
|---|---|---|---|---|---|
| [OK] | Login basligi | CSS (yapi) | `.login-form h2` | HTML'den 1 | Sayfaya dogru gelindigini dogrular |
| [OK] | Login e-posta | CSS (data-qa) | `[data-qa='login-email']` | 1 of 1 | `name='email'` iki formda da var (2 eslesme) |
| [OK] | Login sifre | CSS (data-qa) | `[data-qa='login-password']` | 1 of 1 | OK icin ayrilmis ozellik |
| [OK] | Login butonu | CSS (data-qa) | `[data-qa='login-button']` | 1 of 1 | Buton metni/stili degisse de calisir |
| [OK] | Signup isim | CSS (data-qa) | `[data-qa='signup-name']` | 1 of 1 | `name='name'` bu sayfada benzersiz ama form ozelligine bagli |
| [OK] | Signup e-posta | CSS (data-qa) | `[data-qa='signup-email']` | 1 of 1 | `name='email'` login alaniyla karisirdi |
| [OK] | Signup butonu | CSS (data-qa) | `[data-qa='signup-button']` | 1 of 1 | Benzersiz |
| [OK] | Login hata mesaji | CSS (yapi) | `.login-form form p` | HTML'den 1 (yalnizca hatali giristen sonra) | id/class/data-qa yok; metin ve stilden bagimsiz. Mesaj yalnizca hatali giristen sonra DOM'da olur |
| [OK] | Signup hata mesaji ("Email Address already eOKist!") | CSS (yapi) | `.signup-form form p` | OK ile (sayi yok) | id/class/data-qa yok; login hata mesajinin sag form karsiligi |
| [OK] | CSRF gizli alani | Kullanilmaz | `input[name='csrfmiddlewaretoken']` | - | Degeri her yuklemede degisir (dinamik attribute) |

## 4. Kayit bilgileri sayfasi (`/signup`)

| Durum | Element | Tur | Locator | Eslesme | Gerekce |
|---|---|---|---|---|---|
| [OK] | "Mr." radyo butonu | CSS (id) | `#id_gender1` | 1 of 1 | Benzersiz id; "Mrs." icin ayri id (`id_gender2`) var |
| [OK] | Sifre | CSS (data-qa) | `[data-qa='password']` | 1 of 1 | OK icin ayrilmis ozellik |
| [OK] | Gun (select) | CSS (data-qa) | `[data-qa='days']` | 1 of 1 | Select elementi; kodda `Select` sinifiyla kullanilir |
| [OK] | Ay (select) | CSS (data-qa) | `[data-qa='months']` | 1 of 1 | Select elementi; kodda `Select` sinifiyla kullanilir |
| [OK] | Yil (select) | CSS (data-qa) | `[data-qa='years']` | 1 of 1 | Select elementi; kodda `Select` sinifiyla kullanilir |
| [OK] | Ad | CSS (data-qa) | `[data-qa='first_name']` | 1 of 1 | Benzersiz |
| [OK] | Soyad | CSS (data-qa) | `[data-qa='last_name']` | 1 of 1 | Benzersiz |
| [OK] | Adres | CSS (data-qa) | `[data-qa='address']` | 1 of 1 | Benzersiz |
| [OK] | Ulke (select) | CSS (data-qa) | `[data-qa='country']` | 1 of 1 | Select elementi; kodda `Select` sinifiyla kullanilir |
| [OK] | Eyalet | CSS (data-qa) | `[data-qa='state']` | 1 of 1 | Benzersiz |
| [OK] | Sehir | CSS (data-qa) | `[data-qa='city']` | 1 of 1 | Benzersiz |
| [OK] | Posta kodu | CSS (data-qa) | `[data-qa='zipcode']` | 1 of 1 | Benzersiz |
| [OK] | Cep telefonu | CSS (data-qa) | `[data-qa='mobile_number']` | 1 of 1 | Benzersiz |
| [OK] | Hesap olustur butonu | CSS (data-qa) | `[data-qa='create-account']` | 1 of 1 | Buton metni degisse de calisir |

## 5. Hesap olusturuldu / silindi sayfalari

| Durum | Element | Tur | Locator | Eslesme | Gerekce |
|---|---|---|---|---|---|
| [OK] | "ACCOUNT CREATED!" basligi | CSS (data-qa) | `[data-qa='account-created']` | 1 of 1 | Basari dogrulamasi icin sabit hedef |
| [OK] | Continue butonu | CSS (data-qa) | `[data-qa='continue-button']` | 1 of 1 | Benzersiz |
| [OK] | "Logged in as ..." baglantisi (kullanici adi) | OKPath (metin) | `//a[contains(., 'Logged in as')]/b` | 1 of 1 | Kullanici adi degisken; sabit kisma gore bulunur, `/b` ile kalin yazilmis ismi alir |
| [OK] | Delete Account baglantisi | CSS (href) | `a[href='/delete_account']` | 1 of 1 | href sabit, metin degisse de calisir |
| [OK] | "ACCOUNT DELETED!" basligi | CSS (data-qa) | `[data-qa='account-deleted']` | 1 of 1 | Silme dogrulamasi icin sabit hedef |

## 6. Urunler sayfasi (`/products`)

| Durum | Element | Tur | Locator | Eslesme | Gerekce |
|---|---|---|---|---|---|
| [OK] | Arama kutusu | By.id | `#search_product` | HTML'den 1 | Benzersiz id |
| [OK] | Arama butonu | By.id | `#submit_search` | HTML'den 1 | Benzersiz id |
| [OK] | "All Products" basligi | OKPath (metin) | `//h2[normalize-space()='All Products']` | OK ile (sayi yok) | Iki baslik ayni class'i tasiyor (`title teOKt-center`), yalnizca metin farkli; CSS metne gore bulamaz |
| [OK] | "Searched Products" basligi | OKPath (metin) | `//h2[normalize-space()='Searched Products']` | OK ile (sayi yok) | Ayni gerekce |
| [OK] | Urun adlari | CSS (yapi) | `.product-image-wrapper .productinfo p` | OK ile (sayi yok) | Yalnizca kartin gorunen bolumu; hover katmanindaki ikinci kopyayi disarida birakir |
| [OK] | Urun karti (tek basina) | CSS (class) | `.product-image-wrapper` | Urun sayisi kadar (sayi yok) | **Tekrar eden class ornegi:** her urun icin ayni class; tek urun icin kullanilmaz |
| [OK] | Iki "Add to cart" butonu birlikte | CSS (attribute) | `a[data-product-id='1']` | HTML'den 2 (kartin iki butonu) | **Coklu eslesme ornegi:** her urunde iki buton var (gorunen ve hover katmani); bu locator kullanilmaz, ust ogeyle daraltilir |
| [OK] | Kart "Add to cart" (gorunen) | CSS (class + attribute) | `.productinfo a.add-to-cart[data-product-id='1']` | HTML'den 1 | Ust oge ile daraltilmis, tek eslesme |
| [OK] | Katman "Add to cart" (hover ile acilan) | CSS (class + attribute) | `.product-overlay a.add-to-cart[data-product-id='1']` | HTML'den 1 | Kullanici kartin ustune gelince bu buton tiklanir |
| [OK] | "Added!" penceresi basligi | OKPath (metin) | `//div[contains(@class,'modal-content')]//h4[normalize-space()='Added!']` | OK ile (sayi yok) | Pencerenin gercekten acildigini ayrica beklemek icin |
| [OK] | Pencerede "View Cart" | CSS | `.modal-content a[href='/view_cart']` | HTML'den 1 | Menudeki "Cart" baglantisiyla (ayni href) cakismasin diye pencereyle kisitli |

## 7. Sepet sayfasi (`/view_cart`)

| Durum | Element | Tur | Locator | Eslesme | Gerekce |
|---|---|---|---|---|---|
| [OK] | Sepet tablosu | By.id | `#cart_info_table` | HTML'den 1 | Benzersiz id |
| [OK] | Sepet satirlari | CSS (yapi) | `#cart_info_table tbody tr` | HTML'den 1 (sepette 1 urun varken) | Satir sayisini saymak icin |
| [OK] | Urun satiri (urun 1) | CSS (id) | `#product-1` | HTML'den 1 | Satirin id'si urun numarasini tasir |
| [OK] | Satirdaki urun adi | CSS (satira bagli) | `#product-1 .cart_description h4 a` | HTML'den 1 | Dogru satira daraltilmis |
| [OK] | Satirdaki kategori | CSS (satira bagli) | `#product-1 .cart_description p` | HTML'den 1 | Ayni |
| [OK] | Satirdaki birim fiyat | CSS (satira bagli) | `#product-1 .cart_price p` | HTML'den 1 | Ayni |
| [OK] | Satirdaki adet | CSS (satira bagli) | `#product-1 .cart_quantity button` | HTML'den 1 | Ayni |
| [OK] | Satirdaki toplam | CSS (satira bagli) | `#product-1 .cart_total_price` | HTML'den 1 | Ayni |
| [ ] | "Proceed To Checkout" | CSS (class) | `.check_out` | OK ile (sayi yok); aday | **Aday:** HTML'ini gormeden yazildi; OKte tiklama calisti ama benzersizligi dogrulanmadi |
| [OK] | Misafir uyari metni | OKPath (icerik) | `//div[contains(@class,'modal-body')]//p[contains(., 'Register / Login account to proceed on checkout')]` | OK ile (sayi yok) | Metne gore; DevTools'tan kopyalanan HTML'de siteye ait olmayan bir eleman bulundugu icin `contains` kullanildi |
| [OK] | Uyaridaki "Register / Login" baglantisi | CSS | `.modal-content a[href='/login']` | OK ile (sayi yok) | Pencereyle kisitli (menude de `/login` baglantisi var) |

## 8. Zor durumlar ve cikarimlar

| Durum | Ornek | Cikarim |
|---|---|---|
| Coklu eslesme | `input[name='email']` login sayfasinda 2 sonuc verdi | `By.name("email")` sessizce ilk elementi secerdi; `data-qa` ile ayristirildi |
| Coklu eslesme | `a[data-product-id='1']` iki "Add to cart" butonunu bulur (kartin kendisi ve hover katmani) | Ust ogeyle daraltildi: `.productinfo ...` ve `.product-overlay ...` |
| Dinamik attribute | `csrfmiddlewaretoken` degeri her yuklemede degisiyor | Deger locator'a konmaz; zaten form alani olarak kullanilmaz |
| Id/class/data-qa olmayan element | Login hata mesaji `<p style="color: red;">` | Stile degil yapiya dayali CSS secildi; metin assertion'da dogrulanir |
| Degisken metin | "Logged in as <kullanici adi>" | `contains` ile sabit kisma gore bulunur (OKPath) |
| Tekrar eden class | `.product-image-wrapper` urun sayisi kadar eslesir | Tek urun icin kullanilmaz; urun numarasina bagli locator'lar (`#product-N`, `[data-product-id='N']`) kullanilir |
| Ayni class, farkli metin | "All Products" ve "Searched Products" basliklari ayni `title teOKt-center` class'ini tasir | CSS metne gore bulamadigi icin OKPath (`normalize-space()`) kullanildi |
| Ayni href, farkli yer | `a[href='/view_cart']` hem menude hem "Added!" penceresinde var | Pencereyle kisitlandi: `.modal-content a[href='/view_cart']` |
| DevTools ile OKin gordugu DOM ayni olmayabilir | Chrome'dan kopyalanan checkout uyarisi HTML'inde siteye ait olmayan, tarayici ozelligi tarafindan eklenmis bir eleman vardi | Uyari metni `contains` ile aranir; locator sadece sitenin kendi elemanlarina baglanir |

## 9. Durum ozeti ve acik kalan dogrulamalar

| Durum | Locator sayisi |
|---|---|
| [OK] DevTools'ta dogrulandi | 25 |
| [OK] OKle dolayli dogrulandi | 26 |
| [ ] Aday (dogrulanmadi) | 1 |
| [OK] Kullanilmaz | 3 |
| **Toplam** | **55** |

DevTools'ta aratip `Eslesme` sutununa sayi yazilmasi gereken, onceligi yuksek satirlar:

| Nerede? | Aranacak | Beklenti |
|---|---|---|
| /view_cart (sepette urun varken) | `.check_out` | 1 (emin degil) |
| /products | `.product-image-wrapper` | Urun sayisi kadar (sayiyi not et) |
| /products | `a[data-product-id='1']` | 2 |
| Ana sayfa | `a[href='/products']` | 1 (emin degil) |
| Giris yapilmisken | `a[href='/logout']` | 1 |
| Sayfa basinda | `.logo img` | 1 |
