package HomeWork01;

//轮函数
public class RoundF {

    //交换运算
    public int[] Swap(int[] input){
        int[] output = new int[8];
        for(int i=0;i<4;i++){
            output[i]=input[i+4];
            output[i+4]=input[i];
        }
        return output;
    }

    //EPBox拓展
    public int[] EPBox(int[] n) {
        int[] extendedBits = new int[8];
        extendedBits[0]=n[3];extendedBits[6]=n[3];
        extendedBits[1]=n[0];extendedBits[7]=n[0];
        extendedBits[2]=n[1];extendedBits[4]=n[1];
        extendedBits[3]=n[2];extendedBits[5]=n[2];
        return extendedBits;
    }

    //异或运算
    public int[] XOR(int[] num1,int[] num2) {
        int[] result = new int[num1.length];
        for(int i=0;i<num1.length;i++) {
            if(num1[i]!=num2[i]) {
                result[i]=1;
            }
            else {
                result[i]=0;
            }
        }
        return result;
    }

    //SBox压缩
    public int[] SBoxs(int[] n) {
        //分割数组
        int[] temp1 = new int[4];
        int[] temp2 = new int[4];
        for(int i=0;i<4;i++) {
            temp1[i]=n[i];
            temp2[i]=n[i+4];
        }
        //查表
        String table1[][] = new String[][]{
                {"01","00","11","10"},
                {"11","10","01","00"},
                {"00","10","01","11"},
                {"11","01","00","10"}};
        String table2[][] = new String[][]{
                {"00","01","10","11"},
                {"10","11","01","00"},
                {"11","00","01","10"},
                {"10","01","00","11"}};
        int row1 = temp1[0]*2+temp1[3];
        int column1 = temp1[1]*2+temp1[2];
        int row2 = temp2[0]*2+temp2[3];
        int column2 = temp2[1]*2+temp2[2];
        //合并
        String result = new String();
        result = table1[row1][column1]+table2[row2][column2];
        int[] compressed_bits = new int[4];
        for(int i=0;i<4;i++) {
            compressed_bits[i]=Integer.parseInt(result.substring(i,i+1));
        }
        return compressed_bits;
    }

    //SPbox置换
    public int[] SPBox(int[] n) {
        int[] temp = new int[4];
        temp[0]=n[1];
        temp[1]=n[3];
        temp[2]=n[2];
        temp[3]=n[0];
        return temp;
    }

    //轮函数
    public int[] RoundFun(int[] roundData,int[] roundKey) {
        //分割数组
        int[] left = new int[4];
        int[] right = new int[4];
        for(int i=0;i<4;i++) {
            left[i]=roundData[i];
            right[i]=roundData[i+4];
        }
        //右半数组进行F运算
        int [] changedRight = SPBox(SBoxs(XOR(EPBox(right),roundKey)));
        //运算后的右半数组与左半异或
        left = XOR(left,changedRight);
        //合并结果
        int[] roundOutput = new int[8];
        for(int i=0;i<4;i++){
            roundOutput[i]=left[i];
            roundOutput[i+4]=right[i];
        }
        return roundOutput;
    }

    //字符串型转化为整型
    public int[] StringToArray(String s) {
        int[] Input = new int[s.length()];
        for(int i=0;i<s.length();i++) {
            Input[i]=Integer.parseInt(s.substring(i,i+1));
        }
        return Input;
    }
}
