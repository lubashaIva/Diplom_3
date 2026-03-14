package Assets;

public class Resources {

    // Данные для регистрации пользователя
    public static final String name = "Любовь";
    public static final String email = "ivanova_39@mail.ru";
    public static final String valid_password = "654321";
    public static final String wrong_password = "123";

    public static final String mainURL = "https://stellarburgers.education-services.ru";
    public static final String registerURL = "https://stellarburgers.education-services.ru/register";
    public static final String forgotPasswordURL = "https://stellarburgers.education-services.ru/forgot-password";
    public static final String loginURL = "https://stellarburgers.education-services.ru/login";

    public static final String baseApiUrl = "https://stellarburgers.education-services.ru";
    public static final String authApiUrl = "api/auth/register";
    public static final String loginApiUrl = "api/auth/login";
    public static final String userApiUrl = "api/auth/user";

    // Выбор браузера, на котором запустить тесты (chrome или yandex)
    public static final String chromeBrowser = "chrome";
    public static final String yandexBrowser = "yandex";
    public static final String pathToDriver = "C:\\WebDriver\\bin\\%sdriver.exe";
}