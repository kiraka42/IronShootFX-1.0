import javafx.application.*;
import javafx.beans.property.*;
import javafx.scene.*;
import javafx.scene.transform.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.image.*;
import javafx.stage.*;
import javafx.scene.input.*;
import java.lang.*;
import javafx.event.*;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.geometry.*;
import javafx.scene.media.AudioClip;
import javafx.util.Duration;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

//Stockage de scores
public class UtilScore
{
        int murderVampire;

        public UtilScore(int score){
              murderVampire=score;
        }

        public void writeScore(){
           ecrire("Score.txt",murderVampire+"\n");
        }

        public String  fichier (String ss){
		String chaine="";
		String fichier = ss;


		try{
			InputStream ips=new FileInputStream(fichier);
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				chaine+=ligne+"\n";
			}
			br.close();
		}
		catch (Exception e){
			System.out.println(e.toString());
			return "" ;
		}

		return chaine ;
	}

	 public void ecrire(String nomFic , String texte)
		{

			String adressedufichier = System.getProperty("user.dir") + "/"+ nomFic;


			try
			{
				FileWriter fw = new FileWriter(adressedufichier, true);
				BufferedWriter output = new BufferedWriter(fw);
				output.write(texte);
				output.flush();
				System.out.println("fichier cr");
			}
			catch(IOException ioe){
				System.out.print("Erreur : ");
				ioe.printStackTrace();
				}

		}
}
