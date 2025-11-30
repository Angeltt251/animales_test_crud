package com.example.animales_test_crud;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FirebaseFirestore db_firebase;
    private ArrayList<Animal> lista_animales;
    private ArrayList<String> arreglo;
    private ListView listViewDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Conexión a Firestore
        db_firebase = FirebaseFirestore.getInstance();

        // Inicializar listas
        lista_animales = new ArrayList<>();
        arreglo = new ArrayList<>();

        listViewDatos = findViewById(R.id.lista_Animales);


        // Obtener registros de Firestore
        db_firebase.collection("animales").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot documento : task.getResult()) {
                        Animal obj = new Animal();
                        obj.ID = documento.getId();
                        obj.NOMBRE = documento.getString("NOMBRE");
                        obj.ESPECIE = documento.getString("ESPECIE");

                        // Validar EDAD
                        Long edadLong = documento.getLong("EDAD");
                        if (edadLong != null) {
                            obj.EDAD = edadLong.intValue();
                        } else {
                            obj.EDAD = 0;
                        }

                        lista_animales.add(obj);

                        arreglo.add( obj.NOMBRE + " (" + obj.ESPECIE + " " + obj.EDAD + ")");
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_list_item_1, arreglo);
                    listViewDatos.setAdapter(adapter);

                } else {
                    Toast.makeText(MainActivity.this, "Error al obtener datos de Firestore", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Botón agregar
        Button boton_agregar = findViewById(R.id.boton_agregar_animales);
        boton_agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AgregarActivity.class);
                startActivity(intent);
            }
        });

        // Al hacer click en un item → ir a DetalleActivity
        listViewDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Animal objeto_actual = lista_animales.get(position);
                Intent intent_detalle = new Intent(MainActivity.this, DetalleActivity.class);
                intent_detalle.putExtra("ID", objeto_actual.ID);
                startActivity(intent_detalle);
            }
        });
    }
}
