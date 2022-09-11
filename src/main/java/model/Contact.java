package model;

import java.util.Objects;

public class Contact {
    private String name;
    private String surname;
    private String telephone;
    private String email;
    private String address;
    private String description;

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public Contact setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public Contact setTelephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public Contact setAddress(String address) {
        this.address = address;
        return this;
    }

    public Contact setDescription(String description) {
        this.description = description;
        return this;
    }

    public String name() {
        return name;
    }

    public String surname() {
        return surname;
    }

    public String telephone() {
        return telephone;
    }

    public String email() {
        return email;
    }

    public String address() {
        return address;
    }

    public String description() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Contact) obj;
        return Objects.equals(this.name, that.name) &&
                Objects.equals(this.surname, that.surname) &&
                Objects.equals(this.telephone, that.telephone) &&
                Objects.equals(this.email, that.email) &&
                Objects.equals(this.address, that.address) &&
                Objects.equals(this.description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, telephone, email, address, description);
    }

    @Override
    public String toString() {
        return "Contact[" +
                "name=" + name + ", " +
                "surname=" + surname + ", " +
                "telephone=" + telephone + ", " +
                "email=" + email + ", " +
                "address=" + address + ", " +
                "description=" + description + ']';
    }

}