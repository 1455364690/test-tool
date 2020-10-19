package demo;

import java.io.*;

class Node {
    String name;
    String description;
    String location;
    String category;
    String img;
}

public class CreateNode {
    public static void main(String[] args) throws Exception {
        double catchRate601 = 0.7;
        double catchRate2018 = 0.8;
        double catchRate2019 = 0.5;
        createNode601(catchRate601);
        createNode2018(catchRate2018);
        createNode2019(catchRate2019);
    }

    public static void createCatchNode() throws Exception{

    }

    public static void createNode2018(double rate) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input2018")));
        File create = new File("2018node.json");
        if (create.exists()) {
            create.delete();
        }
        create.createNewFile();
        FileOutputStream file = new FileOutputStream("2018node.json", true);
        String str = null;
        String name = null;
        String category = null;
        int index = 0;
        file.write("[".getBytes());
        while ((str = br.readLine()) != null) {
            if (str.contains("name")) {
                String[] split = str.split("\"");
                name = split[3];
            }
            if (str.contains("category")) {
                String[] split = str.split("\"");
                category = split[3];
                write(index, name, category, file, rate, "2018");
                index++;
            }
        }
        file.write("]".getBytes());
    }

    public static void createNode2019(double rate) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input2019")));
        File create = new File("2019node.json");
        if (create.exists()) {
            create.delete();
        }
        create.createNewFile();
        FileOutputStream file = new FileOutputStream("2019node.json", true);
        String str = null;
        String name = null;
        String category = null;
        int index = 0;
        file.write("[".getBytes());
        while ((str = br.readLine()) != null) {
            if (str.contains("name")) {
                String[] split = str.split("\"");
                name = split[3];
            }
            if (str.contains("category")) {
                String[] split = str.split("\"");
                category = split[3];
                write(index, name, category, file, rate, "2019");
                index++;
            }
        }
        file.write("]".getBytes());
    }

    public static void createNode601(double rate) throws Exception {
        File create = new File("node.json");
        if (create.exists()) {
            create.delete();
        }
        create.createNewFile();
        FileOutputStream file = new FileOutputStream("node.json", true);
        String node = "{\n" +
                "        \"id\":\"12345\",\n" +
                "        \"examId\":4962,\n" +
                "        \"caseId\":601,\n" +
                "        \"nodeName\":\"probes[{nodeNameNum}]\",\n" +
                "        \"ifCatch\":{ifCatch},\n" +
                "        \"userIds\":[99222],\n" +
                "        \"category\":\"probes\",\n" +
                "        \"catchNum\":{catchNum},\n" +
                "        \"totalNum\":1\n" +
                "    }";
        StringBuilder sb = new StringBuilder();
        file.write("[".getBytes());
        for (int i = 0; i < 128; i++) {
            if (0 != i) {
                file.write(",".getBytes());
            }
            String temp = node.replace("{nodeNameNum}", "" + i);
            boolean ifCatch = Math.random() < rate;
            temp = temp.replace("{ifCatch}", "" + ifCatch);
            temp = temp.replace("{catchNum}", ifCatch ? "" + 1 : "" + 0);
            file.write(temp.getBytes());
        }
        file.write("]".getBytes());
    }

    public static void write(int index, String name, String category, FileOutputStream fileOutputStream, double rate, String caseId) throws Exception {
        String node = "{\n" +
                "        \"id\":\"12345\",\n" +
                "        \"examId\":4962,\n" +
                "        \"caseId\":{caseId},\n" +
                "        \"nodeName\":\"{nodeName}\",\n" +
                "        \"ifCatch\":{ifCatch},\n" +
                "        \"userIds\":[99222],\n" +
                "        \"category\":\"{category}\",\n" +
                "        \"catchNum\":{catchNum},\n" +
                "        \"totalNum\":1\n" +
                "    }";
        node = node.replace("{caseId}", caseId);
        node = node.replace("{nodeName}", name);
        node = node.replace("{category}", category);
        boolean ifCatch = Math.random() < rate;
        node = node.replace("{ifCatch}", "" + ifCatch);
        node = node.replace("{catchNum}", ifCatch ? "" + 1 : "" + 0);
        if (index != 0) {
            fileOutputStream.write(",".getBytes());
        }
        fileOutputStream.write(node.getBytes());
    }
}
