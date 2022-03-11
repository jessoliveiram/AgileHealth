package br.ufrj.agilehealth.process.consultProcess;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consult-process/task-book-appointment")
public class TaskBookAppointmentController {

    private final Logger log = LoggerFactory.getLogger(TaskBookAppointmentController.class);

    private final TaskBookAppointmentService taskBookAppointmentService;

    public TaskBookAppointmentController(TaskBookAppointmentService taskBookAppointmentService) {
        this.taskBookAppointmentService = taskBookAppointmentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskBookAppointmentContextDTO> loadContext(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskBookAppointmentContextDTO taskBookAppointmentContext = taskBookAppointmentService.loadContext(id);
        return ResponseEntity.ok(taskBookAppointmentContext);
    }

    @GetMapping("/{id}/claim")
    public ResponseEntity<TaskBookAppointmentContextDTO> claim(@PathVariable Long id) {
        log.debug("REST request to load the context of task hotel {}", id);
        TaskBookAppointmentContextDTO taskBookAppointmentContext = taskBookAppointmentService.claim(id);
        return ResponseEntity.ok(taskBookAppointmentContext);
    }

    @PostMapping("/complete")
    public ResponseEntity<Void> complete(@RequestBody TaskBookAppointmentContextDTO taskBookAppointmentContext) {
        log.debug("REST request to complete ConsultProcess.TaskBookAppointment {}", taskBookAppointmentContext.getTaskInstance().getId());
        taskBookAppointmentService.complete(taskBookAppointmentContext);
        return ResponseEntity.noContent().build();
    }
}
