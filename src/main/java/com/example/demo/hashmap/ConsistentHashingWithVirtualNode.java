package com.example.demo.hashmap;

import java.util.*;

/**
 * @author GEEX177
 * @date 2019/10/30
 */
public class ConsistentHashingWithVirtualNode {

    private static String[] servers = {"192.168.0.0:111", "192.168.0.1:111", "192.168.0.2:111", "192.168.0.3:111", "192.168.0.4:111"};

    private static List<String> realNodes = new LinkedList<>();

    private static SortedMap<Long, String> virtualNodes = new TreeMap<>();

    private static final int VIRTUAL_NODES = 10;

    static {
        // 添加真实节点
        Collections.addAll(realNodes, servers);
        // 添加虚拟节点
        for (String node : realNodes) {
            for (int i = 0; i < VIRTUAL_NODES; i++) {
                String virtualNodeName = node + "&&VN" + String.valueOf(i);
                long hash = HashingAlg.fnv1HashingAlg(virtualNodeName);
                System.out.println("虚拟节点[" + virtualNodeName + "]被添加， hash值为" + hash);
                virtualNodes.put(hash, virtualNodeName);
            }
        }
        System.out.println();
    }

    private static String getServer(String node) {
        long hash = HashingAlg.fnv1HashingAlg(node);
        String virtualNode;
        Long i;
        SortedMap<Long, String> subMap = virtualNodes.tailMap(hash);
        if (subMap.size() == 0) {
            i = virtualNodes.firstKey();
            virtualNode = virtualNodes.get(i);
        } else {
            i = subMap.firstKey();
            virtualNode = subMap.get(i);
        }
        return virtualNode.substring(0, virtualNode.indexOf("&&"));
    }

    public static void main(String[] args) {
        String[] nodes = {"127.0.0.1:1111", "221.226.0.1:2222", "10.211.0.1:3333"};
        for (String node : nodes) {
            System.out.println("[" + node + "]的hash值为" + HashingAlg.fnv1HashingAlg(node) + "，被路由到结点[" + getServer(node) + "]");
        }
    }
}
