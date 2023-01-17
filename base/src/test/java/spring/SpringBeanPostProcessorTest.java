package spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.PriorityOrdered;
import org.springframework.stereotype.Component;

@ComponentScan(basePackages = "spring")
public class SpringBeanPostProcessorTest {


    @Component
    public static class BeanDefinitionRegistryPostProcessorTest implements BeanDefinitionRegistryPostProcessor, PriorityOrdered {
        /**
         * 传入的为所有的beanDefinition,进行bean初始化之前进行调用，实现PriorityOrdered接口的由上层单独处理
         */
        @Override
        public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
            // 新增
            BeanDefinition bd = new GenericBeanDefinition();
            bd.setBeanClassName("spring.SpringBeanPostProcessorTest$Student");
            registry.registerBeanDefinition("student", bd);
            // 查询和修改
            BeanDefinition student = registry.getBeanDefinition("student");
            // 删除
            registry.removeBeanDefinition("student");
        }

        @Override
        public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
            System.out.println("============= BeanDefinitionRegistryPostProcessorTest invoke beanFactory =========");
        }

        @Override
        public int getOrder() {
            return 1;
        }
    }


    public class Student {
        private String name = "zhangsan";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringBeanPostProcessorTest.class);
        Student bean = context.getBean(Student.class);
        System.out.println("================" + bean.getName() + "===================");
    }

}
