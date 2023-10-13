package com.marcosimon.autosurvey.currencyinfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICurrencyInfoDbRepository extends JpaRepository<CurrencyInfo, Long> {
}
