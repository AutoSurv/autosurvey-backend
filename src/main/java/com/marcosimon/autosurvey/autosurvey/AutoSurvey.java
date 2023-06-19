package com.marcosimon.autosurvey.autosurvey;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.countrygroup.CountryGroup;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="autosurvey")
public class AutoSurvey {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(
            name = "system-uuid", strategy = "uuid"
    )
    @Column(name="survey_id")
    private String id;

    @Column(name = "country")
    private String country;
    @Column(name = "rent")
    private long rent;

    @Column(name = "utilities")
    private long utilities;

    @Column(name = "food")
    private long food;

    @Column(name = "basic_items")
    private long basicItems;

    @Column(name = "transportation")
    private long transportation;

    @Column(name = "education_total")
    private long educationTotal;

    @Column(name = "education_supplies")
    private long educationSupplies;

    @Column(name = "education_fee")
    private long educationFee;

    @Column(name = "education_type")
    private String educationType;

    @Column(name = "accommodation_type")
    private String accommodationType;

    @Column(name = "profession")
    private String profession;

    @Column(name = "location_given")
    private String locationGiven;

    @Column(name = "location_clustered")
    private String locationClustered;

    @Column(name = "num_residents")
    private int numResidents;

    @Column(name = "num_incomes")
    private int numIncomes;

    @Column(name = "num_full_incomes")
    private int numFullIncomes;

    @Column(name = "num_children")
    private int numChildren;
    @Column(name = "total_income")
    private long totalIncome;

    @Column(name = "comments")
    private String comments;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonIgnore
    private CountryGroup countryGroup;

    public AutoSurvey() {
    }

    public AutoSurvey(String country, long rent, long utilities, long food, long basicItems, long transportation, long educationTotal, long educationSupplies, long educationFee, String educationType, String accommodationType, String profession, String locationGiven, String locationClustered, int numResidents, int numIncomes, int numFullIncomes, int numChildren, long totalIncome, String comments) {
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
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public CountryGroup getCountryGroup() {
        return countryGroup;
    }

    public void setCountryGroup(CountryGroup countryGroup) {
        this.countryGroup = countryGroup;
    }
}
