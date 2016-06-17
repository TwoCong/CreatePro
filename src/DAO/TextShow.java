package DAO;

import java.util.regex.Pattern;

/**
 * Created by Administrator on 2016/3/13.
 */
public class TextShow {
    protected String startTag = "<font color=red>";
    protected String endTag = "</font>";
    protected int maxNumFragmentsRequired=5;
    protected int textFragmenterlegth=150;
    protected String PatternText ="(?=(?:"+startTag+"[\\s\\S]+?"+endTag+")+)";;

    /*    public String gettextfragmenter(String input)
        {
            //×î¶à±£Áô¼¸¶Î
            int _maxNumFragmentsRequired = maxNumFragmentsRequired;
            String result = "";
            Pattern pattern = Pattern.compile(PatternText, Pattern.MULTILINE);
    //Ê¹ÓÃÉÏÃæ¶¨ÒåµÄÕýÔò½«ÎÄ±¾·Ö¸î,¹Ø¼ü²¿·Ö
            String[] textfragmenter = pattern.split(input);
    //Èç¹û¶¨ÒåµÄ¶ÎÊý´óÓÚ·Ö¸îºóµÄÊµ¼Ê¶ÎÊý
            if(textfragmenter.length<_maxNumFragmentsRequired)
            {
                _maxNumFragmentsRequired = textfragmenter.length;
            }
    //ÏÂÃæÒ»¸öÑ­»·½«½á¹ûÖØ×é³ÉÎÒÃÇÐèÒªµÄ½á¹û
            for(int i=0;i<_maxNumFragmentsRequired;i++)
            {
                String str = textfragmenter[i].length()>textFragmenterlegth ? textfragmenter[i].substring(0, textFragmenterlegth)+"": textfragmenter[i];
                result +=str;
            }
            return result;
        }*/
//////5.26ÐÞ¸Ä²¿·Ö
    public String gettextfragmenter(String input,String keyword)
    {
        String result = "";
        if(input.contains(keyword)){
            int _maxNumFragmentsRequired = maxNumFragmentsRequired;
            Pattern pattern = Pattern.compile(keyword);
            String[] text = pattern.split(input);

            if(text[0]!=null&&text[1]!=null){
                String str1=text[0];
                String str2=text[1];
                if(text[0].length()>=20){
                    str1 = text[0].substring(text[0].length()-19);
                }
                if(text[1].length()>=20){
                    str2 = text[1].substring(0,19);
                }
                result = str1+keyword+str2;
            }
        }
        else result = null;
        return result;
    }

    //doc :document fields:ËÑË÷µÄfieldsnameµÄ¼¯ºÏ words:ËÑË÷µÄ¹Ø¼ü´Ê¼¯ºÏ
//    public Document highlighter(Document doc,String[] fields,String[] words)
//    {
//        if(words!=null)
//        {
//            String deleteTag = endTag + startTag;
//            String[] hlwords = new String[words.length];
//            for(int i=0;i<words.length;i++)
//            {
//                hlwords[i] = startTag + words[i] + endTag;
//            }
//            for(String field : fields) {
//                int j=0;
//                Field fField = (Field) doc.getField(field);
////ÕâÀï¾ÍÊÇÎÒËµµÄÌæ»»µôËùÓÐµÄhtml±êÇ©
//                String value = fField.stringValue().replaceAll("<[^>]+>|&[^;]+;","");
//                for(int i=0;i<words.length;i++)
//                {
//                    if(value != null && value.length() > words[i].length()) {
//                        value = value.replaceAll(words[i],hlwords[i]);
//                        j++;
//                    }
//                }
//                if(j > 0) {
//                    if(j > 1) value = value.replaceAll(deleteTag, "");
////µ÷ÓÃÎÒËµ µÄ·Ö¸îÖØ×é·½·¨
//                    value = gettextfragmenter(value);
//                   // fField.setValue(value);
//                    fField.setStringValue(value);
//                }
//            }
//        }
//        return doc;
//    }
    public String highlighter(String text,String[] words)
    {
        String value = null;
        if(text!=null){
            if(words!=null)
            {
                String deleteTag = endTag + startTag;
                String[] hlwords = new String[words.length];
                for(int i=0;i<words.length;i++)
                {
                    hlwords[i] = startTag + words[i] + endTag;
                }
                int j=0;
//ÕâÀï¾ÍÊÇÎÒËµµÄÌæ»»µôËùÓÐµÄhtml±êÇ©
                value = text.replaceAll("<[^>]+>|&[^;]+;", "");
                for(int i=0;i<words.length;i++)
                {
                    if(value != null && value.length() > words[i].length()) {
                        value = value.replaceAll(words[i],hlwords[i]);
                        j++;
                    }
                }
                if(j > 0) {
                    if(j > 1) value = value.replaceAll(deleteTag, "");
//µ÷ÓÃÎÒËµ µÄ·Ö¸îÖØ×é·½·¨
                }
            }
        }

        return value;
    }
}
