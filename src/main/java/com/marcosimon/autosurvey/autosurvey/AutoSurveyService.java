package com.marcosimon.autosurvey.autosurvey;

import com.marcosimon.autosurvey.models.AutoSurveyDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class AutoSurveyService {

  @Autowired
  AutoSurveyRepository autoSurveyRepository;


  public AutoSurveyDTO convertToDto (AutoSurvey autoSurvey) {
    AutoSurveyDTO autoSurveyDTO = new AutoSurveyDTO(
            autoSurvey.getId(),
            autoSurvey.getCountry(),
            autoSurvey.getRent(),
            autoSurvey.getUtilities(),
            autoSurvey.getFood(),
            autoSurvey.getBasicItems(),
            autoSurvey.getTransportation(),
            autoSurvey.getEducationTotal(),
            autoSurvey.getEducationSupplies(),
            autoSurvey.getEducationFee(),
            autoSurvey.isEducationType(),
            autoSurvey.getAccommodationType(),
            autoSurvey.getProfession(),
            autoSurvey.getLocationGiven(),
            autoSurvey.getLocationClustered(),
            autoSurvey.getNumResidents(),
            autoSurvey.getNumIncomes(),
            autoSurvey.getNumFullIncomes(),
            autoSurvey.getNumChildren(),
            autoSurvey.getTotalIncome(),
            autoSurvey.getComments()
    );

    return autoSurveyDTO;
  }
  public AutoSurvey convertFromDto (AutoSurveyDTO dto) {
    AutoSurvey autoSurvey = new AutoSurvey(
            dto.id(),
            dto.country(),
            dto.rent(),
            dto.utilities(),
            dto.food(),
            dto.basicItems(),
            dto.transportation(),
            dto.educationTotal(),
            dto.educationSupplies(),
            dto.educationFee(),
            dto.educationType(),
            dto.accommodationType(),
            dto.profession(),
            dto.locationGiven(),
            dto.locationClustered(),
            dto.numResidents(),
            dto.numIncomes(),
            dto.numFullIncomes(),
            dto.numChildren(),
            dto.totalIncome(),
            dto.comments()
    );
    return  autoSurvey;
  }


  public List<AutoSurveyDTO> convertToDtoList (List<AutoSurvey> autoSurveyList) {
    List<AutoSurveyDTO> autoSurveyDTOList = new ArrayList<>();

    for (AutoSurvey survey : autoSurveyList) {
      autoSurveyDTOList.add(convertToDto(survey));
    }

    return autoSurveyDTOList;
  }


  public List<AutoSurveyDTO> getAllSurvey() {
    List<AutoSurvey> autoSurveyList = autoSurveyRepository.listSurveys();

    return convertToDtoList(autoSurveyList);
  }


  public AutoSurveyDTO getSurveyById(String id) {
    AutoSurvey survey = autoSurveyRepository.getById(id);
    if (survey == null) {
      return null;
    }

    AutoSurveyDTO autoSurveyDTO = convertToDto(survey);

    return autoSurveyDTO;
  }

  public AutoSurvey saveSurvey(AutoSurveyDTO surveyDTO) {

    return autoSurveyRepository.saveSurvey(convertFromDto(surveyDTO));

  }

  public void saveSurveys(List<AutoSurveyDTO> dtos) {
    autoSurveyRepository.saveSurveys(dtos.stream().map(this::convertFromDto).toList());
  }

  public AutoSurveyDTO updateSurvey(String id, AutoSurveyDTO autoSurveyDTO) {
    AutoSurveyDTO surveyDto = getSurveyById(id);
    if (surveyDto == null) return null;

    AutoSurvey surveyToUpdate = convertFromDto(surveyDto);

    if (autoSurveyDTO.country() != null) {
     surveyToUpdate.setCountry(autoSurveyDTO.country());
    }

    if (autoSurveyDTO.rent() > -1) {
      surveyToUpdate.setRent(autoSurveyDTO.rent());
    }

    if (autoSurveyDTO.utilities() > 0) {
      surveyToUpdate.setUtilities(autoSurveyDTO.utilities());
    }

    if (autoSurveyDTO.food() > 0) {
      surveyToUpdate.setFood(autoSurveyDTO.food());
    }

    if (autoSurveyDTO.basicItems() > 0) {
      surveyToUpdate.setBasicItems(autoSurveyDTO.basicItems());
    }

    if (autoSurveyDTO.transportation() > -1) {
      surveyToUpdate.setTransportation(surveyDto.transportation());
    }

    if (autoSurveyDTO.educationTotal() > -1) {
      surveyToUpdate.setEducationTotal(autoSurveyDTO.educationTotal());
    }

    if (autoSurveyDTO.educationSupplies() > -1) {
      surveyToUpdate.setEducationSupplies(autoSurveyDTO.educationSupplies());
    }

    if (autoSurveyDTO.educationFee() > -1) {
      surveyToUpdate.setEducationFee(surveyDto.educationFee());
    }

    if (autoSurveyDTO.educationType() != null) {
      surveyToUpdate.setEducationType(autoSurveyDTO.educationType());
    }

    if (autoSurveyDTO.accommodationType() != null) {
      surveyToUpdate.setAccommodationType(autoSurveyDTO.accommodationType());
    }

    if (autoSurveyDTO.profession() != null) {
      surveyToUpdate.setProfession(autoSurveyDTO.profession());
    }

    if (autoSurveyDTO.locationGiven() != null) {
      surveyToUpdate.setLocationGiven(autoSurveyDTO.locationGiven());
    }

    if (autoSurveyDTO.locationClustered() != null) {
      surveyToUpdate.setLocationClustered(autoSurveyDTO.locationClustered());
    }

    if (autoSurveyDTO.numResidents() > 0) {
      surveyToUpdate.setNumResidents(autoSurveyDTO.numResidents());
    }

    if (autoSurveyDTO.numIncomes() > -1) {
      surveyToUpdate.setNumIncomes(autoSurveyDTO.numIncomes());
    }

    if (autoSurveyDTO.numFullIncomes() > -1) {
      surveyToUpdate.setNumFullIncomes(autoSurveyDTO.numFullIncomes());
    }

    if (autoSurveyDTO.numChildren() > -1) {
      surveyToUpdate.setNumChildren(autoSurveyDTO.numChildren());
    }

    if (autoSurveyDTO.totalIncome() > -1) {
      surveyToUpdate.setTotalIncome(autoSurveyDTO.totalIncome());
    }

    if (autoSurveyDTO.comments() != null) {
      surveyToUpdate.setComments(autoSurveyDTO.comments());
    }


    //AutoSurvey surveyToUpdate = saveSurvey(autoSurveyDTO);
    AutoSurveyDTO surveyDTO = convertToDto(surveyToUpdate);
    AutoSurvey updatedSurvey = saveSurvey(surveyDTO);
    return convertToDto(updatedSurvey);

  }

  public void deleteSurvey(String id) {
    autoSurveyRepository.deleteSurvey(id);
  }
}
