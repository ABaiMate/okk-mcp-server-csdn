package com.okkform.mcp.server.csdn.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author can
 * @description
 * @since 2025/9/16
 */
@NoArgsConstructor
@Data
public class ArticleResponseDTO {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("traceId")
    private String traceId;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("msg")
    private String msg;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
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
