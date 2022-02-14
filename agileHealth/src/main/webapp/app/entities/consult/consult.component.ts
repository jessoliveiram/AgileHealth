import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IConsult } from '@/shared/model/consult.model';

import ConsultService from './consult.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Consult extends Vue {
  @Inject('consultService') private consultService: () => ConsultService;
  private removeId: number = null;

  public consults: IConsult[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllConsults();
  }

  public clear(): void {
    this.retrieveAllConsults();
  }

  public retrieveAllConsults(): void {
    this.isFetching = true;

    this.consultService()
      .retrieve()
      .then(
        res => {
          this.consults = res.data;
          this.isFetching = false;
        },
        err => {
          this.isFetching = false;
        }
      );
  }

  public handleSyncList(): void {
    this.clear();
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
