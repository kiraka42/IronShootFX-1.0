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
import javafx.beans.value.ChangeListener;
import javafx.scene.input.KeyEvent;
import javafx.beans.value.ObservableValue;

public class ControllerPistolero
{
        //Main Sprite 2D

         String [] character = {"b1.gif","b2.gif","b3.gif","b4.gif","d1.gif","d2.gif","d3.gif","d4.gif",
        "l1.gif","l2.gif","l3.gif","l4.gif","h1.gif","h2.gif","h3.gif","h4.gif","ed1.gif","ed2.gif","ed3.gif","ed4.gif"
        ,"eg1.gif","eg2.gif","eg3.gif","eg4.gif", "eh1.gif","eh2.gif","eh3.gif","eh4.gif"
        ,"e1.gif","e2.gif","e3.gif","e4.gif"};

        final AudioClip walk =
                new AudioClip(ViewMain.class.getResource("obj/walk.wav").toString());
        final AudioClip walk2 =
                new AudioClip(ViewMain.class.getResource("obj/fly.wav").toString());
        final AudioClip walk3 =
                new AudioClip(ViewMain.class.getResource("obj/rep.wav").toString());

         final AudioClip walk4 =
                new AudioClip(ViewMain.class.getResource("obj/boom.wav").toString());
        int depX=0;
        int depY=0;

        int moveH=0;
        int moveR=4;
        int moveL=8;
        int moveB=12;


        static int ShootDirection=0;
        int sens = 0 ;
        static int directionSens=0;

        PhongMaterial [] phongs;
        Image [] images;

        boolean [] keys = new boolean[4];

        Group group;
        Box sprite=new Box(50,50,3);

        Timeline animation;
        UtilFXObject objet;
        Group mapp3;
        final Box earth3=new Box(1500,20,800);
        UtilBall ball=new UtilBall(0);
        UtilLife life =new UtilLife(3,depX,depY);


        /**
        * Sprite 2D
        */
        public ControllerPistolero(int depX, int depY)
        {
                this.depX=depX;
                this.depY=depY;
                phongs = new PhongMaterial[character.length];
                images = new Image[character.length];

                for(int i=0;i<character.length;i++)
                {
                        images[i]= new Image(ControllerPistolero.class.getResource("/res/"+character[i]).toExternalForm());
                        phongs[i] = new PhongMaterial();
                        phongs[i].setDiffuseMap(images[i]);
                }
                sprite.setMaterial(phongs[0]);
                group = new Group(sprite);
                group.setTranslateX(depX);
                group.setTranslateY(360);
                group.setTranslateZ(depY);
        }

        /**
        *.obj file
        */
        public ControllerPistolero(int depX,int depY, String name)
        {
                this.depX=depX;
                this.depY=depY;
                objet=new UtilFXObject(name);
                objet.setPos(depX,270,depY);
                Image im3 = new Image(ControllerPistolero.class.getResource("/obj/map.jpg").toExternalForm());
                PhongMaterial phongs3 = new PhongMaterial(Color.color(1.0,1.0,1.0,0.3));
                phongs3.setDiffuseMap(im3);
                earth3.setMaterial(phongs3);
                Rotate rz = new Rotate(90.0, Rotate.X_AXIS);
                mapp3=new Group(earth3);
                mapp3.getTransforms().add(rz);
                mapp3.setTranslateX(150);
                mapp3.setTranslateY(100);
                mapp3.setTranslateZ(0);
                
                
               

              /*  Box jarvis=new Box(500,500,3);
                phongs = new PhongMaterial[character.length];
                images = new Image[character.length];


                        images[0]= new Image(Pistolero.class.getResource("/res/fond2.gif").toExternalForm());
                        phongs[0] = new PhongMaterial(Color.web("#ffff0080"));
                       // phongs[0].setSpecularColor(Color.INDIANRED);
                        phongs[0].setDiffuseMap(images[0]);

                jarvis.setMaterial(phongs[0]);
                group = new Group(jarvis);
                group.setTranslateX(depX);
                group.setTranslateY(360);
                group.setTranslateZ(depY);*/
        }

        //set a map in
       ModelFXMap mappa;
       public void setMap(ModelFXMap m){
                mappa=m;
       }


       boolean CanMove=true;
       boolean Fly=false;

       
       class PP implements Runnable{
            Scene scene;
            ModelCamera sceneP;
            Stage stage;

            public PP(Scene scene ,ModelCamera sceneP ,Stage stage){
                    this.scene=scene;
                    this.sceneP=sceneP;
                    this.stage=stage;
                    this.scene.setOnMouseClicked(new EventHandler<MouseEvent>() {

                        @Override
                        public void handle(MouseEvent arg0) {
                            if(life.VALUE<=0)
                                stage.close();
                            try{
                                Thread thread=new Thread(ball);

                                 if(!UtilBall.quantity){
                                      walk3.play(1000);
                                      ball.positionX=depX;
                                      ball.positionY=depY;
                                      ball.setSens(ShootDirection);
                                            thread.start();

                                 }

                              }catch(Exception e){}
                        }

                });
            }

            public void run(){
              
                depX+=15;
                objet.setPosX(depX);
                sceneP.camX=depX-450;
                sceneP.setTranslateX(depX-350);



                scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                 public void handle(KeyEvent ke) {
                    if(life.VALUE <= 0){
                        stage.close();
                    }
                     System.out.println("Key Released: " + ke.getText());
                     if(KeyCode.RIGHT.equals(ke.getCode())){
                        keys[0]=false;
                     }
                     if(KeyCode.UP.equals(ke.getCode())){
                        keys[1]=false;
                     }
                     if(KeyCode.DOWN.equals(ke.getCode())){
                        keys[2]=false;
                     }
                     if(KeyCode.LEFT.equals(ke.getCode())){
                        keys[3]=false;
                     }
                     if(walk.isPlaying() ){
                        walk.stop();
                     }
                  }

        });
  

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
                {

                    public void handle(KeyEvent ke)
                    {
                   try{
                     if (KeyCode.SPACE.equals(ke.getCode())) {
                                    if (stage.isFullScreen()) {
                                        stage.setFullScreen(false);
                                    } else {
                                        stage.setFullScreen(true);
                                     
                                    }
                      }

                     if(KeyCode.W.equals(ke.getCode())){
                        if(directionSens==0)
                            sceneP.setTranslateZ(depY+800);
                        else if(directionSens==1 || directionSens==-3)
                            sceneP.setTranslateX(depX+500);
                        else if(directionSens==2 || directionSens==-2)
                            sceneP.setTranslateZ(depY-700);
                        else if(directionSens==3 || directionSens ==-1  )
                            sceneP.setTranslateX(depX-800);
                     }
                     if(Fly){
                        walk.stop();
                     }
                      if (KeyCode.P.equals(ke.getCode())) {
                            Stage s = new Stage() ;
                            Label lb = new Label("Point de vie :"+life.VALUE+"\nNombre de Vampire en vie:"+ModelFXMap.LenVamp
                                +"\nIron vitesse:"+SPEED);
                            BorderPane bp = new BorderPane();
                            bp.setCenter(lb);
                            final Slider opacityLevel = new Slider(0, 1, 0);
                            bp.setBottom(opacityLevel); 
                             opacityLevel.valueProperty().addListener(new ChangeListener<Number>() {
                                public void changed(ObservableValue<? extends Number> ov,
                                    Number old_val, Number new_val) {
                                       SPEED= (int)(50.0*(double)new_val) ; 
                                }
                            });
                            scene =new Scene(bp,200,150,true);
                            s.setScene(scene);
                            s.show();
                      }
                     if (KeyCode.Z.equals(ke.getCode())) {

                                try{
                                Thread thread=new Thread(ball);

                                 if(!UtilBall.quantity){
                                      walk3.play(1000);
                                      ball.positionX=depX;
                                      ball.positionY=depY;
                                      ball.setSens(ShootDirection);
                                            thread.start();

                                 }

                              }catch(Exception e){}
                     }

                     if (KeyCode.A.equals(ke.getCode())) {

                        if(!Fly){

                                 objet.rotation(90.0,Rotate.X_AXIS);
                                 objet.setPosY(50);
                                 SPEED=80;
                                 Fly=true;
                                  if(!walk2.isPlaying()){
                                        walk2.setCycleCount(100);
                                        walk2.play(1000);

                                  }
                        }else{
                                 Fly=false;
                                 SPEED=15;
                                 objet.rotation(-90.0,Rotate.X_AXIS);
                                 objet.setPosY(280);

                                 if(walk2.isPlaying()){
                                        walk2.stop();
                                 }
                        }
                     }
                      if(!walk.isPlaying()){
                         walk.setCycleCount(100);
                         walk.play(1000);

                      }

                       CanMove=true;
                       if (KeyCode.RIGHT.equals(ke.getCode()))
                         {

                          keys[0]=true;

                         }

                          if (KeyCode.DOWN.equals(ke.getCode()))
                         {
                                keys[2]=true;

                         }
                          if (KeyCode.UP.equals(ke.getCode()))
                         {
                              keys[1]=true;

                         }
                          if (KeyCode.LEFT.equals(ke.getCode()))
                         {
                                 keys[3]=true;
                                 //life.lostPV(2);
                         }
                          if(KeyCode.S.equals(ke.getCode())){
                                if(Fly){
                                      objet.rotation(90,Rotate.Z_AXIS);
                                }
                                else{
                                      objet.rotation(90,Rotate.Y_AXIS);
                                }
                                 Rotate rz = new Rotate(90, Rotate.Y_AXIS);
                                 life.parents1.getTransforms().clear();
                                 life.parents1.getTransforms().add(rz);

                                ShootDirection++;
                                if(ShootDirection>=4)ShootDirection=0;
                          }
                          if(KeyCode.F.equals(ke.getCode())){
                                if(Fly){
                                      objet.rotation(-90,Rotate.Z_AXIS);
                                }
                                else{
                                     objet.rotation(-90,Rotate.Y_AXIS);
                                }
                                  Rotate rz = new Rotate(-90, Rotate.Y_AXIS);
                                    life.parents1.getTransforms().clear();
                                 life.parents1.getTransforms().add(rz);
                                ShootDirection--;
                                if(ShootDirection<=-1)ShootDirection=3;
                          }

                          if(KeyCode.Q.equals(ke.getCode())){
                                  directionSens++;
                                  if(directionSens == 4) directionSens = 0;
                                    final Rotate rotate = new Rotate(0, Rotate.Y_AXIS);

                                    rotate.setPivotX(depX-sceneP.camX);
                                    rotate.setPivotY(360);
                                    rotate.setPivotZ(depY-sceneP.camY);
                                    animation = new Timeline(
                                            new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                                            new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), 90))
                                    );
                                    animation.setCycleCount(1);
                                    
                                    sceneP.getTransforms().add(rotate);

                                    animation.play();

                               //    Thread turn = new Thread(new UtilRotationC(0,mappa.tiles2,mappa.vamp,group));
                             //      turn.start();
                                    //turn.interrupt();

                         }

                          if(KeyCode.D.equals(ke.getCode())){
                                  directionSens--;
                                  if(directionSens == -4) directionSens=0;
                                  final Rotate rotate = new Rotate(0, Rotate.Y_AXIS);

                                    rotate.setPivotX(depX-sceneP.camX);
                                    rotate.setPivotY(360);
                                    rotate.setPivotZ(depY-sceneP.camY);
                                    animation = new Timeline(
                                            new KeyFrame(Duration.ZERO, new KeyValue(rotate.angleProperty(), 0)),
                                            new KeyFrame(Duration.seconds(1), new KeyValue(rotate.angleProperty(), -90))
                                    );
                                    animation.setCycleCount(1);

                                    sceneP.getTransforms().add(rotate);
                                    animation.play();
                                     //   Thread turn = new Thread(new UtilRotationC(1,mappa.tiles2,mappa.vamp,group));
                                    // turn.start();
                                    //turn.interrupt();
                         }
                        //  System.out.println((int)(depX/(3000/30)) + "  "+(int)(depY/(3000/30)) );

                        deplacement(sceneP);
                        int [] tab = mappa.position(depX,depY);
                        mappa.updateAr(tab[1],tab[0],-4);
                        }catch(Exception ecp){}

                       /* for(int l=0 ; l< mappa.vamp.length ;l++)
                        {
                            int [] tab2 = mappa.position(mappa.vamp[l].positionX,mappa.vamp[l].positionY);
                            mappa.updateAr(tab[1],tab[0],7);
                        }*/
                        mappa.print();
                       
                    }
               });
            Thread.currentThread().interrupt();
            }
       }




       int SPEED=15;
       public void pp(Scene scene ,ModelCamera sceneP ,Stage stage){
              Thread pp = new Thread(new PP(scene,sceneP,stage));
              pp.start();
       }
       
       public void deplacement(ModelCamera sceneP){
                try{
                if(keys[0]){
                         int [] tab;
                              if(directionSens==0){
                                        tab = mappa.position(depX,depY);
                                        if(mappa.field[tab[1]][tab[0]+1] !=0 &&  depX > ((3000/30)*(tab[0]+1)-30)){
                                             CanMove=false;
                                           //  System.exit(0);
                                            if(mappa.field[tab[1]][tab[0]+1] > 0){
                                                life.lostPV(life.VALUE-1);
                                                depX+=200;
                                                objet.setPosX(depX);
                                                walk4.play();
                                            }
                                        }
                                        }else if(directionSens == 1 || directionSens==-3){
                                                 tab = mappa.position(depX,depY);

                                        if(mappa.field[tab[1]-1][tab[0]] != 0  && depY<=(tab[1])*(3000/30)+30 ){ CanMove=false;
                                             if(mappa.field[tab[1]-1][tab[0]] > 0){
                                                life.lostPV(life.VALUE-1);
                                                depX-=200;
                                                objet.setPosX(depX);
                                                walk4.play();
                                            }
                                        }
                                        }else if(directionSens==2 || directionSens==-2){
                                               tab = mappa.position(depX,depY);
                                              if(mappa.field[tab[1]][tab[0]-1] !=0 &&  depX <= ((3000/30)*(tab[0]))+20)
                                            {
                                               CanMove=false;
                                                if(mappa.field[tab[1]][tab[0]-1] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depY-=200;
                                                    objet.setPosY(depY);
                                                    walk4.play();
                                                }
                                            }
                                        }else if(directionSens==3 || directionSens==-1){
                                             tab = mappa.position(depX,depY);
                                             if(mappa.field[tab[1]+1][tab[0]] !=0  && depY>(tab[1]+1)*(3000/30)-30 )
                                            {
                                              CanMove=false;
                                                if(mappa.field[tab[1]+1][tab[0]] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depY+=200;
                                                    objet.setPosY(depY);
                                                    walk4.play();
                                                }
                                            }
                                        }
                               if(CanMove){
                                        if(directionSens==0){
                                                depX+=SPEED;
                                                objet.setPosX(depX);
                                                sceneP.camX=depX-450;
                                                sceneP.setTranslateX(depX-350);
                                        }else if(directionSens==1 || directionSens==-3){

                                                depY-=SPEED;
                                                objet.setPosZ(depY);
                                                sceneP.camY=depY+10;
                                                sceneP.setTranslateZ(depY+10);
                                        }else if(directionSens==2|| directionSens==-2){

                                                depX-=SPEED;
                                                objet.setPosX(depX);
                                                sceneP.camX=depX-450;
                                                sceneP.setTranslateX(depX-350);
                                        }else if(directionSens==3 || directionSens==-1){

                                                depY+=SPEED;
                                                objet.setPosZ(depY);
                                                sceneP.camY=depY-10;
                                                sceneP.setTranslateZ(depY-10);

                                        }
                                }
                            
                                sens =1;
                }
                if( keys[1]){
                        int [] tab;
                                        if(directionSens==0){
                                             tab = mappa.position(depX,depY);
                                             if(mappa.field[tab[1]+1][tab[0]] !=0  && depY>(tab[1]+1)*(3000/30)-30 )
                                            {
                                              CanMove=false;
                                                if(mappa.field[tab[1]+1][tab[0]] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depX+=200;
                                                    objet.setPosX(depX);
                                                    walk4.play();
                                                }
                                            }
                                        }else if(directionSens == 1 || directionSens==-3){
                                              tab = mappa.position(depX,depY);
                                              if(mappa.field[tab[1]][tab[0]+1] !=0 &&  depX > ((3000/30)*(tab[0]+1)-30)) 
                                              {
                                                CanMove=false;
                                                  if(mappa.field[tab[1]][tab[0]+1] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depX-=200;
                                                    objet.setPosX(depX);
                                                    walk4.play();
                                                }
                                              }
                                        }else if(directionSens==2 || directionSens==-2){
                                               tab = mappa.position(depX,depY);
                                              if(mappa.field[tab[1]-1][tab[0]] !=0  && depY<=(tab[1])*(3000/30)+30 ) 
                                              {
                                                CanMove=false;
                                                 if(mappa.field[tab[1]-1][tab[0]] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depY+=200;
                                                    objet.setPosY(depY);
                                                    walk4.play();
                                                }
                                             }
                                        }else if(directionSens==3 || directionSens==-1){
                                             tab = mappa.position(depX,depY);
                                              if(mappa.field[tab[1]][tab[0]-1] !=0 &&  depX <= ((3000/30)*(tab[0]))+20) 
                                              {
                                                CanMove=false;
                                                 if(mappa.field[tab[1]][tab[0]-1] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depX-=200;
                                                    objet.setPosX(depX);
                                                    walk4.play();
                                                }
                                             }
                                        }

                             if(CanMove){
                                        if(directionSens==0){
                                                depY+=SPEED;
                                                objet.setPosZ(depY);
                                                sceneP.camY=depY-10;
                                                sceneP.setTranslateZ(depY-10);
                                        }else if(directionSens == 1 || directionSens==-3){
                                                depX+=SPEED;
                                                objet.setPosX(depX);
                                                sceneP.camX=depX-450;
                                                sceneP.setTranslateX(depX-350);
                                        }else if(directionSens==2 || directionSens==-2){
                                                depY-=SPEED;
                                                objet.setPosZ(depY);
                                                sceneP.camY=depY+10;
                                                sceneP.setTranslateZ(depY+10);

                                        }else if(directionSens==3 || directionSens==-1){
                                                depX-=SPEED;
                                                objet.setPosX(depX);
                                                sceneP.camX=depX-450;
                                                sceneP.setTranslateX(depX-350);
                                        }
                                }
                              /*  sprite.setMaterial(phongs[moveB]);
                                moveB++;
                                if(moveB>15)moveB=12;*/

                                sens =3;
                }
                if(keys[2]){
                          int [] tab ;
                                 if(directionSens==0){
                                            tab = mappa.position(depX,depY);
                                        if(mappa.field[tab[1]-1][tab[0]] != 0  && depY<=(tab[1])*(3000/30)+30 )
                                        {
                                         CanMove=false;
                                          if(mappa.field[tab[1]-1][tab[0]] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depY-=200;
                                                    objet.setPosY(depY);
                                                    walk4.play();
                                                }
                                        }
                                        }else if(directionSens == 1 || directionSens==-3){
                                               tab = mappa.position(depX,depY);
                                               if(mappa.field[tab[1]][tab[0]-1] !=0 &&  depX <= ((3000/30)*(tab[0]))+20)
                                               {
                                                CanMove=false;
                                                 if(mappa.field[tab[1]][tab[0]-1] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depX+=200;
                                                    objet.setPosX(depX);
                                                    walk4.play();
                                                }
                                               }
                                        }else if(directionSens==2 || directionSens==-2){
                                                tab = mappa.position(depX,depY);
                                             if(mappa.field[tab[1]+1][tab[0]] !=0  && depY>(tab[1]+1)*(3000/30)-30 ) 
                                             {
                                                CanMove=false;
                                                 if(mappa.field[tab[1]+1][tab[0]] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depY+=200;
                                                    objet.setPosY(depY);
                                                    walk4.play();
                                                }
                                            }
                                        }else if(directionSens==3 || directionSens==-1){
                                             tab = mappa.position(depX,depY);
                                               if(mappa.field[tab[1]][tab[0]+1] !=0 &&  depX > ((3000/30)*(tab[0]+1)-30)) 
                                                {CanMove=false;
                                                     if(mappa.field[tab[1]][tab[0]+1] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depX-=200;
                                                    objet.setPosX(depX);
                                                    walk4.play();
                                                }
                                                }
                                        }
                           if(CanMove){
                                       if(directionSens==0){
                                                depY-=SPEED;
                                                objet.setPosZ(depY);
                                                sceneP.camY=depY+10;
                                                sceneP.setTranslateZ(depY+10);
                                       }else if(directionSens==1 || directionSens == -3){
                                                depX-=SPEED;
                                                objet.setPosX(depX);
                                                sceneP.camX=depX-450;
                                                sceneP.setTranslateX(depX-350);
                                       }else if(directionSens==2 || directionSens == -2){
                                                depY+=SPEED;
                                                objet.setPosZ(depY);
                                                sceneP.camY=depY-10;
                                                sceneP.setTranslateZ(depY-10);

                                       }else if(directionSens==3 || directionSens == -1){
                                                depX+=SPEED;
                                                objet.setPosX(depX);
                                                sceneP.camX=depX-450;
                                                sceneP.setTranslateX(depX-350);
                                       }
                               }
                              /*  sprite.setMaterial(phongs[moveH]);
                                moveH++;
                                if(moveH>3)moveH=0;*/

                                sens =4;

                }
                if(keys[3]){
                 int [] tab;
                                        if(directionSens==0){
                                            tab = mappa.position(depX,depY);
                                             if(mappa.field[tab[1]][tab[0]-1] !=0 &&  depX <= ((3000/30)*(tab[0]))+20) {
                                                CanMove=false;
                                                 if(mappa.field[tab[1]][tab[0]-1] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depX+=200;
                                                    objet.setPosX(depX);
                                                    walk4.play();
                                                }
                                             }
                                        }else if(directionSens == 1 || directionSens==-3){
                                             tab = mappa.position(depX,depY);
                                             if(mappa.field[tab[1]+1][tab[0]] !=0  && depY>(tab[1]+1)*(3000/30)-30 ) {
                                                CanMove=false;
                                                 if(mappa.field[tab[1]+1][tab[0]] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depY+=200;
                                                    objet.setPosY(depY);
                                                    walk4.play();
                                                }
                                              }
                                        }else if(directionSens==2 || directionSens==-2){
                                              tab = mappa.position(depX,depY);
                                             if(mappa.field[tab[1]][tab[0]+1] !=0 &&  depX > ((3000/30)*(tab[0]+1)-30)) {
                                                CanMove=false;
                                                 if(mappa.field[tab[1]][tab[0]+1] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depX-=200;
                                                    objet.setPosX(depX);
                                                    walk4.play();
                                                }
                                             }
                                        }else if(directionSens==3 || directionSens==-1){
                                            tab = mappa.position(depX,depY);
                                        if(mappa.field[tab[1]-1][tab[0]] == 6  && depY<=(tab[1])*(3000/30)+30 ) {
                                            CanMove=false;
                                             if(mappa.field[tab[1]-1][tab[0]] > 0){
                                                    life.lostPV(life.VALUE-1);
                                                    depY-=200;
                                                    objet.setPosY(depY);
                                                    walk4.play();
                                                }
                                        }
                                        }

                         if(CanMove){
                                       if(directionSens==0){
                                                depX-=SPEED;
                                                objet.setPosX(depX);
                                                sceneP.camX=depX-450;
                                                sceneP.setTranslateX(depX-350);
                                        }else if(directionSens==1 || directionSens==-3){
                                                depY+=SPEED;
                                                objet.setPosZ(depY);
                                                sceneP.camY=depY-10;
                                                sceneP.setTranslateZ(depY-10);
                                        }else if(directionSens==2|| directionSens==-2){
                                                depX+=SPEED;
                                                objet.setPosX(depX);
                                                sceneP.camX=depX-450;
                                                sceneP.setTranslateX(depX-350);
                                        }else if(directionSens==3 || directionSens==-1){
                                                depY-=SPEED;
                                                objet.setPosZ(depY);
                                                sceneP.camY=depY+10;
                                                sceneP.setTranslateZ(depY+10);
                                        }
                                }
                              /*  sprite.setMaterial(phongs[moveL]);
                                moveL++;
                                if(moveL>11)moveL=8;*/
                                sens =2;
                }
                }catch(Exception exp){

                }

                life.setValue(depX,depY);
       }


}
