import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//data class User1(
//    val name: String,
//    val age: Int
//) {
//    companion object {
//        const val id: String = "id"
//    }
//}
fun main() {

    runBlocking {
        launch {
            delay(1000)
            println("Hell from coroutines")
        }
        println("Main starts")
    }

//    val coldFFlow = flow {
//        println("Starting cold flow...")
//        emit(1)
//        emit(2)
//        emit(3)
//    }

//    runBlocking {
//        coldFFlow.collect {
//            println("Collector 1: $it")
//        }
//        coldFFlow.collect {
//            println("Collector 2: $it")
//        }
//    }

//    val _shareFlow = MutableSharedFlow<Int>()
//    val sharedFlow = _shareFlow.asSharedFlow()
//
//    runBlocking {
//        launch {
//            listOf(1,2,3).forEach {
//                delay(100)
//                _shareFlow.emit(it)
//            }
//        }
//        delay(150)
//        sharedFlow.collect {
//            println("Collector 1: $it")
//        }
//    }

//    val _stateFlow = MutableStateFlow(1)
//    val stateFlow = _stateFlow.asStateFlow()
//
//    runBlocking {
//        launch {
//            listOf(1,2,3).forEach {
//                delay(100)
//                _stateFlow.emit(it)
//            }
//        }
//        delay(150)
//        stateFlow.collect {
//            println("Collector 2: $it")
//        }
//    }
}

//fun main() = runBlocking {
//    val api = FakeApi()
//    val repo = UserRepository(api = api)
//    val viewModel = UserViewModel(repo = repo)
//    viewModel.load()
//    viewModel.state.take(2).collect { state ->
//        println("UI State: $state")
//    }
//}

data class User(val id: String, val name: String)
data class UserProfile(val name: String, val balance: Int)

class FakeApi {
    suspend fun getUser(): User {
        delay(300)
        return User("123","Alice")
    }

    suspend fun getBalance(): Int {
        delay(200)
        return 500
    }
}

class UserRepository(private val api: FakeApi) {
    fun getUserFlow(): Flow<User> = flow {
        emit(api.getUser())
    }

    fun getUserBalance(): Flow<Int> = flow {
        emit(api.getBalance())
    }

    fun getUserProfile(): Flow<UserProfile> {
        return getUserFlow().combine(getUserBalance()) { user, balance ->
            UserProfile(user.name, balance)
        }
    }
}

sealed class UiState {
    object Loading: UiState()
    data class Success(val data: UserProfile): UiState()
    data class Error(val msg: String) : UiState()
}

class UserViewModel(private val repo: UserRepository) {
    private val _state = MutableStateFlow<UiState>(UiState.Loading)
    val state = _state.asStateFlow()

    fun load() {
        CoroutineScope(Dispatchers.Default).launch {
            repo.getUserProfile()
                .catch { e ->
                    _state.value = UiState.Error(e.message ?: "Unknow error")
                }
                .collect { profile ->
                    _state.value = UiState.Success(profile)
                }
        }
    }
}