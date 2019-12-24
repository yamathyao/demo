package com.example.demo.guava;

import com.google.common.base.*;
import com.google.common.base.Optional;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.*;
import com.google.common.primitives.Ints;
import com.google.common.util.concurrent.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @author yao
 * @date 2019/12/19
 */
@Slf4j
public class SplitterDemo {

    /**
     * 连接器，跳过NULL元素用skipNulls，对于NULL元素使用其他值'nullText'替代 useForNull(String)
     */
    private static final Joiner JOINER_SKIP_NULL = Joiner.on(",").skipNulls();

    /**
     * 分割器
     */
    private static final Splitter SPLITTER = Splitter.on(",").trimResults().omitEmptyStrings();

    /**
     * 数字匹配
     */
    private static final CharMatcher CHAR_MATCHER_DIGIT = CharMatcher.inRange('0', '9');


    public static void main(String[] args) {
//        joinMethod();

//        charMatcherMethod();

//        intsMethod();

//        multiSetMethod();

//        immutableMethod();

//        biMapMethod();

//        tableMethod();

//        functionMethod();

//        preconditionMethod();

//        cacheMethod();

        futureMethod();
    }

    private static void joinMethod() {
        String join = JOINER_SKIP_NULL.join(Lists.newArrayList("a", null, "c"));

        String joinAndNull = Joiner.on(",").useForNull("null").join(Lists.newArrayList("a", null, "c"));

        log.info("join = " + join);

        log.info("join for null = " + joinAndNull);

        for (String tmp : SPLITTER.split("a   ,b,  , , c,")) {
            log.info("|" + tmp + "|");
        }
    }

    private static void charMatcherMethod() {
        String retain = CHAR_MATCHER_DIGIT.retainFrom("abc2def134f~");
        log.info(retain);
        String remove = CHAR_MATCHER_DIGIT.removeFrom("yes, i love u 1314");
        log.info(remove);
        String any = CharMatcher.inRange('a', 'f').or(CharMatcher.is('n')).replaceFrom("zhangfengzhe", "*");
        log.info(any);
    }

    private static void intsMethod() {
        // 集合转换
        List<Integer> list = Ints.asList(1, 3, 5, 7, 9);
        log.info(list.toString());
        log.info(Ints.join(",", 1, 2, 3, 4));
        // 原生类型数组合并
        int[] newIntArray = Ints.concat(new int[]{1, 2}, new int[]{2, 4, 5});
        log.info("Length: {}, list: {}", newIntArray.length, newIntArray);
        // 最大/最小
        int max = Ints.max(newIntArray);
        int min = Ints.min(newIntArray);
        log.info("Max: {}, Min: {}", max, min);
        // 是否包含
        boolean b = Ints.contains(newIntArray, 6);
        log.info("Contains 6? {}", b);
        // 集合转换数组
        int[] someArray = Ints.toArray(list);
        log.info("list: {} 转 int[]: {}", list, someArray);
    }

    private static void multiSetMethod() {
        // 无序，可重复
        Multiset<String> multiset = HashMultiset.create();
        multiset.add("a");
        multiset.add("a");
        multiset.add("b");
        multiset.add("c");
        multiset.add("b");
        log.info("MultiSet: {}, size: {}, count a:{}", multiset, multiset.size(), multiset.count("a"));
    }

    private static void immutableMethod() {
        List<String> immutable = ImmutableList.of("a", "b", "c");
        List<String> list = ImmutableList.copyOf(immutable);
        // UnsupportedOperationException
//        list.add("d");
        log.info("Origin list: {}, size: {}, new list: {}, size: {}", immutable, immutable.size(), list, list.size());
    }

    private static void biMapMethod() {
        // 双向map
        BiMap<String, String> biMap = HashBiMap.create();
        biMap.put("name", "zhang");
        // IllegalArgumentException: value already present: zhang value重复会报错
//        biMap.put("nick", "zhang");
        // 强制更新
        biMap.forcePut("nick", "zhang");
        biMap.put("123", "zhang@163.com");
        // biMap.inverse 反向 biMap.inverse != biMap
        String key = biMap.inverse().get("zhang@163.com");
        log.info("BiMap: {}, find key by value {}", biMap, key);
    }

    private static void tableMethod() {
        Table<String, String, Integer> table = HashBasedTable.create();
        table.put("张三", "数学", 80);
        table.put("张三", "语文", 60);
        table.put("张三", "英语", 35);
        table.put("李四", "数学", 60);
        table.put("李四", "语文", 80);
        table.put("李四", "英语", 95);

        Set<String> rowSet = table.rowKeySet();
        log.info("Table row: {}", rowSet);
        Set<String> columnSet = table.columnKeySet();
        log.info("Table column: {}", columnSet);
        log.info("Table row[张三] value: {}", table.row("张三"));
        log.info("Table column[语文] value: {}", table.column("语文"));
    }

    private static void functionMethod() {
        List<String> list = Lists.newArrayList("HelloWord", "yes", "zhangsan");

        Function<String, String> f1 = s -> s.length() <= 5 ? s : s.substring(0, 5);

        Function<String, String> f2 = String::toUpperCase;

        Function<String, String> f3 = Functions.compose(f1, f2);

        Collection<String> collection = Collections2.transform(list, f3);
        for (String s : collection) {
            log.info(s);
        }
    }

    private static void preconditionMethod() {
        String name = "zhangsan";
        int age = 19;
        Map<String, String> extInfo = Maps.newHashMap();
        extInfo.put("name", name);
        extInfo.put("age", String.valueOf(age));
        extInfo.put("height", "168cm");
        Preconditions.checkNotNull(name, "name must be given!");
        Preconditions.checkArgument(age >= 18, "the game you can not play it, your age is under 18!");

        Map<String, String> info = Maps.newHashMap();
        info.put("sex", "man");

        extInfo = Optional.fromNullable(extInfo).or(info);
        for (Map.Entry<String, String> entry : extInfo.entrySet()) {
            log.info("key: [{}], value: [{}]", entry.getKey(), entry.getValue());
        }
    }

    private static void cacheMethod() {
        try {
            for (int i = 0; i < 20; i++) {
                User user = userCacheData.get(1L);
                log.info("User: {}", user);
                TimeUnit.SECONDS.sleep(1);
            }
            log.info("cache stat: {}", userCacheData.stats());
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    private static final CacheLoader<Long, User> userCacheLoader = new CacheLoader<Long, User>() {
        @Override
        public User load(Long aLong) throws Exception {
            User user = new User();
            user.setId(aLong);
            user.setName(Thread.currentThread().getName() + "-" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-" + aLong);
            return user;
        }
    };

    private static final LoadingCache<Long, User> userCacheData = CacheBuilder.newBuilder()
//            .expireAfterAccess(2, TimeUnit.SECONDS)
            .expireAfterWrite(8, TimeUnit.SECONDS)
//            .refreshAfterWrite(2, TimeUnit.SECONDS)
            .maximumSize(1000L)
            .concurrencyLevel(10)
            .recordStats()
            .removalListener(notification -> log.info(notification.getKey() + " was removed, cause is " + notification.getCause()))
            .build(userCacheLoader);


    @Getter
    @Setter
    @ToString
    public static class User {
        private Long id;

        private String name;
    }

    private static void futureMethod() {
        // 线程池
        ExecutorService executor = new ThreadPoolExecutor(4, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(), new ThreadPoolExecutor.AbortPolicy());
        // 监听回调线程池
        ListeningExecutorService listenExecutor = MoreExecutors.listeningDecorator(executor);

        ListenableFuture listenableFuture = listenExecutor.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                if (new Random().nextInt(3) == 2) {
                    throw new NullPointerException();
                }
                return 1;
            }
        });
        FutureCallback futureCallback = new FutureCallback<Integer>() {
            @Override
            public void onSuccess(final Integer o) {
                log.info("success, {}", o);
            }

            @Override
            public void onFailure(Throwable throwable) {
                log.info(throwable.getMessage(), throwable);
            }
        };

        Futures.addCallback(listenableFuture, futureCallback, listenExecutor);
    }
}
