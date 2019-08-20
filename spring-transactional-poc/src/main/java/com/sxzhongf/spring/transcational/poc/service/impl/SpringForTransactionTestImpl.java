package com.sxzhongf.spring.transcational.poc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * SpringForTransactionTestImpl for 为了演示不同类中方法互调的事务信息
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
@Service
@Slf4j
public class SpringForTransactionTestImpl {

    private final TestSpringTransactionImpl springTransaction;

    @Autowired
    public SpringForTransactionTestImpl(TestSpringTransactionImpl springTransaction) {
        this.springTransaction = springTransaction;
    }

    public void springTransaction(){
        springTransaction.saveNonTransactionalEntity();
    }
}
