package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.contactinfo.ContactInfo;
import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "org_info")
public class MsfOrgInfo {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "org_id")
  private String orgId;
  @Column(name = "org_full_name")
  private String orgFullName;
  @Column(name = "org_name")
  private String orgName;
  @Column(name = "working_hours")
  private Integer workingHours;
  @Column(name = "thirteenth_salary ")
  private Integer thirteenthSalary;
  //@ManyToOne
  @OneToOne
  @PrimaryKeyJoinColumn
  private ContactInfo contact; //Country countryId
//  @Column(name = "contact_person")
//  private String contactInfo; //ContactInfo contactId

  @Column(name = "currency_in_use")
  private String currencyInUse;

  public MsfOrgInfo(String orgName, Integer workingHours, Integer thirteenthSalary, String currencyInUse) {
    this.orgFullName = orgName;
    this.workingHours = workingHours;
    this.thirteenthSalary = thirteenthSalary;
    this.currencyInUse = currencyInUse;
  }


}
