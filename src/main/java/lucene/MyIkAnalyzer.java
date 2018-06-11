package lucene;

import java.io.Reader;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.util.IOUtils;


public class MyIkAnalyzer extends Analyzer {

     @Override
    protected TokenStreamComponents createComponents(String arg0) {
        Reader reader=null;
        try{
            reader=new StringReader(arg0);
            MyIKTokenizer it = new MyIKTokenizer(reader);
            return new Analyzer.TokenStreamComponents(it);
        }finally {
            IOUtils.closeWhileHandlingException(reader);
        }
    }

   /* @Override
    protected TokenStreamComponents createComponents(String s, Reader reader) {
        try{
            reader=new StringReader(s);
            MyIKTokenizer it = new MyIKTokenizer(reader);
            return new Analyzer.TokenStreamComponents(it);
        }finally {
            IOUtils.closeWhileHandlingException(reader);
        }
    }*/


    public MyIkAnalyzer() {
        super();
    }

    public MyIkAnalyzer(ReuseStrategy reuseStrategy) {
        super(reuseStrategy);
    }



    @Override
    protected Reader initReader(String fieldName, Reader reader) {
        return super.initReader(fieldName, reader);
    }

    @Override
    public int getPositionIncrementGap(String fieldName) {
        return super.getPositionIncrementGap(fieldName);
    }

    @Override
    public int getOffsetGap(String fieldName) {
        return super.getOffsetGap(fieldName);
    }

    @Override
    public void close() {
        super.close();
    }



}
