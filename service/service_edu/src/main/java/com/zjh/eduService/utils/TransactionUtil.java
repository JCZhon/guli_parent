package com.zjh.eduService.utils;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

/**
 * 自定义处理事务
 */
@Component
public class TransactionUtil {
    private DataSourceTransactionManager dataSourceTransactionManager;

    /**
     * begin事务
     */
    public TransactionStatus begin() {
        TransactionStatus transactionStatus = dataSourceTransactionManager.
                getTransaction(new DefaultTransactionAttribute());
        return transactionStatus;
    }

    /**
     * commit事务
     */
    public void commit(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.commit(transactionStatus);
    }

    /**
     * rollback事务
     */
    public void rollback(TransactionStatus transactionStatus) {
        dataSourceTransactionManager.rollback(transactionStatus);
    }
}
