package com.hua.Distributed.systems.project.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ApplicationForm {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private String location;

    @Column
    private String description;

    @Column
    private Date dateOfSubmission;

    @Column
    private Boolean damagesRecovery;

    @Column
    private int recoveryAmount;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "farmer_id")
    private Farmer farmer;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "damage_category_id")
    private DamageCategory damageCategory;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "inspector_id")
    private Inspector inspector;

    public Inspector getInspector() {
        return inspector;
    }

    public void setInspector(Inspector inspector) {
        this.inspector = inspector;
    }

    public DamageCategory getDamageCategory() {
        return damageCategory;
    }

    public void setDamageCategory(DamageCategory damageCategory) {
        this.damageCategory = damageCategory;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public ApplicationForm(){
    }

    public ApplicationForm(Integer id, String location, String description, Date dateOfSubmission, Boolean damagesRecovery, int recoveryAmount) {
        this.id = id;
        this.location = location;
        this.description = description;
        this.dateOfSubmission = dateOfSubmission;
        this.damagesRecovery = damagesRecovery;
        this.recoveryAmount = recoveryAmount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateOfSubmission() {
        return dateOfSubmission;
    }

    public void setDateOfSubmission(Date dateOfSubmission) {
        this.dateOfSubmission = dateOfSubmission;
    }

    public Boolean getDamagesRecovery() {
        return damagesRecovery;
    }

    public void setDamagesRecovery(Boolean damagesRecovery) {
        this.damagesRecovery = damagesRecovery;
    }

    public int getRecoveryAmount() {
        return recoveryAmount;
    }

    public void setRecoveryAmount(int recoveryAmount) {
        this.recoveryAmount = recoveryAmount;
    }

    @Override
    public String toString() {
        return "ApplicationForm{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", dateOfSubmission=" + dateOfSubmission +
                ", damagesRecovery=" + damagesRecovery +
                ", recoveryAmount=" + recoveryAmount +
                '}';
    }
}
