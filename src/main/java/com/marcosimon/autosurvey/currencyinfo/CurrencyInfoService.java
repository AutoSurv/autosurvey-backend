package com.marcosimon.autosurvey.currencyinfo;

import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewCurrencyInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

import static com.marcosimon.autosurvey.constants.ErrorCode.CURRENCY_INFO_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CurrencyInfoService {

    private final ICurrencyInfoDbRepository currencyInfoDbRepository;

    public List<CurrencyInfo> getAllCurrencyInfo() { return currencyInfoDbRepository.findAll(); }

    public CurrencyInfo getCurrencyInfoById(String id) {
        return currencyInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));
    }

    @Transactional
    public synchronized CurrencyInfo updatedCurrencyInfo(String id, NewCurrencyInfoDTO updateCurrencyInfoDTO) {
        CurrencyInfo storedCurrencyInfo = currencyInfoDbRepository
                .findById(id)
                .orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));

        if (!Objects.equals(updateCurrencyInfoDTO.currency(), "")) {
            storedCurrencyInfo.setCurrency(updateCurrencyInfoDTO.currency());
        }

        if (updateCurrencyInfoDTO.exchangeRate() >= 0) {
            storedCurrencyInfo.setExchangeRate(updateCurrencyInfoDTO.exchangeRate());
        }

        return currencyInfoDbRepository.save(storedCurrencyInfo);
    }

    public void deleteCurrencyInfo(String id) {
        currencyInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(CURRENCY_INFO_NOT_FOUND));
        currencyInfoDbRepository.deleteById(id);
    }

}
