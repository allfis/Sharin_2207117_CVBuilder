package com.example.cvbuilder;

public class CV {

    private final int id;
    private final String name;
    private final String email;
    private final String phone;
    private final String address;
    private final String education;
    private final String skills;
    private final String experience;
    private final String projects;

    public CV(int id, String name, String email, String phone, String address,
              String education, String skills, String experience, String projects) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.experience = experience;
        this.projects = projects;
    }

    public CV(String name, String email, String phone, String address,
              String education, String skills, String experience, String projects) {
        this.id = -1; // or 0 or some default
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.education = education;
        this.skills = skills;
        this.experience = experience;
        this.projects = projects;
    }


    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
    public String getAddress() { return address; }
    public String getEducation() { return education; }
    public String getSkills() { return skills; }
    public String getExperience() { return experience; }
    public String getProjects() { return projects; }
}
