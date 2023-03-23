package rh.cola.api.application.ext;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import rh.cola.api.application.job.ChatbotTask;
import rh.cola.api.common.PropertyUtil;
import rh.cola.api.domain.ai.IOpenAI;
import rh.cola.api.domain.zsxq.IZsxqApi;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Name: TaskRegistrarAutoConfig
 * @Author: Cola
 * @Time: 2023/3/23 1:28
 * @Description: TaskRegistrarAutoConfig
 */
@Configuration
@EnableScheduling
@Slf4j
public class TaskRegistrarAutoConfig implements EnvironmentAware, SchedulingConfigurer {


    /**
     * 任务配置组
     */
    private Map<String, Map<String, Object>> taskGroupMap = new HashMap<>();

    @Resource
    private IZsxqApi zsxqApi;
    @Resource
    private IOpenAI openAIApi;

    /**
     * 初始化任务
     *
     * @param environment environment
     */
    @Override
    public void setEnvironment(Environment environment) {
        String prefix = "chatbot-api.";
        String launchListStr = environment.getProperty(prefix + "launchList");
        if (StringUtils.isEmpty(launchListStr)) return;
        for (String groupKey : launchListStr.split(",")) {
            Map<String, Object> taskGroupProps = PropertyUtil.handle(environment, prefix + groupKey, Map.class);
            taskGroupMap.put(groupKey, taskGroupProps);
        }
    }

    /**
     * 配置任务组
     *
     * @param taskRegistrar the registrar to be configured.
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        Set<String> taskGroups = taskGroupMap.keySet();
        for (String groupKey : taskGroups) {
            Map<String, Object> taskGroup = taskGroupMap.get(groupKey);
            String groupName = taskGroup.get("groupName").toString();
            String groupId = taskGroup.get("groupId").toString();
            String cookie = taskGroup.get("cookie").toString();
            String openAiKey = taskGroup.get("openAiKey").toString();
            String cronExpression = taskGroup.get("cronExpression").toString();
            boolean silenced = Boolean.parseBoolean(taskGroup.get("silenced").toString());
            log.info("创建任务 groupName：{} groupId：{} cronExpression：{}", groupName, groupId, cronExpression);
            /*添加任务*/
            taskRegistrar.addCronTask(new ChatbotTask(groupName, groupId, cookie, openAiKey, zsxqApi, openAIApi, silenced), cronExpression);
        }
    }

}

