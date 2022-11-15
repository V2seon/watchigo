package com.example.watchigo.common;

import lombok.Getter;

@Getter
public class Pagination {
    boolean isPrevSection;
    boolean isNextSection;
    int lastBtnIndex;
    int firstBtnIndex;
    int totalPages;
    int page;

    public Pagination(int totalPages, int page) {
        this.totalPages = totalPages -= 1;
        this.page = page;

        int thisSectionValue = (page / 10);
        int totalSection = (totalPages / 10);


        if(thisSectionValue <= 0){
            isPrevSection = false;
        }else{
            isPrevSection = true;
        }


        if(thisSectionValue >= totalSection){
            isNextSection = false;
            lastBtnIndex = (thisSectionValue * 10) + (totalPages % 10);
        }else{
            isNextSection = true;
            lastBtnIndex = (thisSectionValue * 10) + 9;
        }

        if(lastBtnIndex < 0){
            lastBtnIndex = 0;
        }

        firstBtnIndex = thisSectionValue * 10;



    }
}
