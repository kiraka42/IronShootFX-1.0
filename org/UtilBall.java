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




public class UtilBall implements Runnable{
  //Porte de tire de 40*unit 
        static boolean quantity=false;
        UtilFXObject objet;
        int sens;
        final AudioClip walk3 =
                new AudioClip(ViewMain.class.getResource("obj/boom.wav").toString());
        PointLight light = new PointLight(Color.RED);
        public UtilBall(int sens)
        {
                this.sens=sens;
                objet=new UtilFXObject("obj/ball/ball.obj");
                objet.setPosX(-500);
                objet.setPosY(230);
                objet.setPosZ(-500);
                
                
                light.setTranslateX(-500);
                light.setTranslateY(230);
                light.setTranslateZ(-500);
        }

        int positionX;
        int positionY;
        public  void run(){
                

                 quantity=true;
                 if(sens==0){
                   
                     int incr=positionY;
                     try{
                     Thread.sleep(1000);
                     objet.setPosX(positionX);
                     objet.setPosZ(positionY);
                    
                     for(int ii =0 ; ii < 40;ii++){
                                    objet.setPosZ(incr);
                                    light.setTranslateZ(incr);
                                    objet.rotation(10.0,Rotate.Y_AXIS);
                                    incr-=110;
                                    Thread.sleep(50);
                                    
                                   if( touched(-1,0,incr,positionX)){
                                    break;
                                   }  
                     }
                     objet.setPosX(-1000);
                     objet.setPosZ(-500);
                   

                     }catch(Exception exp){ }

               }else if(sens==1){
                   int incr=positionX;
                   try{
                   Thread.sleep(1000);
                   objet.setPosX(positionX);
                   objet.setPosZ(positionY);
                   for(int i =0 ; i < 40;i++){
                                  objet.setPosX(incr);
                                  objet.rotation(10.0,Rotate.Y_AXIS);
                                  incr-=110;
                                  Thread.sleep(50);
                                   if( touched(0,-1 ,positionY,incr)){
                                    break;
                                   }
                                  
                   }
                   objet.setPosX(-1000);
                   objet.setPosZ(-500);

                   }catch(Exception exp){ }
               }else if(sens==2){
                   int incr=positionY;
                   try{
                   Thread.sleep(1000);
                   objet.setPosX(positionX);
                   objet.setPosZ(positionY);
                   for(int i =0 ; i < 40;i++){
                                  objet.setPosZ(incr);
                                  objet.rotation(10.0,Rotate.Y_AXIS);
                                  incr+=110;
                                  Thread.sleep(50);
                                   if( touched(1,0,incr,positionX)){
                                    break;
                                   }
                   }
                   objet.setPosX(-1000);
                   objet.setPosZ(-500);

                   }catch(Exception exp){ }
               }
               else if(sens==3){
                   int incr=positionX;
                   try{
                   Thread.sleep(1000);
                   objet.setPosX(positionX);
                   objet.setPosZ(positionY);
                   for(int i =0 ; i < 40;i++){
                                  objet.setPosX(incr);
                                  objet.rotation(10.0,Rotate.Y_AXIS);
                                  incr+=110;
                                  Thread.sleep(50);
                                    if( touched(0,1 ,positionY,incr)){
                                    break;
                                   }
                   }
                   objet.setPosX(-1000);
                   objet.setPosZ(-500);

                   }catch(Exception exp){ }
               }
                quantity=false;
                Thread.currentThread().interrupt();

                
        }
        
        
        public boolean touched(int value,int value2,int incr ,int pos){
                                    try{
                                     int tmp=ModelFXMap.field[(int)(incr/(10000/100)) +value][(int)(pos/(10000/100)) + value2];
                                     if(tmp >0){
                                      ModelFXMap.field[(int)(incr/(10000/100)) +value][(int)(pos/(10000/100)) + value2]=0;
                                        ModelFXMap.LenVamp --;
                                        walk3.play();
                                       ModelFXMap.vamp[tmp].type=true;
                                       int t=0; 

                                        if(ControllerPistolero.directionSens ==2 || ControllerPistolero.directionSens == 0)
                                          t=ModelFXMap.vamp[tmp].positionY;
                                        else
                                          t=ModelFXMap.vamp[tmp].positionX;

                                        for(int i=0 ; i < 7;i++){  
                                           if(ControllerPistolero.directionSens == 2 ){ 
                                                t-=230;
                                                ModelFXMap.vamp[tmp].objet.setPosZ(t);
                                           }else if(ControllerPistolero.directionSens == 0){
                                                t+=230;
                                                ModelFXMap.vamp[tmp].objet.setPosZ(t);
                                           }else if(ControllerPistolero.directionSens == -1 || ControllerPistolero.directionSens == 3){
                                               t-=230;
                                                ModelFXMap.vamp[tmp].objet.setPosX(t);
                                           } else if(ControllerPistolero.directionSens == -2 || ControllerPistolero.directionSens == 1) {
                                              t+=230;
                                                ModelFXMap.vamp[tmp].objet.setPosX(t);
                                           }
                                                try{
                                                        Thread.sleep(80);
                                                }catch(Exception exp){}
                                         }
                                          ModelFXMap.vamp[tmp].objet.setPosY(3500);
                                      
                                       return true;
                                     }
                                      }catch(Exception exp){}
                                
                                     return false;

        }

        public void setSens(int sens)
        {
              this.sens=sens;
        }
}
