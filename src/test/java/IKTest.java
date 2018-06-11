import org.apache.commons.lang.StringUtils;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.StringReader;

/**
 * Demo
 * Create by cy on 2018/5/11
 **/
public class IKTest {


        /**
         * 分词器分词
         *
         * @param text
         * @param analyzer
         * @throws Exception
         */
        private static String token(String text, boolean isMaxWordLength)
                throws Exception {
            try {
                java.util.List<String> list = new java.util.ArrayList<String>();
                IKSegmenter ikSegmenter = new IKSegmenter(new StringReader(text), isMaxWordLength);
                Lexeme lexeme;
                while ((lexeme = ikSegmenter.next()) != null) {
                    list.add(lexeme.getLexemeText());
                }
                return StringUtils.join(list, "|");
            } catch (Exception e) {
                throw e;
            }
        }


        public static void main(String[] args) throws Exception {
            String text = "中国人民解放军";
            // String text = "希腊总理：相信希腊人民希望留在欧元区";


            System.out.println(token(text, false));
            System.out.println(token(text, true));


    }
}
