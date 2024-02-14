/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ems_client_levi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 *
 * @author Doshe PC
 */
public class Stock_db {
    
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("num")
    @Expose
    private int num;
    
    @SerializedName("nom")
    @Expose
    private int nom;
     
    @SerializedName("fabricant")
    @Expose
    private int fabricant;
      
    @SerializedName("prixachat")
    @Expose
    private int prixachat;
       
    @SerializedName("prixvente")
    @Expose
    private int prixvente;
        
    @SerializedName("alert")
    @Expose
    private int alert;
    
    @SerializedName("max_alert")
    @Expose
    private int max_alert;
          
    @SerializedName("partcode")
    @Expose
    private int partcode;
             
    @SerializedName("category")
    @Expose
    private int category;
                
    @SerializedName("oldnum")
    @Expose
    private int oldnum;

    public Stock_db(int id, int num, int nom, int fabricant, int prixachat, int prixvente, int alert, int max_alert, int partcode, int category, int oldnum) {
        this.id = id;
        this.num = num;
        this.nom = nom;
        this.fabricant = fabricant;
        this.prixachat = prixachat;
        this.prixvente = prixvente;
        this.alert = alert;
        this.max_alert = max_alert;
        this.partcode = partcode;
        this.category = category;
        this.oldnum = oldnum;
    }

    @Override
    public String toString() {
        return "Stock_db{" + "id=" + id + ", num=" + num + ", nom=" + nom + ", fabricant=" + fabricant + ", prixachat=" + prixachat + ", prixvente=" + prixvente + ", alert=" + alert + ", max_alert=" + max_alert + ", partcode=" + partcode + ", category=" + category + ", oldnum=" + oldnum + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getNom() {
        return nom;
    }

    public void setNom(int nom) {
        this.nom = nom;
    }

    public int getFabricant() {
        return fabricant;
    }

    public void setFabricant(int fabricant) {
        this.fabricant = fabricant;
    }

    public int getPrixachat() {
        return prixachat;
    }

    public void setPrixachat(int prixachat) {
        this.prixachat = prixachat;
    }

    public int getPrixvente() {
        return prixvente;
    }

    public void setPrixvente(int prixvente) {
        this.prixvente = prixvente;
    }

    public int getAlert() {
        return alert;
    }

    public void setAlert(int alert) {
        this.alert = alert;
    }

    public int getMax_alert() {
        return max_alert;
    }

    public void setMax_alert(int max_alert) {
        this.max_alert = max_alert;
    }

    public int getPartcode() {
        return partcode;
    }

    public void setPartcode(int partcode) {
        this.partcode = partcode;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getOldnum() {
        return oldnum;
    }

    public void setOldnum(int oldnum) {
        this.oldnum = oldnum;
    }
 
    
    
}
