package com.example.animales_test_crud;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class DetalleActivity extends AppCompatActivity {

    private FirebaseFirestore db_firebase;
    private String ID_ELEMENTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Obtener ID desde Intent
        ID_ELEMENTO = getIntent().getStringExtra("ID");



        db_firebase = FirebaseFirestore.getInstance();


        TextView detalle_id = findViewById(R.id.detalle_id);
        TextView detalle_nombre = findViewById(R.id.detalle_Nombre);
        TextView detalle_especie = findViewById(R.id.detalle_Especie);
        TextView detalle_edad = findViewById(R.id.detalle_Edad);

        Button boton_volver = findViewById(R.id.boton_volver);
        Button boton_editar = findViewById(R.id.boton_editar);
        Button boton_eliminar = findViewById(R.id.boton_eliminar);

        // Cargar datos desde Firestore
        db_firebase.collection("animales").document(ID_ELEMENTO).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot documento = task.getResult();
                    if (documento.exists()) {
                        Animal objeto_animal = new Animal();
                        objeto_animal.ID = documento.getId();
                        objeto_animal.NOMBRE = documento.getString("NOMBRE");
                        objeto_animal.ESPECIE = documento.getString("ESPECIE");
                        objeto_animal.EDAD = documento.getLong("EDAD").intValue();


                        detalle_id.setText("ID : " + objeto_animal.ID);
                        detalle_nombre.setText("NOMBRE : " + objeto_animal.NOMBRE);
                        detalle_especie.setText("ESPECIE : " + objeto_animal.ESPECIE);
                        detalle_edad.setText("EDAD : " + objeto_animal.EDAD);
                    }
                } else {
                    Toast.makeText(DetalleActivity.this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón volver
        boton_volver.setOnClickListener(v -> {
            Intent intent_listar = new Intent(DetalleActivity.this, MainActivity.class);
            startActivity(intent_listar);
        });

        // Botón editar
        boton_editar.setOnClickListener(v -> {
            Intent intent_editar = new Intent(DetalleActivity.this, EditarActivity.class);
            intent_editar.putExtra("ID", ID_ELEMENTO);
            startActivity(intent_editar);
        });

        // Botón eliminar
        boton_eliminar.setOnClickListener(v -> {
            db_firebase.collection("animales").document(ID_ELEMENTO).delete()
                    .addOnSuccessListener(aVoid -> {
                        Toast.makeText(DetalleActivity.this, "Animal eliminado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(DetalleActivity.this, MainActivity.class);
                        startActivity(intent);
                    })
                    .addOnFailureListener(e ->
                            Toast.makeText(DetalleActivity.this, "Error al eliminar", Toast.LENGTH_SHORT).show()
                    );
        });
    }
}
