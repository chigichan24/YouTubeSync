package com.cyder.android.syncpod.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.cyder.android.syncpod.api.SyncPodApi
import com.cyder.android.syncpod.repository.*
import com.cyder.android.syncpod.websocket.SyncPodWsApi
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by chigichan24 on 2018/01/12.
 */
@Module
class AppModule(private val context: Context) {
    @Singleton
    @Provides
    fun provideContext(): Context = context

    // TODO create singleton ormadatabase

    @Singleton
    @Provides
    fun provideUserRepository(
            api: SyncPodApi
    ): UserRepository {
        val pref = context.getSharedPreferences(APP_NAME, MODE_PRIVATE)
        return UserDataRepository(api, pref)
    }

    @Singleton
    @Provides
    fun provideRoomRepository(
            api: SyncPodApi,
            wsApi: SyncPodWsApi,
            repository: UserRepository
    ): RoomRepository = RoomDataRepository(api, wsApi, repository.getAccessToken().blockingGet()!!)

    @Singleton
    @Provides
    fun provideVideoRepository(
            syncPodWsApi: SyncPodWsApi
    ): VideoRepository = VideoDataRepository(syncPodWsApi)

    @Singleton
    @Provides
    fun provideChatRepository(
            syncPodWsApi: SyncPodWsApi
    ): ChatRepository = ChatDataRepository(syncPodWsApi)

    @Singleton
    @Provides
    fun provideYouTubeRepository(
            api: SyncPodApi,
            repository: UserRepository
    ): YouTubeRepository = YouTubeDataRepository(api, repository.getAccessToken().blockingGet()!!)

    @Singleton
    @Provides
    fun provideUserReportRepository(
            api: SyncPodApi,
            repository: UserRepository
    ): UserReportRepository = UserReportDataRepository(api, repository.getAccessToken().blockingGet()!!)


    companion object {
        const val APP_NAME = "sync-pod"
    }
}