package nju.software.util;


import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;

public final class ResourceUtil {
    public static String changeResourceEncoding(String[] args) throws IOException {
        Resource res = new ClassPathResource("/Users/luyu/Scp2/src/main/resources/conf/file1.txt");
        EncodedResource encodedResource = new EncodedResource(res,"UTF-8");
        String content = FileCopyUtils.copyToString(encodedResource.getReader());
        return content;
    }
}
