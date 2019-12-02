package com.example.demo.hashmap;

import java.util.*;

/**
 * @author GEEX177
 * @date 2019/10/30
 */
public class ConsistentHashing {

    // 物理节点
    private Set<String> physicalNodes = new TreeSet<String>() {
        {
            add("192.168.1.101");
            add("192.168.1.102");
            add("192.168.1.103");
            add("192.168.1.104");
        }
    };

    // 物理节点至虚拟节点的复制倍数
//    private final int VIRTUAL_COPIES = 1;
//    private final int VIRTUAL_COPIES = 32;
    private final int VIRTUAL_COPIES = 1048576;

    private TreeMap<Long, String> virtualNodes = new TreeMap<>();

    public ConsistentHashing() {
        for (String nodeIp : physicalNodes) {
            addPhysicalNode(nodeIp);
        }
    }

    public void addPhysicalNode(String nodeIp) {
        for (int i = 0; i < VIRTUAL_COPIES; ++i) {
            long hash = HashingAlg.fnv1HashingAlg(nodeIp + "#" + i);
            virtualNodes.put(hash, nodeIp);
        }
    }

    public void removePhysicalNode(String nodeIp) {
        for (int i = 0; i < VIRTUAL_COPIES; ++i) {
            long hash = HashingAlg.fnv1HashingAlg(nodeIp + "#" + i);
            virtualNodes.remove(hash);
        }
    }

    public String getObjectNode(String object) {
        long hash = HashingAlg.fnv1HashingAlg(object);
        // 所有大于hash的节点
        SortedMap<Long, String> tailMap = virtualNodes.tailMap(hash);
        Long key = tailMap.isEmpty() ? virtualNodes.firstKey() : tailMap.firstKey();
        return virtualNodes.get(key);
    }

    public void dumpObjectNodeMap(String label, int objectMin, int objectMax) {
        // 统计
        Map<String, Integer> objectNodeMap = new TreeMap<>();
        for (int object = objectMin; object <= objectMax; ++object) {
            String nodeIp = getObjectNode(Integer.toString(object));
            Integer count = objectNodeMap.get(nodeIp);
            objectNodeMap.put(nodeIp, (count == null ? 0 : count + 1));
        }

        double totalCount = objectMax - objectMin + 1;
        System.out.println("= = = = = =" + label + "= = = = = =");
        for (Map.Entry<String, Integer> entry : objectNodeMap.entrySet()) {
            long percent = (int) (100 * entry.getValue() / totalCount);
            System.out.println("IP=" + entry.getKey() + ":Rate=" + percent + "%");
        }
    }

    public static void main(String[] args) {
        ConsistentHashing ch = new ConsistentHashing();
        ch.dumpObjectNodeMap("初始情况", 0, 65536);

        ch.removePhysicalNode("192.168.1.103");
        ch.dumpObjectNodeMap("删除物理节点", 0, 65536);

        ch.addPhysicalNode("192.168.1.108");
        ch.dumpObjectNodeMap("添加物理节点", 0, 65536);
    }
}
