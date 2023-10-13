package com.marcosimon.autosurvey.functionsalaryinfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.functioninfo.FunctionInfo;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "function_salary_info")
public class FunctionSalaryInfo {
  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  @Column(name = "function_salary_info_id")
  private String functionSalaryInfoId;

  @Column(name = "function_custom_name")
  private String functionCustomName;

  @Column(name = "basic_salary")
  private Integer basicSalary;

  private Integer tgc;

  @Column(name = "monthly_allowance")
  private Integer monthlyAllowance;

  @JsonBackReference
  @JsonIgnore
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "org_id", nullable = true)
  private MsfOrgInfo msfOrgInfo;

  @ManyToOne
  @JoinColumn(name = "function_info_id", nullable = false)
  private FunctionInfo functionInfo;

  public FunctionSalaryInfo(String functionCustomName, Integer basicSalary, Integer tgc, Integer monthlyAllowance) {
    this.functionCustomName = functionCustomName;
    this.basicSalary = basicSalary;
    this.tgc = tgc;
    this.monthlyAllowance = monthlyAllowance;
  }

  public FunctionSalaryInfo(String functionCustomName, Integer basicSalary, Integer tgc, Integer monthlyAllowance, MsfOrgInfo msfOrgInfo, FunctionInfo functionInfo) {
    this.functionCustomName = functionCustomName;
    this.basicSalary = basicSalary;
    this.tgc = tgc;
    this.monthlyAllowance = monthlyAllowance;
    this.msfOrgInfo = msfOrgInfo;
    this.functionInfo = functionInfo;
  }
}
