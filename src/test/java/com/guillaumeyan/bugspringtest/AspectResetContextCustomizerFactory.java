package com.guillaumeyan.bugspringtest;

import org.springframework.beans.factory.aspectj.AnnotationBeanConfigurerAspect;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfigurationAttributes;
import org.springframework.test.context.ContextCustomizer;
import org.springframework.test.context.ContextCustomizerFactory;
import org.springframework.test.context.MergedContextConfiguration;

import java.util.List;

final class AspectResetContextCustomizerFactory implements ContextCustomizerFactory {

    private static final AspectResetContextCustomizer customizer = new AspectResetContextCustomizer();

    @Override
    public ContextCustomizer createContextCustomizer(Class<?> testClass,
                                                     List<ContextConfigurationAttributes> configAttributes) {
        return customizer;
    }

    static class AspectResetContextCustomizer implements ContextCustomizer {

        @Override
        public void customizeContext(ConfigurableApplicationContext context, MergedContextConfiguration mergedConfig) {
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            AnnotationBeanConfigurerAspect.aspectOf().setBeanFactory(beanFactory);
        }

    }

}