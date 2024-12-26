package prioneer.homework.excel.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import prioneer.homework.excel.domain.Excel;
import prioneer.homework.excel.repository.ExcelRepository;
import prioneer.homework.member.domain.Member;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExcelService{

    private final ExcelRepository excelRepository;

    public List<Excel> getAll(){
        List<Member> allMember = excelRepository.getAllMember();
        return excelRepository.getAllInfo(allMember);
    }
}
