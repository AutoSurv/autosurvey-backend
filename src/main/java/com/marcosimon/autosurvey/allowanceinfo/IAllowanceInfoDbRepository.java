package com.marcosimon.autosurvey.allowanceinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAllowanceInfoDbRepository extends JpaRepository<AllowanceInfo, Long> {
}
