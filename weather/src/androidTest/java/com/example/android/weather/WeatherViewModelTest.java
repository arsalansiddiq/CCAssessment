package com.example.android.weather;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;


import com.example.android.weather.data.APIResponseWeather;
import com.example.android.weather.data.NetworkCalls;
import com.example.android.weather.viewmodels.WeatherViewModel;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;


@RunWith(JUnit4.class)
public class WeatherViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    NetworkCalls apiClient;
    private WeatherViewModel viewModel;
    @Mock
    Observer<APIResponseWeather> observer;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = new WeatherViewModel();
        viewModel.getWeather().observeForever(observer);
    }

    @Test
    public void testNull() {
        Mockito.when(apiClient.getForecast(24.2355,34.2223)).thenReturn(null);
        assertNotNull(viewModel.getWeather());
        assertTrue(viewModel.getWeather().hasObservers());
    }

    @Test
    public void testApiFetchDataSuccess() {
        // Mock API response
        Mockito.when(apiClient.getForecast(24.2355,34.2223)).thenCallRealMethod();
        viewModel.getWeatherForecast(24.2355,34.2223);
        Mockito.verify(observer).onChanged(APIResponseWeather.loading());
        Mockito.verify(observer).onChanged(APIResponseWeather.success( viewModel.getWeather().getValue().data));
    }

    @Test
    public void testApiFetchDataError() {
        Mockito.when(apiClient.getForecast(24.2355,34.2223)).thenCallRealMethod();
        viewModel.getWeatherForecast(24.2355,34.2223);
        Mockito.verify(observer).onChanged(APIResponseWeather.loading());
        Mockito.verify(observer).onChanged(APIResponseWeather.error(viewModel.getWeather().getValue().error));
    }

    @After
    public void tearDown() throws Exception {
        apiClient = null;
        viewModel = null;
    }}