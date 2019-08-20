package com.sxzhongf.spring.transcational.poc.dao;

import com.sxzhongf.spring.transcational.poc.entity.TestTransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * TestTransactionDao for 数据库操作类
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
public interface TestTransactionDao extends JpaRepository<TestTransactionEntity, Long> {
}
