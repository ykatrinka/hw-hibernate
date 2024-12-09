package ru.clevertec.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class LoggingHandlerBeanPostProcessor implements BeanPostProcessor {

    private final Map<String, Class> loggingBeans = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Class<?> beanClass = bean.getClass();

        if (beanClass.isAnnotationPresent(Logging.class)) {
            loggingBeans.put(beanName, beanClass);
            return bean;
        }

        if (Arrays.stream(beanClass.getMethods())
                .anyMatch(method -> method.isAnnotationPresent(Logging.class))) {
            loggingBeans.put(beanName, beanClass);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        Class beanClass = loggingBeans.get(beanName);

        if (beanClass != null) {
            return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(),
                    (proxy, method, args) -> {

                        if (beanClass.isAnnotationPresent(Logging.class)
                                || method.isAnnotationPresent(Logging.class)) {
                            log.info("Input parameters {} :: {}", method.getName(), args);
                            Object retVal = method.invoke(bean, args);
                            log.info("Output parameters {}", retVal);
                            return retVal;
                        } else {
                            return method.invoke(bean, args);
                        }
                    });

        }
        return bean;
    }
}
