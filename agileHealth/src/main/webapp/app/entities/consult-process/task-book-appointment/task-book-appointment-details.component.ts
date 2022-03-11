import { Component, Vue, Inject } from 'vue-property-decorator';

import TaskBookAppointmentService from './task-book-appointment.service';
import { TaskBookAppointmentContext } from './task-book-appointment.model';

@Component
export default class TaskBookAppointmentDetailsComponent extends Vue {
  private taskBookAppointmentService: TaskBookAppointmentService = new TaskBookAppointmentService();
  private taskContext: TaskBookAppointmentContext = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.taskInstanceId) {
        vm.retrieveContext(to.params.taskInstanceId);
      }
    });
  }

  public retrieveContext(taskInstanceId) {
    this.taskBookAppointmentService.loadContext(taskInstanceId).then(res => {
      this.taskContext = res;
    });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
