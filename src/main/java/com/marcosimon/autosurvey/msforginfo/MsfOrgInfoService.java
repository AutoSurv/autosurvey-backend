package com.marcosimon.autosurvey.msforginfo;

import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MsfOrgInfoService {

  private final IMsfOrgInfoDbRepository iMsfOrgInfoDbRepository;

  public List<MsfOrgInfo> getAllMsfOrgInfo() {
    return iMsfOrgInfoDbRepository.findAll();
  }

  public MsfOrgInfo createMsfOrgInfo(NewMsfOrgInfoDTO newMsfOrgInfoDTO) {

    return null;
  }
}
