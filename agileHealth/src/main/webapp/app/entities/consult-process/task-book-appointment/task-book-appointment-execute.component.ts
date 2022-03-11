import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskBookAppointmentService from './task-book-appointment.service';
import { TaskBookAppointmentContext } from './task-book-appointment.model';

const validations: any = {
  taskContext: {
    consultProcess: {
      consult: {
        mode: {},
        medicalSpecialty: {},
        local: {},
        date: {},
      },
    },
  },
};

@Component({
  validations,
})
export default class TaskBookAppointmentExecuteComponent extends Vue {
  private taskBookAppointmentService: TaskBookAppointmentService = new TaskBookAppointmentService();
  private taskContext: TaskBookAppointmentContext = {};
  public isSaving = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.claimTaskInstance(to.params.taskInstanceId);
      }
    });
  }

  public claimTaskInstance(taskInstanceId) {
    this.taskBookAppointmentService.claim(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }

  public complete() {
    this.taskBookAppointmentService.complete(this.taskContext).then(res => {
      this.$router.go(-1);
    });
  }

  public initRelationships(): void {}
}
