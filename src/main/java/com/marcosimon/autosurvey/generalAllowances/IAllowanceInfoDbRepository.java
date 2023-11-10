package com.marcosimon.autosurvey.generalAllowances;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAllowanceInfoDbRepository extends JpaRepository<GeneralAllowances, String> {
}
