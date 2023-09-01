package chl.ancud.m7_sprintfinal_superheroes.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SuperheroesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSuperheroes(superheroesListadoEntity: List<SuperheroesListadoEntity>)

    @Query("Select * from tbl_superheroes")
    fun selectSuperheroes(): LiveData<List<SuperheroesListadoEntity>>
}