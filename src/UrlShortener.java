import java.util.concurrent.atomic.AtomicLong;

public class UrlShortener {
    private static final String DOMAIN = "http://short.ly/";
    private final UrlRepository repository = new UrlRepository();
    private final AtomicLong idCounter = new AtomicLong(1000);

    public String shortenUrl(String longUrl) {
        if (repository.containsLongUrl(longUrl)) {
            return DOMAIN + repository.getShortUrl(longUrl);
        }

        long id = idCounter.incrementAndGet();
        String shortKey = Base62Encoder.encode(id);
        repository.save(shortKey, longUrl);
        return DOMAIN + shortKey;
    }

    public String expandUrl(String shortUrl) {
        String shortKey = shortUrl.replace(DOMAIN, "");
        String longUrl = repository.getLongUrl(shortKey);
        if (longUrl == null) {
            return "Error: Short URL not found!";
        }
        return longUrl;
    }
}

