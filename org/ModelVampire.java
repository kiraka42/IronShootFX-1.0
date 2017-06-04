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


public class ModelVampire extends Thread
{
        int identifiant;
        int direction;
        boolean type;
        Box sprite = new Box(100,100,3);
        Image[] images;
        PhongMaterial[] phongs;
        //the thing to check is a group a vampire.
        Group group;

        String [] tab1 ={"vmagie.png","vdos.png","vdos2.png","vrecul.png"};
        String [] tab2 ={"battack.png","bstand.png","bstand2.png" };

        final AudioClip walk =
                new AudioClip(ViewMain.class.getResource("obj/walk.wav").toString());
        public ModelVampire(int i,boolean s)
        {
                identifiant=i;
                images = new Image[4];
                phongs = new PhongMaterial[4];
                    for(int ii=0;ii<4;ii++)
                          {
                                if(s)
                                        images[ii]= new Image(ModelVampire.class.getResource("/res/"+tab1[ii]).toExternalForm());
                                else
                                        images[ii]= new Image(ModelVampire.class.getResource("/res/"+tab2[0]).toExternalForm());
                                phongs[ii] = new PhongMaterial();
                                phongs[ii].setDiffuseMap(images[ii]);
                          }
                          group = new Group(sprite);

        }

        UtilFXObject objet;

        public ModelVampire(int i , boolean s ,int len){
                 identifiant=i;
                 if(s){
                        int tmp=(int)(Math.random()*len);
                        if(tmp ==0){
                                 objet=new UtilFXObject("obj/ultron/M-FF_iOS_VILLAIN_Ultron_Prime.obj");
                                 objet.setPosY(280);
                        }else if(tmp == 1){
                                objet=new UtilFXObject("obj/hulkv/M-FF_iOS_HERO_Robert_Banner_Hulk_Avengers.obj");
                                 objet.setPosY(250);
                        }
                 }	else{

                        objet=new UtilFXObject("obj/Normal Midna/midna.obj");
                        objet.setPosY(380);
                 }

        }
        
        
        public void applyRot(int mesure){
                objet.rotation((90*mesure),Rotate.Y_AXIS);
        }


        int positionX;
        int positionY;

          public synchronized void run()
                {



                        int inc2=positionX;
                        int inc=positionY;
                        objet.rotation(-20,Rotate.Y_AXIS);

                        while(true){
                                objet.rotation(40,Rotate.Y_AXIS);
                                objet.setPosZ(inc);
                                inc-=30;

                                try{

                                       Thread.sleep(700);
                                       for(int i=0; i < ModelFXMap.field.length ; i++){
                                                for(int j=0; j < ModelFXMap.field[0].length ; j++){
                                                        if(ModelFXMap.field[i][j] ==identifiant) ModelFXMap.field[i][j]=0;
                                        }
                                    }
                                     ModelFXMap.field[(int)(inc/(10000/100))][(int)(inc2/(10000/100))]=identifiant;
                                     if( ModelFXMap.field[(int)(inc/(10000/100))-1][(int)(inc2/(10000/100))] == -4) break;



                                objet.rotation(-40,Rotate.Y_AXIS);
                                Thread.sleep(700);

                                }catch(Exception exp){}


                        }

                }
}
