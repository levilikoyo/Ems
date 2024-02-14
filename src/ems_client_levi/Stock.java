/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ems_client_levi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Doshe PC
 */
public class Stock {   
      @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("num_fiche")
    @Expose
    private String num_fiche;
    @SerializedName("resp")
    @Expose
    private String resp;
    
      @SerializedName("contact")
    @Expose
    private String contact;
      
    @SerializedName("bin")
    @Expose
    private String bin;
    
     @SerializedName("ref")
    @Expose
    private String ref;
     
    @SerializedName("nom")
    @Expose
    private String nom;
    
    @SerializedName("depot")
    @Expose
    private String depot;
    
    @SerializedName("ranger")
    @Expose
    private String range;
    
    @SerializedName("etagere")
    @Expose
    private String etagere;
    
    @SerializedName("ligne")
    @Expose
    private String ligne;
    
    @SerializedName("bins")
    @Expose
    private String bins;
    
    @SerializedName("profondeur")
    @Expose
    private String profondeur;
    
    @SerializedName("qty_d")
    @Expose
    private String qty_d;
    
    @SerializedName("qty_c")
    @Expose
    private String qty_c;
    
    @SerializedName("num")
    @Expose
    private String num;
    
    @SerializedName("fabricant")
    @Expose
    private String fabricant;
    
        @SerializedName("dates")
    @Expose
    private String dates;
        
    @SerializedName("noms")
    @Expose
    private String noms;
    
        @SerializedName("rn")
    @Expose
    private String rn;
    
        
        @SerializedName("pr")
    @Expose
    private String pr;
        
       @SerializedName("po")
    @Expose
    private String po;
       
       
    @SerializedName("qty_r")
    @Expose
    private String qty_r;
  
    @SerializedName("stock_sub")
    @Expose
    private String stock_sub;
              
              
    @SerializedName("stock_subc")
    @Expose
    private String stock_subc;
          
    @SerializedName("stock_sub_bin")
    @Expose
    private String stock_sub_bin;
    
     @SerializedName("partcode")
    @Expose
    private String partcode;
  
    @SerializedName("category")
    @Expose
    private String category;
              
              
    @SerializedName("old_num")
    @Expose
    private String oldname;
    
       @SerializedName("qty_send")
    @Expose
    private String qty_send;
          
    @SerializedName("ms_synchronised")
    @Expose
    private String ms_synchronised;
    
    @SerializedName("ss_synchronised")
    @Expose
    private String ss_synchronised;

    @Override
    public String toString() {
        return "Stock{" + "id=" + id + ", num_fiche=" + num_fiche + ", resp=" + resp + ", contact=" + contact + ", bin=" + bin + ", ref=" + ref + ", nom=" + nom + ", depot=" + depot + ", range=" + range + ", etagere=" + etagere + ", ligne=" + ligne + ", bins=" + bins + ", profondeur=" + profondeur + ", qty_d=" + qty_d + ", qty_c=" + qty_c + ", num=" + num + ", fabricant=" + fabricant + ", dates=" + dates + ", noms=" + noms + ", rn=" + rn + ", pr=" + pr + ", po=" + po + ", qty_r=" + qty_r + ", stock_sub=" + stock_sub + ", stock_subc=" + stock_subc + ", stock_sub_bin=" + stock_sub_bin + ", partcode=" + partcode + ", category=" + category + ", oldname=" + oldname + ", qty_send=" + qty_send + ", ms_synchronised=" + ms_synchronised + ", ss_synchronised=" + ss_synchronised + '}';
    }
    
    
    public Stock(int id, String num_fiche, String resp, String contact, String bin, String ref, String nom, String depot, String range, String etagere, String ligne, String bins, String profondeur, String qty_d, String qty_c, String num, String fabricant, String dates, String noms, String rn, String pr, String po, String qty_r, String stock_sub, String stock_subc, String stock_sub_bin, String partcode, String category, String oldname,String qty_send, String ms_synchronised, String ss_synchronised) {
        this.id = id;//1
        this.num_fiche = num_fiche;//2
        this.resp = resp;//3
        this.contact = contact;//4
        this.bin = bin;//5
        this.ref = ref;
        this.nom = nom;
        this.depot = depot;
        this.range = range;
        this.etagere = etagere;
        this.ligne = ligne;
        this.bins = bins;
        this.profondeur = profondeur;
        this.qty_d = qty_d;
        this.qty_c = qty_c;
        this.num = num;
        this.fabricant = fabricant;
        this.dates = dates;
        this.noms = noms;
        this.rn = rn;//20
        this.pr = pr;
        this.po = po;
        this.qty_r = qty_r;
        this.stock_sub = stock_sub;
        this.stock_subc = stock_subc;
        this.stock_sub_bin = stock_sub_bin;
        this.partcode = partcode;
        this.category = category;
        this.oldname = oldname;
         this.qty_send = qty_send;
        this.ms_synchronised = ms_synchronised;
        this.ss_synchronised = ss_synchronised;
    }

    public String getQty_send() {
        return qty_send;
    }

    public void setQty_send(String qty_send) {
        this.qty_send = qty_send;
    }

   
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum_fiche() {
        return num_fiche;
    }

    public void setNum_fiche(String num_fiche) {
        this.num_fiche = num_fiche;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getBin() {
        return bin;
    }

    public void setBin(String bin) {
        this.bin = bin;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getEtagere() {
        return etagere;
    }

    public void setEtagere(String etagere) {
        this.etagere = etagere;
    }

    public String getLigne() {
        return ligne;
    }

    public void setLigne(String ligne) {
        this.ligne = ligne;
    }

    public String getBins() {
        return bins;
    }

    public void setBins(String bins) {
        this.bins = bins;
    }

    public String getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(String profondeur) {
        this.profondeur = profondeur;
    }

    public String getQty_d() {
        return qty_d;
    }

    public void setQty_d(String qty_d) {
        this.qty_d = qty_d;
    }

    public String getQty_c() {
        return qty_c;
    }

    public void setQty_c(String qty_c) {
        this.qty_c = qty_c;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getFabricant() {
        return fabricant;
    }

    public void setFabricant(String fabricant) {
        this.fabricant = fabricant;
    }

    public String getDates() {
        return dates;
    }

    public void setDates(String dates) {
        this.dates = dates;
    }

    public String getNoms() {
        return noms;
    }

    public void setNoms(String noms) {
        this.noms = noms;
    }

    public String getRn() {
        return rn;
    }

    public void setRn(String rn) {
        this.rn = rn;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    public String getQty_r() {
        return qty_r;
    }

    public void setQty_r(String qty_r) {
        this.qty_r = qty_r;
    }

    public String getStock_sub() {
        return stock_sub;
    }

    public void setStock_sub(String stock_sub) {
        this.stock_sub = stock_sub;
    }

    public String getStock_subc() {
        return stock_subc;
    }

    public void setStock_subc(String stock_subc) {
        this.stock_subc = stock_subc;
    }

    public String getStock_sub_bin() {
        return stock_sub_bin;
    }

    public void setStock_sub_bin(String stock_sub_bin) {
        this.stock_sub_bin = stock_sub_bin;
    }

    public String getPartcode() {
        return partcode;
    }

    public void setPartcode(String partcode) {
        this.partcode = partcode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getOldname() {
        return oldname;
    }

    public void setOldname(String oldname) {
        this.oldname = oldname;
    }

    public String getMs_synchronised() {
        return ms_synchronised;
    }

    public void setMs_synchronised(String ms_synchronised) {
        this.ms_synchronised = ms_synchronised;
    }

    public String getSs_synchronised() {
        return ss_synchronised;
    }

    public void setSs_synchronised(String ss_synchronised) {
        this.ss_synchronised = ss_synchronised;
    }
                    
    

      
}
