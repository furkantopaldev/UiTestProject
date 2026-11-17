Element / sayfa	Locator türü
Seçilen locator	Tercih gerekçesi

Login e-posta (login)	CSS (data-qa)   	[data-qa='login-email']	        Benzersiz; name='email' iki formda da var
Login şifre (login)	    CSS (data-qa)       [data-qa='login-password']	    Benzersiz, test için ayrılmış özellik
Login butonu (login)	CSS (data-qa)   	[data-qa='login-button']	    Buton metni/stili değişse de çalışır
Signup isim (login)	    CSS (data-qa)       [data-qa='signup-name']	        name='name' bu sayfada benzersiz ama form özelliğine bağlı
Signup e-posta (login)	CSS (data-qa)	    [data-qa='signup-email']	    name='email' ile login alanıyla karışırdı (2 eşleşme)
Signup butonu (login)	CSS (data-qa)	    [data-qa='signup-button']	    Benzersiz
Login hata mesajı	    CSS (yapı)	        .login-form form p	            id/class/data-qa yok; metin ve stilden bağımsız, yapıya dayalı
CSRF gizli alanı	    Kullanılmaz	input   [name='csrfmiddlewaretoken']	Değeri her yüklemede değişir (dinamik attribute), locator’a konmaz