package dev.orme.ludotheque.objects;

public class UserDetails {
    private final String givenName;
    private final String familyName;
    private final String email;
    private final String username;

    public UserDetails(String givenName, String familyName, String email, String username) {
        this.givenName = givenName;
        this.familyName = familyName;
        this.email = email;
        this.username = username;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }
}
