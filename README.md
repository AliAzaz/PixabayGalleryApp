# PixabayGalleryApp

[![API](https://img.shields.io/badge/API-23%2B-blue.svg?style=flat)](https://android-arsenal.com/api?level=15) [![License: MIT](https://img.shields.io/badge/License-MIT-brightgreen.svg)](https://opensource.org/licenses/MIT)

✨ Image gallery app that consumed Pixabay API ✨

<img alt="Pic-1" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic1.png" width="220" height="460"/> &nbsp; <img alt="Pic-2" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic2.png" width="220" height="460"/> &nbsp; <img alt="Pic-3" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic3.png" width="220" height="460"/> <br/> <img alt="Pic-4" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic4.png" width="220" height="460"/> &nbsp; <img alt="Pic-5" src="https://raw.githubusercontent.com/AliAzaz/PixabayGalleryApp/master/images/Pic5.png" width="220" height="460"/>

## Features

- Search photos by querying it
- Populate the latest photos
- Gallery support pagination
- Dark/Light Mode
- GitHub Actions for automation

## Architecture

- Using Modern Android Development practices.
- Implemented MVVM-Clean Architecture using Usecase, Repository pattern for data, and Hilt for
  injection.
- MockK tests are available for several classes.

## Security

I have implemented CMake security to secure an API
key. [Please take a look at my article to learn how to implement it](https://medium.com/kotlin-academy/how-to-secure-secrets-in-android-android-security-01-a345e97c82be).

Therefore, you can paste the key here in [cpp/lib.cpp](app/src/main/cpp/lib.cpp#L7) file and add it in .gitignore file so, it will be secure, or paste the key in the [gradle.properties](/gradle.properties#L23) file to fetch it through BuildConfig.

## GitHub Actions

There are three GitHub actions available in this project, let me explain each of them:
- [unit_test_workflow.yml](/.github/workflows/unit_test_workflow.yml), this workflow triggers when any **pull request** is generated and it runs all available **Unit Tests**.
- [dispatch_git_artifact_workflow.yml](/.github/workflows/dispatch_git_artifact_workflow.yml), this workflow triggers when any merging happens at the **master** branch and it will generate **apk** and upload it to **GitHub Artifacts**.
- [manual_dispatch_app_workflow.yml](/.github/workflows/manual_dispatch_app_workflow.yml), this is a manual workflow means the user has to start it manually, as it will take these inputs, from which **Branch** you want to Generate apk and **Application type (debug or release)** and it will generate **apk** and upload it to **GitHub Artifacts**.

## How it built

- Kotlin
- Flow
- Retrofit
- Coroutine
- MVVM --- **M** [Repository Pattern] **V** [Live Data & Data Binding] **VM** [ViewModel]
- DI -> Hilt & Dagger2 implementation in
  branch *[dagger2-implementation](https://github.com/AliAzaz/PixabayGalleryApp/tree/dagger2-implementation)*
- Binding Adapters
- Material Components
- MockK Test


## CONNECT👍

Connect with me through my socials:

[Stackoverflow](https://stackoverflow.com/story/ali-azaz-alam), [Medium](https://medium.com/@ali.azaz.alam), [Twitter](https://twitter.com/AliAzazAlam1), and [LinkedIn](https://www.linkedin.com/in/aliazazalam/)
