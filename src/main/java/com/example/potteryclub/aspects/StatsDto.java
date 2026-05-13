package com.example.potteryclub.aspects;

import java.util.Map;

public class StatsDto {

    private Map<String, Double> endpointUsagePercent;
    private String longestPostTitle;
    private int longestPostContentLength;
    private int totalPostsServed;

    public Map<String, Double> getEndpointUsagePercent() { return endpointUsagePercent; }
    public void setEndpointUsagePercent(Map<String, Double> e) { this.endpointUsagePercent = e; }

    public String getLongestPostTitle() { return longestPostTitle; }
    public void setLongestPostTitle(String t) { this.longestPostTitle = t; }

    public int getLongestPostContentLength() { return longestPostContentLength; }
    public void setLongestPostContentLength(int l) { this.longestPostContentLength = l; }

    public int getTotalPostsServed() { return totalPostsServed; }
    public void setTotalPostsServed(int t) { this.totalPostsServed = t; }
}
