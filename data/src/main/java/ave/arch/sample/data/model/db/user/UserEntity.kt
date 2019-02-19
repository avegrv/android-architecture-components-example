package ave.arch.sample.data.model.db.user

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ave.arch.sample.data.storage.db.Tables

@Entity(tableName = Tables.USER)
data class UserEntity constructor(

        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: Long,

        @ColumnInfo(name = "first_name")
        val firstName: String,

        @ColumnInfo(name = "last_name")
        val lastName: String
)