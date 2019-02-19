package ave.arch.sample.data.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ave.arch.sample.data.dao.user.UserEntityDao
import ave.arch.sample.data.model.db.user.UserEntity

@Database(entities = [
    UserEntity::class
], version = 1)
abstract class AppDatabaseImpl : RoomDatabase(), AppDatabase {

    companion object {
        const val DATABASE_NAME = "data"
    }

    abstract override fun userEntityDao(): UserEntityDao
}