package com.example.konsercb.repositori

import com.example.konsercb.database.Event
import com.example.konsercb.database.EventDAO
import kotlinx.coroutines.flow.Flow

class OfflineRepositoriEvent(private val eventDao: EventDAO):RepositoriEvent {

    override fun getAllEventStream(): Flow<List<Event>> = eventDao.getAllEvent()

    override fun getEventStream(id: Int): Flow<Event?> = eventDao.getEvent(id)

    override suspend fun insertEvent(event: Event) = eventDao.insert(event)

    override suspend fun deleteEvent(event: Event) = eventDao.delete(event)

    override suspend fun updateEvent(event: Event) = eventDao.update(event)

}
