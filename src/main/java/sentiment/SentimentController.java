package sentiment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SentimentController {

    @GetMapping("/sentiment")
    public SentimentResponse sentiment(@RequestParam("text") String text) {
        if (text == null || text.isBlank()) {
            return new SentimentResponse("neutral");
        }

        String normalized = text.trim().toLowerCase();

        if (normalized.contains("hello")) {
            return new SentimentResponse("positive");
        }

        String sentiment = (normalized.length() % 2 == 0) ? "positive" : "negative";
        return new SentimentResponse(sentiment);
    }

    public record SentimentResponse(String sentiment) {
    }
}
