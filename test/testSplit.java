
import java.util.ArrayList;

public class testSplit {
    public static void main(String [] args){
        String lsValue = "ONLINE@M00119000001@2019-06-15@M0X119000001@SAMP@150@1234@869592033772571»GAP0190362»0011800067804»+639270359404";
        
        String lasArr1 [] = lsValue.split("»");
        String lasArr2 [];
        
        ArrayList lasVal = new ArrayList();

        for (int lnCtr = 0; lnCtr <= lasArr1.length -1; lnCtr++){
            if (lnCtr == 0){
                lasArr2 = lasArr1[0].split("@");
                
                for (int x = 0; x <= lasArr2.length -1; x++){
                    lasVal.add(lasArr2[x]);
                }
            } else {
                lasVal.add(lasArr1[lnCtr]);
            }
        } 
        
        for (int y = 0; y <= lasVal.size() -1; y++){
            System.out.println(lasVal.get(y));
        }
    }
}
