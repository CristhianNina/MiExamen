package com.example.miexamen;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.HashMap;
import java.util.Map;

public class ExamenActivity extends AppCompatActivity {

    TextView mTextViewRecibidoP1;
    TextView mTextViewRecibidoP2;
    TextView mTextViewRecibidoP3;
    TextView mTextViewRecibidoP4;
    TextView mTextViewRecibidoP5;

    EditText mEditTextR1;
    EditText mEditTextR2;
    EditText mEditTextR3;
    EditText mEditTextR4;
    EditText mEditTextR5;

    Button mButtonEnviarRespuestas;

    FirebaseFirestore mFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_examen);

        mFirestore = FirebaseFirestore.getInstance();

        mTextViewRecibidoP1 = findViewById(R.id.textViewRecibidoP1);
        mTextViewRecibidoP2 = findViewById(R.id.textViewRecibidoP2);
        mTextViewRecibidoP3 = findViewById(R.id.textViewRecibidoP3);
        mTextViewRecibidoP4 = findViewById(R.id.textViewRecibidoP4);
        mTextViewRecibidoP5 = findViewById(R.id.textViewRecibidoP5);


        mEditTextR1 = findViewById(R.id.editTextR1);
        mEditTextR2 = findViewById(R.id.editTextR2);
        mEditTextR3 = findViewById(R.id.editTextR3);
        mEditTextR4 = findViewById(R.id.editTextR4);
        mEditTextR5 = findViewById(R.id.editTextR5);

        mButtonEnviarRespuestas = findViewById(R.id.btnEnviarRespuestas);

        obtenerDatos1();

        obtenerDatos2();

        obtenerDatos3();

        obtenerDatos4();

        obtenerDatos5();


        mButtonEnviarRespuestas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EnviarRespuesta1();

            }
        });

    }


//----------------------------------OBTENER PREGUNTAS---------------------------------------------------------------------------------


    private void obtenerDatos1() {
        mFirestore.collection("Examen").document("1").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {
                    String i = documentSnapshot.getString("Pregunta1");
                    mTextViewRecibidoP1.setText(i);
                }
            }
        });
    }


    private void obtenerDatos2() {
        mFirestore.collection("Examen").document("2").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {
                    String i = documentSnapshot.getString("Pregunta2");
                    mTextViewRecibidoP2.setText(i);
                }
            }
        });
    }


    private void obtenerDatos3() {
        mFirestore.collection("Examen").document("3").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {
                    String i = documentSnapshot.getString("Pregunta3");
                    mTextViewRecibidoP3.setText(i);
                }
            }
        });
    }


    private void obtenerDatos4() {
        mFirestore.collection("Examen").document("4").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {
                    String i = documentSnapshot.getString("Pregunta4");
                    mTextViewRecibidoP4.setText(i);
                }
            }
        });
    }


    private void obtenerDatos5() {
        mFirestore.collection("Examen").document("5").addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {

                if (documentSnapshot.exists()) {
                    String i = documentSnapshot.getString("Pregunta5");
                    mTextViewRecibidoP5.setText(i);
                }
            }
        });
    }


//------------------------------------ENVIAR RESPUESTAS-----------------------------------------------------------


    private void EnviarRespuesta1() {
        //convertimos
        String respuesta1 = mEditTextR1.getText().toString();
        //cramos campos enFirebase
        Map<String, Object> map = new HashMap<>();
        map.put("Respuesta1", respuesta1);


        mFirestore.collection("Examen").document("10").set(map).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                Toast.makeText(ExamenActivity.this, "Se enviaron los datos CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }

        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ExamenActivity.this, "No se pudieron crear los datos", Toast.LENGTH_SHORT).show();
            }
        });


    }

}