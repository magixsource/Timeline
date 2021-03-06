package com.ctlok.springframework.web.servlet.view.rythm.log;

import org.rythmengine.extension.ILoggerFactory;
import org.rythmengine.logger.ILogger;
import org.slf4j.LoggerFactory;

/**
 * @author Lawrence Cheung
 *
 */
public class RythmLoggerFactory implements ILoggerFactory {

    @Override
    public ILogger getLogger(Class<?> clazz) {
        return new RythmSlf4jLogger(LoggerFactory.getLogger(clazz));
    }

}
