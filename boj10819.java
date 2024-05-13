// package boj10819;

import java.io.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        // 입력 파일에서 데이터 읽기 (테스트용)
        // System.setIn(new FileInputStream("src/input.txt"));
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 정수의 개수 N 입력받기
        int N = Integer.parseInt(br.readLine());
        
        // 정수 배열 A 입력받기
        String[] arr = br.readLine().split(" ");
        A = new int[N];
        B = new int[N];
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(arr[i]);
        }
        
        br.close();
        
        // 순열 생성 및 최댓값 계산
        recursion(N, 0);
        
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(max));
        bw.flush();
        bw.close();
    }
    
    public static int[] A; // 입력받은 정수 배열
    public static int[] B; // 생성된 순열을 저장할 배열
    public static boolean[] visited = new boolean[9]; // 방문 여부를 체크할 배열
    public static int max = 0; // 최댓값을 저장할 변수
    
    public static void recursion(int N, int depth) {
        // 순열이 완성되었을 때
        if (N == depth) {
            int sum = 0;
            // 인접한 요소의 차이의 절댓값의 합 계산
            for (int i = 0; i < B.length - 1; i++) {
                sum += Math.abs(B[i] - B[i+1]);
            }
            // 최댓값 갱신
            max = Math.max(max, sum);
            return;
        }
        
        // 순열 생성
        for (int i = 0; i < N; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            B[depth] = A[i];
            recursion(N, depth + 1);
            visited[i] = false;
        }
    }
}