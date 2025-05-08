package com.udistrital.tiendaderopa;

/**
 * Base class representing a person in the system.
 * Contains common attributes and methods for authentication.
 */
public class Person {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private boolean isLoggedIn;

    /**
     * Constructor for Person
     * @param firstName Person's first name
     * @param lastName Person's last name
     * @param email Email address (used as username)
     * @param password Login password
     */
    public Person(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.isLoggedIn = false;
    }

    /**
     * Authenticates the user and starts a session
     * @param email Email to authenticate
     * @param password Password to authenticate
     * @return true if authentication was successful, false otherwise
     */
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.isLoggedIn = true;
            System.out.println("Login successful for " + this.firstName);
            return true;
        }
        System.out.println("Invalid email or password");
        return false;
    }

    /**
     * Ends the current user session
     */
    public void logout() {
        this.isLoggedIn = false;
        System.out.println("Logout successful for " + this.firstName);
    }

    // Getters and Setters
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Checks if the user is currently logged in
     * @return true if user is logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    // Note: No setter for password for security reasons
}