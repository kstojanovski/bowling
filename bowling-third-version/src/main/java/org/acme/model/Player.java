package org.acme.model;

public class Player {

    private String firstname;
    private String lastname;
    private String alias;

    public Player(String firstname, String lastname, String alias) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.alias = alias;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getState() {
        return String.format("%s %s - %s", firstname, lastname, alias);
    }
}
