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


public class UtilRotationC implements Runnable
{
        //Be carefull ! a vampire is a tile but a vampire is a vampire !!!
        ModelVampire [] tiles;
        Group [] vampires;
        int direction;
        Group main ;

        public UtilRotationC(int direction,Group [] v ,ModelVampire [] t,Group main){
                this.direction=direction;
                vampires=v;
                tiles=t;
                this.main=main;
        }

        public synchronized  void run(){
                Rotate rz = new Rotate(10.0, Rotate.Y_AXIS);
                Rotate rz2 = new Rotate(-10.0, Rotate.Y_AXIS);
                if(direction==0)
                 {
                  for(int i=0; i < 9;i++)
                  {
                              //  main.getTransforms().add(rz);
                                for(int j=0 ; j < vampires.length ; j++){
                                        if(vampires[j]!=null)
                                        vampires[j].getTransforms().add(rz);
                                }
                             /*   for(int j=0 ; j < tiles.length ; j++){
                                       if(tiles[j].group!=null)
                                        tiles[j].group.getTransforms().add(rz);
                                }*/
                                try{
                                        Thread.sleep(100);}catch(Exception exp){}
                  }
                 }else
                        {
                                for(int i=0; i < 9;i++)
                                {       
                           //      main.getTransforms().add(rz2);
                                for(int j=0 ; j < vampires.length ; j++){
                                        if(vampires[j]!=null)
                                        vampires[j].getTransforms().add(rz2);
                                }
                              /*  for(int j=0 ; j < tiles.length ; j++){
                                      if(tiles[j].group!=null)
                                       tiles[j].group.getTransforms().add(rz2);
                                }*/
                                try{
                                        Thread.sleep(100);}catch(Exception exp){}
                                }
                        }
        }
}
