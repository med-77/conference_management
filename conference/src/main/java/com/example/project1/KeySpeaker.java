package com.example.project1;

public class KeySpeaker {
    private int id;
    private String name;
    private String country;
    private String institution;
    private String title;
    private String conference;

    public KeySpeaker(int id, String name, String country, String institution, String title, String conference) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.institution = institution;
        this.title = title;
        this.conference = conference;
    }

    // Getters and setters for all fields

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    @Override
    public String toString() {
        return "KeySpeaker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", institution='" + institution + '\'' +
                ", title='" + title + '\'' +
                ", conference='" + conference + '\'' +
                '}';
    }
}
