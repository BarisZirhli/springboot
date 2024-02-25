import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Example {
    public static void main(String[] args) {
        // Örnek bir liste oluştur
        List<Map<String, Object>> authors = new ArrayList<>();

        // İlk yazarın bilgilerini ekle
        Map<String, Object> author1 = new HashMap<>();
        author1.put("name", "John Doe");
        author1.put("age", 40);
        author1.put("country", "USA");
        authors.add(author1);

        // İkinci yazarın bilgilerini ekle
        Map<String, Object> author2 = new HashMap<>();
        author2.put("name", "Jane Smith");
        author2.put("age", 35);
        author2.put("country", "UK");
        authors.add(author2);

        // Dönüştürücüyü kullanarak veriyi bir JSON dizesine dönüştür
        ListMapConverter converter = new ListMapConverter();
        String json = converter.convertToDatabaseColumn(authors);
        System.out.println("JSON: " + json);

        // JSON dizesini tekrar orijinal veri yapısına dönüştür
        List<Map<String, Object>> convertedAuthors = converter.convertToEntityAttribute(json);

        // Dönüştürülmüş veriyi kontrol et
        System.out.println("Converted authors:");
        for (Map<String, Object> author : convertedAuthors) {
            System.out.println("Name: " + author.get("name") + ", Age: " + author.get("age") + ", Country: " + author.get("country"));
        }
    }
}
