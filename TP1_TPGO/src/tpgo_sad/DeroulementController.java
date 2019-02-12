/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tpgo_sad;

import java.net.InterfaceAddress;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

/**
 * FXML Controller class
 *
 * @author Youcef
 */

public class DeroulementController implements Initializable {

    int poidsMax  ; // le poids maximum
    int nbObjs ;    // le nombre d'objets
    
    Objet [] tabObjs ;  // la table des objets 
    int [][] matBenif ; // matrice des benifices 
    
    ArrayList<String> listeObjetsPris = new ArrayList<String>() ; // la liste des objets pris, la forme est :" Objet 'i'
    
    int rank = 0 ; // pour itérer le nombre d'objets dans la création 
    
    @FXML
    private TextField poidsMaxTextField ;
    
    @FXML
    private TextField nbObjsTextField ;
    
    @FXML
    private TextField poidsObjeTextField ;
    
    @FXML
    private TextField benificeObjetTextField ;
    
    @FXML
    private Label solutionLabel ;
    
    @FXML
    private Label tempsexecLabel ;

    @FXML
    private VBox matr;
    @FXML
    private HBox pris;
    
    //Afficher la matrice matBenif dans matr (VBOX)
    public void showMatr(){
        HBox h = new HBox();
        Label l = new Label();
        l.setMinWidth(60);
        l.setMinHeight(60);
        l.setAlignment(Pos.CENTER);
        h.getChildren().add(l);
        for(int j=0;j<=poidsMax;j++){
            l = new Label();
            l.setText("j= "+j);
            l.setStyle("-fx-font-weight: bold;-fx-border-color:black;-fx-border-width: 1px;");
            l.setMinWidth(60);
            l.setMinHeight(60);
            l.setAlignment(Pos.CENTER);
            h.getChildren().add(l);
        }
        matr.getChildren().add(h);

        for(int i=0;i<nbObjs;i++){
             h = new HBox();
             l = new Label();
             int i0=i+1;
            l.setText("i= "+i0);
            l.setStyle("-fx-font-weight: bold;-fx-border-color:black;-fx-border-width: 1px;");

            l.setMinWidth(60);
            l.setMinHeight(60);
            l.setAlignment(Pos.CENTER);
            h.getChildren().add(l);
            for(int j=0;j<=poidsMax;j++){
                l = new Label();
                l.setText(String.valueOf(matBenif[i][j]));
                l.setStyle("-fx-border-color:black;-fx-border-width: 1px;");

                l.setMinWidth(60);
                l.setMinHeight(60);
                l.setAlignment(Pos.CENTER);
                h.getChildren().add(l);
            }
            matr.getChildren().add(h);
        }
    }
    
    
    // cette fonction remplis la matrice et affiche le benifice maximale
    public int remplirMatrice ()
    {
        matBenif = new int [nbObjs][poidsMax+1];;
        // Premier Objet 
        for (int i = 0 ; i <= this.poidsMax ; i++ )
        {
            this.matBenif[0][i] = ( i < this.tabObjs[0].getPoids() ) ? 0 : this.tabObjs[0].getBenifice() ;
        }
            
        // Les autres objets
        for(int k = 1 ; k < this.nbObjs ; k++ )
        {
            for (int i = 0 ; i <= this.poidsMax ; i++)
            {
                this.matBenif[k][i] = ( i < this.tabObjs[k].getPoids())? this.matBenif[k-1][i] :
                Math.max(this.matBenif[k-1][i],this.matBenif[k-1][i-this.tabObjs[k].getPoids()]+this.tabObjs[k].getBenifice()); 
            }
        }
        elementsPris();
        showMatr();
        retournerElementsPris();
        return this.matBenif[this.nbObjs-1][this.poidsMax]; 
    }



    // cette fonction permet d'attribuer la valeur TRUE ou FALSE au champs pris des elements
    public void elementsPris()
    {
        int k = this.nbObjs - 1 ;     
        int l = this.poidsMax ;
        int valeurA = 0 , valeurB = 0 ;
        
        while (k > 0) 
        {
            valeurA = this.matBenif[k][l];
            valeurB = this.matBenif[k-1][l];
            if (valeurA != valeurB) 
            {
                this.tabObjs[k].setPris(true);
                l = l-this.tabObjs[k].getPoids();
            }
            else 
            {
                this.tabObjs[k].setPris(false);
            }
            k = k-1 ;
        }
        if (valeurB != 0) this.tabObjs[0].setPris(true);
        else this.tabObjs[0].setPris(false);      
    }
    
    public void retournerElementsPris ()
    {
        int j ;
        for(int i=0 ; i<this.nbObjs ; i++)
        {
            j = i+1 ;
            // les éléments pris sont enregistrés dans la liste comme un string pour les afficher dans liste view 
            if (this.tabObjs[i].getPris()) this.listeObjetsPris.add("Objet "+j);
        }

        for(String s: listeObjetsPris){
            System.out.println(s);
            ObjetConainer obj = new ObjetConainer(s);
            pris.getChildren().add(obj.getAffichage());

        }

    }

    class ObjetConainer{
        private String nom;
        private int poids ;
        private int benifice ;
        public ObjetConainer(String nom){
            this.nom = nom;
            int indx = Integer.parseInt(nom.toCharArray()[nom.length()-1]+"")-1;
            this.poids = tabObjs[indx].getPoids();
            this.benifice = tabObjs[indx].getBenifice();
        }
        public HBox getAffichage(){
            Label n = new Label();
            Label margin = new Label();
            margin.setMinWidth(20);
            HBox ap = new HBox();
            n.setStyle("-fx-background-color:  #0b79a1;-fx-text-fill: white");
            margin.setStyle("-fx-background-color: white");
            n.setText(" "+nom+"( Poids = "+this.poids+" , Benifice = "+this.benifice+" ) ");
            n.setMinWidth(100);
            ap.getChildren().add(n);
            ap.setAlignment(Pos.CENTER);

            ap.getChildren().add(margin);
            return ap;
        }
    }
    
    // Set poidsMax & nbObjs
    public void valider1Button ()
    {
        this.poidsMax = Integer.parseInt(poidsMaxTextField.getText()) ;     
        this.nbObjs = Integer.parseInt(nbObjsTextField.getText()) ; 
        tabObjs = new Objet [nbObjs];
    }
    
    // Set poids & benifice pour chaque objet 
    public void valider2Button ()
    {
        int poids ;
        int benifice ;
        
        if (this.rank < this.nbObjs)
        {
            poids = Integer.parseInt(poidsObjeTextField.getText());
            benifice = Integer.parseInt(benificeObjetTextField.getText());  
            tabObjs[rank] = new Objet(poids,benifice);
            this.rank ++ ;
        }
        poidsObjeTextField.setText("");
        benificeObjetTextField.setText("");
        if (this.rank == nbObjs) {
            long start = System.currentTimeMillis();
            solutionLabel.setText(Integer.toString(remplirMatrice()));
            long duree = System.currentTimeMillis() - start;
            tempsexecLabel.setText(Long.toString(duree));
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    
    
}
