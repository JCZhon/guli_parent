package com.zjh.eduService.aop;

import com.zjh.eduService.utils.TransactionUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;

/**
 * 事务处理
 */
@Aspect
@Component
public class TransactionAop {

    @Autowired
    private TransactionUtil transactionUtil;

/*
    @Pointcut(value="@annotation(com.zjh.eduService.myAnnotations.MyTransaction)")
    public void pointcut(){

    }
*/

    @Around(value = "@annotation(com.zjh.eduService.myAnnotations.MyTransaction)")//("pointcut()")
    public void around(ProceedingJoinPoint pjp) {
        TransactionStatus begin = null;
        try {
            begin = transactionUtil.begin();
            pjp.proceed();
            transactionUtil.commit(begin);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            if (begin != null) {
                transactionUtil.rollback(begin);
            }
        }
    }
}
