package com.sxzhongf.spring.transcational.poc.exception;

/**
 * CustomException for 自定义checked exception(集成Exception)
 * 默认spring transaction 不会回滚
 *
 * @author <a href="mailto:magicianisaac@gmail.com">Isaac.Zhang | 若初</a>
 * @since 2019/8/20
 */
public class CustomException extends Exception {

    public CustomException(String message) {
        super(message);
    }
}
