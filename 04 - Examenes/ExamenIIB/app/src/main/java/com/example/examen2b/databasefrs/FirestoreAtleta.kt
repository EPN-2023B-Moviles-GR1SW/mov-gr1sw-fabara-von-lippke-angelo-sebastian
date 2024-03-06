package com.example.examenib.databasefrs

import com.example.examenib.model.Atleta
import com.example.examenib.model.Deporte
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreAtleta {

    companion object{
        fun crearProducto(atleta: Atleta){
            val db = Firebase.firestore
            val productos = db.collection("productos")

            val datosProductos = hashMapOf(
                "nombre" to atleta.nombre,
                "descripcion" to atleta.descripcion,
                "precio" to atleta.precio,
                "fechaCreacion" to atleta.fechaCreacion
            )
            productos.document(atleta.id).set(datosProductos)
        }

        fun consultarProductos( listener: (ArrayList<Atleta>) -> Unit)
        {
            val db = Firebase.firestore
            val arregloAtletas = arrayListOf<Atleta>()
            val productosRefUnico = db.collection("productos")

            productosRefUnico
                .orderBy("nombre", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener {querySnapshot ->
                    // it == eso (lo que llegue)
                    for (productos in querySnapshot){
                        productos.id
                        arregloAtletas.add(anadirProducto(productos))
                    }
                    listener(arregloAtletas)
                }
                .addOnFailureListener{
                    // Errores
                }
        }


        fun anadirProducto(
            producto: QueryDocumentSnapshot
        ) : Atleta {
            val nuevoAtleta =  Atleta(

                producto.id as String,
                producto.data.get("nombre") as String,
                producto.data.get("descripcion") as String,
                producto.data.get("precio") as Long,
                (producto.data.get("fechaCreacion") as com.google.firebase.Timestamp).toDate(),
                producto.data.get("resenia") as ArrayList<Deporte>?,
            )
            return nuevoAtleta
        }

        fun consultarProducto(
            id: String,
            listener: (Atleta) -> Unit,
        ) {
            val db = Firebase.firestore
            val productosRefUnica = db.collection("productos")
            productosRefUnica
                .document(id)
                .get() // obtener 1 DOCUMENTO
                .addOnSuccessListener { querySnapshot ->
                    val document = querySnapshot
                    val atleta = Atleta(
                        document.reference.id,
                        document.data?.get("nombre") as String,
                        document.data?.get("descripcion") as String,
                        document.data?.get("precio") as Long,
                        (document.data?.get("fechaCreacion") as com.google.firebase.Timestamp).toDate(),)
                    listener(atleta)
                }
                .addOnFailureListener{
                    // Errores
                }
        }

        fun eliminarProducto(
            id: String
        ){
            val db = Firebase.firestore
            val productosRefUnica = db
                .collection("productos")

            productosRefUnica
                .document(id)
                .delete()
                .addOnCompleteListener{ /* si todo sale bien */}
                .addOnFailureListener{/* Si algo salio mal*/}
        }

        fun actualizarProductos(
            atleta: Atleta
        ){
            val db = Firebase.firestore
            val productosRefUnica = db
                .collection("productos")


            val datosActualizados = hashMapOf(
                "nombre" to atleta.nombre,
                "descripcion" to atleta.descripcion,
                "precio" to atleta.precio,
                "fechaCreacion" to atleta.fechaCreacion,
                "resenias" to listOf("opinion1", "opinion2"),
            )

            productosRefUnica
                .document(atleta.id.toString())
                .update(datosActualizados)
                .addOnSuccessListener {
                    // Operación de actualización exitosa
                }
                .addOnFailureListener { e ->
                    // Manejar el error en caso de falla
                }
        }
    }
}