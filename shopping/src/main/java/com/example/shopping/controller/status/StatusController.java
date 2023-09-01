package com.example.shopping.controller.status;

import com.example.shopping.domain.status.Status;
import com.example.shopping.service.status.StatusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
@RequestMapping("/status")
public class StatusController extends HttpServlet {
    private final StatusService statusService;
    private Map<String, Object> responseBody = null;

    @GetMapping
    public ResponseEntity<Map<String, Object>> getCargoStatus() {
        if(responseBody==null){

            List<Status> statuses = statusService.selectAll();
            Map<String, List<String>> largeStatus = new LinkedHashMap<>();
            Map<String, List<String>> detailStatus = new LinkedHashMap<>();
            initStatus(statuses, largeStatus, detailStatus);

            responseBody = new LinkedHashMap<>();
            responseBody.put("mainStatus", largeStatus);
            responseBody.put("detailStatus", detailStatus);
        }

        return ResponseEntity.ok(responseBody);
    }

    private void initStatus(List<Status> statuses, Map<String, List<String>> largeStatus,
                              Map<String, List<String>> detailStatus){
        for(Status status : statuses){
            if(status.getMasterStatusId()==null){
                largeStatus.put(status.getStatusId()+";"+status.getStatusName(),new ArrayList<>());
            }
        }

        setStatus(statuses, largeStatus, detailStatus);

    }

    private void setStatus(List<Status> statuses, Map<String, List<String>> statusMap,
                             Map<String, List<String>> nextStatusMap) {
        for (String statusKey : statusMap.keySet()) {
            String[] key = statusKey.split(";");
            for (Status status : statuses) {
                if(status.getMasterStatusId()==null) continue;
                if (Long.parseLong(key[0]) == status.getMasterStatusId()) { // 타입 변환 후 비교
                    List<String> list = statusMap.get(statusKey);
                    list.add(status.getStatusId() + ";" + status.getStatusName());
                    nextStatusMap.put(status.getStatusId() + ";" + status.getStatusName(), new ArrayList<>());
                }
            }
        }
    }
}