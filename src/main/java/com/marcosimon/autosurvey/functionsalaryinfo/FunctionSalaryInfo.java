package com.marcosimon.autosurvey.functionsalaryinfo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.functioninfo.FunctionInfo;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.*;
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
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "function_salary_info_id")
  private Long functionSalaryInfoId;

  @Column(name = "org_function_id")
  private String orgFunctionId;

  @Column(name = "org_function")
  private String orgFunction;

  @Column(name = "basic_salary")
  private Integer basicSalary;

  private Integer tgc;

  @Column(name = "allowance_per_function")
  private Integer allowancePerFunction;

  //@JsonBackReference
  @JsonIgnore
  @ManyToOne
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JoinColumn(name = "org_id", nullable = true)
  private MsfOrgInfo msfOrgInfo;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "function_info_id")
  private FunctionInfo functionInfo;

  public FunctionSalaryInfo(String orgFunctionId, String orgFunction, Integer basicSalary, Integer tgc, Integer allowancePerFunction, MsfOrgInfo msfOrgInfo, FunctionInfo functionInfo) {
    this.orgFunctionId = orgFunctionId;
    this.orgFunction = orgFunction;
    this.basicSalary = basicSalary;
    this.tgc = tgc;
    this.allowancePerFunction = allowancePerFunction;
    this.msfOrgInfo = msfOrgInfo;
    this.functionInfo = functionInfo;
  }
}
