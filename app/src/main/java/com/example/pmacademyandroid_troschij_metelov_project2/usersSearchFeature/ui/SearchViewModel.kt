package com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.ui

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.frominstantsearch.data.repository.impl.GitHubUsersRepositoryImpl
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.data.GitHubUsersResponseToModelMapper
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.datasource.GitHubUsersRemoteDataSource
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.domain.GetGitHubUsersUseCase
import com.example.pmacademyandroid_troschij_metelov_project2.usersSearchFeature.domain.GitHubUsersModelToUIMapper

import com.google.gson.GsonBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.mapLatest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

sealed class SearchResult
class ValidResult(val result: List<GitHubUsersUIModel>) : SearchResult()
object EmptyResult : SearchResult()
object EmptyQuery : SearchResult()
object ErrorResult : SearchResult()
object TerminalError : SearchResult()

class SearchViewModel : ViewModel() {
    companion object {
        const val SEARCH_DELAY_MS = 500L
        const val MIN_QUERY_LENGTH = 3
    }

    private val getGitHubUsersUseCase: GetGitHubUsersUseCase = GetGitHubUsersUseCase(
        GitHubUsersRepositoryImpl(
            GitHubUsersRemoteDataSource(
                Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(
                        GsonConverterFactory.create(
                            GsonBuilder().setLenient().create()
                        )
                    )
                    .build()
            ),
            GitHubUsersResponseToModelMapper()
        ),
        GitHubUsersModelToUIMapper()
    )

    @ExperimentalCoroutinesApi
    @VisibleForTesting
    internal val queryChannel = BroadcastChannel<String>(Channel.CONFLATED)

    @FlowPreview
    @ExperimentalCoroutinesApi
    @VisibleForTesting
    internal val internalSearchResult = queryChannel
        .asFlow()
        .debounce(SEARCH_DELAY_MS)
        .mapLatest {
            try {
                if (it.length >= MIN_QUERY_LENGTH) {
                    val searchResult = withContext(Dispatchers.Main) {
                        getGitHubUsersUseCase.execute(it)//ТУТ МЫ ПЕРЕДАЛИ СТРОКУ НА ПОИСК
                    }
                    println("Search result: ${searchResult?.size} hits")

                    if (searchResult?.isNotEmpty() == true) {
                        ValidResult(searchResult)
                    } else {
                        EmptyResult
                    }
                } else {
                    EmptyQuery
                }
            } catch (e: Throwable) {
                if (e is CancellationException) {
                    println("Search was cancelled!")
                    throw e
                } else {
                    ErrorResult
                }
            }
        }
        .catch { emit(TerminalError) }

    @FlowPreview
    @ExperimentalCoroutinesApi
    val searchResult = internalSearchResult.asLiveData()
}
