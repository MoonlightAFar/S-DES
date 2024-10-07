package HomeWork01;

public class CrackTest {
    
    //初始化参数
    static int[] crackBits = {0,0,0,0,0,0,0,0,0,0};
    static boolean keyFound = false;//判断是否找到正确的密钥
    
    //最终输出的密钥
    static String endKey = new String();
    
    //破解函数
    public static String[] crack(String plainText,String cipherText) {
        long startTime = System.currentTimeMillis();//开始破解时间
        S_DES s_des = new S_DES();
        Recursion(crackBits,0,plainText,cipherText,s_des);
        String[] output = new String[2];
        output[0]=endKey;
        startTime=System.currentTimeMillis()-startTime;//总破解时间
        output[1]=Long.toString(startTime);
        return output;
    }

    //递归遍历所有可能的密钥
    public static void Recursion(int [] input,int index,String plainText,String cipherText,S_DES s_des) {
        if(keyFound) {
            return;
        }
        else {
            if(index==input.length) {//判断是否到达叶子节点
                String bits = new String();
                for(int num:input) {
                    bits+=num;
                }
                String Try = s_des.SDES(cipherText,bits,1)[0];
                if(plainText.equals(Try)) {//判断密钥是否正确
                    keyFound=true;
                    endKey = bits;
                }
                else {
                    System.out.println(bits+" isn't endKey!");
                    return;
                }
            }
            else {//递归
                int[] temp = new int[input.length];
                for(int i=0;i<input.length;i++) {
                    temp[i]=input[i];
                }
                Recursion(temp,index+1,plainText,cipherText,s_des);
                temp[index]=1;
                Recursion(temp,index+1,plainText,cipherText,s_des);
            }
        }
    }

    //暴力破解得到第一个密钥
    public static void main(String args[]) {
        S_DES s = new S_DES();
        String cipher=s.SDES("01110110", "0010011101",0)[0];
        String[] result = crack("01110110",cipher);
        System.out.println("The endKey is:"+result[0]);
        System.out.print("Process running time: "+result[1]+"ms");
    }
}