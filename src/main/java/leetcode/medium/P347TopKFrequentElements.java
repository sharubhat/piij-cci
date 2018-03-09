package leetcode.medium;

import java.util.*;

/** https://leetcode.com/problems/top-k-frequent-elements/description/ */
public class P347TopKFrequentElements {
  public List<Integer> topKFrequentUsingBuckets(int[] nums, int k) {
    // create a frequency map
    Map<Integer, Integer> frequencies = getFrequencyMap(nums);
    // create a bucket with element sorted by frequency
    List<Integer>[] buckets = new List[nums.length + 1];
    for (Map.Entry<Integer, Integer> e : frequencies.entrySet()) {
      if (buckets[e.getValue()] == null) {
        buckets[e.getValue()] = new ArrayList<>();
      }
      buckets[e.getValue()].add(e.getKey());
    }
    // create result by flattening out the bucket
    List<Integer> result = new ArrayList<>();
    int tmpK = k;
    for (int i = buckets.length - 1; i > 0 && tmpK > 0; i--) {
      if (buckets[i] != null) {
        Iterator<Integer> listIt = buckets[i].iterator();
        while(listIt.hasNext() && tmpK > 0) {
          result.add(listIt.next());
          tmpK--;
        }
      }
    }
    // slightly less optimal but less and easier to read code.
    // difference is O(k) vs O(n) which does not change overall time complexity
    return kMostFrequentInBucket(k, buckets).subList(0, k);
  }

  private List<Integer> kMostFrequentInBucket(int k, List<Integer>[] buckets) {
    List<Integer> result2 = new ArrayList<>();
    for (int i = buckets.length - 1; i > 0 && k > 0; i--) {
      if (buckets[i] != null) {
        result2.addAll(buckets[i]);
      }
    }
    return result2;
  }

  private Map<Integer, Integer> getFrequencyMap(int[] nums) {
    Map<Integer, Integer> frequencies = new HashMap<>();
    for (int i : nums) {
      frequencies.put(i, frequencies.getOrDefault(i, 0) + 1);
    }
    return frequencies;
  }

  public List<Integer> topKFrequentUsingMaxHeap(int[] nums, int k) {
    // create a frequency map
    Map<Integer, Integer> frequencies = getFrequencyMap(nums);
    // create a max heap that uses value comparator for map's entries
    PriorityQueue<Map.Entry<Integer, Integer>> pq =
        new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
    for (Map.Entry<Integer, Integer> e : frequencies.entrySet()) {
      pq.add(e);
    }
    // create result by flattening out the bucket
    List<Integer> result = new ArrayList<>();
    while (!pq.isEmpty()) {
      result.add(pq.poll().getKey());
    }
    return result.subList(0, k);
  }

  public static void main(String[] args) {
    System.out.println(
        new P347TopKFrequentElements().topKFrequentUsingBuckets(new int[] {1, 1, 1, 2, 2, 3,3,3}, 2));
    System.out.println(
        new P347TopKFrequentElements().topKFrequentUsingMaxHeap(new int[] {1, 1, 1, 2, 2, 3,3,3}, 2));
  }
}
