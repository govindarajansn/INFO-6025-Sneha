package edu.neu.coe.info6205.union_find;

import java.util.Random;

//public class UF_Client {
//}

import java.util.Random;
public class UF_Client {

    public static int count(int n) {
        System.out.println("---------------");
//        Random rand = new Random();
//        UF_HWQUPC uf = new UF_HWQUPC(n, false);
//        int connections = 0;
//        while (uf.components() > 1) {
//            int p = rand.nextInt(n);
//            int q = rand.nextInt(n);
//            uf.union(p, q);
//            connections++;
//        }
        UF_HWQUPC uf = new UF_HWQUPC(n,true);
        Random rand = new Random();
        int connections = 0;

        while (uf.find(0) != uf.find(n - 1)) {
            int p = rand.nextInt(n);
            int q = rand.nextInt(n);

            if (!uf.connected(p, q)) {
                uf.union(p, q);
                connections++;
            }
        }

        System.out.println("---------------");
        return connections;
    }

    public static void main(String[] args) {
//        int n = Integer.parseInt(args[0]);
        int [] counts = {100, 200, 500, 1000 };
        for(int i=0;i< counts.length;i++){
            int connections = count(counts[i]);
            System.out.println("Input = "+ counts[i] +" Number of connections: " + connections);
        }

    }
}
