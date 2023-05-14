package spring;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
public class InitOrderTest {

    /**
     * 接口集合的方式注入。元素顺序为order指定的加载顺序
     */
    @Autowired
    private List<OrderTest> orderTests;

    @Test
    public void initOrderTest() {
        OrderTest orderTest = orderTests.get(1);
        System.out.println("orderTest = " + orderTest);
    }


    @Order(0)
    @Component
    public class OrderTest0 implements OrderTest {

        private int order = 0;

        public OrderTest0() {
            System.out.println("order = " + order);
        }
    }

    @Order(1)
    @Component
    public class OrderTest1 implements OrderTest {

        private int order = 0;

        public OrderTest1() {
            System.out.println("order = " + order);
        }
    }

    public interface OrderTest {
    }

}