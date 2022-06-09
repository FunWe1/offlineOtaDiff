package com.hxzj.javafxboot;

import com.hxzj.javafxboot.util.StringUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JavafxBootApplicationTests {

    @Test
    public void contextLoads() {
    }
    @Test
    public void test(){
        String s = "028300C89F0607A0000003330101DF010101DF11050000000000DF12050000000000DF13050000000000DF14039F3704DF150400000000DF160100DF1701009F1B04000027109F090200309F150212349F160F3030303030303030303030303030309F4E085465726D696E616C9F1C0846726F6E743132339F1D01015F3601029F3C020156DF81010201569F3D0102DF8102039F37045F2A0201569F010512345678919F7B06000000020000DF1906000000015000DF2006000000020000DF2106000000010000DFC1080101";
        byte[] bytes = StringUtil.hexToBytes(s);
        System.out.println(StringUtil.bytesToHex(bytes, bytes.length));
    }
    @Test
    public void test2(){
        System.out.println(Integer.toHexString(1000));
    }

}
