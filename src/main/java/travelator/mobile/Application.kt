package travelator.mobile

class Application(private var preference: UserPreference) {
    fun showWelcome() {
        WelcomeView(preference).show()
    }

    fun editPreferences() {
        preference = PreferenceView().showModal(preference)
    }
}
