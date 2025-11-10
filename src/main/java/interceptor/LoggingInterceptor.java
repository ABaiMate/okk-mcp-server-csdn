package interceptor;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

@Slf4j
public class LoggingInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        
        // 打印请求信息
        log.info("Retrofit"+"请求URL: " + request.url());
        log.info("Retrofit"+"请求方法: " + request.method());
        
        // 打印请求头
        log.info("Retrofit"+"请求Headers:");
        for (int i = 0, size = request.headers().size(); i < size; i++) {
            log.info("Retrofit"+request.headers().name(i) + ": " + request.headers().value(i));
        }
        
        // 继续执行请求
        return chain.proceed(request);
    }
}