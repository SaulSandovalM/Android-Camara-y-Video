package com.camara.fixtergeek.camara;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //declaramos las variables contenedoras
    private static final int VIDEO_CAPTURE = 101;
    private Uri fileUri;

    //Esto se podria si fuera foto lo que esta comentado es lo que cambia
    //private static final int CAMERA_CAPTURE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // checamos que el celular tenga camara
        Button elButton = (Button) findViewById(R.id.elButton);
        if (!hasCamera())
            elButton.setEnabled(false);
    }// onCreate

    private boolean hasCamera(){
        return (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY));
    }

    public void videoGrabar(View view){
        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);//IMAGE_CAPTURE
        startActivityForResult(intent, VIDEO_CAPTURE);//CAMERA_CAPTURE
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        Uri videoUri = data.getData();
        if (requestCode == VIDEO_CAPTURE) { //CAMERA_CAPTURE
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Tu video esta en: \n" + videoUri, Toast.LENGTH_LONG).show();
            }else if (requestCode == RESULT_CANCELED){
                Toast.makeText(this, "Grabacion de video cancelada", Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(this, "Fallo Grabacion", Toast.LENGTH_LONG).show();
            }
        }
    }//onActivityResult
}
