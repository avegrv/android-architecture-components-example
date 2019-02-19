package ave.arch.sample.data.delegate

import android.content.Context
import androidx.room.Room
import ave.arch.sample.data.storage.db.AppDatabase
import ave.arch.sample.data.storage.db.AppDatabaseImpl

object DataLayerDelegate {

    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabaseImpl::class.java, AppDatabaseImpl.DATABASE_NAME).build()
    }
}