package cn.bugstack.mcp.server.csdn;

import cn.bugstack.mcp.server.csdn.domain.service.CSDNArticleService;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.ICSDNService;
import cn.bugstack.mcp.server.csdn.types.properties.CSDNApiProperties;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.ai.tool.method.MethodToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@SpringBootApplication
public class McpServerApplication implements CommandLineRunner {

    private final Logger log = LoggerFactory.getLogger(McpServerApplication.class);

    @Resource
    private CSDNApiProperties csdnApiProperties;

    public static void main(String[] args) {
        SpringApplication.run(McpServerApplication.class, args);
    }

    @Bean
    public ICSDNService csdnService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bizapi.csdn.net/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit.create(ICSDNService.class);
    }

    @Bean
    public ToolCallbackProvider csdnTools(CSDNArticleService csdnArticleService) {
        return MethodToolCallbackProvider.builder().toolObjects(csdnArticleService).build();
    }

    @Override
    public void run(String... args) throws Exception {
        log.info("check csdn cookie ...");
        if (csdnApiProperties.getCookie() == null) {
            log.warn("csdn cookie key is null, please set it in application.yml");
        } else {
            log.info("csdn cookie  key is {}", csdnApiProperties.getCookie());
        }
    }

}
