package emailaddress;

import java.util.Objects;

public class EmailAddress {
    private final String localPart;
    private final String domain;

    public static EmailAddress parse(String value) {
        var atIndex = value.lastIndexOf('@');
        if (atIndex < 1 || atIndex == value.length() - 1) {
            throw new IllegalArgumentException(
                "EmailAddress must be two parts separated by @"
            );
        }
        return new EmailAddress(
            value.substring(0, atIndex),
            value.substring(atIndex + 1)
        );
    }

    public EmailAddress(final String localPart, final String domain) {
        this.localPart = localPart;
        this.domain = domain;
    }

    public String getLocalPart() {
        return localPart;
    }

    public String getDomain() {
        return domain;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EmailAddress that = (EmailAddress) o;
        return Objects.equals(localPart, that.localPart) && Objects.equals(domain,
            that.domain);
    }

    @Override
    public int hashCode() {
        return Objects.hash(localPart, domain);
    }

    @Override
    public String toString() {
        return localPart + "@" + domain;
    }
}
