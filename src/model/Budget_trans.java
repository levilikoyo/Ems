/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Home_page.home;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Doshe PC
 */
public class Budget_trans {
//    
//    @SerializedName("ID")
//    @Expose
//    private int id;
    @SerializedName("ITEM")
    @Expose
    private String item;
    @SerializedName("DEBIT")
    @Expose
    private String debit;
    
    
     @SerializedName("CREDIT")
    @Expose
    private String credit;
    @SerializedName("SOLD")
    @Expose
    private String sold;
    @SerializedName("PROJET")
    @Expose
    private String projet;
    
    
     @SerializedName("CODE")
    @Expose
    private String code;
    @SerializedName("NUM")
    @Expose
    private String num;
    @SerializedName("CAT")
    @Expose
    private String cat;
    
    
     @SerializedName("DATES")
    @Expose
    private String dates;
    @SerializedName("MOIS")
    @Expose
    private String mois;
    @SerializedName("ANNEE")
    @Expose
    private String annee;
    
     @SerializedName("SUB_CAT")
    @Expose
    private String sub_cat;
    @SerializedName("CODE_CAT")
    @Expose
    private String code_cat;
    @SerializedName("CODE_SUB_CAT")
    @Expose
    private String code_sub_cat;
    
     @SerializedName("ITEMS")
    @Expose
    private String items;
    @SerializedName("BATCH")
    @Expose
    private String batch;
    @SerializedName("ONLINE")
    @Expose
    private String online;
    
    @SerializedName("USER")
    @Expose
    private String user;

//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
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

    public String getSold() {
        return sold;
    }

    public void setSold(String sold) {
        this.sold = sold;
    }

    public String getProjet() {
        return projet;
    }

    public void setProjet(String projet) {
        this.projet = projet;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getMois() {
        return mois;
    }

    public void setMois(String mois) {
        this.mois = mois;
    }

    public String getAnnee() {
        return annee;
    }

    public void setAnnee(String annee) {
        this.annee = annee;
    }

    public String getSub_cat() {
        return sub_cat;
    }

    public void setSub_cat(String sub_cat) {
        this.sub_cat = sub_cat;
    }

    public String getCode_cat() {
        return code_cat;
    }

    public void setCode_cat(String code_cat) {
        this.code_cat = code_cat;
    }

    public String getCode_sub_cat() {
        return code_sub_cat;
    }

    public void setCode_sub_cat(String code_sub_cat) {
        this.code_sub_cat = code_sub_cat;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }
   
     public String getuser() {
        return user;
    }

    public void setuser(String user) {
        this.user = user;
    }
    

    public Budget_trans(String item, String debit, String credit, String sold, String projet, String code, String num, String cat, String dates, String mois, String annee, String sub_cat, String code_cat, String code_sub_cat, String items, String batch) {
//        this.id = id;
        this.item = item;//1
        this.debit = debit;//2
        this.credit = credit;//3
        this.sold = sold;//4
        this.projet = projet;//5
        this.code = code;//6
        this.num = num;//7
        this.cat = cat;//8
        this.dates = dates;//9
        this.mois = mois;//10
        this.annee = annee;//11
        this.sub_cat = sub_cat;//12
        this.code_cat = code_cat;//13
        this.code_sub_cat = code_sub_cat;//14
        this.items = items;//15
        this.batch = batch;//16
        this.online = "Yes";//17
        this.user = "Levi LIKOYO";//18
    }

    
    
    
}
