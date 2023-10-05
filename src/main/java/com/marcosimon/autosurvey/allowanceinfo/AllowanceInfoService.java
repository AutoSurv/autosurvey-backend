package com.marcosimon.autosurvey.allowanceinfo;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AllowanceInfoService {

    private final IAllowanceInfoDbRepository allowanceInfoDbRepository;


}
