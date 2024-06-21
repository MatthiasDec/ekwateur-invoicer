package org.invoicer.model;

public class IndividualCustomer extends Customer{
    private final String name;
    private final String surname;
    private final String gender;
    public IndividualCustomer(String reference, String name, String surname, String gender) {
        super(reference);
        this.name = name;
        this.surname = surname;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getGender() {
        return gender;
    }
}
