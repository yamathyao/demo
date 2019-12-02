package com.example.demo.cqs;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
public interface CommandHandler<R, C extends Command<R>> {
    /**
     * 处理 command
     *
     * @param command command
     * @return return model
     */
    R handle(C command);
}
