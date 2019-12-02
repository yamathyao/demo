package com.example.demo.cqs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author GEEX177
 * @date 2019/11/4
 */
@Service("bus")
public class SpringBus implements Bus {

    @Autowired
    private CommandHandler commandHandler;

    @Autowired
    private QueryHandler queryHandler;

    @Override
    public <R, C extends Command<R>> R executeCommand(C command) {
        return (R) commandHandler.handle(command);
    }

    @Override
    public <R, Q extends Query<R>> R executeQuery(Q query) {
        return (R) queryHandler.handle(query);
    }
}
