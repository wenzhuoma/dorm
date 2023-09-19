package com.dorm;

import java.util.Scanner;

public class ZuiYouShiJianBiao {

    private static int[] mt;
    private static int[] s,t;
    private static int n,k;

    private static int MAX = 100000;

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        while (true){
            n = input.nextInt();
            k = input.nextInt();
            mt = new int[n+2];
            s = new int[k+1];
            t = new int[k+1];

            for(int i=1; i<=k; i++){
                s[i] = input.nextInt();
                t[i] = input.nextInt();
            }

            mt[n+1] = n;
            for(int i=1; i<=n; i++)
                mt[i] = MAX;
            dynamic();

            System.out.println(mt[1]);
        }

    }

    private static void dynamic(){
        for(int i=n; i>0; i--){
            int count = 0;
            for(int j=1; j<=k; j++){
                if(s[j] == i){
                    count++;
                    int temp = mt[i+t[j]];
                    if(mt[i] > temp)
                        mt[i] = temp;
                }
            }
            if(count == 0)
                mt[i] = mt[i+1]-1;
        }
    }
}