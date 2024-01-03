package com.hua.Distributed.systems.project.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class DamageCategory {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column
    private Boolean fire;

    @Column
    private Boolean flood;

    @Column
    private Boolean heatWave;

    @Column
    private Boolean snow;

    @Column
    private Boolean plantDesease;

    @Column
    private Boolean storm;

    @Column
    private Boolean faunaInvasion;

    @Column
    private Boolean other;

    @OneToMany(mappedBy = "damageCategory", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<ApplicationForm> forms;

    public DamageCategory(Integer id, Boolean fire, Boolean flood, Boolean heatWave, Boolean snow, Boolean plantDesease, Boolean storm, Boolean faunaInvasion, Boolean other) {
        this.id = id;
        this.fire = fire;
        this.flood = flood;
        this.heatWave = heatWave;
        this.snow = snow;
        this.plantDesease = plantDesease;
        this.storm = storm;
        this.faunaInvasion = faunaInvasion;
        this.other = other;
    }
    public DamageCategory(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getFire() {
        return fire;
    }

    public void setFire(Boolean fire) {
        this.fire = fire;
    }

    public Boolean getFlood() {
        return flood;
    }

    public void setFlood(Boolean flood) {
        this.flood = flood;
    }

    public Boolean getHeatWave() {
        return heatWave;
    }

    public void setHeatWave(Boolean heatWave) {
        this.heatWave = heatWave;
    }

    public Boolean getSnow() {
        return snow;
    }

    public void setSnow(Boolean snow) {
        this.snow = snow;
    }

    public Boolean getPlantDesease() {
        return plantDesease;
    }

    public void setPlantDesease(Boolean plantDesease) {
        this.plantDesease = plantDesease;
    }

    public Boolean getStorm() {
        return storm;
    }

    public void setStorm(Boolean storm) {
        this.storm = storm;
    }

    public Boolean getFaunaInvasion() {
        return faunaInvasion;
    }

    public void setFaunaInvasion(Boolean faunaInvasion) {
        this.faunaInvasion = faunaInvasion;
    }

    public Boolean getOther() {
        return other;
    }

    public void setOther(Boolean other) {
        this.other = other;
    }

    @Override
    public String toString() {
        return "DamageCategory{" +
                "id=" + id +
                ", fire=" + fire +
                ", flood=" + flood +
                ", heatWave=" + heatWave +
                ", snow=" + snow +
                ", plantDesease=" + plantDesease +
                ", storm=" + storm +
                ", faunaInvasion=" + faunaInvasion +
                ", other=" + other +
                '}';
    }
}
