package com.example.konsercb.repositori

import com.example.konsercb.database.Person
import com.example.konsercb.database.PersonDAO
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriPerson(private val personDao: PersonDAO):RepositoriPerson {

    override fun getAllPersonStream(): Flow<List<Person>> = personDao.getAllPerson()

    override fun getPersonStream(id: Int): Flow<Person?> = personDao.getPerson(id)

    override suspend fun insertPerson(person: Person) = personDao.insert(person)

    override suspend fun deletePerson(person: Person) = personDao.delete(person)

    override suspend fun updatePerson(person: Person) = personDao.update(person)

}