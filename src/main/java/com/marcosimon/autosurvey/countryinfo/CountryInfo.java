package com.marcosimon.autosurvey.countryinfo;

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

  private String countryName;

  private SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy");

  private String currencyRef;

  @OneToMany(mappedBy = "countryInfo")
  private List<MsfOrgInfo> orgInfoList;

  @OneToOne
  @PrimaryKeyJoinColumn
  private CurrencyInfo currencyInfo;


  public CountryInfo(String countryName, SimpleDateFormat date, String currencyRef) {
    this.countryName = countryName;
    this.date = date;
    this.currencyRef = currencyRef;
  }
}
