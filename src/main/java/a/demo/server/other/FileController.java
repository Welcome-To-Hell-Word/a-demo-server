package a.demo.server.other;

import a.demo.server.tools.TheResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RequestMapping("file")
@RestController
public class FileController {
    @RequestMapping(value = "files-up-load")
    public String FilesUpLoad(HttpServletRequest request){
        List<String>paths=new ArrayList<>();
        String path=System.getProperty("user.dir")+"/src/main/resources/static/file/".replace("/",File.separator);
        try {
            MultipartHttpServletRequest multipartHttpServletRequest=(MultipartHttpServletRequest)request;
            Iterator iterator=multipartHttpServletRequest.getFileNames();
            while (iterator.hasNext()){
                String name=iterator.next().toString();
                MultipartFile multipartFile=multipartHttpServletRequest.getFile(name);
                System.err.println(multipartFile.getOriginalFilename());
                String filePath=path+multipartFile.getOriginalFilename();
                multipartFile.transferTo(new File(filePath));
                paths.add(filePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TheResult().successString();
    }
}
