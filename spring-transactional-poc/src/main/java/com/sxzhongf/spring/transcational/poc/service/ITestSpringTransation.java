package com.sxzhongf.spring.transcational.poc.service;

import com.sxzhongf.spring.transcational.poc.exception.CustomException;

/**
 * ITestSpringTransation for Spring Transaction 测试接口
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
public interface ITestSpringTransation {

    /**
     * 主动捕获异常，事务不能回滚
     */
    void catchExceptionNoRollback();

    /**
     * 非 unchecked exception,事务无法回滚
     */
    void catchNonRuntimeExceptionNoRollback() throws CustomException;
}
