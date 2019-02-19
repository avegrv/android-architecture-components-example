package ave.arch.sample.data.repository.user

import ave.arch.sample.data.converter.UserConverter
import ave.arch.sample.data.dao.user.UserEntityDao
import ave.arch.sample.data.model.network.user.UserResponse
import ave.arch.sample.data.network.api.user.UserApi
import com.ave.arch.sample.domain.model.user.UserInfo
import com.ave.arch.sample.domain.providers.rx.SchedulersProvider
import com.ave.arch.sample.domain.providers.system.SystemInfoProvider
import com.ave.arch.sample.domain.repositories.user.UserRepository
import io.reactivex.Single
import javax.inject.Inject

class UserDataRepository @Inject constructor(
        private val schedulers: SchedulersProvider,
        private val systemInfoProvider: SystemInfoProvider,
        private val userApi: UserApi,
        private val userDao: UserEntityDao
) : UserRepository {

    override fun getUserInfo(): Single<UserInfo> {
        val diskObservable =
                loadFromDb()
                        .subscribeOn(schedulers.computation())
        val networkObservable =
                createCall()
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.computation())
                        .map(this::processResponse)
                        .map(this::saveCallResult)
                        .flatMap { loadFromDb() }
        val observable = if (systemInfoProvider.hasNetwork()) networkObservable else diskObservable
        return observable.map<UserInfo> { it }
                .observeOn(schedulers.mainThread())
    }

    private fun createCall() = userApi.getUserInfo()

    private fun processResponse(response: UserResponse) = UserConverter.fromNetwork(response)

    private fun saveCallResult(source: UserInfo) {
        userDao.insert(UserConverter.toDatabase(source))
    }

    private fun loadFromDb() = userDao.load().map { UserConverter.fromDatabase(it) }
}