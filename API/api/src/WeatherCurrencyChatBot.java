import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class WeatherCurrencyChatBot {

    // API Keys - Replace with your actual API keys
    private static final String WEATHER_API_KEY = "d8da3ea8d06b9046841c6efd6b9d5f68";
    private static final String EXCHANGE_API_KEY = "c7fa7d0c0ae6294d8d978904";

    // API URLs
    private static final String WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/weather";
    private static final String EXCHANGE_BASE_URL = "https://v6.exchangerate-api.com/v6/";

    private Scanner scanner;

    public WeatherCurrencyChatBot() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("🤖 Weather & Currency Exchange Bot");
        System.out.println("=====================================");
        System.out.println("Hello! I can help you with:");
        System.out.println("1. Current weather for any city");
        System.out.println("2. Currency exchange rates");
        System.out.println("\nType 'help' for commands or 'quit' to exit.");
        System.out.println("=====================================\n");

        while (true) {
            System.out.print("You: ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.equals("quit") || input.equals("exit")) {
                System.out.println("Bot: Goodbye! Have a great day! 👋");
                break;
            }

            processInput(input);
        }
    }

    private void processInput(String input) {
        if (input.equals("help")) {
            showHelp();
        } else if (input.contains("weather")) {
            handleWeatherRequest(input);
        } else if (input.contains("currency") || input.contains("exchange") || input.contains("rate")) {
            handleCurrencyRequest(input);
        } else if (Pattern.matches(".*\\b[a-z]{3}\\s+to\\s+[a-z]{3}\\b.*", input)) {
            handleCurrencyRequest(input);
        } else {
            System.out.println("Bot: I can help you with weather information and currency exchange rates.");
            System.out.println("     Try saying 'weather in London' or 'USD to EUR' or type 'help' for more options.");
        }
    }

    private void showHelp() {
        System.out.println("Bot: Here are the commands I understand:");
        System.out.println("📍 Weather Commands:");
        System.out.println("   - 'weather in [city]' - Get current weather");
        System.out.println("   - 'weather [city]' - Get current weather");
        System.out.println("   - Example: 'weather in New York'");
        System.out.println("\n💱 Currency Commands:");
        System.out.println("   - '[from] to [to]' - Get exchange rate");
        System.out.println("   - 'currency [from] to [to]' - Get exchange rate");
        System.out.println("   - Example: 'USD to EUR' or 'currency GBP to JPY'");
        System.out.println("\n📝 Other Commands:");
        System.out.println("   - 'help' - Show this help message");
        System.out.println("   - 'quit' or 'exit' - Exit the bot");
    }

    private void handleWeatherRequest(String input) {
        String city = extractCityFromWeatherRequest(input);
        if (city.isEmpty()) {
            System.out.println("Bot: Please specify a city. Example: 'weather in London'");
            return;
        }

        try {
            String weatherData = getWeatherData(city);
            if (weatherData != null) {
                displayWeatherInfo(weatherData, city);
            } else {
                System.out.println("Bot: Sorry, I couldn't fetch weather data for " + city + ". Please check the city name.");
            }
        } catch (Exception e) {
            System.out.println("Bot: Sorry, there was an error getting weather data: " + e.getMessage());
        }
    }

    private void handleCurrencyRequest(String input) {
        String[] currencies = extractCurrenciesFromRequest(input);
        if (currencies.length != 2) {
            System.out.println("Bot: Please specify currency codes. Example: 'USD to EUR'");
            return;
        }

        try {
            double rate = getExchangeRate(currencies[0], currencies[1]);
            if (rate > 0) {
                System.out.printf("Bot: 💱 1 %s = %.4f %s%n",
                        currencies[0].toUpperCase(), rate, currencies[1].toUpperCase());
                System.out.printf("     💰 100 %s = %.2f %s%n",
                        currencies[0].toUpperCase(), rate * 100, currencies[1].toUpperCase());
            } else {
                System.out.println("Bot: Sorry, I couldn't get the exchange rate. Please check the currency codes.");
            }
        } catch (Exception e) {
            System.out.println("Bot: Sorry, there was an error getting exchange rate: " + e.getMessage());
        }
    }

    private String extractCityFromWeatherRequest(String input) {
        Pattern pattern = Pattern.compile("weather\\s+(?:in\\s+)?(.+)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return matcher.group(1).trim();
        }
        return "";
    }

    private String[] extractCurrenciesFromRequest(String input) {
        Pattern pattern = Pattern.compile("(?:currency\\s+)?([a-z]{3})\\s+to\\s+([a-z]{3})");
        Matcher matcher = pattern.matcher(input);
        if (matcher.find()) {
            return new String[]{matcher.group(1), matcher.group(2)};
        }
        return new String[0];
    }

    private String getWeatherData(String city) throws IOException {
        String urlString = String.format("%s?q=%s&appid=%s&units=metric",
                WEATHER_BASE_URL, city.replace(" ", "%20"), WEATHER_API_KEY);

        System.out.println("Debug: Requesting URL: " + urlString.replace(WEATHER_API_KEY, "***API_KEY***"));
        return makeHttpRequest(urlString);
    }

    private double getExchangeRate(String fromCurrency, String toCurrency) throws IOException {
        String urlString = String.format("%s%s/pair/%s/%s",
                EXCHANGE_BASE_URL, EXCHANGE_API_KEY, fromCurrency.toUpperCase(), toCurrency.toUpperCase());
       System.out.println("Debug: Requesting URL: " + urlString);
        String response = makeHttpRequest(urlString);
        if (response != null) {
            return parseExchangeRate(response);
        }
        return -1;
    }

    private String makeHttpRequest(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int responseCode = connection.getResponseCode();
        System.out.println("Debug: HTTP Response Code: " + responseCode);

        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            // Read error response
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
            StringBuilder errorResponse = new StringBuilder();
            String line;

            while ((line = errorReader.readLine()) != null) {
                errorResponse.append(line);
            }
            errorReader.close();
            System.out.println("Debug: Error Response: " + errorResponse.toString());
        }
        return null;
    }

    private void displayWeatherInfo(String jsonResponse, String city) {
        try {
            // Simple JSON parsing for weather data
            double temp = parseJsonValue(jsonResponse, "temp");
            double feelsLike = parseJsonValue(jsonResponse, "feels_like");
            int humidity = (int) parseJsonValue(jsonResponse, "humidity");
            String description = parseJsonString(jsonResponse, "description");

            System.out.println("Bot: 🌤️ Weather in " + capitalizeWords(city) + ":");
            System.out.printf("     🌡️ Temperature: %.1f°C (feels like %.1f°C)%n", temp, feelsLike);
            System.out.printf("     💧 Humidity: %d%%%n", humidity);
            System.out.printf("     📝 Description: %s%n", capitalizeWords(description));

        } catch (Exception e) {
            System.out.println("Bot: Got weather data but couldn't parse it properly.");
        }
    }

    private double parseJsonValue(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*([\\d.-]+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return 0;
    }

    private String parseJsonString(String json, String key) {
        Pattern pattern = Pattern.compile("\"" + key + "\"\\s*:\\s*\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return "";
    }

    private double parseExchangeRate(String json) {
        Pattern pattern = Pattern.compile("\"conversion_rate\"\\s*:\\s*([\\d.]+)");
        Matcher matcher = pattern.matcher(json);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group(1));
        }
        return -1;
    }

    private String capitalizeWords(String text) {
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
                result.append(c);
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                result.append(c);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println("⚠️ SETUP REQUIRED:");
        System.out.println("1. Get API key from https://openweathermap.org/api");
        System.out.println("2. Get API key from https://www.exchangerate-api.com");
        System.out.println("3. Replace YOUR_OPENWEATHER_API_KEY and YOUR_EXCHANGERATE_API_KEY in the code");
        System.out.println("4. Recompile and run the program");
        System.out.println();

        if (WEATHER_API_KEY.equals("YOUR_OPENWEATHER_API_KEY") ||
                EXCHANGE_API_KEY.equals("YOUR_EXCHANGERATE_API_KEY")) {
            System.out.println("❌ Please set up your API keys before running the bot!");
            System.out.println("The bot will still start but API calls will fail.");
            System.out.println();
        }

        WeatherCurrencyChatBot bot = new WeatherCurrencyChatBot();
        bot.start();
    }
}