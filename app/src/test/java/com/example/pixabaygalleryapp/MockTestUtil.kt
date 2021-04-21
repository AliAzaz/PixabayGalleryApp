package com.example.pixabaygalleryapp

import com.example.pixabaygalleryapp.model.*

/**
 * @author AliAzazAlam on 4/21/2021.
 */
class MockTestUtil {
    companion object {
        fun createZeroImage(): ImagesResult {
            return ImagesResult(
                total = 0,
                totalHits = 0,
                imagesInfo = listOf()
            )
        }

        fun createImages(): ImagesResult {
            return ImagesResult(
                total = 1,
                totalHits = 1,
                imagesInfo = createImageResultsList()
            )
        }

        fun fetchImages():FetchDataModel{
            return FetchDataModel(
                page = 1,
                imagesInfo = createImageResultsList()
            )
        }

        private fun createImageResultsList() = listOf(
            ImagesInfo(
                id = 2416718,
                pageURL = "https://pixabay.com/photos/baby-hands-fingers-infant-child-2416718/",
                type = "photo",
                tags = "baby, hands, fingers",
                previewURL = "https://cdn.pixabay.com/photo/2017/06/18/18/39/baby-2416718_150.jpg",
                previewWidth = 150,
                previewHeight = 101,
                webformatURL = "https://pixabay.com/get/gcdb03abd54a9acae8122994d12b830efda50211fa47bfb05acc1f0108f02c5c1a54cc33ef778082ec2b20fa19c42b961ea24a37d86cfa21e2304c943fe4ff845_640.jpg",
                webformatWidth = 640,
                webformatHeight = 432,
                largeImageURL = "https://pixabay.com/get/gdd2414bde3b91344e02294d10a73ce6ec3fc1bf35d39e0c2249db65890277b7c038b636b1e728e264bafe48e94490970a288093cc8fcff29a149563619043517_1280.jpg",
                imageWidth = 4597,
                imageHeight = 3105,
                imageSize = 2426809,
                views = 821951,
                downloads = 590734,
                favorites = 1101,
                likes = 1211,
                comments = 307,
                user_id = 19628,
                user = "RitaE",
                userImageURL = "https://cdn.pixabay.com/user/2020/09/07/16-49-31-85_250x250.jpg"
            )
        )
    }
}
