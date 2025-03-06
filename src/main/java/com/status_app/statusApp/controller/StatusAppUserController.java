package com.status_app.statusApp.controller;

import java.util.List;

import com.status_app.statusApp.dto.IncidentDTO;
import com.status_app.statusApp.dto.IncidentUpdateDTO;
import com.status_app.statusApp.dto.ServiceDTO;
import com.status_app.statusApp.service.StatusAppService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "${cors.allowed.origins}")
public class StatusAppUserController {

    @Autowired
    StatusAppService statusAppService;

    @GetMapping("/services")
    public ResponseEntity<List<ServiceDTO>> getServiceList(){
        List<ServiceDTO> serviceListResp=statusAppService.getServices();
        if(!CollectionUtils.isEmpty(serviceListResp)) {
            return ResponseEntity.status(HttpStatus.OK).body(serviceListResp);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @GetMapping("/incidents")
    public ResponseEntity<List<IncidentDTO>> getIncidentList() {
        List<IncidentDTO> incidentListResp=statusAppService.getIncidents();
        if(!CollectionUtils.isEmpty(incidentListResp)) {
            return ResponseEntity.status(HttpStatus.OK).body(incidentListResp);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

    @PostMapping("/incidents")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incident created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, incident creation failed")
    })
    public ResponseEntity<String> postIncident(
        @RequestBody IncidentDTO incidentDTO
    ) {
        boolean success = statusAppService.createIncident(incidentDTO);
        if(success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failure");
    }

    @PostMapping("/incidentUpdates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Incident created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, incident creation failed")
    })
    public ResponseEntity<String> postIncidentUpdate(
            @RequestBody IncidentUpdateDTO incidentUpdateDTO
    ) {
        boolean success = statusAppService.createIncidentUpdate(incidentUpdateDTO);
        if(success) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failure");
    }

    @GetMapping("/incidents/{incidentId}/incidentUpdates")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Incident updates retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request, incident updates retrieval failed")
    })
    public ResponseEntity<List<IncidentUpdateDTO>> getIncidentUpdatesList(@PathVariable String incidentId){
        List<IncidentUpdateDTO> incidentUpdateLists=statusAppService.getIncidentUpdates(incidentId);
        if(!CollectionUtils.isEmpty(incidentUpdateLists)) {
            // sort in descending order of updatedAt
            incidentUpdateLists.sort((update1, update2) -> update2.getUpdatedAt().compareTo(update1.getUpdatedAt()));
            return ResponseEntity.status(HttpStatus.OK).body(incidentUpdateLists);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }

}
