package utils;

public class CreateVcf
{
    
    private CreateVcf(){}
    
    
    public static String buildVcf(String personName, String phone){
        String personNameCode = QuotedPrintableCode.qpEncodeing(personName);
        StringBuilder sb = new StringBuilder();
        sb.append("BEGIN:VCARD\r\n");
        sb.append("VERSION:2.1\r\n");
        sb.append("N;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:;"+personNameCode+";;;\r\n");
        sb.append("FN;CHARSET=UTF-8;ENCODING=QUOTED-PRINTABLE:"+personNameCode+"\r\n");
        sb.append("TEL;CELL:"+phone+"\r\n");
        sb.append("END:VCARD\r\n");
        return sb.toString() ;
    }
}
