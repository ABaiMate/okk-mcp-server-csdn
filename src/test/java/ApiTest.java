import com.alibaba.fastjson.JSON;
import com.okkform.mcp.server.csdn.domain.adapter.ICSDNPort;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import com.okkform.mcp.server.csdn.gateway.ICSDNService;
import com.okkform.mcp.server.csdn.gateway.dto.ArticleRequestDTO;
import com.okkform.mcp.server.csdn.gateway.dto.ArticleResponseDTO;
import com.okkform.mcp.server.csdn.types.properties.CSDNApiProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Collections;

/**
 * @author can
 * @description
 * @since 2025/11/10
 */
@Slf4j
@SpringBootTest(classes = com.okkform.mcp.server.csdn.McpServerApplication.class)
public class ApiTest {

    @Resource
    private ICSDNPort icsdnPort;

    @Resource
    private ICSDNService icsdnService;

    @Resource
    private CSDNApiProperties csdnApiProperties;

    @Test
    public void test() throws IOException {
        ArticleRequestDTO request = new ArticleRequestDTO();

// 必填或常用字段
        request.setTitle("测试文章标题01");
        request.setContent("<h1>测试文章内容</h1><p>这是一篇测试文章</p>");
        request.setDescription("这是一篇测试文章的描述");
        request.setTags("测试,文章");
        request.setCategories("后端");
        request.setType("original");           // 原创
        request.setReadType("public");        // 公开阅读
        request.setStatus(0);                 // 草稿状态（假设 0=草稿，1=发布）
        request.setSource("pc_mdeditor");     // 来源：PC 端 Markdown 编辑器

// 其他辅助字段（按典型默认值设置）
        request.setArticleId("");             // 新建时不传 ID
        request.setReason("");
        request.setOriginalLink("");
        request.setAuthorizedStatus(false);
        request.setCheckOriginal(false);
        request.setCreationStatement(0);      // 无创作声明
        request.setNotAutoSaved(1);           // 1 表示“不是自动保存”（即用户主动保存）
        request.setCreatorActivityId("");
        request.setCoverImages(Collections.emptyList()); // 无封面图
        request.setCoverType(1);              // 封面类型：1=默认/自动生成
        request.setVoteId(0);                 // 无关联投票
        request.setResourceId("");
        request.setScheduledTime(0);          // 非定时发布
        request.setIsNew(1);                  // 1=新文章
        request.setSyncGitCode(0);            // 不同步到 Git

        Call<ArticleResponseDTO> call = icsdnService.saveArticle(request, csdnApiProperties.getCookie());
        log.info(JSON.toJSONString(request));
        Response<ArticleResponseDTO> response = call.execute();
        log.info(JSON.toJSONString(response));

        log.info("CSDN文章保存成功");
    }

    /*
    set-cookie uuid_tt_dd=10_30706389210-1764054514797-232813; Max-Age=34560000; Path=/; Domain=.csdn.net;
set-cookie dc_session_id=10_1764054514797.604240; Max-Age=34560000; Path=/; Domain=.csdn.net;
     */
}