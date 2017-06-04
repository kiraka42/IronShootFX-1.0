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
import javafx.scene.SubScene;
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
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import java.util.*;
import java.net.URL;
import javafx.scene.shape.MeshView;


public class ViewMain  extends Application
{
        ControllerPistolero pistol = new ControllerPistolero(10,10,"obj/hulk/M-FF_iOS_HERO_Tony_Stark_Iron_Man_Mark_XLIV.obj");

        ModelCamera sceneP = new ModelCamera(false);

        Group root=new Group();

        ModelFXMap carte  = new ModelFXMap("obj/map.jpg","obj/mapping01.jpg");

        Scene scene;
        SubScene subScene;

        public static int days=0;

        public void start(Stage stage)
        {
                sceneP.camX=pistol.depX;
                sceneP.camY=pistol.depY;
                stage.setTitle("Ultron invasion");

                final AudioClip walk =
                new AudioClip(ViewMain.class.getResource("song.wav").toString());
             //   final AudioClip walk2 =
               // new AudioClip(ViewMain.class.getResource("obj/begin.wav").toString());
               // final AudioClip walk3 =
                //new AudioClip(ViewMain.class.getResource("obj/forest_fire.wav").toString());

                PointLight [] light = new PointLight[100];
                int inc=0;
                for(int i=0;i < 10 ; i++)
                {
                        for(int j=0 ; j<10 ; j++)
                        {
                                light[inc]=new PointLight(Color.WHITE);
                                light[inc].setTranslateX(i*100);
                                light[inc].setTranslateY(100);
                                light[inc].setTranslateZ(j*100);
                                root.getChildren().addAll(light[inc]);
                                inc++;
                        }
                }
                AmbientLight ambientLight = new AmbientLight(Color.color(0.7, 0.2, 0.4));
                pistol.setMap(carte);
                pistol.mappa.generate(20,30,30);
                root.getChildren().addAll(pistol.objet.meshViews);
                root.getChildren().addAll(pistol.ball.objet.meshViews);
                root.getChildren().addAll(pistol.ball.light,ambientLight);
                root.getChildren().addAll(carte.mapp,carte.mapp2);

                //Element of decors
                final Sphere boule = new Sphere(600);
                PhongMaterial first = new PhongMaterial(Color.color(0.8, 0.8 , 0.3));
                boule.setMaterial(first);
                Group parents1=new Group(boule);
                parents1.setTranslateX(200);
                parents1.setTranslateY(-1000);
                parents1.setTranslateZ(6000);



                root.getChildren().addAll(parents1 );

                //Adding tiles
                for(int i=0 ; i < pistol.mappa.bloc.length ; i++){
                        if(pistol.mappa.bloc[i]!=null)
                                root.getChildren().add(pistol.mappa.bloc[i]);

                }

                for (int i=0 ;i < pistol.mappa.vamp.length ;i++){
                        if(pistol.mappa.vamp[i].objet.meshViews.length !=0)
                                root.getChildren().addAll(pistol.mappa.vamp[i].objet.meshViews);
                }

                for (int i=0 ;i < pistol.mappa.tiles2.length ;i++){
                        if(pistol.mappa.trees[i]!=null)
                                root.getChildren().addAll(pistol.mappa.trees[i].meshViews);
                }
                root.getChildren().addAll(pistol.life.parents1 , pistol.life.parents2,pistol.life.parents3);
                HBox hbox = new HBox();
                hbox.setLayoutX(75);
                hbox.setLayoutY(200);

               
                scene =new Scene(root,800,650,true);

                scene.setFill(Color.color(0.1, 0.8, 0.8,1.0));
               // stage.setFullScreen(true);
                sceneP.setTranslateX(0);
               // Basic position of camera
               
                sceneP.setTranslateY(-320);
                sceneP.setTranslateZ(0);
                scene.setCamera(sceneP);
                stage.setFullScreen(true);
                stage.setScene(scene);


                stage.show();


                try{
                    pistol.pp(scene,sceneP,stage);
                }catch(Exception ee){}


                walk.setCycleCount(100);
               // walk.play();
               // walk3.setCycleCount(900);
              //  walk3.play();
             //   walk2.play(1000);
              //  thread.start();
                Thread t = new Thread(new Day());
                t.start();


        }

        public class Day implements Runnable{
            public void run(){
                double tmp = 0.8;
                double tmp2 = 0.8;
                while(true){

                    for(int j=0 ; j < 2 ; j++){
                        for(int i=0 ; i< 7;i++){
                            scene.setFill(Color.color(0.1,tmp,tmp2,1.0));
                            if(j==0){
                                tmp-=0.1;
                                tmp2-=0.1;
                            }else{
                                tmp+=0.1;
                                tmp2+=0.1;
                            }
                            try{
                                Thread.sleep(5000);
                            }catch(Exception exp){}
                        }

                    }
                    days ++ ;
                }

            }
        }

         public static void main(String args[])
        {
           launch(args);
        }
}
