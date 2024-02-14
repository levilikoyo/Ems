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
public class Ohada {
     @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("name")
    @Expose
    private int name;
    
    @SerializedName("code")
    @Expose
    private int code;
     
    @SerializedName("comptemere")
    @Expose
    private int comptemere;
      
    @SerializedName("codemere")
    @Expose
    private int codemere;
       
    @SerializedName("classe")
    @Expose
    private int classe;
        
    @SerializedName("substr")
    @Expose
    private int substr;

    public Ohada(int id, int name, int code, int comptemere, int codemere, int classe, int substr) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.comptemere = comptemere;
        this.codemere = codemere;
        this.classe = classe;
        this.substr = substr;
    }

    @Override
    public String toString() {
        return "Ohada{" + "id=" + id + ", name=" + name + ", code=" + code + ", comptemere=" + comptemere + ", codemere=" + codemere + ", classe=" + classe + ", substr=" + substr + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getComptemere() {
        return comptemere;
    }

    public void setComptemere(int comptemere) {
        this.comptemere = comptemere;
    }

    public int getCodemere() {
        return codemere;
    }

    public void setCodemere(int codemere) {
        this.codemere = codemere;
    }

    public int getClasse() {
        return classe;
    }

    public void setClasse(int classe) {
        this.classe = classe;
    }

    public int getSubstr() {
        return substr;
    }

    public void setSubstr(int substr) {
        this.substr = substr;
    }
    
}
