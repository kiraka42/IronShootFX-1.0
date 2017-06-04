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




public class ModelCamera extends PerspectiveCamera
{
        int camX=0;//depX;
        int camY=0;//depY;
        public ModelCamera(boolean bool)
        {
                super(bool);
        }

}
