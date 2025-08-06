package cn.bugstack.mcp.server.csdn.infrastructure.gateway;

import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleRequestDTO;
import cn.bugstack.mcp.server.csdn.infrastructure.gateway.dto.ArticleResponseDTO;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ICSDNService {

    @Headers({
            "accept: */*",
            "accept-language: zh-CN,zh;q=0.9",
            "content-type: application/json",
            "dnt: 1",
            "origin: https://editor.csdn.net",
            "priority: u=1, i",
            "referer: https://editor.csdn.net/",
            "sec-ch-ua: \"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Google Chrome\";v=\"138\"",
            "sec-ch-ua-mobile: ?0",
            "sec-ch-ua-platform: \"Windows\"",
            "sec-fetch-dest: empty",
            "sec-fetch-mode: cors",
            "sec-fetch-site: same-site",
            "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36",
            "x-ca-key: 203803574",
            "x-ca-nonce: e1b0b136-53ac-4ea7-b480-bdf30cea9c9b",
            "x-ca-signature: VZ/2YOryUNMMQ7hZt0SDpMQXbI25jKk3CeHu7f/b7rM=",
            "x-ca-signature-headers: x-ca-key,x-ca-nonce",
    })
    @POST("/blog-console-api/v3/mdeditor/saveArticle")
    Call<ArticleResponseDTO> saveArticle(@Body ArticleRequestDTO request, @Header("Cookie") String cookieValue);

}
