package com.marcosimon.autosurvey.countryinfo;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.marcosimon.autosurvey.currencyinfo.CurrencyInfo;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
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
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "country_info_id")
  private String countryInfoId;

  @Column(name = "country_name")
  private String countryName;

  private String date;

  @Column(name = "currency_ref")
  private String currencyRef;

  @JsonManagedReference
  @OneToMany(mappedBy = "countryInfo")
  private List<MsfOrgInfo> orgInfoList;

  @OneToOne
  @PrimaryKeyJoinColumn
  private CurrencyInfo currencyInfo;

  public CountryInfo(String countryName, String date, String currencyRef) {
    this.countryName = countryName;
    this.date = date;
    this.currencyRef = currencyRef;
  }
}
