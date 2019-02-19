package ave.arch.sample.data.storage.db

import ave.arch.sample.data.dao.user.UserEntityDao

interface AppDatabase {

    fun userEntityDao(): UserEntityDao
}