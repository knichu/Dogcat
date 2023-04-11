# DogCat - 귀여운 강아지와 고양이

<img src="/app_github_image/app_image.png" align="left"
width="200" hspace="10" vspace="10">
 
DogCat을 이용해 귀여운 강아지와 고양이 사진을 계속 볼수 있습니다.
강아지나 고양이탭에서 사진을 스크롤하면 다음 사진을 계속 랜덤으로 확인할 수 있습니다.
마음에 드는 사진이 나오면 "+" 버튼을 통해 사진을 collection에 저장할 수 있습니다.
저장한 사진들은 각 Dog, Cat collection앨범에서 저장한 날짜별로 확인할 수 있습니다.

<p align="left">
<a href="https://play.google.com/store/apps/details?id=com.godminq.dogcat">
    <img alt="Get it on Google Play"
        height="80"
        src="https://play.google.com/intl/en_us/badges/images/generic/en_badge_web_generic.png" />
</a>

<img src="/app_github_image/main1.jpeg" width="160px" title="1" alt="1"></img>
<img src="/app_github_image/main2.jpeg" width="160px" title="2" alt="2"></img>
<img src="/app_github_image/select_collection.jpeg" width="160px" title="3" alt="3"></img>
<img src="/app_github_image/collection.jpeg" width="160px" title="4" alt="4"></img>

## 개발환경 / Application Version
- Android Studio @Dolphin 2021.3.1
- minSdkVersion : 23
- targetSdkVersion : 33

## android tech
- language : Kotlin
- architecture : ACC MVVM
- di : Dagger-Hlit
- async library : Kotlin Coroutines, Flow
- image library : Glide
- network library : Retrofit2, Gson, OkHttp
- jetpack : Navigation Component, DataBinding, LiveData, Room Database, Paging3…

## 특징
- Kotlin Coroutines을 사용하여 비동기 프로그래밍을 구현했습니다.
- ACC ViewModel을 사용해 ACC MVVM 디자인 패턴을 구현하려고 노력했습니다.
- android jetpack을 최대한 활용하였습니다.

## APIs
- 강아지 API : thedogapi
- 고양이 API : thecatapi
