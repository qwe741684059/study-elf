package stu.cmq.service;

import stu.cmq.domain.Memorandum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author kamifeng
 * @date 2023/4/24
 */

public interface MemorandumService {
    /**
     * 寻找备忘录
     * @param userId
     * @return List
     */
    Map<String,List<Memorandum>> selectMemorandumList(String userId);

    /**
     * 新增备忘录
     * @param memorandum
     */
    void insertMemorandum(Memorandum memorandum);

    /**
     * 删除备忘录
     * @param memorandum
     */
    void deleteMemorandum(Memorandum memorandum);

    /**
     * 修改备忘录
     * @param memorandum
     */
    void updateMemorandum(Memorandum memorandum);

    /**
     * 获取时间list
     * @param userId
     * @return
     */
    List<String> getTimeList(String userId);
}
