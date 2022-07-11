package au.com.vaishali.practice.presentation.viewmodel

import androidx.lifecycle.Observer
import au.com.vaishali.practice.application.beforeEachScenarioLiveDataStructure
import au.com.vaishali.practice.domain.model.FlickerPhotos
import au.com.vaishali.practice.domain.usecase.GetListOfFlickerPhotoUseCase
import au.com.vaishali.practice.presentation.flickerPhotosResponseDomainBuilder
import au.com.vaishali.practice.presentation.mapDomainToPresentationFlickerResponseBuilder
import au.com.vaishali.practice.presentation.viewmodel.HomeViewModel.DisplayEvent.*
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

@ExperimentalCoroutinesApi
class HomeViewModelTest : Spek({

    Feature("Test Home view model") {
        val mockDisplayObserver by memoized { mockk<Observer<HomeViewModel.DisplayEvent>>(relaxed = true) }
        val mockGetListOfFlickerPhotoUseCase by memoized {
            mockk<GetListOfFlickerPhotoUseCase>(
                relaxed = true
            )
        }

        val testDispatcher = UnconfinedTestDispatcher()

        val viewModel by memoized {
            HomeViewModel(mockGetListOfFlickerPhotoUseCase)
        }

        var mockFlickerDomainResponse: FlickerPhotos = flickerPhotosResponseDomainBuilder()
        val mockFlickerResponse = mapDomainToPresentationFlickerResponseBuilder()

        beforeEachScenario {
            Dispatchers.setMain(testDispatcher)
            coEvery { mockGetListOfFlickerPhotoUseCase.getListOfFlickerPhotos("dog") } returns mockFlickerDomainResponse
        }

        afterEachScenario {
            Dispatchers.resetMain()
        }

        beforeEachScenarioLiveDataStructure {
            viewModel.getListOfFlickerPhoto.observeForever(mockDisplayObserver)
        }

        Scenario("I search dog images using flicker api") {
            When("data is being called from api successfully") {
                testDispatcher.run {
                    viewModel.searchPhoto("dog")
                }
            }

            Then("loading displayed") {
                verify { mockDisplayObserver.onChanged(DisplayLoading) }
            }

            Then("load data of all searched images according to search text") {
                verify { mockDisplayObserver.onChanged(DisplayPhotos(mockFlickerResponse)) }
            }

            And("dismiss loading") {
                verify { mockDisplayObserver.onChanged(FinishLoading) }
            }
        }
    }
})