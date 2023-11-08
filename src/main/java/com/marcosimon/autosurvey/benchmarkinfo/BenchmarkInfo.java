package com.marcosimon.autosurvey.benchmarkinfo;

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
@Table(name = "Benchmark_Info")
public class BenchmarkInfo {
  @Id
  //@GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "BM_Info_ID")
  private String benchmarkInfoId;

  @Column(name = "Country_Name")
  private String countryName;

  @Column(name = "Country_ISO")
  private String countryIso;

  @Column(name = "Continent")
  private String continent;

  @Column(name = "BM_Start_Date")
  private String benchmarkStartDate;

  @Column(name = "BM_End_Date")
  private String benchmarkEndDate;

  @Column(name = "BM_Reference_Currency")
  private String benchmarkReferenceCurrency;

  @Column(name = "BM_Selected_DB_Working_Hours")
  private Float benchmarkSelectedDbWorkingHours;

  @Column(name = "Average_Working_Hours")
  private Float averageWorkingHours;

  //@JsonManagedReference
  @JsonIgnore
  @OneToMany(mappedBy = "countryInfo", cascade = CascadeType.REMOVE, orphanRemoval = true)
  private List<MsfOrgInfo> orgInfoList;

  public BenchmarkInfo(String benchmarkInfoId, String countryName, String countryIso, String continent, String benchmarkStartDate,
                        String benchmarkEndDate, String benchmarkReferenceCurrency, Float benchmarkSelectedDbWorkingHours, Float averageWorkingHours) {
    this.benchmarkInfoId = benchmarkInfoId;
    this.countryName = countryName;
    this.countryIso = countryIso;
    this.continent = continent;
    this.benchmarkStartDate = benchmarkStartDate;
    this.benchmarkEndDate = benchmarkEndDate;
    this.benchmarkReferenceCurrency = benchmarkReferenceCurrency;
    this.benchmarkSelectedDbWorkingHours = benchmarkSelectedDbWorkingHours;
    this.averageWorkingHours = averageWorkingHours;
  }
}
