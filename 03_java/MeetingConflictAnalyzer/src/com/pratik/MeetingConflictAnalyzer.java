package com.pratik;

import java.util.*;

public class MeetingConflictAnalyzer {

    public static boolean canAttendMeetings(
            int[][] meetings) {

        // Sort by start time
        Arrays.sort(meetings,
                (a, b) -> a[0] - b[0]);

        for (int i = 1;
             i < meetings.length;
             i++) {

            // Check overlap
            if (meetings[i][0]
                    < meetings[i - 1][1]) {

                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {

        int[][] meetings = {
                {0, 30},
                {35, 40},
                {5, 10}
        };

        boolean result =
                canAttendMeetings(meetings);

        System.out.println(
                "Can Attend All Meetings: "
                        + result);
    }
}