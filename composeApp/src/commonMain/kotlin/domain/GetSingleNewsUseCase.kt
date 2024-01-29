package domain

import com.ahmed.shaban.remote.Resource
import com.ahmed.shaban.remote.ResultWrapper
import com.ahmed.shaban.remote.requester.RequestHandler
import data.news.repo.NewsRepository
import kotlinx.coroutines.flow.flow
import model.NewsModel

class GetSingleNewsUseCase(
    private val repository: NewsRepository,
    private val requestHandler: RequestHandler
) : RequestHandler by requestHandler {
    suspend operator fun invoke(title: String, id: String) = flow {
        val results = makeApiRequest {
            repository.getSingleNews(title)
        }
        when (results) {
            is ResultWrapper.GenericError -> emit(Resource.Error(errorCode = results.errorCode))
            is ResultWrapper.Success -> emit(Resource.Success(data = results.value.findLast { it.articleId == id }))
        }
    }

}
