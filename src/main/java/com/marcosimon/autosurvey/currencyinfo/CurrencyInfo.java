package com.marcosimon.autosurvey.currencyinfo;

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
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    @Column(name = "currency_info_id")
    private String currencyInfoId;

    private String currency;

    private Float exchangeRate;

    @MapsId
    @OneToOne(mappedBy =  "currencyInfo")
    @JoinColumn(name = "currency_info_id")
    private CountryInfo countryInfo;

    public CurrencyInfo(String currency, Float exchangeRate) {
        this.currency = currency;
        this.exchangeRate = exchangeRate;
    }
}
