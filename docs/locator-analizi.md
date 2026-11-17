Locator Analizi

Uygulama: https://automationexercise.com

Tum locator'lar Chrome DevTools'ta (F12, Elements, Ctrl+F) aranarak dogrulanir. "Eslesme" sutunu arama kutusunda gorulen sayidir. Yalnizca 1 of 1 veren locator kullanilir.

Durum isaretleri:

[OK] DevTools'ta dogrulandi
[TEST] Testle dolayli dogrulandi (DevTools kontrolu de yapilmali)
[ ] Henuz dogrulanmadi
[x] Kullanilmaz
1. Locator secim kriterleri
Kriter	Soru
Benzersizlik	Locator sayfada tam olarak 1 element buluyor mu?
Okunabilirlik	Koda bakan biri hangi elementi hedefledigini anliyor mu?
Dayaniklilik	Tasarim, metin veya sira degisince kirilir mi?

Tercih sirasi: test attribute (data-qa), benzersiz id, anlamli CSS, goreli XPath. Mutlak XPath, nth-child ve degisken degerler (token, otomatik id) kullanilmaz.

2. Ana sayfa (/)
Durum	Element	Tur	Locator	Eslesme	Gerekce
[TEST]	Logo	CSS	.logo img		Sayfada tek logo var; HomePageTest'te kullaniliyor
[TEST]	Signup / Login menu baglantisi	CSS	a[href='/login']		href sabit, gorunen metin degisse de calisir
3. Login / Signup sayfasi (/login)
Durum	Element	Tur	Locator	Eslesme	Gerekce
[TEST]	Login basligi	CSS (yapi)	.login-form h2		Sayfaya dogru gelindigini dogrular
[OK]	Login e-posta	CSS (data-qa)	[data-qa='login-email']	1 of 1	name='email' iki formda da var (2 eslesme)
[OK]	Login sifre	CSS (data-qa)	[data-qa='login-password']	1 of 1	Test icin ayrilmis ozellik
[OK]	Login butonu	CSS (data-qa)	[data-qa='login-button']	1 of 1	Buton metni/stili degisse de calisir
[OK]	Signup isim	CSS (data-qa)	[data-qa='signup-name']	1 of 1	name='name' bu sayfada benzersiz ama form ozelligine bagli
[OK]	Signup e-posta	CSS (data-qa)	[data-qa='signup-email']	1 of 1	name='email' login alaniyla karisirdi
[OK]	Signup butonu	CSS (data-qa)	[data-qa='signup-button']	1 of 1	Benzersiz
[TEST]	Login hata mesaji	CSS (yapi)	.login-form form p		id/class/data-qa yok; metin ve stilden bagimsiz. Mesaj yalnizca hatali giristen sonra DOM'da olur
[X]	CSRF gizli alani	Kullanilmaz	input[name='csrfmiddlewaretoken']	-	Degeri her yuklemede degisir (dinamik attribute)
4. Kayit bilgileri sayfasi (/signup)

Bu bolum DevTools'ta dogrulandikca doldurulacak.

Durum	                                    Element	Tur	Aday locator	                                    Eslesme	Gerekce
[OK]	Mr. radyo butonu		                #id_gender1		
[OK]	Sifre		                            [data-qa='password']		
[OK]	Gun (select)		                    [data-qa='days']		
[OK]	Ay (select)		                        [data-qa='months']		
[ok]	Yil (select)		                    [data-qa='years']		
[OK]	Ad		                                [data-qa='first_name']		
[OK]	Soyad		                            [data-qa='last_name']		
[OK]	Adres		                            [data-qa='address']		
[OK]	Ulke (select)	    	                [data-qa='country']		
[OK]	Eyalet		                            [data-qa='state']		
[OK]	Sehir		                            [data-qa='city']		
[OK]	Posta kodu		                        [data-qa='zipcode']		
[OK]	Cep telefonu		                    [data-qa='mobile_number']		
[OK]	Hesap olustur butonu		            [data-qa='create-account']		
5. Hesap olusturuldu / silindi sayfalari
Durum	                                    Element	Tur	Aday locator	                                    Eslesme	Gerekce
[OK]	"ACCOUNT CREATED!" basligi		        [data-qa='account-created']		
[OK]	Continue butonu		                    [data-qa='continue-button']		
[OK]	"Logged in as ..." baglantisi			Kullanici adi degisken; sabit kisma gore bulunmali
[ok]	Delete Account baglantisi		        a[href='/delete_account']		
[OK]	"ACCOUNT DELETED!" basligi		        [data-qa='account-deleted']		
6. Zor durumlar ve cikarimlar
Durum	Ornek	Cikarim
Coklu eslesme	input[name='email'] login sayfasinda 2 sonuc verdi	By.name("email") sessizce ilk elementi secerdi; data-qa ile ayristirildi
Dinamik attribute	csrfmiddlewaretoken degeri her yuklemede degisiyor	Deger locator'a konmaz; zaten form alani olarak kullanilmaz
Id/class/data-qa olmayan element	Login hata mesaji <p style="color: red;">	Stile degil yapiya dayali CSS secildi; metin assertion'da dogrulanir
Degisken metin	"Logged in as <kullanici adi>"	contains ile sabit kisma gore bulunacak (XPath)
Tekrar eden class	Urun kartlari (.productinfo .add-to-cart)	Urunler sayfasina gelindiginde dogrulanacak