package com.example.konsercb.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(person: Person)

    @Update
    suspend fun update(person: Person)

    @Delete
    suspend fun delete(person: Person)

    @Query("SELECT * from tblPerson WHERE id = :id")
    fun getPerson(id: Int): Flow<Person>

    @Query("SELECT * from tblPerson ORDER BY namaperson ASC")
    fun getAllPerson(): Flow<List<Person>>
}