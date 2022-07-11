package au.com.vaishali.practice.presentation.mapper

import au.com.vaishali.practice.presentation.flickerPhotosResponseDomainBuilder
import au.com.vaishali.practice.presentation.model.FlickerPhoto
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature
import kotlin.test.assertEquals

class FlickerPhotoMapperTest : Spek({

    Feature("I have domain flicker response and It will map to presentation model") {
        val domainFlickerResponse = flickerPhotosResponseDomainBuilder()

        Scenario("I have domain flicker response") {
            lateinit var mockFlickerPresentation: List<FlickerPhoto>

            When("I map domain flicker photo to presentation photo item") {
                mockFlickerPresentation =
                    mapToDomainToPresentationFlickerPhotoItem(domainFlickerResponse)!!
            }

            Then("I check for photo's id") {
                assertEquals("52208012689", mockFlickerPresentation[0].id)
            }

            Then("I check for photo's title")
            {
                assertEquals("2022-07-08 129 kitten", mockFlickerPresentation[0].title)
            }

            And("I check for photo's web url") {
                assertEquals("https://farm66.static.flickr.com/65535/52208012689_683bc0834a.jpg", mockFlickerPresentation[0].imageUrl)
            }
        }
    }
})