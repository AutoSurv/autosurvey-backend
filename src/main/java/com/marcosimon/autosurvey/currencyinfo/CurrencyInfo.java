package com.marcosimon.autosurvey.currencyinfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "currency_info")
public class CurrencyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "currency_info_id")
    private Long currencyInfoId;

    private String currency;

    @Column(name = "exchange_rate")
    private Float exchangeRate;

    @JsonIgnore
    @OneToMany(mappedBy = "currencyInfo")
    private List<MsfOrgInfo> msfOrgInfo;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "country_info_id", nullable = false)
    private CountryInfo countryInfo;

    public CurrencyInfo(String currency, Float exchangeRate, CountryInfo countryInfo) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
        this.countryInfo = countryInfo;
    }
}
