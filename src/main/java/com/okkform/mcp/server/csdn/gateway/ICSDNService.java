package com.okkform.mcp.server.csdn.gateway;

import com.okkform.mcp.server.csdn.gateway.dto.ArticleRequestDTO;
import com.okkform.mcp.server.csdn.gateway.dto.ArticleResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ICSDNService {

    @Headers({
            "accept: application/json, text/plain, */*",
            "accept-language: zh-CN,zh;q=0.9,en;q=0.8",
            "content-type: application/json;",
            "origin: https://mp.csdn.net",
            "priority: u=1, i",
            "referer: https://mp.csdn.net/",
            "sec-ch-ua: \"Chromium\";v=\"140\", \"Not=A?Brand\";v=\"24\", \"Google Chrome\";v=\"140\"",
            "sec-ch-ua-mobile: ?0",
            "sec-ch-ua-platform: \"macOS\"",
            "sec-fetch-dest: empty",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "user-agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/140.0.0.0 Safari/537.36",
            "x-ca-key: 203803574",
            "x-ca-nonce: 55c8c351-d1bc-4dc4-bce4-8abca37241fb",
            "x-ca-signature: Q13EHr2JDNnKG1Xi93+TPazQSJoZpFtsj4zdFhPusBU=",
            "x-ca-signature-headers: x-ca-key,x-ca-nonce"
    })
    @POST("https://bizapi.csdn.net/blog-console-api/v1/postedit/saveArticle")
    Call<ArticleResponseDTO> saveArticle(@Body ArticleRequestDTO articleRequestDTO, @Header("cookie") String cookie);

}
