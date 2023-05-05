package stu.cmq.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import stu.cmq.domain.File;
import stu.cmq.domain.Memorandum;
import stu.cmq.mapper.MemorandumMapper;
import stu.cmq.service.MemorandumService;

import java.time.LocalDateTime;
import java.util.*;

/**
 * @author kamifeng
 * @date 2023/4/24
 */

@Service
@RequiredArgsConstructor
public class MemorandumServiceImpl implements MemorandumService {

    private final MemorandumMapper memorandumMapper;

    @Override
    public Map<String, List<Memorandum>> selectMemorandumList(String userId) {
        QueryWrapper<Memorandum> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Memorandum> memorandumList = memorandumMapper.selectList(queryWrapper);
        List<Memorandum> futureList = new ArrayList<>();
        List<Memorandum> pastList = new ArrayList<>();
        for (Memorandum memorandum : memorandumList) {
            if (memorandum.getMemorandumTime().isAfter(LocalDateTime.now())) {
                futureList.add(memorandum);
            } else {
                pastList.add(memorandum);
            }
        }
        futureList.sort(Comparator.comparing(Memorandum::getMemorandumTime));
        pastList.sort(Comparator.comparing(Memorandum::getMemorandumTime).reversed());
        Map<String, List<Memorandum>> map = new HashMap<>(2);
        map.put("futureList", futureList);
        map.put("pastList", pastList);
        return map;
    }

    @Override
    public void insertMemorandum(Memorandum memorandum) {
        memorandumMapper.insert(memorandum);
    }

    @Override
    public void deleteMemorandum(Memorandum memorandum) {
        memorandumMapper.deleteById(memorandum);
    }

    @Override
    public void updateMemorandum(Memorandum memorandum) {
        memorandumMapper.updateById(memorandum);
    }

    @Override
    public List<String> getTimeList(String userId) {
        QueryWrapper<Memorandum> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Memorandum> memorandumList = memorandumMapper.selectList(queryWrapper);
        List<String> list = new ArrayList<>();
        for (Memorandum memorandum : memorandumList) {
            int year = memorandum.getMemorandumTime().getYear();
            int month = memorandum.getMemorandumTime().getMonth().getValue();

            int day = memorandum.getMemorandumTime().getDayOfMonth();

            String monthString;
            String dayString;
            if (month >= 10) {
                monthString = month+"";
            } else{
                monthString = "0" + month;
            }
            if (day >=10) {
                dayString = day + "";
            } else {
                dayString = "0" + day;
            }
            list.add(year + "-" + monthString + "-" + dayString);

        }
        return list;
    }
}
