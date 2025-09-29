package com.okkform.mcp.server.csdn.domain.adapter;

import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionResponse;

import java.io.IOException;

/**
 * @author can
 * @description CSDN 保存文章接口
 * @since 2025/9/16
 */
public interface ICSDNPort {

    ArticleFunctionResponse writeArticle(ArticleFunctionRequest articleFunctionRequest) throws IOException;
}
