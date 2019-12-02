package com.example.demo.cqs;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
public interface QueryHandler<R, Q extends Query<R>> {
    /**
     * 处理query
     *
     * @param query query
     * @return return model
     */
    R handle(Q query);
}
