package com.marcosimon.autosurvey.contactinfo;

import com.marcosimon.autosurvey.constants.ErrorCode;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewContactInfoDTO;
import com.marcosimon.autosurvey.models.NewMsfOrgInfoDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.marcosimon.autosurvey.constants.ErrorCode.CONTACT_INFO_NOT_FOUND;
import static com.marcosimon.autosurvey.constants.ErrorCode.ALREADY_SAVED_CONTACT_INFO;

@Service
@RequiredArgsConstructor
public class ContactInfoService {

  private final IContactInfoDbRepository contactInfoDbRepository;
  private final IMsfOrgInfoDbRepository iMsfOrgInfoDbRepository;

  public List<ContactInfo> getAllContacts() {
    return contactInfoDbRepository.findAll();
  }

  public ContactInfo getContactById(String id) {
    return contactInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(CONTACT_INFO_NOT_FOUND));
  }


//  public ContactInfo addContactInfo(NewContactInfoDTO newContactInfoDTO, NewMsfOrgInfoDTO newMsfOrgInfoDTO) {
//
//    MsfOrgInfo msfOrgInfo = iMsfOrgInfoDbRepository.findbyOr
//  }





}
