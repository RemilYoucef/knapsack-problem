/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgo_sad;

/**
 *
 * @author Youcef
 */
public class Objet {
    
    private int poids ;
    private int benifice ;
    private boolean pris ; 
    
    public Objet (int poids,int benifice) {
        this.poids = poids ;
        this.benifice = benifice ;        
    }
    
    public int getPoids () {
        return poids ;
    }
    
    public int getBenifice () {
        return benifice ;
    }
    
    public void setPris (boolean etat) {
        pris = etat ;
    }
    
    public boolean getPris () {
        return pris ;
    }
    
}
