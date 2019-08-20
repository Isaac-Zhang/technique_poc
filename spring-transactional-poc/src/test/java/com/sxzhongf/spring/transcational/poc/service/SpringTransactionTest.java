package com.sxzhongf.spring.transcational.poc.service;

import com.alibaba.fastjson.JSON;
import com.sxzhongf.spring.transcational.poc.TransactionalApplication;
import com.sxzhongf.spring.transcational.poc.entity.TestTransactionEntity;
import com.sxzhongf.spring.transcational.poc.exception.CustomException;
import com.sxzhongf.spring.transcational.poc.service.impl.TestSpringTransactionImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

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
        List<TestTransactionEntity> entity = (List<TestTransactionEntity>) testSpringTransation.findByName("catchExceptionNoRollback");
        if (CollectionUtils.isEmpty(entity)) System.out.println("The database saved failed!");
        assert entity.get(0).getName().equals("catchExceptionNoRollback");
        System.out.println("The database saved : Runtime " + JSON.toJSONString(entity));
    }

    @Test
    public void testCatchNonExceptionNoRollback() throws CustomException {
        // 执行函数
        testSpringTransation.catchNonRuntimeExceptionNoRollback();
        List<TestTransactionEntity> entity = (List<TestTransactionEntity>) testSpringTransation.findByName("catchNonRuntimeExceptionNoRollback");
        if (CollectionUtils.isEmpty(entity)) System.out.println("The database saved failed!");
        assert entity.get(0).getName().equals("catchNonRuntimeExceptionNoRollback");
        System.out.println("The database saved : NonRuntime " + JSON.toJSONString(entity));
    }

    @Test
    public void testCatchRuntimeCanRollback() {
        testSpringTransation.catchRuntimeCanRollback();
    }

    @Test
    public void testSpecifiedExceptionCanRollback() throws CustomException {
        testSpringTransation.specifiedExceptionCanRollback();
    }

    @Test
    public void testSetRollbackOnlyCanRollback() {
        testSpringTransation.setRollbackOnlyCanRollback();
    }

    @Test
    public void testNonTransactionNoRollback() {
        testSpringTransation.nonTransactionNoRollback();
    }
}
