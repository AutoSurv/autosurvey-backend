package com.marcosimon.autosurvey.functioninfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.functionsalaryinfo.FunctionSalaryInfo;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "function_info")
public class FunctionInfo {
  @Id
  @Column(name = "function_info_id")
  private String functionInfoId;

  @Column(name = "msf_entity")
  private String msfEntity;

  @Column(name = "msf_professional_group")
  private String msfProfessionalGroup;

  @Column(name = "msf_level")
  private String msfLevel;

  @Column(name = "irffg_level")
  private String irffgLevel;

  @Column(name = "msf_JobFamily_Dep")
  private String msfJobFamilyDep;

  @Column(name = "msf_function")
  private String msfFunction;

  //@JsonManagedReference
  @JsonIgnore
  @OneToMany(mappedBy = "functionSalaryInfoId")
  private List<FunctionSalaryInfo> functionSalaryInfoList;

  public FunctionInfo(String functionInfoId, String msfEntity, String msfProfessionalGroup, String msfLevel, String irffgLevel, String msfJobFamilyDep, String msfFunction) {
    this.functionInfoId = functionInfoId;
    this.msfEntity = msfEntity;
    this.msfProfessionalGroup = msfProfessionalGroup;
    this.msfLevel = msfLevel;
    this.irffgLevel = irffgLevel;
    this.msfJobFamilyDep = msfJobFamilyDep;
    this.msfFunction = msfFunction;
  }
}
