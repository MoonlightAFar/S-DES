package HomeWork01;

public class S_DES {

    //state=0表示加密，state=1表示解密
    public static String[] SDES(String plainText,String key,int state) {
        RoundF roundF = new RoundF();
        IP IP = new IP();
        SonKeys sonKeys = new SonKeys();
        //初始IP置换
        plainText = IP.IP(plainText);
        //轮密钥生成
        String[] Keys = sonKeys.Key(key);
        int[] PT = roundF.StringToArray(plainText);
        int[] key1 = roundF.StringToArray(Keys[0]);
        int[] key2 = roundF.StringToArray(Keys[1]);
        //密文或明文生成
        int[] cipherArray = new int[8];
        if(state==0) {
            cipherArray = roundF.RoundFun(roundF.Swap(roundF.RoundFun(PT,key1)),key2);
        }
        else {
            cipherArray = roundF.RoundFun(roundF.Swap(roundF.RoundFun(PT,key2)),key1);
        }
        String cipherText = new String();
        for(int i=0;i<cipherArray.length;i++) {
            cipherText += cipherArray[i];
        }
        //最终IP逆置换
        cipherText = IP.IPNi(cipherText);
        //输出
        String[] output = new String[]{cipherText,key};
        return output;
    }


}
