
package journals;

import ems_client.*;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class etat_de_besoin_engin {
    
    @SerializedName("no")
    @Expose
    private int no;
    @SerializedName("demandeur")
    @Expose
    private String demandeur;
    @SerializedName("description")
    @Expose
    private String description;
     @SerializedName("qty")
    @Expose
    private String qty;
    @SerializedName("unite")
    @Expose
    private String unite;
    @SerializedName("up")
    @Expose
    private String up;
    @SerializedName("pt")
    @Expose
    private String pt;
    @SerializedName("device")
    @Expose
    private String device;
    

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getDemandeur() {
        return demandeur;
    }

    public void setDemandeur(String demandeur) {
        this.demandeur = demandeur;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    
        public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }
        public String getUnite() {
        return unite;
    }

    public void setUnite(String unite) {
        this.unite = unite;
    }
        public String getUp() {
        return up;
    }

    public void setUp(String up) {
        this.up = up;
    }
        public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }
        public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    
    
    @Override
    public String toString() {
        return "Data {no : " + no +", demandeur : "+ demandeur + ", description : "+ description + ",qty : " + qty +", unite : "+ unite + ", pt : "+ pt + ", device : "+ device + "}";
    }
    
    
    
}
