package com.example.demo.cqs;

import org.springframework.context.ApplicationContext;
import org.springframework.core.GenericTypeResolver;

import java.util.HashMap;
import java.util.Map;

/**
 * @author GEEX177
 * @date 2019/11/4
 */

public class Registry {
//    private Map<Class<? extends Command>, CommandProvider> commandProviderMap = new HashMap<>();
//    private Map<Class<? extends Query>, QueryProvider> queryProviderMap = new HashMap<>();
//
//    public Registry(ApplicationContext applicationContext) {
//        String[] names = applicationContext.getBeanNamesForType(CommandHandler.class);
//        for (String name : names) {
//            registryCommand(applicationContext, name);
//        }
//        names = applicationContext.getBeanNamesForType(QueryHandler.class);
//        for (String name : names) {
//            registryQuery(applicationContext, name);
//        }
//    }
//
//    private void registryCommand(ApplicationContext applicationContext, String name) {
//        Class<CommandHandler<?, ?>> handlerClass = (Class<CommandHandler<?, ?>>) applicationContext.getType(name);
//        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(handlerClass, CommandHandler.class);
//        Class<? extends Command> commandType = (Class<? extends Command>) generics[1];
//        commandProviderMap.put(commandType, new CommandProvider(applicationContext, handlerClass));
//    }
//
//    private void registryQuery(ApplicationContext applicationContext, String name) {
//        Class<QueryHandler<?, ?>> queryClass = (Class<QueryHandler<?, ?>>) applicationContext.getType(name);
//        Class<?>[] generics = GenericTypeResolver.resolveTypeArguments(queryClass, QueryHandler.class);
//        Class<? extends Query> queryType = (Class<? extends Query>) generics[1];
//        queryProviderMap.put(queryType, new QueryProvider(applicationContext, queryClass));
//    }
//
//    @SuppressWarnings("unchecked")
//    <R, C extends Command<R>> CommandHandler<R, C> getCmd(Class<C> commandClass) {
//        return commandProviderMap.get(commandClass).get();
//    }
//
//    @SuppressWarnings("unchecked")
//    <R, C extends Query<R>> QueryHandler<R, C> getQuery(Class<C> queryClass) {
//        return queryProviderMap.get(queryClass).get();
//    }
}
