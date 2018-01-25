package model;

public class Customer {
    private String name;
    private String email;
    private String password;
    private String contact;
    private String country;

    public Customer(String name, String email, String password, String contact, String country) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
