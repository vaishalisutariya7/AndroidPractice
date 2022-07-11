package au.com.vaishali.practice.data.mapper

import au.com.vaishali.practice.data.flickerPhotosResponseBuilder
import org.junit.Assert
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

class FlickerMapperTest : Spek({

    describe(" a flicker response is passed to mapper") {
        context("flicker response is already exist") {

            val flickerResponse = flickerPhotosResponseBuilder()
            val mockDomainFlickerResponse = mapDataToDomainFlickerPhotos(flickerResponse)

            it("It will return correct mapping data of flicker response as per domain model") {
                mockDomainFlickerResponse.photo?.get(0)?.run {
                    Assert.assertEquals("52208012689", id)
                    Assert.assertEquals("38188346@N03", owner)
                    Assert.assertEquals("683bc0834a", secret)
                    Assert.assertEquals("65535", server)
                    Assert.assertEquals(66, farm)
                    Assert.assertEquals("2022-07-08 129 kitten", title)
                }
            }
        }
    }
})