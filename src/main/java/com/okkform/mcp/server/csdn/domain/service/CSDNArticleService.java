package com.okkform.mcp.server.csdn.domain.service;

import com.okkform.mcp.server.csdn.domain.adapter.ICSDNPort;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class CSDNArticleService  {

    @Resource
    private ICSDNPort iCSDNPort;

    @Tool(description = "CSDN Article publish")
    public ArticleFunctionResponse saveArticle(ArticleFunctionRequest articleFunctionRequest) throws IOException {
        log.info("CSDN文章保存开始");
        return this.iCSDNPort.writeArticle(articleFunctionRequest);
    }

}
