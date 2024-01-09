package com.example.konsercb.repositori

import com.example.konsercb.database.Person
import kotlinx.coroutines.flow.Flow

interface RepositoriPerson {
    fun getAllPersonStream(): Flow<List<Person>>

    fun getPersonStream(id: Int): Flow<Person?>

    suspend fun insertPerson(person: Person)

    suspend fun deletePerson(person: Person)

    suspend fun updatePerson(person: Person)
}
