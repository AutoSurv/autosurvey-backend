package com.marcosimon.autosurvey.autosurvey;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.marcosimon.autosurvey.organization.Organization;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document
public class AutoSurvey {


    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String country;
    private Long year;
    private Long rent;
    private Long utilities;
    private Long food;
    private Long basicItems;
    private Long transportation;
    private Long educationTotal;
    private Long educationSupplies;
    private Long educationFee;
    private String educationType;
    private String accommodationType;
    private String profession;
    private String locationGiven;
    private String locationClustered;
    private Integer numResidents;
    private Integer numIncomes;
    private Integer numFullIncomes;
    private Integer numChildren;
    private Long totalIncome;
    private String comments;

    @DocumentReference(lazy = true)
    @JsonBackReference
    private Organization organization;




    public AutoSurvey() {
    }

    public AutoSurvey(String id, String country, Long year, Long rent, Long utilities, Long food, Long basicItems, Long transportation, Long educationTotal, Long educationSupplies, Long educationFee, String educationType, String accommodationType, String profession, String locationGiven, String locationClustered, Integer numResidents, Integer numIncomes, Integer numFullIncomes, Integer numChildren, Long totalIncome, String comments, Organization organization) {
        this.id = id;
        this.country = country;
        this.year = year;
        this.rent = rent;
        this.utilities = utilities;
        this.food = food;
        this.basicItems = basicItems;
        this.transportation = transportation;
        this.educationTotal = educationTotal;
        this.educationSupplies = educationSupplies;
        this.educationFee = educationFee;
        this.educationType = educationType;
        this.accommodationType = accommodationType;
        this.profession = profession;
        this.locationGiven = locationGiven;
        this.locationClustered = locationClustered;
        this.numResidents = numResidents;
        this.numIncomes = numIncomes;
        this.numFullIncomes = numFullIncomes;
        this.numChildren = numChildren;
        this.totalIncome = totalIncome;
        this.comments = comments;
        this.organization = organization;
    }

    public AutoSurvey(String country, Long year, Long rent, Long utilities, Long food, Long basicItems, Long transportation, Long educationTotal, Long educationSupplies, Long educationFee, String educationType, String accommodationType, String profession, String locationGiven, String locationClustered, Integer numResidents, Integer numIncomes, Integer numFullIncomes, Integer numChildren, Long totalIncome, String comments, Organization organization) {
        this.country = country;
        this.year = year;
        this.rent = rent;
        this.utilities = utilities;
        this.food = food;
        this.basicItems = basicItems;
        this.transportation = transportation;
        this.educationTotal = educationTotal;
        this.educationSupplies = educationSupplies;
        this.educationFee = educationFee;
        this.educationType = educationType;
        this.accommodationType = accommodationType;
        this.profession = profession;
        this.locationGiven = locationGiven;
        this.locationClustered = locationClustered;
        this.numResidents = numResidents;
        this.numIncomes = numIncomes;
        this.numFullIncomes = numFullIncomes;
        this.numChildren = numChildren;
        this.totalIncome = totalIncome;
        this.comments = comments;
        this.organization = organization;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getRent() {
        return rent;
    }

    public void setRent(Long rent) {
        this.rent = rent;
    }

    public Long getUtilities() {
        return utilities;
    }

    public void setUtilities(Long utilities) {
        this.utilities = utilities;
    }

    public Long getFood() {
        return food;
    }

    public void setFood(Long food) {
        this.food = food;
    }

    public Long getBasicItems() {
        return basicItems;
    }

    public void setBasicItems(Long basicItems) {
        this.basicItems = basicItems;
    }

    public Long getTransportation() {
        return transportation;
    }

    public void setTransportation(Long transportation) {
        this.transportation = transportation;
    }

    public Long getEducationTotal() {
        return educationTotal;
    }

    public void setEducationTotal(Long educationTotal) {
        this.educationTotal = educationTotal;
    }

    public Long getEducationSupplies() {
        return educationSupplies;
    }

    public void setEducationSupplies(Long educationSupplies) {
        this.educationSupplies = educationSupplies;
    }

    public Long getEducationFee() {
        return educationFee;
    }

    public void setEducationFee(Long educationFee) {
        this.educationFee = educationFee;
    }

    public String getEducationType() {
        return educationType;
    }

    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

    public String getAccommodationType() {
        return accommodationType;
    }

    public void setAccommodationType(String accommodationType) {
        this.accommodationType = accommodationType;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getLocationGiven() {
        return locationGiven;
    }

    public void setLocationGiven(String locationGiven) {
        this.locationGiven = locationGiven;
    }

    public String getLocationClustered() {
        return locationClustered;
    }

    public void setLocationClustered(String locationClustered) {
        this.locationClustered = locationClustered;
    }

    public Integer getNumResidents() {
        return numResidents;
    }

    public void setNumResidents(Integer numResidents) {
        this.numResidents = numResidents;
    }

    public Integer getNumIncomes() {
        return numIncomes;
    }

    public void setNumIncomes(Integer numIncomes) {
        this.numIncomes = numIncomes;
    }

    public Integer getNumFullIncomes() {
        return numFullIncomes;
    }

    public void setNumFullIncomes(Integer numFullIncomes) {
        this.numFullIncomes = numFullIncomes;
    }

    public Integer getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(Integer numChildren) {
        this.numChildren = numChildren;
    }

    public Long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Long getYear() {
        return year;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }
}
