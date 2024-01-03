package com.hua.Distributed.systems.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Farmer {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Integer afm;

    @Column
    private String fullName;

    @Column
    private Integer cropArea;

    @Column
    private Integer production;

    @Column
    private Boolean insurance;

    @Column
    private Integer phoneNumber;

    @Column
    private String email;

    @OneToMany(mappedBy="farmer", cascade = CascadeType.ALL)
    private List<ApplicationForm> forms;

    public Farmer(){
    }

    public Farmer(Integer id, Integer afm, String fullName, Integer cropArea, Integer production, Boolean insurance, Integer phoneNumber, String email) {
        this.id = id;
        this.afm = afm;
        this.fullName = fullName;
        this.cropArea = cropArea;
        this.production = production;
        this.insurance = insurance;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAfm() {
        return afm;
    }

    public void setAfm(Integer afm) {
        this.afm = afm;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Integer getCropArea() {
        return cropArea;
    }

    public void setCropArea(Integer cropArea) {
        this.cropArea = cropArea;
    }

    public Integer getProduction() {
        return production;
    }

    public void setProduction(Integer production) {
        this.production = production;
    }

    public Boolean getInsurance() {
        return insurance;
    }

    public void setInsurance(Boolean insurance) {
        this.insurance = insurance;
    }

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                ", afm=" + afm +
                ", fullName='" + fullName + '\'' +
                ", cropArea=" + cropArea +
                ", production=" + production +
                ", insurance=" + insurance +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                '}';
    }
}
