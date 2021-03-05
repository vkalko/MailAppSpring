package entity;

import java.util.Objects;

public class Recipient {

    private String name;
    private String email;

    public Recipient() {
    }

    public Recipient(String email) {
        this.email = email;
    }

    public Recipient(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format("Recipient: {name: %s, email: %s}", name, email);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipient recipient = (Recipient) o;
        return Objects.equals(name, recipient.name) && Objects.equals(email, recipient.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, email);
    }


}
