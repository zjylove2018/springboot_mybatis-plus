package cn.zjy.dayong.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    private static DemoApplicationTests demoApplicationTests = new DemoApplicationTests();
    
    public DemoApplicationTests() {
    }

    @Test
    public void contextLoads() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(list);
        list.remove("c");
        System.out.println(list);

        if(list.contains("b")){
            System.out.println("b已在集合中");
        }else {
            list.add("b");
            System.out.println("将b添加到集合中成功!");
        }
    }

    /**
     * HashMap的键和值可以为null
     */
    @Test
    public void run(){
        Map<String,String> map = new HashMap<>();
        map.put("a","100");
        map.put(null,"200");
        map.put("c",null);
        System.out.println(map);
    }

    /**
     * Hashtable的键和值不可以为null
     */
    @Test
    public void run1(){
        Map<String,String> map = new Hashtable<>();
        map.put("a","100");
        map.put(null,"200");
        map.put("c",null);
        System.out.println(map);
    }

    @Test
    public void run2(){
        TreeMap<Integer,String> map = new TreeMap<>();
        map.put(60,"小明");
        map.put(90,"小红");
        map.put(70,"小王");
        map.put(100,"小李");
        System.out.println(map);
    }

    @Test
    public void run3(){
        StringBuilder sb = new StringBuilder();
        String s1 = "";
        String s2 = "";
        for (int i = 0; i <3 ; i++) {
            sb.append(i+"这是I");
            for (int j = 0; j <5 ; j++) {
                sb.append("\r\n");
                sb.append(j+"这是J");
                sb.append("\r\n");
            }
        }
        System.out.print(sb);
    }

    @Test
    public void getYear(){
        int a = 730;
        Integer days = a * 7;
        System.out.println(days/365);
    }

    public static void main(String[] args) {
        int a = 730;
        Integer days = a * 7;
        System.out.println(days/365);
    }

    @Test
    public void run4(){
        Stream stream = Stream.of("a","b","c");
        System.out.println(stream);
        String [] str = new String[]{"a","b","c"};
        stream = Stream.of(str);
        System.out.println(stream);
        stream = Arrays.stream(str);
        System.out.println(stream);
        List<String> list = Arrays.asList(str);
        stream = list.stream();
        System.out.println(stream);
    }

    @Test
    public void run5(){
        IntStream.of(new int[]{1, 2, 3}).forEach(System.out::println); //123
        IntStream.range(1, 3).forEach(System.out::println); //12
        IntStream.rangeClosed(1, 3).forEach(System.out::println);   //123
    }

    @Test
    public void run6(){
        List<Integer> nums = Arrays.asList(1,2,3,4);
        List<Integer> squareNums = nums.stream().
                map(n -> n*n).
                collect(Collectors.toList());
        System.out.println(squareNums); //[1, 4, 9, 16]
    }

    @Test
    public void run7(){
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2,3),
                Arrays.asList(4,5,6)
        );
       System.out.println(inputStream); //java.util.stream.ReferencePipeline$Head@4fb56bea
    }

    @Test
    public void run8(){
        Integer [] num = {1,2,3,4,5,6,7,8,9,10};
        Integer [] evens = Stream.of(num).filter(n -> n%2 == 0).toArray(Integer[]::new);
        System.out.println(evens);  //[Ljava.lang.Integer;@220c9a63
        for (int i = 0; i <evens.length ; i++) {
            System.out.println(evens[i]);   //2 4 6 8 10
        }
    }

    @Test
    public void run9(){
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("a","b","c","d").reduce("",String::concat);
        System.out.println(concat); //abcd

        // 求最小值，minValue = -5.0
        double minValue = Stream.of(-1.5,1.0,5.0,-5.0).reduce(Double.MAX_VALUE,Double::min);
        System.out.println(minValue);   //-5.0

        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1,2,3,4).reduce(0,Integer::sum);
        System.out.println(sumValue);   //10
    }

    @Test
    public void run10(){
        String s = "E:\\ykkwork\\ykk-client\\Template\\InputData_{part-of-day}_template.xlsx";
        String s2 = "E:\\ykkwork\\ykk-client\\Template\\InputData_{file-name}_template.xlsx";
        Map<String,String> map = new HashMap<>();
        map.put("张三","北京");
        map.put("李四","天津");
        map.put("file-name","南京");
        map.put("part-of-day", "西安");
        String string = replace(s,map);
        String string2 = replace(s2,map);
        System.out.println(string);
        System.out.println(string2);
    }

    public String replace(String source, Map<String, String> params) {
        String result = source;
        for (Map.Entry<String, String> param : params.entrySet()) {
            result = result.replace("{" + param.getKey() + "}", param.getValue());
        }
        return result;
    }

    @Test
    public void run11(){

    }
}
