package com.xx.service;


import com.xx.mapper.DutyMapper;
import com.xx.pojo.Duty;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class DutyService {

    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    @Autowired
    private DutyMapper dutyMapper;


    @Transactional
    public void readExcel(MultipartFile file) {
        List<Duty> list = new ArrayList<>();
        //检查文件
        checkFile(file);
        //获得workbook工作簿
        Workbook workBook = getWorkBook(file);

        //获取第一个表
        Sheet sheet = workBook.getSheetAt(0);
        //获取行数
        int lastRowNum = sheet.getLastRowNum();

        for (int i = 1; i <= lastRowNum; i++) {

            Row row = sheet.getRow(i);
            Duty duty = new Duty();
            if (row.getCell(0) != null) {
                row.getCell(0).setCellType(CellType.STRING);
                duty.setWeeks(row.getCell(0).getStringCellValue());
            }
            if (row.getCell(1) != null) {
                row.getCell(1).setCellType(CellType.STRING);
                duty.setTime(row.getCell(1).getStringCellValue());
            }
            if (row.getCell(2) != null) {
                row.getCell(2).setCellType(CellType.STRING);
                duty.setType(row.getCell(2).getStringCellValue());
            }
            if (row.getCell(3) != null) {
                row.getCell(3).setCellType(CellType.STRING);
                duty.setMon(row.getCell(3).getStringCellValue());
            }
            if (row.getCell(4) != null) {
                row.getCell(4).setCellType(CellType.STRING);
                duty.setTue(row.getCell(4).getStringCellValue());
            }
            if (row.getCell(5) != null) {
                row.getCell(5).setCellType(CellType.STRING);
                duty.setWed(row.getCell(5).getStringCellValue());
            }
            if (row.getCell(6) != null) {
                row.getCell(6).setCellType(CellType.STRING);
                duty.setThu(row.getCell(6).getStringCellValue());
            }
            if (row.getCell(7) != null) {
                row.getCell(7).setCellType(CellType.STRING);
                duty.setFri(row.getCell(7).getStringCellValue());
            }
            if (row.getCell(8) != null) {
                row.getCell(8).setCellType(CellType.STRING);
                duty.setSat(row.getCell(8).getStringCellValue());
            }
            if (row.getCell(9) != null) {
                row.getCell(9).setCellType(CellType.STRING);
                duty.setSun(row.getCell(9).getStringCellValue());
            }
            list.add(duty);
        }
        for (Duty duty : list) {
            dutyMapper.insert(duty);
        }


    }


    //检验文件是否合法
    private static void checkFile(MultipartFile file) {
        //判断文件是否存在
        if (null == file) {
            try {
                throw new FileNotFoundException("文件不存在");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        //获取文件名
        String filename = file.getOriginalFilename();
        if (!filename.endsWith(xls) && !filename.endsWith(xlsx)) {
            try {
                throw new IOException(filename + "不是一个excel文件");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //获得excel实现类对象
    public static Workbook getWorkBook(MultipartFile file) {
        //获取文件名
        String filename = file.getOriginalFilename();
        //创建工作簿对象代表整个excel
        Workbook workbook = null;
        try {
            //获取excel的io流
            InputStream is = file.getInputStream();
            //根据文件的不同后缀名创建不同的workbook对象
            if (filename.endsWith(xls)) {
                workbook = new HSSFWorkbook(is);
            } else if (filename.endsWith(xlsx)) {
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return workbook;
    }
}
