package com.marcosimon.autosurvey.autosurvey;


import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;


@Document
public class AutoSurvey {


    @MongoId(value = FieldType.OBJECT_ID)
    private String id;
    private String country;
    private long rent;
    private long utilities;
    private long food;
    private long basicItems;
    private long transportation;
    private long educationTotal;
    private long educationSupplies;
    private long educationFee;
    private String educationType;
    private String accommodationType;
    private String profession;
    private String locationGiven;
    private String locationClustered;
    private int numResidents;
    private int numIncomes;
    private int numFullIncomes;
    private int numChildren;
    private long totalIncome;
    private String comments;
    private String orgId;
    private String orgName;



    public AutoSurvey() {
    }

    public AutoSurvey(String id, String country, long rent, long utilities, long food, long basicItems, long transportation, long educationTotal, long educationSupplies, long educationFee, String educationType, String accommodationType, String profession, String locationGiven, String locationClustered, int numResidents, int numIncomes, int numFullIncomes, int numChildren, long totalIncome, String comments, String orgId, String orgName) {
        this.id = id;
        this.country = country;
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
        this.orgId = orgId;
        this.orgName = orgName;
    }

    public AutoSurvey(String country, long rent, long utilities, long food, long basicItems, long transportation, long educationTotal, long educationSupplies, long educationFee, String educationType, String accommodationType, String profession, String locationGiven, String locationClustered, int numResidents, int numIncomes, int numFullIncomes, int numChildren, long totalIncome, String comments, String orgId, String orgName) {
        this.country = country;
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
        this.orgId = orgId;
        this.orgName = orgName;
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

    public long getRent() {
        return rent;
    }

    public void setRent(long rent) {
        this.rent = rent;
    }

    public long getUtilities() {
        return utilities;
    }

    public void setUtilities(long utilities) {
        this.utilities = utilities;
    }

    public long getFood() {
        return food;
    }

    public void setFood(long food) {
        this.food = food;
    }

    public long getBasicItems() {
        return basicItems;
    }

    public void setBasicItems(long basicItems) {
        this.basicItems = basicItems;
    }

    public long getTransportation() {
        return transportation;
    }

    public void setTransportation(long transportation) {
        this.transportation = transportation;
    }

    public long getEducationTotal() {
        return educationTotal;
    }

    public void setEducationTotal(long educationTotal) {
        this.educationTotal = educationTotal;
    }

    public long getEducationSupplies() {
        return educationSupplies;
    }

    public void setEducationSupplies(long educationSupplies) {
        this.educationSupplies = educationSupplies;
    }

    public long getEducationFee() {
        return educationFee;
    }

    public void setEducationFee(long educationFee) {
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

    public int getNumResidents() {
        return numResidents;
    }

    public void setNumResidents(int numResidents) {
        this.numResidents = numResidents;
    }

    public int getNumIncomes() {
        return numIncomes;
    }

    public void setNumIncomes(int numIncomes) {
        this.numIncomes = numIncomes;
    }

    public int getNumFullIncomes() {
        return numFullIncomes;
    }

    public void setNumFullIncomes(int numFullIncomes) {
        this.numFullIncomes = numFullIncomes;
    }

    public int getNumChildren() {
        return numChildren;
    }

    public void setNumChildren(int numChildren) {
        this.numChildren = numChildren;
    }

    public long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
