package prioneer.homework.excel.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import prioneer.homework.board.domain.Board;
import prioneer.homework.board.repository.HomeworkRepository;
import prioneer.homework.excel.domain.Excel;
import prioneer.homework.excel.service.ExcelService;
import prioneer.homework.info.domain.Info;
import prioneer.homework.info.repository.InfoRepository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
@Controller
@RequiredArgsConstructor
@Slf4j
public class ExcelController {

    private final ExcelService excelService;
    private final InfoRepository infoRepository;

    @PostMapping("/excel")
    public ResponseEntity<byte[]> downloadExcel() {
        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            // 엑셀 파일 생성
            Sheet sheet = workbook.createSheet("피로그래밍 22기 과제 결과 반영표");

            // 제목 가져오기
            List<Info> titles = infoRepository.infoALl();
            List<Excel> all = excelService.getAll();

            // Header Row 설정
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("이름");
            for (int i = 0; i < titles.size(); i++) {
                headerRow.createCell(i + 1).setCellValue(titles.get(i).getContent());
            }

            // 데이터 행 작성
            for (int i = 0; i < all.size(); i++) {
                Excel excel = all.get(i);
                Row dataRow = sheet.createRow(i + 1);

                // 첫 번째 열: 이름
                dataRow.createCell(0).setCellValue(excel.getMember().getName());

                // 두 번째 열부터: Board 내용
                List<Board> boards = excel.getBoardExcels();
                for (int j = 0; j < titles.size() && j < boards.size(); j++) {
                    dataRow.createCell(j + 1).setCellValue(boards.get(j).getResult());
                }
            }

            // 엑셀 파일 작성
            workbook.write(out);

            // UTF-8로 파일 이름 인코딩
            String fileName = URLEncoder.encode("피로그래밍_22기_과제_결과_반영표.xlsx", StandardCharsets.UTF_8.toString())
                    .replace("+", "%20"); // 공백 처리

            // HTTP 응답 헤더 설정
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);

            return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
        } catch (IOException e) {
            log.error("엑셀 파일 생성 중 오류", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
