//import java.util.Base64;
import java.security.MessageDigest;
import sun.misc.BASE64Encoder;
//import java.utils.Base64;
import java.time.Instant;
import org.apache.commons.codec.binary.Base64;


public static byte[] encode(String message) {
   if (message == null)
       return "".getBytes();
   try {
       MessageDigest md5 = MessageDigest.getInstance("MD5");
       return md5.digest(message.getBytes());
   } catch (NoSuchAlgorithmException e) {
       Ln.e(e);
   }
   return null;
}

public static String getMessageDigest(String message) {
        byte[] md5Msg = encode(message);
//        Base64.Encoder encoder = Base64.getEncoder();
//        BASE64Encoder encoder = new BASE64Encoder();
        Base64 base64 = new Base64();
        String result = base64.encodeToString(md5Msg);
//        String result = Base64.encodeToString(md5Msg, Base64.NO_WRAP);
        result = result.replace("/", "_").replace("=", "").replace("+", "-");
        return result;
}
public static String getMessage(String utime,String pathstr,String skey){
        return utime+" "+pathstr+" "+skey;

}

//log.info("*****加密*****");
long unixTimestamp= Instant.now().getEpochSecond();
long unixTimestamp30= unixTimestamp+30*60;
String unxt = String.valueOf(unixTimestamp30);
//String pathx = bsh.args[0];
String pathx = sampler.getName();
String msg = getMessage(unxt,pathx,"the_scret_key");
//log.info(unxt);
//log.info(msg);
String md5Str = getMessageDigest(msg);
vars.put("expires",unxt);
vars.put("md5",md5Str);
