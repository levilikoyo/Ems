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
public class Ohada_trans {
     @SerializedName("id")
    @Expose
    private int ID;

    @SerializedName("compt_m")
    @Expose
    private String COMPTE_M;
    
     @SerializedName("compte")
    @Expose
    private String COMPTE;
     
      @SerializedName("code_m")
    @Expose
    private String CODE_M;
      
       @SerializedName("code")
    @Expose
    private String CODE;
       
        @SerializedName("classe")
    @Expose
    private String CLASSE;
        
         @SerializedName("substr")
    @Expose
    private String SUBSTR;
         
  @SerializedName("debit")
    @Expose
    private String DEBIT;
  
  @SerializedName("credit")
    @Expose
    private String CREDIT;
  
    @SerializedName("num")
    @Expose
    private String NUM;
    
      @SerializedName("dates")
    @Expose
    private String DATES;
      
        @SerializedName("buss")
    @Expose
    private String BUSS;
        
    @SerializedName("libelle")
    @Expose
    private String LIBELLE;
    
    @SerializedName("journal")
    @Expose
    private String JOURNAL;
    
    @SerializedName("code1")
    @Expose
    private String CODE1;
    
    @SerializedName("num_facture")
    @Expose
    private String NUM_FACTURE;
    
    @SerializedName("debit_cdf")
    @Expose
    private String DEBIT_CDF;
    
    @SerializedName("credit_cdf")
    @Expose
    private String CREDIT_CDF;
    
     @SerializedName("lb")
    @Expose
    private String LB;
     
      @SerializedName("pay")
    @Expose
    private String PAY;
      
       @SerializedName("device")
    @Expose
    private String DEVICE;
       
        @SerializedName("archive")
    @Expose
    private String ARCHIVE;
        
         @SerializedName("solde")
    @Expose
    private String SOLDE;
         
    @SerializedName("batch_ecriture")
    @Expose
    private String BATCH_ECRITURE;
    
      @SerializedName("online")
    @Expose
    private String ONLINE;
      
        @SerializedName("user_id")
    @Expose
    private String USER_ID;
        
          @SerializedName("organization")
    @Expose
    private String ORGANIZATION;

    public Ohada_trans(int ID, String COMPTE_M, String COMPTE, String CODE_M, String CODE, String CLASSE, String SUBSTR, String DEBIT, String CREDIT, String NUM, String DATES, String BUSS, String LIBELLE, String JOURNAL, String CODE1, String NUM_FACTURE, String DEBIT_CDF, String CREDIT_CDF, String LB, String PAY, String DEVICE, String ARCHIVE, String SOLDE, String BATCH_ECRITURE, String ONLINE, String USER_ID, String ORGANIZATION) {
        this.ID = ID;
        this.COMPTE_M = COMPTE_M;
        this.COMPTE = COMPTE;
        this.CODE_M = CODE_M;
        this.CODE = CODE;
        this.CLASSE = CLASSE;
        this.SUBSTR = SUBSTR;
        this.DEBIT = DEBIT;
        this.CREDIT = CREDIT;
        this.NUM = NUM;
        this.DATES = DATES;
        this.BUSS = BUSS;
        this.LIBELLE = LIBELLE;
        this.JOURNAL = JOURNAL;
        this.CODE1 = CODE1;
        this.NUM_FACTURE = NUM_FACTURE;
        this.DEBIT_CDF = DEBIT_CDF;
        this.CREDIT_CDF = CREDIT_CDF;
        this.LB = LB;
        this.PAY = PAY;
        this.DEVICE = DEVICE;
        this.ARCHIVE = ARCHIVE;
        this.SOLDE = SOLDE;
        this.BATCH_ECRITURE = BATCH_ECRITURE;
        this.ONLINE = ONLINE;
        this.USER_ID = USER_ID;
        this.ORGANIZATION = ORGANIZATION;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCOMPTE_M() {
        return COMPTE_M;
    }

    public void setCOMPTE_M(String COMPTE_M) {
        this.COMPTE_M = COMPTE_M;
    }

    public String getCOMPTE() {
        return COMPTE;
    }

    public void setCOMPTE(String COMPTE) {
        this.COMPTE = COMPTE;
    }

    public String getCODE_M() {
        return CODE_M;
    }

    public void setCODE_M(String CODE_M) {
        this.CODE_M = CODE_M;
    }

    public String getCODE() {
        return CODE;
    }

    public void setCODE(String CODE) {
        this.CODE = CODE;
    }

    public String getCLASSE() {
        return CLASSE;
    }

    public void setCLASSE(String CLASSE) {
        this.CLASSE = CLASSE;
    }

    public String getSUBSTR() {
        return SUBSTR;
    }

    public void setSUBSTR(String SUBSTR) {
        this.SUBSTR = SUBSTR;
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

    public String getNUM() {
        return NUM;
    }

    public void setNUM(String NUM) {
        this.NUM = NUM;
    }

    public String getDATES() {
        return DATES;
    }

    public void setDATES(String DATES) {
        this.DATES = DATES;
    }

    public String getBUSS() {
        return BUSS;
    }

    public void setBUSS(String BUSS) {
        this.BUSS = BUSS;
    }

    public String getLIBELLE() {
        return LIBELLE;
    }

    public void setLIBELLE(String LIBELLE) {
        this.LIBELLE = LIBELLE;
    }

    public String getJOURNAL() {
        return JOURNAL;
    }

    public void setJOURNAL(String JOURNAL) {
        this.JOURNAL = JOURNAL;
    }

    public String getCODE1() {
        return CODE1;
    }

    public void setCODE1(String CODE1) {
        this.CODE1 = CODE1;
    }

    public String getNUM_FACTURE() {
        return NUM_FACTURE;
    }

    public void setNUM_FACTURE(String NUM_FACTURE) {
        this.NUM_FACTURE = NUM_FACTURE;
    }

    public String getDEBIT_CDF() {
        return DEBIT_CDF;
    }

    public void setDEBIT_CDF(String DEBIT_CDF) {
        this.DEBIT_CDF = DEBIT_CDF;
    }

    public String getCREDIT_CDF() {
        return CREDIT_CDF;
    }

    public void setCREDIT_CDF(String CREDIT_CDF) {
        this.CREDIT_CDF = CREDIT_CDF;
    }

    public String getLB() {
        return LB;
    }

    public void setLB(String LB) {
        this.LB = LB;
    }

    public String getPAY() {
        return PAY;
    }

    public void setPAY(String PAY) {
        this.PAY = PAY;
    }

    public String getDEVICE() {
        return DEVICE;
    }

    public void setDEVICE(String DEVICE) {
        this.DEVICE = DEVICE;
    }

    public String getARCHIVE() {
        return ARCHIVE;
    }

    public void setARCHIVE(String ARCHIVE) {
        this.ARCHIVE = ARCHIVE;
    }

    public String getSOLDE() {
        return SOLDE;
    }

    public void setSOLDE(String SOLDE) {
        this.SOLDE = SOLDE;
    }

    public String getBATCH_ECRITURE() {
        return BATCH_ECRITURE;
    }

    public void setBATCH_ECRITURE(String BATCH_ECRITURE) {
        this.BATCH_ECRITURE = BATCH_ECRITURE;
    }

    public String getONLINE() {
        return ONLINE;
    }

    public void setONLINE(String ONLINE) {
        this.ONLINE = ONLINE;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public void setUSER_ID(String USER_ID) {
        this.USER_ID = USER_ID;
    }

    public String getORGANIZATION() {
        return ORGANIZATION;
    }

    public void setORGANIZATION(String ORGANIZATION) {
        this.ORGANIZATION = ORGANIZATION;
    }

    @Override
    public String toString() {
        return "Ohada_trans{" + "ID=" + ID + ", COMPTE_M=" + COMPTE_M + ", COMPTE=" + COMPTE + ", CODE_M=" + CODE_M + ", CODE=" + CODE + ", CLASSE=" + CLASSE + ", SUBSTR=" + SUBSTR + ", DEBIT=" + DEBIT + ", CREDIT=" + CREDIT + ", NUM=" + NUM + ", DATES=" + DATES + ", BUSS=" + BUSS + ", LIBELLE=" + LIBELLE + ", JOURNAL=" + JOURNAL + ", CODE1=" + CODE1 + ", NUM_FACTURE=" + NUM_FACTURE + ", DEBIT_CDF=" + DEBIT_CDF + ", CREDIT_CDF=" + CREDIT_CDF + ", LB=" + LB + ", PAY=" + PAY + ", DEVICE=" + DEVICE + ", ARCHIVE=" + ARCHIVE + ", SOLDE=" + SOLDE + ", BATCH_ECRITURE=" + BATCH_ECRITURE + ", ONLINE=" + ONLINE + ", USER_ID=" + USER_ID + ", ORGANIZATION=" + ORGANIZATION + '}';
    }
   
}
