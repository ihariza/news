package com.example.cleanarchitecture.domain;

import com.example.cleanarchitecture.domain.repository.NewsRepository;
import com.example.cleanarchitecture.domain.usecase.GetReportUseCase;

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
public class GetReportUseCaseTest {

    private static final String REPORT_ID = "1";

    @Mock
    private NewsRepository repository;

    private GetReportUseCase getReportUseCase;

    @Before
    public void setup() {
        getReportUseCase = givenAReportByIdUseCase();
    }

    @Test
    public void givenAConcreteUseCaseOfGetReport() {
        assertThat(getReportUseCase, instanceOf(GetReportUseCase.class));
    }

    @Test
    public void getReportByIdObservableFromRepository() {
        getReportUseCase.getReport(REPORT_ID);

        verify(repository).getReport(REPORT_ID);
        verifyNoMoreInteractions(repository);
    }

    private GetReportUseCase givenAReportByIdUseCase() {
        return new GetReportUseCase(repository);
    }
}
