package com.pratik;
import java.util.*;

public class MeetingRoomScheduler {

    public static int minMeetingRooms(int[][] intervals) {

        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort meetings by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // Min Heap for end times
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        minHeap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {

            // Reuse room if meeting ended
            if (intervals[i][0] >= minHeap.peek()) {
                minHeap.poll();
            }

            minHeap.offer(intervals[i][1]);
        }

        return minHeap.size();
    }

    public static void main(String[] args) {

        int[][] meetings = {
                {0, 30},
                {5, 10},
                {15, 20}
        };

        int result = minMeetingRooms(meetings);

        System.out.println("Minimum Meeting Rooms Required: " + result);
    }
}