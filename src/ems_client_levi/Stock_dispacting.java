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
public class Stock_dispacting {
    
 @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("bin")
    @Expose
    private int bin;
    
    @SerializedName("num")
    @Expose
    private int num;
     
    @SerializedName("nom")
    @Expose
    private int nom;
      
    @SerializedName("store")
    @Expose
    private int store;
       
    @SerializedName("ranges")
    @Expose
    private int ranges;
        
    @SerializedName("etagere")
    @Expose
    private int etagere;
    
    @SerializedName("ligne")
    @Expose
    private int ligne;
          
    @SerializedName("bins")
    @Expose
    private int bins;
             
    @SerializedName("profondeur")
    @Expose
    private int profondeur;
                
    @SerializedName("fabricant")
    @Expose
    private int fabricant;

    public Stock_dispacting(int id, int bin, int num, int nom, int store, int ranges, int etagere, int ligne, int bins, int profondeur, int fabricant) {
        this.id = id;
        this.bin = bin;
        this.num = num;
        this.nom = nom;
        this.store = store;
        this.ranges = ranges;
        this.etagere = etagere;
        this.ligne = ligne;
        this.bins = bins;
        this.profondeur = profondeur;
        this.fabricant = fabricant;
    }

    @Override
    public String toString() {
        return "Stock_dispacting{" + "id=" + id + ", bin=" + bin + ", num=" + num + ", nom=" + nom + ", store=" + store + ", ranges=" + ranges + ", etagere=" + etagere + ", ligne=" + ligne + ", bins=" + bins + ", profondeur=" + profondeur + ", fabricant=" + fabricant + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBin() {
        return bin;
    }

    public void setBin(int bin) {
        this.bin = bin;
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

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public int getRanges() {
        return ranges;
    }

    public void setRanges(int ranges) {
        this.ranges = ranges;
    }

    public int getEtagere() {
        return etagere;
    }

    public void setEtagere(int etagere) {
        this.etagere = etagere;
    }

    public int getLigne() {
        return ligne;
    }

    public void setLigne(int ligne) {
        this.ligne = ligne;
    }

    public int getBins() {
        return bins;
    }

    public void setBins(int bins) {
        this.bins = bins;
    }

    public int getProfondeur() {
        return profondeur;
    }

    public void setProfondeur(int profondeur) {
        this.profondeur = profondeur;
    }

    public int getFabricant() {
        return fabricant;
    }

    public void setFabricant(int fabricant) {
        this.fabricant = fabricant;
    }
    
    
}
