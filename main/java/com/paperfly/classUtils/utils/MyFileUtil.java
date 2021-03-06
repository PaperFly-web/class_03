package com.paperfly.classUtils.utils;

import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.paperfly.classUtils.Listener.ExcelListener;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
public class MyFileUtil {
    /**
     * 把文件(file)流内容写入到传入的，filePath中
     * @param filePath 保存的文件路径(不是目录)
     * @param file MultipartFile类型的文件
     */
    public static void uploadFile(String  filePath, MultipartFile file) {

        // 文件对象
        File dest = new File(filePath);
        // 判断路径是否存在，如果不存在则创建
        if(!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            // 将上传文件写到服务器上指定的文件
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 传入文件名字，获取文件扩展名（带.）
     * @param filePath
     * @return
     */
    public static String getExtName(String filePath){
        String fileType=filePath.substring(filePath.lastIndexOf("."));
        return fileType;
    }

    /**
     * 传入文件路径，获取文件名称
     * @param filePath
     * @return
     */
    public static String getFileName(String filePath){
        filePath=filePath.substring(filePath.lastIndexOf("/")).replace("/","");
        return filePath;
    }
    /**
     * 传入excel文件流，获取excel中的数据
     * @param file
     * @return
     */
    public static   R readExcel( MultipartFile file) {
        log.info("文件类型:"+file.getContentType());
        if(!file.getContentType().equals("application/vnd.ms-excel")){
            return R.error("文件不是excel").put("code",444);
        }
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();

            ExcelReader excelReader = new ExcelReader(file.getInputStream(), ExcelTypeEnum.XLS, null, listener);
            excelReader.read();
            List<List<String>> data = listener.getData();
            if(data.size()==0){
                return R.notFound("没有发现数据");
            }
            //把表头去掉，如果需要表头的话，可以把这句话删除掉
//            data.remove(0);
            return R.ok().put("data",data);
        } catch (Exception e) {
            e.printStackTrace();
            return  R.error("error");
        } finally {
            try {
                file.getInputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void downloadExcel(HttpServletResponse response, List list, Class c, String fileName){
        //设置文件名字
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        //设置响应文件类型
        response.setContentType("application/msexcel;charset=UTF-8");
        response.setStatus(HttpStatus.SC_OK);
        ServletOutputStream out=null;
        try {
            out= response.getOutputStream();

            ExcelWriter excelWriter =new ExcelWriter(out, ExcelTypeEnum.XLS, true);
            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0, c);

            // 第一个 sheet 名称
            sheet1.setSheetName("sheet1");
            excelWriter.write(list,sheet1);

            // 将上下文中的最终 outputStream
            excelWriter.finish();

            out.flush();
        } catch (IOException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }finally {
            try {
                // 关闭流
                out.close();
            } catch (IOException e) {
                response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
                e.printStackTrace();
            }
        }
    }

    public static void downloadExcelAndOpenStream(HttpServletResponse response, List list, Class c, String fileName){
        //设置文件名字
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        //设置响应文件类型
        response.setContentType("application/msexcel;charset=UTF-8");
        response.setStatus(HttpStatus.SC_OK);
        ServletOutputStream out=null;
        try {
            out= response.getOutputStream();

            ExcelWriter excelWriter =new ExcelWriter(out, ExcelTypeEnum.XLS, true);
            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0, c);

            // 第一个 sheet 名称
            sheet1.setSheetName("sheet1");
            excelWriter.write(list,sheet1);

            // 将上下文中的最终 outputStream
            excelWriter.finish();

            out.flush();
        } catch (IOException e) {
            response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            e.printStackTrace();
        }
    }
}
