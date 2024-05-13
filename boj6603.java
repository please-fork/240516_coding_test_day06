// package boj6603;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // System.setIn(new FileInputStream("src/input.txt")); // 입력 파일에서 데이터 읽기 (테스트용)
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String input;
        while ((input = br.readLine()) != null && !input.equals("0")) { // 입력이 "0"이 아닐 때까지 반복
            StringTokenizer st = new StringTokenizer(input);
            int k = Integer.parseInt(st.nextToken()); // 집합 S의 크기 k 입력받기
            int[] S = new int[k]; // 크기가 k인 집합 S 배열 생성
            
            for (int i = 0; i < k; i++) {
                S[i] = Integer.parseInt(st.nextToken()); // 집합 S의 원소 입력받기
            }
            
            recursion(S, 0, 0, new int[6], bw); // 재귀 함수 호출하여 조합 생성
            bw.write("\n"); // 각 테스트 케이스 사이에 빈 줄 출력
        }
        
        br.close();
        bw.flush();
        bw.close();
    }
    
    // 재귀 함수를 사용하여 조합 생성
    public static void recursion(int[] S, int depth, int idx, int[] tmp, BufferedWriter bw) throws IOException {
        if (depth == 6) { // 선택한 숫자의 개수가 6이 되면
            for (int i = 0; i < 6; i++) {
                bw.write(tmp[i] + " "); // 선택한 숫자 출력
            }
            bw.newLine(); // 줄 바꿈
            return;
        }
        
        if (idx >= S.length) { // 인덱스가 집합 S의 크기 이상이면 종료
            return;
        }
        
        tmp[depth] = S[idx]; // 현재 인덱스의 숫자 선택
        recursion(S, depth + 1, idx + 1, tmp, bw); // 다음 숫자 선택을 위해 재귀 호출
        recursion(S, depth, idx + 1, tmp, bw); // 현재 숫자를 선택하지 않고 다음 인덱스로 이동
    }
}