package com.marcosimon.autosurvey.contactinfo;

import com.marcosimon.autosurvey.countryinfo.CountryInfo;
import com.marcosimon.autosurvey.countryinfo.ICountryInfoDbRepository;
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
  private final ICountryInfoDbRepository countryInfoDbRepository;

  public List<ContactInfo> getAllContacts() {
    return contactInfoDbRepository.findAll();
  }

  public ContactInfo getContactById(Long id) {
    return contactInfoDbRepository
            .findById(id)
            .orElseThrow(() -> new CustomException(CONTACT_INFO_NOT_FOUND));
  }
  @Transactional
  public synchronized ContactInfo addContactInfo(NewContactInfoDTO newContactInfoDTO) {
    CountryInfo countryInfo = countryInfoDbRepository.findByNameAndYear(newContactInfoDTO.countryName(), newContactInfoDTO.year())
            .orElseThrow(() -> new CustomException(COUNTRY_INFO_NOT_FOUND));
    MsfOrgInfo msfOrgInfo = msfOrgInfoDbRepository
            .findByOrgNameAndCountryInfo(newContactInfoDTO.orgName(), countryInfo)
            .orElseThrow(() -> new CustomException(ORGANIZATION_NOT_FOUND));

    contactInfoDbRepository
            .findById(msfOrgInfo.getOrgId()).ifPresent( info -> {
              throw new CustomException(ALREADY_SAVED_CONTACT_INFO);
            });

    ContactInfo contactInfo = new ContactInfo();
    contactInfo.setMsfOrgInfo(msfOrgInfo);
    contactInfo.setContactPerson(newContactInfoDTO.contactPerson());
    contactInfo.setContactPhone(newContactInfoDTO.contactPhone());
    contactInfo.setContactEmail(newContactInfoDTO.contactEmail());
    contactInfo.setContactJobTitle(newContactInfoDTO.contactJobTitle());

    msfOrgInfo.setContact(contactInfo);
    msfOrgInfoDbRepository.save(msfOrgInfo);

    return contactInfoDbRepository.save(contactInfo);
  }

  @Transactional
  public synchronized ContactInfo updateContactInfo(Long id, NewContactInfoDTO updateContactInfoDTO) {
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

    if (updateContactInfoDTO.contactJobTitle() != null && !updateContactInfoDTO.contactJobTitle().isEmpty()) {
      System.out.println(storedContactInfo.getContactJobTitle());
      storedContactInfo.setContactJobTitle(updateContactInfoDTO.contactJobTitle());
      System.out.println(storedContactInfo.getContactJobTitle());
    }

    return contactInfoDbRepository.save(storedContactInfo);

  }

  public void deleteContactInfo(Long id) {
    contactInfoDbRepository.findById(id).orElseThrow(() -> new CustomException(CONTACT_INFO_NOT_FOUND));
    contactInfoDbRepository.deleteById(id);
  }

}
