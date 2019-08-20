package com.sxzhongf.spring.transcational.poc.service;

import com.alibaba.fastjson.JSON;
import com.sxzhongf.spring.transcational.poc.TransactionalApplication;
import com.sxzhongf.spring.transcational.poc.dao.TestTransactionDao;
import com.sxzhongf.spring.transcational.poc.entity.TestTransactionEntity;
import com.sxzhongf.spring.transcational.poc.exception.CustomException;
import com.sxzhongf.spring.transcational.poc.service.impl.TestSpringTransactionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * SpringTransactionTest for test {@link TestSpringTransactionImpl}
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = TransactionalApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.NONE
)
public class SpringTransactionTest {

    @Autowired
    private ITestSpringTransation testSpringTransation;

    @Test
    public void testCatchExceptionNoRollback() {
        // 执行函数
        testSpringTransation.catchExceptionNoRollback();
        List<TestTransactionEntity> entity = (List<TestTransactionEntity>) testSpringTransation.findByName("Isaac Runtime");
        assert entity.get(0).getName().equals("Isaac Runtime");
        System.out.println("The database saved : Runtime " + JSON.toJSONString(entity));
    }

    @Test
    public void testCatchNonExceptionNoRollback() throws CustomException {
        // 执行函数
        testSpringTransation.catchNonRuntimeExceptionNoRollback();
        List<TestTransactionEntity> entity = (List<TestTransactionEntity>) testSpringTransation.findByName("Isaac NonRuntime");
        assert entity.get(0).getName().equals("Isaac NonRuntime");
        System.out.println("The database saved : NonRuntime " + JSON.toJSONString(entity));
    }
}
