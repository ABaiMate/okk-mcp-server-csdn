import com.alibaba.fastjson.JSON;
import com.okkform.mcp.server.csdn.domain.adapter.ICSDNPort;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionRequest;
import com.okkform.mcp.server.csdn.domain.model.ArticleFunctionResponse;
import com.okkform.mcp.server.csdn.domain.service.CSDNArticleService;
import com.okkform.mcp.server.csdn.infrastructure.gateway.ICSDNService;
import com.okkform.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import com.okkform.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
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

    @Resource
    private CSDNArticleService csdnArticleService;

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

    @Test
    public void test_domain_saveArticle() throws IOException {
        String json = "{\"content\":\"<h2>场景：</h2>\\n<p>在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。</p>\\n<p><strong>面试官</strong>：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？</p>\\n<p><strong>程序员小张</strong>：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？</p>\\n<p><strong>面试官</strong>：嗯，第二个问题，请说说HashMap的工作原理。</p>\\n<p><strong>程序员小张</strong>：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……</p>\\n<p><strong>面试官</strong>：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？</p>\\n<p><strong>程序员小张</strong>：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……</p>\\n<p><strong>面试官</strong>：好，今天的问题就问到这里。回去等通知吧。</p>\\n<h2>答案解析：</h2>\\n<ol>\\n<li>\\n<p><strong>JVM内存管理</strong>：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。</p>\\n</li>\\n<li>\\n<p><strong>HashMap原理</strong>：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。</p>\\n</li>\\n<li>\\n<p><strong>Spring与SpringBoot区别</strong>：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。</p>\\n</li>\\n</ol>\\n\",\"cover_images\":[],\"cover_type\":0,\"description\":\"在互联网大厂的面试中，严肃的面试官与搞笑的程序员上演了一场精彩的对话。面试官提出Java核心知识、HashMap、Spring等问题，程序员则用幽默的方式作答。本文不仅展现了轻松的面试氛围，还附上了详细的技术问题答案解析，帮助读者更好地理解相关知识。\",\"is_new\":1,\"level\":\"0\",\"markdowncontent\":\"## 场景：\\n\\n在某互联网大厂的面试室，一位严肃的面试官正准备提问，而对面坐着一位看似紧张却又想显得轻松的程序员小张。\\n\\n**面试官**：我们先来聊聊Java核心知识。第一个问题，Java中的JVM是如何管理内存的？\\n\\n**程序员小张**：哦，这个简单！JVM就像一个巨大的购物车，负责把所有的变量都放进去，呃……然后就……管理起来？\\n\\n**面试官**：嗯，第二个问题，请说说HashMap的工作原理。\\n\\n**程序员小张**：HashMap嘛，就是……呃，一个很大的箱子，大家都往里面扔东西，有时候会打架……\\n\\n**面试官**：那么第三个问题，能不能讲讲Spring和SpringBoot的区别？\\n\\n**程序员小张**：Spring是……呃，春天？SpringBoot就是穿靴子的春天嘛！哈哈……\\n\\n**面试官**：好，今天的问题就问到这里。回去等通知吧。\\n\\n## 答案解析：\\n\\n1. **JVM内存管理**：JVM内存管理包括堆内存和栈内存，堆内存用于存储对象实例，栈内存用于执行线程时的栈帧。\\n\\n2. **HashMap原理**：HashMap通过哈希函数将键映射到对应的值，并通过链表解决哈希冲突。\\n\\n3. **Spring与SpringBoot区别**：Spring是一个大型应用框架，而SpringBoot是基于Spring的快速开发套件，简化了Spring应用的配置。\",\"not_auto_saved\":\"0\",\"pubStatus\":\"draft\",\"readType\":\"public\",\"resource_id\":\"\",\"resource_url\":\"\",\"source\":\"pc_mdeditor\",\"status\":0,\"sync_git_code\":0,\"tags\":\"Java,面试,互联网,程序员,Spring,SpringBoot,HashMap,JVM\",\"title\":\"互联网大厂Java面试：严肃面试官与搞笑程序员的对决\",\"vote_id\":0}";
        ArticleFunctionRequest request = JSON.parseObject(json, ArticleFunctionRequest.class);
        ArticleFunctionResponse response = csdnArticleService.saveArticle(request);
        log.info("测试结果:{}", JSON.toJSONString(response));
    }

    /*
    set-cookie uuid_tt_dd=10_30706389210-1764054514797-232813; Max-Age=34560000; Path=/; Domain=.csdn.net;
set-cookie dc_session_id=10_1764054514797.604240; Max-Age=34560000; Path=/; Domain=.csdn.net;
     */
}