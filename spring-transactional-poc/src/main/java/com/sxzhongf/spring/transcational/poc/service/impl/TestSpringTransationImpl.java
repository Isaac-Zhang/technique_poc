package com.sxzhongf.spring.transcational.poc.service.impl;

import com.sxzhongf.spring.transcational.poc.dao.TestTransactionDao;
import com.sxzhongf.spring.transcational.poc.entity.TestTransactionEntity;
import com.sxzhongf.spring.transcational.poc.exception.CustomException;
import com.sxzhongf.spring.transcational.poc.service.ITestSpringTransation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * TestSpringTransationImpl for TODO
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
@Slf4j
@Service
public class TestSpringTransationImpl implements ITestSpringTransation {

    @Autowired
    private TestTransactionDao transactionDao;

    @Override
    public void catchExceptionNoRollback() {
        try {
            transactionDao.save(new TestTransactionEntity().builder().name("Isaac").build());
            throw new RuntimeException();
        } catch (RuntimeException ex) {
            //主动捕获异常以后，程序认为没有发生错误，就不会主动回滚事务
            log.error("TestSpringTransationImpl :: catchExceptionNoRollback error msg : {}", ex.getMessage());
        }
    }

    @Override
    public void catchNonRuntimeExceptionNoRollback() throws CustomException {

    }
}
