package com.bitc.java501team3.dto.hotPlaceDTO;

import lombok.Data;

@Data
public class ApiDbDTO {
    private int idx;
    private int UCSEQ;
    private String MAINTITLE;
    private String GUGUNNM;
    private double LAT;
    private double LNG;
    private String PLACE;
    private String TITLE;
    private String SUBTITLE;
    private String ADDR1;
    private String ADDR2;
    private String CNTCTTEL;
    private String HOMEPAGEURL;
    private String USAGEDAYWEEKANDTIME;
    private String RPRSNTVMENU;
    private String MAINIMGNORMAL;
    private String MAINIMGTHUMB;
    private String ITEMCNTNTS;

    public int getUCSEQ() {
        return UCSEQ;
    }

    public void setUCSEQ(int UCSEQ) {
        this.UCSEQ = UCSEQ;
    }

    public String getMAINTITLE() {
        return MAINTITLE;
    }

    public void setMAINTITLE(String MAINTITLE) {
        this.MAINTITLE = MAINTITLE;
    }

    public String getGUGUNNM() {
        return GUGUNNM;
    }

    public void setGUGUNNM(String GUGUNNM) {
        this.GUGUNNM = GUGUNNM;
    }

    public double getLAT() {
        return LAT;
    }

    public void setLAT(double LAT) {
        this.LAT = LAT;
    }

    public double getLNG() {
        return LNG;
    }

    public void setLNG(double LNG) {
        this.LNG = LNG;
    }

    public String getPLACE() {
        return PLACE;
    }

    public void setPLACE(String PLACE) {
        this.PLACE = PLACE;
    }

    public String getTITLE() {
        return TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public String getSUBTITLE() {
        return SUBTITLE;
    }

    public void setSUBTITLE(String SUBTITLE) {
        this.SUBTITLE = SUBTITLE;
    }

    public String getADDR1() {
        return ADDR1;
    }

    public void setADDR1(String ADDR1) {
        this.ADDR1 = ADDR1;
    }

    public String getADDR2() {
        return ADDR2;
    }

    public void setADDR2(String ADDR2) {
        this.ADDR2 = ADDR2;
    }

    public String getCNTCTTEL() {
        return CNTCTTEL;
    }

    public void setCNTCTTEL(String CNTCTTEL) {
        this.CNTCTTEL = CNTCTTEL;
    }

    public String getHOMEPAGEURL() {
        return HOMEPAGEURL;
    }

    public void setHOMEPAGEURL(String HOMEPAGEURL) {
        this.HOMEPAGEURL = HOMEPAGEURL;
    }

    public String getUSAGEDAYWEEKANDTIME() {
        return USAGEDAYWEEKANDTIME;
    }

    public void setUSAGEDAYWEEKANDTIME(String USAGEDAYWEEKANDTIME) {
        this.USAGEDAYWEEKANDTIME = USAGEDAYWEEKANDTIME;
    }

    public String getRPRSNTVMENU() {
        return RPRSNTVMENU;
    }

    public void setRPRSNTVMENU(String RPRSNTVMENU) {
        this.RPRSNTVMENU = RPRSNTVMENU;
    }

    public String getMAINIMGNORMAL() {
        return MAINIMGNORMAL;
    }

    public void setMAINIMGNORMAL(String MAINIMGNORMAL) {
        this.MAINIMGNORMAL = MAINIMGNORMAL;
    }

    public String getMAINIMGTHUMB() {
        return MAINIMGTHUMB;
    }

    public void setMAINIMGTHUMB(String MAINIMGTHUMB) {
        this.MAINIMGTHUMB = MAINIMGTHUMB;
    }

    public String getITEMCNTNTS() {
        return ITEMCNTNTS;
    }

    public void setITEMCNTNTS(String ITEMCNTNTS) {
        this.ITEMCNTNTS = ITEMCNTNTS;
    }
}
