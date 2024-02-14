/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ems_client_levi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author HP
 */
public class Budget_trans {

  @SerializedName("id")
    @Expose
    private int ID;

  @SerializedName("item")
    @Expose
    private String ITEM;
  
    @SerializedName("debit")
    @Expose
    private String DEBIT;
    
    @SerializedName("credit")
    @Expose
    private String CREDIT;
    
    @SerializedName("sold")
    @Expose
    private String SOLD;
      
    @SerializedName("projet")
    @Expose
    private String PROJET;
      
    @SerializedName("code")
    @Expose
    private String CODE;
    
    @SerializedName("num")
    @Expose
    private String NUM;
   
    @SerializedName("cat")
    @Expose
    private String CAT;
     
    @SerializedName("dates")
    @Expose
    private String DATES;
        
    @SerializedName("mois")
    @Expose
    private String MOIS;
        
    @SerializedName("annee")
    @Expose
    private String ANNEE;
    
     @SerializedName("sub_cat")
    @Expose
    private String SUB_CAT;
     
      @SerializedName("code_cat")
    @Expose
    private String CODE_CAT;
      
       @SerializedName("code_sub_cat")
    @Expose
    private String CODE_SUB_CAT;
       
        @SerializedName("items")
    @Expose
    private String ITEMS;
        
    @SerializedName("batch")
    @Expose
    private String BATCH;
    
        @SerializedName("online")
    @Expose
    private String ONLINE;
        
        @SerializedName("id_user")
    @Expose
    private String ID_USER;
        
        @SerializedName("organization")
    @Expose
    private String ORGANIZATION;

    public Budget_trans(int ID, String ITEM, String DEBIT, String CREDIT, String SOLD, String PROJET, String CODE, String NUM, String CAT, String DATES, String MOIS, String ANNEE, String SUB_CAT, String CODE_CAT, String CODE_SUB_CAT, String ITEMS, String BATCH, String ONLINE, String ID_USER, String ORGANIZATION) {
        this.ID = ID;
        this.ITEM = ITEM;
        this.DEBIT = DEBIT;
        this.CREDIT = CREDIT;
        this.SOLD = SOLD;
        this.PROJET = PROJET;
        this.CODE = CODE;
        this.NUM = NUM;
        this.CAT = CAT;
        this.DATES = DATES;
        this.MOIS = MOIS;
        this.ANNEE = ANNEE;
        this.SUB_CAT = SUB_CAT;
        this.CODE_CAT = CODE_CAT;
        this.CODE_SUB_CAT = CODE_SUB_CAT;
        this.ITEMS = ITEMS;
        this.BATCH = BATCH;
        this.ONLINE = ONLINE;
        this.ID_USER = ID_USER;
        this.ORGANIZATION = ORGANIZATION;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getITEM() {
        return ITEM;
    }

    public void setITEM(String ITEM) {
        this.ITEM = ITEM;
    }

    public String getDEBIT() {
        return DEBIT;
    }

    public void setDEBIT(String DEBIT) {
        this.DEBIT = DEBIT;
    }

    public String getCREDIT() {
        return CREDIT;
    }

    public void setCREDIT(String CREDIT) {
        this.CREDIT = CREDIT;
    }

    public String getSOLD() {
        return SOLD;
    }

    public void setSOLD(String SOLD) {
        this.SOLD = SOLD;
    }

    public String getPROJET() {
        return PROJET;
    }

    public void setPROJET(String PROJET) {
        this.PROJET = PROJET;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getNUM() {
        return NUM;
    }

    public void setNUM(String NUM) {
        this.NUM = NUM;
    }

    public String getCAT() {
        return CAT;
    }

    public void setCAT(String CAT) {
        this.CAT = CAT;
    }

    public String getDATES() {
        return DATES;
    }

    public void setDATES(String DATES) {
        this.DATES = DATES;
    }

    public String getMOIS() {
        return MOIS;
    }

    public void setMOIS(String MOIS) {
        this.MOIS = MOIS;
    }

    public String getANNEE() {
        return ANNEE;
    }

    public void setANNEE(String ANNEE) {
        this.ANNEE = ANNEE;
    }

    public String getSUB_CAT() {
        return SUB_CAT;
    }

    public void setSUB_CAT(String SUB_CAT) {
        this.SUB_CAT = SUB_CAT;
    }

    public String getCODE_CAT() {
        return CODE_CAT;
    }

    public void setCODE_CAT(String CODE_CAT) {
        this.CODE_CAT = CODE_CAT;
    }

    public String getCODE_SUB_CAT() {
        return CODE_SUB_CAT;
    }

    public void setCODE_SUB_CAT(String CODE_SUB_CAT) {
        this.CODE_SUB_CAT = CODE_SUB_CAT;
    }

    public String getITEMS() {
        return ITEMS;
    }

    public void setITEMS(String ITEMS) {
        this.ITEMS = ITEMS;
    }

    public String getBATCH() {
        return BATCH;
    }

    public void setBATCH(String BATCH) {
        this.BATCH = BATCH;
    }

    public String getONLINE() {
        return ONLINE;
    }

    public void setONLINE(String ONLINE) {
        this.ONLINE = ONLINE;
    }

    public String getID_USER() {
        return ID_USER;
    }

    public void setID_USER(String ID_USER) {
        this.ID_USER = ID_USER;
    }

    public String getORGANIZATION() {
        return ORGANIZATION;
    }

    public void setORGANIZATION(String ORGANIZATION) {
        this.ORGANIZATION = ORGANIZATION;
    }

    @Override
    public String toString() {
        return "Budget_trans{" + "ID=" + ID + ", ITEM=" + ITEM + ", DEBIT=" + DEBIT + ", CREDIT=" + CREDIT + ", SOLD=" + SOLD + ", PROJET=" + PROJET + ", CODE=" + CODE + ", NUM=" + NUM + ", CAT=" + CAT + ", DATES=" + DATES + ", MOIS=" + MOIS + ", ANNEE=" + ANNEE + ", SUB_CAT=" + SUB_CAT + ", CODE_CAT=" + CODE_CAT + ", CODE_SUB_CAT=" + CODE_SUB_CAT + ", ITEMS=" + ITEMS + ", BATCH=" + BATCH + ", ONLINE=" + ONLINE + ", ID_USER=" + ID_USER + ", ORGANIZATION=" + ORGANIZATION + '}';
    }
    
}
