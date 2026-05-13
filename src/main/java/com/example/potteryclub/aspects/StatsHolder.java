package com.example.potteryclub.aspects;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class StatsHolder {

    // endpoint call counts
    private final Map<String, AtomicInteger> endpointCalls = new ConcurrentHashMap<>();

    // post stats
    private int longestPostLength = 0;
    private String longestPostTitle = "none";
    private int totalPostsServed = 0;

    public void recordEndpointCall(String endpoint) {
        endpointCalls.computeIfAbsent(endpoint, k -> new AtomicInteger(0)).incrementAndGet();
    }

    public Map<String, AtomicInteger> getEndpointCalls() {
        return endpointCalls;
    }

    public synchronized void updatePostStats(int contentLength, String title, int postCount) {
        totalPostsServed += postCount;
        if (contentLength > longestPostLength) {
            longestPostLength = contentLength;
            longestPostTitle = title;
        }
    }

    public int getLongestPostLength() { return longestPostLength; }
    public String getLongestPostTitle() { return longestPostTitle; }
    public int getTotalPostsServed() { return totalPostsServed; }
}
