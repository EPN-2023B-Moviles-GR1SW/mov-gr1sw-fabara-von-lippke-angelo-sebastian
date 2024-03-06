package com.example.examenib.databasefrs


import com.example.examenib.model.Deporte
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreDeporte {
    companion object{
        fun crearResenia(deporte: Deporte, idProducto: String){
            val db = Firebase.firestore
            val resenias = db.collection("resenias")

            val datosResenia = hashMapOf(
                "comentario" to deporte.comentario,
                "calificacion" to deporte.calificacion,
                "recomendado" to deporte.recomendado,
                "fechaPublicacion" to deporte.fechaPublicacion,
                "idProducto" to idProducto
            )
            resenias.document(deporte.id).set(datosResenia)
        }

        fun consultarResenias(listener: (ArrayList<Deporte>) -> Unit)
        {
            val db = Firebase.firestore
            val arregloDeportes = arrayListOf<Deporte>()
            val reseniasRefUnico = db.collection("resenias")

            reseniasRefUnico
                .orderBy("nombre", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener {querySnapshot ->
                    for (resenia in querySnapshot){
                        resenia.id
                        arregloDeportes.add(anadirResenia(resenia))
                    }
                    listener(arregloDeportes)
                }
                .addOnFailureListener{
                    // Errores
                }
        }


        fun anadirResenia(
            resenia: QueryDocumentSnapshot
        ) : Deporte {
            val nuevaDeporte =  Deporte(
                resenia.id as String,
                resenia.data.get("comentario") as String,
                resenia.data.get("calificacion") as Long,
                resenia.data.get("recomendado") as Boolean,
                (resenia.data.get("fechaPublicacion") as com.google.firebase.Timestamp).toDate()
            )
            return nuevaDeporte
        }

        fun consultarResenia(
            id: String,
            onSuccess: (Deporte) -> Unit
        ) {
            val db = Firebase.firestore
            val reseniasRefUnica = db.collection("resenias")
            reseniasRefUnica
                .document(id)
                .get() // obtener 1 DOCUMENTO
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val document = task.result
                        if (document != null && document.exists()) {
                            val deporte = Deporte(
                                document.reference.id,
                                document.data?.get("comentario") as String,
                                document.data?.get("calificacion") as Long,
                                document.data?.get("recomendado") as Boolean,
                                (document.data?.get("fechaPublicacion") as com.google.firebase.Timestamp).toDate()
                            )
                            onSuccess(deporte)
                        } else {
                            //salio mal
                        }
                    } else {
                        //salio mal
                    }
                }
        }

        fun eliminarResenia(
            id: String
        ){
            val db = Firebase.firestore
            val materiasRefUnica = db
                .collection("resenias")

            materiasRefUnica
                .document(id)
                .delete()
                .addOnCompleteListener{ /* si todo sale bien */}
                .addOnFailureListener{/* Si algo salio mal*/}
        }

        fun actualizarResenia(
            deporte: Deporte
        ){
            val db = Firebase.firestore
            val reseniasRefUnica = db
                .collection("resenias")

            val datosActualizados = hashMapOf(
                "comentario" to deporte.comentario,
                "calificacion" to deporte.calificacion,
                "recomendado" to deporte.recomendado,
                "fechaPublicacion" to deporte.fechaPublicacion
            )

            reseniasRefUnica
                .document(deporte.id)
                .update(datosActualizados as Map<String, Any>)
                .addOnSuccessListener {
                    // Operación de actualización exitosa
                }
                .addOnFailureListener { e ->
                    // Manejar el error en caso de falla
                }
        }

        fun consultarReseniasProductos(
            id: String,
            listener: (ArrayList<Deporte>) -> Unit
        ){
            var arregloDeportes = arrayListOf<Deporte>()
            val db = Firebase.firestore
            val reseniasRefUnica = db.collection("resenias")
            reseniasRefUnica
                .whereEqualTo("idProducto", id)
                .orderBy("calificacion", Query.Direction.ASCENDING)
                .get()
                .addOnSuccessListener { querySnapshot ->
                    for (resenia in querySnapshot){
                        resenia.id
                        arregloDeportes.add(anadirResenia(resenia))
                    }
                    listener(arregloDeportes)
                }
                .addOnFailureListener{
                    // Errores
                }
        }
    }
}