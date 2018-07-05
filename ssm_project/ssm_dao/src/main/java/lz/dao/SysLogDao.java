package lz.dao;

import lz.domain.SysLog;
import org.apache.ibatis.annotations.Insert;

public interface SysLogDao {

    @Insert("insert into sys_log(visitTime,username,ip,method)values(#{visitTime},#{username},#{ip},#{method})")
    void addLog(SysLog sysLog);
}
