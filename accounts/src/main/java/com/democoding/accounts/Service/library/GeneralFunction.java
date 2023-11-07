package com.democoding.accounts.Service.library;

import com.democoding.accounts.demo.Url;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class GeneralFunction {

    Logger log = LoggerFactory.getLogger(GeneralFunction.class);

    public String exportXLS(Map map, List<Object[]> result) throws IOException {
        try {
            String reportName = (String) map.get("reportName");
            List<String> label = (List) map.get("label");
            List<String> text = (List) map.get("text");
            List<String> tableHeader = (List) map.get("tableHeader");
            List<String> subDetailLabel = (List) map.get("subDetailLabel");
            List<String> subDetailValue = (List) map.get("subDetailValue");

//			Workbook wb = new HSSFWorkbook();
            Workbook wb = new XSSFWorkbook();
            Sheet sheet = wb.createSheet(reportName);

            Row headerRow = sheet.createRow(0);
            Cell headerCell = headerRow.createCell(0);
            headerCell.setCellValue(reportName);

            int labelRowStart = 3;
            for (int i = 0; i < label.size(); i++) {
                Row labelRow = sheet.createRow(labelRowStart);
                String lbl = label.get(i);
                String txt = text.get(i);

                Cell labelCell = labelRow.createCell(0);
                labelCell.setCellValue(lbl);

                Cell textCell = labelRow.createCell(1);
                textCell.setCellValue(txt);

                labelRowStart++;
            }

            if (subDetailLabel != null) {
                for (int i = 0; i < subDetailLabel.size(); i++) {
                    Row labelRow = sheet.createRow(labelRowStart);
                    String lbl = subDetailLabel.get(i);
                    String txt = subDetailValue.get(i);

                    Cell labelCell = labelRow.createCell(0);
                    labelCell.setCellValue(lbl);

                    Cell textCell = labelRow.createCell(1);
                    try {
                        textCell.setCellValue(Double.parseDouble(txt));
                    } catch (Exception e) {
                        try {
                            textCell.setCellValue(txt);
                        } catch (Exception ee) {
                            textCell.setCellValue("");
                        }

                    }

                    labelRowStart++;
                }
            }

            CellStyle style = wb.createCellStyle();
            style.setBorderBottom(HSSFCellStyle.BORDER_MEDIUM);
            style.setBorderTop(HSSFCellStyle.BORDER_MEDIUM);
            style.setBorderRight(HSSFCellStyle.BORDER_MEDIUM);
            style.setBorderLeft(HSSFCellStyle.BORDER_MEDIUM);

            int row = labelRowStart + 2;
            Row tableHeaderRow = sheet.createRow(row);


            int column = 0;
            for (int i = 0; i < tableHeader.size(); i++) {
                String header = tableHeader.get(i);
                Cell tableHeaderCell = tableHeaderRow.createCell(column);
                tableHeaderCell.setCellValue(header);
                tableHeaderCell.setCellStyle(style);
                column++;
            }

            int freezePaneRowStart = row;

            row++;
            for (Object[] res : result) {
                Row dataRow = sheet.createRow(row);
                column = 0;
                for (Object object : res) {
//					Object string ;
                    Cell dataCell = dataRow.createCell(column);
                    try {
                        dataCell.setCellValue(Double.parseDouble(object.toString()));
                    } catch (Exception e) {
                        try {
                            dataCell.setCellValue(object.toString());
                        } catch (Exception ee) {
                            dataCell.setCellValue("");
                        }

                    }
                    dataCell.setCellStyle(style);
                    column++;
                }
                row++;
            }

            for (int i = 0; i < tableHeader.size(); i++) {
                sheet.autoSizeColumn(i);
            }


            String maxColumn = "";
            int totalCols = tableHeader.size();
            while (totalCols > 0) {
                if (totalCols - 26 > 0) {
                    maxColumn += "A";
                } else {
                    maxColumn += Character.toString((char) (totalCols + 65 - 1));
                }
                totalCols -= 26;
            }

            maxColumn += row;
            freezePaneRowStart += 1;
            String autoFilterAddress = "A" + freezePaneRowStart + ":" + maxColumn;

            sheet.createFreezePane(0, freezePaneRowStart);
            sheet.setAutoFilter(CellRangeAddress.valueOf(autoFilterAddress));

            log.debug("Workbook Finish, attempt to generate file");
            String fileName = createFile(wb);
            return Url.reportUrl + encrypt(fileName);
        } catch (Exception e) {
            return "Error Generating Report! " + e.getMessage();
        }

    }

    public String encrypt(String text) {
        try {
            byte[] bytesEncoded = Base64.encodeBase64(text.getBytes());
            text = new String(bytesEncoded);
            text = text.replace("/", "----");
            return text;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public String createFile(Workbook wb) {
        String workingDir = this.getBaseUrl();


        String rootReportDir = this.addSeparator(workingDir, "report");
        String subRootReportDir = this.addSeparator(rootReportDir, "tmp");
        String fileName = String.valueOf(new Date().getTime());
        String outputDirPath = this.addSeparator(subRootReportDir, fileName + ".xlsx");

        log.debug("Output Directory:" + outputDirPath);

        File directory = new File(String.valueOf(subRootReportDir));
        directory.mkdirs();

        try {
            FileOutputStream fileOut = new FileOutputStream(outputDirPath);
            wb.write(fileOut);
            fileOut.close();
        } catch (Exception e) {
            log.debug("Error FileOutputStream : " + e.getMessage());
        }
        return fileName;
    }

    public String getBaseUrl() {
        String directory;

        if (SystemUtils.IS_OS_WINDOWS) {
            directory = System.getProperty("user.dir");
        } else {
            if (Constants.SERVER_CONTROL == 1) {

                directory = "/home/itciti/webapps";
            } else {
                directory = System.getProperty("user.home");
                directory = this.addSeparator(directory, "webapps");
            }
        }

        return directory;
    }

    public String addSeparator(String text, String addText) {
        String[] splitter = text.split("/");
        if (splitter.length == 1) { //format slash nya \
            text += "\\" + addText;
        } else { //format slash nya /
            text += "/" + addText;
        }
        return text;
    }

    public static String decrypt(String text) {
        try {
            text = text.replace("----", "/");
            byte[] bytesEncoded = Base64.decodeBase64(text.getBytes());
            text = new String(bytesEncoded);
            String[] splitter = text.split("/");
            text = splitter[splitter.length - 1];
            return text;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
