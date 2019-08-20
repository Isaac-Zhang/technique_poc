package com.sxzhongf.spring.transcational.poc.service;

import com.sxzhongf.spring.transcational.poc.TransactionalApplication;
import com.sxzhongf.spring.transcational.poc.service.impl.SpringForTransactionTestImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * SpringTransactionTest for test {@link SpringForTransactionTestImpl}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TransactionalApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public class SpringForTransactionImplTest {

    @Autowired
    private SpringForTransactionTestImpl springForTransactionTest;

    @Test
    public void testSpringTransaction() {
        springForTransactionTest.springTransaction();
    }
}
