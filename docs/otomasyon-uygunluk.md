# Manuel Test Senaryolarinin Otomasyon Uygunluk Analizi

Kaynak: 2. hafta test dokumani (Test_Case_Son_Surum_Yeni_Format). Belgede 53 test case vardir: 19 Register/Login, 15 Products/Search, 8 Cart, 8 Checkout, 3 Home Page.

## 1. Degerlendirme kriterleri

| Kriter | Soru |
|---|---|
| Tekrar edilebilirlik | Her calistirmada ayni kosullarla tekrarlanabiliyor mu? |
| Dogrulanabilirlik | Sonuc kodla kesin olarak (dogru/yanlis) kontrol edilebiliyor mu? |
| Veri bagimliligi | Sabit hesap/veri gerekiyor mu, kalici veri uretiyor mu, temizlenebilir mi? |
| Bakim maliyeti | Adim sayisi, dis sisteme baglilik ve degisme sikligi |

Uygunluk: **Evet** = bu kriterlerde belirgin sorun yok. **Kismen** = otomatiklenebilir ama zayif/kirilgan bir yani var. **Hayir** = su haliyle otomasyona uygun degil.

Oncelik: **Yuksek** = is acisindan kritik akis, **Orta** = onemli ama tekil, **Dusuk** = kirilgan ya da dusuk deger.

## 2. Ozet

| Modul | Toplam | Evet | Kismen | Hayir |
|---|---|---|---|---|
| Register / Login | 19 | 18 | 1 | 0 |
| Products / Search | 15 | 12 | 2 | 1 |
| Cart | 8 | 6 | 2 | 0 |
| Checkout | 8 | 4 | 2 | 2 |
| Home Page | 3 | 0 | 3 | 0 |
| **Toplam** | **53** | **40** | **10** | **3** |

Plan dagilimi (53 case):

| Plan | Case sayisi |
|---|---|
| Yapildi | 13 |
| Karantina | 2 |
| Planli | 7 |
| Yedek / veri tabanli | 22 |
| Bilinen hata | 5 |
| Manuel kalir | 4 |

Plan sutunu: **Yapildi** = projede yazildi ve aktif. **Karantina** = yazildi, kararsiz oldugu icin toplu calistirmadan cikarildi. **Planli** = siradaki testler. **Yedek** = uygun ama sirada degil. **Veri tabanli** = ayni test metodu farkli veriyle (DataProvider). **Bilinen hata** = manuel testte kusur bulunmus, beklenen davranisi dogrulayan test kirmizi olur. **Manuel kalir** = otomasyon maliyetine degmez.

## 3. Tum test case'ler

| Test Case ID | Senaryo | Manuel sonuc | Uygunluk | Oncelik | Plan | Gerekce |
|---|---|---|---|---|---|---|
| AUTH-001 | Register - Yeni kullanici ile basarili kayit | Pass | Evet | Yuksek | Yapildi (RegisterTest) | Tekrarlanabilir; sonuc ("Account Created!", menude kullanici adi) otomatik dogrulanir. Sabit e-posta yerine her kosuda benzersiz e-posta uretilir, test sonunda hesap silinir |
| AUTH-002 | Register - Kayitli e-posta ile tekrar kayit | Pass | Evet | Yuksek | Yapildi (RegisterTest) | Sonuc net (hata mesaji, forma gecilmez). On kosul olan kayitli hesap test icinde olusturulup silinir; sabit hesaba bagli kalinmaz. Hata mesaji locator'i dogrulanmali |
| AUTH-003 | Register - Gecersiz e-posta formati | Pass | Kismen | Orta | Yedek | Dogrulama tarayicinin yerlesik HTML5 uyarisi; uyari metni tarayiciya ve dile bagli. Metin yerine alanin gecerlilik durumu (JavaScript validity) ve sayfanin degismedigi kontrol edilir. 3 girdi icin veri tabanli yazilabilir |
| AUTH-004 | Register - Ad alani bos | Pass | Evet | Orta | Yedek | Zorunlu alan kontrolu; sayfanin /login'de kalmasi ve alanin gecersiz isaretlenmesi dogrulanir. Hesap olusmadigi icin temizlik gerekmez |
| AUTH-005 | Register - Hesap bilgi formunda Sifre (Password) alani bos | Pass | Evet | Orta | Yedek (veri tabanli) | AUTH-005..012 ayni adimlari izler, tek fark bos birakilan alan; tek test metodu ve 8 veri satiri kod tekrarini onler. Hesap olusmamasi beklenir; olusursa temizlik adimi siler |
| AUTH-006 | Register - Hesap bilgi formunda Ad (First name) alani bos | Pass | Evet | Orta | Yedek (veri tabanli) | AUTH-005..012 ayni adimlari izler, tek fark bos birakilan alan; tek test metodu ve 8 veri satiri kod tekrarini onler. Hesap olusmamasi beklenir; olusursa temizlik adimi siler |
| AUTH-007 | Register - Hesap bilgi formunda Soyad (Last name) alani bos | Pass | Evet | Orta | Yedek (veri tabanli) | AUTH-005..012 ayni adimlari izler, tek fark bos birakilan alan; tek test metodu ve 8 veri satiri kod tekrarini onler. Hesap olusmamasi beklenir; olusursa temizlik adimi siler |
| AUTH-008 | Register - Hesap bilgi formunda Adres (Address) alani bos | Pass | Evet | Orta | Yedek (veri tabanli) | AUTH-005..012 ayni adimlari izler, tek fark bos birakilan alan; tek test metodu ve 8 veri satiri kod tekrarini onler. Hesap olusmamasi beklenir; olusursa temizlik adimi siler |
| AUTH-009 | Register - Hesap bilgi formunda Eyalet (State) alani bos | Pass | Evet | Orta | Yedek (veri tabanli) | AUTH-005..012 ayni adimlari izler, tek fark bos birakilan alan; tek test metodu ve 8 veri satiri kod tekrarini onler. Hesap olusmamasi beklenir; olusursa temizlik adimi siler |
| AUTH-010 | Register - Hesap bilgi formunda Sehir (City) alani bos | Pass | Evet | Orta | Yedek (veri tabanli) | AUTH-005..012 ayni adimlari izler, tek fark bos birakilan alan; tek test metodu ve 8 veri satiri kod tekrarini onler. Hesap olusmamasi beklenir; olusursa temizlik adimi siler |
| AUTH-011 | Register - Hesap bilgi formunda Posta kodu (Zipcode) alani bos | Pass | Evet | Orta | Yedek (veri tabanli) | AUTH-005..012 ayni adimlari izler, tek fark bos birakilan alan; tek test metodu ve 8 veri satiri kod tekrarini onler. Hesap olusmamasi beklenir; olusursa temizlik adimi siler |
| AUTH-012 | Register - Hesap bilgi formunda Telefon (Mobile Number) alani bos | Pass | Evet | Orta | Yedek (veri tabanli) | AUTH-005..012 ayni adimlari izler, tek fark bos birakilan alan; tek test metodu ve 8 veri satiri kod tekrarini onler. Hesap olusmamasi beklenir; olusursa temizlik adimi siler |
| AUTH-013 | Login - Dogru e-posta ve sifre ile giris | Pass | Evet | Yuksek | Yapildi (LoginTest) | Temel is akisi, sonuc otomatik dogrulanir. Kayitli kullanici gerekir: test kendi kullanicisini arayuzden olusturur ve sonunda siler |
| AUTH-014 | Login - Kayitli e-posta, yanlis sifre | Pass | Evet | Yuksek | Yapildi (LoginTest) | Negatif temel senaryo; AUTH-013 ile ayni kullanici hazirligi kullanilir |
| AUTH-015 | Login - Kayitsiz e-posta ile giris | Pass | Evet | Yuksek | Yapildi (LoginTest) | Kullanici gerektirmez; en ucuz ve en kararli negatif senaryo |
| AUTH-016 | Login - Tum alanlar bos | Pass | Evet | Orta | Yapildi (LoginTest, veri tabanli) | Karar tablosunun satiri; HTML5 zorunlu alan uyarisi. AUTH-016/017/018 tek test metodu ve 3 veri satiri olarak yazilabilir |
| AUTH-017 | Login - Sifre alani bos | Pass | Evet | Orta | Yapildi (LoginTest, veri tabanli) | AUTH-016 ile ayni test metodunun veri satiri. Tarayici formu gondermeden durdurdugu icin kayitli e-posta gerekmemesi beklenir |
| AUTH-018 | Login - E-posta alani bos | Pass | Evet | Orta | Yapildi (LoginTest, veri tabanli) | AUTH-016 ile ayni test metodunun veri satiri |
| AUTH-019 | Logout - Oturumu kapatma | Pass | Evet | Yuksek | Yapildi (LogoutTest) | Sonuc net (login sayfasina yonlenme, menude "Signup / Login" geri gelir). On kosul: kendi olusturdugu giris yapmis kullanici |
| PROD-001 | Urun listesi - Tum urunlerin goruntulenmesi | Pass | Evet | Yuksek | Yapildi (ProductsTest) | Salt okunur, veri bagimliligi yok; urun listesinin bos olmadigi ve kategori/marka panelinin gorundugu dogrulanir |
| PROD-002 | Urun detayi - Detay sayfasinin goruntulenmesi | Pass | Evet | Orta | Planli (ek test) | Salt okunur; detay alanlarinin (ad, kategori, fiyat, stok, marka) gorunurlugu dogrulanir |
| PROD-003 | Arama - Var olan urunu arama | Pass | Evet | Yuksek | Yapildi (ProductsTest, PROD-004 ile veri tabanli) | Salt okunur, kalici veri uretmez. Dogrulama kurali (sonuc basliklari terimi iceriyor mu) sitenin gercek davranisina gore netlestirilecek |
| PROD-004 | Arama - Buyuk/kucuk harf duyarliligi | Pass | Evet | Orta | Yapildi (PROD-003 ile veri tabanli) | PROD-003 ile ayni adimlar, tek fark terimin buyuk/kucuk harfi |
| PROD-005 | Arama - Kismi kelime ile arama | Pass | Evet | Orta | Veri tabanli (PROD-003 ile) | PROD-003 ile ayni adimlar, tek fark kismi kelime |
| PROD-006 | Arama - Var olmayan urun | Pass | Evet | Orta | Yapildi (ProductsTest) | Salt okunur negatif senaryo; sonuc: liste bos ve sayfa hata vermez |
| PROD-007 | Arama - Bos arama | Pass | Kismen | Dusuk | Manuel kalir | Beklenen sonuc belgede varsayima dayaniyor ("gozlenen davranis"); net bir dogru/yanlis olcutu yok, otomasyonda zayif assertion olur |
| PROD-008 | Arama - Ozel karakter ve SQL benzeri girdi | Pass | Kismen | Dusuk | Manuel kalir | Sayfanin hata vermedigi kontrol edilebilir ama guvenlik acigi olup olmadigi arayuz testiyle kanitlanamaz; guvenlik testi ayri arac ve uzmanlik ister |
| PROD-009 | Filtre - Kategoriye gore urun listeleme | Pass | Evet | Orta | Yedek | Alt kategoriler tiklayinca acildigi icin gorunur olana kadar beklenmeli (WaitHelper); sayfa basligi ve listelenen urunler dogrulanir |
| PROD-010 | Filtre - Markaya gore urun listeleme | Pass | Evet | Orta | Planli (ek test) | Salt okunur; sayfa basligi ve marka filtresi dogrulanir |
| PROD-011 | View a cart - Write Your Review kismi | Pass | Evet | Orta | Yedek | Basari mesaji ("Thanks for your review") dogrulanir. Yorumun sitede kalici olup olmadigi belgeden anlasilmiyor |
| PROD-012 | View a cart - Write Your Review kismini gecersiz bilgilerle doldurmak | Bug | Hayir | Dusuk | Bilinen hata | Manuel testte Bug: site gecersiz bilgiyi de kabul ediyor. Beklenen davranis dogrulanirsa test surekli kirmizi olur; duzelene kadar "bilinen hata" grubunda tutulur |
| PROD-013 | Write Your Review - Yorum alani bos | Pass | Evet | Orta | Planli (ek test, veri tabanli) | HTML5 zorunlu alan; PROD-013/014/015 tek test metodu ve 3 veri satiri olabilir |
| PROD-014 | Write Your Review - Ad alani bos | Pass | Evet | Orta | Planli (ek test, veri tabanli) | PROD-013 ile ayni test metodunun veri satiri |
| PROD-015 | Write Your Review - E-posta alani bos | Pass | Evet | Orta | Planli (ek test, veri tabanli) | PROD-013 ile ayni test metodunun veri satiri |
| CART-001 | Cart - Urun listesinden sepete ekleme | Pass | Evet | Yuksek | Karantina (CartTest, yazildi) | Temel is akisi; kullanici gerektirmez, sepet oturuma ozel oldugu icin kalici veri olusmaz. Ad, fiyat, adet, toplam dogrulanir |
| CART-002 | Cart - Birden fazla farkli urun ekleme ve toplam | Pass | Evet | Yuksek | Yedek | Karar tablosu (urun x adet = toplam); hesaplama otomatik dogrulanir. "Continue Shopping" penceresi icin bekleme gerekir |
| CART-003 | Cart - Detay sayfasindan birden fazla adet ekleme | Pass | Evet | Orta | Planli (ek test, kapi deneyine bagli) | Detay sayfasinda adet alani 3; sepette adet ve satir toplami dogrulanir |
| CART-004 | Cart - Ayni urunu tekrar ekleme | Pass | Evet | Orta | Yedek | Beklenen sonuc belgede varsayim (adet 2 olur); manuel testte gozlenen davranisla uyumlu (Pass) |
| CART-005 | Cart - Sepetten urun silme | Pass | Evet | Yuksek | Planli (ek test, kapi deneyine bagli) | Silinen satirin kaybolmasi dinamik beklemenin (invisible) iyi bir ornegi |
| CART-006 | Cart - Bos sepet durumu | Bug | Kismen | Orta | Bilinen hata | Manuel testte Bug (bos sepette beklenmeyen davranis); beklenen sonuc dogrulanirsa test kirmizi olur |
| CART-007 | Cart - Sifir ve negatif adet | Bug | Evet | Yuksek | Bilinen hata (gosterim testi) | Sonuc net: toplam negatif olmamali. Manuel testte Bug (Rs -500). Gercek bir kusuru yakalayan test, rapor ve ekran goruntusu gosterimi icin ideal aday; ana suite'e degil "bilinen-hata" grubuna konur |
| CART-008 | Cart - Cok yuksek adet | Bug | Kismen | Dusuk | Manuel kalir | Beklenen davranis tanimsiz (limit belirtilmemis); assertion yazilacak net bir olcut yok |
| CHK-001 | Checkout - Giris yapmis kullanici ile siparis tamamlama | Pass | Evet | Yuksek | Yedek | En kritik is akisi ama uzun (kayit, urun, adres, odeme) ve bakim maliyeti yuksek. Kayitli kullanici ve sahte kart gerekir; siparis kalici veri uretir, hesap silinerek temizlenir (siparisin sitede kalip kalmadigi dogrulanmali) |
| CHK-002 | Checkout - Misafir kullanici odeme akisi engeli | Pass | Evet | Yuksek | Karantina (CheckoutTest, yazildi) | Hesap gerektirmez, kalici veri uretmez, sonuc net (uyari penceresi ve Register / Login baglantisi). Kisa ve kararli |
| CHK-003 | Checkout - Odeme sirasinda kayit olma, sepetin korunmasi | Pass | Kismen | Orta | Yedek | AUTH-001 ile buyuk olcude ortusuyor; 10 adimlik uzun akis. Sepetin korunmasi (adim 9) tek basina degerli |
| CHK-004 | Checkout - Adres bilgilerinin dogrulanmasi | Pass | Evet | Orta | Yedek | Kayit verileriyle adres karsilastirmasi; kayit testindeki User nesnesi dogrudan kullanilabilir |
| CHK-005 | Checkout - Siparis ozetinin dogrulanmasi | Pass | Evet | Orta | Yedek | Siparis ozeti ve toplam hesabi dogrulamasi |
| CHK-006 | Checkout - Bos odeme formu | Pass | Kismen | Dusuk | Yedek | Odeme sayfasina ulasmak icin uzun on kosul gerekir; HTML5 zorunlu alan uyarisi |
| CHK-007 | Checkout - Gecersiz kart bilgisi | Bug | Hayir | Orta | Bilinen hata | Manuel testte Bug: gecersiz kartla siparis onaylaniyor; beklenen davranis dogrulanirsa test kirmizi olur. Kart kurallari belgede tanimli degil |
| CHK-008 | Checkout - Siparis sonrasi faturanin indirilmesi | Defect | Hayir | Dusuk | Manuel kalir | Dosya indirme dogrulamasi ortama bagli (indirme klasoru, headless); "eksik bilgi" olcutu net degil |
| HOME-001 | Home Page - "Arrow" butonuyla yukari asagi kaydirma | Pass | Kismen | Dusuk | Yedek (dusuk oncelik) | Sayfa konumu JavaScript ile (scrollY) kontrol edilebilir; ama animasyon zamanlamasi ve gorsel beklenti kirilgan |
| HOME-002 | Home Page - "Arrow" butonu olmadan yukari asagi kaydirma | Pass | Kismen | Dusuk | Yedek (dusuk oncelik) | HOME-001 ile buyuk olcude ortusuyor |
| HOME-003 | Home Page - "Subscription" kismini olmayan mail adresleriyle doldurma | Bug | Kismen | Orta | Bilinen hata / Yedek | Teknik olarak otomatiklenebilir; ancak "olmayan e-posta icin uyari" beklentisi tartisilabilir (istemci tarafli form adresin gercekten var olup olmadigini normalde dogrulayamaz). Gecersiz format ve gecerli e-posta senaryolari daha anlamli |

## 4. Veri bagimliligi stratejisi

- Belgedeki ornek hesaplar (ornegin sabit e-postalar) otomasyonda **kullanilmaz**. Her test kendi kullanicisini benzersiz (zaman damgali) e-postayla olusturur ve sonunda siler. Boylece testler birbirinden bagimsiz calisir ve tekrar calistirilabilir.
- Salt okunur senaryolar (urun listesi, arama, filtre) hesap gerektirmez ve kalici veri uretmez; en kararli grup bunlardir.
- Sepet senaryolari misafir oturumunda calisir; tarayici her testte yeniden acildigi icin sepet bosla baslar.
- Siparis veren senaryolar (CHK-001) kalici veri uretir; bu yuzden hesap silinerek temizlenir ve dusuk sayida tutulur.

## 5. Otomasyona alinan testler ve siradaki testler

PDF en az 10 manuel testin otomasyona donusturulmesini ister. HomePageTest bir smoke testtir ve belgedeki bir case'e karsilik gelmez, bu yuzden sayima katilmaz. Maven'in "Tests run" sayisi (18) farkli bir seydir: veri tabanli testlerin her veri satirini ayri saydigi icin test metodu sayisindan buyuktur.

| Case | Test (sinif.metot) | Tur | Durum |
|---|---|---|---|
| AUTH-001 | RegisterTest.registerNewUserThenDeleteAccount | Pozitif | Aktif |
| AUTH-002 | RegisterTest.registerWithExistingEmailShouldShowError | Negatif | Aktif |
| AUTH-013 | LoginTest.loginWithValidCredentialsShouldSucceed | Pozitif | Aktif |
| AUTH-014 | LoginTest.loginWithWrongPasswordShouldShowError | Negatif | Aktif |
| AUTH-015 | LoginTest.loginWithUnregisteredEmailShouldShowError | Negatif | Aktif |
| AUTH-016/017/018 | LoginTest.loginWithEmptyFieldsShouldBeBlocked (3 veri satiri) | Negatif | Aktif |
| AUTH-019 | LogoutTest.logoutShouldEndSession | Pozitif | Aktif |
| PROD-001 | ProductsTest.allProductsPageShouldListProducts | Pozitif | Aktif |
| PROD-003/004 | ProductsTest.searchForExistingProductShouldListMatchingProducts (3 veri satiri) | Pozitif | Aktif |
| PROD-006 | ProductsTest.searchForNonExistingProductShouldListNothing | Negatif | Aktif |
| CART-001 | CartTest.addProductFromListShouldAppearInCart | Pozitif | Karantina (kararsiz) |
| CHK-002 | CheckoutTest.guestCannotProceedToCheckout | Negatif | Karantina (kararsiz) |
| PROD-002 | (yazilacak) | Pozitif | Planli |
| PROD-013/014/015 | (yazilacak, 3 veri satiri) | Negatif | Planli |
| PROD-010 | (yazilacak) | Pozitif | Planli |
| CART-003 | (yazilacak) | Pozitif | Planli (kapi deneyine bagli) |
| CART-005 | (yazilacak) | Pozitif | Planli (kapi deneyine bagli) |

Aktif testler: 10 test metodu, 13 manuel case; 5 pozitif, 5 negatif; aktif modul sayisi 2 (Register/Login ve Products). Cart ve Checkout testleri yazildi ama karantinada. Home Page modulu HomePageTest smoke testiyle temsil edilir.

Siradaki 5 test: PROD-002, PROD-013/014/015, PROD-010 ve (sepete ekleme akisi icin yapilacak "kapi deneyi" gecerse) CART-003 ile CART-005.

## 6. Belgede dikkat edilmesi gereken noktalar

- **Sabit hesaplar:** AUTH-002, AUTH-013, AUTH-014, AUTH-017, AUTH-019 ve CHK senaryolari manuel testte onceden olusturulmus hesaplara dayanir. Otomasyonda bu hesaplar yerine testin kendi urettigi kullanici kullanilir (bkz. Bolum 4).
- **PROD-012:** Tur alani "Pozitif" yazilmis, ancak senaryo gecersiz bilgiyle yorum gondermeyi kapsiyor; negatif olarak siniflandirilmasi daha tutarli olur.
- **HOME-003:** Beklenen sonuc ("olmayan e-posta icin uyari") uygulamanin yapabilecegi bir davranis mi tartisilabilir; gercek hata olarak raporlamadan once bu beklentinin gereksinimle uyumlu oldugu dogrulanmali.
- **Varsayima dayanan beklentiler:** PROD-004, PROD-007, CART-004, CART-007 ve CHK-007'de beklenen sonuc belgede "varsayim" olarak isaretli; CART-008'de ise davranisin gozlenecegi belirtilmis. Otomasyon, bu beklentiler gercek gereksinim olarak netlesmeden bu senaryolarda guvenilir bir dogrulama uretemez.
- **Bilinen hatalar:** PROD-012, CART-006, CART-007, CHK-007, HOME-003 manuel testte Bug olarak isaretli. Bunlar ana suite'e konursa suite surekli kirmizi olur; ayri bir "bilinen-hata" grubunda tutulmasi onerilir.
