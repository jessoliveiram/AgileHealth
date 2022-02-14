import { Component, Vue, Inject } from 'vue-property-decorator';

import { IConsultProcess } from '@/shared/model/consult-process.model';
import ConsultProcessService from './consult-process.service';

@Component
export default class ConsultProcessDetailsComponent extends Vue {
  @Inject('consultProcessService') private consultProcessService: () => ConsultProcessService;
  public consultProcess: IConsultProcess = {};

  public isFetching: boolean = false;

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.processInstanceId) {
        vm.retrieveConsultProcess(to.params.processInstanceId);
      }
    });
  }

  public retrieveConsultProcess(consultProcessId) {
    this.isFetching = true;
    this.consultProcessService()
      .find(consultProcessId)
      .then(
        res => {
          this.consultProcess = res;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public previousState() {
    this.$router.go(-1);
  }
}
