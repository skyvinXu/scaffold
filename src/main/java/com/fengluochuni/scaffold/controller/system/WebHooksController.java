package com.fengluochuni.scaffold.controller.system;

import com.fengluochuni.scaffold.commons.base.BaseController;
import com.fengluochuni.scaffold.commons.scan.SpringUtils;
import com.fengluochuni.scaffold.commons.utils.JsonUtils;
import com.fengluochuni.scaffold.commons.utils.StringUtils;
import com.fengluochuni.scaffold.event.WebHooksEvent;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * WebHooks 自动更新部署
 *
 * @author zhixuan.wang
 * @since 2015/10/1 14:51
 */
@Controller
public class WebHooksController extends BaseController {
    /**
     * git@osc WebHooks 设置
     * WIKI: http://git.oschina.net/oschina/git-osc/wikis/WebHook-%E4%BD%BF%E7%94%A8%E7%AE%80%E4%BB%8B
     * @param request hook json字符串
     * @return jsonBean
     */
    @PostMapping("webhooks")
    @ResponseBody
    public Object hooks(HttpServletRequest request) {
        String hook = request.getParameter("hook");
        if (StringUtils.isBlank(hook)) {
            return renderError("json hook isBlank!");
        }
        logger.info("webhooks json: {}", hook);
        Map<String, Object> hookMap = JsonUtils.parse(hook, Map.class);
        // 发送事件 ThreadPoolTaskExecutor
        SpringUtils.publishEvent(new WebHooksEvent(hookMap));
        return renderSuccess("ok");
    }
}
