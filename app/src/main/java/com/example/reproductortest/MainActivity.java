package com.example.reproductortest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_PICK_VIDEO = 1;

    private Button mBtnSelectVideo;
    private VideoView mVideoView;
    private LinearLayout mControlsContainer;
    private Button mBtnPlay;
    private Button mBtnPause;
    private Button mBtnStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buscar vistas por ID
        mBtnSelectVideo = findViewById(R.id.btn_select_video);
        mVideoView = findViewById(R.id.video_view);
        mControlsContainer = findViewById(R.id.controls_container);
        mBtnPlay = findViewById(R.id.btn_play);
        mBtnPause = findViewById(R.id.btn_pause);
        mBtnStop = findViewById(R.id.btn_stop);

        // Configurar los botones
        mBtnSelectVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Abrir el selector de archivos de video
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE_PICK_VIDEO);
            }
        });

        mBtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Iniciar la reproducción del video
                mVideoView.start();
            }
        });

        mBtnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pausar la reproducción del video
                mVideoView.pause();
            }
        });

        mBtnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Detener la reproducción del video y regresar al inicio
                mVideoView.stopPlayback();
                mVideoView.seekTo(0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PICK_VIDEO && resultCode == RESULT_OK && data != null) {
            // Obtener la URI del video seleccionado
            Uri videoUri = data.getData();

            // Asignar la URI al VideoView
            mVideoView.setVideoURI(videoUri);

            // Mostrar los controles de reproducción
            mControlsContainer.setVisibility(View.VISIBLE);
        }
    }
}