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
import javafx.application.Platform;

public class UtilLife{
    //only 3 life point compelled
	public int VALUE=3;
	
	Cylinder life=new Cylinder(100,100);
	Group parents1;
	Group parents2;
	Group parents3;
    UtilScore score =new UtilScore(10);
	public UtilLife(int v , int X , int Y){
		VALUE=v;

		final Box vie = new Box(50,50,50);
                PhongMaterial first = new PhongMaterial(Color.color(0.0 , 0.1, 0.8));
                vie.setMaterial(first);
                parents1=new Group(vie);   
                parents1.setTranslateX(X-20);
                parents1.setTranslateY(20);
                parents1.setTranslateZ(Y);
                final Box vie2 = new Box(50,50,50);
                PhongMaterial first2 = new PhongMaterial(Color.color(0.0, 0.1, 0.8));
                vie.setMaterial(first2);
                parents2=new Group(vie2);   
                parents2.setTranslateX(X+50);
                parents2.setTranslateY(20);
                parents2.setTranslateZ(Y); 
                final Box vie3 = new Box(50,50,50);
                PhongMaterial first3 = new PhongMaterial(Color.color(0.0, 0.1, 0.8));
                vie.setMaterial(first3);
                parents3=new Group(vie3);   
                parents3.setTranslateX(X+70);
                parents3.setTranslateY(20);
                parents3.setTranslateZ(Y); 
	}

	public void setValue(int x, int y){
  				                parents1.setTranslateX(x);
                                parents1.setTranslateZ(y);
                                parents2.setTranslateX(x+50);
                                parents2.setTranslateZ(y);
                                parents3.setTranslateX(x+70);
                                parents3.setTranslateZ(y);
	}

	public void lostPV(int v){
                        if(v<0) return;
                       if(v==0)
                                parents1.setTranslateY(-1000);
                       if(v==1)
                                parents2.setTranslateY(-1000);
                       if(v==2)
                                parents3.setTranslateY(-1000);  
                       VALUE--;
                       if(VALUE <= 0){
                            try{
                                Stage end = new Stage();
                                Label lb = new Label("GAME OVER ! SORRY"+"\nScore :\nNombre de Vampire restant:"+ModelFXMap.LenVamp
                                    +"\nNombre de jours tenu: " + ViewMain.days);
                               
                                BorderPane pp = new BorderPane();
                                pp.setCenter(lb);
                                Button buton = new Button("Close");
                                pp.setBottom(buton);
                                buton.setOnAction(e -> {
                                    score.ecrire("Score.txt","\nScore :\nNombre de Vampire restant:"+ModelFXMap.LenVamp
                                    +"\nNombre de jours tenu: " + ViewMain.days);
                                    System.exit(0);
                                });
                                 Scene sce = new Scene(pp,400,110);
                                end.setTitle("Game over screen");
                                end.setScene(sce);
                                end.show();
                               
                            }catch(Exception exp){

                            }
                          //  System.exit(0);
                       }
	}


}
