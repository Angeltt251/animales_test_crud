# CRUD de Animales con Firebase Firestore 🐾

Proyecto Android desarrollado en **Java + Firebase Firestore** que permite:
- Listar animales desde la base de datos.
- Agregar nuevos registros.
- Editar y eliminar animales existentes.
- Visualizar detalles de cada animal.

---

## 🚀 Funcionalidades principales
- **Dashboard inicial**: muestra la lista de animales con nombre, especie y edad.
- **Detalle de animal**: permite ver información completa y acceder a opciones de editar/eliminar.
- **Agregar animal**: formulario para ingresar nombre, especie y edad.
- **Integración con Firebase Firestore**: los datos se almacenan y sincronizan en la nube.

---

## 📸 Capturas de pantalla

### Dashboard con datos
![Dashboard](imagenes/dashboard.png)

### Pestaña de detalle con opciones
![Detalle](imagenes/detalle.png)

### Firebase Console mostrando colección `animales`
![Firebase Console](imagenes/firebase.png)

---

## ⚙️ Tecnologías utilizadas
- Android Studio
- Java
- Firebase Firestore

---

## 📂 Estructura del proyecto
- `MainActivity.java`: lista los animales desde Firestore.
- `AgregarActivity.java`: agrega un nuevo animal (nombre como ID).
- `DetalleActivity.java`: muestra detalle y permite editar/eliminar.
- `EditarActivity.java`: actualiza datos de un animal existente.

---

## 📝 Cómo ejecutar
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/tuusuario/animales-crud.git
