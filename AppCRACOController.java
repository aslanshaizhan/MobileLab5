package kz.ecf.ns.controller;

import io.swagger.annotations.ApiOperation;
import kz.ecf.ns.dto.UpdateStatusDTO;
import kz.ecf.ns.model.applications.craco.AppCRACO;
import kz.ecf.ns.dto.ApplicationCracoDTO;
import kz.ecf.ns.service.IAppCRACOService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/private/v1/craco", produces = APPLICATION_JSON_VALUE)
public class AppCRACOController {

    private final IAppCRACOService cracoService;

    @PostMapping
    @ApiOperation(value = "Create or update craco application (status DRAFT)")
    public ResponseEntity<AppCRACO> save(@Valid @RequestBody ApplicationCracoDTO app) {
        return ResponseEntity.ok(cracoService.save(app));
    }


    @GetMapping("/{id}")
    public ResponseEntity<AppCRACO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(cracoService.getById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<AppCRACO>> getAll() {
        return ResponseEntity.ok(cracoService.getAll());
    }

    @GetMapping("/check/appId/{appId}/userId/{userId}/bpmId/{bpmId}")
    public ResponseEntity<Boolean> checkAppValidation(@PathVariable("bpmId") String bpmId,
                                                      @PathVariable("appId") Long appId,
                                                      @PathVariable("userId") String userId) {
        return ResponseEntity.ok(cracoService.checkAppValidation(bpmId, appId, userId));
    }

    @PutMapping("/update/status")
    public ResponseEntity<String> updateStatus(@Valid @RequestBody UpdateStatusDTO app) {
        cracoService.updateStatus(app);
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/issued")
    public ResponseEntity<String> applicationIssued(@Valid @RequestBody UpdateStatusDTO app) {
        cracoService.applicationIssued(app);
        return ResponseEntity.ok("OK");
    }


    // TODO Add update by ID

    // TODO ADD CONROLLESR GET all BY user&status, BY bpmId, all by userId, all By chief_id expert_id coordinator_id

}
