package com.mobile.restaruant;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.mobile.restaruant.data.APIResponseRestaurant;
import com.mobile.restaruant.data.NetworkCalls;
import com.mobile.restaruant.data.network.model.response.restaurantresponse.RestaurantResponse;
import com.mobile.restaruant.viewmodels.RestaurantViewModel;

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
public class RestaurantViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantExecutorRule = new InstantTaskExecutorRule();

    @Mock
    NetworkCalls apiClient;
    private RestaurantViewModel viewModel;
    private RestaurantResponse restaurantResponse;
    private Throwable throwable;
    @Mock
    Observer<APIResponseRestaurant> observer;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        viewModel = new RestaurantViewModel();
        viewModel.getResponseLiveData().observeForever(observer);
    }

    @Test
    public void testNull() {
        Mockito.when(apiClient.getRestaurants("24.2355,34.2223")).thenReturn(null);
        assertNotNull(viewModel.getResponseLiveData());
        assertTrue(viewModel.getResponseLiveData().hasObservers());
    }

    @Test
    public void testApiFetchDataSuccess() {
        // Mock API response
        Mockito.when(apiClient.getRestaurants("24.2355,34.2223")).thenCallRealMethod();
        viewModel.getRestaurants("24.2355,34.2223");
        Mockito.verify(observer).onChanged(APIResponseRestaurant.loading());
        restaurantResponse = viewModel.getResponseLiveData().getValue().data;
        Mockito.verify(observer).onChanged(APIResponseRestaurant.success(restaurantResponse));
    }

    @Test
    public void testApiFetchDataError() {
        Mockito.when(apiClient.getRestaurants("24.2355,34.2223")).thenCallRealMethod();
        viewModel.getRestaurants("24.2355,34.2223");
        Mockito.verify(observer).onChanged(APIResponseRestaurant.loading());
        throwable = viewModel.getResponseLiveData().getValue().error;
        Mockito.verify(observer).onChanged(APIResponseRestaurant.error(throwable));
    }

    @After
    public void tearDown() throws Exception {
        apiClient = null;
        viewModel = null;
    }}