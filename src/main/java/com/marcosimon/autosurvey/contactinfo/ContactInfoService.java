package com.marcosimon.autosurvey.contactinfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.exception.CustomException;
import com.marcosimon.autosurvey.models.NewContactInfoDTO;
import com.marcosimon.autosurvey.msforginfo.IMsfOrgInfoDbRepository;
import com.marcosimon.autosurvey.msforginfo.MsfOrgInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.marcosimon.autosurvey.constants.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class ContactInfoService {

  private final IContactInfoDbRepository contactInfoDbRepository;
  private final IMsfOrgInfoDbRepository msfOrgInfoDbRepository;

  public List<ContactInfo> getAllContacts() {
    return contactInfoDbRepository.findAll();
  }

  public ContactInfo getContactById(String id) {
    return contactInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(CONTACT_INFO_NOT_FOUND));
  }
  @Transactional
  public synchronized ContactInfo addContactInfo(String orgName, CountryInfo countryInfo, NewContactInfoDTO newContactInfoDTO) {

    MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
            .findByOrgNameAndCountryInfo(orgName, countryInfo)
            .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

    contactInfoDbRepository
            .findById(msfOrgInfo.getOrgId()).ifPresent( info -> {
              throw new CustomException(ALREADY_SAVED_CONTACT_INFO);
            });

    return contactInfoDbRepository
            .save(new ContactInfo(msfOrgInfo.getOrgId(),
                    newContactInfoDTO.contactPerson(),
                    newContactInfoDTO.contactPhone(),
                    newContactInfoDTO.contactEmail(),
                    newContactInfoDTO.contactJobTile()));
  }

  @Transactional
  public synchronized ContactInfo updateContactInfo(String id, NewContactInfoDTO updateContactInfoDTO) {
    ContactInfo storedContactInfo = contactInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(CONTACT_INFO_NOT_FOUND));

    if (updateContactInfoDTO.contactPerson() != null && !updateContactInfoDTO.contactPerson().isEmpty()) {
      storedContactInfo.setContactPerson(updateContactInfoDTO.contactPerson());
    }
    if (updateContactInfoDTO.contactPhone() != null && !updateContactInfoDTO.contactPhone().isEmpty()) {
      storedContactInfo.setContactPhone(updateContactInfoDTO.contactPhone());
    }
    if (updateContactInfoDTO.contactEmail() != null && !updateContactInfoDTO.contactEmail().isEmpty()) {
      storedContactInfo.setContactEmail(updateContactInfoDTO.contactEmail());
    }
    if (updateContactInfoDTO.contactJobTile() != null && !updateContactInfoDTO.contactJobTile().isEmpty()) {
      storedContactInfo.setContactJobTitle(updateContactInfoDTO.contactJobTile());
    }

    return contactInfoDbRepository.save(storedContactInfo);

  }

  public void deleteContactInfo(String id) {
    contactInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(CONTACT_INFO_NOT_FOUND));
    contactInfoDbRepository.deleteById(id);
  }

}
