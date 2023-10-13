package com.marcosimon.autosurvey.functioninfo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcosimon.autosurvey.functionsalaryinfo.FunctionSalaryInfo;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

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
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "function_info_id")
  private Long functionInfoId;

  private Integer level;

  @Column(name = "function_name")
  private String functionName;

  //@JsonManagedReference
  @JsonIgnore
  @OneToMany(mappedBy = "functionSalaryInfoId")
  private List<FunctionSalaryInfo> functionSalaryInfoList;

  public FunctionInfo(Integer level, String functionName) {
    this.level = level;
    this.functionName = functionName;
  }
}
