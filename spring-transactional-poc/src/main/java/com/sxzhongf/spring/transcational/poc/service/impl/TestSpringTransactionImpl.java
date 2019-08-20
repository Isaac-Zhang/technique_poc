package com.sxzhongf.spring.transcational.poc.service.impl;

import com.sxzhongf.spring.transcational.poc.dao.TestTransactionDao;
import com.sxzhongf.spring.transcational.poc.entity.TestTransactionEntity;
import com.sxzhongf.spring.transcational.poc.exception.CustomException;
import com.sxzhongf.spring.transcational.poc.service.ITestSpringTransation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.Collection;

/**
 * TestSpringTransactionImpl for 测试事务service, 事务不能出现在controller中
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
@Slf4j
@Service
public class TestSpringTransactionImpl implements ITestSpringTransation {

    private final TestTransactionDao transactionDao;

    @Autowired
    public TestSpringTransactionImpl(TestTransactionDao transactionDao) {
        this.transactionDao = transactionDao;
    }

    @Override
    public Collection<TestTransactionEntity> findByName(String name) {
        return transactionDao.findByName(name);
    }

    @Override
    @Transactional
    public void catchExceptionNoRollback() {
        try {
            transactionDao.save(TestTransactionEntity.builder().name("catchExceptionNoRollback").build());
            throw new RuntimeException();
        } catch (Exception ex) {
            //主动捕获异常以后，程序认为没有发生错误，就不会主动回滚事务
            log.error("TestSpringTransactionImpl :: catchExceptionNoRollback error msg : {}", ex.getCause());

            //因为不会自动回滚，如果我们需要回滚事务，需要设置当前事务状态
            //尽量不要这么实现（因为将Spring的编码与业务代码耦合在了一起）
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
        }
    }

    @Override
    @Transactional
    public void catchNonRuntimeExceptionNoRollback() throws CustomException {
        try {
            transactionDao.save(TestTransactionEntity.builder().name("catchNonRuntimeExceptionNoRollback").build());
            throw new RuntimeException();
        } catch (Exception ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    public void catchRuntimeCanRollback() {
        transactionDao.save(TestTransactionEntity.builder().name("catchRuntimeCanRollback").build());
        throw new RuntimeException();
    }

    @Override
    @Transactional(rollbackFor = {CustomException.class})
    public void specifiedExceptionCanRollback() throws CustomException {
        try {
            transactionDao.save(TestTransactionEntity.builder().name("specifiedExceptionCanRollback").build());
            throw new RuntimeException();
        } catch (RuntimeException ex) {
            throw new CustomException(ex.getMessage());
        }
    }

    @Override
    @Transactional
    /**
     * 因为spring 事务的传播性，会将2个事务合二为一
     */
    public void setRollbackOnlyCanRollback() {
        saveEntity();
        try {
            //不设置名称，让程序报错
            transactionDao.save(TestTransactionEntity.builder().build());
            //程序报错之后，Spring 会将当前事务标记为rollback-only
            //org.springframework.transaction.UnexpectedRollbackException:
            //Transaction silently rolled back because it has been marked as rollback-only
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional
    public void saveEntity() {
        transactionDao.save(TestTransactionEntity.builder().name("saveEntity").build());
    }
}
