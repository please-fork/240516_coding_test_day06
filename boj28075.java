// package boj28075;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // N과 M 입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 일수
        M = Integer.parseInt(st.nextToken()); // 목표 진척도
        
        // A 장소의 진척도 입력받기
        A = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        
        // B 장소의 진척도 입력받기
        B = new int[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }
        
        br.close();
        
        // 재귀를 활용한 경우의 수 계산
        recursiveSearch(0, -1, 0);
        
        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(count));
        bw.flush();
        bw.close();
    }
    
    public static int N; // 일수
    public static int M; // 목표 진척도
    public static int[] A; // A 장소의 진척도
    public static int[] B; // B 장소의 진척도
    public static int count = 0; // 경우의 수
    
    public static void recursiveSearch(int day, int prev, int progress) {
        // 기저 조건: N일 동안 방문한 경우
        if (day == N) {
            // 진척도 합이 M 이상인 경우 경우의 수 증가
            if (progress >= M) {
                count++;
            }
            return;
        }
        
        // 각 장소에 대해 재귀 호출
        for (int i = 0; i < 3; i++) {
            // 이전에 방문한 장소와 동일한 경우
            if (i == prev) {
                // A 장소 방문 (진척도 절반)
                recursiveSearch(day + 1, i, progress + A[i] / 2);
                // B 장소 방문 (진척도 절반)
                recursiveSearch(day + 1, i, progress + B[i] / 2);
            } else {
                // A 장소 방문 (진척도 전체)
                recursiveSearch(day + 1, i, progress + A[i]);
                // B 장소 방문 (진척도 전체)
                recursiveSearch(day + 1, i, progress + B[i]);
            }
        }
    }
}