package com.ihariza.news.presentation.view.main;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.verify;

public class MainPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    private MainContract.View view;

    private MainPresenter mainPresenter;

    @Before
    public void setup() {
        mainPresenter = new MainPresenter(view);
    }

    @Test
    public void startShouldShowNews() {
        mainPresenter.start();

        verify(view).showNews();
    }
}
