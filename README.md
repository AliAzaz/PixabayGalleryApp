# PixabayGalleryApp

[![API](https://img.shields.io/badge/API-21%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=15) [![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

✨ Image gallery app that consumed Pixabay API ✨

<img alt="Pic-1" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic1.png" width="220" height="460"/> &nbsp; <img alt="Pic-2" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic2.png" width="220" height="460"/> &nbsp; <img alt="Pic-3" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic3.png" width="220" height="460"/> <br/> <img alt="Pic-4" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic4.png" width="220" height="460"/> &nbsp; <img alt="Pic-5" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic5.png" width="220" height="460"/>

## Features

- Search photos by querying it
- Populate latest photos
- Gallery support pagination
- Dark/Light Mode


## Architecture

 - Using Modern Android Development practices.
 - Implemented MVVM-Clean Architecture using Usecase, Repository pattern for data, and Dagger for injection.
 - MockK tests available for several classes.

## Security

I have implemented CMake security to secure an API key. If you don't want to secure your API key then [replace this](https://github.com/AliAzaz/PixabayGalleryApp/blob/0130838850daf26f322fb0036bf7fb8e080b6679/app/src/main/java/com/example/pixabaygalleryapp/base/repository/GeneralRepository.kt#L22) to your key or you can also direct pass the key in [ApiRoutes const](app/src/main/java/com/example/pixabaygalleryapp/di/auth/ApiRoutes.kt).

## How it built

- Kotlin
- Flow
- Retrofit
- Coroutine
- MVVM --- **M** [Repository Pattern] **V** [Live Data & Data Binding] **VM** [ViewModel]
- Dagger2
- Binding Adapters
- Material Components
- MockK Test


## CONNECT👍

Connect with me through my socials:

[Stackoverflow](https://stackoverflow.com/story/ali-azaz-alam), [Medium](https://medium.com/@ali.azaz.alam), [Twitter](https://twitter.com/AliAzazAlam1), and [LinkedIn](https://www.linkedin.com/in/aliazazalam/)
