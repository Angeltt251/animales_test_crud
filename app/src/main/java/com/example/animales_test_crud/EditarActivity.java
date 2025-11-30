package com.example.animales_test_crud;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class EditarActivity extends AppCompatActivity {

    private FirebaseFirestore db_firebase;
    private String ID_ELEMENTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener ID desde Intent
        ID_ELEMENTO = getIntent().getStringExtra("ID");

        db_firebase = FirebaseFirestore.getInstance();

        // Referencias UI

        TextView editar_titulo = findViewById(R.id.editar_Titulo);
        EditText input_nombre = findViewById(R.id.input_Nombre);
        EditText input_especie = findViewById(R.id.input_Especie);
        EditText input_edad = findViewById(R.id.input_Edad);
        Button boton_editar = findViewById(R.id.boton_editar);

        // Cargar datos actuales desde Firestore
        db_firebase.collection("animales").document(ID_ELEMENTO).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documento = task.getResult();
                    if (documento.exists()) {
                        String nombre = documento.getString("NOMBRE");
                        String especie = documento.getString("ESPECIE");
                        int edad = documento.getLong("EDAD").intValue();

                        editar_titulo.setText("Editar Animal #" + ID_ELEMENTO);
                        input_nombre.setText(nombre);
                        input_especie.setText(especie);
                        input_edad.setText(String.valueOf(edad));
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón editar → actualizar en Firestore
        boton_editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = input_nombre.getText().toString();
                String especie = input_especie.getText().toString();
                String edadStr = input_edad.getText().toString();

                if (nombre.isEmpty() || especie.isEmpty() || edadStr.isEmpty()) {
                    Toast.makeText(EditarActivity.this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                int edad = Integer.parseInt(edadStr);

                Map<String, Object> objeto_firebase = new HashMap<>();
                objeto_firebase.put("NOMBRE", nombre);
                objeto_firebase.put("ESPECIE", especie);
                objeto_firebase.put("EDAD", edad);

                db_firebase.collection("animales").document(ID_ELEMENTO).update(objeto_firebase)
                        .addOnSuccessListener(aVoid -> {
                            Toast.makeText(EditarActivity.this, "Animal actualizado", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(EditarActivity.this, MainActivity.class);
                            startActivity(intent);
                        })
                        .addOnFailureListener(e ->
                                Toast.makeText(EditarActivity.this, "Error al actualizar", Toast.LENGTH_SHORT).show()
                        );
            }
        });
    }
}
