package com.sxzhongf.spring.transcational.poc.service;

import com.sxzhongf.spring.transcational.poc.entity.TestTransactionEntity;
import com.sxzhongf.spring.transcational.poc.exception.CustomException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

/**
 * ITestSpringTransation for Spring Transaction 测试接口
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
public interface ITestSpringTransation {

    /**
     * 根据名称查询记录
     */
    Collection<TestTransactionEntity> findByName(String name);

    /**
     * 主动捕获异常，事务不能回滚
     */
    void catchExceptionNoRollback();

    /**
     * 非 unchecked exception,事务无法回滚
     */
    void catchNonRuntimeExceptionNoRollback() throws CustomException;

    /**
     * unchecked异常，spring可以自动异常事务回滚
     */
    void catchRuntimeCanRollback();

    /**
     * {@link Transactional} 指定的异常，spring可以自动回滚事务
     */
    void specifiedExceptionCanRollback() throws CustomException;

    /**
     * 设置 TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
     * 可以自动回滚事务
     */
    void setRollbackOnlyCanRollback();

    /**
     * 同一个实现类中，不标注事务的方法调用标注了@Transactional的方法
     * 事务失效（前提是在同一个类中）
     */
    void nonTransactionNoRollback();
}
