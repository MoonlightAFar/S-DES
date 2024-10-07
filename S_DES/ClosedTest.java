package HomeWork01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClosedTest {

    // 初始化参数
    static int[] crackBits = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    static List<String> keysFound = new ArrayList<>(); // 存储找到的密钥
    static Map<String, List<String>> cipherToKeysMap = new HashMap<>(); // 存储密文与密钥的映射
    static long startTime; // 开始破解时间

    // 破解函数
    public static List<String> crack(String plainText, String cipherText) {
        startTime = System.currentTimeMillis(); // 开始破解时间
        S_DES s_des = new S_DES();
        Recursion(crackBits, 0, plainText, cipherText, s_des);
        return keysFound; // 返回找到的所有密钥
    }

    // 递归遍历所有可能的密钥
    public static void Recursion(int[] input, int index, String plainText, String cipherText, S_DES s_des) {
        if (index == input.length) { // 判断是否到达叶子节点
            StringBuilder bits = new StringBuilder();
            for (int num : input) {
                bits.append(num);
            }
            String Try = s_des.SDES(plainText, bits.toString(), 1)[0]; // 使用明文加密
            if (cipherText.equals(Try)) { // 判断密钥是否正确
                keysFound.add(bits.toString()); // 将找到的密钥添加到列表中
                System.out.println(bits + " is a valid key!");

                // 将密文与密钥的映射存储
                cipherToKeysMap.putIfAbsent(cipherText, new ArrayList<>());
                cipherToKeysMap.get(cipherText).add(bits.toString());
            } else {
                System.out.println(bits + " isn't a valid key!");
            }
            return;
        } else { // 递归
            int[] temp = new int[input.length];
            System.arraycopy(input, 0, temp, 0, input.length);
            // 不选择当前位
            Recursion(temp, index + 1, plainText, cipherText, s_des);
            // 选择当前位
            temp[index] = 1;
            Recursion(temp, index + 1, plainText, cipherText, s_des);
        }
    }

    // 验证是否有多个密钥对应同一密文
    public static void verifyMultipleKeys() {
        for (Map.Entry<String, List<String>> entry : cipherToKeysMap.entrySet()) {
            String cipher = entry.getKey();
            List<String> keys = entry.getValue();
            if (keys.size() > 1) {
                System.out.println("Cipher " + cipher + " has multiple keys: " + keys);
            }
        }
    }

    // 暴力破解得到所有可能的密钥
    public static void main(String args[]) {
        S_DES s = new S_DES();
        String plainText = "01110110";
        String cipherText = "00111000";
        System.out.println("Random Plain Text: " + plainText);
        System.out.println("Random Cipher Text: " + cipherText);
        System.out.println("Possible Keys:");
        List<String> result = crack(plainText, cipherText);
        System.out.println("Found keys: " + result);
        System.out.println("Total keys found: " + result.size());

        // 验证是否有多个密钥对应同一密文
        verifyMultipleKeys();

        // 额外测试不同明文的情况
        String testPlainText = "11101110";
        String testCipherText1 = s.SDES(testPlainText, "0000000000", 1)[0];
        String testCipherText2 = s.SDES(testPlainText, "0000110100", 1)[0];
        System.out.println("Cipher for key 0000000000: " + testCipherText1);
        System.out.println("Cipher for key 0000110100: " + testCipherText2);
        if (testCipherText1.equals(testCipherText2)) {
            System.out.println("Different keys produce the same cipher for plain text " + testPlainText);
        }
    }
}