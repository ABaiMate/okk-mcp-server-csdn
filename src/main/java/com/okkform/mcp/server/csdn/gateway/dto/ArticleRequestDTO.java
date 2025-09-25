package com.okkform.mcp.server.csdn.gateway.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author can
 * @description
 * @since 2025/9/15
 */
@NoArgsConstructor
@Data
public class ArticleRequestDTO {

    @JsonProperty("article_id")
    private String articleId;
    @JsonProperty("title")
    private String title;
    @JsonProperty("description")
    private String description;
    @JsonProperty("content")
    private String content;
    @JsonProperty("tags")
    private String tags;
    @JsonProperty("categories")
    private String categories;
    @JsonProperty("type")
    private String type;
    @JsonProperty("status")
    private Integer status;
    @JsonProperty("read_type")
    private String readType;
    @JsonProperty("reason")
    private String reason;
    @JsonProperty("original_link")
    private String originalLink;
    @JsonProperty("authorized_status")
    private Boolean authorizedStatus;
    @JsonProperty("check_original")
    private Boolean checkOriginal;
    @JsonProperty("source")
    private String source;
    @JsonProperty("not_auto_saved")
    private Integer notAutoSaved;
    @JsonProperty("creator_activity_id")
    private String creatorActivityId;
    @JsonProperty("cover_images")
    private List<?> coverImages;
    @JsonProperty("cover_type")
    private Integer coverType;
    @JsonProperty("vote_id")
    private Integer voteId;
    @JsonProperty("resource_id")
    private String resourceId;
    @JsonProperty("scheduled_time")
    private Integer scheduledTime;
    @JsonProperty("is_new")
    private Integer isNew;
    @JsonProperty("sync_git_code")
    private Integer syncGitCode;
}
