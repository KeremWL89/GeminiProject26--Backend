package com.Backend.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;


@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		// --- .ENV DOSYASINI PROGRAMATİK OKUMA KODU ---
		// allah kahretsin neler yapmak zorunda kaldık
		try {
			if (Files.exists(Paths.get(".env"))) {
				List<String> lines = Files.readAllLines(Paths.get(".env"));
				for (String line : lines) {
					line = line.trim();
					// Boş satırları veya yorum satırlarını atla
					if (line.isEmpty() || line.startsWith("#")) continue;

					String[] parts = line.split("=", 2);
					if (parts.length == 2) {
						String key = parts[0].trim();
						String value = parts[1].trim();
						// Sistem çevre değişkeni olarak belleğe yaz
						System.setProperty(key, value);
					}
				}
				System.out.println(">>> .env dosyası başarıyla belleğe yüklendi! <<<");
			} else {
				System.out.println(">>> UYARI: Proje kök dizininde .env dosyası bulunamadı! <<<");
			}
		} catch (IOException e) {
			System.err.println(".env dosyası okunurken hata oluştu: " + e.getMessage());
		}
		// ---------------------------------------------

		SpringApplication.run(Application.class, args);
	}

}
