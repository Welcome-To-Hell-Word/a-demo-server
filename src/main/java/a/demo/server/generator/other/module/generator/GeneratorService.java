package a.demo.server.generator.other.module.generator;

import org.apache.commons.io.IOUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class GeneratorService {
    public void download(List<Map<String,Object>> tables, HttpServletResponse response) {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream=new ZipOutputStream(byteArrayOutputStream);
        for (Map<String,Object>table:tables){
            generatorFile(table,zipOutputStream);
        }
        IOUtils.closeQuietly(zipOutputStream);
        byte[]data=byteArrayOutputStream.toByteArray();
        downloadFile(data,response);
    }

    private void generatorFile(Map<String,Object> table, ZipOutputStream zipOutputStream) {
        initVelocity();
        VelocityContext velocityContext=velocityContext(table);
        List<String>templatePaths=templatePaths();
        for (String templatePath:templatePaths){
            StringWriter stringWriter=new StringWriter();
            Template template=Velocity.getTemplate(templatePath,"UTF-8");
            template.merge(velocityContext,stringWriter);
            try {
                zipOutputStream.putNextEntry(new ZipEntry(filePath(templatePath,table)));
                IOUtils.write(stringWriter.toString(),zipOutputStream,"UTF-8");
                IOUtils.closeQuietly(stringWriter);
                zipOutputStream.flush();
                zipOutputStream.closeEntry();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private VelocityContext velocityContext(Map<String, Object> table) {
        VelocityContext velocityContext=new VelocityContext(){{
            put("className",table.get("className"));
            put("packageParent",table.get("packageParent"));
            put("packageModule",table.get("packageModule"));

            put("columns",new ArrayList<Map<String,Object>>(){{
                add(new HashMap<String,Object>(){{
                    put("columnName","id");
                    put("columnType",String.class);
                }});
                add(new HashMap<String,Object>(){{
                    put("columnName","username");
                    put("columnType",String.class);
                }});
                add(new HashMap<String,Object>(){{
                    put("columnName","password");
                    put("columnType",String.class);
                }});
                add(new HashMap<String,Object>(){{
                    put("columnName","photo");
                    put("columnType",String.class);
                }});
            }});
        }};
        return velocityContext;
    }

    private List<String> templatePaths() {
        return new ArrayList<String>(){{
            add("template/other/vm/entity.java.vm");
            add("template/other/vm/mapper.java.vm");
            add("template/other/vm/mapper.xml.vm");
            add("template/other/vm/service.java.vm");
            add("template/other/vm/serviceImpl.java.vm");
            add("template/other/vm/controller.java.vm");
        }};
    }

    private String filePath(String templatePath, Map<String, Object> table) {
        String filePath="";
        String className=(String)table.get("className");
        String packageName=table.get("packageParent").toString()+table.get("packageModule").toString();
        if (templatePath.contains("entity.java.vm"))
            filePath=String.format("%s/entity/%s.java",packageName,className);
        if (templatePath.contains("mapper.java.vm"))
            filePath=String.format("%s/mapper/%sMapper.java",packageName,className);
        if (templatePath.contains("mapper.xml.vm"))
            filePath=String.format("%s/mapper/xml/%sMapper.xml",packageName,className);
        if (templatePath.contains("service.java.vm"))
            filePath=String.format("%s/service/I%sService.java",packageName,className);
        if (templatePath.contains("serviceImpl.java.vm"))
            filePath=String.format("%s/service/impl/%sServiceImpl.java",packageName,className);
        if (templatePath.contains("controller.java.vm"))
            filePath=String.format("%s/controller/%sController.java",packageName,className);
        return filePath;
    }

    private void initVelocity() {
        Properties properties=new Properties();
        properties.setProperty("file.resource.loader.class","org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        properties.setProperty(Velocity.ENCODING_DEFAULT,"UTF-8");
        properties.setProperty(Velocity.OUTPUT_ENCODING,"UTF-8");
        Velocity.init(properties);
    }

    private void downloadFile(byte[] data, HttpServletResponse response) {
        response.reset();
        response.setHeader("Content-Disposition","attachment; filename=\"zip.zip\"");
        response.setContentLength(data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        try {
            IOUtils.write(data,response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
