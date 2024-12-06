package com.example.doanstudy.PassWordEncoder;

import com.example.doanstudy.model.entity.AdminEntity;
import com.example.doanstudy.service.AdminService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BCryptPasswordEncoder2 implements PasswordEncoder {
    private Pattern BCRYPT_PATTERN;
    private final Log logger;
    private final int strength;
    private final org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion version;
    private final SecureRandom random;

    public BCryptPasswordEncoder2() {
        this(-1);
    }

    public BCryptPasswordEncoder2(int strength) {
        this(strength, (SecureRandom)null);
    }

    public BCryptPasswordEncoder2(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion version) {
        this(version, (SecureRandom)null);
    }

    public BCryptPasswordEncoder2(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion version, SecureRandom random) {
        this(version, -1, random);
    }

    public BCryptPasswordEncoder2(int strength, SecureRandom random) {
        this(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion.$2A, strength, random);
    }

    public BCryptPasswordEncoder2(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion version, int strength) {
        this(version, strength, (SecureRandom)null);
    }

    public BCryptPasswordEncoder2(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion version, int strength, SecureRandom random) {
        this.BCRYPT_PATTERN = Pattern.compile("\\A\\$2(a|y|b)?\\$(\\d\\d)\\$[./0-9A-Za-z]{53}");
        this.logger = LogFactory.getLog(this.getClass());
        if (strength == -1 || strength >= 4 && strength <= 31) {
            this.version = version;
            this.strength = strength == -1 ? 10 : strength;
            this.random = random;
        } else {
            throw new IllegalArgumentException("Bad strength");
        }
    }

    public String encode(CharSequence rawPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else {
            String salt = this.getSalt();
            return BCrypt.hashpw(rawPassword.toString(), salt);
        }
    }

    private String getSalt() {
        return this.random != null ? BCrypt.gensalt(this.version.getVersion(), this.strength, this.random) : BCrypt.gensalt(this.version.getVersion(), this.strength);
    }

    public synchronized String sha1(String s) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.reset();
        byte[] buffer = s.getBytes();
        md.update(buffer);
        byte[] digest = md.digest();

        String hexStr = "";
        for (int i = 0; i < digest.length; i++) {
            hexStr +=  Integer.toString( ( digest[i] & 0xff ) + 0x100, 16).substring( 1 );
        }
        return hexStr;
    }

    public synchronized String sha256(String s)
    {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(s.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }
    public synchronized String md5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1,3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e)
        {

        }
        return "";
    }
    private AdminService adminService;
    public void setUserId(AdminService adminService){
        this.adminService = adminService;
    }
    public synchronized String passwordEncode(String user_id, String password)
    {
        String s = sha256("[" + user_id + "]" + password);
        for( int i=0; i<password.length(); i++)
        {
            char ch = (char) (i + 48);
            s = s + ch;
        }
        s = md5(s);
        return s;
    }

    public synchronized String passwordEd(String user_id, String password)
    {
        String s = sha256("[" + user_id + "]" + password);
        for( int i=0; i<password.length(); i++)
        {
            char ch = (char) (i + 48);
            s = s + ch;
        }
        s = md5(s);
        return s;
    }

    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else if (encodedPassword != null && encodedPassword.length() != 0) {
            try {
                AdminEntity userInfo = adminService.findByPassWord(encodedPassword);
                String user_id = userInfo.getId();

                String enc = sha1(rawPassword.toString());
                enc = passwordEd(user_id,enc);
                return enc.equals(encodedPassword);
            }catch (Exception ex){
                return false;
            }

        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }

    public boolean upgradeEncoding(String encodedPassword) {
        if (encodedPassword != null && encodedPassword.length() != 0) {
            Matcher matcher = this.BCRYPT_PATTERN.matcher(encodedPassword);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Encoded password does not look like BCrypt: " + encodedPassword);
            } else {
                int strength = Integer.parseInt(matcher.group(2));
                return strength < this.strength;
            }
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }

    public static enum BCryptVersion {
        $2A("$2a"),
        $2Y("$2y"),
        $2B("$2b");

        private final String version;

        private BCryptVersion(String version) {
            this.version = version;
        }

        public String getVersion() {
            return this.version;
        }
    }
}
