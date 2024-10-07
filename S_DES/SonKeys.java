package HomeWork01;

public class SonKeys {

    //Leftshift置换1
    public String LeftShift1(String haftText){
        String  newHaftText = new String();
        int leftShift1[] = {1,2,3,4,0};
        for(int i=0;i<5;i++){
            newHaftText+=haftText.charAt(leftShift1[i]);
        }
        return newHaftText;
    }

    //Leftshift置换2
    public String LeftShift2(String halfText){
        String  newHalfText = new String();
        int leftShift2[] = {2,3,4,0,1};
        for(int i=0;i<5;i++){
            newHalfText+=halfText.charAt(leftShift2[i]);
        }
        return newHalfText;
    }

    //P10置换
    public String P10(String originKey){
        String  str = new String();
        int p10[] = {2,4,1,6,3,9,0,8,7,5};
        for(int i=0;i<10;i++){
            str+=originKey.charAt(p10[i]);
        }
        return  str;
    }

    //P8置换
    public String P8(String lastText){
        String  str = new String();
        int p8[] = {5,2,6,3,7,4,9,8};
        for(int i=0;i<8;i++){
            str+=lastText.charAt(p8[i]);
        }
        return  str;
    }

    //密钥生成，生成子密钥k1、k2
    public String[] Key(String originKey){
        String str = P10(originKey);
        String left1 = LeftShift1(str.substring(0,5));
        String right1 = LeftShift1(str.substring(5,10));

        String key1 = P8(left1+right1);

        String left2 = LeftShift2(left1);
        String right2 = LeftShift2(right1);

        String key2 = P8(left2+right2);

        String key[] = {key1,key2};

        return key;
    }
}
