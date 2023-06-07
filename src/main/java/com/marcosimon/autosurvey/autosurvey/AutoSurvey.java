package com.marcosimon.autosurvey.autosurvey;

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
    @Column(name="autosurvey_id")
    private String id;

    @Column(name="country")
    private String country;

    @Column(name = "family_member")
    private int familyMember;

    @Column(name = "num_incomes")
    private int numIncomes;

    @Column(name = "total_income")
    private long totalIncome;

    @Column(name = "job")
    private String job;

    @Column(name = "area")
    private String area;

    @Column(name = "rent")
    private long rent;

    @Column(name = "bills")
    private long bills;

    @Column(name = "transport")
    private long transport;

    @Column(name = "food")
    private long food;


    public AutoSurvey() {
    }

    public AutoSurvey(String country, int familyMember, int numIncomes, long totalIncome, String job, String area, long rent, long bills, long transport, long food) {
        this.country = country;
        this.familyMember = familyMember;
        this.numIncomes = numIncomes;
        this.totalIncome = totalIncome;
        this.job = job;
        this.area = area;
        this.rent = rent;
        this.bills = bills;
        this.transport = transport;
        this.food = food;
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
    public int getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(int familyMember) {
        this.familyMember = familyMember;
    }

    public int getNumIncomes() {
        return numIncomes;
    }

    public void setNumIncomes(int numIncomes) {
        this.numIncomes = numIncomes;
    }

    public long getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(long totalIncome) {
        this.totalIncome = totalIncome;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public long getRent() {
        return rent;
    }

    public void setRent(long rent) {
        this.rent = rent;
    }

    public long getBills() {
        return bills;
    }

    public void setBills(long bills) {
        this.bills = bills;
    }

    public long getTransport() {
        return transport;
    }

    public void setTransport(long transport) {
        this.transport = transport;
    }

    public long getFood() {
        return food;
    }

    public void setFood(long foods) {
        this.food = foods;
    }
}
