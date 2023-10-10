package com.marcosimon.autosurvey.currencyinfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "currency_info")
public class CurrencyInfo {
    @Id
    //@GeneratedValue(generator = "system-uuid")
    //@GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "currency_info_id")
    private String currencyInfoId;

    private String currency;

    @Column(name = "exchange_rate")
    private Float exchangeRate;

    @JsonIgnore
    @MapsId
    @OneToOne(mappedBy =  "currencyInfo")
    @JoinColumn(name = "currency_info_id")
    private CountryInfo countryInfo;

    public CurrencyInfo(String currency, Float exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }

    public CurrencyInfo(String currencyInfoId, String currency, Float exchangeRate) {
        this.currencyInfoId = currencyInfoId;
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }
}
