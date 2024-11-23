package travelator.mobile;

public class Application {

    private final UserPreference preference;

    public Application(final UserPreference preference) {
        this.preference = preference;
    }

    public void showWelcome() {
        new WelcomeView(preference).show();
    }

    public void editPreferences() {
        new PreferenceView(preference).show();
    }
}
