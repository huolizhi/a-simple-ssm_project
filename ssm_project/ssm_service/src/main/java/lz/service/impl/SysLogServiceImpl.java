package lz.service.impl;

import lz.dao.SysLogDao;
import lz.domain.SysLog;
import lz.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SysLogServiceImpl implements SysLogService {
    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void addLog(SysLog sysLog) {
        sysLogDao.addLog(sysLog);
    }
}
