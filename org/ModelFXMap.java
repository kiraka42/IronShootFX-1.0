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
import java.util.*;
import javafx.animation.AnimationTimer;




public class ModelFXMap
{
        Group [] tiles2 = new Group[100]; //tiles
        UtilFXObject [] trees = new UtilFXObject[100];

        Group [] bloc = new Group[100];

        static ModelVampire[] vamp;

        PhongMaterial [] phongs = new PhongMaterial[100];
        Image [] images = new Image[100];

        Group mapp;
        Group mapp2;

        int XX = 10000;
        int YY = 10000;
        final Box earth=new Box(XX,20,YY);
        final Box earth2=new Box(XX,20,YY);

        static int [][] field = new int[100][100];//field
        static int LenVamp=0;

        public ModelFXMap(String name , String plafond){
                Image im = new Image(ModelFXMap.class.getResource(plafond).toExternalForm());
                PhongMaterial phongs = new PhongMaterial();
                phongs.setDiffuseMap(im);
                earth.setMaterial(phongs);
                mapp=new Group(earth);
                mapp.setTranslateX(XX/2);
                mapp.setTranslateY(400);
                mapp.setTranslateZ(YY/2);

                /*Bric pour representer le plafond*/
                Image im2 = new Image(ModelFXMap.class.getResource(name).toExternalForm());
                PhongMaterial phongs2 = new PhongMaterial();
                phongs2.setDiffuseMap(im2);
                earth2.setMaterial(phongs2);
                mapp2=new Group(earth2);
                mapp2.setTranslateX(XX/2);
                mapp2.setTranslateY(-8000);
            //  mapp2.setTranslateY(-100);
                mapp2.setTranslateZ(YY/2);


        }

        public int [] position(int depX , int depY){
                int [] tab = new int[2];
                tab[0]=(int)(depX/(XX/100));
                tab[1]=(int)(depY/(YY/100));
                return tab;
        }

        public void updateAr(int a , int b,int c){
                for(int i=0; i < field.length ; i++){
                        for(int j=0; j < field[0].length ; j++){
                                if(field[i][j] ==c) field[i][j]=0;
                }
            }
                field[a][b]=c;
        }

        //t = 3d v = vampires  t2 = 2d paper
        public void generate(int v ,int t ,int t2){
                vamp = new  ModelVampire[v];
                ArrayList<Integer> corX =new ArrayList<>();
                ArrayList<Integer> corY =new ArrayList<>();
                LenVamp = v ; 
                //for the moment only 30 positions pon the data
                for(int i=1; i< 100;i++){
                        corX.add(i);
                        corY.add(i);
                }
                //pilliers Non mouvable pour le moment
                for(int i=0 ; i< t ; i++)
                {
                        int pos = (int)(Math.random()*corX.size());
                        int pos2 = (int)(Math.random()*corY.size());

                          images[0] = new Image(ModelFXMap.class.getResource("obj/map.jpg").toExternalForm());
                        
                        phongs[0] = new PhongMaterial();
                        phongs[0].setDiffuseMap(images[0]);
                        final Cylinder tile3=new Cylinder(((XX/100)/2),460);
                        tile3.setMaterial(phongs[0]);
                        bloc[i] = new Group(tile3);
                        bloc[i].setTranslateY(150);
                        bloc[i].setTranslateX((XX/100)*corX.get(pos)+((XX/100)/2));
                        bloc[i].setTranslateZ((YY/100)*corY.get(pos2)+((YY/100)/2));
                        field[corY.get(pos2)][corX.get(pos)]=-6;
                        corX.remove(pos);
                        corY.remove(pos2);
                }

                int sizeMin=210;
                //tile2D like trees
                for(int i= 0 ; i <  t2; i++)
                {
                        //rematch
                        int pos = (int)(Math.random()*corX.size());
                        int pos2 = (int)(Math.random()*corY.size());
                       


                        trees[i] = new UtilFXObject("obj/w/treewillow_tslocator_gmdc.obj");
                        trees[i].scalee(40);
                       
                        trees[i].setPosY(sizeMin);
                        trees[i].setPosX((XX/100)*corX.get(pos)+((XX/100)/2));
                        trees[i].setPosZ((YY/100)*corY.get(pos2)+((YY/100)/2));
                        field[corY.get(pos2)][corX.get(pos)]=-6;
                        corX.remove(pos);
                        corY.remove(pos2);
                       //  System.out.println(corY.size());
                }
                for(int i=0 ; i<v ;i++)
                {
                        Thread [] current = new Thread[v];
                        int pos = (int)(Math.random()*corX.size());
                        int pos2 = (int)(Math.random()*corY.size());
                        boolean tempor=true;
                        if(Math.random()<0.6){
                                tempor=true;
                        }else{
                                tempor=false;
                        }
                        vamp[i] = new ModelVampire(i,tempor,2);
                      /*vamp[i].sprite.setMaterial(vamp[i].phongs[0]);

                        vamp[i].group.setTranslateY(330);
                        vamp[i].group.setTranslateX((XX/30)*corX.get(pos)+((XX/30)/2));
                        vamp[i].group.setTranslateZ((YY/30)*corY.get(pos2)+((YY/30)/2));*/

                      //  vamp[i].objet.setPosY(380);
                        int dir=(int)(Math.random()*4);
                        vamp[i].direction=dir;
                        vamp[i].applyRot(dir+1);
                        vamp[i].objet.setPosX((XX/100)*corX.get(pos)+((XX/100)/2));
                        vamp[i].objet.setPosZ((YY/100)*corY.get(pos2)+((YY/100)/2));

                        vamp[i].positionX = (XX/100)*corX.get(pos)+((XX/100)/2);
                        vamp[i].positionY = (YY/100)*corY.get(pos2)+((YY/100)/2);

                        field[corY.get(pos2)][corX.get(pos)]=i;
                        corX.remove(pos);
                        corY.remove(pos2);

                       //vamp[i].start();

                }
                 Thread thread=new Thread(new VampireGestion(vamp));
                 thread.start();


        }

        //DEBUG
        public void print(){
               System.out.println("\n");
                  for(int i=0; i < 10; i++){
                        for(int j=0; j < 10; j++){
                               //   System.out.print(field[i][j]+" ");
                        }
                    //   System.out.println();
                  }
        }

public class VampireGestion implements Runnable
{
   // Vampire [] vamp;
    int cible =-6;
    public VampireGestion(ModelVampire [] v)
    {
      /*  vamp = new Vampire[v.length];
        for(int i=0; i< v.length ;i++){
            vamp[i]=v[i];
        }*/

    }

    public void checkingP(int in){
                                          if(vamp[in].direction==0){
                                                    vamp[in].direction=2;
                                                }else if(vamp [in].direction==1){
                                                    vamp[in].direction=3;
                                                }
                                                else if(vamp [in].direction==2){
                                                    vamp[in].direction=0;
                                                }
                                                else if(vamp [in].direction==3){
                                                    vamp[in].direction=1;
                                                }
    }
    public void run()
    {
        boolean check=false;
        boolean ishere=false;
        int inc=0;
        int inc2=0;
        while(true){
                for(int in=0; in< vamp.length ; in++){
                        if(!check){
                                inc2=vamp[in].positionX;
                                inc=vamp[in].positionY;
                                vamp[in].objet.rotation(-20,Rotate.Y_AXIS);
                        }
                                vamp[in].objet.rotation(40,Rotate.Y_AXIS);
              /*  if( FXMap.field[(int)(inc/(10000/100))-1][(int)(inc2/(10000/100))] == -4 || FXMap.field[(int)(inc/(10000/100))+1][(int)(inc2/(10000/100))] == -4 || FXMap.field[(int)(inc/(10000/100))][(int)(inc2/(10000/100))-1] == -4 || FXMap.field[(int)(inc/(10000/100))][(int)(inc2/(10000/100))+1] == -4) 
                                        ishere=true;
                                else
                                        ishere=false;*/
                                if(!vamp[in].type){
                                if(!ishere){
                                        switch(vamp[in].direction){
                                                case 0 : vamp[in].positionX-=50;
                                                 vamp[in].objet.setPosX(vamp[in].positionX);
                                                break;
                                                case 1:vamp[in].positionY+=50;
                                                vamp[in].objet.setPosZ(vamp[in].positionY);
                                                break;
                                                case 2:vamp[in].positionX+=50;
                                                vamp[in].objet.setPosX(vamp[in].positionX);
                                                break;
                                                case 3:vamp[in].positionY-=50;
                                                vamp[in].objet.setPosZ(vamp[in].positionY);
                                                break;
                                              
                                        }
                                
                               
                                try{

                                       Thread.sleep(50);
                                       for(int i=0; i < ModelFXMap.field.length ; i++){
                                                for(int j=0; j < ModelFXMap.field[0].length ; j++){
                                                        if(ModelFXMap.field[i][j] ==vamp[in].identifiant) ModelFXMap.field[i][j]=0;
                                        }
                                    }
                                  
                                
                                     ModelFXMap.field[(int)(vamp[in].positionY/(10000/100))][(int)(vamp[in].positionX/(10000/100))]=vamp[in].identifiant;
                                    
                                      if(ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) +1][(int)(vamp[in].positionX/(10000/100))]>0){
                                            
                                            if(vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) +1][(int)(vamp[in].positionX/(10000/100))]].type != 
                                                vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100))][(int)(vamp[in].positionX/(10000/100))]].type ){

                                            }else 
                                                vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) +1][(int)(vamp[in].positionX/(10000/100))]].objet.setPosY(1000);
                                      }else if(ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) -1][(int)(vamp[in].positionX/(10000/100))]>0){
                                            if(vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) -1][(int)(vamp[in].positionX/(10000/100))]].type != 
                                                vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100))][(int)(vamp[in].positionX/(10000/100))]].type ){
                                                
                                            }else 
                                                vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) -1][(int)(vamp[in].positionX/(10000/100))]].objet.setPosY(1000);

                                           
                                      }else if(ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) ][(int)(vamp[in].positionX/(10000/100))+1]>0){
                                           
                                         if(vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) ][(int)(vamp[in].positionX/(10000/100))+1]].type != 
                                                vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100))][(int)(vamp[in].positionX/(10000/100))]].type ){
                                                
                                            }else 
                                                vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) ][(int)(vamp[in].positionX/(10000/100))+1]].objet.setPosY(1000);
                                           
                                      }else if(ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) ][(int)(vamp[in].positionX/(10000/100))-1]>0){
                                           
                                         if(vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) ][(int)(vamp[in].positionX/(10000/100))-1]].type != 
                                                vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100))][(int)(vamp[in].positionX/(10000/100))]].type ){
                                                
                                            }else 
                                                vamp[ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) ][(int)(vamp[in].positionX/(10000/100))-1]].objet.setPosY(1000);
                                      }


                                    if(ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) +1][(int)(vamp[in].positionX/(10000/100))]  ==cible
                                        || ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) -1][(int)(vamp[in].positionX/(10000/100))]  ==cible
                                        || ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) ][(int)(vamp[in].positionX/(10000/100))+1]  ==cible
                                        || ModelFXMap.field[(int)(vamp[in].positionY/(10000/100)) +1][(int)(vamp[in].positionX/(10000/100))-1]  ==cible){
                                         checkingP(in);
                                    }
                                

                                Thread.sleep(50);
                                vamp[in].objet.rotation(-40,Rotate.Y_AXIS);
                               // Thread.sleep(100);
                                
                                        }catch(Exception exp){
                                              checkingP(in);
                                        }
                                    }
                                }
                }
                check=true;
        }


    }
}
}
