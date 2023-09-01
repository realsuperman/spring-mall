package com.example.shopping.service.status;

import com.example.shopping.dao.status.StatusDao;
import com.example.shopping.domain.status.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class StatusService {
    private final StatusDao statusDao;

    public List<Status> selectAll(){
        return statusDao.selectAll();
    }

}