package com.luna.wednesday.hashcatcore.constant;

/**
 * @author Tony
 */
public interface HashcatProjectStatusConstant {
    /** 初始态，一般指运行project的上下文还不完整，比如keyspace尚未计算 */
    String INIT                 = "init";
    /** 正在计算keyspace */
    String CALCULATING_KEYSPACE = "calculating keyspace";
    /** 等待task分配 */
    String PENDING              = "pending";
    /** 等待执行 */
    String WAIT                 = "wait";
    /** 运行中 */
    String RUNNING              = "running";
    /** 完成 */
    String FINISH               = "finish";
    /** hash已处理 */
    // String PROCESSED = "processed";
    /** 终止 */
    String TERMINATE            = "terminate";
}
