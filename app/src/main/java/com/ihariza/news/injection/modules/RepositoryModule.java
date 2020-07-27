package com.ihariza.news.injection.modules;

import androidx.room.Room;

import com.ihariza.news.NewsApplication;
import com.ihariza.news.data.api.NewsApi;
import com.ihariza.news.data.database.AppDatabase;
import com.ihariza.news.data.database.ReportDao;
import com.ihariza.news.data.entity.mapper.ReportDataToReportEntityMapper;
import com.ihariza.news.data.entity.mapper.ReportEntityToReportMapper;
import com.ihariza.news.data.repository.NewsRepositoryImp;
import com.ihariza.news.data.repository.local.LocalNewsRepository;
import com.ihariza.news.data.repository.local.LocalNewsRepositoryImp;
import com.ihariza.news.data.repository.remote.RemoteNewsRepository;
import com.ihariza.news.data.repository.remote.RemoteNewsRepositoryImp;
import com.ihariza.news.domain.repository.NewsRepository;
import com.ihariza.news.injection.scopes.PerApplication;

import dagger.Module;
import dagger.Provides;

import static com.ihariza.news.data.database.AppDatabase.DATABASE_NAME;

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
