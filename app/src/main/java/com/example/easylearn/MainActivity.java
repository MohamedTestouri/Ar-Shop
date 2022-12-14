package com.example.easylearn;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.ar.core.Anchor;
import com.google.ar.core.Config;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.assets.RenderableSource;
import com.google.ar.sceneform.math.Quaternion;
import com.google.ar.sceneform.math.Vector3;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

public class MainActivity extends AppCompatActivity {
    private ArFragment arFragment;
    private ModelRenderable modelRenderable;
    //3d model credit : google.poly.com
    private String Model_URL = "https://modelviewer.dev/shared-assets/models/Astronaut.glb";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
if(getIntent().getStringExtra("url") != null)
{Model_URL = getIntent().getStringExtra("url");
    System.out.println(getIntent().getStringExtra("url"));}
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        setUpModel();
        setUpPlane();
    }

    private void setUpModel() {
       /* ModelRenderable.builder()
                .setSource(this, R.raw.wolves)
                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Toast.makeText(MainActivity.this,"Model can't be Loaded", Toast.LENGTH_SHORT).show();
                    return null;
                }); */
        ModelRenderable.builder()
                        .setSource(this,
                                 RenderableSource.builder().setSource(
                                this,
                                Uri.parse(Model_URL),
                                RenderableSource.SourceType.GLB)
                                         .setScale(0.75f)
                                .setRecenterMode(RenderableSource.RecenterMode.ROOT)
                                 .build())

                .setRegistryId(Model_URL)
             //   .setSource(this, R.raw.astronaut)



                .build()
                .thenAccept(renderable -> modelRenderable = renderable)
                .exceptionally(throwable -> {
                    Log.i("Model","cant load");
                    Toast.makeText(MainActivity.this,"Model can't be Loaded", Toast.LENGTH_SHORT).show();
                    return null;
                });
    }

    private void setUpPlane(){
        arFragment.setOnTapArPlaneListener(((hitResult, plane, motionEvent) -> {
            Anchor anchor = hitResult.createAnchor();
            AnchorNode anchorNode = new AnchorNode(anchor);
            anchorNode.setParent(arFragment.getArSceneView().getScene());
            createModel(anchorNode);
        }));
    }

    private void createModel(AnchorNode anchorNode){
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
       // node.setLocalRotation(Quaternion.axisAngle(new Vector3(1f, 1f, 1f), 90f));
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        node.select();
    }
}
