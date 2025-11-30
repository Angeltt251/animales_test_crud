package com.example.animales_test_crud;

import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AgregarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        Button boton_agregar = findViewById(R.id.boton_editar);

        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText input_nombre = findViewById(R.id.input_Nombre);
                EditText input_especie = findViewById(R.id.input_Especie);
                EditText input_edad = findViewById(R.id.input_Edad);

                //Crear INSERT para nuestra base de datos

                String nombre = input_nombre.getText().toString();
                String especie = input_especie.getText().toString();
                String edad = input_edad.getText().toString();

                //Agregar documento a Firestore
                FirebaseFirestore db_firebase = FirebaseFirestore.getInstance();

                //Crear id en firestore
                CollectionReference collecion_animales =  db_firebase.collection("animales");
                DocumentReference documento_animales = collecion_animales.document(nombre); // IMPORTANTE SUBCOLECCION SI SE DEJA EN VACIO CREAR ID ALEATORIO
                String documentId = documento_animales.getId();

                //generar objeto a almacenar en FireStore
                Map<String, Object> objeto_firestore = new HashMap<>();
                objeto_firestore.put("ID", documentId);
                objeto_firestore.put("NOMBRE", nombre);
                objeto_firestore.put("ESPECIE", especie);
                objeto_firestore.put("EDAD", Integer.parseInt(edad) );

                //asociar objeto a documento
                documento_animales.set(objeto_firestore);

                //Redireccionar a Listar
                Intent  intent = new Intent(AgregarActivity.this , MainActivity.class);
                startActivity(intent);
            }
        });


    }
}