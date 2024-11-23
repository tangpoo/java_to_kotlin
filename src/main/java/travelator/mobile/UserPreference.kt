package travelator.mobile;

import java.util.Currency;
import java.util.Locale;

public class UserPreference {

    private String greeting;
    private Locale locale;
    private Currency currency;

    public UserPreference() {
        this("Hello", Locale.UK, Currency.getInstance(Locale.UK));
    }

    public UserPreference(final String greeting, final Locale locale, final Currency currency) {
        this.greeting = greeting;
        this.locale = locale;
        this.currency = currency;
    }

    public String getGreeting() {
        return greeting;
    }

    public Locale getLocale() {
        return locale;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setGreeting(final String greeting) {
        this.greeting = greeting;
    }

    public void setLocale(final Locale locale) {
        this.locale = locale;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }
}
