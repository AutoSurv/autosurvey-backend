package com.marcosimon.autosurvey.allowancepercentinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAllowancePercentInfoDbRepository extends JpaRepository<AllowancePercentInfo, String> {
}
