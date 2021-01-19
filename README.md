## financial
-  android, kotlin, mvvm, jetPack, fundData
-  This is a small project of fund data

## Tech stack & Open-source libraries
- This project structure is based on skyDoves [pokedex](https://github.com/skydoves/Pokedex)
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- JetPack
  - Hilt - for di
  - Navigation - for navigate
  - DataBinding - ui consistency
  - LiveData - notify domain layer data to views.
  - Lifecycle - dispose of observing data when lifecycle state changes.
  - ViewModel - UI related data holder, lifecycle aware.
  - Room Persistence - construct a database using the abstract layer.
- Architecture
  - Clean architecture for architecture
- [Retrofit2 & OkHttp3] - construct the REST APIs and paging network data.
- [Sandwich]  - construct lightweight http API response and handling error responses.
- [Moshi] - A modern JSON library for Kotlin and Java.
- [desugar] - use java new feature on android, for date and time
- [WhatIf] - checking nullable object and empty collections more fluently.
- [Material-Components] - Material design components like ripple animation, cardView.
- [FullDraggableDrawer](https://github.com/PureWriter/FullDraggableDrawer) -  for sidebar navigation
- [SingleLiveEvent] - for one-shot command
