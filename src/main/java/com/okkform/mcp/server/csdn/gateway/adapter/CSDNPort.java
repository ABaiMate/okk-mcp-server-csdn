package com.okkform.mcp.server.csdn.gateway.adapter;

import com.alibaba.fastjson.JSON;
import com.okkform.mcp.server.csdn.domain.adapter.ICSDNPort;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import com.okkform.mcp.server.csdn.gateway.ICSDNService;
import com.okkform.mcp.server.csdn.gateway.dto.ArticleRequestDTO;
import com.okkform.mcp.server.csdn.gateway.dto.ArticleResponseDTO;
import com.okkform.mcp.server.csdn.types.properties.CSDNApiProperties;
import com.okkform.mcp.server.csdn.types.utils.MarkdownConverter;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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

        Call<ArticleResponseDTO> call = this.icsdnService.saveArticle(articleRequestDTO);
        Response<ArticleResponseDTO> response = call.execute();

        log.info("请求CSDN发帖 \nreq:{} \nres:{}", JSON.toJSONString(articleRequestDTO), JSON.toJSONString(response));

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
