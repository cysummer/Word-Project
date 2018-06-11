package lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;

public class WordUtil {

    /** 获取关键字个数 */
    private final static Integer NUM=10;

    /** 截取关键字在几个单词以上的数量 */
    private final static Integer QUANTITY=1;

    /**
     * 传入String类型的文章，智能提取单词放入list中
     */
    private static List<String> extract(String article,Integer a) throws IOException {
        List<String> list =new ArrayList<String>(); //定义一个list来接收将要截取出来单词

        Analyzer myIkAnalyzer=new MyIkAnalyzer();

//        IKAnalyzer analyzer = new IKAnalyzer(); //初始化IKAnalyzer
        TokenStream tokenStream= //调用tokenStream方法(读取文章的字符流)
                myIkAnalyzer.tokenStream("", new StringReader(article));

        tokenStream.reset();

        while (tokenStream.incrementToken()) { //循环获得截取出来的单词
            CharTermAttribute charTermAttribute = //转换为char类型
                    tokenStream.getAttribute(CharTermAttribute.class);
            String keWord= charTermAttribute.toString(); //转换为String类型
            if (keWord.length()>a) { //判断截取关键字在几个单词以上的数量(默认为2个单词以上)
                list.add(keWord); //将最终获得的单词放入list集合中
            }
        }
        return list;
    }

    /**
     * 将list中的集合转换成Map中的key，value为数量默认为1
     */
    private static Map<String, Integer> list2Map(List<String> list){
        Map<String, Integer> map=new HashMap<String, Integer>();
        for(String key:list){ //循环获得的List集合
            if (list.contains(key)) { //判断这个集合中是否存在该字符串
                map.put(key, map.get(key) == null ? 1 : map.get(key)+1);
            } //将集中获得的字符串放在map的key键上
        } //并计算其value是否有值，如有则+1操作
        return map;
    }

    /**
     * 提取关键字方法
     */
    public static String[] getKeyWords(String article,Integer a,Integer n) throws IOException {
        List<String> keyWordsList= extract(article,a); //调用提取单词方法
        Map<String, Integer> map=list2Map(keyWordsList); //list转map并计次数
        //使用Collections的比较方法进行对map中value的排序
        ArrayList<Entry<String, Integer>> list = new ArrayList<Entry<String,Integer>>(map.entrySet());
        Collections.sort(list, new Comparator<Entry<String, Integer>>() {
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                return (o2.getValue() - o1.getValue());
            }
        });
        if (list.size()<n) n=list.size(); //排序后的长度，以免获得到null的字符
        String[] keyWords=new String[n]; //设置将要输出的关键字数组空间
        for(int i=0; i< list.size(); i++) { //循环排序后的数组
            if (i<n) { //判断个数
                keyWords[i]=list.get(i).getKey(); //设置关键字进入数组
            }
        }
        return keyWords;
    }



    //返回值为String[]类型
    public static String[] getKeyWords(String article) throws IOException{
        return getKeyWords(article,QUANTITY,NUM);
    }

    public static  String readWord(String path) {
        String buffer = "";
        try {
            if (path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件！");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return buffer;
    }

    public static void main(String[] args) {

        String content = readWord("F:\\word_project\\1525426885627_照片采集标准.doc");
        String keyword = "";
        try {
            String [] keywords = getKeyWords(content);
            for(int i=0; i<keywords.length; i++){
                keyword+=keywords[i]+ "|";
            }
            System.out.println(keyword);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
