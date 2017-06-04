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
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import java.util.*;
import java.net.URL;

import javafx.scene.shape.MeshView;

public class UtilFXObject
{
         ObjModelImporter objImporter = new ObjModelImporter();
         MeshView[] meshViews ;
         public UtilFXObject(String path){
                try {
                    URL modelUrl = this.getClass().getResource(path);
                    objImporter.read(modelUrl);
                }
                catch (Exception e) {
                    // handle exception
                }
                meshViews = objImporter.getImport();
                if( path.equals("obj/hulkv/M-FF_iOS_HERO_Robert_Banner_Hulk_Avengers.obj") || path.equals("obj/ultron/M-FF_iOS_VILLAIN_Ultron_Prime.obj") || path.equals("obj/ultron2/M-FF_iOS_VILLAIN_Ultron_Mark_I.obj")){
                        setPos(0,0,0);
                }
        
         }

         public void setPos(int a ,int b ,int c)
         {
                for(MeshView meshView : meshViews){
                        meshView.setTranslateX(a);
                        meshView.setTranslateY(b);
                        meshView.setTranslateZ(c);
                        meshView.setScaleX(100);
                        meshView.setScaleY(100);
                        meshView.setScaleZ(100);
                }
         }

         public void scalee(int max){
                  for(MeshView meshView : meshViews){
                        meshView.setScaleX(max);
                        meshView.setScaleY(max);
                        meshView.setScaleZ(max);
                  }
         }

         public void setPosX(int x)
         {
                for(MeshView meshView : meshViews){
                        meshView.setTranslateX(x);
                }
         }
          public void setPosY(int y)
          {
                for(MeshView meshView : meshViews){
                        meshView.setTranslateY(y);

                }
          }
          
          public void setPosZ(int z)
          {
                for(MeshView meshView : meshViews){
                        meshView.setTranslateZ(z);
                }
          }

         public void rotation(double angle,Point3D p)
         {
          Rotate rz = new Rotate(angle, p);
          for(MeshView meshView : meshViews){
                  meshView.getTransforms().add(rz);
          }
         }
}
