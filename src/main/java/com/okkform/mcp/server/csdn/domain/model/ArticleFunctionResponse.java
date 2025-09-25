package com.okkform.mcp.server.csdn.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author can
 * @description
 * @since 2025/9/16
 */
@Data
public class ArticleFunctionResponse {

    private Integer code;

    private String msg;

    private ArticleData articleData;

    public static class ArticleData {
        @JsonProperty("url")
        private String url;
        @JsonProperty("article_id")
        private Integer articleId;
        @JsonProperty("title")
        private String title;
        @JsonProperty("description")
        private String description;
    }

}
