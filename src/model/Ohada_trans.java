/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Doshe PC
 */
public class Ohada_trans {
      @SerializedName("ID")
    @Expose
    private int id;
   @SerializedName("COMPTE_M")
    @Expose
    private String compte_m;
    @SerializedName("COMPTE")
    @Expose
    private String compte; 
    
    @SerializedName("CODE_M")
    @Expose
    private String code_m;
    @SerializedName("CODE")
    @Expose
    private String code; 
    
     @SerializedName("CLASSE")
    @Expose
    private String classe;
    @SerializedName("SUBSTR")
    @Expose
    private String substr; 
    
     @SerializedName("DEBIT")
    @Expose
    private String debit;
    @SerializedName("CREDIT")
    @Expose
    private String credit; 
    

    @SerializedName("NUM")
    @Expose
    private String num; 
     @SerializedName("DATES")
    @Expose
    private String dates;
     
    @SerializedName("BUSS")
    @Expose
    private String buss; 
     @SerializedName("LIBELLE")
    @Expose
    private String libelle;
     
    @SerializedName("JOURNAL")
    @Expose
    private String journal; 
    @SerializedName("CODE1")
    @Expose
    private String code1;
    
      @SerializedName("NUM_FACTURE")
    @Expose
    private String num_facture; 
    @SerializedName("DEBIT_CDF")
    @Expose
    private String debit_cdf;
    
      @SerializedName("CREDIT_CDF")
    @Expose
    private String credit_cdf; 
    @SerializedName("LB")
    @Expose
    private String lb;
    
      @SerializedName("PAY")
    @Expose
    private String pay; 
    @SerializedName("DEVICE")
    @Expose
    private String device;
    
      @SerializedName("ARCHIVE")
    @Expose
    private String archive; 
    @SerializedName("SOLDE")
    @Expose
    private String solde;

    
      public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCompte_m() {
        return compte_m;
    }

    public void setCompte_m(String compte_m) {
        this.compte_m = compte_m;
    }

    public String getCompte() {
        return compte;
    }

    public void setCompte(String compte) {
        this.compte = compte;
    }

    public String getCode_m() {
        return code_m;
    }

    public void setCode_m(String code_m) {
        this.code_m = code_m;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public String getSubstr() {
        return substr;
    }

    public void setSubstr(String substr) {
        this.substr = substr;
    }

    public String getDebit() {
        return debit;
    }

    public void setDebit(String debit) {
        this.debit = debit;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getBuss() {
        return buss;
    }

    public void setBuss(String buss) {
        this.buss = buss;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getCode1() {
        return code1;
    }

    public void setCode1(String code1) {
        this.code1 = code1;
    }

    public String getNum_facture() {
        return num_facture;
    }

    public void setNum_facture(String num_facture) {
        this.num_facture = num_facture;
    }

    public String getDebit_cdf() {
        return debit_cdf;
    }

    public void setDebit_cdf(String debit_cdf) {
        this.debit_cdf = debit_cdf;
    }

    public String getCredit_cdf() {
        return credit_cdf;
    }

    public void setCredit_cdf(String credit_cdf) {
        this.credit_cdf = credit_cdf;
    }

    public String getLb() {
        return lb;
    }

    public void setLb(String lb) {
        this.lb = lb;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getArchive() {
        return archive;
    }

    public void setArchive(String archive) {
        this.archive = archive;
    }

    public String getSolde() {
        return solde;
    }

    public void setSolde(String solde) {
        this.solde = solde;
    }

    public Ohada_trans(String compte_m, String compte, String code_m, String code, String classe, String substr, String debit, String credit, String num, String dates, String buss, String libelle, String journal, String code1, String num_facture, String debit_cdf, String credit_cdf, String lb, String pay, String device, String archive, String solde) {
        this.compte_m = compte_m;
        this.compte = compte;
        this.code_m = code_m;
        this.code = code;
        this.classe = classe;
        this.substr = substr;
        this.debit = debit;
        this.credit = credit;
        this.num = num;
        this.dates = dates;
        this.buss = buss;
        this.libelle = libelle;
        this.journal = journal;
        this.code1 = code1;
        this.num_facture = num_facture;
        this.debit_cdf = debit_cdf;
        this.credit_cdf = credit_cdf;
        this.lb = lb;
        this.pay = pay;
        this.device = device;
        this.archive = archive;
        this.solde = solde;
    }
  
    
    
}
