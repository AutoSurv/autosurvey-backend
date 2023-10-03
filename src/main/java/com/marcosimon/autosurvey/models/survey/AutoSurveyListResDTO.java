package com.marcosimon.autosurvey.models.survey;

import com.marcosimon.autosurvey.autosurvey.AutoSurvey;

import java.util.List;

public class AutoSurveyListResDTO {
    List<AutoSurvey> surveys;
    int pageSize;
    int totalPages;
    int numberOfElements;
    boolean first;
    boolean last;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }


    public List<AutoSurvey> getSurveys() {
        return surveys;
    }

    public void setSurveys(List<AutoSurvey> surveys) {
        this.surveys = surveys;
    }
}



