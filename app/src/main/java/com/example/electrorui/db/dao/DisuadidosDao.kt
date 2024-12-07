package com.example.electrorui.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.electrorui.db.entityModel.DisuadidosEntity

@Dao
interface DisuadidosDao {

    @Query("SELECT * FROM disuadidos_table")
    suspend fun getAll(): List<DisuadidosEntity>

    @Query("SELECT * FROM disuadidos_table WHERE idDisuadidos = :id")
    suspend fun getById(id : Int) : DisuadidosEntity

//    @Query("SELECT IFNULL( SUM( (ASolos_hombres + ASolos_mujeresNoEmb + ASolos_mujeresEmb + \n" +
//            "AAcomp_NNAs_hombres + AAcomp_NNAs_mujeresNoEmb + AAcomp_NNAs_mujeresEmb + \n" +
//            "NNAsAcomp_hombres + NNAsAcomp_mujeresNoEmb + NNAsAcomp_mujeresEmb +\n" +
//            "NNAsSolos_hombres + NNAsSolos_mujeresNoEmb + NNAsSolos_mujeresEmb) ) , 0) as total \n" +
//            "from datos_registro_table;")
//    suspend fun getTotal(): Int

    @Update
    suspend fun update(datosDisuadidosEntity: DisuadidosEntity)

    @Insert
    suspend fun insert(datosDisuadidosEntities : List<DisuadidosEntity>)

    @Delete
    suspend fun deleteEntry(datosDisuadidosEntity: DisuadidosEntity)

    @Query("DELETE FROM disuadidos_table")
    suspend fun deleteAll()

}