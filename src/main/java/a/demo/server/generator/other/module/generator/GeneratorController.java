package a.demo.server.generator.other.module.generator;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("generator")
@RestController
public class GeneratorController {
    private final GeneratorService generatorService;

    @GetMapping("download/{tablesName}")
    public void download(@PathVariable("tablesName")String tablesName,HttpServletResponse response) {
        List<Map<String,Object>>tables=new ArrayList<>();
        String[]tableNames=tablesName.split(",");
        for (int i=0;i<tableNames.length;i++){
            String tableName=tableNames[i];
            tables.add(new HashMap<String,Object>(){{
                put("tableName",tableName);
                put("className",toClassName(tableName));

                put("packageParent","a.demo.server");
                put("packageModule","module");
            }});
        }
        generatorService.download(tables,response);
    }
    private String toClassName(String tableName){
        String result="";
        String[]parts=tableName.split("_");
        for (String part:parts){
            char[]chars=part.toCharArray();
            int after=chars[0]-32;
            if ((48<=after&&after<=57)||(65<=after&&after<=90)||(97<=after&&after<=122))
                chars[0]-=32;
            result+=new String(chars);
        }
        return result;
    }
}
