package com.example.animales_test_crud;


public class Animal {
    public String ID;
    public String NOMBRE;
    public String ESPECIE;
    public int EDAD;

    public Animal() {} // requerido por Firestore

    public Animal(String id, String nombre, String especie, int edad) {
        this.ID = id;
        this.NOMBRE = nombre;
        this.ESPECIE = especie;
        this.EDAD = edad;
    }
}