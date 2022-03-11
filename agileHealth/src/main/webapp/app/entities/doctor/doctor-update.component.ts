import { Component, Vue, Inject } from 'vue-property-decorator';

import { IDoctor, Doctor } from '@/shared/model/doctor.model';
import DoctorService from './doctor.service';

const validations: any = {
  doctor: {
    name: {},
  },
};

@Component({
  validations,
})
export default class DoctorUpdate extends Vue {
  @Inject('doctorService') private doctorService: () => DoctorService;
  public doctor: IDoctor = new Doctor();
  public isSaving = false;
  public currentLanguage = '';

  beforeRouteEnter(to, from, next) {
    next(vm => {
      if (to.params.doctorId) {
        vm.retrieveDoctor(to.params.doctorId);
      }
    });
  }

  created(): void {
    this.currentLanguage = this.$store.getters.currentLanguage;
    this.$store.watch(
      () => this.$store.getters.currentLanguage,
      () => {
        this.currentLanguage = this.$store.getters.currentLanguage;
      }
    );
  }

  public save(): void {
    this.isSaving = true;
    if (this.doctor.id) {
      this.doctorService()
        .update(this.doctor)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('agileHealthApp.doctor.updated', { param: param.id });
          return this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Info',
            variant: 'info',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    } else {
      this.doctorService()
        .create(this.doctor)
        .then(param => {
          this.isSaving = false;
          this.$router.go(-1);
          const message = this.$t('agileHealthApp.doctor.created', { param: param.id });
          this.$root.$bvToast.toast(message.toString(), {
            toaster: 'b-toaster-top-center',
            title: 'Success',
            variant: 'success',
            solid: true,
            autoHideDelay: 5000,
          });
        });
    }
  }

  public retrieveDoctor(doctorId): void {
    this.doctorService()
      .find(doctorId)
      .then(res => {
        this.doctor = res;
      });
  }

  public previousState(): void {
    this.$router.go(-1);
  }

  public initRelationships(): void {}
}
