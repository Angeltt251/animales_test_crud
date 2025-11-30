# CRUD de Animales con Firebase Firestore 🐾🐾🐾

Proyecto Android desarrollado en **Java + Firebase Firestore** que permite:
- Listar animales desde la base de datos.
- Agregar nuevos registros.
- Editar y eliminar animales existentes.
- Visualizar detalles de cada animal.

---

##  Funcionalidades principales 📋📋📋
- **Dashboard inicial**: muestra la lista de animales con nombre, especie y edad.
- **Detalle de animal**: permite ver información completa y acceder a opciones de editar/eliminar.
- **Agregar animal**: formulario para ingresar nombre, especie y edad.
- **Integración con Firebase Firestore**: los datos se almacenan y sincronizan en la nube.

---

##  Capturas de pantalla 📸📸📸

### Dashboard con datos
![620B1997-79C7-4A44-A281-97E8C4C22C19](https://github.com/user-attachments/assets/3b63ecd1-ab90-405c-892f-d0ba0558e5e6)
 
### Pestaña de detalle con opciones
![2E9FE124-2C29-4729-99A6-F843EE3F728F](https://github.com/user-attachments/assets/c9854890-5e64-4ee0-87ac-e6da0b9fcc20)


### Firebase Console mostrando colección `animales`
![224F83BA-4F3F-47D6-8912-95A81ED72CE5](https://github.com/user-attachments/assets/5e5f38ff-5caf-49ee-b056-1fb25e1e0e42)


---


##  Estructura del proyecto 📂📂📂
- `MainActivity.java`: lista los animales desde Firestore.
- `AgregarActivity.java`: agrega un nuevo animal (nombre como ID).
- `DetalleActivity.java`: muestra detalle y permite editar/eliminar.
- `EditarActivity.java`: actualiza datos de un animal existente.
