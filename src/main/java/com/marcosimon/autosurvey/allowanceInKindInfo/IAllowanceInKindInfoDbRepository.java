package com.marcosimon.autosurvey.allowanceInKindInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAllowanceInKindInfoDbRepository extends JpaRepository<AllowanceInKindInfo, Long> {
}
