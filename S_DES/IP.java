package HomeWork01;

public class IP {

    //IP置换
    public String IP(String plainText){
        String str = new String();
        int ip[] = {1,5,2,0,3,7,4,6};
        for(int i=0;i<8;i++){
            str+=plainText.charAt(ip[i]);
        }
        return str;
    }

    //IP逆置换
    public String IPNi(String lastText){
        String str = new String();
        int ipNi[] = {3,0,2,4,6,1,7,5};
        for(int i=0;i<8;i++){
            str+=lastText.charAt(ipNi[i]);
        }
        return str;
    }
}
