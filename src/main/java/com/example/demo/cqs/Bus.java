package com.example.demo.cqs;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
public interface Bus {
    /**
     * 执行command
     *
     * @param command command
     * @param <R>     return model
     * @param <C>     command
     * @return return model
     */
    <R, C extends Command<R>> R executeCommand(C command);

    /**
     * 执行query
     *
     * @param query query
     * @param <R>   return model
     * @param <Q>   query
     * @return return model
     */
    <R, Q extends Query<R>> R executeQuery(Q query);
}
