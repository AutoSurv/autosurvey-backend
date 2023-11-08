package com.marcosimon.autosurvey.benchmarkinfo;

import com.marcosimon.autosurvey.allowanceinfo.IAllowanceInfoDbRepository;
import com.marcosimon.autosurvey.allowancepercentinfo.IAllowancePercentInfoDbRepository;
import com.marcosimon.autosurvey.contactinfo.IContactInfoDbRepository;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.functionsalaryinfo.IFunctionSalaryInfoDbRepository;
import com.marcosimon.autosurvey.models.NewBenchmarkInfoDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@Transactional
@RequiredArgsConstructor
public class BenchmarkInfoService {

    private final IBenchmarkInfoDbRepository benchmarkInfoDbRepository;
    private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;
    private final IContactInfoDbRepository contactInfoDbRepository;
    private final IAllowanceInfoDbRepository allowanceInfoDbRepository;
    private final IAllowancePercentInfoDbRepository allowancePercentInfoDbRepository;
    private final IFunctionSalaryInfoDbRepository functionSalaryInfoDbRepository;

    public List<BenchmarkInfo> getAllBenchmarkInfo() { return benchmarkInfoDbRepository.findAll(); }

    public BenchmarkInfo getBenchmarkInfoById(String id) {
        return benchmarkInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(BENCHMARK_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized BenchmarkInfo addBenchmarkInfo(NewBenchmarkInfoDTO newBenchmarkInfoDTO) {
        benchmarkInfoDbRepository
                .findById(newBenchmarkInfoDTO.benchmarkInfoId())
                        .ifPresent( info -> {
                            throw new CustomException(ALREADY_SAVED_BENCHMARK_INFO);
                        });

        return benchmarkInfoDbRepository.save(new BenchmarkInfo(newBenchmarkInfoDTO.benchmarkInfoId(), newBenchmarkInfoDTO.countryName(),
                newBenchmarkInfoDTO.countryIso(), newBenchmarkInfoDTO.continent(), newBenchmarkInfoDTO.benchmarkStartDate(),
                newBenchmarkInfoDTO.benchmarkEndDate(), newBenchmarkInfoDTO.benchmarkReferenceCurrency(),
                newBenchmarkInfoDTO.benchmarkSelectedDbWorkingHours(), newBenchmarkInfoDTO.averageWorkingHours()));
    }
    @Transactional
    public synchronized BenchmarkInfo updateBenchmarkInfo(String id, NewBenchmarkInfoDTO updateBenchmarkInfo) {
        BenchmarkInfo storedBenchmarkInfo = benchmarkInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(BENCHMARK_INFO_NOT_FOUND));

        if (updateBenchmarkInfo.countryName() != null && !updateBenchmarkInfo.countryName().isEmpty()) {
            storedBenchmarkInfo.setCountryName(updateBenchmarkInfo.countryName());
        }

        if (updateBenchmarkInfo.countryIso() != null && !updateBenchmarkInfo.countryIso().isEmpty()) {
            storedBenchmarkInfo.setCountryIso(updateBenchmarkInfo.countryIso());
        }

        if (updateBenchmarkInfo.continent() != null && !updateBenchmarkInfo.continent().isEmpty()) {
            storedBenchmarkInfo.setContinent(updateBenchmarkInfo.continent());
        }

        if (updateBenchmarkInfo.benchmarkStartDate() != null && !updateBenchmarkInfo.benchmarkStartDate().isEmpty()) {
            storedBenchmarkInfo.setBenchmarkStartDate(updateBenchmarkInfo.benchmarkStartDate());
        }

        if (updateBenchmarkInfo.benchmarkEndDate() != null && !updateBenchmarkInfo.benchmarkEndDate().isEmpty()) {
            storedBenchmarkInfo.setBenchmarkEndDate(updateBenchmarkInfo.benchmarkEndDate());
        }

        if (updateBenchmarkInfo.benchmarkReferenceCurrency() != null && !updateBenchmarkInfo.benchmarkReferenceCurrency().isEmpty()) {
            storedBenchmarkInfo.setBenchmarkReferenceCurrency(updateBenchmarkInfo.benchmarkReferenceCurrency());
        }

        if (updateBenchmarkInfo.benchmarkSelectedDbWorkingHours() != null && updateBenchmarkInfo.benchmarkSelectedDbWorkingHours() >= 0) {
            storedBenchmarkInfo.setBenchmarkSelectedDbWorkingHours(updateBenchmarkInfo.benchmarkSelectedDbWorkingHours());
        }

        if (updateBenchmarkInfo.averageWorkingHours() != null && updateBenchmarkInfo.averageWorkingHours() >= 0) {
            storedBenchmarkInfo.setAverageWorkingHours(updateBenchmarkInfo.averageWorkingHours());
        }

        return benchmarkInfoDbRepository.save(storedBenchmarkInfo);

    }

    public void deleteBenchmarkInfo(String id) {
        benchmarkInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(BENCHMARK_INFO_NOT_FOUND));
        msfOrgInfoDbRepository.findAllByBenchmarkInfoId(id).forEach(o -> {
            contactInfoDbRepository.findById(o.getOrgId()).ifPresent(contactInfoDbRepository::delete);
            allowanceInfoDbRepository.findById(o.getOrgId()).ifPresent(allowanceInfoDbRepository::delete);
            allowancePercentInfoDbRepository.findById(o.getOrgId()).ifPresent(allowancePercentInfoDbRepository::delete);
            functionSalaryInfoDbRepository.findAllByOrg(o).forEach(functionSalaryInfoDbRepository::delete);
            msfOrgInfoDbRepository.findById(o.getOrgId()).ifPresent(msfOrgInfoDbRepository::delete);
        });
        benchmarkInfoDbRepository.deleteById(id);
    }

}
