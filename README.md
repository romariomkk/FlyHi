# FlyHi app showcase
*Kotlin 100%*

<p>
<img src="animation.gif.mp4" alt="Here should be the screen-record" width="300" height="600">
<img src="animation_2.gif.mp4" alt="Here should be the screen-record" width="300" height="600">
</p>

### Architecture and 'glue':
* Multi-modular app
* Coroutines + MVVM + 2-way DataBinding
* <b>M</b> = UseCases + Repositories
* Hilt, with the use of Assisted Inject
* Room
* NavComponent
* ViewModel + Lifecycle + ktx
* Timber for logging, Stetho for quick db queries check-up

### Details and solutions
#### Domain
* The user selects amongst the accessible *destinations*, further considering the preferred date, number of passengers of different age, assuming the number of Children has to be lower or equal as of the Adults
* In case of the flights' availability under the provided criteria, the filtering data is complemented by one more constraint - the Price limit
* Upon click on the flight, the dialog with some additional info is shown
* The app consumes the publicly available API of Ryanair


#### Technical
* Single-module was the <i>initial</i> implementation, yet the consideration of showing personal capability to do good in <b>multi-modular</b> environment outweighed. To be enhanced! Most of the dependencies are provided by means of gradle <i>'api'</i> command in the <i>:core</i> module
* <b>Coroutines + Resource</b> class showed the power of synergy.
* <b>Room</b> used to store the static list of destinations, primordially retrieved from API.
* Retrieval from network is done upon launch of the app, if such hasn't been done before. If failed, user is prompted to launch the network when selecting destination.
* Additionally, for the purpose of quick text-oriented search, <b>FTS4</b> was implemented on top of Room.
* <b>Hilt</b> (Dagger latest upgrade) significantly reduces the amount of boilerplate DI code. Why not Koin then? DI is preferred to ServiceLocators - less itching-causing code is born and needs to be supported.
* The use and application of DTO and business models is segregated at the <b>UseCase</b> layer.
* <b>Navigation</b> in multi-modular app is leveraged by <b>nested graphs</b>, with arguments definition copied both in the parent graph and in the nested's startDestination. Yet effective, don't find it the most mind-blowing and minimally supportable(so far) solution.
* Used the recently released <b>Fragment Result API</b>. SharedViewModel was surely considered, but upon the morph to multi-module structure the cyclic dependency could be unavoidable.
* <b>2-way DataBinding</b> is applied wherever its efficiency is justified.