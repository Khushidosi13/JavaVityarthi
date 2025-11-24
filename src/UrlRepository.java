import java.util.HashMap;
import java.util.Map;

public class UrlRepository {
    private final Map<String, String> shortToLong = new HashMap<>();
    private final Map<String, String> longToShort = new HashMap<>();

    public void save(String shortUrl, String longUrl) {
        shortToLong.put(shortUrl, longUrl);
        longToShort.put(longUrl, shortUrl);
    }

    public String getLongUrl(String shortUrl) {
        return shortToLong.get(shortUrl);
    }

    public String getShortUrl(String longUrl) {
        return longToShort.get(longUrl);
    }

    public boolean containsLongUrl(String longUrl) {
        return longToShort.containsKey(longUrl);
    }
}
