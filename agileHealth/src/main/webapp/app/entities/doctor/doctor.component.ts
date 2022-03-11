import { mixins } from 'vue-class-component';

import { Component, Vue, Inject } from 'vue-property-decorator';
import Vue2Filters from 'vue2-filters';
import { IDoctor } from '@/shared/model/doctor.model';

import DoctorService from './doctor.service';

@Component({
  mixins: [Vue2Filters.mixin],
})
export default class Doctor extends Vue {
  @Inject('doctorService') private doctorService: () => DoctorService;
  private removeId: number = null;

  public doctors: IDoctor[] = [];

  public isFetching = false;

  public mounted(): void {
    this.retrieveAllDoctors();
  }

  public clear(): void {
    this.retrieveAllDoctors();
  }

  public retrieveAllDoctors(): void {
    this.isFetching = true;

    this.doctorService()
      .retrieve()
      .then(
        res => {
          this.doctors = res.data;
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

  public prepareRemove(instance: IDoctor): void {
    this.removeId = instance.id;
    if (<any>this.$refs.removeEntity) {
      (<any>this.$refs.removeEntity).show();
    }
  }

  public removeDoctor(): void {
    this.doctorService()
      .delete(this.removeId)
      .then(() => {
        const message = this.$t('agileHealthApp.doctor.deleted', { param: this.removeId });
        this.$bvToast.toast(message.toString(), {
          toaster: 'b-toaster-top-center',
          title: 'Info',
          variant: 'danger',
          solid: true,
          autoHideDelay: 5000,
        });
        this.removeId = null;
        this.retrieveAllDoctors();
        this.closeDialog();
      });
  }

  public closeDialog(): void {
    (<any>this.$refs.removeEntity).hide();
  }
}
