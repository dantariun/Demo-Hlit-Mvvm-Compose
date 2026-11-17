# Demo-Hilt-Mvvm-Compose

Hilt · MVVM · Jetpack Compose · 멀티모듈 아키텍처를 한 프로젝트에서 실습하는 Android 데모 앱.
Convention Plugin을 직접 구현해 Gradle 설정을 모듈 단위로 분리·재사용하는 구조를 보여준다.

## Stack

![Kotlin](https://img.shields.io/badge/Kotlin-2.x-7F52FF)
![Compose](https://img.shields.io/badge/Jetpack_Compose-UI-4285F4)
![Hilt](https://img.shields.io/badge/Hilt-DI-orange)
![Room](https://img.shields.io/badge/Room-DB-green)

- **UI**: Jetpack Compose
- **DI**: Hilt
- **DB**: Room
- **비동기**: Coroutines + Flow
- **빌드**: Version Catalog + Convention Plugin (build-logic)
- **아키텍처**: Clean Architecture (Multi-Module)

## 모듈 구조

```
root
├── app/                  # 진입점, 모듈 조립
├── build-logic/          # Convention Plugin 정의
│   └── src/main/kotlin/
│       ├── KotlinAndroid.kt     # Android + Kotlin 기본 설정
│       ├── HiltAndroid.kt       # Hilt 의존성 플러그인
│       ├── ComposeAndroid.kt    # Compose 설정 플러그인
│       ├── AndroidRoomPlugin.kt # Room 설정 플러그인
│       └── CoroutineAndroid.kt  # Coroutines 의존성 플러그인
├── data/                 # Repository 구현체, DI 모듈
├── database/             # Room Entity, DAO, Database
├── designSystem/         # 공통 UI 컴포넌트
└── feature/
    ├── login/            # 로그인 화면 (Screen + ViewModel + Navigation)
    ├── signup/           # 회원가입 화면
    ├── admin/            # 관리자 화면
    └── main/             # MainActivity (네비게이션 호스트)
```

## Convention Plugin 핵심

`build-logic` 모듈에 커스텀 Gradle 플러그인을 정의해, 각 feature/data 모듈이 아래처럼 간결하게 의존성을 선언할 수 있게 한다.

```kotlin
// feature 모듈의 build.gradle.kts
plugins {
    id("dantariun.android.feature")  // Compose + Hilt + Coroutine 한 번에 적용
}
```

## 주요 기능

- **멀티모듈**: feature별 독립적인 모듈 → 빌드 속도 개선, 명확한 의존성 경계
- **로그인 / 회원가입 / 관리자** 화면 Compose로 구현
- **UserRepository**: data 모듈에서 Room + DI로 사용자 데이터 관리
- **이벤트 기반 ViewModel**: `LoginEvent`, `SignUpEvent`로 단방향 이벤트 처리

## 시작하기

```bash
git clone https://github.com/dantariun/Demo-Hlit-Mvvm-Compose.git
```

Android Studio Hedgehog 이상, compileSdk 적용 버전은 `build-logic/Extension.kt` 참고.

```bash
./gradlew assembleDebug
```

## 참고

- Convention Plugin 패턴은 [Now in Android](https://github.com/android/nowinandroid) 아키텍처를 참고
- Room Schema 자동 export 경로: `app/schemas/`
