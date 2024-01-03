package com.hua.Distributed.systems.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Inspector {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    private String areaOfInterest;

    @OneToMany(mappedBy = "inspector", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ApplicationForm> forms;

    public Inspector(Integer id, String fullName, String email, String areaOfInterest) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.areaOfInterest = areaOfInterest;
    }
    public Inspector() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAreaOfInterest() {
        return areaOfInterest;
    }

    public void setAreaOfInterest(String areaOfInterest) {
        this.areaOfInterest = areaOfInterest;
    }

    @Override
    public String toString() {
        return "Inspector{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", areaOfInterest='" + areaOfInterest + '\'' +
                '}';
    }
}
