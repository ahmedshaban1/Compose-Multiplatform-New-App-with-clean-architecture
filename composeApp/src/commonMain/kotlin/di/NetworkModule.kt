package di

import com.ahmed.shaban.remote.requester.RequestHandler
import com.ahmed.shaban.remote.requester.RequestHandlerImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                        coerceInputValues = true
                    }
                )
            }
        }
    }

    single<RequestHandler> {
        RequestHandlerImpl()
    }
}
