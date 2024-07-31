package com.keyware.MR.Emulator;

import java.math.BigDecimal;

public class Fault {

    public static String calculationData(String max,String min) {
//       1。 生成一个个位随机整数，根据最小值的位数给该随机数扩大或缩小10的n次方倍或直接生成个位数 2。 如果随机数比最小值大 则递归调用该方法 反之执行selectMinOrMax（） 3。 selectMinOrMax（）将该随机数取余  随机决定最终数值是 min-随机数 / max➕随机数
        int v = (int)(Math.random() * 10);
        BigDecimal deviation = null;
        String outputData = null;
        if (min.contains(".")){
            deviation = new BigDecimal(v).divide(new BigDecimal(Math.pow(10, min.length() - min.indexOf(".") - 1)));;
        }else if (min.equals("1")){
            deviation = new BigDecimal(v).divide(new BigDecimal(10));
        }else {
            deviation = new BigDecimal(v);
        }
        if (new BigDecimal(min).subtract(deviation).doubleValue()<=0){
            outputData=calculationData(max,min);
        }else {
            outputData=selectMinOrMax(v,deviation,max,min);
        }
        return outputData;
    }
   private static String selectMinOrMax(int v,BigDecimal deviation,String max,String min){
       BigDecimal outdata = null;
       System.out.println(deviation);
       if (v%2==1){
           System.out.println("低于正常值");
           outdata = new BigDecimal(min).subtract(deviation);
        }else {
           System.out.println("高于正常值");
           outdata = new BigDecimal(max).add(deviation);
        }
        return  outdata.toString();
    }
}
