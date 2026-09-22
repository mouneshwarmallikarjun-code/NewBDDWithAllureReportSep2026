package com.orangehrm.utils;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

import java.io.Serializable;

@Plugin(name = "AllureLogCaptor", category = "Core", elementType = "appender", printObject = true)
public class AllureLogCaptor extends AbstractAppender {

    private static final ThreadLocal<StringBuilder> LOG_BUFFER =
            ThreadLocal.withInitial(StringBuilder::new);

    protected AllureLogCaptor(String name, Filter filter, Layout<? extends Serializable> layout) {
        super(name, filter, layout, false, null);
    }

    @PluginFactory
    public static AllureLogCaptor createAppender(
            @PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") Filter filter) {

        if (layout == null) {
            layout = PatternLayout.newBuilder()
                    .withPattern("%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n")
                    .build();
        }
        return new AllureLogCaptor(name, filter, layout);
    }

    @Override
    public void append(LogEvent event) {
        LOG_BUFFER.get().append(new String(getLayout().toByteArray(event), java.nio.charset.StandardCharsets.UTF_8));
    }

    public static String getLogsAndClear() {
        String logs = LOG_BUFFER.get().toString();
        LOG_BUFFER.get().setLength(0);
        return logs;
    }
}