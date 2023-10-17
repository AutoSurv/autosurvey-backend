package com.marcosimon.autosurvey.countryinfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.currencyinfo.CurrencyInfo;
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
@Table(name = "country_info")
public class CountryInfo {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "country_info_id")
  private Long countryInfoId;

  @Column(name = "country_name")
  private String countryName;

  private String isoCode;

  private String year;

  @Column(name = "currency_ref")
  private String currencyRef;

  //@JsonManagedReference
  @JsonIgnore
  @OneToMany(mappedBy = "countryInfo", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<MsfOrgInfo> orgInfoList;

  //@JsonManagedReference
  @JsonIgnore
  @OneToMany(mappedBy = "countryInfo", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<CurrencyInfo> currencyInfoList;

  public CountryInfo(String countryName, String isoCode, String year, String currencyRef) {
    this.countryName = countryName;
    this.isoCode = isoCode;
    this.year = year;
    this.currencyRef = currencyRef;
  }
}
