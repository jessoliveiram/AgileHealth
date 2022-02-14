import { Component, Vue, Inject } from 'vue-property-decorator';

import { IConsult } from '@/shared/model/consult.model';
import ConsultService from './consult.service';

@Component
export default class ConsultDetails extends Vue {
  @Inject('consultService') private consultService: () => ConsultService;
  public consult: IConsult = {};

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.consultId) {
        vm.retrieveConsult(to.params.consultId);
      }
    });
  }

  public retrieveConsult(consultId) {
    this.consultService()
      .find(consultId)
      .then(res => {
        this.consult = res;
      });
  }

  public previousState() {
    this.$router.go(-1);
  }
}
