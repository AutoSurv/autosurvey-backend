package com.marcosimon.autosurvey.contactinfo;

import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "contact_info")
public class ContactInfo implements Serializable {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String contactInfoId;
  @MapsId
  @OneToOne(mappedBy = "contact")
  @JoinColumn(name = "contactInfoId")
  private MsfOrgInfo msfOrgInfo;
  @Column(name = "contact_person")
  private String contactPerson;
  @Column(name = "contact_phone")
  private String contactPhone;
  @Column(name = "contact_email")
  private String contactEmail;
  @Column(name = "contact_job_title")
  private String contactJobTitle;

  public ContactInfo(String contactPerson, String contactPhone, String contactEmail, String contactJobTitle) {
    this.contactPerson = contactPerson;
    this.contactPhone = contactPhone;
    this.contactEmail = contactEmail;
    this.contactJobTitle = contactJobTitle;
  }
}
