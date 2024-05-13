package boj15658;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // 입력 파일에서 데이터 읽기 (테스트용)
        System.setIn(new FileInputStream("src/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 수의 개수 N 입력받기
        int N = Integer.parseInt(br.readLine());
        
        // 수열 A 입력받기
        String[] arr;
        arr = br.readLine().split(" ");
        A = new int[N];
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(arr[i]);
        }
        
        // 연산자의 개수 입력받기
        // visited 배열에 각 연산자(+, -, *, /)의 개수를 저장
        // visited[0]: '+' 연산자의 개수
        // visited[1]: '-' 연산자의 개수
        // visited[2]: '*' 연산자의 개수
        // visited[3]: '/' 연산자의 개수
        arr = br.readLine().split(" ");
        for (int i = 0; i < 4; i++) {
            visited[i] = Integer.parseInt(arr[i]);
        }
        
        br.close();
        
        // 백트래킹을 활용한 연산자 배치 및 최댓값, 최솟값 계산
        // 초기 depth는 1, 초기 값은 수열의 첫 번째 수 A[0]으로 설정
        recursion(N, 1, A[0]);
        
        // 결과 출력
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(max)); // 최댓값 출력
        bw.write("\n");
        bw.write(Integer.toString(min)); // 최솟값 출력
        bw.flush();
        bw.close();
    }
    
    public static int[] A; // 수열 A
    public static int[] visited = new int[4]; // 연산자의 개수를 저장할 배열
    public static int max = Integer.MIN_VALUE; // 최댓값을 저장할 변수
    public static int min = Integer.MAX_VALUE; // 최솟값을 저장할 변수
    
    public static void recursion(int N, int depth, int val) {
        // 연산자 배치가 완료되었을 때 (모든 수를 사용한 경우)
        if (N == depth) {
            max = Math.max(max, val); // 현재 값과 최댓값 비교하여 갱신
            min = Math.min(min, val); // 현재 값과 최솟값 비교하여 갱신
            return;
        }
        
        // 연산자 배치
        for (int i = 0; i < 4; i++) {
            if (visited[i] == 0) {
                // 해당 연산자를 모두 사용한 경우 건너뛰기
                continue;
            }
            
            visited[i]--; // 해당 연산자 사용 표시
            
            switch (i) {
                case 0: {
                    // '+' 연산자인 경우
                    recursion(N, depth + 1, val + A[depth]);
                    break;
                }
                case 1: {
                    // '-' 연산자인 경우
                    recursion(N, depth + 1, val - A[depth]);
                    break;
                }
                case 2: {
                    // '*' 연산자인 경우
                    recursion(N, depth + 1, val * A[depth]);
                    break;
                }
                case 3: {
                    // '/' 연산자인 경우
                    recursion(N, depth + 1, val / A[depth]);
                    break;
                }
            }
            
            visited[i]++; // 해당 연산자 사용 해제 (백트래킹)
        }
    }
}