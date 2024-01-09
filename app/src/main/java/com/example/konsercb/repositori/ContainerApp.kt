package com.example.konsercb.repositori

import android.content.Context
import com.example.konsercb.database.DatabaseEvent
import com.example.konsercb.database.DatabasePerson

interface ContainerApp{
    val repositoriEvent : RepositoriEvent
    val repositoriPerson : RepositoriPerson
}
class ContainerDataApp(private val context: Context): ContainerApp{
    override val repositoriEvent: RepositoriEvent by lazy {
        OfflineRepositoriEvent(DatabaseEvent.getDatabase(context).EventDAO())
    }

    override val repositoriPerson: RepositoriPerson by lazy {
        OfflineRepositoriPerson(DatabasePerson.getDatabase(context).PersonDAO())
    }
}
