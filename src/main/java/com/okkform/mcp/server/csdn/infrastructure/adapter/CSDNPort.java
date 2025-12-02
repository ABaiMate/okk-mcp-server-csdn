package com.okkform.mcp.server.csdn.infrastructure.adapter;

import com.alibaba.fastjson.JSON;
import com.okkform.mcp.server.csdn.domain.adapter.ICSDNPort;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import com.okkform.mcp.server.csdn.infrastructure.gateway.ICSDNService;
import com.okkform.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import com.okkform.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import com.okkform.mcp.server.csdn.types.properties.CSDNApiProperties;
import com.okkform.mcp.server.csdn.types.utils.MarkdownConverter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Slf4j
@Component
public class CSDNPort implements ICSDNPort {

    @Resource
    private ICSDNService icsdnService;

    @Resource
    private CSDNApiProperties csdnApiProperties;

    @Override
    public ArticleFunctionResponse writeArticle(ArticleFunctionRequest articleFunctionRequest) throws IOException {
        String cookie = this.csdnApiProperties.getCookie();
        String categories = this.csdnApiProperties.getCategories();

        ArticleRequestDTO articleRequestDTO = new ArticleRequestDTO();
        articleRequestDTO.setTitle(articleFunctionRequest.getTitle());
        articleRequestDTO.setContent(MarkdownConverter.convertToHtml(articleFunctionRequest.getContent()));
        articleRequestDTO.setTags(articleFunctionRequest.getTags());
        articleRequestDTO.setDescription(articleFunctionRequest.getDescription());
        articleRequestDTO.setCategories(categories);
        articleRequestDTO.setType("original");
        articleRequestDTO.setReadType("public");
        articleRequestDTO.setStatus(0);
        articleRequestDTO.setSource("pc_mdeditor");

        Call<ArticleResponseDTO> call = this.icsdnService.saveArticle(articleRequestDTO, cookie);
        Request request = call.request();
        log.info("CSDN发帖请求 req={}", JSON.toJSONString(request));
        Response<ArticleResponseDTO> response = call.execute();

        if (!response.isSuccessful()) {
            // 失败时才打印错误信息，且只打印字符串
            String errorBody = response.errorBody() != null ? response.errorBody().string() : "null";
            log.error("CSDN发帖失败 code={} \nreq={} \nerrorBody={}",
                    response.code(),
                    JSON.toJSONString(articleRequestDTO),
                    errorBody);
        } else {
            // 成功只打印关键信息
            log.info("CSDN发帖成功 code={} articleId={}",
                    response.code(),
                    response.body() != null ? response.body().getData() : "null");
        }

        if (response.isSuccessful()) {
            ArticleResponseDTO body = response.body();
            if (body == null) {
                return null;
            }

            ArticleResponseDTO.ArticleData articleData = body.getData();
            return ArticleFunctionResponse.builder()
                    .code(body.getCode())
                    .msg(body.getMsg())
                    .articleData(ArticleFunctionResponse.ArticleData.builder()
                            .id(articleData.getArticleId())
                            .url(articleData.getUrl())
                            .qrcode(articleData.getQrcode())
                            .title(articleData.getTitle())
                            .description(articleData.getDescription())
                            .build())
                    .build();
        }

        return null;
    }
}
