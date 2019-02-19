package ave.arch.sample.data.dao.user

import androidx.room.Dao
import androidx.room.Query
import io.reactivex.Single
import ave.arch.sample.data.dao.base.BaseDao
import ave.arch.sample.data.model.db.user.UserEntity
import ave.arch.sample.data.storage.db.Tables

@Dao
abstract class UserEntityDao : BaseDao<UserEntity> {

    @Query("SELECT * FROM ${Tables.USER}")
    abstract fun load(): Single<UserEntity>
}