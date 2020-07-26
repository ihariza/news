package com.example.cleanarchitecture.injection.modules;

import androidx.room.Room;

import com.example.cleanarchitecture.NewsApplication;
import com.example.cleanarchitecture.data.api.NewsApi;
import com.example.cleanarchitecture.data.database.AppDatabase;
import com.example.cleanarchitecture.data.database.ReportDao;
import com.example.cleanarchitecture.data.entity.mapper.ReportDataToReportEntityMapper;
import com.example.cleanarchitecture.data.entity.mapper.ReportEntityToReportMapper;
import com.example.cleanarchitecture.data.repository.NewsRepositoryImp;
import com.example.cleanarchitecture.data.repository.local.LocalNewsRepository;
import com.example.cleanarchitecture.data.repository.local.LocalNewsRepositoryImp;
import com.example.cleanarchitecture.data.repository.remote.RemoteNewsRepository;
import com.example.cleanarchitecture.data.repository.remote.RemoteNewsRepositoryImp;
import com.example.cleanarchitecture.domain.repository.NewsRepository;
import com.example.cleanarchitecture.injection.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

import static com.example.cleanarchitecture.data.database.AppDatabase.DATABASE_NAME;

@Module
public class RepositoryModule {

    private AppDatabase appDatabase;

    public RepositoryModule(NewsApplication application) {
        appDatabase = Room.databaseBuilder(application, AppDatabase.class, DATABASE_NAME).build();
    }

    @Provides
    @PerApplication
    AppDatabase providesRoomDatabase() {
        return appDatabase;
    }

    @Provides
    @PerApplication
    ReportDao providesReportDao(AppDatabase appDatabase) {
        return appDatabase.reportDao();
    }

    @Provides
    @PerApplication
    NewsRepository providesNewsRepository(LocalNewsRepository localNewsRepository,
                                          RemoteNewsRepository remoteNewsRepository,
                                          ReportEntityToReportMapper reportEntityToReportMapper) {
        return new NewsRepositoryImp(localNewsRepository, remoteNewsRepository, reportEntityToReportMapper);
    }

    @Provides
    @PerApplication
    LocalNewsRepository provideLocalNewsRepository(ReportDao reportDao) {
        return new LocalNewsRepositoryImp(reportDao);
    }

    @Provides
    @PerApplication
    RemoteNewsRepository provideRemoteNewsRepository(NewsApi newsApi,
                                                     ReportDataToReportEntityMapper reportDataToReportEntityMapper) {
        return new RemoteNewsRepositoryImp(newsApi, reportDataToReportEntityMapper);
    }

}
