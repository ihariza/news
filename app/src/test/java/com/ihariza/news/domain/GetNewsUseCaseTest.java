package com.ihariza.news.domain;

import com.ihariza.news.domain.repository.NewsRepository;
import com.ihariza.news.domain.usecase.GetNewsUseCase;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetNewsUseCaseTest {

    @Mock
    private NewsRepository repository;
    private GetNewsUseCase getNewsUseCase;

    @Before
    public void setUp() {
        getNewsUseCase = givenAReportListUseCase();
    }

    @Test
    public void givenAConcreteUseCaseOfGetNews() {
        assertThat(getNewsUseCase, instanceOf(GetNewsUseCase.class));
    }

    @Test
    public void getReportListObservableFromRepository() {
        getNewsUseCase.getNews(0);

        verify(repository).getNews(0);
        verifyNoMoreInteractions(repository);
    }

    private GetNewsUseCase givenAReportListUseCase() {
        return new GetNewsUseCase(repository);
    }
}
